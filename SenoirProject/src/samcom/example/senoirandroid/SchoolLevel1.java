package samcom.example.senoirandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class SchoolLevel1 extends Activity {
	
	String CurrentUser;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_school_level1);
		
		
		schoolLevel1();
		
	
	}
	void schoolLevel1(){
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		ImageView imgLogo;  
	    Animation animCalendar;  
	     
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/teddy.ttf"); 
		   
		   
		CurrentUser = myDb.SelectCurrentUser();
		if(!(CurrentUser.equals("Guest"))){
			TextView result = (TextView) findViewById(R.id.textUser);
			result.setTypeface(type);
			//result.setTextAppearance(getApplicationContext(),R.style.AudioFileInfoOverlayText);
			result.setTextColor(Color.rgb(2, 101, 203));
			result.setVisibility(TextView.VISIBLE);
			result.setText(CurrentUser);
			Button LogoutBt = (Button) findViewById(R.id.logout);
			LogoutBt.setVisibility(Button.VISIBLE);
			Button LoginBt = (Button) findViewById(R.id.loginn);
			LoginBt.setVisibility(Button.INVISIBLE);
		}
		
		if((CurrentUser.equals("Guest"))){
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
				//Intent intent = new Intent(SchoolLevel1.this,Main.class);
				//startActivity(intent);
				Intent in = new Intent(getApplicationContext(),Main.class);
				in.putExtra("loginButt", 1);
				startActivity(in);
			}
		});
		
		Button LogoutButton = (Button)findViewById(R.id.logout);
		LogoutButton.setOnClickListener(new View.OnClickListener() {
			 
			public void onClick(View v) {	
				myDb.logoutUser(CurrentUser);
				myDb.ChangeHome(0);
				Intent intent = new Intent(SchoolLevel1.this,Main.class);
				startActivity(intent);
			}
			
		});
		Animation myFadeInAnimation = AnimationUtils.loadAnimation(SchoolLevel1.this, R.anim.tween);
		//Animation myFadeOutAnimation = AnimationUtils.loadAnimation(SchoolLevel1.this, R.anim.tween_reverse);
		Button CountTable = (Button)findViewById(R.id.table);
		 CountTable.startAnimation(myFadeInAnimation);
		CountTable.setOnClickListener(new View.OnClickListener() {
			 

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SchoolLevel1.this,L1ScCount.class);
				startActivity(intent);
			}
		});
		/*Button GameCalendar = (Button)findViewById(R.id.calendar);
		
		//aniMate();
		 
		 //ImageView myImageView = (ImageView) findViewById(R.id.imageView2); 
		 
		 GameCalendar.startAnimation(myFadeInAnimation);
		
		
		GameCalendar.setOnClickListener(new View.OnClickListener() {
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SchoolLevel1.this,L1ScCalendar.class);
				startActivity(intent);
			}
		});
		
		Button GameBoard = (Button)findViewById(R.id.board);
		GameBoard.setOnClickListener(new View.OnClickListener() {
			 

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SchoolLevel1.this,L1ScLongShort.class);
				startActivity(intent);
			}
		});*/
		
		Button backButton = (Button)findViewById(R.id.backToselectSchool);
		backButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent intent = new Intent(SchoolLevel1.this,Main.class);
				Intent intent = new Intent(SchoolLevel1.this,SelectSchoolLevel.class);
				startActivity(intent);
				//finish();
			}
		});
	}
	public void aniMate(){
		final Button GameCalendar = (Button)findViewById(R.id.calendar);
		
		AnimationSet animationSet = new AnimationSet(true);
		
		Animation animation1 = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
		Animation animation2 = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
		
		animation1 = new AlphaAnimation(0.0f, 1.0f);
		animation1.setDuration(1000);
		animation1.setStartOffset(5000);

	    //animation1 AnimationListener
		
	    animation1.setAnimationListener(new AnimationListener(){
	    	Animation animation2 = new AlphaAnimation(1.0f, 0.0f);
	        public void onAnimationEnd(Animation arg0) {
	            // start animation2 when animation1 ends (continue)
	            GameCalendar.startAnimation(animation2);
	        }

	        public void onAnimationRepeat(Animation arg0) {
	            // TODO Auto-generated method stub

	        }

	        public void onAnimationStart(Animation arg0) {
	            // TODO Auto-generated method stub

	        }

	    });

	    animation2 = new AlphaAnimation(1.0f, 0.0f);
	    animation2.setDuration(1000);
	    animation2.setStartOffset(5000);

	    //animation2 AnimationListener
	    animation2.setAnimationListener(new AnimationListener(){
	    	Animation animation1 = new AlphaAnimation(0.0f, 1.0f);
	        public void onAnimationEnd(Animation arg0) {
	            // start animation1 when animation2 ends (repeat)
	            GameCalendar.startAnimation(animation1);
	        }

	        public void onAnimationRepeat(Animation arg0) {
	            // TODO Auto-generated method stub

	        }

	        public void onAnimationStart(Animation arg0) {
	            // TODO Auto-generated method stub

	        }

	    });

	    GameCalendar.startAnimation(animation1);
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		Boolean isThisContinue;
		isThisContinue = myDb.isCurrentContinue();
		myDb.close();
		if(isThisContinue == true){
			schoolLevel1();
		}
		else{
			myDb.getWritableDatabase();
			myDb.ChangeHome(0);
			Intent intent = new Intent(SchoolLevel1.this,Main.class);
			startActivity(intent);
		}
		
		super.onRestart();
	}
}