package samcom.example.senoirandroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class SchoolLevel1 extends Activity {
	
	String CurrentUser;
	final Context context = this;
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
		
		Button getHighScore = (Button)findViewById(R.id.showGameHighScore);
		getHighScore.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				showListViewHighScore();
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
	
	protected void showListViewHighScore(){
		final Dialog HighPop = new Dialog(context, R.style.FullHeightDialog);
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		HighPop.setContentView(R.layout.activity_highscore);
		HighPop.setCanceledOnTouchOutside(false);
		HighPop.setCancelable(false); 
		
        ListView lv = (ListView)HighPop.findViewById(R.id.listview);

        // create the grid item mapping
        //String[] from = new String[] {"rowid", "col_1", "col_2", "col_3"};
        int[] to = new int[] { R.id.item1, R.id.item2, R.id.item3, R.id.item4 };
        String[] title = new String[] {"rowid", "col_1", "col_2", "col_3"};
        String from[][] = new String[15][5];
        int lengths;
        lengths = myDb.getGameHighScore("001",from);
        //int lengths = from.length;
        // prepare the list of all records
        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
        for(int i = 0; i < lengths; i++){
        	HashMap<String, String> map = new HashMap<String, String>();
        	map.put("rowid", "" + (i+1));
        	map.put("col_1", from[i][0]);
        	map.put("col_2", from[i][1]);
        	map.put("col_3", from[i][2]);
        	fillMaps.add(map);
        }

        // fill in the grid_item layout
        SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.grid_item, title, to);
        lv.setAdapter(adapter);
        
        Button skipButton = (Button)HighPop.findViewById(R.id.button1);
		skipButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				HighPop.dismiss();
				schoolLevel1();
			}
		});
        
        HighPop.show();

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
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		myDb.ChangeHome(1);
		
		super.onDestroy();
	}
}