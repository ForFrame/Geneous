package samcom.example.senoirandroid;

import android.app.Activity;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;


public class L1ScCount extends Activity {
	static int score = 10;
	static int found = -1;
	static int randomInt;
	long startTime;
	boolean firstSound;
	boolean OnPause = false;
	boolean RunningCount = false;
	final Context context = this;
	String username;
	int timeRemain;
	int Round;
	MyCountDown countdown;
	MediaPlayer instructPage,soundMain;
	
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
			TextView result = (TextView) findViewById(R.id.textView1);
			//timeRemain = (float) remain / 1000;
			timeRemain = (int) (remain) / 1000;
			result.setText(" Rest: " + timeRemain);
		}
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_l1_sc_count);
		
		soundMain = MediaPlayer.create(context, R.raw.main);
		soundMain.start();
		soundMain.setLooping(true);
		soundMain.setVolume(30, 30);
		
		final myDBClass myDb = new myDBClass(this);

		myDb.getReadableDatabase();
		username = myDb.SelectCurrentUser();
		
		Round = myDb.getNumRound("001", username);
		myDb.getWritableDatabase();
		myDb.emptyNumberTable();
		myDb.close();
		
	
			game001();
	

					
	}	
	
	void game001(){
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		final LoginManage myUser = new LoginManage(this);
		int count = myDb.CountNumRan();
		int Random = 0;
		int scores;
		
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/teddy.ttf"); 
		
		username = myDb.SelectCurrentUser();
		if(!(username.equals("Guest"))){
			TextView result = (TextView) findViewById(R.id.textUser);
			result.setTypeface(type);
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
				myUser.showLoginPopup();

			}
		});
		
		Button LogoutButton = (Button)findViewById(R.id.logout);
		LogoutButton.setOnClickListener(new View.OnClickListener() {
			 
			public void onClick(View v) {	
				myDb.logoutUser(username);
				Intent intent = new Intent(L1ScCount.this,Main.class);
				intent.putExtra("Logout", 1);
				startActivity(intent);
			}
			
		});
		if(OnPause == false){
			if(count < 10){
				Random = RanNum();
						
				//final long startTime = ((Random*2)+10)*1000;
				int LastRan = myDb.getLastNum(0);
				if(LastRan!= 0){
					hideTables(LastRan);
				}
				checkAns(Random,count+1);
			}
			else{
				scores = myDb.countScore("001", username, Round);
				int LastRan = myDb.getLastNum(10);
				hideTables(LastRan);
				countdown.cancel();
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
	void stopTime(){
		ImageView instructFing = (ImageView)findViewById(R.id.finger);
		if(RunningCount == true){
			countdown.cancel();
			if(instructFing.isEnabled()){
				instructFing.clearAnimation();
			}
		}	
	}
	void checkAns(final int RandomNum,final int item){
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		
		//final MediaPlayer soundCorrect = MediaPlayer.create(context, R.raw.correct_sound);
		final MediaPlayer soundCorrect = MediaPlayer.create(context, R.raw.crab_sound);
		final MediaPlayer soundWrong = MediaPlayer.create(context, R.raw.wrong_sound2);
		
		Random randomGenerator = new Random();
		int randomSound = randomGenerator.nextInt(3)+1;
		if(randomSound == 1){
			instructPage= MediaPlayer.create(context, R.raw.ins_sclv1_1);
		}
		else if(randomSound == 2){
			instructPage= MediaPlayer.create(context, R.raw.ins_sclv1_2);
		}
		else {
			instructPage= MediaPlayer.create(context, R.raw.ins_sclv1_3);
		}
		
		startTime = (20)*1000;
		final float countTime = (float) startTime /1000;
		//if(firstSound == true){
		//	timeRemain = (int)countTime;
		//}
		final View imgWrong = (View)findViewById(R.id.showwrongnumber); 
		final View imgCorrect = (View)findViewById(R.id.showcorrectnumber);
		
		TextView current = (TextView) findViewById(R.id.currentitem);
		//timeRemain = (float) remain / 1000;
		current.setText(item +"/ 10");
		
		
		showTables(RandomNum);
		
		final MediaPlayer soundAns = MediaPlayer.create(context, R.raw.choose_correct_ans);
		final View helpAnswer = (View)findViewById(R.id.showAnswer);
		final Animation myFadeonceAnimation = AnimationUtils.loadAnimation(L1ScCount.this, R.anim.tween_once);
		final Animation myFadeAnimation = AnimationUtils.loadAnimation(L1ScCount.this, R.anim.tween);
		final ImageView instructFinger = (ImageView)findViewById(R.id.finger);
		
		//if(Round == 1 || (username.equals("Guest") && item == 1)){
		//	instructPage.start();
		//	firstSound = true;
		//}
		//else{
			
			countdown = new MyCountDown(startTime,1000);
			countdown.start();	
			instructPage.start();
		//}

		if(Round == 1 || (username.equals("Guest") && item == 1)){
        	instructFinger.startAnimation(myFadeonceAnimation);	
        }
		if(item == 2){
			if(helpAnswer.isFocused()){
				helpAnswer.clearAnimation();
			}
		}
        		
		instructPage.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundCorrect) {
            	if(Round == 1 || (username.equals("Guest") && item == 1)){
            	//	if(firstSound == true){
            	//		instructFinger.startAnimation(myFadeAnimation);
            	//		firstSound = false;
            	//	}
            	//	else{
	            		helpAnswer.startAnimation(myFadeAnimation);
	            		//startTime = ((RandomNum*2)+10)*1000;
	        			//countdown = new MyCountDown(startTime,1000);
	        			//countdown.start();
	        			//instructFinger.clearAnimation();
	            		soundAns.start();
            	//	}
            	}
            }
        });
		
		Button selectButton1 = (Button)findViewById(R.id.buttonnumber1);
		selectButton1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				if(Round == 1 || (username.equals("Guest") && item == 1)){
					helpAnswer.clearAnimation();
				}				
				if(RandomNum == 1){
					imgWrong.setVisibility(View.INVISIBLE);
					imgCorrect.setVisibility(View.VISIBLE);
					stopTime();
					soundCorrect.start();
					myDb.addItemScore("001",username,Round,item,1,(countTime - timeRemain));
					
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					imgCorrect.setVisibility(View.INVISIBLE);
					stopTime();
					soundWrong.start();
					myDb.addItemScore("001",username,Round,item,0,(countTime - timeRemain));
				}
			}
		});
		
		Button selectButton2 = (Button)findViewById(R.id.buttonnumber2);
		selectButton2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				if(Round == 1 || (username.equals("Guest") && item == 1)){
					helpAnswer.clearAnimation();
				}
				if(RandomNum == 2){
					imgWrong.setVisibility(View.INVISIBLE);
					imgCorrect.setVisibility(View.VISIBLE);
					stopTime();
					soundCorrect.start();
					myDb.addItemScore("001",username,Round,item,1,(countTime - timeRemain));
					
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					imgCorrect.setVisibility(View.INVISIBLE);
					stopTime();
					soundWrong.start();
					myDb.addItemScore("001",username,Round,item,0,(countTime - timeRemain));
				}
			}
		});
		
		Button selectButton3 = (Button)findViewById(R.id.buttonnumber3);
		selectButton3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				if(Round == 1 || (username.equals("Guest") && item == 1)){
					helpAnswer.clearAnimation();
				}
				if(RandomNum == 3){
					imgWrong.setVisibility(View.INVISIBLE);
					imgCorrect.setVisibility(View.VISIBLE);
					stopTime();
					soundCorrect.start();
					myDb.addItemScore("001",username,Round,item,1,(countTime - timeRemain));
					
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					imgCorrect.setVisibility(View.INVISIBLE);
					stopTime();
					soundWrong.start();
					myDb.addItemScore("001",username,Round,item,0,(countTime - timeRemain));
				}
			}
		});
		
		Button selectButton4 = (Button)findViewById(R.id.buttonnumber4);
		selectButton4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				if(Round == 1 || (username.equals("Guest") && item == 1)){
					helpAnswer.clearAnimation();
				}
				if(RandomNum == 4){
					imgWrong.setVisibility(View.INVISIBLE);
					imgCorrect.setVisibility(View.VISIBLE);
					stopTime();
					soundCorrect.start();
					myDb.addItemScore("001",username,Round,item,1,(countTime - timeRemain));
					
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					imgCorrect.setVisibility(View.INVISIBLE);
					stopTime();
					soundWrong.start();
					myDb.addItemScore("001",username,Round,item,0,(countTime - timeRemain));
				}
			}
		});
		
		Button selectButton5 = (Button)findViewById(R.id.buttonnumber5);
		selectButton5.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				if(Round == 1 || (username.equals("Guest") && item == 1)){
					helpAnswer.clearAnimation();
				}
				if(RandomNum == 5){
					imgWrong.setVisibility(View.INVISIBLE);
					imgCorrect.setVisibility(View.VISIBLE);
					stopTime();
					soundCorrect.start();
					myDb.addItemScore("001",username,Round,item,1,(countTime - timeRemain));
					
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					imgCorrect.setVisibility(View.INVISIBLE);
					stopTime();
					soundWrong.start();
					myDb.addItemScore("001",username,Round,item,0,(countTime - timeRemain));
				}
			}
		});
		
		Button selectButton6 = (Button)findViewById(R.id.buttonnumber6);
		selectButton6.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				if(Round == 1 || (username.equals("Guest") && item == 1)){
					helpAnswer.clearAnimation();
				}
				if(RandomNum == 6){
					imgWrong.setVisibility(View.INVISIBLE);
					imgCorrect.setVisibility(View.VISIBLE);
					stopTime();
					soundCorrect.start();
					myDb.addItemScore("001",username,Round,item,1,(countTime - timeRemain));
					
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					imgCorrect.setVisibility(View.INVISIBLE);
					stopTime();
					soundWrong.start();
					myDb.addItemScore("001",username,Round,item,0,(countTime - timeRemain));
				}
			}
		});
		
		Button selectButton7 = (Button)findViewById(R.id.buttonnumber7);
		selectButton7.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				if(Round == 1 || (username.equals("Guest") && item == 1)){
					helpAnswer.clearAnimation();
				}
				if(RandomNum == 7){
					imgWrong.setVisibility(View.INVISIBLE);
					imgCorrect.setVisibility(View.VISIBLE);
					stopTime();
					soundCorrect.start();
					myDb.addItemScore("001",username,Round,item,1,(countTime - timeRemain));
					
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					imgCorrect.setVisibility(View.INVISIBLE);
					stopTime();
					soundWrong.start();
					myDb.addItemScore("001",username,Round,item,0,(countTime - timeRemain));
				}
			}
		});
		
		Button selectButton8 = (Button)findViewById(R.id.buttonnumber8);
		selectButton8.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				if(Round == 1 || (username.equals("Guest") && item == 1)){
					helpAnswer.clearAnimation();
				}
				if(RandomNum == 8){
					imgWrong.setVisibility(View.INVISIBLE);
					imgCorrect.setVisibility(View.VISIBLE);
					stopTime();
					soundCorrect.start();
					myDb.addItemScore("001",username,Round,item,1,(countTime - timeRemain));
					
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					imgCorrect.setVisibility(View.INVISIBLE);
					stopTime();
					soundWrong.start();
					myDb.addItemScore("001",username,Round,item,0,(countTime - timeRemain));
				}
			}
		});
		
		Button selectButton9 = (Button)findViewById(R.id.buttonnumber9);
		selectButton9.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				if(Round == 1 || (username.equals("Guest") && item == 1)){
					helpAnswer.clearAnimation();
				}
				if(RandomNum == 9){
					imgWrong.setVisibility(View.INVISIBLE);
					imgCorrect.setVisibility(View.VISIBLE);
					stopTime();
					soundCorrect.start();
					myDb.addItemScore("001",username,Round,item,1,(countTime - timeRemain));
					
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					imgCorrect.setVisibility(View.INVISIBLE);
					stopTime();
					soundWrong.start();
					myDb.addItemScore("001",username,Round,item,0,(countTime - timeRemain));
				}
			}
		});
		
		Button selectButton10 = (Button)findViewById(R.id.buttonnumber10);
		selectButton10.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				if(Round == 1 || (username.equals("Guest") && item == 1)){
					helpAnswer.clearAnimation();
				}
				if(RandomNum == 10){
					imgWrong.setVisibility(View.INVISIBLE);
					imgCorrect.setVisibility(View.VISIBLE);
					stopTime();
					soundCorrect.start();
					myDb.addItemScore("001",username,Round,item,1,(countTime - timeRemain));
					
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					imgCorrect.setVisibility(View.INVISIBLE);
					stopTime();
					soundWrong.start();
					myDb.addItemScore("001",username,Round,item,0,(countTime - timeRemain));
				}
			}
		});
		
		final View imgWrongClick = (View)findViewById(R.id.showwrongnumber); 
		final View imgCorrectClick = (View)findViewById(R.id.showcorrectnumber);
		imgWrongClick.setClickable(false);
		imgCorrectClick.setClickable(false);
		
		soundCorrect.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundCorrect) {
            	imgCorrect.setVisibility(View.INVISIBLE);
            	game001();
            }
        });

		soundWrong.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	imgWrong.setVisibility(View.INVISIBLE);
            	game001();
            }
        });
		
		Button HelpButton = (Button)findViewById(R.id.helpCountTable);
		HelpButton.setOnClickListener(new View.OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//countdown.cancel();
				helpAnswer.startAnimation(myFadeonceAnimation);
				
			}
		});
		
		Button countButton = (Button)findViewById(R.id.backtoHome);
		countButton.setOnClickListener(new View.OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopTime();
				Intent in = new Intent(L1ScCount.this,Main.class);
				in.putExtra("showPopup", 1);
				startActivity(in);
			}
		});
	
	}
	
	
	protected void showPopup(int scores){

		// custom dialog
		final Dialog dialog = new Dialog(context, R.style.FullHeightDialog);
		dialog.setContentView(R.layout.activity_dialog_score_sclv1g1);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false); 

		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		//int i = (int)f;
		//int showScore = (int)scores; 
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
				Intent in = new Intent(L1ScCount.this,Main.class);
				in.putExtra("showPopup", 1);
				startActivity(in);
				
			}
		});
		
		Button dialogReplyBt = (Button)dialog.findViewById(R.id.scoreback);
		dialogReplyBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				//soundPage.stop();
				int LastRan = myDb.getLastNum(10);
					hideTables(LastRan);

				myDb.getWritableDatabase();
				myDb.emptyNumberTable();
				myDb.close();
				myDb.getReadableDatabase();
				final View imgWrongpop = (View)findViewById(R.id.showwrongnumber); 
				final View imgCorrectpop = (View)findViewById(R.id.showcorrectnumber);
				imgWrongpop.setVisibility(View.INVISIBLE);
				imgCorrectpop.setVisibility(View.INVISIBLE);
				
				Round = myDb.getNumRound("001", username);
				game001();
			}
		});
		
		Button dialogNextBt = (Button)dialog.findViewById(R.id.scorenext);
		dialogNextBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				//soundPage.stop();
				Intent intent = new Intent(L1ScCount.this,L1ScCalendar.class);
				startActivity(intent);
				
			}
		});
		dialog.show();

	}			
	void showTables(int randomNum){
		
		switch(randomNum){
		
			case 1: ImageView imgView1 = (ImageView)findViewById(R.id.counttable1); 
					imgView1.setVisibility(ImageView.VISIBLE);	break;
			case 2: ImageView imgView2 = (ImageView)findViewById(R.id.counttable2); 
			 		imgView2.setVisibility(ImageView.VISIBLE);	break;
			case 3: ImageView imgView3 = (ImageView)findViewById(R.id.counttable3); 
					imgView3.setVisibility(ImageView.VISIBLE);	break;
			case 4: ImageView imgView4 = (ImageView)findViewById(R.id.counttable4); 
			 		imgView4.setVisibility(ImageView.VISIBLE);	break;	
			case 5: ImageView imgView5 = (ImageView)findViewById(R.id.counttable5); 
					imgView5.setVisibility(ImageView.VISIBLE);	break;
			case 6: ImageView imgView6 = (ImageView)findViewById(R.id.counttable6); 
					imgView6.setVisibility(ImageView.VISIBLE);	break;	
			case 7: ImageView imgView7 = (ImageView)findViewById(R.id.counttable7); 
					imgView7.setVisibility(ImageView.VISIBLE);	break;
			case 8: ImageView imgView8 = (ImageView)findViewById(R.id.counttable8); 
					imgView8.setVisibility(ImageView.VISIBLE);	break;	
			case 9: ImageView imgView9 = (ImageView)findViewById(R.id.counttable9); 
					imgView9.setVisibility(ImageView.VISIBLE);	break;
			case 10: ImageView imgView10 = (ImageView)findViewById(R.id.counttable10); 
					imgView10.setVisibility(ImageView.VISIBLE);	break;	
		}
	}
	
	void hideTables(int randomNum){
		
		switch(randomNum){
		
			case 1: ImageView imgView11 = (ImageView)findViewById(R.id.counttable1); 
					imgView11.setVisibility(ImageView.INVISIBLE);	break;
			case 2: ImageView imgView12 = (ImageView)findViewById(R.id.counttable2); 
			 		imgView12.setVisibility(ImageView.INVISIBLE);	break;
			case 3: ImageView imgView13 = (ImageView)findViewById(R.id.counttable3); 
					imgView13.setVisibility(ImageView.INVISIBLE);	break;
			case 4: ImageView imgView14 = (ImageView)findViewById(R.id.counttable4); 
			 		imgView14.setVisibility(ImageView.INVISIBLE);	break;	
			case 5: ImageView imgView15 = (ImageView)findViewById(R.id.counttable5); 
					imgView15.setVisibility(ImageView.INVISIBLE);	break;
			case 6: ImageView imgView16 = (ImageView)findViewById(R.id.counttable6); 
					imgView16.setVisibility(ImageView.INVISIBLE);	break;	
			case 7: ImageView imgView17 = (ImageView)findViewById(R.id.counttable7); 
					imgView17.setVisibility(ImageView.INVISIBLE);	break;
			case 8: ImageView imgView18 = (ImageView)findViewById(R.id.counttable8); 
					imgView18.setVisibility(ImageView.INVISIBLE);	break;	
			case 9: ImageView imgView19 = (ImageView)findViewById(R.id.counttable9); 
					imgView19.setVisibility(ImageView.INVISIBLE);	break;
			case 10: ImageView imgView20 = (ImageView)findViewById(R.id.counttable10); 
					imgView20.setVisibility(ImageView.INVISIBLE);	break;	
		}
	}
	
	void showTimeout(){
		final View imgWrongFin = (View)findViewById(R.id.showwrongnumber); 
		final View imgCorrectFin = (View)findViewById(R.id.showcorrectnumber);
		imgWrongFin.setVisibility(View.VISIBLE);
		imgCorrectFin.setVisibility(View.INVISIBLE);
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		int item = myDb.CountNumRan();
		//int item = count+1;
		myDb.close();
		myDb.getWritableDatabase();
		myDb.addItemScore("001",username,Round,item,0,startTime/1000);
		
		final MediaPlayer soundWrongFin = MediaPlayer.create(context, R.raw.wrong_sound2);
		soundWrongFin.start();
		
		soundWrongFin.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	imgWrongFin.setVisibility(View.INVISIBLE);
            	game001();
            }
        });
		
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(L1ScCount.this,Main.class);
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
	
	public boolean onTouchEvent (MotionEvent event) {
		instructPage.start();

		return super.onTouchEvent(event);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
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
		OnPause = true;
		if(soundMain.isLooping()){
    		soundMain.stop();
    	}
    	if(instructPage.isPlaying()){
    		instructPage.stop();
    	}
		super.onPause();
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	stopTime();
			Intent in = new Intent(L1ScCount.this,Main.class);
			in.putExtra("showPopup", 1);
			startActivity(in); 
        	return false;
        }
	    return super.onKeyDown(keyCode, event);
	}


}
	
	

