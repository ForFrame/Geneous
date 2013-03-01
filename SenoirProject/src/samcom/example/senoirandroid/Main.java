package samcom.example.senoirandroid;


import android.app.Activity;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.view.Gravity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer.Orientation;

public class Main extends Activity {
String CurrentUser;
int chooseSex = 0;
Context context2 = this;
MediaPlayer soundMain;
MediaPlayer instrucMain;

SQLiteDatabase db;

/** Called when the activity is first created. */
		

	@Override
 	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
			
		setContentView(R.layout.activity_main);
		instrucMain = MediaPlayer.create(context2, R.raw.select_mode);
		instrucMain.setVolume(50, 50);
		soundMain = MediaPlayer.create(context2, R.raw.main);
		
		int valueShowPopup = 0;
		//int backToMain = 0;
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			valueShowPopup = extras.getInt("showPopup");
			//backToMain = extras.getInt("ToMain");
		}
		else{
			soundMain.start();
			soundMain.setLooping(true);
			soundMain.setVolume(30, 30);
		}

		
		        
		mainPage(valueShowPopup);
	}
	
	void mainPage(int valueLogin){
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
				
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/teddy.ttf"); 
		   
		   
		CurrentUser = myDb.SelectCurrentUser();
		
		if(valueLogin == 0){
			popUpLogIn();
		}
		else{
			instrucMain.start();
		}
		
		if(!(CurrentUser.equals("Guest"))){
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
		}
		
		if((CurrentUser.equals("Guest"))){
			TextView result = (TextView) findViewById(R.id.textUser);
			result.setVisibility(TextView.INVISIBLE);
			Button LogoutBt = (Button) findViewById(R.id.logout);
			LogoutBt.setVisibility(Button.INVISIBLE);
			Button LoginBt = (Button) findViewById(R.id.loginn);
			LoginBt.setVisibility(Button.VISIBLE);
			Button getHighScore = (Button)findViewById(R.id.showHighScore);
			getHighScore.setVisibility(TextView.INVISIBLE);
		}
		
		Button loginBut = (Button)findViewById(R.id.loginn);
		loginBut.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instrucMain.stop();
				showLoginPopup();
			}
		});
		
		Button swapPoliceButton1 = (Button)findViewById(R.id.maintopolice1);
	
		swapPoliceButton1.setOnClickListener(new View.OnClickListener() {
			 
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instrucMain.stop();
				//soundMain.stop();
				myDb.ChangeHome(1);
				Intent intent = new Intent(Main.this,SelectPoliceLevel.class);
				startActivity(intent);
			}
		});
	
		Button swapMarketButton1 = (Button)findViewById(R.id.maintomarket1);
	
		swapMarketButton1.setOnClickListener(new View.OnClickListener() {
		 
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instrucMain.stop();
				//soundMain.stop();
				myDb.ChangeHome(1);
				Intent intent = new Intent(Main.this,SelectMarketLevel.class);
				startActivity(intent);
			}
		});
	
		Button swapHouseButton1 = (Button)findViewById(R.id.maintohouse1);
	
		swapHouseButton1.setOnClickListener(new View.OnClickListener() {
			 
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instrucMain.stop();
				//soundMain.stop();
				myDb.ChangeHome(1);
				Intent intent = new Intent(Main.this,SelectHouseLevel.class);
				startActivity(intent);
			}
		});
	
		Button swapSchoolButton = (Button)findViewById(R.id.maintoschool);
	
		swapSchoolButton.setOnClickListener(new View.OnClickListener() {
			 
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instrucMain.stop();
				//soundMain.stop();
				myDb.ChangeHome(1);
				Intent intent = new Intent(Main.this,SelectSchoolLevel.class);
				startActivity(intent);
			}
		});
	
		Button swapFarmButton1 = (Button)findViewById(R.id.maintofarm1);
	
		swapFarmButton1.setOnClickListener(new View.OnClickListener() {
			 
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instrucMain.stop();
				soundMain.stop();
				myDb.ChangeHome(1);
				Intent intent = new Intent(Main.this,FarmLevel1.class);
				startActivity(intent);
			}
		});
	
	
		Button swapHospitalButton1 = (Button)findViewById(R.id.maintohospital1);
	
		swapHospitalButton1.setOnClickListener(new View.OnClickListener() {
			 
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				instrucMain.stop();
				soundMain.stop();
				myDb.ChangeHome(1);
				Intent intent = new Intent(Main.this,HospitalLevel1.class);
				startActivity(intent);
			}
		});
		
		Button LogoutButton = (Button)findViewById(R.id.logout);
		
		LogoutButton.setOnClickListener(new View.OnClickListener() {
			 
			public void onClick(View v) {
				instrucMain.stop();
				soundMain.stop();				
				myDb.logoutUser(CurrentUser);
				popUpLogIn();
			}
			
		});
		
		Button getHighScore = (Button)findViewById(R.id.showHighScore);
		getHighScore.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				instrucMain.stop();
				setPostionSelected("",0);
				showListViewHighScore();
			}
		});
	}

	void showLoginPopup(){
	
		final Dialog LoginPop = new Dialog(context2, R.style.FullHeightDialog);
		LoginPop.setContentView(R.layout.activity_popup_login);
		LoginPop.setCanceledOnTouchOutside(false);
		LoginPop.setCancelable(false); 
		
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
		
				//check user info if got -> insert status table(name,date) ,no -> message Toast 
				checkUser = myDb.checkUserInfo(username);
				if(checkUser == true){
					
					LoginPop.dismiss();
					
					CurrentUser = username;
					
					myDb.InsertCurrent(CurrentUser,d,continueLoginState);
					if(!(CurrentUser.equals("Guest"))){
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
					}
					instrucMain.start();
				}
				else{
					 
					Toast toast= Toast.makeText(getApplicationContext(), "ชื่อของคุณยังไม่มีในระบบ กรุณาลงทะเบียนค่ะ", Toast.LENGTH_SHORT);  
					toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 40, 170);
					toast.show();
				}
				
			}
		});
		
		Button RegissBt = (Button)LoginPop.findViewById(R.id.regisBt);
		RegissBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	    		imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
	    		
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
				popUpLogIn();
			}
		});
			
		LoginPop.show();
	}
	void showRegisPopup(String inputname){
		
		final Dialog RegisPop = new Dialog(context2, R.style.FullHeightDialog);
		
		RegisPop.setContentView(R.layout.activity_popup_regis);
		RegisPop.setCanceledOnTouchOutside(false);
		RegisPop.setCancelable(false); 
		 
		final myDBClass myDb = new myDBClass(this);
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
				Typeface type = Typeface.createFromAsset(getAssets(),"fonts/teddy.ttf"); 
				 
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
						RegisPop.dismiss();
						CurrentUser = username;
						
						myDb.InsertUser(CurrentUser,age,chooseSex);
						myDb.InsertCurrent(CurrentUser,d,continueLoginState);
						if(!(CurrentUser.equals("Guest"))){
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
						}
						instrucMain.start();
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
		final Dialog popLog = new Dialog(context2, R.style.FullHeightDialog);
		popLog.setContentView(R.layout.activity_popup_play);
		popLog.setCanceledOnTouchOutside(false);
		popLog.setCancelable(false); 
		
		
		final myDBClass myDb = new myDBClass(this);
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
				
				instrucMain.start();
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
	
	String selectedGame;
	int GameNoSelected;
	void setPostionSelected(String game,int posi){
		selectedGame = game;
		GameNoSelected = posi;
	}
	protected void showListViewHighScore(){
		final Dialog HighPop = new Dialog(context2, R.style.FullHeightDialog);
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
		
		
		
		Button viewIvdGraph = (Button)HighPop.findViewById(R.id.viewGraph);
		viewIvdGraph.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HighPop.dismiss();
				showListViewGraph();
			}
		});
		
        Button skipButton = (Button)HighPop.findViewById(R.id.button1);
		skipButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				//soundMain.stop();
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
				
				instrucMain.start();
			}
		});
        
        HighPop.show();

	}
	
	protected void showListViewGraph(){
		final Dialog GraphPop = new Dialog(context2, R.style.FullHeightDialog);
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		
		
		final Typeface type = Typeface.createFromAsset(getAssets(),"fonts/teddy.ttf"); 
 
		GraphPop.setContentView(R.layout.activity_graph_individual);
		GraphPop.setCanceledOnTouchOutside(false);
		GraphPop.setCancelable(false); 
     
		final Spinner spin1 = (Spinner)GraphPop.findViewById(R.id.gameSelection);
		final TextView gt = (TextView)GraphPop.findViewById(R.id.GameText);
		final Typeface type2 = Typeface.createFromAsset(getAssets(),"fonts/hbo.ttf");
		gt.setTypeface(type2);
		
		if(!selectedGame.equals("")){
			spin1.setSelection(GameNoSelected);
			gt.setText(selectedGame);
		}

		
		spin1.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
			        
					String selected = spin1.getItemAtPosition(pos).toString();
					gt.setTypeface(type2);
			    	gt.setText(selected);
    
			        String game[] = selected.split(" ");
			        String gameNo = game[0];
			        setPostionSelected(selected,pos);
			        String[] series1Numbers = {"1","2","3","4","5"};

			        //lengths = myDb.getIdvHighScore(gameNo,CurrentUser,from);
			        double[] scores = myDb.getIdvGraphScore(gameNo,CurrentUser,series1Numbers); 
			    
					GraphicalView gv = createIntent(scores,series1Numbers);

					LinearLayout rl = (LinearLayout)GraphPop.findViewById(R.id.Graphlayout);
					rl.addView(gv);

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				gt.setText("เลือกเกมส์ที่ต้องการ");
			}
			
			 public GraphicalView createIntent(double scores[],String series1Numbers[]) {
			        String[] titles = new String[] { "คะแนนที่ได้"};
			        List<double[]> values = new ArrayList<double[]>();
			        values.add(scores);
			        
			        int[] colors = new int[] { Color.parseColor("#77c4d3")};
			        XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
			        renderer.setOrientation(Orientation.HORIZONTAL);
			        setChartSettings(renderer, "", " ครั้งที่ ", " คะแนน ", 0.5,
			            6, 0, 10, Color.BLACK, Color.BLACK);
			        renderer.setXLabels(1);
			        renderer.setYLabels(5);
			        for(int i=0;i<5;i++){
			        	renderer.addXTextLabel(i+1, series1Numbers[i]);
			        }

			        int length = renderer.getSeriesRendererCount();
			        for (int i = 0; i < length; i++) {
			          SimpleSeriesRenderer seriesRenderer = renderer.getSeriesRendererAt(i);
			          seriesRenderer.setDisplayChartValues(true);
			        }
			 
			 
			        final GraphicalView grfv = ChartFactory.getBarChartView(Main.this, buildBarDataset(titles, values), renderer,Type.DEFAULT);
			   
			         
			        return grfv;
			      }
			      protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
			            XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
			            renderer.setAxisTitleTextSize(18);
			           // renderer.setChartTitleTextSize(20);
			            renderer.setLabelsTextSize(15);
			            renderer.setLegendTextSize(15);
			            renderer.setBarSpacing(1);
			             
			            renderer.setMarginsColor(Color.parseColor("#EEEDED"));
			            renderer.setXLabelsColor(Color.BLACK);
			            renderer.setYLabelsColor(0,Color.BLACK);
			             
			            renderer.setApplyBackgroundColor(true);
			            renderer.setBackgroundColor(Color.parseColor("#FBFBFC"));
			             
			            int length = colors.length;
			            for (int i = 0; i < length; i++) {
			              SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			              r.setColor(colors[i]);
			              r.setChartValuesTextAlign(Align.RIGHT);
			              r.setChartValuesTextSize(15);
			              r.setChartValuesSpacing(10);
			              renderer.addSeriesRenderer(r);
			            }
			            return renderer;
			          }
			      protected XYMultipleSeriesDataset buildBarDataset(String[] titles, List<double[]> values) {
			            XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
			            int length = titles.length;
			            for (int i = 0; i < length; i++) {
			              CategorySeries series = new CategorySeries(titles[i]);
			              double[] v = values.get(i);
			              int seriesLength = v.length;
			              for (int k = 0; k < seriesLength; k++) {
			                series.add(v[k]);
			              }
			              dataset.addSeries(series.toXYSeries());
			            }
			            return dataset;
			          }
			      protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
			              String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor,
			              int labelsColor) {

			    	  	renderer.setChartTitleTextSize(10);
			    	  	renderer.setYLabelsAlign(Align.RIGHT);
			            renderer.setXTitle(xTitle);
			            renderer.setYTitle(yTitle);
			            renderer.setXAxisMin(xMin);
			            renderer.setXAxisMax(xMax);
			            renderer.setYAxisMin(yMin);
			            renderer.setYAxisMax(yMax);
			            renderer.setMargins(new int[] { 15, 60, 15, 15 });
			            renderer.setAxesColor(axesColor);
			            renderer.setLabelsColor(labelsColor);
			          }
		});
		
		
		
		//view high score
		Button viewIvdHigh = (Button)GraphPop.findViewById(R.id.viewHighscore);
		viewIvdHigh.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				 //TODO Auto-generated method stub
				GraphPop.dismiss();
				showListViewHighScore();
			}
		});
		
		
	
		Button viewIvdGraph = (Button)GraphPop.findViewById(R.id.viewGraph);
		viewIvdGraph.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
		        String[] series1Numbers = {"1","2","3","4","5"};
		        String getCurrentSelected = gt.getText().toString();
		        String game[] = getCurrentSelected.split(" ");
		        String gameNo = game[0];
		        
		        double[] scores = myDb.getIdvGraphScore(gameNo,CurrentUser,series1Numbers); 
		        
		        GraphicalView gv = createIntent(scores,series1Numbers);

				LinearLayout rl = (LinearLayout)GraphPop.findViewById(R.id.Graphlayout);
				rl.addView(gv);
				
			}
			
			 public GraphicalView createIntent(double scores[],String series1Numbers[]) {
			        String[] titles = new String[] { "คะแนนที่ได้"};
			        List<double[]> values = new ArrayList<double[]>();
			        values.add(scores);
			        
			        int[] colors = new int[] { Color.parseColor("#77c4d3")};
			        XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
			        renderer.setOrientation(Orientation.HORIZONTAL);
			        setChartSettings(renderer, "", " ครั้งที่ ", " คะแนน ", 0.5,
			            6, 0, 10, Color.BLACK, Color.BLACK);
			        renderer.setXLabels(1);
			        renderer.setYLabels(5);
			        for(int i=0;i<5;i++){
			        	renderer.addXTextLabel(i+1, series1Numbers[i]);
			        }

			        int length = renderer.getSeriesRendererCount();
			        for (int i = 0; i < length; i++) {
			          SimpleSeriesRenderer seriesRenderer = renderer.getSeriesRendererAt(i);
			          seriesRenderer.setDisplayChartValues(true);
			        }
			 
			 
			        final GraphicalView grfv = ChartFactory.getBarChartView(Main.this, buildBarDataset(titles, values), renderer,Type.DEFAULT);
			   
			         
			        return grfv;
			      }
			      protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
			            XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
			            renderer.setAxisTitleTextSize(18);
			           // renderer.setChartTitleTextSize(20);
			            renderer.setLabelsTextSize(15);
			            renderer.setLegendTextSize(15);
			            renderer.setBarSpacing(1);
			             
			            renderer.setMarginsColor(Color.parseColor("#EEEDED"));
			            renderer.setXLabelsColor(Color.BLACK);
			            renderer.setYLabelsColor(0,Color.BLACK);
			             
			            renderer.setApplyBackgroundColor(true);
			            renderer.setBackgroundColor(Color.parseColor("#FBFBFC"));
			             
			            int length = colors.length;
			            for (int i = 0; i < length; i++) {
			              SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			              r.setColor(colors[i]);
			              r.setChartValuesTextAlign(Align.RIGHT);
			              r.setChartValuesTextSize(15);
			              r.setChartValuesSpacing(10);
			              renderer.addSeriesRenderer(r);
			            }
			            return renderer;
			          }
			      protected XYMultipleSeriesDataset buildBarDataset(String[] titles, List<double[]> values) {
			            XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
			            int length = titles.length;
			            for (int i = 0; i < length; i++) {
			              CategorySeries series = new CategorySeries(titles[i]);
			              double[] v = values.get(i);
			              int seriesLength = v.length;
			              for (int k = 0; k < seriesLength; k++) {
			                series.add(v[k]);
			              }
			              dataset.addSeries(series.toXYSeries());
			            }
			            return dataset;
			          }
			      protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title, String xTitle,
			              String yTitle, double xMin, double xMax, double yMin, double yMax, int axesColor,
			              int labelsColor) {

			    	  	renderer.setChartTitleTextSize(10);
			    	  	renderer.setYLabelsAlign(Align.RIGHT);
			            renderer.setXTitle(xTitle);
			            renderer.setYTitle(yTitle);
			            renderer.setXAxisMin(xMin);
			            renderer.setXAxisMax(xMax);
			            renderer.setYAxisMin(yMin);
			            renderer.setYAxisMax(yMax);
			            renderer.setMargins(new int[] { 15, 60, 15, 15 });
			            renderer.setAxesColor(axesColor);
			            renderer.setLabelsColor(labelsColor);
			          }
		});
	
        Button skipButton = (Button)GraphPop.findViewById(R.id.button1);
		skipButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				//soundMain.stop();
				GraphPop.dismiss();
				
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
				
				instrucMain.start();
			}
		});
        
        GraphPop.show();

	}
	public boolean onTouchEvent (MotionEvent event) {
		
		
		//InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    	//	imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);

    	
		return super.onTouchEvent(event);
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		myDb.ChangeHome(0);
		mainPage(0);
		
		super.onRestart();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	
	
	
}

