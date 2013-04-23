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
import android.widget.CheckBox;
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
Spinner spinSelect,spinAge,spinMode,spinGame;
TextView userText,dateText;
CheckBox cRound,cDate,cUsername,cSex,cAge,cScore,cTime,sortMax,sortMin;
Button CalendarBT;
RadioGroup radioSexGroup;
String selected;


public void onCreate(Bundle savedInstanceState) {
//TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	setContentView(R.layout.admin_report);
	
	soundMain = MediaPlayer.create(context, R.raw.main);
	soundMain.start();
	soundMain.setLooping(true);
	soundMain.setVolume(30, 30);
	
	//setPostionSelected("",0);
	//showListViewHighScore();
	
	spinSelect = (Spinner)findViewById(R.id.Selection);
	spinAge = (Spinner)findViewById(R.id.ageSelection);
	spinMode = (Spinner)findViewById(R.id.modeSelection);
	spinGame = (Spinner)findViewById(R.id.gameSelection);
	userText = (TextView)findViewById(R.id.Usertext);
	dateText = (TextView)findViewById(R.id.DateText);
	cRound = (CheckBox)findViewById(R.id.round_checked);
	cDate = (CheckBox)findViewById(R.id.date_checked);
	cUsername = (CheckBox)findViewById(R.id.username_checked);
	cSex = (CheckBox)findViewById(R.id.sex_checked);
	cAge = (CheckBox)findViewById(R.id.age_checked);
	cScore = (CheckBox)findViewById(R.id.score_checked);
	cTime = (CheckBox)findViewById(R.id.time_checked);
	sortMin = (CheckBox)findViewById(R.id.SortMin);
	sortMax = (CheckBox)findViewById(R.id.SortMax);
	CalendarBT = (Button)findViewById(R.id.CalendarBt);
	radioSexGroup = (RadioGroup)findViewById(R.id.radioSex);
	 
	ReportSelection();
}

String selectedGame;
	int GameNoSelected;
	void setPostionSelected(String game,int posi){
		selectedGame = game;
		GameNoSelected = posi;
	}
	
