package samcom.example.senoirandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.content.Intent;

public class Main extends Activity {
/** Called when the activity is first created. */
@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);

Button swapPoliceButton1 = (Button)findViewById(R.id.maintopolice1);

swapPoliceButton1.setOnClickListener(new View.OnClickListener() {
	 

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Main.this,PoliceLevel1.class);
		startActivity(intent);
	}
});

Button swapMarketButton1 = (Button)findViewById(R.id.maintomarket1);

swapMarketButton1.setOnClickListener(new View.OnClickListener() {
 

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Main.this,MarketLevel1.class);
		startActivity(intent);
	}
});

Button swapHouseButton1 = (Button)findViewById(R.id.maintohouse1);

swapHouseButton1.setOnClickListener(new View.OnClickListener() {
	 

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Main.this,HouseLevel1.class);
		startActivity(intent);
	}
});

Button swapSchoolButton = (Button)findViewById(R.id.maintoschool);

swapSchoolButton.setOnClickListener(new View.OnClickListener() {
	 

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Main.this,SelectSchoolLevel.class);
		startActivity(intent);
	}
});

Button swapFarmButton1 = (Button)findViewById(R.id.maintofarm1);

swapFarmButton1.setOnClickListener(new View.OnClickListener() {
	 

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Main.this,FarmLevel1.class);
		startActivity(intent);
	}
});


Button swapHospitalButton1 = (Button)findViewById(R.id.maintohospital1);

swapHospitalButton1.setOnClickListener(new View.OnClickListener() {
	 

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(Main.this,HospitalLevel1.class);
		startActivity(intent);
	}
});







}
}