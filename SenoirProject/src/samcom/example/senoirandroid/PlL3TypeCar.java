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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class PlL3TypeCar extends Activity {

	String username;
	long startTime;
	final Context context = this;
	float timeRemain;
	int Round;
	int Day = 1;
	int ranDay=0;
	
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
	
	setContentView(R.layout.activity_l1_sc_calendar);
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		username = myDb.SelectCurrentUser();
		
		//mediaPlayer.start();
		Round = myDb.getNumRound("002", username);
		
		int valueCalenTutorial = 0;
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
				Intent intent2 = new Intent(PlL3TypeCar.this,L1ScCalendarTutorial.class);
				startActivity(intent2);
			}
			else{
				game002();
			}
		}
	}

	void game002(){
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
				Intent intent = new Intent(PlL3TypeCar.this,Main.class);
				startActivity(intent);
			}
			
		});
		if(Day < 11){
			startTime = (30)*1000;
			checkAns(false);
		}
		else{
			scores = myDb.countScore("002", username, Round);
			myDb.getWritableDatabase();
			myDb.emptyNumberTable();
					
			if(username.equals("Guest")){
				myDb.deleteGuest();
			}
			
			showPopup(scores);
		}
	}
	
	
void checkAns(Boolean isInterupt){
		
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
		}
		else{
			answer = choiceExtra(isInterupt);
			startTime = (30)*1000;
		}
		
		
		final MyCountDown countdownTime = new MyCountDown(startTime,1000);
		
		final float countTime = (float) startTime /1000;
		final View imgWrong = (View)findViewById(R.id.showwrong); 
		final View imgCorrect = (View)findViewById(R.id.showcorrect);
		
		TextView current = (TextView) findViewById(R.id.currentitem);
		current.setText(Day +"/ 10");
		
		
		
		countdownTime.start();
		
				ans1.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//mediaPlayer.stop();
						if(answer == 1){
							imgCorrect.setVisibility(View.VISIBLE);
							countdownTime.cancel();
							soundCorrect.start();
							myDb.addItemScore("002",username,Round,Day,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							countdownTime.cancel();
							soundWrong.start();
							myDb.addItemScore("002",username,Round,Day,0,(countTime - timeRemain));
						}
						
					}
				});
				ans2.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//mediaPlayer.stop();
						if(answer == 2){
							imgCorrect.setVisibility(View.VISIBLE);
							countdownTime.cancel();
							soundCorrect.start();
							myDb.addItemScore("002",username,Round,Day,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							countdownTime.cancel();
							soundWrong.start();
							myDb.addItemScore("002",username,Round,Day,0,(countTime - timeRemain));
						}
					}
				});
				ans3.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//mediaPlayer.stop();
						if(answer == 3){
							imgCorrect.setVisibility(View.VISIBLE);
							countdownTime.cancel();
							soundCorrect.start();
							myDb.addItemScore("002",username,Round,Day,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							countdownTime.cancel();
							soundWrong.start();
							myDb.addItemScore("002",username,Round,Day,0,(countTime - timeRemain));
						}
					}
				});
			
		
		final View imgWrongClick = (View)findViewById(R.id.showwrong); 
		final View imgCorrectClick = (View)findViewById(R.id.showcorrect);
		
		imgWrongClick.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				soundWrong.stop();
				imgWrong.setVisibility(View.INVISIBLE);
				Day++;
				game002();
			}
		});
		
		imgCorrectClick.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				soundCorrect.stop();
				imgCorrect.setVisibility(View.INVISIBLE);
				Day++;
				game002();
			}
		});
		
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
				countdownTime.cancel();
				Intent intent = new Intent(PlL3TypeCar.this,L1ScCalendarTutorial.class);
				intent.putExtra("frombutt", 1);
				
				intent.putExtra("today", Day);
				if(Day>7)
					  intent.putExtra("numran", ranDay);
				startActivity(intent);
			}
		});
		
		Button calendarButton = (Button)findViewById(R.id.calendarbackHome);
		calendarButton.setOnClickListener(new View.OnClickListener() {
		
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			countdownTime.cancel();
			Intent intent = new Intent(PlL3TypeCar.this,SelectSchoolLevel.class);
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
		}
		else if(days == 2){
			answer = 2;
			thai.setBackgroundResource(R.drawable.mon_thai);
			eng.setBackgroundResource(R.drawable.mon_day_eng);
			Ceng.setBackgroundResource(R.drawable.mon_color_eng);
			ans1.setBackgroundResource(R.drawable.mon1);
			ans2.setBackgroundResource(R.drawable.mon2);
			ans3.setBackgroundResource(R.drawable.mon3);
		} 
		else if(days == 3){
			answer = 1;
			thai.setBackgroundResource(R.drawable.tue_thai);
			eng.setBackgroundResource(R.drawable.tue_day_eng);
			Ceng.setBackgroundResource(R.drawable.tue_color_eng);
			ans1.setBackgroundResource(R.drawable.tue1);
			ans2.setBackgroundResource(R.drawable.tue2);
			ans3.setBackgroundResource(R.drawable.tue3);
		}
		else if(days == 4){
			answer = 2;
			thai.setBackgroundResource(R.drawable.wed_thai);
			eng.setBackgroundResource(R.drawable.wed_day_eng);
			Ceng.setBackgroundResource(R.drawable.wed_color_eng);
			ans1.setBackgroundResource(R.drawable.wed1);
			ans2.setBackgroundResource(R.drawable.wed2);
			ans3.setBackgroundResource(R.drawable.wed3);
		}
		else if(days == 5){
			answer = 3;
			thai.setBackgroundResource(R.drawable.thu_thai);
			eng.setBackgroundResource(R.drawable.thu_day_eng);
			Ceng.setBackgroundResource(R.drawable.thu_color_eng);
			ans1.setBackgroundResource(R.drawable.thu1);
			ans2.setBackgroundResource(R.drawable.thu2);
			ans3.setBackgroundResource(R.drawable.thu3);
		}
		else if(days == 6){
			answer = 1;
			thai.setBackgroundResource(R.drawable.fri_thai);
			eng.setBackgroundResource(R.drawable.fri_day_eng);
			Ceng.setBackgroundResource(R.drawable.fri_color_eng);
			ans1.setBackgroundResource(R.drawable.fri1);
			ans2.setBackgroundResource(R.drawable.fri2);
			ans3.setBackgroundResource(R.drawable.fri3);
		}
		else{
			answer = 2;
			thai.setBackgroundResource(R.drawable.sat_thai);
			eng.setBackgroundResource(R.drawable.sat_day_eng);
			Ceng.setBackgroundResource(R.drawable.sat_color_eng);
			ans1.setBackgroundResource(R.drawable.sat1);
			ans2.setBackgroundResource(R.drawable.sat2);
			ans3.setBackgroundResource(R.drawable.sat3);
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
				Intent intent = new Intent(PlL3TypeCar.this,SchoolLevel2.class);
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
				Intent intent = new Intent(PlL3TypeCar.this,L1ScLongShort.class);
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
		myDb.addItemScore("002",username,Round,Day,0,startTime);
		
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
			Intent intent = new Intent(PlL3TypeCar.this,Main.class);
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