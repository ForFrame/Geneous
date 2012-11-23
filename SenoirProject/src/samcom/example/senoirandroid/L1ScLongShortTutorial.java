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
import android.widget.AbsoluteLayout;
import android.widget.ImageView;


public class L1ScLongShortTutorial extends Activity{
	
	//MediaPlayer mp;
	//AnimationDrawable animation;
	final Context context = this;
	int RandomNum = 0,item = 0,frombutton =0  ;
	MediaPlayer soundCorrect1;
	MediaPlayer soundCorrect2;
	MediaPlayer soundShortIns;
	MediaPlayer soundLongIns;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    setContentView(R.layout.activity_l1_sc_longshort_tutorial);
	    
	    soundCorrect1 = MediaPlayer.create(context, R.raw.crab_sound);
	    soundCorrect2 = MediaPlayer.create(context, R.raw.crab_sound);
		soundShortIns = MediaPlayer.create(context, R.raw.which_short);
		soundLongIns = MediaPlayer.create(context, R.raw.which_long);
		final Animation myFadeOnceAnimation = AnimationUtils.loadAnimation(L1ScLongShortTutorial.this, R.anim.tween_once);
		final ImageView shortAns = (ImageView)findViewById(R.id.shortcorrect);
		final ImageView longAns = (ImageView)findViewById(R.id.longcorrect);
		final ImageView correctFace = (ImageView)findViewById(R.id.showcorrect);
		
		
		//intent.putExtra("numran", RandomNum);
		//intent.putExtra("numitem", item);
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			frombutton = extras.getInt("frombutt");
			RandomNum = extras.getInt("numran");
			item = extras.getInt("numitem");
			
		}
		
		//soundIns for short is instruction short sound
		soundShortIns.start();
		
		soundShortIns.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundShortIns) {
            	shortAns.setAnimation(myFadeOnceAnimation);
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
                    	 soundCorrect1.start();
                     };
                 });             
            	
            	shortAns.startAnimation(myFadeOnceAnimation);
            	
            }
        });
		
		soundCorrect1.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundCorrect) {
            	correctFace.setVisibility(View.INVISIBLE);
            	AbsoluteLayout Thislayout=(AbsoluteLayout)findViewById(R.id.longshortLayoutTuto);
            	Thislayout.setBackgroundResource(R.drawable.long_bg);
            	soundLongIns.start();
            }
        });
		
		soundLongIns.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundLongIns) {
            	longAns.setAnimation(myFadeOnceAnimation);
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
                    	 soundCorrect2.start();
                     };
                 });             
            	
            	longAns.startAnimation(myFadeOnceAnimation);
            	
            }
        });
		
		soundCorrect2.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundCorrect) {
            	
            	Intent in = new Intent(getApplicationContext(),L1ScLongShort.class);
            	if(frombutton == 1){
            		in.putExtra("longshort_tuto", 2);
            		in.putExtra("numran", RandomNum);
            		in.putExtra("numitem", item);
  				 
  				}
  			  	else{
  				  in.putExtra("longshort_tuto", 1);
  			  	}
  			  	startActivity(in);
            }
        });

		
		      
	}
	
		
	public boolean onTouchEvent (MotionEvent event) {
		//final MediaPlayer soundCorrect = MediaPlayer.create(context, R.raw.crab_sound);
		//final MediaPlayer soundIns = MediaPlayer.create(context, R.raw.ins_count_tuto);
		soundCorrect1.stop();
		soundCorrect2.stop();
		soundShortIns.stop();
		soundLongIns.stop();
		
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			  
			  
			  Intent in = new Intent(getApplicationContext(),L1ScLongShort.class);
			 
			  if(frombutton == 1){
				  	in.putExtra("longshort_tuto", 2);
          			in.putExtra("numran", RandomNum);
          			in.putExtra("numitem", item);
			  }
			  else{
				  in.putExtra("longshort_tuto", 1);
			  }
			  startActivity(in);
			
			//finish();
	
		}
		return super.onTouchEvent(event);
	}

}

