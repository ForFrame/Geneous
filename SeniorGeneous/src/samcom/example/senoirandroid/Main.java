package samcom.example.senoirandroid;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

public class Main extends Activity {
protected String UserText;
final Context context2 = this;

SQLiteDatabase db;

/** Called when the activity is first created. */
	
	
	@Override
 	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		
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
		
		Button LoginButton = (Button)findViewById(R.id.login1);
		
		LoginButton.setOnClickListener(new View.OnClickListener() {
			 
	
			public void onClick(View v) {
				popUpLogIn();
			}
			
		});
	}

	void popUpLogIn(){
		final Dialog popLog = new Dialog(context2);
		popLog.setContentView(R.layout.activity_popup_login);
		
				
		Button LoginBt = (Button)popLog.findViewById(R.id.logbnt);
		LoginBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popLog.dismiss();
				EditText user = (EditText)popLog.findViewById(R.id.userTxt);
				UserText = user.getText().toString();
				TextView result = (TextView) findViewById(R.id.textUser);
				result.setText(user.getText().toString());
			}
		});
		
		
		popLog.show();
		
		
	}
}