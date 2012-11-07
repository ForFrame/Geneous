package samcom.example.senoirandroid;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;

import java.util.Date;

public class Main extends Activity {
String CurrentUser;
int chooseSex = 0;
final Context context2 = this;



SQLiteDatabase db;

/** Called when the activity is first created. */
		

	@Override
 	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		//final myDBClass myDb = new myDBClass(this);
		//myDb.getWritableDatabase();
		
		// SelectCurrentUser(); check MAX(No)loginStatus table on Status == 'Logout'-> No or 'Y' -> name 
		mainPage();
	}
	
	void mainPage(){
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/teddy.ttf"); 
		   
		   
		CurrentUser = myDb.SelectCurrentUser();
		myDb.close();
		if(CurrentUser.equals("Logout")){
			popUpLogIn();
		}
		
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
		
		Button loginBut = (Button)findViewById(R.id.loginn);
		loginBut.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showLoginPopup();
			}
		});
		
		Button swapPoliceButton1 = (Button)findViewById(R.id.maintopolice1);
	
		swapPoliceButton1.setOnClickListener(new View.OnClickListener() {
			 
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Main.this,PoliceLevel1.class);
				startActivity(intent);
			}
		});
	
		Button swapMarketButton1 = (Button)findViewById(R.id.maintomarket1);
	
		swapMarketButton1.setOnClickListener(new View.OnClickListener() {
		 
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Main.this,MarketLevel1.class);
				startActivity(intent);
			}
		});
	
		Button swapHouseButton1 = (Button)findViewById(R.id.maintohouse1);
	
		swapHouseButton1.setOnClickListener(new View.OnClickListener() {
			 
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Main.this,HouseLevel1.class);
				startActivity(intent);
			}
		});
	
		Button swapSchoolButton = (Button)findViewById(R.id.maintoschool);
	
		swapSchoolButton.setOnClickListener(new View.OnClickListener() {
			 
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Main.this,SelectSchoolLevel.class);
				startActivity(intent);
			}
		});
	
		Button swapFarmButton1 = (Button)findViewById(R.id.maintofarm1);
	
		swapFarmButton1.setOnClickListener(new View.OnClickListener() {
			 
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Main.this,FarmLevel1.class);
				startActivity(intent);
			}
		});
	
	
		Button swapHospitalButton1 = (Button)findViewById(R.id.maintohospital1);
	
		swapHospitalButton1.setOnClickListener(new View.OnClickListener() {
			 
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Main.this,HospitalLevel1.class);
				startActivity(intent);
			}
		});
		
		Button LogoutButton = (Button)findViewById(R.id.logout);
		
		LogoutButton.setOnClickListener(new View.OnClickListener() {
			 
			public void onClick(View v) {
								
				myDb.logoutUser(CurrentUser);
				popUpLogIn();
			}
			
		});
	}

	void showLoginPopup(){
	
		final Dialog LoginPop = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
				
		LoginPop.setContentView(R.layout.activity_popup_login);
		
		
		
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
				
		
		
		Button LoginnBt = (Button)LoginPop.findViewById(R.id.LoginBt);
		LoginnBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final boolean checkUser;
				final String username;
				final Date d = new Date();
				int continueLoginState = 0;
				Typeface type = Typeface.createFromAsset(getAssets(),"fonts/teddy.ttf"); 
				EditText user = (EditText)LoginPop.findViewById(R.id.usertext);
				username = user.getText().toString();
				
				CheckBox checkbox = (CheckBox)LoginPop.findViewById(R.id.checkContinueLogin);
				//checkbox.setTypeface(type);
				if(checkbox.isChecked())
				{
					continueLoginState = 1;

				}
				//check user info if got -> insert status table(name,date) ,no -> message Toast 
				checkUser = myDb.checkUserInfo(username);
				myDb.close();
				if(checkUser == true){
					LoginPop.dismiss();
					CurrentUser = username;
					
					myDb.InsertCurrent(CurrentUser,d,continueLoginState);
					if(!(CurrentUser.equals("Guest"))){
							TextView result = (TextView) findViewById(R.id.textUser);
							result.setTypeface(type); 
							//result.setTextColor(Color.WHITE);
							result.setTextColor(Color.rgb(2, 101, 203));
							//result.setTextAppearance(getApplicationContext(),R.style.AudioFileInfoOverlayText);
							result.setVisibility(TextView.VISIBLE);
							result.setText(CurrentUser);
							Button LogoutBt = (Button) findViewById(R.id.logout);
							LogoutBt.setVisibility(Button.VISIBLE);
							Button LoginBt = (Button) findViewById(R.id.loginn);
							LoginBt.setVisibility(Button.INVISIBLE);
					}
				}
				else{
					//String strTxt = editT1.getText().toString();              
					Toast.makeText(Main.this, username + " : not in user info", Toast.LENGTH_LONG).show(); 
				}
				
				//CurrentUser = user.getText().toString();
				
			}
		});
		
		Button RegissBt = (Button)LoginPop.findViewById(R.id.regisBt);
		RegissBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
			String name;	
				EditText user = (EditText)LoginPop.findViewById(R.id.usertext);
				name = user.getText().toString();
				
				//
				LoginPop.dismiss();
				showRegisPopup(name);
				
				//CurrentUser = user.getText().toString();
				
			}
		});
		
		Button cancellogin = (Button)LoginPop.findViewById(R.id.CancelLog);
		cancellogin.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LoginPop.dismiss();
				popUpLogIn();
			}
		});
			
		LoginPop.show();
	}
	void showRegisPopup(String inputname){
		
		final Dialog RegisPop = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
		RegisPop.setContentView(R.layout.activity_popup_regis);
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/touchy_boy.ttf"); 
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
				
		EditText user = (EditText)RegisPop.findViewById(R.id.regUsertext);
		//user.setTypeface(type);
		user.setText(inputname);
		
		//Regis Button
		Button RegissBt = (Button)RegisPop.findViewById(R.id.regisBt);
		RegissBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final boolean checkUser;
				final String selectedAge;
				final String username;
				final Date d = new Date();
				int continueLoginState = 0,lenghtName;
				Typeface type = Typeface.createFromAsset(getAssets(),"fonts/teddy.ttf"); 
				 
				//Username
				EditText user = (EditText)RegisPop.findViewById(R.id.regUsertext);
				//user.setTypeface(type);
				username = user.getText().toString();
				
				
				//Choose sex (boy/girl)
				
				RadioGroup radioSexGroup = (RadioGroup)RegisPop.findViewById(R.id.radioSex);
						 
				int selectedId = radioSexGroup.getCheckedRadioButtonId();
				RadioButton radioSexButton = (RadioButton)RegisPop.findViewById(selectedId);
				//radioSexButton.setTypeface(type);		
				if(radioSexButton.equals("radioMale")){
					chooseSex = 1;
				}
				else{
					chooseSex = 0;
				}
				
			   	
				
				//Select age (2-6 year)	
				Spinner spin1 = (Spinner)RegisPop.findViewById(R.id.ageSelection);
				
					selectedAge = String.valueOf(spin1.getSelectedItem());
					String ages[] = selectedAge.split(" ");
					int age = Integer.parseInt(ages[0]);

				//Continue Login
				CheckBox checkboxx = (CheckBox)RegisPop.findViewById(R.id.checkContinueLogin);
				//checkboxx.setTypeface(type);
				if(checkboxx.isChecked())
				{
					continueLoginState = 1;

				}
			
				lenghtName = username.length();
				if((lenghtName<1)){
					Toast.makeText(Main.this, username + " : username must > 1", Toast.LENGTH_LONG).show();
				}
				else if((lenghtName>10)){
					Toast.makeText(Main.this, username + " : username must < 10", Toast.LENGTH_LONG).show();
				}
				else{
					checkUser = myDb.checkUserInfo(username);
					if(checkUser == true){
						Toast.makeText(Main.this, username + " : same as in user info", Toast.LENGTH_LONG).show(); 
						
					}
					else{
						//check user info if got -> already , not -> insert new user
						RegisPop.dismiss();
						CurrentUser = username;
						
						myDb.InsertUser(CurrentUser,age,chooseSex);
						myDb.InsertCurrent(CurrentUser,d,continueLoginState);
						myDb.close();
						if(!(CurrentUser.equals("Guest"))){
							TextView result = (TextView) findViewById(R.id.textUser);
							result.setTypeface(type);
							//result.setTextColor(Color.WHITE);
							result.setTextColor(Color.rgb(2, 101, 203));
							//result.setTextAppearance(getApplicationContext(),R.style.AudioFileInfoOverlayText);
							result.setVisibility(TextView.VISIBLE);
							result.setText(CurrentUser);
							Button LogoutBt = (Button) findViewById(R.id.logout);
							LogoutBt.setVisibility(Button.VISIBLE);
							Button LoginBt = (Button) findViewById(R.id.loginn);
							LoginBt.setVisibility(Button.INVISIBLE);
						}
					}
				}	
				
			}
		});
		
		Button cancelregis = (Button)RegisPop.findViewById(R.id.CancelReg);
		cancelregis.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RegisPop.dismiss();
				showLoginPopup();
			}
		});
		
		RegisPop.show();
	}
	//Play popup
	void popUpLogIn(){
		final Dialog popLog =  new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
		popLog.setContentView(R.layout.activity_popup_play);
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
			
		Button PlayyBt = (Button)popLog.findViewById(R.id.playBt);
		PlayyBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Date d = new Date();
				String username = null;
				popLog.dismiss();
				
				
				myDb.InsertCurrent("Guest",d,0);
				myDb.close();
				myDb.getReadableDatabase();
				username = myDb.SelectCurrentUser();
				myDb.close();
				Toast.makeText(Main.this, username + " : username ", Toast.LENGTH_LONG).show();
				TextView result = (TextView) findViewById(R.id.textUser);
				result.setVisibility(TextView.INVISIBLE);
				Button LogoutBt = (Button) findViewById(R.id.logout);
				LogoutBt.setVisibility(Button.INVISIBLE);
				Button LoginBt = (Button) findViewById(R.id.loginn);
				LoginBt.setVisibility(Button.VISIBLE);
			}
		});
		
		Button LoginnBt = (Button)popLog.findViewById(R.id.LogRegisBt);
		LoginnBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popLog.dismiss();
				showLoginPopup();
				
			}
		});
	
		popLog.show();
		
		
	}
}