void clearView(){
	
	if(spinAge.isShown()){
		spinAge.setVisibility(View.INVISIBLE);
	}
	if(spinMode.isShown()){
		spinMode.setVisibility(View.INVISIBLE);
	}
	if(spinGame.isShown()){
		spinGame.setVisibility(View.INVISIBLE);
	}
	if(userText.isShown()){
		userText.setVisibility(View.INVISIBLE);
	}
	if(dateText.isShown()){
		dateText.setVisibility(View.INVISIBLE);
	}
	if(CalendarBT.isShown()){
		CalendarBT.setVisibility(View.INVISIBLE);
	}
	if(radioSexGroup.isShown()){
		radioSexGroup.setVisibility(View.INVISIBLE);
	}
	
}
void ReportSelection(){
				
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		
		Button Tab1_bt = (Button)findViewById(R.id.Tab1BT);
		Button Tab2_bt = (Button)findViewById(R.id.Tab2BT);
		Button Tab3_bt = (Button)findViewById(R.id.Tab3BT);
		Tab1_bt.setClickable(true);
		Tab2_bt.setClickable(false);
		Tab3_bt.setClickable(true);
		
		
		
		spinSelect.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
			        
					//String selected = spinSelect.getItemAtPosition(pos).toString();
					//gt.setTypeface(type2);
			    	//gt.setText(selected);
					selected = spinSelect.getItemAtPosition(pos).toString();
					clearView();
					if(pos == 0){
						userText.setVisibility(View.VISIBLE);
					}
					else if(pos == 1){
						radioSexGroup.setVisibility(View.VISIBLE);
						int selectedId = radioSexGroup.getCheckedRadioButtonId();
						RadioButton radioSexButton = (RadioButton)findViewById(selectedId);
						
						if(radioSexButton.equals("radioMale")){
							chooseSex = 1;
						}
						else{
							chooseSex = 0;
						}
					}
					else if(pos == 2){
						spinAge.setVisibility(View.VISIBLE);
					}
					else if(pos == 3){
						CalendarBT.setVisibility(View.VISIBLE);
						dateText.setVisibility(View.VISIBLE);
					}
					else if(pos == 4){
						spinMode.setVisibility(View.VISIBLE);
					}
					
				/*	
			    	
			    	ListView lv = (ListView)findViewById(R.id.listview);
					
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
			    */
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				//gt.setText("เลือกเกมส์ที่ต้องการ");
			}
		});
		
		spinMode.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
			        
					//selected = spinSelect.getItemAtPosition(pos).toString();
					//clearView();
				
					if(pos == 0){
						userText.setVisibility(View.VISIBLE);
					}
					else if(pos == 1){
						radioSexGroup.setVisibility(View.VISIBLE);
						int selectedId = radioSexGroup.getCheckedRadioButtonId();
						RadioButton radioSexButton = (RadioButton)findViewById(selectedId);
						
						if(radioSexButton.equals("radioMale")){
							chooseSex = 1;
						}
						else{
							chooseSex = 0;
						}
					}
					else if(pos == 2){
						spinAge.setVisibility(View.VISIBLE);
					}
					else if(pos == 3){
						CalendarBT.setVisibility(View.VISIBLE);
						dateText.setVisibility(View.VISIBLE);
					}
					else if(pos == 4){
						spinMode.setVisibility(View.VISIBLE);
					}
					
				/*	
			    	
			    	ListView lv = (ListView)findViewById(R.id.listview);
					
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
			    */
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				//gt.setText("เลือกเกมส์ที่ต้องการ");
			}
		});
		
		
		
		//Regis Button
		Button ReportBT = (Button)findViewById(R.id.reportBt);
		ReportBT.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String sql = "SELECT ";
				int column = 0;
				
				if(cUsername.isChecked()){
					sql += " Username";
					column++;
				}
				if(cSex.isChecked()){
					if(column>0){
						sql += ",";
					}
					sql += " Sex";
					column++;
				}
				if(cAge.isChecked()){
					if(column>0){
						sql += ",";
					}
					sql += " Age";
					column++;
				}
				if(cRound.isChecked()){
					if(column>0){
						sql += ",";
					}
					sql += " Round";
					column++;
				}
				if(cDate.isChecked()){
					if(column>0){
						sql += ",";
					}
					sql += " Date";
					column++;
				}
				if(cTime.isChecked()){
					if(column>0){
						sql += ",";
					}
					sql += " Time";
					column++;
				}
				if(cScore.isChecked()){
					if(column>0){
						sql += ",";
					}
					sql += " Score";
					column++;
				}
				
				sql += " FROM reportTable WHERE ";
				
				if(spinAge.isShown()){
					String selectedAge = String.valueOf(spinAge.getSelectedItem());
					String ages[] = selectedAge.split(" ");
					sql += "Age = '";
					sql += ages[0];
					sql += "' ";
				}
				if(spinGame.isShown()){
					String selectedGame = String.valueOf(spinGame.getSelectedItem());
					if(selectedGame.equals("")){
						String selectedMode = String.valueOf(spinMode.getSelectedItem());
						String modes[] = selectedMode.split(" ");
						sql += "ModeNo = '";
						sql += modes[0];
						sql += "' ";
					}
					else{
						String games[] = selectedGame.split(" ");
						sql += "GameNo = '";
						sql += games[0];
						sql += "' ";
					}
				}
				if(userText.isShown()){
					String username = userText.getText().toString();
					sql += "Username = '";
					sql += username;
					sql += "' ";
				}
				if(dateText.isShown()){
					String date = dateText.getText().toString();
					sql += "Date = '";
					sql += date;
					sql += "' ";
				}
				if(radioSexGroup.isShown()){
					int selectedId = radioSexGroup.getCheckedRadioButtonId();
					RadioButton radioSexButton = (RadioButton)findViewById(selectedId);
					if(radioSexButton.equals("radioMale")){
						chooseSex = 1;
					}
					else{
						chooseSex = 0;
					}
					sql += "Sex = '";
					sql += chooseSex;
					sql += "' ";
				}
				 
				sql += " ORDER BY Score ";
				if(sortMin.isChecked()){
					sql += "ASC ;";
				}
				if(sortMax.isChecked()){
					sql += "DESC ;";
				}
				
				//myDb.getReport(sql,column);
			}
		});
		
				
		Tab1_bt.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//selectReport();
			}
		});
		
		Tab3_bt.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//editAdmin();
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