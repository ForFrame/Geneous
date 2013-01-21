package samcom.example.senoirandroid;

import java.util.Date;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LoginManage extends Activity {

Context context;
String CurrentUser;
int chooseSex = 0;

public LoginManage(Context context2) {

//TODO Auto-generated constructor stub
	context = context2;
}

public void onCreate(Bundle savedInstanceState) {
//TODO Auto-generated method stub
	
}

public void showLoginPopup(){
	
	final Dialog LoginPop = new Dialog(context, R.style.FullHeightDialog);
	LoginPop.setContentView(R.layout.activity_popup_login);
	LoginPop.setCanceledOnTouchOutside(false);
	LoginPop.setCancelable(false); 
	
	final myDBClass myDb = new myDBClass(context);
	
	Button LoginnBt = (Button)LoginPop.findViewById(R.id.LoginBt);
	LoginnBt.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			final boolean checkUser;
			final String username;
			final Date d = new Date();
			int continueLoginState = 0; 
			EditText user = (EditText)LoginPop.findViewById(R.id.usertext);
			username = user.getText().toString();
	
			//check user info if got -> insert status table(name,date) ,no -> message Toast 
			myDb.getReadableDatabase();
			checkUser = myDb.checkUserInfo(username);
			if(checkUser == true){
				myDb.getWritableDatabase();
				myDb.InsertCurrent(username,d,continueLoginState);
				LoginPop.dismiss();
				finish();
			}
			else{
				 
				Toast toast= Toast.makeText(context, "ชื่อของคุณยังไม่มีในระบบ กรุณาลงทะเบียนค่ะ", Toast.LENGTH_SHORT);  
				toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 40, 170);
				toast.show();
			}
			
		}
	});
	
	Button RegissBt = (Button)LoginPop.findViewById(R.id.regisBt);
	RegissBt.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			// TODO Auto-generated method stub
		String name;	
			EditText user = (EditText)LoginPop.findViewById(R.id.usertext);
			name = user.getText().toString();
			
			LoginPop.dismiss();
			showRegisPopup(name);
			
		}
	});
	
	Button cancellogin = (Button)LoginPop.findViewById(R.id.CancelLog);
	cancellogin.setOnClickListener(new View.OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			LoginPop.dismiss();
			finish();
		}
	});
		
	LoginPop.show();
}
public void showRegisPopup(String inputname){
	
	final Dialog RegisPop = new Dialog(context, R.style.FullHeightDialog);
	
	RegisPop.setContentView(R.layout.activity_popup_regis);
	RegisPop.setCanceledOnTouchOutside(false);
	RegisPop.setCancelable(false); 
	 
	final myDBClass myDb = new myDBClass(context);
	myDb.getWritableDatabase();
			
	EditText user = (EditText)RegisPop.findViewById(R.id.regUsertext);
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
			 
			//Username
			EditText user = (EditText)RegisPop.findViewById(R.id.regUsertext);
			username = user.getText().toString();
			
			//Choose sex (boy/girl)
			
			RadioGroup radioSexGroup = (RadioGroup)RegisPop.findViewById(R.id.radioSex);
					 
			int selectedId = radioSexGroup.getCheckedRadioButtonId();
			RadioButton radioSexButton = (RadioButton)RegisPop.findViewById(selectedId);
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

		
			lenghtName = username.length();
			if((lenghtName<1)){
				Toast toast= Toast.makeText(context, "ชื่อผู้ใช้งานต้องมีอย่างน้อย  1 ตัวอักษรค่ะ", Toast.LENGTH_SHORT);  
				toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 130);
				toast.show();
			}
			else if((lenghtName>10)){
				Toast toast= Toast.makeText(context, "ชื่อผู้ใช้งานต้อง  < 10  ตัวอักษรค่ะ", Toast.LENGTH_SHORT);  
				toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 150);
				toast.show();
			}
			else{
				checkUser = myDb.checkUserInfo(username);
				if(checkUser == true){
					Toast toast= Toast.makeText(context, "ชื่อนี้ถูกใช้แล้ว กรุณากรอกชื่ออื่นค่ะ ", Toast.LENGTH_SHORT);  
					toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 150);
					toast.show();
					
				}
				else{
					//check user info if got -> already , not -> insert new user
					
					CurrentUser = username;
					
					myDb.InsertUser(CurrentUser,age,chooseSex);
					myDb.InsertCurrent(CurrentUser,d,continueLoginState);
					
					RegisPop.dismiss();
					finish();
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
public void popUpLogIn(){
	final Dialog popLog = new Dialog(context, R.style.FullHeightDialog);
	popLog.setContentView(R.layout.activity_popup_play);
	popLog.setCanceledOnTouchOutside(false);
	popLog.setCancelable(false); 
	
	
	final myDBClass myDb = new myDBClass(context);
	myDb.getWritableDatabase();
	
	Button PlayyBt = (Button)popLog.findViewById(R.id.playBt);
	PlayyBt.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			final Date d = new Date();
			popLog.dismiss();
			
			myDb.InsertCurrent("Guest",d,0);
			TextView result = (TextView) findViewById(R.id.textUser);
			result.setVisibility(TextView.INVISIBLE);
			Button LogoutBt = (Button) findViewById(R.id.logout);
			LogoutBt.setVisibility(Button.INVISIBLE);
			Button LoginBt = (Button) findViewById(R.id.loginn);
			LoginBt.setVisibility(Button.VISIBLE);
			Button getHighScore = (Button)findViewById(R.id.showHighScore);
			getHighScore.setVisibility(TextView.INVISIBLE);
			
			//instrucMain.start();
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
