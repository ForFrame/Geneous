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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class L1ScCalendar extends Activity {

	String username;
	long startTime;
	final Context context = this;
	int timeRemain;
	boolean firstSound;
	boolean RunningCount = false;
	int Round;
	int Day = 1;
	int ranDay=0;
	MyCountDown countdownTime;
	
	MediaPlayer instructPage;
	MediaPlayer instructThai;
	MediaPlayer instructEng;
	MediaPlayer instructColor;
	MediaPlayer soundMain;
		
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
		
		setContentView(R.layout.activity_l1_sc_calendar);
		
		soundMain = MediaPlayer.create(context, R.raw.main);
		soundMain.start();
		soundMain.setLooping(true);
		soundMain.setVolume(30, 30);
		
		instructThai = MediaPlayer.create(context, R.raw.sclv2_sun_thai);
		instructEng = MediaPlayer.create(context, R.raw.sclv2_sunday);
		instructColor = MediaPlayer.create(context, R.raw.sclv2_red);
		instructPage = MediaPlayer.create(context, R.raw.ins_sclv2_sun);
	
	
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		username = myDb.SelectCurrentUser();
		
		//mediaPlayer.start();
		Round = myDb.getNumRound("002", username);
		myDb.getWritableDatabase();
		myDb.emptyNumberTable();
		
		game002();
			
		
	}

	void game002(){
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
				myDb.ChangeHome(0);
				stopSound();
				myUser.showLoginPopup();

			}
		});
		
		Button LogoutButton = (Button)findViewById(R.id.logout);
		LogoutButton.setOnClickListener(new View.OnClickListener() {
			 
			public void onClick(View v) {	
				myDb.logoutUser(username);
				stopSound();
				Intent intent = new Intent(L1ScCalendar.this,Main.class);
				intent.putExtra("Logout", 1);
				startActivity(intent);
			}
			
		});
		if(Day < 11){
			//startTime = (30)*1000;
			checkAns(false);
		}
		else{
			scores = myDb.countScore("002", username, Round);
			countdownTime.cancel();
			showPopup(scores);
		}
	}
	
	
	void stopSound(){
		if(instructPage.isPlaying()){
			instructPage.stop();
		}
		if(instructThai.isPlaying()){
			instructThai.stop();
		}
		if(instructEng.isPlaying()){
			instructEng.stop();
		}
		if(instructColor.isPlaying()){
			instructColor.stop();
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
void checkAns(Boolean isInterupt){
		Button ExtraInstruct = (Button)findViewById(R.id.charlen_int);
		Button thai = (Button)findViewById(R.id.Sunday_thai);
		Button eng = (Button)findViewById(R.id.Sunday_eng);
		Button Ceng = (Button)findViewById(R.id.Sunday_Ceng);
		Button ans1 = (Button)findViewById(R.id.Sunday_ans1);
		Button ans2 = (Button)findViewById(R.id.Sunday_ans2);
		Button ans3 = (Button)findViewById(R.id.Sunday_ans3);
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		final int answer;
		final MediaPlayer soundCorrect = MediaPlayer.create(context, R.raw.crab_sound);
		final MediaPlayer soundWrong = MediaPlayer.create(context, R.raw.wrong_sound2);
		
		
		
		if(Day<8){	
			answer = choice(Day);
			startTime = (20)*1000;
			//instructThai.start();
		}
		else{
			answer = choiceExtra(isInterupt);
			startTime = (30)*1000;
			//instructPage.start();
		}
		
		
		//countdownTime = new MyCountDown(startTime,1000);
		
		final float countTime = (float) startTime /1000;
		timeRemain = (int)countTime;
		final View imgWrong = (View)findViewById(R.id.showwrong); 
		final View imgCorrect = (View)findViewById(R.id.showcorrect);
		
		TextView current = (TextView) findViewById(R.id.currentitem);
		current.setText(Day +"/ 10");
		
		ExtraInstruct.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					stopSound();
					instructPage.start();
				}
			});
			thai.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					stopSound();
					instructThai.start();
				}
			});
			eng.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					stopSound();
					instructEng.start();
				}
			});
			Ceng.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					// TODO Auto-generated method stub
					stopSound();
					instructColor.start();
				}
			});
		
			final MediaPlayer soundAns = MediaPlayer.create(context, R.raw.choose_correct_ans);
			final View helpAnswer = (View)findViewById(R.id.showAnswer);
			final Animation myFadeonceAnimation = AnimationUtils.loadAnimation(L1ScCalendar.this, R.anim.tween_once);
			final Animation myFadeAnimation = AnimationUtils.loadAnimation(L1ScCalendar.this, R.anim.tween);
			final ImageView instructFinger = (ImageView)findViewById(R.id.finger);
			if(Round == 1 || (username.equals("Guest") && Day == 1)){
				instructThai.start();
				firstSound = true;
			}
			else{
				countdownTime = new MyCountDown(startTime,1000);
				countdownTime.start();	
				if(Day<8){	
					instructThai.start();
				}
				else{
					instructPage.start();
				}
			}
			instructPage.setOnCompletionListener(new OnCompletionListener() {
	            public void onCompletion(MediaPlayer soundCorrect) {
	            	if(Round == 1 || (username.equals("Guest") && Day == 1)){
	            		if(firstSound == true){
	            			instructFinger.startAnimation(myFadeAnimation);
	            			firstSound = false;
	            		}
	            		else{
		            		helpAnswer.startAnimation(myFadeonceAnimation);
		        			countdownTime = new MyCountDown(startTime,1000);
		        			countdownTime.start();
		        			instructFinger.clearAnimation();
		            		soundAns.start();
	            		}
	            	}
	            }
	        });
		
				ans1.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						stopSound();
						if(answer == 1){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("002",username,Round,Day,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("002",username,Round,Day,0,(countTime - timeRemain));
						}
						
					}
				});
				ans2.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						stopSound();
						if(answer == 2){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("002",username,Round,Day,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("002",username,Round,Day,0,(countTime - timeRemain));
						}
					}
				});
				ans3.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						stopSound();
						if(answer == 3){
							imgCorrect.setVisibility(View.VISIBLE);
							stopTime();
							soundCorrect.start();
							myDb.addItemScore("002",username,Round,Day,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							stopTime();
							soundWrong.start();
							myDb.addItemScore("002",username,Round,Day,0,(countTime - timeRemain));
						}
					}
				});
			
		
		final View imgWrongClick = (View)findViewById(R.id.showwrong);
		final View imgCorrectClick = (View)findViewById(R.id.showcorrect);
		imgWrongClick.setClickable(false);
		imgCorrectClick.setClickable(false);
		
		
		soundCorrect.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundCorrect) {
            	imgCorrect.setVisibility(View.INVISIBLE);
            	Day++;
            	game002();
            }
        });

		soundWrong.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	imgWrong.setVisibility(View.INVISIBLE);
            	Day++;
            	game002();
            }
        });
		
		Button HelpButton = (Button)findViewById(R.id.calendarHelp);
		HelpButton.setOnClickListener(new View.OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//countdownTime.cancel();
				helpAnswer.startAnimation(myFadeonceAnimation);
			}
		});
		
		Button calendarButton = (Button)findViewById(R.id.calendarbackHome);
		calendarButton.setOnClickListener(new View.OnClickListener() {
		
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			stopSound();
			stopTime();
			Intent in = new Intent(L1ScCalendar.this,Main.class);
			in.putExtra("showPopup", 1);
			startActivity(in);
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
			instructPage = MediaPlayer.create(context, R.raw.ins_sclv2_sun);
		}
		else if(ExtraDay == 2){
			answerExtra = 2;
			ExtraInstruct.setBackgroundResource(R.drawable.monday_add);
			ans1.setBackgroundResource(R.drawable.mon1);
			ans2.setBackgroundResource(R.drawable.mon2);
			ans3.setBackgroundResource(R.drawable.mon3);
			instructPage = MediaPlayer.create(context, R.raw.ins_sclv2_mon);
		} 
		else if(ExtraDay == 3){
			answerExtra = 1;
			ExtraInstruct.setBackgroundResource(R.drawable.tuesday_add);
			ans1.setBackgroundResource(R.drawable.tue1);
			ans2.setBackgroundResource(R.drawable.tue2);
			ans3.setBackgroundResource(R.drawable.tue3);
			instructPage = MediaPlayer.create(context, R.raw.ins_sclv2_tue);
		}
		else if(ExtraDay == 4){
			answerExtra = 2;
			ExtraInstruct.setBackgroundResource(R.drawable.wednesday_add);
			ans1.setBackgroundResource(R.drawable.wed1);
			ans2.setBackgroundResource(R.drawable.wed2);
			ans3.setBackgroundResource(R.drawable.wed3);
			instructPage = MediaPlayer.create(context, R.raw.ins_sclv2_wed);
		}
		else if(ExtraDay == 5){
			answerExtra = 3;
			ExtraInstruct.setBackgroundResource(R.drawable.thursday_add);
			ans1.setBackgroundResource(R.drawable.thu1);
			ans2.setBackgroundResource(R.drawable.thu2);
			ans3.setBackgroundResource(R.drawable.thu3);
			instructPage = MediaPlayer.create(context, R.raw.ins_sclv2_thu);
		}
		else if(ExtraDay == 6){
			answerExtra = 1;
			ExtraInstruct.setBackgroundResource(R.drawable.friday_add);
			ans1.setBackgroundResource(R.drawable.fri1);
			ans2.setBackgroundResource(R.drawable.fri2);
			ans3.setBackgroundResource(R.drawable.fri3);
			instructPage = MediaPlayer.create(context, R.raw.ins_sclv2_fri);
		}
		else{
			answerExtra = 2;
			ExtraInstruct.setBackgroundResource(R.drawable.saturday_add);
			ans1.setBackgroundResource(R.drawable.sat1);
			ans2.setBackgroundResource(R.drawable.sat2);
			ans3.setBackgroundResource(R.drawable.sat3);
			instructPage = MediaPlayer.create(context, R.raw.ins_sclv2_sat);
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
	
	int choice(int days){
		Button ExtraNo = (Button)findViewById(R.id.charlen_int);
		Button thai = (Button)findViewById(R.id.Sunday_thai);
		Button eng = (Button)findViewById(R.id.Sunday_eng);
		Button Ceng = (Button)findViewById(R.id.Sunday_Ceng);
		Button ans1 = (Button)findViewById(R.id.Sunday_ans1);
		Button ans2 = (Button)findViewById(R.id.Sunday_ans2);
		Button ans3 = (Button)findViewById(R.id.Sunday_ans3);
		int answer = 0;
		
		thai.setVisibility(View.VISIBLE);
		eng.setVisibility(View.VISIBLE);
		Ceng.setVisibility(View.VISIBLE);
		ExtraNo.setVisibility(View.INVISIBLE);
		
		if(days == 1){
			answer = 2;
			thai.setBackgroundResource(R.drawable.sun_thai);
			eng.setBackgroundResource(R.drawable.sun_day_eng);
			Ceng.setBackgroundResource(R.drawable.sun_color_eng);
			ans1.setBackgroundResource(R.drawable.sun1);
			ans2.setBackgroundResource(R.drawable.sun2);
			ans3.setBackgroundResource(R.drawable.sun3);
			instructThai = MediaPlayer.create(context, R.raw.sclv2_sun_thai);
			instructEng = MediaPlayer.create(context, R.raw.sclv2_sunday);
			instructColor = MediaPlayer.create(context, R.raw.sclv2_red);
		}
		else if(days == 2){
			answer = 2;
			thai.setBackgroundResource(R.drawable.mon_thai);
			eng.setBackgroundResource(R.drawable.mon_day_eng);
			Ceng.setBackgroundResource(R.drawable.mon_color_eng);
			ans1.setBackgroundResource(R.drawable.mon1);
			ans2.setBackgroundResource(R.drawable.mon2);
			ans3.setBackgroundResource(R.drawable.mon3);
			instructThai = MediaPlayer.create(context, R.raw.sclv2_mon_thai);
			instructEng = MediaPlayer.create(context, R.raw.sclv2_monday);
			instructColor = MediaPlayer.create(context, R.raw.sclv2_yellow);
		} 
		else if(days == 3){
			answer = 1;
			thai.setBackgroundResource(R.drawable.tue_thai);
			eng.setBackgroundResource(R.drawable.tue_day_eng);
			Ceng.setBackgroundResource(R.drawable.tue_color_eng);
			ans1.setBackgroundResource(R.drawable.tue1);
			ans2.setBackgroundResource(R.drawable.tue2);
			ans3.setBackgroundResource(R.drawable.tue3);
			instructThai = MediaPlayer.create(context, R.raw.sclv2_tue_thai);
			instructEng = MediaPlayer.create(context, R.raw.sclv2_tuesday);
			instructColor = MediaPlayer.create(context, R.raw.sclv2_pink);
		}
		else if(days == 4){
			answer = 2;
			thai.setBackgroundResource(R.drawable.wed_thai);
			eng.setBackgroundResource(R.drawable.wed_day_eng);
			Ceng.setBackgroundResource(R.drawable.wed_color_eng);
			ans1.setBackgroundResource(R.drawable.wed1);
			ans2.setBackgroundResource(R.drawable.wed2);
			ans3.setBackgroundResource(R.drawable.wed3);
			instructThai = MediaPlayer.create(context, R.raw.sclv2_wed_thai);
			instructEng = MediaPlayer.create(context, R.raw.sclv2_wednesday);
			instructColor = MediaPlayer.create(context, R.raw.sclv2_green);
		}
		else if(days == 5){
			answer = 3;
			thai.setBackgroundResource(R.drawable.thu_thai);
			eng.setBackgroundResource(R.drawable.thu_day_eng);
			Ceng.setBackgroundResource(R.drawable.thu_color_eng);
			ans1.setBackgroundResource(R.drawable.thu1);
			ans2.setBackgroundResource(R.drawable.thu2);
			ans3.setBackgroundResource(R.drawable.thu3);
			instructThai = MediaPlayer.create(context, R.raw.sclv2_thur_thai);
			instructEng = MediaPlayer.create(context, R.raw.sclv2_thursday);
			instructColor = MediaPlayer.create(context, R.raw.sclv2_orange);
		}
		else if(days == 6){
			answer = 1;
			thai.setBackgroundResource(R.drawable.fri_thai);
			eng.setBackgroundResource(R.drawable.fri_day_eng);
			Ceng.setBackgroundResource(R.drawable.fri_color_eng);
			ans1.setBackgroundResource(R.drawable.fri1);
			ans2.setBackgroundResource(R.drawable.fri2);
			ans3.setBackgroundResource(R.drawable.fri3);
			instructThai = MediaPlayer.create(context, R.raw.sclv2_fri_thai);
			instructEng = MediaPlayer.create(context, R.raw.sclv2_friday);
			instructColor = MediaPlayer.create(context, R.raw.blue);
		}
		else{
			answer = 2;
			thai.setBackgroundResource(R.drawable.sat_thai);
			eng.setBackgroundResource(R.drawable.sat_day_eng);
			Ceng.setBackgroundResource(R.drawable.sat_color_eng);
			ans1.setBackgroundResource(R.drawable.sat1);
			ans2.setBackgroundResource(R.drawable.sat2);
			ans3.setBackgroundResource(R.drawable.sat3);
			instructThai = MediaPlayer.create(context, R.raw.sclv2_sat_thai);
			instructEng = MediaPlayer.create(context, R.raw.sclv2_saturday);
			instructColor = MediaPlayer.create(context, R.raw.sclv2_purple);
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
				Intent in = new Intent(L1ScCalendar.this,Main.class);
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
				myDb.getReadableDatabase();
				final View imgWrongpop = (View)findViewById(R.id.showwrong); 
				final View imgCorrectpop = (View)findViewById(R.id.showcorrect);
				imgWrongpop.setVisibility(View.INVISIBLE);
				imgCorrectpop.setVisibility(View.INVISIBLE);
				
				Round = myDb.getNumRound("002", username);
				//Round++;
				Day=1;
				game002();
			}
		});
		
		Button dialogNextBt = (Button)dialog.findViewById(R.id.scorenext);
		dialogNextBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(L1ScCalendar.this,L1ScLongShort.class);
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
		myDb.addItemScore("002",username,Round,Day,0,startTime/1000);
		
		final MediaPlayer soundWrongFin = MediaPlayer.create(context, R.raw.wrong_sound2);
		soundWrongFin.start();
		
		soundWrongFin.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	imgWrongFin.setVisibility(View.INVISIBLE);
            	game002();
            }
        });
		
	}
	
	protected void onRestart() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(L1ScCalendar.this,Main.class);
		startActivity(intent);
		
		super.onRestart();
	}
	
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
    	stopSound();
		super.onDestroy();
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if(soundMain.isLooping()){
    		soundMain.stop();
    	}
		stopSound();
    	
		super.onPause();
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        	stopTime();
			Intent in = new Intent(L1ScCalendar.this,Main.class);
			in.putExtra("showPopup", 1);
			startActivity(in);  
        	return false;
        }
	    return super.onKeyDown(keyCode, event);
	}
}