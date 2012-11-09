package samcom.example.senoirandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SelectSchoolLevel extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_select_school_level);
	
		
		selectLevel();
	
	}
	
	void selectLevel(){

		Button ScLv1 = (Button)findViewById(R.id.schoollevel1);
		ScLv1.setOnClickListener(new View.OnClickListener() {
			 

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SelectSchoolLevel.this,SchoolLevel1.class);
				startActivity(intent);
			}
		});
		
		
		Button ScLv2 = (Button)findViewById(R.id.schoollevel2);
		ScLv2.setOnClickListener(new View.OnClickListener() {
			 

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SelectSchoolLevel.this,SchoolLevel2.class);
				startActivity(intent);
			}
		});

			
		Button ScLv3 = (Button)findViewById(R.id.schoollevel3);
		ScLv3.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SelectSchoolLevel.this,SchoolLevel3.class);
				startActivity(intent);
				//finish();
			}
		});
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
			selectLevel();
		}
		else{
			myDb.getWritableDatabase();
			myDb.ChangeHome(0);
			Intent intent = new Intent(SelectSchoolLevel.this,Main.class);
			startActivity(intent);
		}
		
		super.onRestart();
	}
	
	
}