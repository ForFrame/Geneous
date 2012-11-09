package samcom.example.senoirandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SchoolLevel1 extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_school_level1);
	
		
		schoolLevel1();
		
	
	}
	void schoolLevel1(){
		Button CountTable = (Button)findViewById(R.id.table);
		CountTable.setOnClickListener(new View.OnClickListener() {
			 

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SchoolLevel1.this,L1ScCount.class);
				startActivity(intent);
			}
		});
		
		
		Button GameBoard = (Button)findViewById(R.id.board);
		GameBoard.setOnClickListener(new View.OnClickListener() {
			 

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SchoolLevel1.this,L1ScBoard.class);
				startActivity(intent);
			}
		});

		Button GameCalendar = (Button)findViewById(R.id.calendar);
		GameCalendar.setOnClickListener(new View.OnClickListener() {
			 

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SchoolLevel1.this,L1ScCalendar.class);
				startActivity(intent);
			}
		});
		
		Button backButton = (Button)findViewById(R.id.schoolbutton1);
		backButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SchoolLevel1.this,Main.class);
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
			schoolLevel1();
		}
		else{
			myDb.getWritableDatabase();
			myDb.ChangeHome(0);
			Intent intent = new Intent(SchoolLevel1.this,Main.class);
			startActivity(intent);
		}
		
		super.onRestart();
	}
}