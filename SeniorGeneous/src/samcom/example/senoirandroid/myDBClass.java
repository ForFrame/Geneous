package samcom.example.senoirandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class myDBClass extends SQLiteOpenHelper{

private static final int DATABASE_VERSION = 1;
private static final String DATABASE_NAME = "geneousdatabase";
private static final String TABLE_USER = "userinfo";

public myDBClass(Context context) {
super(context, DATABASE_NAME, null, DATABASE_VERSION);
//TODO Auto-generated constructor stub
}

@Override
public void onCreate(SQLiteDatabase db) {
//TODO Auto-generated method stub
//Create User Info table
db.execSQL("CREATE TABLE "+ TABLE_USER +" (Username TEXT(100) PRIMARY KEY,"+
" Age INTEGER,"+" Sex CHARECTER);");

Log.d("CREATE TABLE","Create Table Successfully.");
}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//TODO Auto-generated method stub

}

}