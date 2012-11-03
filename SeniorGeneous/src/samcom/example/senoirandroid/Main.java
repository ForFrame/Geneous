package samcom.example.senoirandroid;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

public class Main extends Activity {
String CurrentUser;
final Context context2 = this;


SQLiteDatabase db;

/** Called when the activity is first created. */
		
	@Override
 	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		
		// SelectCurrentUser(); check MAX(No)loginStatus table on Status == 'Logout'-> No or 'Y' -> name 
		CurrentUser = myDb.SelectCurrentUser();
		if(CurrentUser.equals("Logout")){
			popUpLogIn();
		}
		
		if(!(CurrentUser.equals("Guest"))){
			TextView result = (TextView) findViewById(R.id.textUser);
			result.setVisibility(TextView.VISIBLE);
			result.setText(CurrentUser);
			Button LogoutBt = (Button) findViewById(R.id.logout);
			LogoutBt.setVisibility(Button.VISIBLE);
		}
		
		
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
		final Dialog LoginPop = new Dialog(context2);
		LoginPop.setContentView(R.layout.activity_popup_login);
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
				
		
		
		Button LoginBt = (Button)LoginPop.findViewById(R.id.LoginBt);
		LoginBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final boolean checkUser;
				final String username;
				final Date d = new Date();
				boolean continueLoginState = false;
				
				EditText user = (EditText)LoginPop.findViewById(R.id.usertext);
				username = user.getText().toString();
				
				CheckBox checkbox = (CheckBox)LoginPop.findViewById(R.id.checkContinueLogin);
				if(checkbox.isChecked())
				{
					continueLoginState = true;

				}
				//check user info if got -> insert status table(name,date) ,no -> message Toast 
				checkUser = myDb.checkUserInfo(username);
				if(checkUser == true){
					LoginPop.dismiss();
					CurrentUser = username;
					
					myDb.InsertCurrent(CurrentUser,d,continueLoginState);
					if(!(CurrentUser.equals("Guest"))){
							TextView result = (TextView) findViewById(R.id.textUser);
							result.setVisibility(TextView.VISIBLE);
							result.setText(CurrentUser);
							Button LogoutBt = (Button) findViewById(R.id.logout);
							LogoutBt.setVisibility(Button.VISIBLE);
					}
				}
				else{
					//String strTxt = editT1.getText().toString();              
					Toast.makeText(Main.this, username + " : not in user info", Toast.LENGTH_LONG).show(); 
				}
				
				//CurrentUser = user.getText().toString();
				
			}
		});
		
		Button RegisBt = (Button)LoginPop.findViewById(R.id.regisBt);
		LoginBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
			String name;	
				EditText user = (EditText)LoginPop.findViewById(R.id.usertext);
				name = user.getText().toString();
				
				LoginPop.dismiss();
				showRegisPopup(name);				
				//CurrentUser = user.getText().toString();
				
			}
		});
		
			
		LoginPop.show();
	}
	void showRegisPopup(String inputname){
		
		final Dialog RegisPop = new Dialog(context2);
		RegisPop.setContentView(R.layout.activity_popup_regis);
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
				
		EditText user = (EditText)RegisPop.findViewById(R.id.regUsertext);
		user.setText(inputname);
		
		//Regis Button
		Button RegisBt = (Button)RegisPop.findViewById(R.id.regisBt);
		RegisBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final boolean checkUser;
				char chooseSex = 0;
				final String selectedAge;
				final String username;
				final Date d = new Date();
				boolean continueLoginState = false;
				
				//Username
				EditText user = (EditText)RegisPop.findViewById(R.id.regUsertext);
				username = user.getText().toString();
				
				//Choose sex (boy/girl)
				RadioButton chooseBoy = (RadioButton)RegisPop.findViewById(R.id.radioBoy);
				if(chooseBoy.isChecked()){
					chooseSex = 'B';
				}
				RadioButton chooseGirl = (RadioButton)RegisPop.findViewById(R.id.radioGirl);
				if(chooseBoy.isChecked()){
					chooseSex = 'G';
				}
				
				//Select age (2-6 year)	
				Spinner spin1 = (Spinner)RegisPop.findViewById(R.id.ageSelection);
					selectedAge = String.valueOf(spin1.getSelectedItem());
					String ages[] = selectedAge.split(" ");
					int age = Integer.parseInt(ages[0]);

				//Continue Login
				CheckBox checkbox = (CheckBox)RegisPop.findViewById(R.id.checkContinueLogin);
				if(checkbox.isChecked())
				{
					continueLoginState = true;

				}
			
					
				//check user info if got -> already , not -> insert new user
				checkUser = myDb.checkUserInfo(username);
				if(checkUser == true){
					Toast.makeText(Main.this, username + " : same as in user info", Toast.LENGTH_LONG).show(); 
					
				}
				else{
					              
					RegisPop.dismiss();
					CurrentUser = username;
					
					myDb.InsertUser(CurrentUser,age,chooseSex);
					myDb.InsertCurrent(CurrentUser,d,continueLoginState);
					if(!(CurrentUser.equals("Guest"))){
						TextView result = (TextView) findViewById(R.id.textUser);
						result.setVisibility(TextView.VISIBLE);
						result.setText(CurrentUser);
						Button LogoutBt = (Button) findViewById(R.id.logout);
						LogoutBt.setVisibility(Button.VISIBLE);
					}
				}
			
				
			}
		});
		
		RegisPop.show();
	}
	//Play popup
	void popUpLogIn(){
		final Dialog popLog = new Dialog(context2);
		popLog.setContentView(R.layout.activity_popup_play);
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
			
		Button PlayBt = (Button)popLog.findViewById(R.id.playBt);
		PlayBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final Date d = new Date();
				
				popLog.dismiss();
				
				myDb.InsertCurrent("Guest",d,false);
				TextView result = (TextView) findViewById(R.id.textUser);
				result.setVisibility(TextView.INVISIBLE);
				Button LogoutBt = (Button) findViewById(R.id.logout);
				LogoutBt.setVisibility(Button.INVISIBLE);
			}
		});
		
		Button LoginBt = (Button)popLog.findViewById(R.id.LogRegisBt);
		LoginBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popLog.dismiss();
				showLoginPopup();
			}
		});
	
		popLog.show();
		
		
	}
}