package samcom.example.senoirandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;


public class PlL2NearFarTutorial extends Activity{
	
	//MediaPlayer mp;
	//AnimationDrawable animation;
	final Context context = this;
	int RandomNum = 0,item = 0,frombutton =0  ;
	MediaPlayer soundIns;
	MediaPlayer soundAns;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	    setContentView(R.layout.activity_pl_l2_nearfar_tutorial);
	    
	    soundIns = MediaPlayer.create(context, R.raw.try_to_count);
		soundAns = MediaPlayer.create(context, R.raw.choose_count);
		final Animation myFadeAnimation = AnimationUtils.loadAnimation(PlL2NearFarTutorial.this, R.anim.tween);
		final ImageView helpAns = (ImageView)findViewById(R.id.showAnswer);
		final ImageView instruct = (ImageView)findViewById(R.id.helpNearFar);
		
		//soundWrong is instruction sound
				instruct.startAnimation(myFadeAnimation);
				soundIns.start();
				
				soundIns.setOnCompletionListener(new OnCompletionListener() {
		            public void onCompletion(MediaPlayer soundIns) {
		            	instruct.clearAnimation();
		            	soundAns.start();
		            	helpAns.startAnimation(myFadeAnimation);
		            }
		        });
				
				Button skipHelp = (Button)findViewById(R.id.bt_skip);
				skipHelp.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						// TODO Auto-generated method stub
						soundIns.stop();
						soundAns.stop();
						Intent in = new Intent(getApplicationContext(),PlL2NearFar.class);
							   in.putExtra("tutorial", 1);
		  			  	startActivity(in);
					}
				});
		
		
		      
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		final myDBClass myDb = new myDBClass(this);
		myDb.getWritableDatabase();
		myDb.ChangeHome(1);
		
		super.onDestroy();
	}

}

