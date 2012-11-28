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
	float timeRemain;
	int Round;
	int Items = 1;
	int ranDay=0;
	
	final MyCountDown countdownTime = new MyCountDown(startTime,1000);
	
	public class MyCountDown extends CountDownTimer {
		public MyCountDown(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
		// TODO Auto-generated constructor stub
		}
		
		@Override
		public void onFinish() { // เน€เธกเธทเน�เธญเธ—เธณเธ�เธฒเธ�เน€เธชเธฃเน�เธ�เธชเธดเน�เธ�
		// TODO Auto-generated method stub
			showTimeout();
		}
		
		@Override
		public void onTick(long remain) { // เน�เธ�เธ�เธ“เธฐเธ—เธตเน�เธ—เธณเธ�เธฒเธ�เธ—เธธเธ� เน� เธ�เธฃเธฑเน�เธ�
		// TODO Auto-generated method stub
			
			TextView result = (TextView) findViewById(R.id.textTime);
			int timeRemain = (int) (remain) / 1000;
			result.setText(" Times: " + timeRemain);
		}
	}

@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	
	setContentView(R.layout.activity_pl_l1_cross);
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		username = myDb.SelectCurrentUser();
		
		//mediaPlayer.start();
		
		Round = myDb.getNumRound("004", username);
		
		/*int valueCalenTutorial = 0;
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			valueCalenTutorial = extras.getInt("calen_tuto");
			if(valueCalenTutorial == 1){
				game002();
			}
			else if(valueCalenTutorial == 2){
				
				Day = extras.getInt("today");
				ranDay = extras.getInt("numran");
				
				username = myDb.SelectCurrentUser();
				
				Typeface type = Typeface.createFromAsset(getAssets(),"fonts/teddy.ttf"); 
				if(!(username.equals("Guest"))){
					Round--;
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
				startTime = (30)*1000;
				checkAns(true);
				
			}
		}
		else{
						
			if(Round == 1){
				myDb.getWritableDatabase();
				myDb.emptyNumberTable();
				Intent intent2 = new Intent(PlL1Cross.this,L1ScCalendarTutorial.class);
				startActivity(intent2);
			}
			else{*/
				game004();
			//}
		//}
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
				Intent intent = new Intent(PlL1Cross.this,Main.class);
				startActivity(intent);
			}
			
		});
		if(Items < 11){
			//startTime = (30)*1000;
			checkAns(false);
		}
		else{
			scores = myDb.countScore("004", username, Round);
			myDb.getWritableDatabase();
			//myDb.emptyNumberTable();
					
			if(username.equals("Guest")){
				myDb.deleteGuest();
			}
			
			showPopup(scores);
		}
	}
	
	
