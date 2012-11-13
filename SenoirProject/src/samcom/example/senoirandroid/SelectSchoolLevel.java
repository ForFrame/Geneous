package samcom.example.senoirandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SelectSchoolLevel extends Activity {

	String CurrentUser;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_select_school_level);
	
		
		selectLevel();
	
	}
	
	void selectLevel(){
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		
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
				Intent intent = new Intent(SelectSchoolLevel.this,Main.class);
				startActivity(intent);
			}
		});
		
		Button LogoutButton = (Button)findViewById(R.id.logout);
		LogoutButton.setOnClickListener(new View.OnClickListener() {
			 
			public void onClick(View v) {	
				myDb.logoutUser(CurrentUser);
				myDb.ChangeHome(0);
				Intent intent = new Intent(SelectSchoolLevel.this,Main.class);
				startActivity(intent);
			}
			
		});

		Button ScLv1 = (Button)findViewById(R.id.schoollevel1);
		ScLv1.setOnClickListener(new View.OnClickListener() {
			 

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SelectSchoolLevel.this,SchoolLevel1.class);
				startActivity(intent);
			}
		});
		
		
		Button ScLv2 = (Button)findViewById(R.id.schoollevel2);
		ScLv2.setOnClickListener(new View.OnClickListener() {
			 

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SelectSchoolLevel.this,SchoolLevel2.class);
				startActivity(intent);
			}
		});
		
		Button countButton = (Button)findViewById(R.id.backToHome);
		countButton.setOnClickListener(new View.OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SelectSchoolLevel.this,Main.class);
				startActivity(intent);
			}
		});

			
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		final myDBClass myDb = new myDBClass(this);
		//this comment for ติ๊ก ๆๆๆๆ  continue (1,1) state
		//myDb.getReadableDatabase();
		//
		//Boolean isThisContinue;
		//isThisContinue = myDb.isCurrentContinue();
		//myDb.close();
		//if(isThisContinue == true){
		//	selectLevel();
		//}
		//else{
			myDb.getWritableDatabase();
			myDb.ChangeHome(0);
			Intent intent = new Intent(SelectSchoolLevel.this,Main.class);
			startActivity(intent);
		//}
		
		super.onRestart();
	}
	
	
}