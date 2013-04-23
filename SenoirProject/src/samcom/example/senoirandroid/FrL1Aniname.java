package samcom.example.senoirandroid;


import java.util.Random;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


@SuppressWarnings("deprecation")
public class FrL1Aniname extends Activity {

	View HidePosition;
	ImageView AnswerChar;
	String username;
	long startTime;
	final Context context = this;
	int timeRemain;
	boolean firstSound;
	boolean OnPause = false;
	boolean RunningCount = false;
	int Round;
	int Begin = 1;
	MediaPlayer instructPage,soundMain;
	MyCountDown countdownTime;
	
	public class MyCountDown extends CountDownTimer {
		public MyCountDown(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
		// TODO Auto-generated constructor stub
		}
		
		@Override
		public void onFinish() { 
		// TODO Auto-generated method stub
			RunningCount = false;
			showTimeout();
		}
		
		@Override
		public void onTick(long remain) { 
		// TODO Auto-generated method stub
			RunningCount = true;
			TextView result = (TextView) findViewById(R.id.textTime);
			timeRemain = (int) (remain) / 1000;
			result.setText(" Times: " + timeRemain);
		}
	}

@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.farm_l1_aniname);
		
		soundMain = MediaPlayer.create(context, R.raw.main);
		soundMain.start();
		soundMain.setLooping(true);
		soundMain.setVolume(30, 30);
	
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		username = myDb.SelectCurrentUser();
	
		//mediaPlayer.start();
		Round = myDb.getNumRound("016", username);
					
		myDb.getWritableDatabase();
		myDb.emptyNumberTable();
		
		game016();

		
	}
	void stopTime(){
		ImageView instructFing = (ImageView)findViewById(R.id.finger);
		if(RunningCount == true){
			countdownTime.cancel();
			if(instructFing.isEnabled()){
				instructFing.clearAnimation();
			}
		}	
	}
	void game016(){
		int scores;
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		int count = myDb.CountNumRan();
		int Random = 0;
		final LoginManage myUser = new LoginManage(this);
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/teddy.ttf");
		
		username = myDb.SelectCurrentUser();
		if(!(username.equals("Guest"))){
			TextView result = (TextView) findViewById(R.id.textUser);
			result.setTypeface(type);
			//result.setTextAppearance(getApplicationContext(),R.style.AudioFileInfoOverlayText);
			result.setTextColor(Color.rgb(2, 101, 203));
			result.setVisibility(TextView.VISIBLE);
			result.setText(username);
			Button LogoutBt = (Button) findViewById(R.id.logout);
			LogoutBt.setVisibility(Button.VISIBLE);
			Button LoginBt = (Button) findViewById(R.id.loginn);
			LoginBt.setVisibility(Button.INVISIBLE);
		}
		
		if((username.equals("Guest"))){
			TextView result = (TextView) findViewById(R.id.textUser);
			result.setVisibility(TextView.INVISIBLE);
			Button LogoutBt = (Button) findViewById(R.id.logout);
			LogoutBt.setVisibility(Button.INVISIBLE);
			Button LoginBt = (Button) findViewById(R.id.loginn);
			LoginBt.setVisibility(Button.VISIBLE);
		}
		
				
		Button loginBut = (Button)findViewById(R.id.loginn);
		loginBut.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				myUser.showLoginPopup();

			}
		});
		
		Button LogoutButton = (Button)findViewById(R.id.logout);
		LogoutButton.setOnClickListener(new View.OnClickListener() {
			 
			public void onClick(View v) {	
				myDb.logoutUser(username);
				instructPage.stop();
				Intent intent = new Intent(FrL1Aniname.this,Main.class);
				intent.putExtra("Logout", 1);
				startActivity(intent);
			}
			
		});
		if(OnPause == false){
			if(count < 10){
				Random = RanNum();
				checkAnswer(Random,count+1);
			}
			else{
				scores = myDb.countScore("016", username, Round);
				countdownTime.cancel();
				showPopup(scores);
			}
		}

	}
	
	int RanNum(){
		int randomInt;
		Boolean isExited;
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		
		do{
			Random randomGenerator = new Random();
			randomInt = randomGenerator.nextInt(10)+1;
			isExited = myDb.checkNumber(randomInt);
		}while(isExited);
		myDb.close();
		myDb.getWritableDatabase();
		myDb.insertRanNumber(randomInt);
		return randomInt;
	}
	
	void checkAnswer(final int RandomNum,final int item){
		
		final Button btA = (Button)findViewById(R.id.ansA);
		final Button btB = (Button)findViewById(R.id.ansB);
		final Button btC = (Button)findViewById(R.id.ansC);
		final Button btD = (Button)findViewById(R.id.ansD);
		final Button btE = (Button)findViewById(R.id.ansE);
		final Button btF = (Button)findViewById(R.id.ansF);
		final Button btG = (Button)findViewById(R.id.ansG);
		final Button btH = (Button)findViewById(R.id.ansH);
		final Button btI = (Button)findViewById(R.id.ansI);
		final Button btK = (Button)findViewById(R.id.ansK);
		final Button btN = (Button)findViewById(R.id.ansN);
		final Button btO = (Button)findViewById(R.id.ansO);
		final Button btP = (Button)findViewById(R.id.ansP);
		final Button btR = (Button)findViewById(R.id.ansR);
		final Button btS = (Button)findViewById(R.id.ansS);
		final Button btT = (Button)findViewById(R.id.ansT);
		final Button btU = (Button)findViewById(R.id.ansU);
		final Button btX = (Button)findViewById(R.id.ansX);
		
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		final char answer;
		final MediaPlayer soundCorrect = MediaPlayer.create(context, R.raw.crab_sound);
		final MediaPlayer soundWrong = MediaPlayer.create(context, R.raw.wrong_sound2);
		
		startTime = (20)*1000;
		countdownTime = new MyCountDown(startTime,1000);
		
		final float countTime = (float) startTime /1000;
		
		
		final View imgWrong = (View)findViewById(R.id.showwrong); 
		final View imgCorrect = (View)findViewById(R.id.showcorrect);
		imgWrong.setClickable(false);
		imgCorrect.setClickable(false);
		TextView current = (TextView) findViewById(R.id.currentitem);
		current.setText(item +"/ 10");
		
		char[] words = setBG(RandomNum);
		int lengths = words.length;
		Random randomGenerator = new Random();
		int randomChar = randomGenerator.nextInt(lengths);
		answer = choice(words,lengths,randomChar);
		
		
		final MediaPlayer soundAns = MediaPlayer.create(context, R.raw.choose_correct_ans);
		final View helpAnswer = (View)findViewById(R.id.showAnswer);
		final Animation myFadeonceAnimation = AnimationUtils.loadAnimation(FrL1Aniname.this, R.anim.tween_once);
		final Animation myFadeAnimation = AnimationUtils.loadAnimation(FrL1Aniname.this, R.anim.tween);
		final ImageView instructFinger = (ImageView)findViewById(R.id.finger);
		
		countdownTime.start();
		instructPage.start();
		

		if(Round == 1 || (username.equals("Guest") && item == 1)){
        	instructFinger.startAnimation(myFadeonceAnimation);	
        }
		
        		
		instructPage.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundCorrect) {
            	if(Round == 1 || (username.equals("Guest") && item == 1)){
            		helpAnswer.startAnimation(myFadeAnimation);
	            	soundAns.start();
            	}
            }
        });
	
			
				btA.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						if(Round == 1 || (username.equals("Guest") && item == 1)){
							helpAnswer.clearAnimation();
						}
						AnswerChar.setImageResource(R.drawable.aa);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'A'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("016",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("016",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btB.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						if(Round == 1 || (username.equals("Guest") && item == 1)){
							helpAnswer.clearAnimation();
						}
						AnswerChar.setImageResource(R.drawable.ab);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'B'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("016",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("016",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btC.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						if(Round == 1 || (username.equals("Guest") && item == 1)){
							helpAnswer.clearAnimation();
						}
						AnswerChar.setImageResource(R.drawable.ac);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'C'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("016",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("016",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btD.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						if(Round == 1 || (username.equals("Guest") && item == 1)){
							helpAnswer.clearAnimation();
						}
						AnswerChar.setImageResource(R.drawable.ad);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'D'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("016",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("016",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btE.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						if(Round == 1 || (username.equals("Guest") && item == 1)){
							helpAnswer.clearAnimation();
						}
						AnswerChar.setImageResource(R.drawable.ae);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'E'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("016",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("016",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btF.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						if(Round == 1 || (username.equals("Guest") && item == 1)){
							helpAnswer.clearAnimation();
						}
						AnswerChar.setImageResource(R.drawable.af);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'F'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("016",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("016",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btG.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						if(Round == 1 || (username.equals("Guest") && item == 1)){
							helpAnswer.clearAnimation();
						}
						AnswerChar.setImageResource(R.drawable.ag);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'G'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("016",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("016",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btH.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						if(Round == 1 || (username.equals("Guest") && item == 1)){
							helpAnswer.clearAnimation();
						}
						AnswerChar.setImageResource(R.drawable.ah);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'H'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("016",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("016",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btI.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						if(Round == 1 || (username.equals("Guest") && item == 1)){
							helpAnswer.clearAnimation();
						}
						AnswerChar.setImageResource(R.drawable.ai);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'I'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("016",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("016",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btK.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						if(Round == 1 || (username.equals("Guest") && item == 1)){
							helpAnswer.clearAnimation();
						}
						AnswerChar.setImageResource(R.drawable.ak);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'K'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("016",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("016",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				
				btN.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						if(Round == 1 || (username.equals("Guest") && item == 1)){
							helpAnswer.clearAnimation();
						}
						AnswerChar.setImageResource(R.drawable.an);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'N'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("016",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("016",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btO.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						if(Round == 1 || (username.equals("Guest") && item == 1)){
							helpAnswer.clearAnimation();
						}
						AnswerChar.setImageResource(R.drawable.ao);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'O'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("016",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("016",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btP.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						if(Round == 1 || (username.equals("Guest") && item == 1)){
							helpAnswer.clearAnimation();
						}
						AnswerChar.setImageResource(R.drawable.ap);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'P'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("016",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("016",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btR.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						if(Round == 1 || (username.equals("Guest") && item == 1)){
							helpAnswer.clearAnimation();
						}
						AnswerChar.setImageResource(R.drawable.ar);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'R'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("016",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("016",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btS.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						if(Round == 1 || (username.equals("Guest") && item == 1)){
							helpAnswer.clearAnimation();
						}
						AnswerChar.setImageResource(R.drawable.as);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'S'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("016",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("016",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btT.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						if(Round == 1 || (username.equals("Guest") && item == 1)){
							helpAnswer.clearAnimation();
						}
						AnswerChar.setImageResource(R.drawable.at);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'T'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("016",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("016",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btU.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						if(Round == 1 || (username.equals("Guest") && item == 1)){
							helpAnswer.clearAnimation();
						}
						AnswerChar.setImageResource(R.drawable.au);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'U'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("016",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("016",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btX.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						if(Round == 1 || (username.equals("Guest") && item == 1)){
							helpAnswer.clearAnimation();
						}
						AnswerChar.setImageResource(R.drawable.ax);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'X'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("016",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("016",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				
		final MediaPlayer soundShowAns = MediaPlayer.create(context, R.raw.sclv2_red);
		
		soundCorrect.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundCorrect) {
            	imgCorrect.setVisibility(View.INVISIBLE);
            	HidePosition.setVisibility(View.INVISIBLE);
            	AnswerChar.setVisibility(View.INVISIBLE);
            	soundShowAns.start();
            	//game016();
            }
        });

		soundWrong.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	imgWrong.setVisibility(View.INVISIBLE);
            	HidePosition.setVisibility(View.INVISIBLE);
            	AnswerChar.setVisibility(View.INVISIBLE);
            	soundShowAns.start();
            	//game016();
            }
        });
		soundShowAns.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	game016();
            }
        });
		
		Button HelpButton = (Button)findViewById(R.id.aninameHelp);
		HelpButton.setOnClickListener(new View.OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//countdownTime.cancel();
				helpAnswer.startAnimation(myFadeonceAnimation);
			}
		});
		
		Button HomeButton = (Button)findViewById(R.id.aninamebackHome);
		HomeButton.setOnClickListener(new View.OnClickListener() {
		
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			stopTime();
			Intent in = new Intent(FrL1Aniname.this,Main.class);
			in.putExtra("showPopup", 1);
			startActivity(in);
		}
		});
	}
	
	char[] setBG(int random){
		
		char [][] Answer = {{'A','N','T'},{'C','A','T'},{'D','O','G'},{'H','E','N'},
				{'R','A','T'},{'P','I','G'},{'D','U','C','K'},{'B','I','R','D'},
				{'F','I','S','H'},{'O','X'}};

		AbsoluteLayout Thislayout=(AbsoluteLayout)findViewById(R.id.aninameLayout);
	
		switch(random){
		case 1:
			Thislayout.setBackgroundResource(R.drawable.frlv1_bg1);
			break;
		case 2:
			Thislayout.setBackgroundResource(R.drawable.frlv1_bg2);
			break;
		case 3:
			Thislayout.setBackgroundResource(R.drawable.frlv1_bg3);
			break;
		case 4:
			Thislayout.setBackgroundResource(R.drawable.frlv1_bg4);
			break;
		case 5:
			Thislayout.setBackgroundResource(R.drawable.frlv1_bg5);
			break;
		case 6:
			Thislayout.setBackgroundResource(R.drawable.frlv1_bg6);
			break;
		case 7:
			Thislayout.setBackgroundResource(R.drawable.frlv1_bg7);
			break;
		case 8:
			Thislayout.setBackgroundResource(R.drawable.frlv1_bg8);
			break;
		case 9:
			Thislayout.setBackgroundResource(R.drawable.frlv1_bg9);
			break;
		default:
			Thislayout.setBackgroundResource(R.drawable.frlv1_bg10);
			break;
		}
		instructPage = MediaPlayer.create(context, R.raw.hs_ins2);
		return Answer[random-1];
	}

	char choice(char[] word,int length,int ranChar){

		char answer = word[ranChar];

		if(length == 3){
			switch(ranChar){
				case 0:
					HidePosition = (View)findViewById(R.id.img5);
					AnswerChar = (ImageView)findViewById(R.id.ansImg5);
					break;
				case 1:
					HidePosition = (View)findViewById(R.id.img6);
					AnswerChar = (ImageView)findViewById(R.id.ansImg6);
					break;
				case 2:
					HidePosition = (View)findViewById(R.id.img7);
					AnswerChar = (ImageView)findViewById(R.id.ansImg7);
					break;
			}
			
		}
		else{
			if(length == 2){
				ranChar += 1;
			}
			
			switch(ranChar){
				case 0:
					HidePosition = (View)findViewById(R.id.img1);
					AnswerChar = (ImageView)findViewById(R.id.ansImg1);
					break;
				case 1:
					HidePosition = (View)findViewById(R.id.img2);
					AnswerChar = (ImageView)findViewById(R.id.ansImg2);
					break;
				case 2:
					HidePosition = (View)findViewById(R.id.img3);
					AnswerChar = (ImageView)findViewById(R.id.ansImg3);
					break;
				case 3:
					HidePosition = (View)findViewById(R.id.img4);
					AnswerChar = (ImageView)findViewById(R.id.ansImg4);
					break;

			}
		}
		
		HidePosition.setVisibility(View.VISIBLE);
		//AnswerChar.setVisibility(View.VISIBLE);
		
		return answer;
		
	}
	
	protected void showPopup(int scores){

		// custom dialog
		final Dialog dialog = new Dialog(context, R.style.FullHeightDialog);
		dialog.setContentView(R.layout.activity_dialog_score_sclv1g1);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false); 
		
	
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		 
		switch(scores){
				case 10: ImageView score5 = (ImageView)dialog.findViewById(R.id.star5); 
						score5.setVisibility(ImageView.VISIBLE);	break;
				case 9: ImageView score4_5 = (ImageView)dialog.findViewById(R.id.star4_5); 
						score4_5.setVisibility(ImageView.VISIBLE);	break;	
				case 8: ImageView score4 = (ImageView)dialog.findViewById(R.id.star4); 
						score4.setVisibility(ImageView.VISIBLE);	break;			
				case 7: ImageView score3_5 = (ImageView)dialog.findViewById(R.id.star3_5); 
						score3_5.setVisibility(ImageView.VISIBLE);	break;
				case 6: ImageView score3 = (ImageView)dialog.findViewById(R.id.star3); 
						score3.setVisibility(ImageView.VISIBLE);	break;
				case 5: ImageView score2_5 = (ImageView)dialog.findViewById(R.id.star2_5); 
						score2_5.setVisibility(ImageView.VISIBLE);	break;	
				case 4: ImageView score2 = (ImageView)dialog.findViewById(R.id.star2); 
						score2.setVisibility(ImageView.VISIBLE);	break;	
				case 3: ImageView score1_5 = (ImageView)dialog.findViewById(R.id.star1_5); 
						score1_5.setVisibility(ImageView.VISIBLE);	break;
				case 2: ImageView score1 = (ImageView)dialog.findViewById(R.id.star1); 
						score1.setVisibility(ImageView.VISIBLE);	break;
				case 1: ImageView score0_5 = (ImageView)dialog.findViewById(R.id.star0_5); 
						score0_5.setVisibility(ImageView.VISIBLE);	break;		
				default: ImageView score0 = (ImageView)dialog.findViewById(R.id.star0); 
						score0.setVisibility(ImageView.VISIBLE);	break;			
		}
		
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/hbo.ttf");
		TextView textCorrect = (TextView)dialog.findViewById(R.id.ScoreCorrect);
		TextView textWrong = (TextView)dialog.findViewById(R.id.ScoreWrong);
		textCorrect.setTypeface(type);
		textWrong.setTypeface(type);
		textCorrect.setTextColor(Color.BLACK);
		textWrong.setTextColor(Color.BLACK);
		String number;
		number = String.valueOf(scores);
		textCorrect.setText(number+" ข้อ");
		number = String.valueOf(10-scores);
		textWrong.setText(number+" ข้อ");
		
		Button dialogHomeBt = (Button)dialog.findViewById(R.id.scorehome);
		dialogHomeBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent in = new Intent(FrL1Aniname.this,Main.class);
				in.putExtra("showPopup", 1);
				startActivity(in);
				
			}
		});
		
		Button dialogReplyBt = (Button)dialog.findViewById(R.id.scoreback);
		dialogReplyBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				
				myDb.getWritableDatabase();
				myDb.emptyNumberTable();
				myDb.close();
				
				myDb.getReadableDatabase();
				final View imgWrongpop = (View)findViewById(R.id.showwrong); 
				final View imgCorrectpop = (View)findViewById(R.id.showcorrect);
				imgWrongpop.setVisibility(View.INVISIBLE);
				imgCorrectpop.setVisibility(View.INVISIBLE);
				
				Round = myDb.getNumRound("016", username);
				//Round++;
				
				game016();
			}
		});
		
		Button dialogNextBt = (Button)dialog.findViewById(R.id.scorenext);
		dialogNextBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(FrL1Aniname.this,FrL2Shadow.class);
				startActivity(intent);
			}
		});
		dialog.show();

	}	
	
	void showTimeout(){
		
		final View imgWrongFin = (View)findViewById(R.id.showwrong); 
		imgWrongFin.setVisibility(View.VISIBLE);
		imgWrongFin.setClickable(false);
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		int item = myDb.CountNumRan();
		myDb.close();
		myDb.getWritableDatabase();
		myDb.addItemScore("016",username,Round,item,0,startTime/1000);
		
		final MediaPlayer soundWrongFin = MediaPlayer.create(context, R.raw.wrong_sound2);
		soundWrongFin.start();
		
		soundWrongFin.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	imgWrongFin.setVisibility(View.INVISIBLE);
            	game016();
            }
        });
		
	}
	
	public boolean onTouchEvent (MotionEvent event) {
		
		instructPage.start();
		return super.onTouchEvent(event);
	}
	
	protected void onRestart() {
		// TODO Auto-generated method stub
		
		Intent intent = new Intent(FrL1Aniname.this,Main.class);
		startActivity(intent);
		
		
		super.onRestart();
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		
		username = myDb.SelectCurrentUser();
		
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/teddy.ttf");
		TextView result = (TextView) findViewById(R.id.textUser);
		result.setTypeface(type);
		result.setTextColor(Color.rgb(2, 101, 203));
		Button LogoutBt = (Button) findViewById(R.id.logout);
		Button LoginBt = (Button) findViewById(R.id.loginn);
		
		if(!(username.equals("Guest"))){
			result.setVisibility(TextView.VISIBLE);
			result.setText(username);
			LogoutBt.setVisibility(Button.VISIBLE);
			LoginBt.setVisibility(Button.INVISIBLE);
		}
		if((username.equals("Guest"))){
			result.setVisibility(TextView.INVISIBLE);
			LogoutBt.setVisibility(Button.INVISIBLE);
			LoginBt.setVisibility(Button.VISIBLE);
		}
		
		super.onWindowFocusChanged(hasFocus);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		stopTime();
		if(soundMain.isLooping()){
    		soundMain.stop();
    	}
    	if(instructPage.isPlaying()){
    		instructPage.stop();
    	}
		super.onDestroy();
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		stopTime();
		if(soundMain.isLooping()){
    		soundMain.stop();
    	}
    	if(instructPage.isPlaying()){
    		instructPage.stop();
    	}
    	OnPause = true;
		super.onPause();
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	stopTime();
			Intent in = new Intent(FrL1Aniname.this,Main.class);
			in.putExtra("showPopup", 1);
			startActivity(in);  
        	return false;
        }
	    return super.onKeyDown(keyCode, event);
	}
}