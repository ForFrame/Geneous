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
public class HpL2Body extends Activity {

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
		setContentView(R.layout.hospital_l2_body);
		
		soundMain = MediaPlayer.create(context, R.raw.main);
		soundMain.start();
		soundMain.setLooping(true);
		soundMain.setVolume(30, 30);
	
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		username = myDb.SelectCurrentUser();
	
		//mediaPlayer.start();
		Round = myDb.getNumRound("014", username);
		
					
		myDb.getWritableDatabase();
		myDb.emptyNumberTable();
		
		game014();

		
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
	void game014(){
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
				Intent intent = new Intent(HpL2Body.this,Main.class);
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
				scores = myDb.countScore("014", username, Round);
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
		final Button btD = (Button)findViewById(R.id.ansD);
		final Button btE = (Button)findViewById(R.id.ansE);
		final Button btF = (Button)findViewById(R.id.ansF);
		final Button btG = (Button)findViewById(R.id.ansG);
		final Button btH = (Button)findViewById(R.id.ansH);
		final Button btI = (Button)findViewById(R.id.ansI);
		final Button btL = (Button)findViewById(R.id.ansL);
		final Button btM = (Button)findViewById(R.id.ansM);
		final Button btN = (Button)findViewById(R.id.ansN);
		final Button btO = (Button)findViewById(R.id.ansO);
		final Button btR = (Button)findViewById(R.id.ansR);
		final Button btS = (Button)findViewById(R.id.ansS);
		final Button btT = (Button)findViewById(R.id.ansT);
		final Button btU = (Button)findViewById(R.id.ansU);
		final Button btY = (Button)findViewById(R.id.ansY);
		
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		final char answer;
		final MediaPlayer soundCorrect = MediaPlayer.create(context, R.raw.crab_sound);
		final MediaPlayer soundWrong = MediaPlayer.create(context, R.raw.wrong_sound2);
		
		startTime = (20)*1000;
		countdownTime = new MyCountDown(startTime,1000);
		
		final float countTime = (float) startTime /1000;
		if(firstSound == true){
			timeRemain = (int)countTime;
		}
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
		final Animation myFadeonceAnimation = AnimationUtils.loadAnimation(HpL2Body.this, R.anim.tween_once);
		final Animation myFadeAnimation = AnimationUtils.loadAnimation(HpL2Body.this, R.anim.tween);
		final ImageView instructFinger = (ImageView)findViewById(R.id.finger);
		
		if(Round == 1 || (username.equals("Guest") && item == 1)){
			instructPage.start();
			firstSound = true;
		}
		else{
			startTime = (20)*1000;
			countdownTime = new MyCountDown(startTime,1000);
			countdownTime.start();	
			instructPage.start();
		}
		
		instructPage.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundCorrect) {
            	if(Round == 1 || (username.equals("Guest") && item == 1)){
            		if(firstSound == true){
            			instructFinger.startAnimation(myFadeAnimation);
            			firstSound = false;
            		}
            		else{
	            		helpAnswer.startAnimation(myFadeonceAnimation);
	            		startTime = (20)*1000;
	        			countdownTime = new MyCountDown(startTime,1000);
	        			countdownTime.start();
	        			instructFinger.clearAnimation();
	            		soundAns.start();
            		}
            	}
            }
        });
	
			
				btA.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						AnswerChar.setImageResource(R.drawable.a);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'A'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("014",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("014",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btD.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						AnswerChar.setImageResource(R.drawable.d);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'D'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("014",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("014",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btE.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						AnswerChar.setImageResource(R.drawable.e);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'E'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("014",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("014",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btF.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						AnswerChar.setImageResource(R.drawable.f);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'F'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("014",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("014",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btG.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						AnswerChar.setImageResource(R.drawable.g);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'G'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("014",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("014",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btH.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						AnswerChar.setImageResource(R.drawable.h);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'H'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("014",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("014",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btI.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						AnswerChar.setImageResource(R.drawable.i);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'I'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("014",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("014",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btL.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						AnswerChar.setImageResource(R.drawable.l);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'L'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("014",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("014",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btM.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						AnswerChar.setImageResource(R.drawable.m);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'M'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("014",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("014",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btN.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						AnswerChar.setImageResource(R.drawable.n);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'N'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("014",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("014",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btO.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						AnswerChar.setImageResource(R.drawable.o);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'O'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("014",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("014",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btR.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						AnswerChar.setImageResource(R.drawable.r);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'R'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("014",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("014",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btS.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						AnswerChar.setImageResource(R.drawable.s);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'S'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("014",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("014",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btT.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						AnswerChar.setImageResource(R.drawable.t);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'T'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("014",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("014",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btU.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						AnswerChar.setImageResource(R.drawable.u);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'U'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("014",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("014",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				btY.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						AnswerChar.setImageResource(R.drawable.y);
						AnswerChar.setVisibility(View.VISIBLE);
						if(answer == 'Y'){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("014",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("014",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				
		final MediaPlayer soundShowAns = MediaPlayer.create(context, R.raw.choose_correct_ans);
		
		soundCorrect.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundCorrect) {
            	imgCorrect.setVisibility(View.INVISIBLE);
            	HidePosition.setVisibility(View.INVISIBLE);
            	AnswerChar.setVisibility(View.INVISIBLE);
            	soundShowAns.start();
            	//game014();
            }
        });

		soundWrong.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	imgWrong.setVisibility(View.INVISIBLE);
            	HidePosition.setVisibility(View.INVISIBLE);
            	AnswerChar.setVisibility(View.INVISIBLE);
            	soundShowAns.start();
            	//game014();
            }
        });
		soundShowAns.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	game014();
            }
        });
		
		Button HelpButton = (Button)findViewById(R.id.bodyHelp);
		HelpButton.setOnClickListener(new View.OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//countdownTime.cancel();
				helpAnswer.startAnimation(myFadeonceAnimation);
			}
		});
		
		Button HomeButton = (Button)findViewById(R.id.bodybackHome);
		HomeButton.setOnClickListener(new View.OnClickListener() {
		
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			stopTime();
			Intent in = new Intent(HpL2Body.this,Main.class);
			in.putExtra("showPopup", 1);
			startActivity(in);
		}
		});
	}
	
	char[] setBG(int random){
		
		char [][] Answer = {{'M','O','U','T','H'},{'E','A','R'},{'E','Y','E'},{'A','R','M'},
				{'L','E','G'},{'F','O','O','T'},{'H','A','N','D'},{'N','O','S','E'},
				{'T','E','E','T','H'},{'H','A','I','R'}};

		AbsoluteLayout Thislayout=(AbsoluteLayout)findViewById(R.id.bodyLayout);
	
		switch(random){
		case 1:
			Thislayout.setBackgroundResource(R.drawable.hpl2_bg1);
			break;
		case 2:
			Thislayout.setBackgroundResource(R.drawable.hpl2_bg2);
			break;
		case 3:
			Thislayout.setBackgroundResource(R.drawable.hpl2_bg3);
			break;
		case 4:
			Thislayout.setBackgroundResource(R.drawable.hpl2_bg4);
			break;
		case 5:
			Thislayout.setBackgroundResource(R.drawable.hpl2_bg5);
			break;
		case 6:
			Thislayout.setBackgroundResource(R.drawable.hpl2_bg6);
			break;
		case 7:
			Thislayout.setBackgroundResource(R.drawable.hpl2_bg7);
			break;
		case 8:
			Thislayout.setBackgroundResource(R.drawable.hpl2_bg8);
			break;
		case 9:
			Thislayout.setBackgroundResource(R.drawable.hpl2_bg9);
			break;
		default:
			Thislayout.setBackgroundResource(R.drawable.hpl2_bg10);
			break;
		}
		instructPage = MediaPlayer.create(context, R.raw.hs_ins2);
		return Answer[random-1];
	}
	char choice(char[] word,int length,int ranChar){

		char answer = word[ranChar];

		if(length == 4){
			switch(ranChar){
				case 0:
					HidePosition = (View)findViewById(R.id.img6);
					AnswerChar = (ImageView)findViewById(R.id.ansImg6);
					break;
				case 1:
					HidePosition = (View)findViewById(R.id.img7);
					AnswerChar = (ImageView)findViewById(R.id.ansImg7);
					break;
				case 2:
					HidePosition = (View)findViewById(R.id.img8);
					AnswerChar = (ImageView)findViewById(R.id.ansImg8);
					break;
				case 3:
					HidePosition = (View)findViewById(R.id.img9);
					AnswerChar = (ImageView)findViewById(R.id.ansImg9);
					break;
			}
			
		}
		else{
			if(length == 3){
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
				case 4:
					HidePosition = (View)findViewById(R.id.img5);
					AnswerChar = (ImageView)findViewById(R.id.ansImg5);
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
				Intent in = new Intent(HpL2Body.this,Main.class);
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
				
				Round = myDb.getNumRound("014", username);
				//Round++;
				
				game014();
			}
		});
		
		Button dialogNextBt = (Button)dialog.findViewById(R.id.scorenext);
		dialogNextBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(HpL2Body.this,HsL3Job.class);
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
		myDb.addItemScore("014",username,Round,item,0,startTime/1000);
		
		final MediaPlayer soundWrongFin = MediaPlayer.create(context, R.raw.wrong_sound2);
		soundWrongFin.start();
		
		soundWrongFin.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	imgWrongFin.setVisibility(View.INVISIBLE);
            	game014();
            }
        });
		
	}
	
	public boolean onTouchEvent (MotionEvent event) {
		
		instructPage.start();
		return super.onTouchEvent(event);
	}
	
	protected void onRestart() {
		// TODO Auto-generated method stub
		
		Intent intent = new Intent(HpL2Body.this,Main.class);
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
			Intent in = new Intent(HpL2Body.this,Main.class);
			in.putExtra("showPopup", 1);
			startActivity(in);  
        	return false;
        }
	    return super.onKeyDown(keyCode, event);
	}
}