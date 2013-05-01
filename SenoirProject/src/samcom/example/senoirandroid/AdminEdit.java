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

public class AdminEdit extends Activity {

Context context = this;
String CurrentUser;
int chooseSex = 0;
MediaPlayer soundMain;

public void onCreate(Bundle savedInstanceState) {
//TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	setContentView(R.layout.admin_edit);
	
	soundMain = MediaPlayer.create(context, R.raw.main);
	soundMain.start();
	soundMain.setLooping(true);
	soundMain.setVolume(30, 30);
	
	//setPostionSelected("",0);
	//showListViewHighScore();
	
	editAdmin();
}

void editAdmin(){
				
		final myDBClass myDb = new myDBClass(this);
		
		
		Button Tab1_bt = (Button)findViewById(R.id.Tab1BT);
		Button Tab2_bt = (Button)findViewById(R.id.Tab2BT);
		Button Tab3_bt = (Button)findViewById(R.id.Tab3BT);
		Tab1_bt.setClickable(true);
		Tab2_bt.setClickable(true);
		Tab3_bt.setClickable(false);
		
		final EditText user = (EditText)findViewById(R.id.regUsertext);
		user.setEnabled(false);		
		
		final EditText oldPass = (EditText)findViewById(R.id.oldPass);
		oldPass.setFocusable(true);
		oldPass.requestFocus();
		oldPass.setOnFocusChangeListener(new View.OnFocusChangeListener(){ 
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
		
		final EditText newPass = (EditText)findViewById(R.id.newPass);
	/*	newPass.setFocusable(true);
		newPass.requestFocus();
		newPass.setOnFocusChangeListener(new View.OnFocusChangeListener(){ 
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
		*/
		final EditText newPass2 = (EditText)findViewById(R.id.newPass2);
		newPass2.setFocusable(true);
		newPass2.requestFocus();
		newPass2.setOnFocusChangeListener(new View.OnFocusChangeListener(){ 
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
		
		//Ok Button
		Button OkjBt = (Button)findViewById(R.id.okBt);
		OkjBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				//Username
				//EditText user = (EditText)findViewById(R.id.regUsertext);
				String opass = oldPass.getText().toString();
				String npass1 = newPass.getText().toString();
				String npass2 = newPass2.getText().toString();
				
				String getPass = myDb.passAdmin();
				
				if(opass.equals(getPass)){
					if(npass1.equals(npass2)){
						//change password
						myDb.changePass(npass1);
						Toast toast= Toast.makeText(getApplicationContext(), " เปลี่ยนรหัสผ่านเสร็จสิิ้น  ", Toast.LENGTH_SHORT);  
						toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 130);
						toast.show();
						
						oldPass.setText("");
						newPass.setText("");
						newPass2.setText("");
					}
					else{
						//newPass 1 != newPass2
						Toast toast= Toast.makeText(getApplicationContext(), " รหัสผ่านใหม่ ไม่เหมือนกันค่ะ  ", Toast.LENGTH_SHORT);  
						toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 130);
						toast.show();
					}
				}
				else{
					// wrong old pass
					Toast toast= Toast.makeText(getApplicationContext(), " รหัสไม่ถูกต้องค่ะ  ", Toast.LENGTH_SHORT);  
					toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 130);
					toast.show();
				}
			}
		});
		
		Button cancelEdit = (Button)findViewById(R.id.Home);
		cancelEdit.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				soundMain.stop();
				Intent in = new Intent(AdminEdit.this,Main.class);
				in.putExtra("showPopup", 1);
				startActivity(in);
			}
		});
				
		Tab1_bt.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				soundMain.stop();
				Intent intent = new Intent(AdminEdit.this,AdminRegis.class);
				startActivity(intent);
			}
		});
		
		Tab2_bt.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//editAdmin();
			}
		});
	}

	protected void onRestart() {
		// TODO Auto-generated method stub
		
		Intent intent = new Intent(AdminEdit.this,Main.class);
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
	    	Intent in = new Intent(AdminEdit.this,Main.class);
			in.putExtra("showPopup", 1);
			startActivity(in);  
	    	return false;
	    }
	    return super.onKeyDown(keyCode, event);
	}

}