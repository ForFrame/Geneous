package samcom.example.senoirandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class L1ScCalendar extends Activity {

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_l1_sc_calendar);

Button calendarButton = (Button)findViewById(R.id.calendarbutton);
calendarButton.setOnClickListener(new View.OnClickListener() {


public void onClick(View v) {
// TODO Auto-generated method stub
finish();
}
});
}
}