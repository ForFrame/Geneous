package samcom.example.senoirandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class SelectSchoolLevel extends Activity {

	String CurrentUser;
	Context context = this;
	//MediaPlayer soundPage;
	MediaPlayer instructPage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	setContentView(R.layout.activity_select_level);
	
		//soundPage = MediaPlayer.create(context, R.raw.page);
		instructPage = MediaPlayer.create(context, R.raw.select_level);
		selectLevel();
	
	}
	
	void selectLevel(){
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		final LoginManage myUser = new LoginManage(this);
		
		
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/teddy.ttf"); 
		   
		//soundPage.start();
		instructPage.start();
		
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
				/*myDb.ChangeHome(0);
				instructPage.stop();
				Intent in = new Intent(getApplicationContext(),Main.class);
				in.putExtra("loginButt", 1);
				startActivity(in);*/
				
				myUser.showLoginPopup();
				CurrentUser = myDb.SelectCurrentUser();
				selectLevel();
			}
		});
		
		Button LogoutButton = (Button)findViewById(R.id.logout);
		LogoutButton.setOnClickListener(new View.OnClickListener() {
			 
			public void onClick(View v) {	
				myDb.logoutUser(CurrentUser);
				/*myDb.ChangeHome(0);
				//soundPage.stop();
				instructPage.stop();
				Intent intent = new Intent(SelectSchoolLevel.this,Main.class);
				startActivity(intent);*/
				
				myUser.popUpLogIn();
			}
			
		});

		Button ScLv1 = (Button)findViewById(R.id.level1);
		ScLv1.setOnClickListener(new View.OnClickListener() {
			 

			public void onClick(View v) {
				// TODO Auto-generated method stub
				//soundPage.stop();
				instructPage.stop();
				Intent intent = new Intent(SelectSchoolLevel.this,SchoolLevel1.class);
				startActivity(intent);
			}
		});
		
		
		Button ScLv2 = (Button)findViewById(R.id.level2);
		ScLv2.setOnClickListener(new View.OnClickListener() {
			 

			public void onClick(View v) {
				// TODO Auto-generated method stub
				//soundPage.stop();
				instructPage.stop();
				Intent intent = new Intent(SelectSchoolLevel.this,SchoolLevel2.class);
				startActivity(intent);
			}
		});
		
		Button ScLv3 = (Button)findViewById(R.id.level3);
		ScLv3.setOnClickListener(new View.OnClickListener() {
			 

			public void onClick(View v) {
				// TODO Auto-generated method stub
				//soundPage.stop();
				instructPage.stop();
				Intent intent = new Intent(SelectSchoolLevel.this,SchoolLevel3.class);
				startActivity(intent);
			}
		});
		
		Button countButton = (Button)findViewById(R.id.backToHome);
		countButton.setOnClickListener(new View.OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//soundPage.stop();
				instructPage.stop();
				//Intent intent = new Intent(SelectSchoolLevel.this,Main.class);
				//startActivity(intent);
				Intent in = new Intent(SelectSchoolLevel.this,Main.class);
				in.putExtra("selectLV", 1);
				startActivity(in);
			}
		});

			
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		final myDBClass myDb = new myDBClass(this);
		
			myDb.getWritableDatabase();
			myDb.ChangeHome(0);
			Intent intent = new Intent(SelectSchoolLevel.this,Main.class);
			startActivity(intent);
		
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

	/*@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		myDb.ChangeHome(1);
		
		super.onStop();
	}*/
	
	
	
}