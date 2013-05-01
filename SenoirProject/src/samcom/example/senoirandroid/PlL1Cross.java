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
public class PlL1Cross extends Activity {

	String username;
	long startTime = (20)*1000;
	final Context context = this;
	int timeRemain;
	boolean firstSound;
	boolean OnPause = false;
	boolean RunningCount = false;
	int Round;
	int Items = 1;
	int ranDay=0;
	int Begin = 1;

	MediaPlayer instructPage,soundMain;
	MediaPlayer soundIns;
	
	MyCountDown countdownTime = new MyCountDown(startTime,1000);
	
	public class MyCountDown extends CountDownTimer {
		public MyCountDown(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
		// TODO Auto-generated constructor stub
		}
		
		@Override
		public void onFinish() { // เน€เธ�โ�ฌเน€เธ�เธ�เน€เธ�เธ—เน€เธ�๏ฟฝเน€เธ�เธ�เน€เธ�โ€”เน€เธ�เธ“เน€เธ�๏ฟฝเน€เธ�เธ’เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�เธ�เน€เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�เธ�เน€เธ�เธ”เน€เธ�๏ฟฝเน€เธ�๏ฟฝ
		// TODO Auto-generated method stub
			RunningCount = false;
			showTimeout();
		}
		
		@Override
		public void onTick(long remain) { // เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ€�เน€เธ�เธ�เน€เธ�โ€”เน€เธ�เธ•เน€เธ�๏ฟฝเน€เธ�โ€”เน€เธ�เธ“เน€เธ�๏ฟฝเน€เธ�เธ’เน€เธ�๏ฟฝเน€เธ�โ€”เน€เธ�เธ�เน€เธ�๏ฟฝ เน€เธ�๏ฟฝ เน€เธ�๏ฟฝเน€เธ�เธ�เน€เธ�เธ‘เน€เธ�๏ฟฝเน€เธ�๏ฟฝ
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
		
		setContentView(R.layout.activity_pl_l1_cross);
		soundMain = MediaPlayer.create(context, R.raw.main);
		soundMain.start();
		soundMain.setLooping(true);
		soundMain.setVolume(30, 30);
	
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		username = myDb.SelectCurrentUser();
		
		//mediaPlayer.start();
		
		Round = myDb.getNumRound("004", username);
		
		game004();
		
		
		
	}

	void game004(){
		int scores;
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		final LoginManage myUser = new LoginManage(this);
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
				instructPage.stop();
				myUser.showLoginPopup();

			}
		});
		
		Button LogoutButton = (Button)findViewById(R.id.logout);
		LogoutButton.setOnClickListener(new View.OnClickListener() {
			 
			public void onClick(View v) {	
				myDb.logoutUser(username);
				instructPage.stop();
				Intent intent = new Intent(PlL1Cross.this,Main.class);
				intent.putExtra("Logout", 1);
				startActivity(intent);
			}
			
		});
		if(OnPause == false){
			if(Items < 11){
				//startTime = (30)*1000;
				//if(Round == 1){
		/*		if((Round == 1)||(username.equals("Guest"))){
					if(Begin == 1){
						if(Items == 1){
							showBeginPopup(1);
						}
						else if(Items == 4){
							showBeginPopup(4);
						}
						else if(Items == 6){
							showBeginPopup(6);
						}
						else if(Items == 8){
							showBeginPopup(8);
						}
						else{
							checkAns(false);
						}
					}
					else{
						checkAns(false);
					}
				}
				else{
				
				  */
				 checkAns();
				
				//}
			}
			else{
				scores = myDb.countScore("004", username, Round);
				countdownTime.cancel();
				showPopup(scores);
			}
		}
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
	
