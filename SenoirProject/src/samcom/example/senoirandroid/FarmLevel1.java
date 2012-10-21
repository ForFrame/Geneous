package samcom.example.senoirandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class FarmLevel1 extends Activity {

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_farm_level1);

Button mainButton = (Button)findViewById(R.id.farmbutton1);
mainButton.setOnClickListener(new View.OnClickListener() {


public void onClick(View v) {
// TODO Auto-generated method stub
finish();
}
});
}
}