package samcom.example.senoirandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SelectSchoolLevel extends Activity implements OnGestureListener {

	String CurrentUser;
	Context context = this;
	MediaPlayer instructPage;
	
	GestureDetector gd;
	private static final int SWIPE_MIN_DISTANCE = 120;
	private static final int SWIPE_MAX_OFF_PATH = 250;
	private static final int SWIPE_THRESHOLD_VELOCITY = 100;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	setContentView(R.layout.activity_select_level);
		gd = new GestureDetector(this, this);
		instructPage = MediaPlayer.create(context, R.raw.select_level);
		selectLevel();
	
	}	
	
	void selectLevel(){
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		final LoginManage myUser = new LoginManage(this);
		
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/teddy.ttf"); 
		   
		instructPage.start();
		
		CurrentUser = myDb.SelectCurrentUser();
		if(!(CurrentUser.equals("Guest"))){
			TextView result = (TextView) findViewById(R.id.textUser);
			result.setTypeface(type);
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
				instructPage.stop();
				myUser.showLoginPopup();
			
			}
		});
		
		Button LogoutButton = (Button)findViewById(R.id.logout);
		LogoutButton.setOnClickListener(new View.OnClickListener() {
			 
			public void onClick(View v) {	
				myDb.logoutUser(CurrentUser);
				instructPage.stop();
				Intent intent = new Intent(SelectSchoolLevel.this,Main.class);
				intent.putExtra("Logout", 1);
				startActivity(intent);
				
			}
			
		});

		Button ScLv1 = (Button)findViewById(R.id.level1);
		ScLv1.setOnClickListener(new View.OnClickListener() {
			 

			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				Intent intent = new Intent(SelectSchoolLevel.this,SchoolLevel1.class);
				startActivity(intent);
			}
		});
		
		
		Button ScLv2 = (Button)findViewById(R.id.level2);
		ScLv2.setOnClickListener(new View.OnClickListener() {
			 

			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				Intent intent = new Intent(SelectSchoolLevel.this,SchoolLevel2.class);
				startActivity(intent);
			}
		});
		
		Button ScLv3 = (Button)findViewById(R.id.level3);
		ScLv3.setOnClickListener(new View.OnClickListener() {
			 

			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				Intent intent = new Intent(SelectSchoolLevel.this,SchoolLevel3.class);
				startActivity(intent);
			}
		});
		
		Button countButton = (Button)findViewById(R.id.backToHome);
		countButton.setOnClickListener(new View.OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				Intent in = new Intent(SelectSchoolLevel.this,Main.class);
				in.putExtra("showPopup", 1);
				startActivity(in);
			}
		});

			
	}
	
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			if (gd.onTouchEvent(event))
				return true;
			else
				return false;
		}
	
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,	float velocityY) {
		// TODO Auto-generated method stub
	
			try {
				if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
					return false;
				// right to left swipe
				if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
			
					Toast.makeText(SelectSchoolLevel.this, "<---- Left Swipe", Toast.LENGTH_SHORT).show();
					instructPage.stop();
					Intent in = new Intent(SelectSchoolLevel.this,SchoolLevel1.class);
					startActivity(in);
					
				} 
				else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
			
					Toast.makeText(SelectSchoolLevel.this, "----> Right Swipe", Toast.LENGTH_SHORT).show();
					instructPage.stop();
					Intent in = new Intent(SelectSchoolLevel.this,Main.class);
					in.putExtra("showPopup", 1);
					startActivity(in);
				}
				else if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
			
					Toast.makeText(SelectSchoolLevel.this, "Swipe up", Toast.LENGTH_SHORT).show();
			
				}  
				else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
			
					Toast.makeText(SelectSchoolLevel.this, "Swipe down", Toast.LENGTH_SHORT).show();
			
				}
				} catch (Exception e) {
				// nothing
			}
			return true;
		}
	
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			//Toast.makeText(MainActivity.this, "onDown", Toast.LENGTH_SHORT).show();
			return false;
		}

		public void onLongPress(MotionEvent e) {
			// TODO Auto-generated method stub
			Toast.makeText(SelectSchoolLevel.this, "onLongPress", Toast.LENGTH_SHORT).show();
		}

		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
			// TODO Auto-generated method stub
			//Toast.makeText(MainActivity.this, "onScroll", Toast.LENGTH_SHORT).show();
			return false;
		}

		public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		//Toast.makeText(MainActivity.this, "onShowPress", Toast.LENGTH_SHORT).show();
		}

		public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		//Toast.makeText(MainActivity.this, "onSingleTapUp", Toast.LENGTH_SHORT).show();
			return false;
		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			getMenuInflater().inflate(R.menu.activity_main, menu);
			return true;
		}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(SelectSchoolLevel.this,Main.class);
		startActivity(intent);
		
		super.onRestart();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		
		CurrentUser = myDb.SelectCurrentUser();
		
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/teddy.ttf");
		TextView result = (TextView) findViewById(R.id.textUser);
		result.setTypeface(type);
		result.setTextColor(Color.rgb(2, 101, 203));
		Button LogoutBt = (Button) findViewById(R.id.logout);
		Button LoginBt = (Button) findViewById(R.id.loginn);
		
		if(!(CurrentUser.equals("Guest"))){
			result.setVisibility(TextView.VISIBLE);
			result.setText(CurrentUser);
			LogoutBt.setVisibility(Button.VISIBLE);
			LoginBt.setVisibility(Button.INVISIBLE);
		}
		if((CurrentUser.equals("Guest"))){
			result.setVisibility(TextView.INVISIBLE);
			LogoutBt.setVisibility(Button.INVISIBLE);
			LoginBt.setVisibility(Button.VISIBLE);
		}
		instructPage.start();
		super.onWindowFocusChanged(hasFocus);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		//stop sound
		
		super.onDestroy();
	}
	
	
}