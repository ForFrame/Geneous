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
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


@SuppressWarnings("deprecation")
public class PlL2NearFar extends Activity {

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
		public void onFinish() { // à¹€à¸?â?¬à¹€à¸?à¸?à¹€à¸?à¸—à¹€à¸?ï¿½à¹€à¸?à¸?à¹€à¸?â€”à¹€à¸?à¸“à¹€à¸?ï¿½à¹€à¸?à¸’à¹€à¸?ï¿½à¹€à¸?â?¬à¹€à¸?à¸?à¹€à¸?à¸?à¹€à¸?ï¿½à¹€à¸?ï¿½à¹€à¸?à¸?à¹€à¸?à¸”à¹€à¸?ï¿½à¹€à¸?ï¿½
		// TODO Auto-generated method stub
			showTimeout();
		}
		
		@Override
		public void onTick(long remain) { // à¹€à¸?ï¿½à¹€à¸?ï¿½à¹€à¸?ï¿½à¹€à¸?â€?à¹€à¸?à¸?à¹€à¸?â€”à¹€à¸?à¸•à¹€à¸?ï¿½à¹€à¸?â€”à¹€à¸?à¸“à¹€à¸?ï¿½à¹€à¸?à¸’à¹€à¸?ï¿½à¹€à¸?â€”à¹€à¸?à¸?à¹€à¸?ï¿½ à¹€à¸?ï¿½ à¹€à¸?ï¿½à¹€à¸?à¸?à¹€à¸?à¸‘à¹€à¸?ï¿½à¹€à¸?ï¿½
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
	setContentView(R.layout.activity_pl_l2_nearfar);
	
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		username = myDb.SelectCurrentUser();
	
		//mediaPlayer.start();
		Round = myDb.getNumRound("005", username);
		
		/*int valueCalenTutorial = 0;
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
				
				//Round--;
				Typeface type = Typeface.createFromAsset(getAssets(),"fonts/teddy.ttf"); 
				if(!(username.equals("Guest"))){
					Round--;
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
				checkAnswer(rannum,thisitem);
				
			}
		}
		else{
			myDb.getWritableDatabase();
			myDb.addGameNo("003", "Short or Long", 1);
			myDb.emptyNumberTable();
			myDb.close();
			
			if(Round == 1){
				Intent intent2 = new Intent(PlL2NearFar.this,L1ScLongShortTutorial.class);
				startActivity(intent2);
			}
			else{*/
				game005();
			//}
		//}
	}

