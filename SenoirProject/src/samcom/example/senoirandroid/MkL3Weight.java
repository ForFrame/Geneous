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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


@SuppressWarnings("deprecation")
public class MkL3Weight extends Activity {

	String username;
	long startTime;
	final Context context = this;
	int timeRemain;
	int Round;
	int Begin = 1;
	MediaPlayer instructPage;
	
	public class MyCountDown extends CountDownTimer {
		public MyCountDown(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
		// TODO Auto-generated constructor stub
		}
		
		@Override
		public void onFinish() { // เน€๏ฟฝ?๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝ?เน€๏ฟฝ?เธ—เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝ?เน€๏ฟฝ?โ€”เน€๏ฟฝ?เธ“เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?เธ’เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝ?เน€๏ฟฝ?๏ฟฝ?เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝ?เน€๏ฟฝ?เธ”เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝ
		// TODO Auto-generated method stub
			showTimeout();
		}
		
		@Override
		public void onTick(long remain) { // เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝ?เน€๏ฟฝ?๏ฟฝ?เน€๏ฟฝ?โ€”เน€๏ฟฝ?เธ•เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?โ€”เน€๏ฟฝ?เธ“เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?เธ’เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?โ€”เน€๏ฟฝ?๏ฟฝ?เน€๏ฟฝ?๏ฟฝ เน€๏ฟฝ?๏ฟฝ เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝ?เน€๏ฟฝ?เธ‘เน€๏ฟฝ?๏ฟฝเน€๏ฟฝ?๏ฟฝ
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
	setContentView(R.layout.market_l3_plus_scales);
	
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		username = myDb.SelectCurrentUser();
	
		//mediaPlayer.start();
		Round = myDb.getNumRound("009", username);
		
					
		myDb.getWritableDatabase();
		myDb.emptyNumberTable();
		
			if(Round == 1){
					showBeginPopup();
			}
			else{
				game009();
			}
		
	}

