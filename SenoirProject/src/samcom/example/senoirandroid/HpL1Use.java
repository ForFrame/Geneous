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
public class HpL1Use extends Activity {

	String username;
	long startTime;
	final Context context = this;
	int timeRemain;
	boolean firstSound;
	boolean OnPause = false;
	boolean RunningCount = false;
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
		setContentView(R.layout.hospital_l1_use);
		
		soundMain = MediaPlayer.create(context, R.raw.main);
		soundMain.start();
		soundMain.setLooping(true);
		soundMain.setVolume(30, 30);
	
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		username = myDb.SelectCurrentUser();
	
		//mediaPlayer.start();
		Round = myDb.getNumRound("013", username);
		
					
		myDb.getWritableDatabase();
		myDb.emptyNumberTable();
		
		game013();
		
	}

	void game013(){
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
				Intent intent = new Intent(HpL1Use.this,Main.class);
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
				scores = myDb.countScore("013", username, Round);
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
		final Animation myFadeonceAnimation = AnimationUtils.loadAnimation(HpL1Use.this, R.anim.tween_once);
		final Animation myFadeAnimation = AnimationUtils.loadAnimation(HpL1Use.this, R.anim.tween);
		final ImageView instructFinger = (ImageView)findViewById(R.id.finger);
		
		
		if(Round == 1 || (username.equals("Guest") && item == 1)){
			instructPage.start();
			firstSound = true;
		}
		else{
			startTime = (20)*1000;
			countdownTime = new MyCountDown(startTime,1000);
			countdownTime.start();	
			instructPage.start();
		}
		
		instructPage.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundCorrect) {
            	if(Round == 1 || (username.equals("Guest") && item == 1)){
            		if(firstSound == true){
            			instructFinger.startAnimation(myFadeAnimation);
            			firstSound = false;
            		}
            		else{
	            		helpAnswer.startAnimation(myFadeonceAnimation);
	            		startTime = (20)*1000;
	        			countdownTime = new MyCountDown(startTime,1000);
	        			countdownTime.start();
	        			instructFinger.clearAnimation();
	            		soundAns.start();
            		}
            	}
            }
        });
		
		final float countTime = (float) startTime /1000;
		if(firstSound == true){
			timeRemain = (int)countTime;
		}
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
			
		Answer1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				if(answer == 1){
					imgCorrect.setVisibility(View.VISIBLE);
					stopSound();
					soundCorrect.start();
					myDb.addItemScore("013",username,Round,item,1,(countTime - timeRemain));
					
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					stopSound();
					soundWrong.start();
					myDb.addItemScore("013",username,Round,item,0,(countTime - timeRemain));
				}
				
			}
		});
		Answer2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				if(answer == 2){
					imgCorrect.setVisibility(View.VISIBLE);
					stopSound();
					soundCorrect.start();
					myDb.addItemScore("013",username,Round,item,1,(countTime - timeRemain));
					
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					stopSound();
					soundWrong.start();
					myDb.addItemScore("013",username,Round,item,0,(countTime - timeRemain));
				}
			}
		});
		
		soundCorrect.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundCorrect) {
            	imgCorrect.setVisibility(View.INVISIBLE);
            	
            	game013();
            }
        });

		soundWrong.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	imgWrong.setVisibility(View.INVISIBLE);
            	
            	game013();
            }
        });
		
		Button HelpButton = (Button)findViewById(R.id.useHelp);
		HelpButton.setOnClickListener(new View.OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				helpAnswer.startAnimation(myFadeonceAnimation);
			}
		});
		
		Button HomeButton = (Button)findViewById(R.id.usebackHome);
		HomeButton.setOnClickListener(new View.OnClickListener() {
		
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopSound();
				Intent in = new Intent(HpL1Use.this,Main.class);
				in.putExtra("showPopup", 1);
				startActivity(in);
			}
		});
	}
	
	int choice(int random){
		
		Button ans1 = (Button)findViewById(R.id.picans1);
		Button ans2 = (Button)findViewById(R.id.picans2);
		
		AbsoluteLayout Thislayout=(AbsoluteLayout)findViewById(R.id.useLayout);
		int answer=0;
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(2)+1;
		answer = randomInt;
	    
	    if(random == 1){
	    	if(answer == 1){
	    		Thislayout.setBackgroundResource(R.drawable.hpl1_bg1);
	    		instructPage = MediaPlayer.create(context, R.raw.hs_ins1_1);
	    	}
	    	else{
	    		Thislayout.setBackgroundResource(R.drawable.hpl1_bg2);
	    		instructPage = MediaPlayer.create(context, R.raw.hs_ins1_3);
	    	}
			ans1.setBackgroundResource(R.drawable.hpl1_ans1_1);
			ans2.setBackgroundResource(R.drawable.hpl1_ans1_2);
			
		}
		else if(random == 2){
			if(answer == 1){
	    		Thislayout.setBackgroundResource(R.drawable.hpl1_bg5);
	    		instructPage = MediaPlayer.create(context, R.raw.hs_ins1_1);
			}
	    	else{
	    		Thislayout.setBackgroundResource(R.drawable.hpl1_bg1);
	    		instructPage = MediaPlayer.create(context, R.raw.hs_ins1_2);
	    	}
			ans1.setBackgroundResource(R.drawable.hpl1_ans2_1);
			ans2.setBackgroundResource(R.drawable.hpl1_ans2_2);
			
		}
		else if(random == 3){
			if(answer == 1){
	    		Thislayout.setBackgroundResource(R.drawable.hpl1_bg2);
	    		instructPage = MediaPlayer.create(context, R.raw.hs_ins1_2);
			}
	    	else{
	    		Thislayout.setBackgroundResource(R.drawable.hpl1_bg1);
	    		instructPage = MediaPlayer.create(context, R.raw.hs_ins1_1);
	    	}
			ans1.setBackgroundResource(R.drawable.hpl1_ans3_1);
			ans2.setBackgroundResource(R.drawable.hpl1_ans3_2);
			
		}
		else if(random == 4){
			if(answer == 1){
	    		Thislayout.setBackgroundResource(R.drawable.hpl1_bg4);
	    		instructPage = MediaPlayer.create(context, R.raw.hs_ins1_2);
			}
	    	else{
	    		Thislayout.setBackgroundResource(R.drawable.hpl1_bg2);
	    		instructPage = MediaPlayer.create(context, R.raw.hs_ins1_3);
	    	}
			ans1.setBackgroundResource(R.drawable.hpl1_ans4_1);
			ans2.setBackgroundResource(R.drawable.hpl1_ans4_2);
			
		}
		else if(random == 5){
			if(answer == 1){
	    		Thislayout.setBackgroundResource(R.drawable.hpl1_bg1);
	    		instructPage = MediaPlayer.create(context, R.raw.hs_ins1_3);
			}
	    	else{
	    		Thislayout.setBackgroundResource(R.drawable.hpl1_bg3);
	    		instructPage = MediaPlayer.create(context, R.raw.hs_ins1_2);
	    	}
			ans1.setBackgroundResource(R.drawable.hpl1_ans5_1);
			ans2.setBackgroundResource(R.drawable.hpl1_ans5_2);
			
		}
		else if(random == 6){
			if(answer == 1){
	    		Thislayout.setBackgroundResource(R.drawable.hpl1_bg3);
	    		instructPage = MediaPlayer.create(context, R.raw.hs_ins1_1);
			}
	    	else{
	    		Thislayout.setBackgroundResource(R.drawable.hpl1_bg1);
	    		instructPage = MediaPlayer.create(context, R.raw.hs_ins1_2);
	    	}
			ans1.setBackgroundResource(R.drawable.hpl1_ans6_1);
			ans2.setBackgroundResource(R.drawable.hpl1_ans6_2);
			
		}
		else if(random == 7){
			if(answer == 1){
	    		Thislayout.setBackgroundResource(R.drawable.hpl1_bg4);
	    		instructPage = MediaPlayer.create(context, R.raw.hs_ins1_3);
			}
	    	else{
	    		Thislayout.setBackgroundResource(R.drawable.hpl1_bg5);
	    		instructPage = MediaPlayer.create(context, R.raw.hs_ins1_1);
	    	}
			ans1.setBackgroundResource(R.drawable.hpl1_ans7_1);
			ans2.setBackgroundResource(R.drawable.hpl1_ans7_2);
			
		}
		else if(random == 8){
			if(answer == 1){
	    		Thislayout.setBackgroundResource(R.drawable.hpl1_bg2);
	    		instructPage = MediaPlayer.create(context, R.raw.hs_ins1_2);
			}
	    	else{
	    		Thislayout.setBackgroundResource(R.drawable.hpl1_bg4);
	    		instructPage = MediaPlayer.create(context, R.raw.hs_ins1_1);
	    	}
			ans1.setBackgroundResource(R.drawable.hpl1_ans8_1);
			ans2.setBackgroundResource(R.drawable.hpl1_ans8_2);
			
		}
		else if(random == 9){
			if(answer == 1){
	    		Thislayout.setBackgroundResource(R.drawable.hpl1_bg5);
	    		instructPage = MediaPlayer.create(context, R.raw.hs_ins1_1);
			}
	    	else{
	    		Thislayout.setBackgroundResource(R.drawable.hpl1_bg3);
	    		instructPage = MediaPlayer.create(context, R.raw.hs_ins1_2);
	    	}
			ans1.setBackgroundResource(R.drawable.hpl1_ans9_1);
			ans2.setBackgroundResource(R.drawable.hpl1_ans9_2);
			
		}
		else{
			if(answer == 1){
	    		Thislayout.setBackgroundResource(R.drawable.hpl1_bg4);
	    		instructPage = MediaPlayer.create(context, R.raw.hs_ins1_1);
			}
	    	else{
	    		Thislayout.setBackgroundResource(R.drawable.hpl1_bg5);
	    		instructPage = MediaPlayer.create(context, R.raw.hs_ins1_2);
	    	}
			ans1.setBackgroundResource(R.drawable.hpl1_ans10_1);
			ans2.setBackgroundResource(R.drawable.hpl1_ans10_2);
			
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
				Intent in = new Intent(HpL1Use.this,Main.class);
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
				
				Round = myDb.getNumRound("013", username);
				//Round++;
				
				game013();
			}
		});
		
		Button dialogNextBt = (Button)dialog.findViewById(R.id.scorenext);
		dialogNextBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(HpL1Use.this,HpL2Body.class);
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
		myDb.addItemScore("013",username,Round,item,0,startTime/1000);
		
		final MediaPlayer soundWrongFin = MediaPlayer.create(context, R.raw.wrong_sound2);
		soundWrongFin.start();
		
		soundWrongFin.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	imgWrongFin.setVisibility(View.INVISIBLE);
            	game013();
            }
        });
		
	}

	public boolean onTouchEvent (MotionEvent event) {
		
		instructPage.start();
		return super.onTouchEvent(event);
	}
	
	protected void onRestart() {
		// TODO Auto-generated method stub
		
		Intent intent = new Intent(HpL1Use.this,Main.class);
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
        	Intent in = new Intent(HpL1Use.this,Main.class);
			in.putExtra("showPopup", 1);
			startActivity(in);  
        	return false;
        }
	    return super.onKeyDown(keyCode, event);
	}
}