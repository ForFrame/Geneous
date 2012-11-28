package samcom.example.senoirandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class L1ScCalendarTutorial extends Activity{
	
	//MediaPlayer mp;
	//AnimationDrawable animation;
	final Context context = this;
	int day = 0,frombutton =0, rannum = 0;
	MediaPlayer soundCorrect;
	MediaPlayer soundIns;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    setContentView(R.layout.activity_l1_sc_calendar_tutorial);
	    
	    soundCorrect = MediaPlayer.create(context, R.raw.crab_sound);
		soundIns = MediaPlayer.create(context, R.raw.calen_tuto_sound);
		final Animation myFadeOnceAnimation = AnimationUtils.loadAnimation(L1ScCalendarTutorial.this, R.anim.tween_once);
		final ImageView helpAns = (ImageView)findViewById(R.id.answer);
		final ImageView correctFace = (ImageView)findViewById(R.id.showcorrect);
		
		
		//intent.putExtra("numran", RandomNum);
		//intent.putExtra("numitem", item);
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			day = extras.getInt("today");
			rannum = extras.getInt("numran");
			frombutton = extras.getInt("frombutt");
			
		}
		
		//soundWrong is instruction sound
		soundIns.start();
		
		soundIns.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundIns) {
            	helpAns.setAnimation(myFadeOnceAnimation);
            	myFadeOnceAnimation.setAnimationListener(new AnimationListener() {
                     public void onAnimationStart(Animation anim)
                     {
                     };
                     public void onAnimationRepeat(Animation anim)
                     {
                     };
                     public void onAnimationEnd(Animation anim)
                     {
                    	 correctFace.setVisibility(View.VISIBLE);
                    	 soundCorrect.start();
                     };
                 });             
            	
            	helpAns.startAnimation(myFadeOnceAnimation);
            	
            }
        });
		
		soundCorrect.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundCorrect) {
            	
            	Intent in = new Intent(getApplicationContext(),L1ScCalendar.class);
            	if(frombutton == 1){
  				  in.putExtra("calen_tuto", 2);
  				  in.putExtra("today", day);
  				  if(day>7)
  					  in.putExtra("numran", rannum);
  				}
  			  	else{
  				  in.putExtra("calen_tuto", 1);
  			  	}
  			  	startActivity(in);
            }
        });

		
		      
	}
	
		
	public boolean onTouchEvent (MotionEvent event) {
		//final MediaPlayer soundCorrect = MediaPlayer.create(context, R.raw.crab_sound);
		//final MediaPlayer soundIns = MediaPlayer.create(context, R.raw.ins_count_tuto);
		soundCorrect.stop();
		soundIns.stop();
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			  
			soundCorrect.stop();
			soundIns.stop();
			  
			  Intent in = new Intent(getApplicationContext(),L1ScCalendar.class);
			  //in.putExtra("tutorial", 1);
			  if(frombutton == 1){
				  in.putExtra("calen_tuto", 2);
				  in.putExtra("today", day);
				  if(day>7)
  					  in.putExtra("numran", rannum);
				  //finish();
			  }
			  else{
				  in.putExtra("calen_tuto", 1);
			  }
			  startActivity(in);
			
			//finish();
	
		}
		return super.onTouchEvent(event);
	}
	
	/*@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		myDb.ChangeHome(0);
		
		super.onDestroy();
	}*/

}

