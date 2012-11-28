package samcom.example.senoirandroid;

import samcom.example.senoirandroid.L1ScCount.MyCountDown;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class L1ScCalendar extends Activity {

	String username;
	long startTime;
	final Context context = this;
	float timeRemain;
	int Round;
	int Day = 1;
	
	public class MyCountDown extends CountDownTimer {
		public MyCountDown(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
		// TODO Auto-generated constructor stub
		}
		
		@Override
		public void onFinish() { // เมื่อทำงานเสร็จสิ้น
		// TODO Auto-generated method stub
			showTimeout();
		}
		
		@Override
		public void onTick(long remain) { // ในขณะที่ทำงานทุก ๆ ครั้ง
		// TODO Auto-generated method stub
			
			TextView result = (TextView) findViewById(R.id.textView);
			int timeRemain = (int) (remain) / 1000;
			result.setText(" Times: " + timeRemain);
		}
	}

@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_l1_sc_calendar);
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		username = myDb.SelectCurrentUser();
	
		//mediaPlayer.start();
		Round = myDb.getNumRound("002", username);
		
		game002();
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
				myDb.ChangeHome(0);
				//Intent intent = new Intent(L1ScCount.this,Main.class);
				//startActivity(intent);
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
				Intent intent = new Intent(L1ScCalendar.this,Main.class);
				startActivity(intent);
			}
			
		});
		if(Day < 8){
			final long startTime = (30)*1000;
			checkAns();
		}
		else{
			scores = myDb.countScore("002", username, Round);
						
			if(username.equals("Guest")){
				myDb.close();
				myDb.getWritableDatabase();
				myDb.deleteGuest();
			}
			
			showPopup(scores);
		}
	}
	void checkAns(){
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
		
		startTime = (30)*1000;
		final MyCountDown countdown = new MyCountDown(startTime,1000);
		
		final float countTime = (float) startTime /1000;
		final View imgWrong = (View)findViewById(R.id.showwrong); 
		final View imgCorrect = (View)findViewById(R.id.showcorrect);

		
			answer = choice(Day);
				ans1.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//mediaPlayer.stop();
						if(answer == 1){
							imgCorrect.setVisibility(View.VISIBLE);
							countdown.cancel();
							soundCorrect.start();
							myDb.addItemScore("002",username,Round,Day,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							countdown.cancel();
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
							countdown.cancel();
							soundCorrect.start();
							myDb.addItemScore("002",username,Round,Day,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							countdown.cancel();
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
							countdown.cancel();
							soundCorrect.start();
							myDb.addItemScore("002",username,Round,Day,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							countdown.cancel();
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
				Intent intent = new Intent(L1ScCalendar.this,SchoolLevel1.class);
				startActivity(intent);
			}
		});
		
		Button calendarButton = (Button)findViewById(R.id.calendarbackHome);
		calendarButton.setOnClickListener(new View.OnClickListener() {
		
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			countdown.cancel();
			Intent intent = new Intent(L1ScCalendar.this,SchoolLevel1.class);
			startActivity(intent);
		}
		});
	}
	
	
	int choice(int days){
		Button thai = (Button)findViewById(R.id.Sunday_thai);
		Button eng = (Button)findViewById(R.id.Sunday_eng);
		Button Ceng = (Button)findViewById(R.id.Sunday_Ceng);
		Button ans1 = (Button)findViewById(R.id.Sunday_ans1);
		Button ans2 = (Button)findViewById(R.id.Sunday_ans2);
		Button ans3 = (Button)findViewById(R.id.Sunday_ans3);
		int answer = 0;
		
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
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.activity_dialog_score_sclv1g1);
	
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		 
		switch(scores){
			case 0: ImageView score0 = (ImageView)dialog.findViewById(R.id.star0); 
					score0.setVisibility(ImageView.VISIBLE);	break;
				
			case 4: ImageView score4 = (ImageView)dialog.findViewById(R.id.star4); 
					score4.setVisibility(ImageView.VISIBLE);	break;			
			
			case 3: ImageView score3 = (ImageView)dialog.findViewById(R.id.star3); 
					score3.setVisibility(ImageView.VISIBLE);	break;
			
			case 2: ImageView score2 = (ImageView)dialog.findViewById(R.id.star2); 
					score2.setVisibility(ImageView.VISIBLE);	break;	
			
			case 1: ImageView score1 = (ImageView)dialog.findViewById(R.id.star1); 
					score1.setVisibility(ImageView.VISIBLE);	break;
			
			default: ImageView score5 = (ImageView)dialog.findViewById(R.id.star5); 
					score5.setVisibility(ImageView.VISIBLE);	break;		
		}
		
		Button dialogHomeBt = (Button)dialog.findViewById(R.id.scorehome);
		dialogHomeBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(L1ScCalendar.this,SchoolLevel1.class);
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
				Intent intent = new Intent(L1ScCalendar.this,SchoolLevel2.class);
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
		
		//this comment for ติ๊ก ๆๆๆๆ  continue (1,1) state
		//Boolean isThisContinue;
		//isThisContinue = myDb.isCurrentContinue();
		//myDb.close();
		//if(isThisContinue == true){
		//	game002();
		//}
		//else{
			myDb.getWritableDatabase();
			myDb.ChangeHome(0);
			Intent intent = new Intent(L1ScCalendar.this,Main.class);
			startActivity(intent);
		//}
		
		super.onRestart();
	}
}