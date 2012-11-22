package samcom.example.senoirandroid;


import java.util.Random;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class L1ScLongShort extends Activity {

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
	setContentView(R.layout.activity_l1_sc_longshort);
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		username = myDb.SelectCurrentUser();
	
		//mediaPlayer.start();
		Round = myDb.getNumRound("003", username);
		
		int valueCalenTutorial = 0;
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			valueCalenTutorial = extras.getInt("longshort_tuto");
			if(valueCalenTutorial == 1){
				game003();
			}
			else if(valueCalenTutorial == 2){
				
				int rannum = extras.getInt("numran");
				int thisitem = extras.getInt("numitem");
				username = myDb.SelectCurrentUser();
				
				Round--;
				Typeface type = Typeface.createFromAsset(getAssets(),"fonts/teddy.ttf"); 
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
				checkAns(rannum,thisitem);
				
			}
		}
		else{
			myDb.getWritableDatabase();
			myDb.addGameNo("003", "Short or Long", 1);
			myDb.emptyNumberTable();
			myDb.close();
			
			if(Round == 1){
				Intent intent2 = new Intent(L1ScLongShort.this,L1ScLongShortTutorial.class);
				startActivity(intent2);
			}
			else{
				game003();
			}
		}
	}

	void game003(){
		int scores;
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		int count = myDb.CountNumRan();
		int Random = 0;
		
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
				Intent intent = new Intent(L1ScLongShort.this,Main.class);
				startActivity(intent);
			}
			
		});
		if(count < 10){
			Random = RanNum();
			checkAns(Random,count+1);
		}
		else{
			scores = myDb.countScore("003", username, Round);
						
			if(username.equals("Guest")){
				myDb.close();
				myDb.getWritableDatabase();
				myDb.deleteGuest();
			}
			
			showPopup(scores);
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
	
	void checkAns(final int RandomNum,final int item){
		
		Button longAns = (Button)findViewById(R.id.longpic);
		Button shortAns = (Button)findViewById(R.id.shortpic);
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		final int answer;
		final MediaPlayer soundCorrect = MediaPlayer.create(context, R.raw.crab_sound);
		final MediaPlayer soundWrong = MediaPlayer.create(context, R.raw.wrong_sound2);
		
		startTime = (30)*1000;
		final MyCountDown countdownTime = new MyCountDown(startTime,1000);
		
		final float countTime = (float) startTime /1000;
		final View imgWrong = (View)findViewById(R.id.showwrong); 
		final View imgCorrect = (View)findViewById(R.id.showcorrect);
		
		TextView current = (TextView) findViewById(R.id.currentitem);
		current.setText(item +"/ 10");
		
		countdownTime.start();
		
			answer = choice(RandomNum);
				longAns.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//mediaPlayer.stop();
						if(answer == 1){
							imgCorrect.setVisibility(View.VISIBLE);
							countdownTime.cancel();
							soundCorrect.start();
							myDb.addItemScore("003",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							countdownTime.cancel();
							soundWrong.start();
							myDb.addItemScore("003",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				shortAns.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//mediaPlayer.stop();
						if(answer == 2){
							imgCorrect.setVisibility(View.VISIBLE);
							countdownTime.cancel();
							soundCorrect.start();
							myDb.addItemScore("003",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							countdownTime.cancel();
							soundWrong.start();
							myDb.addItemScore("003",username,Round,item,0,(countTime - timeRemain));
						}
					}
				});
							
		
		final View imgWrongClick = (View)findViewById(R.id.showwrong); 
		final View imgCorrectClick = (View)findViewById(R.id.showcorrect);
		
		imgWrongClick.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				soundWrong.stop();
				imgWrong.setVisibility(View.INVISIBLE);
				
				game003();
			}
		});
		
		imgCorrectClick.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				soundCorrect.stop();
				imgCorrect.setVisibility(View.INVISIBLE);
				
				game003();
			}
		});
		
		soundCorrect.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundCorrect) {
            	imgCorrect.setVisibility(View.INVISIBLE);
            	
            	game003();
            }
        });

		soundWrong.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	imgWrong.setVisibility(View.INVISIBLE);
            	
            	game003();
            }
        });
		
		Button HelpButton = (Button)findViewById(R.id.longshortHelp);
		HelpButton.setOnClickListener(new View.OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				countdownTime.cancel();
				Intent intent = new Intent(L1ScLongShort.this,L1ScLongShortTutorial.class);
				intent.putExtra("frombutt", 1);
				intent.putExtra("numran", RandomNum);
				intent.putExtra("numitem", item);
				startActivity(intent);
			}
		});
		
		Button calendarButton = (Button)findViewById(R.id.longshortbackHome);
		calendarButton.setOnClickListener(new View.OnClickListener() {
		
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			countdownTime.cancel();
			Intent intent = new Intent(L1ScLongShort.this,SchoolLevel1.class);
			startActivity(intent);
		}
		});
	}
	
	int choice(int random){
		
		Button ans1 = (Button)findViewById(R.id.longpic);
		Button ans2 = (Button)findViewById(R.id.shortpic);
		Random randomGenerator = new Random();
		int longOrshort = randomGenerator.nextInt(2)+1;
		
		AbsoluteLayout Thislayout=(AbsoluteLayout)findViewById(R.id.longshortLayout);
		
	    if(longOrshort == 2)
	    {	
	    	Thislayout.setBackgroundResource(R.drawable.short_bg);
	    	
	    }
	    else
	    {	
	    	Thislayout.setBackgroundResource(R.drawable.long_bg);
	    	
	    }
	    
	    if(random == 1){
			ans1.setBackgroundResource(R.drawable.pencil_long);
			ans2.setBackgroundResource(R.drawable.pencil_shot);
		}
		else if(random == 2){
			ans1.setBackgroundResource(R.drawable.brush_long);
			ans2.setBackgroundResource(R.drawable.brush_shot);
		}
		else if(random == 3){
			ans1.setBackgroundResource(R.drawable.collor_long);
			ans2.setBackgroundResource(R.drawable.collor_shot);
		}
		else if(random == 4){
			ans1.setBackgroundResource(R.drawable.job_long);
			ans2.setBackgroundResource(R.drawable.job_shot);
		}
		else if(random == 5){
			ans1.setBackgroundResource(R.drawable.knife_long);
			ans2.setBackgroundResource(R.drawable.knife_shot);
		}
		else if(random == 6){
			ans1.setBackgroundResource(R.drawable.nail_long);
			ans2.setBackgroundResource(R.drawable.nail_shot);
		}
		else if(random == 7){
			ans1.setBackgroundResource(R.drawable.pen_long);
			ans2.setBackgroundResource(R.drawable.pen_shot);
		}
		else if(random == 8){
			ans1.setBackgroundResource(R.drawable.pin_long);
			ans2.setBackgroundResource(R.drawable.pin_shot);
		}
		else if(random == 9){
			ans1.setBackgroundResource(R.drawable.rubber_long);
			ans2.setBackgroundResource(R.drawable.rubber_shot);
		}
		else{
			ans1.setBackgroundResource(R.drawable.ruler_long);
			ans2.setBackgroundResource(R.drawable.ruler_shot);
		}
		
		return longOrshort;
		
	}
	
	protected void showPopup(int scores){

		// custom dialog
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.activity_dialog_score_sclv1g1);
		dialog.setCanceledOnTouchOutside(false);
		
	
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
				Intent intent = new Intent(L1ScLongShort.this,SchoolLevel1.class);
				startActivity(intent);
				
				//finish();
				
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
				
				Round = myDb.getNumRound("003", username);
				//Round++;
				
				game003();
			}
		});
		
		Button dialogNextBt = (Button)dialog.findViewById(R.id.scorenext);
		dialogNextBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				//Intent intent = new Intent(L1ScLongShort.this,L1ScCount.class);
				//startActivity(intent);
				myDb.getWritableDatabase();
				myDb.ChangeHome(0);
				Intent intent = new Intent(L1ScLongShort.this,Main.class);
				startActivity(intent);
				
			}
		});
		dialog.show();

	}	
	
	void showTimeout(){
		
		final View imgWrongFin = (View)findViewById(R.id.showwrong); 
		imgWrongFin.setVisibility(View.VISIBLE);
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		int item = myDb.CountNumRan();
		myDb.close();
		myDb.getWritableDatabase();
		myDb.addItemScore("003",username,Round,item,0,startTime);
		
		final MediaPlayer soundWrongFin = MediaPlayer.create(context, R.raw.wrong_sound2);
		soundWrongFin.start();
		
		soundWrongFin.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	imgWrongFin.setVisibility(View.INVISIBLE);
            	game003();
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
			Intent intent = new Intent(L1ScLongShort.this,Main.class);
			startActivity(intent);
		//}
		
		super.onRestart();
	}
}