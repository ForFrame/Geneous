package samcom.example.senoirandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class L1ScCalendar extends Activity {

@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_l1_sc_calendar);
	
		game002();
	}

	void game002(){
		Button calendarButton = (Button)findViewById(R.id.calendarbutton);
		calendarButton.setOnClickListener(new View.OnClickListener() {
		
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(L1ScCalendar.this,SchoolLevel1.class);
			startActivity(intent);
		}
		});
	}
	protected void onRestart() {
		// TODO Auto-generated method stub
		final myDBClass myDb = new myDBClass(this);
		//myDb.getReadableDatabase();
		
		//this comment for ติ๊ก ๆๆๆๆ  continue (1,1) state
		//Boolean isThisContinue;
		//isThisContinue = myDb.isCurrentContinue();
		//myDb.close();
		//if(isThisContinue == true){
		//	game002();
		//}
		//else{
			myDb.getWritableDatabase();
			myDb.ChangeHome(0);
			Intent intent = new Intent(L1ScCalendar.this,Main.class);
			startActivity(intent);
		//}
		
		super.onRestart();
	}
}