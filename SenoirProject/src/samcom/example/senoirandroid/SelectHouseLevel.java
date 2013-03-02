package samcom.example.senoirandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class SelectHouseLevel extends Activity {

	String CurrentUser;
	Context context = this;
	MediaPlayer instructPage,soundMain;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_select_level);
		
		soundMain = MediaPlayer.create(context, R.raw.main);
		soundMain.start();
		soundMain.setLooping(true);
		soundMain.setVolume(30, 30);
	
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
				Intent intent = new Intent(SelectHouseLevel.this,Main.class);
				intent.putExtra("Logout", 1);
				startActivity(intent);
			}
			
		});

		Button Lv1 = (Button)findViewById(R.id.level1);
		Lv1.setOnClickListener(new View.OnClickListener() {
			 

			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				Intent intent = new Intent(SelectHouseLevel.this,HouseLevel1.class);
				startActivity(intent);
			}
		});
		
		
		Button Lv2 = (Button)findViewById(R.id.level2);
		Lv2.setOnClickListener(new View.OnClickListener() {
			 

			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				Intent intent = new Intent(SelectHouseLevel.this,HouseLevel2.class);
				startActivity(intent);
			}
		});
		
		Button Lv3 = (Button)findViewById(R.id.level3);
		Lv3.setOnClickListener(new View.OnClickListener() {
			 

			public void onClick(View v) {
				// TODO Auto-generated method stub
				instructPage.stop();
				Intent intent = new Intent(SelectHouseLevel.this,HouseLevel3.class);
				startActivity(intent);
			}
		});
		
		Button HomeButton = (Button)findViewById(R.id.backToHome);
		HomeButton.setOnClickListener(new View.OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SelectHouseLevel.this,Main.class);
				intent.putExtra("showPopup", 1);
				startActivity(intent);
			}
		});

			
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(SelectHouseLevel.this,Main.class);
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
        	Intent intent = new Intent(SelectHouseLevel.this,Main.class);
			intent.putExtra("showPopup", 1);
			startActivity(intent);   
        	return false;
        }
	    return super.onKeyDown(keyCode, event);
	}
}