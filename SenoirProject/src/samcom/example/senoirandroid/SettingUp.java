package samcom.example.senoirandroid;

import android.content.Context;
import android.widget.LinearLayout;

public class SettingUp extends LinearLayout{
	   public SettingUp(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	   }
		
		static public int windowW=0;
		static public int windowH=0;
		
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) 
		{
			// we overriding onMeasure because this is where the application gets its right size.
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			windowW = getMeasuredWidth();
			windowH = getMeasuredHeight();
		}
}
