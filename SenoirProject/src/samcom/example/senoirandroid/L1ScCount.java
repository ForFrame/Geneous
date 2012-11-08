package samcom.example.senoirandroid;

import android.app.Activity;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_l1_sc_count);
		score = 10;
		
		//final int[] chackNumber = new int[10];
		//do{
			Random randomGenerator = new Random();
			randomInt = randomGenerator.nextInt(10)+1;
		//	found = checkArray(randomInt);
		//}while(found != -1);
		
		showTables(randomInt);
		final MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.counttable);
		mediaPlayer.start();
		
		
		
		final View imgWrong = (View)findViewById(R.id.showwrongnumber); 
				
		Button selectButton1 = (Button)findViewById(R.id.buttonnumber1);
		selectButton1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mediaPlayer.stop();
				View imgNum1 = (View)findViewById(R.id.shownumber1); 
				if(randomInt == 1){
					imgWrong.setVisibility(View.INVISIBLE);
					imgNum1.setVisibility(View.VISIBLE);
					showPopup(score);
					
				}
				else{
					//imgBlank.setVisibility(View.VISIBLE);
					imgWrong.setVisibility(View.VISIBLE);
					score -= 1;
				}
			}
		});
		
		Button selectButton2 = (Button)findViewById(R.id.buttonnumber2);
		selectButton2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mediaPlayer.stop();
				View imgNum2 = (View)findViewById(R.id.shownumber2); 
				if(randomInt == 2){
					imgWrong.setVisibility(View.INVISIBLE);
					imgNum2.setVisibility(View.VISIBLE);
					showPopup(score);
				}
				else{
					//imgBlank.setVisibility(View.VISIBLE);
					imgWrong.setVisibility(View.VISIBLE);
					score -= 1;
				}
			}
		});
		
		Button selectButton3 = (Button)findViewById(R.id.buttonnumber3);
		selectButton3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mediaPlayer.stop();
				View imgNum3 = (View)findViewById(R.id.shownumber3); 
				if(randomInt == 3){
					imgWrong.setVisibility(View.INVISIBLE);
					imgNum3.setVisibility(View.VISIBLE);
					showPopup(score);
				}
				else{
					//imgBlank.setVisibility(View.VISIBLE);
					imgWrong.setVisibility(View.VISIBLE);
					score -= 1;
				}
			}
		});
		
		Button selectButton4 = (Button)findViewById(R.id.buttonnumber4);
		selectButton4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mediaPlayer.stop();
				View imgNum4 = (View)findViewById(R.id.shownumber4); 
				if(randomInt == 4){
					imgWrong.setVisibility(View.INVISIBLE);
					imgNum4.setVisibility(View.VISIBLE);
					showPopup(score);
				}
				else{
					//imgBlank.setVisibility(View.VISIBLE);
					imgWrong.setVisibility(View.VISIBLE);
					score -= 1;
				}
			}
		});
		
		Button selectButton5 = (Button)findViewById(R.id.buttonnumber5);
		selectButton5.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mediaPlayer.stop();
				View imgNum5 = (View)findViewById(R.id.shownumber5); 
				if(randomInt == 5){
					imgWrong.setVisibility(View.INVISIBLE);
					imgNum5.setVisibility(View.VISIBLE);
					showPopup(score);
				}
				else{
					//imgBlank.setVisibility(View.VISIBLE);
					imgWrong.setVisibility(View.VISIBLE);
					score -= 1;
				}
			}
		});
		
		Button selectButton6 = (Button)findViewById(R.id.buttonnumber6);
		selectButton6.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mediaPlayer.stop();
				View imgNum6 = (View)findViewById(R.id.shownumber6); 
				if(randomInt == 6){
					imgWrong.setVisibility(View.INVISIBLE);
					imgNum6.setVisibility(View.VISIBLE);
					showPopup(score);
				}
				else{
					//imgBlank.setVisibility(View.VISIBLE);
					imgWrong.setVisibility(View.VISIBLE);
					score -= 1;
				}
			}
		});
		
		Button selectButton7 = (Button)findViewById(R.id.buttonnumber7);
		selectButton7.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mediaPlayer.stop();
				View imgNum7 = (View)findViewById(R.id.shownumber7); 
				if(randomInt == 7){
					imgWrong.setVisibility(View.INVISIBLE);
					imgNum7.setVisibility(View.VISIBLE);
					showPopup(score);
				}
				else{
					//imgBlank.setVisibility(View.VISIBLE);
					imgWrong.setVisibility(View.VISIBLE);
					score -= 1;
				}
			}
		});
		
		Button selectButton8 = (Button)findViewById(R.id.buttonnumber8);
		selectButton8.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mediaPlayer.stop();
				View imgNum8 = (View)findViewById(R.id.shownumber8); 
				if(randomInt == 8){
					imgWrong.setVisibility(View.INVISIBLE);
					imgNum8.setVisibility(View.VISIBLE);
					showPopup(score);
				}
				else{
					//imgBlank.setVisibility(View.VISIBLE);
					imgWrong.setVisibility(View.VISIBLE);
					score -= 1;
				}
			}
		});
		
		Button selectButton9 = (Button)findViewById(R.id.buttonnumber9);
		selectButton9.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mediaPlayer.stop();
				View imgNum1 = (View)findViewById(R.id.shownumber9); 
				if(randomInt == 9){
					imgWrong.setVisibility(View.INVISIBLE);
					imgNum1.setVisibility(View.VISIBLE);
					showPopup(score);
				}
				else{
					//imgBlank.setVisibility(View.VISIBLE);
					imgWrong.setVisibility(View.VISIBLE);
					score -= 1;
				}
			}
		});
		
		Button selectButton10 = (Button)findViewById(R.id.buttonnumber10);
		selectButton10.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mediaPlayer.stop();
				View imgNum10 = (View)findViewById(R.id.shownumber10); 
				if(randomInt == 10){
					imgWrong.setVisibility(View.INVISIBLE);
					imgNum10.setVisibility(View.VISIBLE);
					showPopup(score);
				}
				else{
					//imgBlank.setVisibility(View.VISIBLE);
					imgWrong.setVisibility(View.VISIBLE);
					score -= 1;
				}
			}
		});
		
		if(score < 7){
			
			mediaPlayer.start();
		}
		
		
				
		Button countButton = (Button)findViewById(R.id.countbutton);
		countButton.setOnClickListener(new View.OnClickListener() {
	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}	
	
	
	protected void showPopup(int scores){
		
		// custom dialog
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.activity_dialog_score_sclv1g1);
		//dialog.setTitle("Title...");

		// set the custom dialog components - text, image and button
		//TextView text = (TextView) dialog.findViewById(R.id.textView1);
		//text.setText("Android custom dialog example!");
		switch(scores){
			case 10: ImageView score5 = (ImageView)dialog.findViewById(R.id.star5); 
					score5.setVisibility(ImageView.VISIBLE);	break;
			case 9: ImageView score4 = (ImageView)dialog.findViewById(R.id.star4); 
					score4.setVisibility(ImageView.VISIBLE);	break;	
			case 8: ImageView score3 = (ImageView)dialog.findViewById(R.id.star3); 
					score3.setVisibility(ImageView.VISIBLE);	break;
			case 7: ImageView score2 = (ImageView)dialog.findViewById(R.id.star2); 
					score2.setVisibility(ImageView.VISIBLE);	break;	
			case 6: ImageView score1 = (ImageView)dialog.findViewById(R.id.star1); 
					score1.setVisibility(ImageView.VISIBLE);	break;
			default: ImageView score0 = (ImageView)dialog.findViewById(R.id.star0); 
					score0.setVisibility(ImageView.VISIBLE);	break;		
		}
		//ImageView image = (ImageView) dialog.findViewById(R.id.image);
		//image.setImageResource(R.drawable.ic_launcher);

		Button dialogHomeBt1 = (Button) dialog.findViewById(R.id.scorehome);
		// if button is clicked, close the custom dialog
		
		Button dialogHomeBt = (Button)dialog.findViewById(R.id.scorehome);
		dialogHomeBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				finish();
				
			}
		});
		
		Button dialogReplyBt = (Button)dialog.findViewById(R.id.scoreback);
		dialogReplyBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(L1ScCount.this,L1ScCount.class);
				startActivity(intent);
				
			}
		});
		
		Button dialogNextBt = (Button)dialog.findViewById(R.id.scorenext);
		dialogNextBt.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				Intent intent = new Intent(L1ScCount.this,L1ScCount.class);
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
	
	int checkArray(int number){
		int[] checkNumber = new int[10];
			if(checkNumber[number-1] != 1){
				checkNumber[number-1] = 1;
				return -1;
			}
		
		return number;
		
	}}

