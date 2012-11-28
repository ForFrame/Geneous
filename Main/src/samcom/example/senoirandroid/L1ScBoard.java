package samcom.example.senoirandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class L1ScBoard extends Activity {

@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_l1_sc_board);

Button boardButton = (Button)findViewById(R.id.boardbutton);
boardButton.setOnClickListener(new View.OnClickListener() {


public void onClick(View v) {
// TODO Auto-generated method stub
finish();
}
});
}
}