package samcom.example.senoirandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class School extends Activity {

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_school);

Button mainButton = (Button)findViewById(R.id.schoolbutton);
mainButton.setOnClickListener(new View.OnClickListener() {


public void onClick(View v) {
// TODO Auto-generated method stub
finish();
}
});
}
}