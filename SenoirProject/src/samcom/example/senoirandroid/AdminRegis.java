package samcom.example.senoirandroid;

import java.util.ArrayList;
import java.util.Date;
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
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class AdminRegis extends Activity {

Context context = this;
//String CurrentUser;
int chooseSex = 0;
MediaPlayer soundMain;

public void onCreate(Bundle savedInstanceState) {
//TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	setContentView(R.layout.admin_regis);
	
	soundMain = MediaPlayer.create(context, R.raw.main);
	soundMain.start();
	soundMain.setLooping(true);
	soundMain.setVolume(30, 30);
	
	//setPostionSelected("",0);
	//showListViewHighScore();
	
	RegisNewMember();
}
	
void RegisNewMember(){
				
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		
		Button Tab1_bt = (Button)findViewById(R.id.Tab1BT);
		Button Tab2_bt = (Button)findViewById(R.id.Tab2BT);
		Button Tab3_bt = (Button)findViewById(R.id.Tab3BT);
		Tab1_bt.setClickable(false);
		Tab2_bt.setClickable(true);
		Tab3_bt.setClickable(true);
		
		final EditText user = (EditText)findViewById(R.id.regUsertext);
				
		user.setFocusable(true);
		user.requestFocus();
		user.setOnFocusChangeListener(new View.OnFocusChangeListener(){ 
			 public void onFocusChange(View v, boolean hasFocus) { 
				 if (hasFocus) { 
					 InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
					 imm.showSoftInput(user, InputMethodManager.SHOW_IMPLICIT); 
				 }
				 else{
					 InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
					 imm.hideSoftInputFromWindow(user.getWindowToken(), 0);
				 }
			 } 
		});
		
		//Regis Button
		Button RegissBt = (Button)findViewById(R.id.regisBt);
		RegissBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				final boolean checkUser;
				final String selectedAge;
				final String username;
				final Date d = new Date();
				int lenghtName;
								 
				//Username
				EditText user = (EditText)findViewById(R.id.regUsertext);
				username = user.getText().toString();
				
				//Choose sex (boy/girl)
				
				RadioGroup radioSexGroup = (RadioGroup)findViewById(R.id.radioSex);
						 
				int selectedId = radioSexGroup.getCheckedRadioButtonId();
				RadioButton radioSexButton = (RadioButton)findViewById(selectedId);
				if(radioSexButton.equals("radioMale")){
					chooseSex = 1;
				}
				else{
					chooseSex = 0;
				}
				
				//Select age (2-6 year)	
				Spinner spin1 = (Spinner)findViewById(R.id.ageSelection);
				
					selectedAge = String.valueOf(spin1.getSelectedItem());
					String ages[] = selectedAge.split(" ");
					int age = Integer.parseInt(ages[0]);

			
				lenghtName = username.length();
				if((lenghtName<1)){
					Toast toast= Toast.makeText(getApplicationContext(), "ชื่อผู้ใช้งานต้องมีอย่างน้อย  1 ตัวอักษรค่ะ", Toast.LENGTH_SHORT);  
					toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 130);
					toast.show();
				}
				else if((lenghtName>10)){
					Toast toast= Toast.makeText(getApplicationContext(), "ชื่อผู้ใช้งานต้อง  < 10  ตัวอักษรค่ะ", Toast.LENGTH_SHORT);  
					toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 150);
					toast.show();
				}
				else{
					checkUser = myDb.checkUserInfo(username);
					if(checkUser == true){
						Toast toast= Toast.makeText(getApplicationContext(), "ชื่อนี้ถูกใช้แล้ว กรุณากรอกชื่ออื่นค่ะ ", Toast.LENGTH_SHORT);  
						toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 150);
						toast.show();
						
					}
					else{
						//check user info if got -> already , not -> insert new user
						myDb.InsertUser(username,age,chooseSex);
						Toast toast= Toast.makeText(getApplicationContext(), "ลงทะเบียน "+username+" เรียบร้อยค่ะ ", Toast.LENGTH_SHORT);
						toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 150);
						toast.show();
						
						user.setText("");
						spin1.setSelection(0);
												
					}
				}	
				
			}
		});
		
		
		Button cancelRegis = (Button)findViewById(R.id.Home);
		cancelRegis.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				soundMain.stop();
				Intent in = new Intent(AdminRegis.this,Main.class);
				in.putExtra("showPopup", 1);
				startActivity(in);
			}
		});
				
		Tab2_bt.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//selectReport();
			}
		});
		
		Tab3_bt.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				soundMain.stop();
				Intent intent = new Intent(AdminRegis.this,AdminEdit.class);
				startActivity(intent);
			}
		});
	}

	protected void onRestart() {
		// TODO Auto-generated method stub
		
		Intent intent = new Intent(AdminRegis.this,Main.class);
		startActivity(intent);
		
		
		super.onRestart();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if(soundMain.isLooping()){
			soundMain.stop();
		}
		
		super.onDestroy();
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		
		if(soundMain.isLooping()){
			soundMain.stop();
		}
		
		super.onPause();
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	    	soundMain.stop();
	    	Intent in = new Intent(AdminRegis.this,Main.class);
			in.putExtra("showPopup", 1);
			startActivity(in);  
	    	return false;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
