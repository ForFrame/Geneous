package samcom.example.senoirandroid;


import java.util.Random;

import samcom.example.senoirandroid.FrL2Shadow.MyCountDown;

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
public class FrL3Typeani extends Activity {

	String username;
	long startTime;
	final Context context = this;
	int timeRemain;
	boolean firstSound;
	boolean RunningCount = false;
	boolean OnPause = false;
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
		public void onFinish() { // เน€๏ฟฝ?๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝ?เน€๏ฟฝ?เธ—เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝ?เน€๏ฟฝ?โ€”เน€๏ฟฝ?เธ“เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?เธ’เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝ?เน€๏ฟฝ?๏ฟฝ?เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝ?เน€๏ฟฝ?เธ”เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝ
		// TODO Auto-generated method stub
			RunningCount = false;
			showTimeout();
		}
		
		@Override
		public void onTick(long remain) { // เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝ?เน€๏ฟฝ?๏ฟฝ?เน€๏ฟฝ?โ€”เน€๏ฟฝ?เธ•เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?โ€”เน€๏ฟฝ?เธ“เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?เธ’เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?โ€”เน€๏ฟฝ?๏ฟฝ?เน€๏ฟฝ?๏ฟฝ เน€๏ฟฝ?๏ฟฝ เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝ?เน€๏ฟฝ?เธ‘เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝ
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
		setContentView(R.layout.farm_l3_typeani);
		
		soundMain = MediaPlayer.create(context, R.raw.main);
		soundMain.start();
		soundMain.setLooping(true);
		soundMain.setVolume(30, 30);
	
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		username = myDb.SelectCurrentUser();
	
		//mediaPlayer.start();
		Round = myDb.getNumRound("018", username);
		
					
		myDb.getWritableDatabase();
		myDb.emptyNumberTable();
		
		game018();
		
	}

