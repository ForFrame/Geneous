package samcom.example.senoirandroid;

import android.app.Activity;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Random;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.content.Context;


public class L1ScCount extends Activity {
	static int score = 10;
	static int found = -1;
	static int randomInt;
	
	final Context context = this;
	String username;
	float timeRemain;
	int Round;
	//MyCountDown countdown ;
	
	public class MyCountDown extends CountDownTimer {
		public MyCountDown(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
		// TODO Auto-generated constructor stub
		}
		
		@Override
		public void onFinish() { // เมื่อทำงานเสร็จสิ้น
		// TODO Auto-generated method stub
		}
		
		@Override
		public void onTick(long remain) { // ในขณะที่ทำงานทุก ๆ ครั้ง
		// TODO Auto-generated method stub
			timeRemain = (float) remain / 1000;
		}
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_l1_sc_count);
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		
		myDb.addGameNo("001", "Count tables", 1);
		myDb.emptyNumberTable();
		myDb.close();
		myDb.getReadableDatabase();
		username = myDb.SelectCurrentUser();
		TextView tv1 = (TextView)findViewById(R.id.tv);
		tv1.setText("user: "+username);
		
		//final MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.counttable);
		//mediaPlayer.start();
		Round = myDb.getNumRound("001", username);
		game001();
					
	}	
	
	void game001(){
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		int count = myDb.CountNumRan();
		int Random = 0;
		int LastRanNum = 0;
		float scores;
		if(count < 10){
			Random = RanNum();
			LastRanNum = myDb.getLastNum();
			if(LastRanNum!= 0){
				hideTables(LastRanNum);
			}
			showTables(Random);
			final long startTime = ((Random*2)+10)*1000;
			//countdown = new MyCountDown(startTime,1000);
			//countdown.start();
			checkAns(Random,count);
		}
		else{
			scores = myDb.countScore("001", username, Round, count);
			if(username.equals("Guest")){
				myDb.close();
				myDb.getWritableDatabase();
				myDb.deleteGuest();
			}
			
			showPopup(scores);
		}
		
		
		
	}
	
	
	
	int RanNum(){
		int randomInt;
		Boolean isExited;
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		
		do{
			Random randomGenerator = new Random();
			randomInt = randomGenerator.nextInt(10)+1;
			isExited = myDb.checkNumber(randomInt);
		}while(isExited);
		myDb.close();
		myDb.getWritableDatabase();
		myDb.insertRanNumber(randomInt);
		return randomInt;
	}
	void checkAns(final int RandomNum,final int item){
		
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		
		final long startTime = ((RandomNum*2)+10)*1000;
		final MyCountDown countdown = new MyCountDown(startTime,1000);
		
		final float countTime = (float) startTime /1000;
		final View imgWrong = (View)findViewById(R.id.showwrongnumber); 
		countdown.start();
		Button selectButton1 = (Button)findViewById(R.id.buttonnumber1);
		selectButton1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//mediaPlayer.stop();
				View imgNum1 = (View)findViewById(R.id.shownumber1); 
				if(RandomNum == 1){
					imgWrong.setVisibility(View.INVISIBLE);
					(imgNum1).setVisibility(View.VISIBLE);
					countdown.cancel();
					myDb.addItemScore("001",username,Round,item,1,(countTime - timeRemain));
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					countdown.cancel();
					myDb.addItemScore("001",username,Round,item,0,(countTime - timeRemain));
				}
				game001();
			}
		});
		
		Button selectButton2 = (Button)findViewById(R.id.buttonnumber2);
		selectButton2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//mediaPlayer.stop();
				View imgNum2 = (View)findViewById(R.id.shownumber2); 
				if(RandomNum == 2){
					imgWrong.setVisibility(View.INVISIBLE);
					imgNum2.setVisibility(View.VISIBLE);
					countdown.cancel();
					myDb.addItemScore("001",username,Round,item,1,(countTime - timeRemain));
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					countdown.cancel();
					myDb.addItemScore("001",username,Round,item,0,(countTime - timeRemain));
				}
				game001();
			}
		});
		
		Button selectButton3 = (Button)findViewById(R.id.buttonnumber3);
		selectButton3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//mediaPlayer.stop();
				View imgNum3 = (View)findViewById(R.id.shownumber3); 
				if(randomInt == 3){
					imgWrong.setVisibility(View.INVISIBLE);
					imgNum3.setVisibility(View.VISIBLE);
					countdown.cancel();
					myDb.addItemScore("001",username,Round,item,1,(countTime - timeRemain));
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					countdown.cancel();
					myDb.addItemScore("001",username,Round,item,0,(countTime - timeRemain));
				}
				game001();
			}
		});
		
		Button selectButton4 = (Button)findViewById(R.id.buttonnumber4);
		selectButton4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//mediaPlayer.stop();
				View imgNum4 = (View)findViewById(R.id.shownumber4); 
				if(randomInt == 4){
					imgWrong.setVisibility(View.INVISIBLE);
					imgNum4.setVisibility(View.VISIBLE);
					countdown.cancel();
					myDb.addItemScore("001",username,Round,item,1,(countTime - timeRemain));
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					countdown.cancel();
					myDb.addItemScore("001",username,Round,item,0,(countTime - timeRemain));
				}
				game001();
			}
		});
		
		Button selectButton5 = (Button)findViewById(R.id.buttonnumber5);
		selectButton5.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//mediaPlayer.stop();
				View imgNum5 = (View)findViewById(R.id.shownumber5); 
				if(randomInt == 5){
					imgWrong.setVisibility(View.INVISIBLE);
					imgNum5.setVisibility(View.VISIBLE);
					countdown.cancel();
					myDb.addItemScore("001",username,Round,item,1,(countTime - timeRemain));
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					countdown.cancel();
					myDb.addItemScore("001",username,Round,item,0,(countTime - timeRemain));
				}
				game001();
			}
		});
		
		Button selectButton6 = (Button)findViewById(R.id.buttonnumber6);
		selectButton6.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//mediaPlayer.stop();
				View imgNum6 = (View)findViewById(R.id.shownumber6); 
				if(randomInt == 6){
					imgWrong.setVisibility(View.INVISIBLE);
					imgNum6.setVisibility(View.VISIBLE);
					countdown.cancel();
					myDb.addItemScore("001",username,Round,item,1,(countTime - timeRemain));
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					countdown.cancel();
					myDb.addItemScore("001",username,Round,item,0,(countTime - timeRemain));
				}
				game001();
			}
		});
		
		Button selectButton7 = (Button)findViewById(R.id.buttonnumber7);
		selectButton7.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//mediaPlayer.stop();
				View imgNum7 = (View)findViewById(R.id.shownumber7); 
				if(randomInt == 7){
					imgWrong.setVisibility(View.INVISIBLE);
					imgNum7.setVisibility(View.VISIBLE);
					countdown.cancel();
					myDb.addItemScore("001",username,Round,item,1,(countTime - timeRemain));
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					countdown.cancel();
					myDb.addItemScore("001",username,Round,item,0,(countTime - timeRemain));
				}
				game001();
			}
		});
		
		Button selectButton8 = (Button)findViewById(R.id.buttonnumber8);
		selectButton8.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//mediaPlayer.stop();
				View imgNum8 = (View)findViewById(R.id.shownumber8); 
				if(randomInt == 8){
					imgWrong.setVisibility(View.INVISIBLE);
					imgNum8.setVisibility(View.VISIBLE);
					countdown.cancel();
					myDb.addItemScore("001",username,Round,item,1,(countTime - timeRemain));
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					countdown.cancel();
					myDb.addItemScore("001",username,Round,item,0,(countTime - timeRemain));
				}
				game001();
			}
		});
		
		Button selectButton9 = (Button)findViewById(R.id.buttonnumber9);
		selectButton9.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//mediaPlayer.stop();
				View imgNum1 = (View)findViewById(R.id.shownumber9); 
				if(randomInt == 9){
					imgWrong.setVisibility(View.INVISIBLE);
					imgNum1.setVisibility(View.VISIBLE);
					countdown.cancel();
					myDb.addItemScore("001",username,Round,item,1,(countTime - timeRemain));
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					countdown.cancel();
					myDb.addItemScore("001",username,Round,item,0,(countTime - timeRemain));
				}
				game001();
			}
		});
		
		Button selectButton10 = (Button)findViewById(R.id.buttonnumber10);
		selectButton10.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//mediaPlayer.stop();
				View imgNum10 = (View)findViewById(R.id.shownumber10); 
				if(randomInt == 10){
					imgWrong.setVisibility(View.INVISIBLE);
					imgNum10.setVisibility(View.VISIBLE);
					countdown.cancel();
					myDb.addItemScore("001",username,Round,item,1,(countTime - timeRemain));
				}
				else{
					imgWrong.setVisibility(View.VISIBLE);
					countdown.cancel();
					myDb.addItemScore("001",username,Round,item,0,(countTime - timeRemain));
				}
				game001();
			}
		});
		
		Button countButton = (Button)findViewById(R.id.countbutton);
		countButton.setOnClickListener(new View.OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(L1ScCount.this,SchoolLevel1.class);
				startActivity(intent);
			}
		});
	
	}
	protected void showPopup(float scores){
		
		// custom dialog
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.activity_dialog_score_sclv1g1);
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		
		RatingBar rb = (RatingBar) findViewById(R.id.ratingBar1);
		//rb.setRating(3.5f);
		rb.setRating(scores);
		
		
		// if button is clicked, close the custom dialog
		
		Button dialogHomeBt = (Button)dialog.findViewById(R.id.scorehome);
		dialogHomeBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(L1ScCount.this,SchoolLevel1.class);
				startActivity(intent);
				
				//finish();
				
			}
		});
		
		Button dialogReplyBt = (Button)dialog.findViewById(R.id.scoreback);
		dialogReplyBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				//int random = myDb.getLastNum();
				myDb.getWritableDatabase();
				myDb.emptyNumberTable();
				myDb.close();
				myDb.getReadableDatabase();
				Round = myDb.getNumRound("001", username);
				game001();
			}
		});
		
		Button dialogNextBt = (Button)dialog.findViewById(R.id.scorenext);
		dialogNextBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(L1ScCount.this,L1ScCalendar.class);
				startActivity(intent);
				
			}
		});
		dialog.show();
			
	}			
	protected void showTables(int randomNum){
		
		switch(randomNum){
		
			case 1: ImageView imgView1 = (ImageView)findViewById(R.id.counttable1); 
					imgView1.setVisibility(ImageView.VISIBLE);	break;
			case 2: ImageView imgView2 = (ImageView)findViewById(R.id.counttable2); 
			 		imgView2.setVisibility(ImageView.VISIBLE);	break;
			case 3: ImageView imgView3 = (ImageView)findViewById(R.id.counttable3); 
					imgView3.setVisibility(ImageView.VISIBLE);	break;
			case 4: ImageView imgView4 = (ImageView)findViewById(R.id.counttable4); 
			 		imgView4.setVisibility(ImageView.VISIBLE);	break;	
			case 5: ImageView imgView5 = (ImageView)findViewById(R.id.counttable5); 
					imgView5.setVisibility(ImageView.VISIBLE);	break;
			case 6: ImageView imgView6 = (ImageView)findViewById(R.id.counttable6); 
					imgView6.setVisibility(ImageView.VISIBLE);	break;	
			case 7: ImageView imgView7 = (ImageView)findViewById(R.id.counttable7); 
					imgView7.setVisibility(ImageView.VISIBLE);	break;
			case 8: ImageView imgView8 = (ImageView)findViewById(R.id.counttable8); 
					imgView8.setVisibility(ImageView.VISIBLE);	break;	
			case 9: ImageView imgView9 = (ImageView)findViewById(R.id.counttable9); 
					imgView9.setVisibility(ImageView.VISIBLE);	break;
			case 10: ImageView imgView10 = (ImageView)findViewById(R.id.counttable10); 
					imgView10.setVisibility(ImageView.VISIBLE);	break;	
		}
	}
	
	protected void hideTables(int randomNum){
		
		switch(randomNum){
		
			case 1: ImageView imgView1 = (ImageView)findViewById(R.id.counttable1); 
					imgView1.setVisibility(ImageView.INVISIBLE);	break;
			case 2: ImageView imgView2 = (ImageView)findViewById(R.id.counttable2); 
			 		imgView2.setVisibility(ImageView.INVISIBLE);	break;
			case 3: ImageView imgView3 = (ImageView)findViewById(R.id.counttable3); 
					imgView3.setVisibility(ImageView.INVISIBLE);	break;
			case 4: ImageView imgView4 = (ImageView)findViewById(R.id.counttable4); 
			 		imgView4.setVisibility(ImageView.INVISIBLE);	break;	
			case 5: ImageView imgView5 = (ImageView)findViewById(R.id.counttable5); 
					imgView5.setVisibility(ImageView.INVISIBLE);	break;
			case 6: ImageView imgView6 = (ImageView)findViewById(R.id.counttable6); 
					imgView6.setVisibility(ImageView.INVISIBLE);	break;	
			case 7: ImageView imgView7 = (ImageView)findViewById(R.id.counttable7); 
					imgView7.setVisibility(ImageView.INVISIBLE);	break;
			case 8: ImageView imgView8 = (ImageView)findViewById(R.id.counttable8); 
					imgView8.setVisibility(ImageView.INVISIBLE);	break;	
			case 9: ImageView imgView9 = (ImageView)findViewById(R.id.counttable9); 
					imgView9.setVisibility(ImageView.INVISIBLE);	break;
			case 10: ImageView imgView10 = (ImageView)findViewById(R.id.counttable10); 
					imgView10.setVisibility(ImageView.INVISIBLE);	break;	
		}
	}
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		final myDBClass myDb = new myDBClass(this);
		myDb.getReadableDatabase();
		Boolean isThisContinue;
		isThisContinue = myDb.isCurrentContinue();
		myDb.close();
		if(isThisContinue == true){
			game001();
		}
		else{
			myDb.getWritableDatabase();
			myDb.ChangeHome(0);
			Intent intent = new Intent(L1ScCount.this,Main.class);
			startActivity(intent);
		}
		
		super.onRestart();
	}
}
	
	

