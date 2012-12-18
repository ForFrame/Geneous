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
	int Round;
	int Items = 1;
	int ranDay=0;
	int Begin = 1;
	MediaPlayer soundPage;
	MediaPlayer instructPage;
	MediaPlayer soundIns;
	
	final MyCountDown countdownTime = new MyCountDown(startTime,1000);
	
	public class MyCountDown extends CountDownTimer {
		public MyCountDown(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
		// TODO Auto-generated constructor stub
		}
		
		@Override
		public void onFinish() { // เน€เธ�โ�ฌเน€เธ�เธ�เน€เธ�เธ—เน€เธ�๏ฟฝเน€เธ�เธ�เน€เธ�โ€”เน€เธ�เธ“เน€เธ�๏ฟฝเน€เธ�เธ’เน€เธ�๏ฟฝเน€เธ�โ�ฌเน€เธ�เธ�เน€เธ�เธ�เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�เธ�เน€เธ�เธ”เน€เธ�๏ฟฝเน€เธ�๏ฟฝ
		// TODO Auto-generated method stub
			showTimeout();
		}
		
		@Override
		public void onTick(long remain) { // เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�โ€�เน€เธ�เธ�เน€เธ�โ€”เน€เธ�เธ•เน€เธ�๏ฟฝเน€เธ�โ€”เน€เธ�เธ“เน€เธ�๏ฟฝเน€เธ�เธ’เน€เธ�๏ฟฝเน€เธ�โ€”เน€เธ�เธ�เน€เธ�๏ฟฝ เน€เธ�๏ฟฝ เน€เธ�๏ฟฝเน€เธ�เธ�เน€เธ�เธ‘เน€เธ�๏ฟฝเน€เธ�๏ฟฝ
		// TODO Auto-generated method stub
			
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
	soundPage = MediaPlayer.create(context, R.raw.page);
	soundPage.start();
	soundPage.setLooping(true);
	
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
				myDb.ChangeHome(0);
				soundPage.stop();
				Intent in = new Intent(getApplicationContext(),Main.class);
				in.putExtra("loginButt", 1);
				startActivity(in);

			}
		});
		
		Button LogoutButton = (Button)findViewById(R.id.logout);
		LogoutButton.setOnClickListener(new View.OnClickListener() {
			 
			public void onClick(View v) {	
				myDb.logoutUser(username);
				myDb.ChangeHome(0);
				soundPage.stop();
				Intent intent = new Intent(PlL1Cross.this,Main.class);
				startActivity(intent);
			}
			
		});
		if(Items < 11){
			//startTime = (30)*1000;
			//if(Round == 1){
			if((Round == 1)||(username.equals("Guest"))){
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
			else{checkAns(false);}
		}
		else{
			scores = myDb.countScore("004", username, Round);
			
			showPopup(scores);
		}
	}
	
	