	void game005(){
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
				Intent intent = new Intent(PlL2NearFar.this,Main.class);
				startActivity(intent);
			}
			
		});
		if(count < 10){
			Random = RanNum();
			checkAnswer(Random,count+1);
		}
		else{
			scores = myDb.countScore("005", username, Round);
						
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
	
	void checkAnswer(final int RandomNum,final int item){
		
		final Button Answer1 = (Button)findViewById(R.id.picans1);
		final Button Answer2 = (Button)findViewById(R.id.picans2);
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		final int answer;
		final MediaPlayer soundCorrect = MediaPlayer.create(context, R.raw.crab_sound);
		final MediaPlayer soundWrong = MediaPlayer.create(context, R.raw.wrong_sound2);
		final Animation myFadeOnceAnimation = AnimationUtils.loadAnimation(PlL2NearFar.this, R.anim.tween_once);
		startTime = (20)*1000;
		final MyCountDown countdownTime = new MyCountDown(startTime,1000);
		
		final float countTime = (float) startTime /1000;
		final View imgWrong = (View)findViewById(R.id.showwrong); 
		final View imgCorrect = (View)findViewById(R.id.showcorrect);
		
		TextView current = (TextView) findViewById(R.id.currentitem);
		current.setText(item +"/ 10");
		
		countdownTime.start();
		
			answer = choice(RandomNum);
				Answer1.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//mediaPlayer.stop();
						if(answer == 1){
							imgCorrect.setVisibility(View.VISIBLE);
							countdownTime.cancel();
							soundCorrect.start();
							myDb.addItemScore("005",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							countdownTime.cancel();
							soundWrong.start();
							myDb.addItemScore("005",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});
				Answer2.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//mediaPlayer.stop();
						if(answer == 2){
							imgCorrect.setVisibility(View.VISIBLE);
							countdownTime.cancel();
							soundCorrect.start();
							myDb.addItemScore("005",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							countdownTime.cancel();
							soundWrong.start();
							myDb.addItemScore("005",username,Round,item,0,(countTime - timeRemain));
						}
					}
				});
							
		
		final View imgWrongClick = (View)findViewById(R.id.showwrong); 
		final View imgCorrectClick = (View)findViewById(R.id.showcorrect);
		
		imgWrongClick.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				soundWrong.stop();
				imgWrong.setVisibility(View.INVISIBLE);
				
				game005();
			}
		});
		
		imgCorrectClick.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				soundCorrect.stop();
				imgCorrect.setVisibility(View.INVISIBLE);
				
				game005();
			}
		});
		
		soundCorrect.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundCorrect) {
            	imgCorrect.setVisibility(View.INVISIBLE);
            	
            	game005();
            }
        });

		soundWrong.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	imgWrong.setVisibility(View.INVISIBLE);
            	
            	game005();
            }
        });
		
		Button HelpButton = (Button)findViewById(R.id.nearfarHelp);
		HelpButton.setOnClickListener(new View.OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//countdownTime.cancel();
				//Intent intent = new Intent(PlL2NearFar.this,PlL2NearFarTutorial.class);
				//intent.putExtra("putbutt", 1);
				//intent.putExtra("numran", RandomNum);
				//intent.putExtra("numitem", item);
				//startActivity(intent);
				
				Answer1.startAnimation(myFadeOnceAnimation);
				Answer2.startAnimation(myFadeOnceAnimation);
			}
		});
		
		Button nearfarButton = (Button)findViewById(R.id.nearfarbackHome);
		nearfarButton.setOnClickListener(new View.OnClickListener() {
		
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			countdownTime.cancel();
			Intent intent = new Intent(PlL2NearFar.this,SelectPoliceLevel.class);
			startActivity(intent);
		}
		});
	}
	
	int choice(int random){
		
		Button ans1 = (Button)findViewById(R.id.picans1);
		Button ans2 = (Button)findViewById(R.id.picans2);
		Random randomGenerator = new Random();
		//int nearOrfar = randomGenerator.nextInt(2)+1;
		
		AbsoluteLayout Thislayout=(AbsoluteLayout)findViewById(R.id.nearfarLayout);
		int answer=0;
	    
	    if(random == 1){
	    	answer = 1;
	    	Thislayout.setBackgroundResource(R.drawable.pl2_bg1);
			ans1.setBackgroundResource(R.drawable.pl2_choice1_1);
			ans2.setBackgroundResource(R.drawable.pl2_choice1_2);
		}
		else if(random == 2){
			answer = 2;
	    	Thislayout.setBackgroundResource(R.drawable.pl2_bg2);
			ans1.setBackgroundResource(R.drawable.pl2_choice2_1);
			ans2.setBackgroundResource(R.drawable.pl2_choice2_2);
		}
		else if(random == 3){
			answer = 2;
	    	Thislayout.setBackgroundResource(R.drawable.pl2_bg3);
			ans1.setBackgroundResource(R.drawable.pl2_choice3_1);
			ans2.setBackgroundResource(R.drawable.pl2_choice3_2);
		}
		else if(random == 4){
			answer = 1;
	    	Thislayout.setBackgroundResource(R.drawable.pl2_bg4);
			ans1.setBackgroundResource(R.drawable.pl2_choice4_1);
			ans2.setBackgroundResource(R.drawable.pl2_choice4_2);
		}
		else if(random == 5){
			answer = 1;
	    	Thislayout.setBackgroundResource(R.drawable.pl2_bg5);
			ans1.setBackgroundResource(R.drawable.pl2_choice5_1);
			ans2.setBackgroundResource(R.drawable.pl2_choice5_2);
		}
		else if(random == 6){
			answer = 1;
	    	Thislayout.setBackgroundResource(R.drawable.pl2_bg6);
			ans1.setBackgroundResource(R.drawable.pl2_choice6_1);
			ans2.setBackgroundResource(R.drawable.pl2_choice6_2);
		}
		else if(random == 7){
			answer = 2;
	    	Thislayout.setBackgroundResource(R.drawable.pl2_bg7);
			ans1.setBackgroundResource(R.drawable.pl2_choice7_1);
			ans2.setBackgroundResource(R.drawable.pl2_choice7_2);
		}
		else if(random == 8){
			answer = 1;
	    	Thislayout.setBackgroundResource(R.drawable.pl2_bg8);
			ans1.setBackgroundResource(R.drawable.pl2_choice8_1);
			ans2.setBackgroundResource(R.drawable.pl2_choice8_2);
		}
		else if(random == 9){
			answer = 1;
	    	Thislayout.setBackgroundResource(R.drawable.pl2_bg9);
			ans1.setBackgroundResource(R.drawable.pl2_choice9_1);
			ans2.setBackgroundResource(R.drawable.pl2_choice9_2);
		}
		else{
			answer = 2;
	    	Thislayout.setBackgroundResource(R.drawable.pl2_bg10);
			ans1.setBackgroundResource(R.drawable.pl2_choice10_1);
			ans2.setBackgroundResource(R.drawable.pl2_choice10_2);
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
				Intent intent = new Intent(PlL2NearFar.this,PoliceLevel2.class);
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
				
				Round = myDb.getNumRound("005", username);
				//Round++;
				
				game005();
			}
		});
		
		Button dialogNextBt = (Button)dialog.findViewById(R.id.scorenext);
		dialogNextBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				
				Intent intent = new Intent(PlL2NearFar.this,Main.class);
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
            	game005();
            }
        });
		
		final View imgWrongClick = (View)findViewById(R.id.showwrong); 
		
		imgWrongClick.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				soundWrongFin.stop();
				imgWrongFin.setVisibility(View.INVISIBLE);
				//Items++;
				game005();
			}
		});
		
	}
	
	protected void onRestart() {
		// TODO Auto-generated method stub
		final myDBClass myDb = new myDBClass(this);
		
		myDb.getWritableDatabase();
		myDb.ChangeHome(0);
		Intent intent = new Intent(PlL2NearFar.this,Main.class);
		startActivity(intent);
		
		
		super.onRestart();
	}
	
	/*@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		myDb.ChangeHome(0);
		
		super.onDestroy();
	}*/
}