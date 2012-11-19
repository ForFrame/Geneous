package samcom.example.senoirandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class L1ScCountTutorial extends Activity{
	
	//MediaPlayer mp;
	//AnimationDrawable animation;
	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_l1_sc_count_tutorial);
	    
	    final MediaPlayer soundCorrect = MediaPlayer.create(context, R.raw.crab_sound);
		final MediaPlayer soundIns = MediaPlayer.create(context, R.raw.wrong_sound2);
		final Animation myFadeOnceAnimation = AnimationUtils.loadAnimation(L1ScCountTutorial.this, R.anim.tween_once);
		final ImageView helpNo = (ImageView)findViewById(R.id.helpNumber);
		
		//soundWrong is instruction sound
		soundIns.start();
		
		soundIns.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundIns) {
            	
            	helpNo.startAnimation(myFadeOnceAnimation);
            	
            }
        });
		
		final ImageView correctFace = (ImageView)findViewById(R.id.showcorrectnumber);
		correctFace.setVisibility(View.VISIBLE);
		soundCorrect.start();
		
		soundCorrect.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer soundCorrect) {
            	
            	Intent in = new Intent(getApplicationContext(),L1ScCount.class);
  			  	in.putExtra("tutorial", 1);
  			  	startActivity(in);
            }
        });

		
		      
	}
	
		
	public boolean onTouchEvent (MotionEvent event) {
		final MediaPlayer soundCorrect = MediaPlayer.create(context, R.raw.crab_sound);
		final MediaPlayer soundIns = MediaPlayer.create(context, R.raw.wrong_sound2);
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			  
			soundCorrect.stop();
			  soundIns.stop();
			  
			  //Intent intent = new Intent(L1ScCountTutorial.this,L1ScCount.class);
			  //startActivity(intent);
			  
			  Intent in = new Intent(getApplicationContext(),L1ScCount.class);
			  in.putExtra("tutorial", 1);
			  startActivity(in);
	
		}
		return super.onTouchEvent(event);
	}

}