	void game018(){
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
				Intent intent = new Intent(FrL3Typeani.this,Main.class);
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
				scores = myDb.countScore("018", username, Round);
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
	void stopSound(){

		ImageView instructFing = (ImageView)findViewById(R.id.finger);
		if(RunningCount == true){
			countdownTime.cancel();
			if(instructFing.isEnabled()){
				instructFing.clearAnimation();
			}
		}	

	}
	void checkAnswer(final int RandomNum,final int item){
		
		final Button Answer1 = (Button)findViewById(R.id.picans1);
		final Button Answer2 = (Button)findViewById(R.id.picans2);
		final MediaPlayer soundCorrect = MediaPlayer.create(context, R.raw.crab_sound);
		final MediaPlayer soundWrong = MediaPlayer.create(context, R.raw.wrong_sound2);
		final View imgWrong = (View)findViewById(R.id.showwrong); 
		final View imgCorrect = (View)findViewById(R.id.showcorrect);
		imgWrong.setClickable(false);
		imgCorrect.setClickable(false);
		TextView current = (TextView) findViewById(R.id.currentitem);
		current.setText(item +"/ 10");
		
		final int answer = choice(RandomNum);
		
		
		final MediaPlayer soundAns = MediaPlayer.create(context, R.raw.choose_correct_ans);
		final View helpAnswer = (View)findViewById(R.id.showAnswer);
		final Animation myFadeonceAnimation = AnimationUtils.loadAnimation(FrL3Typeani.this, R.anim.tween_once);
		final Animation myFadeAnimation = AnimationUtils.loadAnimation(FrL3Typeani.this, R.anim.tween);
		final ImageView instructFinger = (ImageView)findViewById(R.id.finger);
		
		
		startTime = (20)*1000;
		countdownTime = new MyCountDown(startTime,1000);
		countdownTime.start();	
		instructPage.start();
		
		if(Round == 1 || (username.equals("Guest") && item == 1)){
        	instructFinger.startAnimation(myFadeonceAnimation);	
        }
		
        		
		instructPage.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundCorrect) {
            	if(Round == 1 || (username.equals("Guest") && item == 1)){
            		helpAnswer.startAnimation(myFadeAnimation);
	            	soundAns.start();
            	}
            }
        });
		
		final float countTime = (float) startTime /1000;
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
			
		Answer1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				if(Round == 1 || (username.equals("Guest") && item == 1)){
					helpAnswer.clearAnimation();
				}
				if(answer == 1){
					imgCorrect.setVisibility(View.VISIBLE);
					stopSound();
					soundCorrect.start();
					myDb.addItemScore("018",username,Round,item,1,(countTime - timeRemain));
					
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					stopSound();
					soundWrong.start();
					myDb.addItemScore("018",username,Round,item,0,(countTime - timeRemain));
				}
				
			}
		});
		Answer2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				if(Round == 1 || (username.equals("Guest") && item == 1)){
					helpAnswer.clearAnimation();
				}
				if(answer == 2){
					imgCorrect.setVisibility(View.VISIBLE);
					stopSound();
					soundCorrect.start();
					myDb.addItemScore("018",username,Round,item,1,(countTime - timeRemain));
					
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					stopSound();
					soundWrong.start();
					myDb.addItemScore("018",username,Round,item,0,(countTime - timeRemain));
				}
			}
		});
		
		soundCorrect.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundCorrect) {
            	imgCorrect.setVisibility(View.INVISIBLE);
            	
            	game018();
            }
        });

		soundWrong.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	imgWrong.setVisibility(View.INVISIBLE);
            	
            	game018();
            }
        });
		
		Button HelpButton = (Button)findViewById(R.id.typeaniHelp);
		HelpButton.setOnClickListener(new View.OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				helpAnswer.startAnimation(myFadeonceAnimation);
			}
		});
		
		Button HomeButton = (Button)findViewById(R.id.typeanibackHome);
		HomeButton.setOnClickListener(new View.OnClickListener() {
		
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopSound();
				Intent in = new Intent(FrL3Typeani.this,Main.class);
				in.putExtra("showPopup", 1);
				startActivity(in);
			}
		});
	}
	
	int choice(int random){
		
		Button ans1 = (Button)findViewById(R.id.picans1);
		Button ans2 = (Button)findViewById(R.id.picans2);
		
		AbsoluteLayout Thislayout=(AbsoluteLayout)findViewById(R.id.typeaniLayout);
		int answer=0;
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(2)+1;
		answer = randomInt;
		//randomInt = randomGenerator.nextInt(2)+1;
    	
	    if(random == 1){
	    	Thislayout.setBackgroundResource(R.drawable.frlv3_bg2);
	    	randomInt = 2; 
	    	instructPage = MediaPlayer.create(context, R.raw.frlv3_water);
	    	if(answer == 1){
	    		ans1.setBackgroundResource(R.drawable.frlv_wani1);
	    	}
	    	else{
	    		ans2.setBackgroundResource(R.drawable.frlv_wani1);
	    	}
			
		}
		else if(random == 2){
			Thislayout.setBackgroundResource(R.drawable.frlv3_bg2);
			randomInt = 2; 
			instructPage = MediaPlayer.create(context, R.raw.frlv3_water);
	    	if(answer == 1){
	    		ans1.setBackgroundResource(R.drawable.frlv_wani2);
	    	}
	    	else{
	    		ans2.setBackgroundResource(R.drawable.frlv_wani2);
	    	}
			
		}
		else if(random == 3){
			Thislayout.setBackgroundResource(R.drawable.frlv3_bg3);
			randomInt = 3; 
			instructPage = MediaPlayer.create(context, R.raw.frlv3_fly);
	    	if(answer == 1){
	    		ans1.setBackgroundResource(R.drawable.frlv_fani2);
	    	}
	    	else{
	    		ans2.setBackgroundResource(R.drawable.frlv_fani2);
	    	}
		}
		else if(random == 4){
			Thislayout.setBackgroundResource(R.drawable.frlv3_bg3);
			randomInt = 3; 
			instructPage = MediaPlayer.create(context, R.raw.frlv3_fly);
	    	if(answer == 1){
	    		ans1.setBackgroundResource(R.drawable.frlv_fani3);
	    	}
	    	else{
	    		ans2.setBackgroundResource(R.drawable.frlv_fani3);
	    	}
			
		}
		else if(random == 5){
			Thislayout.setBackgroundResource(R.drawable.frlv3_bg1);
			randomInt = 1; 
			instructPage = MediaPlayer.create(context, R.raw.frlv3_land);
	    	if(answer == 1){
	    		ans1.setBackgroundResource(R.drawable.frlv_lani6);
	    	}
	    	else{
	    		ans2.setBackgroundResource(R.drawable.frlv_lani6);
	    	}
		}
		else if(random == 6){
			Thislayout.setBackgroundResource(R.drawable.frlv3_bg1);
			randomInt = 1; 
			instructPage = MediaPlayer.create(context, R.raw.frlv3_land);
	    	if(answer == 1){
	    		ans1.setBackgroundResource(R.drawable.frlv_lani1);
	    	}
	    	else{
	    		ans2.setBackgroundResource(R.drawable.frlv_lani1);
	    	}
			
		}
		else if(random == 7){
			Thislayout.setBackgroundResource(R.drawable.frlv3_bg1);
			randomInt = 1; 
			instructPage = MediaPlayer.create(context, R.raw.frlv3_land);
	    	if(answer == 1){
	    		ans1.setBackgroundResource(R.drawable.frlv_lani2);
	    	}
	    	else{
	    		ans2.setBackgroundResource(R.drawable.frlv_lani2);
	    	}
		}
		else if(random == 8){
			Thislayout.setBackgroundResource(R.drawable.frlv3_bg1);
			randomInt = 1; 
			instructPage = MediaPlayer.create(context, R.raw.frlv3_land);
	    	if(answer == 1){
	    		ans1.setBackgroundResource(R.drawable.frlv_lani3);
	    	}
	    	else{
	    		ans2.setBackgroundResource(R.drawable.frlv_lani3);
	    	}
			
		}
		else if(random == 9){
			Thislayout.setBackgroundResource(R.drawable.frlv3_bg1);
			randomInt = 1; 
			instructPage = MediaPlayer.create(context, R.raw.frlv3_land);
	    	if(answer == 1){
	    		ans1.setBackgroundResource(R.drawable.frlv_lani4);
	    	}
	    	else{
	    		ans2.setBackgroundResource(R.drawable.frlv_lani4);
	    	}
		}
		else{
			Thislayout.setBackgroundResource(R.drawable.frlv3_bg1);
			randomInt = 1; 
			instructPage = MediaPlayer.create(context, R.raw.frlv3_land);
	    	if(answer == 1){
	    		ans1.setBackgroundResource(R.drawable.frlv_lani5);
	    	}
	    	else{
	    		ans2.setBackgroundResource(R.drawable.frlv_lani5);
	    	}
		}
	    int randomAns;
	    if(randomInt == 1){
	    	randomAns = randomGenerator.nextInt(6)+1;
	    	if(randomAns < 4){
	    		randomAns +=1;
	    	}
	    	else{
	    		randomAns +=8;
	    	}
	    }
	    else if(randomInt == 2){
	    	randomAns = randomGenerator.nextInt(8)+1;
	    	randomAns +=6;
	    }
	    else{
	    	randomAns = randomGenerator.nextInt(11)+1;
	    }
	    
	    
	    if(answer == 1){
	    	if(randomAns == 1)
	    		ans2.setBackgroundResource(R.drawable.frlv_hani1);
	    	if(randomAns == 2)
	    		ans2.setBackgroundResource(R.drawable.frlv_wani1);
	    	if(randomAns == 3)
	    		ans2.setBackgroundResource(R.drawable.frlv_wani2);
	    	if(randomAns == 4)
	    		ans2.setBackgroundResource(R.drawable.frlv_wani3);
	    	if(randomAns == 5)
	    		ans2.setBackgroundResource(R.drawable.frlv_lani1);
	    	if(randomAns == 6)
	    		ans2.setBackgroundResource(R.drawable.frlv_lani2);
	    	if(randomAns == 7)
	    		ans2.setBackgroundResource(R.drawable.frlv_lani3);
	    	if(randomAns == 8)
	    		ans2.setBackgroundResource(R.drawable.frlv_lani4);
	    	if(randomAns == 9)
	    		ans2.setBackgroundResource(R.drawable.frlv_lani5);
	    	if(randomAns == 10)
	    		ans2.setBackgroundResource(R.drawable.frlv_lani6);
	    	if(randomAns == 11)
	    		ans2.setBackgroundResource(R.drawable.frlv_lani7);
	    	if(randomAns == 12)
	    		ans2.setBackgroundResource(R.drawable.frlv_fani1);
	    	if(randomAns == 13)
	    		ans2.setBackgroundResource(R.drawable.frlv_fani2);
	    	if(randomAns == 14)
	    		ans2.setBackgroundResource(R.drawable.frlv_fani3);
	    }
	    else{
	    	if(randomAns == 1)
	    		ans1.setBackgroundResource(R.drawable.frlv_hani1);
	    	if(randomAns == 2)
	    		ans1.setBackgroundResource(R.drawable.frlv_wani1);
	    	if(randomAns == 3)
	    		ans1.setBackgroundResource(R.drawable.frlv_wani2);
	    	if(randomAns == 4)
	    		ans1.setBackgroundResource(R.drawable.frlv_wani3);
	    	if(randomAns == 5)
	    		ans1.setBackgroundResource(R.drawable.frlv_lani1);
	    	if(randomAns == 6)
	    		ans1.setBackgroundResource(R.drawable.frlv_lani2);
	    	if(randomAns == 7)
	    		ans1.setBackgroundResource(R.drawable.frlv_lani3);
	    	if(randomAns == 8)
	    		ans1.setBackgroundResource(R.drawable.frlv_lani4);
	    	if(randomAns == 9)
	    		ans1.setBackgroundResource(R.drawable.frlv_lani5);
	    	if(randomAns == 10)
	    		ans1.setBackgroundResource(R.drawable.frlv_lani6);
	    	if(randomAns == 11)
	    		ans1.setBackgroundResource(R.drawable.frlv_lani7);
	    	if(randomAns == 12)
	    		ans1.setBackgroundResource(R.drawable.frlv_fani1);
	    	if(randomAns == 13)
	    		ans1.setBackgroundResource(R.drawable.frlv_fani2);
	    	if(randomAns == 14)
	    		ans1.setBackgroundResource(R.drawable.frlv_fani3);
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
				Intent in = new Intent(FrL3Typeani.this,Main.class);
				in.putExtra("showPopup", 1);
				startActivity(in);
				
				
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
				
				Round = myDb.getNumRound("018", username);
				//Round++;
				
				game018();
			}
		});
		
		Button dialogNextBt = (Button)dialog.findViewById(R.id.scorenext);
		dialogNextBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				dialog.dismiss();
				Intent intent = new Intent(FrL3Typeani.this,Main.class);
				intent.putExtra("showPopup", 1);
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
		myDb.addItemScore("018",username,Round,item,0,startTime/1000);
		
		final MediaPlayer soundWrongFin = MediaPlayer.create(context, R.raw.wrong_sound2);
		soundWrongFin.start();
		
		soundWrongFin.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	imgWrongFin.setVisibility(View.INVISIBLE);
            	game018();
            }
        });
		
	}

	public boolean onTouchEvent (MotionEvent event) {
		
		instructPage.start();
		return super.onTouchEvent(event);
	}
	
	protected void onRestart() {
		// TODO Auto-generated method stub
		
		Intent intent = new Intent(FrL3Typeani.this,Main.class);
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
        	stopSound();
        	Intent in = new Intent(FrL3Typeani.this,Main.class);
			in.putExtra("showPopup", 1);
			startActivity(in);  
        	return false;
        }
	    return super.onKeyDown(keyCode, event);
	}
}