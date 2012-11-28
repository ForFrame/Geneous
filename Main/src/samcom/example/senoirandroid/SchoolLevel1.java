package samcom.example.senoirandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class SchoolLevel1 extends Activity {
	
	String CurrentUser;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_school_level1);
	
		
		schoolLevel1();
		
	
	}
	void schoolLevel1(){
		
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
		
		Button CountTable = (Button)findViewById(R.id.table);
		CountTable.setOnClickListener(new View.OnClickListener() {
			 

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SchoolLevel1.this,L1ScCount.class);
				startActivity(intent);
			}
		});
		
		
		Button GameCalendar = (Button)findViewById(R.id.calendar);
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
				Intent intent = new Intent(SchoolLevel1.this,L1ScBoard.class);
				startActivity(intent);
			}
		});
		
		Button backButton = (Button)findViewById(R.id.backToselectSchool);
		backButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SchoolLevel1.this,Main.class);
				startActivity(intent);
				//finish();
			}
		});
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