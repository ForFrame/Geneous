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

public class AdminReport extends Activity {

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
	
	RegisNewMember();
}

String selectedGame;
	int GameNoSelected;
	void setPostionSelected(String game,int posi){
		selectedGame = game;
		GameNoSelected = posi;
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
				int continueLoginState = 0,lenghtName;
				Typeface type = Typeface.createFromAsset(getAssets(),"fonts/teddy.ttf"); 
				 
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
						myDb.InsertUser(CurrentUser,age,chooseSex);
						Toast toast= Toast.makeText(getApplicationContext(), "ลงทะเบียน "+username+" เรียบร้อยค่ะ ", Toast.LENGTH_SHORT);
						toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 150);
						toast.show();
						
						user.setText("");
						spin1.setSelection(0);
												
					}
				}	
				
			}
		});
		
		Button cancelregis = (Button)findViewById(R.id.CancelReg);
		cancelregis.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Spinner spin1 = (Spinner)findViewById(R.id.ageSelection);
				user.setText("");
				spin1.setSelection(0);
			}
		});
				
		Tab2_bt.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectReport();
			}
		});
		
		Tab3_bt.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editAdmin();
			}
		});
	}

	void selectReport(){
		
	}
	
	void editAdmin(){
		setContentView(R.layout.admin_edit);
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		
		Button Tab1_bt = (Button)findViewById(R.id.Tab1BT);
		Button Tab2_bt = (Button)findViewById(R.id.Tab2BT);
		Button Tab3_bt = (Button)findViewById(R.id.Tab3BT);
		Tab1_bt.setClickable(true);
		Tab2_bt.setClickable(false);
		Tab3_bt.setClickable(true);
		
		final EditText user = (EditText)findViewById(R.id.oldPass);
				
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
		
	}
	
	protected void showListViewHighScore(){
		final Dialog HighPop = new Dialog(context, R.style.FullHeightDialog);
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		int[] to = new int[] { R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5  };
        String[] title = new String[] {"rowid", "col_1", "col_2", "col_3","col_4"};
        
		final List<HashMap<String, String>> fillMaps2 = new ArrayList<HashMap<String, String>>();
		final SimpleAdapter adapter2 = new SimpleAdapter(this, fillMaps2, R.layout.grid_item2, title, to);
		final Typeface type = Typeface.createFromAsset(getAssets(),"fonts/teddy.ttf"); 
   
		HighPop.setContentView(R.layout.activity_highscore_individual);
		HighPop.setCanceledOnTouchOutside(false);
		HighPop.setCancelable(false); 
        
		final Spinner spin1 = (Spinner)HighPop.findViewById(R.id.gameSelection);
		final TextView gt = (TextView)HighPop.findViewById(R.id.GameText);
		final Typeface type2 = Typeface.createFromAsset(getAssets(),"fonts/hbo.ttf");
		gt.setTypeface(type2);
		
		Button viewIvdHigh = (Button)HighPop.findViewById(R.id.viewHighscore);
		Button viewIvdGraph = (Button)HighPop.findViewById(R.id.viewGraph);
		viewIvdHigh.setClickable(false);
		viewIvdGraph.setClickable(true);

		if(!selectedGame.equals("")){
			spin1.setSelection(GameNoSelected);
			gt.setText(selectedGame);
		}
		
		spin1.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
			        
					String selected = spin1.getItemAtPosition(pos).toString();
					gt.setTypeface(type2);
			    	gt.setText(selected);
			    	
			    	ListView lv = (ListView)HighPop.findViewById(R.id.listview);
					
			        // create the grid item mapping
			        String from[][] = new String[15][5];
			        int lengths;
			       
			        String game[] = selected.split(" ");
			        String gameNo = game[0];
			        setPostionSelected(selected,pos);
			        
			        lengths = myDb.getIdvHighScore(gameNo,CurrentUser,from);
			        // prepare the list of all records
			        fillMaps2.removeAll(fillMaps2);
			        for(int i = 0; (i < lengths)&&(i<6); i++){
			        	HashMap<String, String> map = new HashMap<String, String>();
			        	map.put("rowid", "" + (i+1));
			        	map.put("col_1", from[i][0]);
			        	map.put("col_2", from[i][1]);
			        	map.put("col_3", from[i][2]);
			        	map.put("col_4", from[i][3]);
			        	fillMaps2.add(map);
			        }

			        // fill in the grid_item layout
			        lv.setAdapter(adapter2);
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				gt.setText("เลือกเกมส์ที่ต้องการ");
			}
		});
	/*	
		//view high score
		Button viewIvdHigh = (Button)HighPop.findViewById(R.id.viewHighscore);
		viewIvdHigh.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				 //TODO Auto-generated method stub

				ListView lv = (ListView)HighPop.findViewById(R.id.listview);
		        
		        // create the grid item mapping
		        String from[][] = new String[15][5];
		        int lengths;
		        String getCurrentSelected = gt.getText().toString();
		        String game[] = getCurrentSelected.split(" ");
		        String gameNo = game[0];
		        int position = Integer.parseInt(gameNo);
		        setPostionSelected(getCurrentSelected,position);
		        
		        lengths = myDb.getIdvHighScore(gameNo,CurrentUser,from);
		        // prepare the list of all records
		        fillMaps2.removeAll(fillMaps2);
		        for(int i = 0; (i < lengths)&&(i<6); i++){
		        	HashMap<String, String> map = new HashMap<String, String>();
		        	map.put("rowid", "" + (i+1));
		        	map.put("col_1", from[i][0]);
		        	map.put("col_2", from[i][1]);
		        	map.put("col_3", from[i][2]);
		        	map.put("col_4", from[i][3]);
		        	fillMaps2.add(map);
		        }

		        // fill in the grid_item layout
		        lv.setAdapter(adapter2);
			}
		});
		
	*/	
		//view graph
		viewIvdGraph.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HighPop.dismiss();
				//showListViewGraph();
			}
		});
		
        Button skipButton = (Button)HighPop.findViewById(R.id.button1);
		skipButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				HighPop.dismiss();
				
				TextView result = (TextView) findViewById(R.id.textUser);
				result.setTypeface(type);
				result.setTextColor(Color.rgb(2, 101, 203));
				result.setVisibility(TextView.VISIBLE);
				result.setText(CurrentUser);
				Button LogoutBt = (Button) findViewById(R.id.logout);
				LogoutBt.setVisibility(Button.VISIBLE);
				Button LoginBt = (Button) findViewById(R.id.loginn);
				LoginBt.setVisibility(Button.INVISIBLE);
				Button getHighScore = (Button)findViewById(R.id.showHighScore);
				getHighScore.setVisibility(TextView.VISIBLE);
				
				//instrucMain.start();
			}
		});
        
        HighPop.show();

	}
}