void checkAns(Boolean isInterupt){
		
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
		
		
		final float countTime = (float) startTime /1000;
		final View imgWrong = (View)findViewById(R.id.showwrong); 
		final View imgCorrect = (View)findViewById(R.id.showcorrect);
		
		TextView current = (TextView) findViewById(R.id.currentitem);
		current.setText(Items +"/ 10");
		
		int currentItem = Items;
		
		answer = choice(currentItem);
		instructPage.start();
		
		countdownTime.start();
			
			ans1_1.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					instructPage.stop();
					Begin = 1;
					ans1_2.setVisibility(View.INVISIBLE);
					ans1_3.setVisibility(View.INVISIBLE);
					if(answer == 1){
						imgCorrect.setVisibility(View.VISIBLE);
						countdownTime.cancel();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						countdownTime.cancel();
						soundWrong.start();
						myDb.addItemScore("004",username,Round,Items,0,(countTime - timeRemain));
					}
					
				}
			});
			ans1_2.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					instructPage.stop();
					Begin = 1;
					ans1_1.setVisibility(View.INVISIBLE);
					ans1_3.setVisibility(View.INVISIBLE);
					if(answer == 2){
						imgCorrect.setVisibility(View.VISIBLE);
						countdownTime.cancel();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						countdownTime.cancel();
						soundWrong.start();
						myDb.addItemScore("004",username,Round,Items,0,(countTime - timeRemain));
					}
					
				}
			});
			ans1_3.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					instructPage.stop();
					Begin = 1;
					ans1_1.setVisibility(View.INVISIBLE);
					ans1_2.setVisibility(View.INVISIBLE);
					if(answer == 3){
						imgCorrect.setVisibility(View.VISIBLE);
						countdownTime.cancel();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						countdownTime.cancel();
						soundWrong.start();
						myDb.addItemScore("004",username,Round,Items,0,(countTime - timeRemain));
					}
					
				}
			});
			answer4_1.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					instructPage.stop();
					Begin = 1;
					if(answer == 1){
						imgCorrect.setVisibility(View.VISIBLE);
						countdownTime.cancel();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						countdownTime.cancel();
						soundWrong.start();
						myDb.addItemScore("004",username,Round,Items,0,(countTime - timeRemain));
					}
					
				}
			});
			answer4_2.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					instructPage.stop();
					Begin = 1;
					if(answer == 2){
						imgCorrect.setVisibility(View.VISIBLE);
						countdownTime.cancel();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						countdownTime.cancel();
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
						countdownTime.cancel();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						countdownTime.cancel();
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
						countdownTime.cancel();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						countdownTime.cancel();
						soundWrong.start();
						myDb.addItemScore("004",username,Round,Items,0,(countTime - timeRemain));
					}
					
				}
			});
			answer6_1.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					instructPage.stop();
					Begin = 1;
					if(answer == 1){
						imgCorrect.setVisibility(View.VISIBLE);
						countdownTime.cancel();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						countdownTime.cancel();
						soundWrong.start();
						myDb.addItemScore("004",username,Round,Items,0,(countTime - timeRemain));
					}
					
				}
			});
			answer6_2.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					instructPage.stop();
					Begin = 1;
					if(answer == 2){
						imgCorrect.setVisibility(View.VISIBLE);
						countdownTime.cancel();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						countdownTime.cancel();
						soundWrong.start();
						myDb.addItemScore("004",username,Round,Items,0,(countTime - timeRemain));
					}
					
				}
			});
			answer8_1.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					instructPage.stop();
					Begin = 1;
					if(answer == 1){
						imgCorrect.setVisibility(View.VISIBLE);
						countdownTime.cancel();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						countdownTime.cancel();
						soundWrong.start();
						myDb.addItemScore("004",username,Round,Items,0,(countTime - timeRemain));
					}
					
				}
			});
			answer8_2.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					instructPage.stop();
					Begin = 1;
					if(answer == 2){
						imgCorrect.setVisibility(View.VISIBLE);
						countdownTime.cancel();
						soundCorrect.start();
						myDb.addItemScore("004",username,Round,Items,1,(countTime - timeRemain));
						
					}
					else{
						imgWrong.setVisibility(View.VISIBLE);
						countdownTime.cancel();
						soundWrong.start();
						myDb.addItemScore("004",username,Round,Items,0,(countTime - timeRemain));
					}
					
				}
			});
		
		final View imgWrongClick = (View)findViewById(R.id.showwrong); 
		final View imgCorrectClick = (View)findViewById(R.id.showcorrect);
		
		
		imgWrongClick.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				soundWrong.stop();
				imgWrong.setVisibility(View.INVISIBLE);
				Items++;
				game004();
			}
		});
		
		imgCorrectClick.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				soundCorrect.stop();
				imgCorrect.setVisibility(View.INVISIBLE);
				Items++;
				game004();
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
					//trafficSign.setAnimation(myFadeOnceAnimation);
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
			countdownTime.cancel();
			instructPage.stop();
			soundPage.stop();
			Intent intent = new Intent(PlL1Cross.this,PoliceLevel1.class);
			startActivity(intent);
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
				soundPage.stop();
				Intent intent = new Intent(PlL1Cross.this,PoliceLevel1.class);
				startActivity(intent);
				
				//finish();
				
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
				soundPage.stop();
				Intent intent = new Intent(PlL1Cross.this,PlL2NearFar.class);
				startActivity(intent);
				
			}
		});
		dialog.show();

	}	
	
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
	
	void showTimeout(){
		
		final View imgWrongFin = (View)findViewById(R.id.showwrong); 
		imgWrongFin.setVisibility(View.VISIBLE);
		
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
		
		final View imgWrongClick = (View)findViewById(R.id.showwrong); 
		
		imgWrongClick.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				soundWrongFin.stop();
				imgWrongFin.setVisibility(View.INVISIBLE);
				Items++;
				game004();
			}
		});
		
	}
	public boolean onTouchEvent (MotionEvent event) {
		//if(instructPage.isPlaying()){
		//	instructPage.stop();
		//	instructPage.start();
		//}
		//else{
			instructPage.start();
		//}
		return super.onTouchEvent(event);
	}
	
	protected void onRestart() {
		// TODO Auto-generated method stub
		final myDBClass myDb = new myDBClass(this);
		//myDb.getReadableDatabase();
		
		//this comment for เน€เธ�โ€ขเน€เธ�เธ”เน€เธ�๏ฟฝเน€เธ�๏ฟฝ เน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝเน€เธ�๏ฟฝ  continue (1,1) state
		//Boolean isThisContinue;
		//isThisContinue = myDb.isCurrentContinue();
		//myDb.close();
		//if(isThisContinue == true){
		//	game002();
		//}
		//else{
			myDb.getWritableDatabase();
			myDb.ChangeHome(0);
			Intent intent = new Intent(PlL1Cross.this,Main.class);
			startActivity(intent);
		//}
		
		super.onRestart();
	}
	
/*	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		myDb.ChangeHome(0);
		
		super.onDestroy();
	}*/
	
	
}