void checkAns(Boolean isInterupt){
		
		//Button inst1 = (Button)findViewById(R.id.tf_ins1);
		//Button inst4 = (Button)findViewById(R.id.tf_ins4);
		//Button inst6 = (Button)findViewById(R.id.tf_ins6);
		//Button inst8 = (Button)findViewById(R.id.tf_ins8);
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
		
		countdownTime.start();
			
			ans1_1.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					//mediaPlayer.stop();
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
					//mediaPlayer.stop();
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
					//mediaPlayer.stop();
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
					//mediaPlayer.stop();
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
					//mediaPlayer.stop();
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
					//mediaPlayer.stop();
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
					//mediaPlayer.stop();
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
					//mediaPlayer.stop();
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
					//mediaPlayer.stop();
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
					//mediaPlayer.stop();
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
					//mediaPlayer.stop();
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
				
				
				//ans4_1.setAnimation(myFadeOnceAnimation);
				//ans4_2.setAnimation(myFadeOnceAnimation);
				//ans5_1.setAnimation(myFadeOnceAnimation);
				//ans5_2.setAnimation(myFadeOnceAnimation);
				//ans6_1.setAnimation(myFadeOnceAnimation);
				//ans6_2.setAnimation(myFadeOnceAnimation);
				//ans8_1.setAnimation(myFadeOnceAnimation);
				//ans8_2.setAnimation(myFadeOnceAnimation);
				
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
			Intent intent = new Intent(PlL1Cross.this,SelectPoliceLevel.class);
			startActivity(intent);
		}
		});
	}
	
	
	int choiceExtra(Boolean interupt){
		int answerExtra,ExtraDay;
		
		Button ExtraInstruct = (Button)findViewById(R.id.charlen_int);
		Button thaiExtra = (Button)findViewById(R.id.Sunday_thai);
		Button engExtra = (Button)findViewById(R.id.Sunday_eng);
		Button CengExtra = (Button)findViewById(R.id.Sunday_Ceng);
		Button ans1 = (Button)findViewById(R.id.Sunday_ans1);
		Button ans2 = (Button)findViewById(R.id.Sunday_ans2);
		Button ans3 = (Button)findViewById(R.id.Sunday_ans3);
		
		thaiExtra.setVisibility(View.INVISIBLE);
		engExtra.setVisibility(View.INVISIBLE);
		CengExtra.setVisibility(View.INVISIBLE);
		ExtraInstruct.setVisibility(View.VISIBLE);
		
		if(interupt==false){
			ExtraDay = RanNum();
			ranDay = ExtraDay;
		}
		else{
			ExtraDay = ranDay;
		}
		
		if(ExtraDay == 1){
			answerExtra = 2;
			ExtraInstruct.setBackgroundResource(R.drawable.sunday_add);
			ans1.setBackgroundResource(R.drawable.sun1);
			ans2.setBackgroundResource(R.drawable.sun2);
			ans3.setBackgroundResource(R.drawable.sun3);
		}
		else if(ExtraDay == 2){
			answerExtra = 2;
			ExtraInstruct.setBackgroundResource(R.drawable.monday_add);
			ans1.setBackgroundResource(R.drawable.mon1);
			ans2.setBackgroundResource(R.drawable.mon2);
			ans3.setBackgroundResource(R.drawable.mon3);
		} 
		else if(ExtraDay == 3){
			answerExtra = 1;
			ExtraInstruct.setBackgroundResource(R.drawable.tuesday_add);
			ans1.setBackgroundResource(R.drawable.tue1);
			ans2.setBackgroundResource(R.drawable.tue2);
			ans3.setBackgroundResource(R.drawable.tue3);
		}
		else if(ExtraDay == 4){
			answerExtra = 2;
			ExtraInstruct.setBackgroundResource(R.drawable.wednesday_add);
			ans1.setBackgroundResource(R.drawable.wed1);
			ans2.setBackgroundResource(R.drawable.wed2);
			ans3.setBackgroundResource(R.drawable.wed3);
		}
		else if(ExtraDay == 5){
			answerExtra = 3;
			ExtraInstruct.setBackgroundResource(R.drawable.thursday_add);
			ans1.setBackgroundResource(R.drawable.thu1);
			ans2.setBackgroundResource(R.drawable.thu2);
			ans3.setBackgroundResource(R.drawable.thu3);
		}
		else if(ExtraDay == 6){
			answerExtra = 1;
			ExtraInstruct.setBackgroundResource(R.drawable.friday_add);
			ans1.setBackgroundResource(R.drawable.fri1);
			ans2.setBackgroundResource(R.drawable.fri2);
			ans3.setBackgroundResource(R.drawable.fri3);
		}
		else{
			answerExtra = 2;
			ExtraInstruct.setBackgroundResource(R.drawable.saturday_add);
			ans1.setBackgroundResource(R.drawable.sat1);
			ans2.setBackgroundResource(R.drawable.sat2);
			ans3.setBackgroundResource(R.drawable.sat3);
		}
		
		return answerExtra;
		
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
				
			}
			else if(itemNo == 2){
				answer = 2;
				inst1.setBackgroundResource(R.drawable.pl1_ins2);
			} 
			else if(itemNo == 3){
				answer = 1;
				inst1.setBackgroundResource(R.drawable.pl1_ins3);
			}
		}
		else if(itemNo < 6){
			
			inst4.setVisibility(View.VISIBLE);
			
						
			if(itemNo == 4){
				answer = 1;
				ans4_1.setVisibility(View.VISIBLE);
				ans4_2.setVisibility(View.VISIBLE);
				Thislayout.setBackgroundResource(R.drawable.pl1_bg2);
				inst4.setBackgroundResource(R.drawable.pl1_ins4);
				//ans4_1.setBackgroundResource(R.drawable.pl1_ans4_1);
				//ans4_2.setBackgroundResource(R.drawable.pl1_ans4_2);
				
			}
			else if(itemNo == 5){
				answer = 1;
				ans5_1.setVisibility(View.VISIBLE);
				ans5_2.setVisibility(View.VISIBLE);
				Thislayout.setBackgroundResource(R.drawable.pl1_bg3);
				inst4.setBackgroundResource(R.drawable.pl1_ins4);
				//ans5_1.setBackgroundResource(R.drawable.pl1_ans4_1);
				//ans5_2.setBackgroundResource(R.drawable.pl1_ans4_2);
				
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
				
			}
			else if(itemNo == 7){
				answer = 1;
				inst6.setBackgroundResource(R.drawable.pl1_ins7);
				ans6_1.setBackgroundResource(R.drawable.pl1_ans6_1);
				ans6_2.setBackgroundResource(R.drawable.pl1_ans6_2);
				
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
				
			}
			else if(itemNo == 9){
				answer = 1;
				inst8.setBackgroundResource(R.drawable.pl1_ins9);
				ans8_1.setBackgroundResource(R.drawable.pl1_9_1);
				ans8_2.setBackgroundResource(R.drawable.pl1_ans9_2);
				
			} 
			else{
				answer = 2;
				inst8.setBackgroundResource(R.drawable.pl1_ins10);
				ans8_1.setBackgroundResource(R.drawable.pl1_ans10_1);
				ans8_2.setBackgroundResource(R.drawable.pl1_ans10_2);
				
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
		
		Button dialogHomeBt = (Button)dialog.findViewById(R.id.scorehome);
		dialogHomeBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
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
				Intent intent = new Intent(PlL1Cross.this,PlL2NearFar.class);
				startActivity(intent);
				
			}
		});
		dialog.show();

	}	
	
	void showTimeout(){
		
		final View imgWrongFin = (View)findViewById(R.id.showwrong); 
		imgWrongFin.setVisibility(View.VISIBLE);
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		myDb.addItemScore("004",username,Round,Items,0,startTime);
		
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
	
	protected void onRestart() {
		// TODO Auto-generated method stub
		final myDBClass myDb = new myDBClass(this);
		//myDb.getReadableDatabase();
		
		//this comment for เธ•เธดเน�เธ� เน�เน�เน�เน�  continue (1,1) state
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