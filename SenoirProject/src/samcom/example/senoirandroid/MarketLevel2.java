package samcom.example.senoirandroid;

import java.util.ArrayList;
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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class MarketLevel2 extends Activity {
	
	String CurrentUser;
	Context context = this;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.market_level2);
		
		marketLevel2();
		
	
	}
	void marketLevel2(){
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		final LoginManage myUser = new LoginManage(this);
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/teddy.ttf"); 
		   
		   
		CurrentUser = myDb.SelectCurrentUser();
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
				myUser.showLoginPopup();
			}
		});
		
		Button LogoutButton = (Button)findViewById(R.id.logout);
		LogoutButton.setOnClickListener(new View.OnClickListener() {
			 
			public void onClick(View v) {	
				myDb.logoutUser(CurrentUser);
				Intent intent = new Intent(MarketLevel2.this,Main.class);
				intent.putExtra("Logout", 1);
				startActivity(intent);
			}
			
		});
		
		Animation myFadeInAnimation = AnimationUtils.loadAnimation(MarketLevel2.this, R.anim.tween);
		
		Button GameMoney = (Button)findViewById(R.id.water);
		GameMoney.startAnimation(myFadeInAnimation);
		GameMoney.setOnClickListener(new View.OnClickListener() {
			 
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MarketLevel2.this,MkL2Money.class);
				startActivity(intent);
			}
		});
		
		Button backButton = (Button)findViewById(R.id.backToselectMarket);
		backButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MarketLevel2.this,SelectMarketLevel.class);
				startActivity(intent);
				
			}
		});
		
		Button getHighScore = (Button)findViewById(R.id.showGameHighScore);
		getHighScore.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				showListViewHighScore();
			}
		});
	}
	
	protected void showListViewHighScore(){
		final Dialog HighPop = new Dialog(context, R.style.FullHeightDialog);
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		
		//soundPage = MediaPlayer.create(context, R.raw.page); 
		    
		HighPop.setContentView(R.layout.activity_highscore);
		HighPop.setCanceledOnTouchOutside(false);
		HighPop.setCancelable(false); 
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/hbo.ttf");
		TextView gt = (TextView)HighPop.findViewById(R.id.GameText);
		gt.setTypeface(type);
		gt.setText("เกมส์มารู้จักการอาหารมีประโยชน์หรือโทษกันเถอะ");
		
        ListView lv = (ListView)HighPop.findViewById(R.id.listview);

        // create the grid item mapping
        //String[] from = new String[] {"rowid", "col_1", "col_2", "col_3"};
        int[] to = new int[] { R.id.item1, R.id.item2, R.id.item3, R.id.item4 };
        String[] title = new String[] {"rowid", "col_1", "col_2", "col_3"};
        String from[][] = new String[15][5];
        int lengths;
        lengths = myDb.getGameHighScore("004",from);
        //int lengths = from.length;
        // prepare the list of all records
        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
        for(int i = 0; i < lengths; i++){
        	HashMap<String, String> map = new HashMap<String, String>();
        	map.put("rowid", "" + (i+1));
        	map.put("col_1", from[i][0]);
        	map.put("col_2", from[i][2]);
        	map.put("col_3", from[i][1]);
        	fillMaps.add(map);
        }

        // fill in the grid_item layout
        SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.grid_item, title, to);
        lv.setAdapter(adapter);
        
        Button skipButton = (Button)HighPop.findViewById(R.id.button1);
		skipButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				HighPop.dismiss();
				marketLevel2();
			}
		});
        
        HighPop.show();

	}
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(MarketLevel2.this,Main.class);
		startActivity(intent);
		
		super.onRestart();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		
		CurrentUser = myDb.SelectCurrentUser();
		
		Typeface type = Typeface.createFromAsset(getAssets(),"fonts/teddy.ttf");
		TextView result = (TextView) findViewById(R.id.textUser);
		result.setTypeface(type);
		result.setTextColor(Color.rgb(2, 101, 203));
		Button LogoutBt = (Button) findViewById(R.id.logout);
		Button LoginBt = (Button) findViewById(R.id.loginn);
		
		if(!(CurrentUser.equals("Guest"))){
			result.setVisibility(TextView.VISIBLE);
			result.setText(CurrentUser);
			LogoutBt.setVisibility(Button.VISIBLE);
			LoginBt.setVisibility(Button.INVISIBLE);
		}
		if((CurrentUser.equals("Guest"))){
			result.setVisibility(TextView.INVISIBLE);
			LogoutBt.setVisibility(Button.INVISIBLE);
			LoginBt.setVisibility(Button.VISIBLE);
		}
		
		super.onWindowFocusChanged(hasFocus);
	}
	
}