	void game009(){
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
				instructPage.stop();
				myUser.showLoginPopup();

			}
		});
		
		Button LogoutButton = (Button)findViewById(R.id.logout);
		LogoutButton.setOnClickListener(new View.OnClickListener() {
			 
			public void onClick(View v) {	
				myDb.logoutUser(username);
				instructPage.stop();
				Intent intent = new Intent(MkL3Weight.this,Main.class);
				intent.putExtra("Logout", 1);
				startActivity(intent);
			}
			
		});
		if(count < 10){
			Random = RanNum();
			checkAnswer(Random,count+1);
		}
		else{
			scores = myDb.countScore("009", username, Round);
			
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
		
		final Button Answer = (Button)findViewById(R.id.AnsBt);
		final EditText AnswerText = (EditText)findViewById(R.id.textAns);
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		final int answer;
		final MediaPlayer soundCorrect = MediaPlayer.create(context, R.raw.crab_sound);
		final MediaPlayer soundWrong = MediaPlayer.create(context, R.raw.wrong_sound2);
		
		startTime = (20)*1000;
		final MyCountDown countdownTime = new MyCountDown(startTime,1000);
		
		final float countTime = (float) startTime /1000;
		final View imgWrong = (View)findViewById(R.id.showwrong); 
		final View imgCorrect = (View)findViewById(R.id.showcorrect);
		
		TextView current = (TextView) findViewById(R.id.currentitem);
		current.setText(item +"/ 10");
		
		countdownTime.start();
		
			answer = choice(RandomNum);
			instructPage.start();
			
				Answer.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						// TODO Auto-generated method stub
						instructPage.stop();
						String answerNumber = AnswerText.getText().toString();
						int ansNum = Integer.parseInt(answerNumber);
						
						if(ansNum == answer){
							imgCorrect.setVisibility(View.VISIBLE);
							countdownTime.cancel();
							soundCorrect.start();
							myDb.addItemScore("009",username,Round,item,1,(countTime - timeRemain));
							
						}
						else{
							imgWrong.setVisibility(View.VISIBLE);
							countdownTime.cancel();
							soundWrong.start();
							myDb.addItemScore("009",username,Round,item,0,(countTime - timeRemain));
						}
						
					}
				});	
		
		final View imgWrongClick = (View)findViewById(R.id.showwrong); 
		final View imgCorrectClick = (View)findViewById(R.id.showcorrect);
		imgWrongClick.setClickable(false);
		imgCorrectClick.setClickable(false);
		
		final Animation myFadeonceAnimation = AnimationUtils.loadAnimation(MkL3Weight.this, R.anim.tween_once);
		final View helpAnswer = (View)findViewById(R.id.showAnswer);
		
		soundCorrect.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundCorrect) {
            	imgCorrect.setVisibility(View.INVISIBLE);
            	
            	game009();
            }
        });

		soundWrong.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	imgWrong.setVisibility(View.INVISIBLE);
            	
            	game009();
            }
        });
		
		Button HelpButton = (Button)findViewById(R.id.plusScalesHelp);
		HelpButton.setOnClickListener(new View.OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//countdownTime.cancel();
				helpAnswer.startAnimation(myFadeonceAnimation);
			}
		});
		
		Button HomeButton = (Button)findViewById(R.id.plusScalesbackHome);
		HomeButton.setOnClickListener(new View.OnClickListener() {
		
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			countdownTime.cancel();
			instructPage.stop();
			Intent intent = new Intent(MkL3Weight.this,MarketLevel2.class);
			startActivity(intent);
		}
		});
	}
	
	int choice(int random){
		
		//Button ans1 = (Button)findViewById(R.id.picans1);
		//Button ans2 = (Button)findViewById(R.id.picans2);
		
		AbsoluteLayout Thislayout=(AbsoluteLayout)findViewById(R.id.plusScalesLayout);
		int answer=0;
	    
	    if(random == 1){
	    	answer = 4;
	    	Thislayout.setBackgroundResource(R.drawable.ml3_bg1);
			instructPage = MediaPlayer.create(context, R.raw.ins_pll2_1);
		}
		else if(random == 2){
			answer = 0;
	    	Thislayout.setBackgroundResource(R.drawable.ml3_bg2);
			instructPage = MediaPlayer.create(context, R.raw.ins_pll2_2);
		}
		else if(random == 3){
			answer = 7;
	    	Thislayout.setBackgroundResource(R.drawable.ml3_bg3);
			instructPage = MediaPlayer.create(context, R.raw.ins_pll2_3);
		}
		else if(random == 4){
			answer = 9;
	    	Thislayout.setBackgroundResource(R.drawable.ml3_bg4);
			instructPage = MediaPlayer.create(context, R.raw.ins_pll2_4);
		}
		else if(random == 5){
			answer = 2;
	    	Thislayout.setBackgroundResource(R.drawable.ml3_bg5);
			instructPage = MediaPlayer.create(context, R.raw.ins_pll2_5);
		}
		else if(random == 6){
			answer = 5;
	    	Thislayout.setBackgroundResource(R.drawable.ml3_bg6);
			instructPage = MediaPlayer.create(context, R.raw.ins_pll2_4);
		}
		else if(random == 7){
			answer = 1;
	    	Thislayout.setBackgroundResource(R.drawable.ml3_bg7);
			instructPage = MediaPlayer.create(context, R.raw.ins_pll2_7);
		}
		else if(random == 8){
			answer = 9;
	    	Thislayout.setBackgroundResource(R.drawable.ml3_bg8);
			instructPage = MediaPlayer.create(context, R.raw.ins_pll2_8);
		}
		else if(random == 9){
			answer = 9;
	    	Thislayout.setBackgroundResource(R.drawable.ml3_bg9);
			instructPage = MediaPlayer.create(context, R.raw.ins_pll2_9);
		}
		else{
			answer = 3;
	    	Thislayout.setBackgroundResource(R.drawable.ml3_bg10);
			instructPage = MediaPlayer.create(context, R.raw.ins_pll2_5);
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
				Intent intent = new Intent(MkL3Weight.this,MarketLevel2.class);
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
				
				Round = myDb.getNumRound("009", username);
				//Round++;
				
				game009();
			}
		});
		
		Button dialogNextBt = (Button)dialog.findViewById(R.id.scorenext);
		dialogNextBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(MkL3Weight.this,MkL3Weight.class);
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
		myDb.addItemScore("009",username,Round,item,0,startTime/1000);
		
		final MediaPlayer soundWrongFin = MediaPlayer.create(context, R.raw.wrong_sound2);
		soundWrongFin.start();
		
		soundWrongFin.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundWrong) {
            	imgWrongFin.setVisibility(View.INVISIBLE);
            	game009();
            }
        });
		
		final View imgWrongClick = (View)findViewById(R.id.showwrong); 
		
		imgWrongClick.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				soundWrongFin.stop();
				imgWrongFin.setVisibility(View.INVISIBLE);
				//Items++;
				game009();
			}
		});
		
	}
	
	protected void showBeginPopup(){
		final Dialog BeginPop = new Dialog(context, R.style.FullHeightDialog);
		final MediaPlayer soundIns;
		final MediaPlayer soundAns;
		BeginPop.setContentView(R.layout.market_l3_plus_scales_tutorial);
		BeginPop.setCanceledOnTouchOutside(false);
		BeginPop.setCancelable(false); 
		
		soundIns = MediaPlayer.create(context, R.raw.ins_pll2_5);
		soundAns = MediaPlayer.create(context, R.raw.choose_correct_ans);
		final Animation myFadeAnimation = AnimationUtils.loadAnimation(MkL3Weight.this, R.anim.tween);
		final ImageView helpAns = (ImageView)BeginPop.findViewById(R.id.showAnswer);
		final ImageView instruct = (ImageView)BeginPop.findViewById(R.id.helpplusScales);
		
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
						//Begin = 2;
						BeginPop.dismiss();
						game009();
					}
				});
			BeginPop.show();
	}
	
	public boolean onTouchEvent (MotionEvent event) {
		
		instructPage.start();
		return super.onTouchEvent(event);
	}
	
	protected void onRestart() {
		// TODO Auto-generated method stub
		
		Intent intent = new Intent(MkL3Weight.this,Main.class);
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
}