void checkAns(){
		
		final Button ans1_1 = (Button)findViewById(R.id.tf_ans1_1);
		final Button ans1_2 = (Button)findViewById(R.id.tf_ans1_2);
		final Button ans1_3 = (Button)findViewById(R.id.tf_ans1_3);
		Button answer4_1 = (Button)findViewById(R.id.tf_ans4_1);
		Button answer4_2 = (Button)findViewById(R.id.tf_ans4_2);
		Button answer5_1 = (Button)findViewById(R.id.tf_ans5_1);
		Button answer5_2 = (Button)findViewById(R.id.tf_ans5_2);
		Button answer6_1 = (Button)findViewById(R.id.tf_ans6_1);
		Button answer6_2 = (Button)findViewById(R.id.tf_ans6_2);
		Button answer8_1 = (Button)findViewById(R.id.tf_ans8_1);
		Button answer8_2 = (Button)findViewById(R.id.tf_ans8_2);
		final Button ans4_1 = (Button)findViewById(R.id.tf_ans4_1);
		final Button ans4_2 = (Button)findViewById(R.id.tf_ans4_2);
		final Button ans5_1 = (Button)findViewById(R.id.tf_ans5_1);
		final Button ans5_2 = (Button)findViewById(R.id.tf_ans5_2);
		final Button ans6_1 = (Button)findViewById(R.id.tf_ans6_1);
		final Button ans6_2 = (Button)findViewById(R.id.tf_ans6_2);
		final Button ans8_1 = (Button)findViewById(R.id.tf_ans8_1);
		final Button ans8_2 = (Button)findViewById(R.id.tf_ans8_2);
		
		//help image
		final ImageView trafficSign = (ImageView)findViewById(R.id.tf_ans1_help);
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		final int answer;
		final MediaPlayer soundCorrect = MediaPlayer.create(context, R.raw.crab_sound);
		final MediaPlayer soundWrong = MediaPlayer.create(context, R.raw.wrong_sound2);
		final Animation myFadeOnceAnimation = AnimationUtils.loadAnimation(PlL1Cross.this, R.anim.tween_once);
		
		startTime = (20)*1000;
		final float countTime = (float) startTime /1000;
		
		final View imgWrong = (View)findViewById(R.id.showwrong); 
		final View imgCorrect = (View)findViewById(R.id.showcorrect);
		imgWrong.setClickable(false);
		imgCorrect.setClickable(false);
		
		TextView current = (TextView) findViewById(R.id.currentitem);
		current.setText(Items +"/ 10");
		
		int currentItem = Items;
		
		answer = choice(currentItem);
		//instructPage.start();
		
		//countdownTime.start();
		
		final MediaPlayer soundAns = MediaPlayer.create(context, R.raw.choose_correct_ans);
		//final View helpAnswer = (View)findViewById(R.id.showAnswer);
		final Animation myFadeonceAnimation = AnimationUtils.loadAnimation(PlL1Cross.this, R.anim.tween_once);
		final Animation myFadeAnimation = AnimationUtils.loadAnimation(PlL1Cross.this, R.anim.tween);
		final ImageView instructFinger = (ImageView)findViewById(R.id.finger);
		
		
			countdownTime = new MyCountDown(startTime,1000);
			countdownTime.start();	
			instructPage.start();
			
			if(Round == 1 || (username.equals("Guest") && Items == 1)){
	        	instructFinger.startAnimation(myFadeonceAnimation);	
	        }
		
		instructPage.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundCorrect) {
            	/*if(Round == 1 || (username.equals("Guest") && (Items == 1||Items == 4 || Items == 6|| Items ==8))){
            		if(firstSound == true){
            			instructFinger.startAnimation(myFadeAnimation);
            			firstSound = false;
            		}
            		else{
            			if(Items == 1){
        					trafficSign.startAnimation(myFadeonceAnimation);
        				}
        				else if(Items == 4){
        					ans4_1.startAnimation(myFadeonceAnimation);
        					ans4_2.startAnimation(myFadeonceAnimation);
        				}
        				else if(Items == 6){
        					ans6_1.startAnimation(myFadeonceAnimation);
        					ans6_2.startAnimation(myFadeonceAnimation);
        				}
        				else{
        					ans8_1.startAnimation(myFadeonceAnimation);
        					ans8_2.startAnimation(myFadeonceAnimation);
        				}
	        			countdownTime = new MyCountDown(startTime,1000);
	        			countdownTime.start();
	        			instructFinger.clearAnimation();
	            		soundAns.start();
            		}
            	}*/
            	if(Round == 1 || (username.equals("Guest") && Items == 1)){
            		trafficSign.startAnimation(myFadeonceAnimation);
	            	soundAns.start();
            	}
            }
        });
			
			ans1_1.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					instructPage.stop();
					ans1_2.setVisibility(View.INVISIBLE);
					ans1_3.setVisibility(View.INVISIBLE);
					if(Round == 1 || (username.equals("Guest") && Items == 1)){
						trafficSign.startAnimation(myFadeonceAnimation);
					}
					if(answer == 1){
						imgCorrect.setVisibility(View.VISIBLE);
						stopTime();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						stopTime();
						soundWrong.start();
						myDb.addItemScore("004",username,Round,Items,0,(countTime - timeRemain));
					}
					
				}
			});
			ans1_2.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					instructPage.stop();
					ans1_1.setVisibility(View.INVISIBLE);
					ans1_3.setVisibility(View.INVISIBLE);
					if(Round == 1 || (username.equals("Guest") && Items == 1)){
						trafficSign.startAnimation(myFadeonceAnimation);
					}
					if(answer == 2){
						imgCorrect.setVisibility(View.VISIBLE);
						stopTime();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						stopTime();
						soundWrong.start();
						myDb.addItemScore("004",username,Round,Items,0,(countTime - timeRemain));
					}
					
				}
			});
			ans1_3.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					instructPage.stop();
					ans1_1.setVisibility(View.INVISIBLE);
					ans1_2.setVisibility(View.INVISIBLE);
					if(Round == 1 || (username.equals("Guest") && Items == 1)){
						trafficSign.startAnimation(myFadeonceAnimation);
					}
					if(answer == 3){
						imgCorrect.setVisibility(View.VISIBLE);
						stopTime();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						stopTime();
						soundWrong.start();
						myDb.addItemScore("004",username,Round,Items,0,(countTime - timeRemain));
					}
					
				}
			});
			answer4_1.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					instructPage.stop();
					if(answer == 1){
						imgCorrect.setVisibility(View.VISIBLE);
						stopTime();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						stopTime();
						soundWrong.start();
						myDb.addItemScore("004",username,Round,Items,0,(countTime - timeRemain));
					}
					
				}
			});
			answer4_2.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					instructPage.stop();
					if(answer == 2){
						imgCorrect.setVisibility(View.VISIBLE);
						stopTime();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						stopTime();
						soundWrong.start();
						myDb.addItemScore("004",username,Round,Items,0,(countTime - timeRemain));
					}
					
				}
			});
			answer5_1.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					instructPage.stop();
					if(answer == 1){
						imgCorrect.setVisibility(View.VISIBLE);
						stopTime();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						stopTime();
						soundWrong.start();
						myDb.addItemScore("004",username,Round,Items,0,(countTime - timeRemain));
					}
					
				}
			});
			answer5_2.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					instructPage.stop();
					if(answer == 2){
						imgCorrect.setVisibility(View.VISIBLE);
						stopTime();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						stopTime();
						soundWrong.start();
						myDb.addItemScore("004",username,Round,Items,0,(countTime - timeRemain));
					}
					
				}
			});
			answer6_1.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					instructPage.stop();
					if(answer == 1){
						imgCorrect.setVisibility(View.VISIBLE);
						stopTime();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						stopTime();
						soundWrong.start();
						myDb.addItemScore("004",username,Round,Items,0,(countTime - timeRemain));
					}
					
				}
			});
			answer6_2.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					instructPage.stop();
					if(answer == 2){
						imgCorrect.setVisibility(View.VISIBLE);
						stopTime();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						stopTime();
						soundWrong.start();
						myDb.addItemScore("004",username,Round,Items,0,(countTime - timeRemain));
					}
					
				}
			});
			answer8_1.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					instructPage.stop();
					if(answer == 1){
						imgCorrect.setVisibility(View.VISIBLE);
						stopTime();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						stopTime();
						soundWrong.start();
						myDb.addItemScore("004",username,Round,Items,0,(countTime - timeRemain));
					}
					
				}
			});
			answer8_2.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					instructPage.stop();
					if(answer == 2){
						imgCorrect.setVisibility(View.VISIBLE);
						stopTime();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						stopTime();
						soundWrong.start();
						myDb.addItemScore("004",username,Round,Items,0,(countTime - timeRemain));
					}
					
				}
			});
		
		soundCorrect.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundCorrect) {
            	imgCorrect.setVisibility(View.INVISIBLE);
            	Items++;
            	game004();
            }
        });

		soundWrong.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	imgWrong.setVisibility(View.INVISIBLE);
            	Items++;
            	game004();
            }
        });
		
		Button HelpButton = (Button)findViewById(R.id.crossHelp);
		HelpButton.setOnClickListener(new View.OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(Items < 4){
					trafficSign.startAnimation(myFadeOnceAnimation);
				}
				else if(Items < 5){
					ans4_1.startAnimation(myFadeOnceAnimation);
					ans4_2.startAnimation(myFadeOnceAnimation);
				}
				else if(Items < 6){
					ans5_1.startAnimation(myFadeOnceAnimation);
					ans5_2.startAnimation(myFadeOnceAnimation);
				}
				else if(Items < 8){
					ans6_1.startAnimation(myFadeOnceAnimation);
					ans6_2.startAnimation(myFadeOnceAnimation);
				}
				else{
					ans8_1.startAnimation(myFadeOnceAnimation);
					ans8_2.startAnimation(myFadeOnceAnimation);
				}
			}
		});
		
		Button crossButton = (Button)findViewById(R.id.crossbackHome);
		crossButton.setOnClickListener(new View.OnClickListener() {
		
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			stopTime();
			Intent in = new Intent(PlL1Cross.this,Main.class);
			in.putExtra("showPopup", 1);
			startActivity(in);
		}
		});
	}
	
	
	int RanNum(){
		int randomInt;
		Boolean isExited;
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		
		do{
			Random randomGenerator = new Random();
			randomInt = randomGenerator.nextInt(7)+1;
			isExited = myDb.checkNumber(randomInt);
		}while(isExited);
		myDb.close();
		myDb.getWritableDatabase();
		myDb.insertRanNumber(randomInt);
		return randomInt;
	}
	
	int choice(int itemNo){
		
		AbsoluteLayout Thislayout=(AbsoluteLayout)findViewById(R.id.tf_layout);
			
	    	
		Button inst1 = (Button)findViewById(R.id.tf_ins1);
		Button inst4 = (Button)findViewById(R.id.tf_ins4);
		Button inst6 = (Button)findViewById(R.id.tf_ins6);
		Button inst8 = (Button)findViewById(R.id.tf_ins8);
		Button ans1_1 = (Button)findViewById(R.id.tf_ans1_1);
		Button ans1_2 = (Button)findViewById(R.id.tf_ans1_2);
		Button ans1_3 = (Button)findViewById(R.id.tf_ans1_3);
		Button ans4_1 = (Button)findViewById(R.id.tf_ans4_1);
		Button ans4_2 = (Button)findViewById(R.id.tf_ans4_2);
		Button ans5_1 = (Button)findViewById(R.id.tf_ans5_1);
		Button ans5_2 = (Button)findViewById(R.id.tf_ans5_2);
		Button ans6_1 = (Button)findViewById(R.id.tf_ans6_1);
		Button ans6_2 = (Button)findViewById(R.id.tf_ans6_2);
		Button ans8_1 = (Button)findViewById(R.id.tf_ans8_1);
		Button ans8_2 = (Button)findViewById(R.id.tf_ans8_2);
		
		int answer = 0;
		
		inst1.setVisibility(View.INVISIBLE);
		inst4.setVisibility(View.INVISIBLE);
		inst6.setVisibility(View.INVISIBLE);
		inst8.setVisibility(View.INVISIBLE);
		ans1_1.setVisibility(View.INVISIBLE);
		ans1_2.setVisibility(View.INVISIBLE);
		ans1_3.setVisibility(View.INVISIBLE);
		ans4_1.setVisibility(View.INVISIBLE);
		ans4_2.setVisibility(View.INVISIBLE);
		ans5_1.setVisibility(View.INVISIBLE);
		ans5_2.setVisibility(View.INVISIBLE);
		ans6_1.setVisibility(View.INVISIBLE);
		ans6_2.setVisibility(View.INVISIBLE);
		ans8_1.setVisibility(View.INVISIBLE);
		ans8_2.setVisibility(View.INVISIBLE);
				
		if(itemNo < 4){
			Thislayout.setBackgroundResource(R.drawable.pl1_bg1);
			inst1.setVisibility(View.VISIBLE);
			ans1_1.setVisibility(View.VISIBLE);
			ans1_2.setVisibility(View.VISIBLE);
			ans1_3.setVisibility(View.VISIBLE);
			
			ans1_1.setBackgroundResource(R.drawable.pl1_ans1_1);
			ans1_2.setBackgroundResource(R.drawable.pl1_ans1_2);
			ans1_3.setBackgroundResource(R.drawable.pl1_ans1_3);
			if(itemNo == 1){
				answer = 3;
				inst1.setBackgroundResource(R.drawable.pl1_ins1);
				instructPage = MediaPlayer.create(context, R.raw.ins_pll1_1);
				
			}
			else if(itemNo == 2){
				answer = 2;
				inst1.setBackgroundResource(R.drawable.pl1_ins2);
				instructPage = MediaPlayer.create(context, R.raw.ins_pll1_2);
			} 
			else if(itemNo == 3){
				answer = 1;
				inst1.setBackgroundResource(R.drawable.pl1_ins3);
				instructPage = MediaPlayer.create(context, R.raw.ins_pll1_3);
			}
		}
		else if(itemNo < 6){
			
			inst4.setVisibility(View.VISIBLE);
			instructPage = MediaPlayer.create(context, R.raw.ins_pll1_4);
						
			if(itemNo == 4){
				answer = 1;
				ans4_1.setVisibility(View.VISIBLE);
				ans4_2.setVisibility(View.VISIBLE);
				Thislayout.setBackgroundResource(R.drawable.pl1_bg2);
				inst4.setBackgroundResource(R.drawable.pl1_ins4);
				
			}
			else if(itemNo == 5){
				answer = 1;
				ans5_1.setVisibility(View.VISIBLE);
				ans5_2.setVisibility(View.VISIBLE);
				Thislayout.setBackgroundResource(R.drawable.pl1_bg3);
				inst4.setBackgroundResource(R.drawable.pl1_ins4);
			} 
		}
		else if(itemNo < 8){
			Thislayout.setBackgroundResource(R.drawable.pl1_bg4);
			inst6.setVisibility(View.VISIBLE);
			ans6_1.setVisibility(View.VISIBLE);
			ans6_2.setVisibility(View.VISIBLE);
						
			if(itemNo == 6){
				answer = 2;
				inst6.setBackgroundResource(R.drawable.pl1_ins6);
				ans6_1.setBackgroundResource(R.drawable.pl1_ans6_1);
				ans6_2.setBackgroundResource(R.drawable.pl1_ans6_2);
				instructPage = MediaPlayer.create(context, R.raw.ins_pll1_7);
			}
			else if(itemNo == 7){
				answer = 1;
				inst6.setBackgroundResource(R.drawable.pl1_ins7);
				ans6_1.setBackgroundResource(R.drawable.pl1_ans6_1);
				ans6_2.setBackgroundResource(R.drawable.pl1_ans6_2);
				instructPage = MediaPlayer.create(context, R.raw.ins_pll1_6);
			} 
		}
		else {
			Thislayout.setBackgroundResource(R.drawable.pl1_bg8);
			inst8.setVisibility(View.VISIBLE);
			ans8_1.setVisibility(View.VISIBLE);
			ans8_2.setVisibility(View.VISIBLE);
						
			if(itemNo == 8){
				answer = 2;
				inst8.setBackgroundResource(R.drawable.pl1_ins8);
				ans8_1.setBackgroundResource(R.drawable.pl1_ans8_1);
				ans8_2.setBackgroundResource(R.drawable.pl1_ans8_2);
				instructPage = MediaPlayer.create(context, R.raw.ins_pll1_8);
			}
			else if(itemNo == 9){
				answer = 1;
				inst8.setBackgroundResource(R.drawable.pl1_ins9);
				ans8_1.setBackgroundResource(R.drawable.pl1_9_1);
				ans8_2.setBackgroundResource(R.drawable.pl1_ans9_2);
				instructPage = MediaPlayer.create(context, R.raw.ins_pll1_9);
			} 
			else{
				answer = 2;
				inst8.setBackgroundResource(R.drawable.pl1_ins10);
				ans8_1.setBackgroundResource(R.drawable.pl1_ans10_1);
				ans8_2.setBackgroundResource(R.drawable.pl1_ans10_2);
				instructPage = MediaPlayer.create(context, R.raw.ins_pll1_10);
			} 
		}
		
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
				Intent in = new Intent(PlL1Cross.this,Main.class);
				in.putExtra("showPopup", 1);
				startActivity(in);
				
			}
		});
		
		Button dialogReplyBt = (Button)dialog.findViewById(R.id.scoreback);
		dialogReplyBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				
				myDb.getReadableDatabase();
				final View imgWrongpop = (View)findViewById(R.id.showwrong); 
				final View imgCorrectpop = (View)findViewById(R.id.showcorrect);
				imgWrongpop.setVisibility(View.INVISIBLE);
				imgCorrectpop.setVisibility(View.INVISIBLE);
				
				Round = myDb.getNumRound("004", username);
				//Round++;
				Items=1;
				game004();
			}
		});
		
		Button dialogNextBt = (Button)dialog.findViewById(R.id.scorenext);
		dialogNextBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(PlL1Cross.this,PlL2NearFar.class);
				startActivity(intent);
				
			}
		});
		dialog.show();

	}	
	/*
	protected void showBeginPopup(int itemm){
		final Dialog BeginPop = new Dialog(context, R.style.FullHeightDialog);
		final MediaPlayer soundAns;
		if(itemm == 1){
			BeginPop.setContentView(R.layout.activity_pl_l1_cross_tutorial1);
			soundIns = MediaPlayer.create(context, R.raw.ins_pll1_1);
		}
		else if(itemm == 4){
			BeginPop.setContentView(R.layout.activity_pl_l1_cross_tutorial4);
			soundIns = MediaPlayer.create(context, R.raw.ins_pll1_4);
		}
		else if(itemm == 6){
			BeginPop.setContentView(R.layout.activity_pl_l1_cross_tutorial6);
			soundIns = MediaPlayer.create(context, R.raw.ins_pll1_6);
		}
		else if(itemm == 8){
			BeginPop.setContentView(R.layout.activity_pl_l1_cross_tutorial8);
			soundIns = MediaPlayer.create(context, R.raw.ins_pll1_8);
		}
		
		BeginPop.setCanceledOnTouchOutside(false);
		BeginPop.setCancelable(false); 
		
		
		soundAns = MediaPlayer.create(context, R.raw.choose_correct_ans);
		final Animation myFadeAnimation = AnimationUtils.loadAnimation(PlL1Cross.this, R.anim.tween);
		final ImageView helpAns = (ImageView)BeginPop.findViewById(R.id.showAnswer);
		final ImageView instruct = (ImageView)BeginPop.findViewById(R.id.helpCross);
		
		//soundWrong is instruction sound
				instruct.startAnimation(myFadeAnimation);
				soundIns.start();
				
				soundIns.setOnCompletionListener(new OnCompletionListener() {
		            public void onCompletion(MediaPlayer soundIns) {
		            	instruct.clearAnimation();
		            	soundAns.start();
		            	helpAns.startAnimation(myFadeAnimation);
		            }
		        });
				
				Button skipHelp = (Button)BeginPop.findViewById(R.id.bt_skip);
				skipHelp.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						soundIns.stop();
						soundAns.stop();
						Begin = 2;
						BeginPop.dismiss();
						game004();
					}
				});
			BeginPop.show();
	}
	*/
	
	void showTimeout(){
		
		final View imgWrongFin = (View)findViewById(R.id.showwrong); 
		imgWrongFin.setVisibility(View.VISIBLE);
		imgWrongFin.setClickable(false);
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		myDb.addItemScore("004",username,Round,Items,0,startTime/1000);
		
		final MediaPlayer soundWrongFin = MediaPlayer.create(context, R.raw.wrong_sound2);
		soundWrongFin.start();
		
		soundWrongFin.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	imgWrongFin.setVisibility(View.INVISIBLE);
            	Items++;
            	game004();
            }
        });
		
	}
	public boolean onTouchEvent (MotionEvent event) {
		instructPage.start();
		
		return super.onTouchEvent(event);
	}
	
	protected void onRestart() {
		// TODO Auto-generated method stub
		
		Intent intent = new Intent(PlL1Cross.this,Main.class);
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
			Intent in = new Intent(PlL1Cross.this,Main.class);
			in.putExtra("showPopup", 1);
			startActivity(in);
        	return false;
        }
	    return super.onKeyDown(keyCode, event);
	}


}