package samcom.example.senoirandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SchoolLevel3 extends Activity {

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_school_level1);

	
	
	Button CountTable = (Button)findViewById(R.id.table);
	CountTable.setOnClickListener(new View.OnClickListener() {
		 

		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(SchoolLevel3.this,L1ScCount.class);
			startActivity(intent);
		}
	});
	
	
	Button GameBoard = (Button)findViewById(R.id.board);
	GameBoard.setOnClickListener(new View.OnClickListener() {
		 

		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(SchoolLevel3.this,L1ScBoard.class);
			startActivity(intent);
		}
	});

	Button GameCalendar = (Button)findViewById(R.id.calendar);
	GameCalendar.setOnClickListener(new View.OnClickListener() {
		 

		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(SchoolLevel3.this,L1ScCalendar.class);
			startActivity(intent);
		}
	});
	
	Button backButton = (Button)findViewById(R.id.backToselectSchool);
	backButton.setOnClickListener(new View.OnClickListener() {

		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(SchoolLevel3.this,Main.class);
			startActivity(intent);
			//finish();
		}
	});

}
}