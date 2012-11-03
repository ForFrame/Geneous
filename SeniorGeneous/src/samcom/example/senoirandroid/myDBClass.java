package samcom.example.senoirandroid;

import java.util.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class myDBClass extends SQLiteOpenHelper{

private static final int DATABASE_VERSION = 1;
private static final String DATABASE_NAME = "geneousdatabase";
private static final String TABLE_USER = "userinfo";
private static final String TABLE_STATUS = "loginStatus";
private static final String TABLE_LEVEL = "level";
private static final String TABLE_HighScore = "highScore";
private static final String TABLE_Dev = "dev";
private static final String TABLE_ScItem = "scItem";
private static final String TABLE_ScGame = "scGame";
private static final String TABLE_PhighScore = "PhighScore";

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
	//Create Login status table
	db.execSQL("CREATE TABLE "+ TABLE_STATUS +" (No INTEGER PRIMARY KEY AUTOINCREMENT,"+
	" Username TEXT(100),"+" Status CHARECTER,"+" Checkbox BOOLEAN,"+" Date DATETIME);");
	//Create List of Game table
	db.execSQL("CREATE TABLE "+ TABLE_LEVEL +" (GameNo TEXT(5) PRIMARY KEY,"+
	" Gamename TEXT(100), "+"Level INTEGER);");
	//Create High score for each game table 
	db.execSQL("CREATE TABLE "+ TABLE_HighScore +" (GameNo TEXT(5) PRIMARY KEY,"+
	" GameHighSc FLOAT,"+"GotHigh TEXT(100));");
	//Create Development of each child table
	db.execSQL("CREATE TABLE "+ TABLE_Dev +" (No INTEGER PRIMARY KEY AUTOINCREMENT,"+
	" Username TEXT(100),"+" GameNo TEXT(5),"+"Dev INTEGER);");
	//Create score of each item table
	db.execSQL("CREATE TABLE "+ TABLE_ScItem +" (No INTEGER PRIMARY KEY AUTOINCREMENT,"+
	" Username TEXT(100), "+" GameNo TEXT(5), "+"Round INTEGER, "+"Item INTEGER, "+
	"Score FLOAT, "+"Time FLOAT);");
	//Create score of each game table
	db.execSQL("CREATE TABLE "+ TABLE_ScGame +" (No INTEGER PRIMARY KEY AUTOINCREMENT,"+
	" Username TEXT(100), "+" GameNo TEXT(5), "+"Round INTEGER, "+
	"AvgScore FLOAT, "+"AvgTime FLOAT);");
	//Create Personal high score table
	db.execSQL("CREATE TABLE "+ TABLE_PhighScore +" (No INTEGER PRIMARY KEY AUTOINCREMENT,"+
	" Username TEXT(100), "+" GameNo TEXT(5), "+"HighScore FLOAT);");
	
	Log.d("CREATE TABLE","Create Table Successfully.");
}

//SelectCurrentUser(); check MAX(No)loginStatus table on Status == 'Logout'-> No or 'Y' -> name 
//CurrentUser = myDb.SelectCurrentUser();
public String SelectCurrentUser(){
	String username = null;
	try{
		String userinfo[] = null;
		
		SQLiteDatabase db;
		db = this.getReadableDatabase();
		
		//Cursor cursor = db.query(TABLE_STATUS, new String[] {"*"}, ""
	} catch (Exception e){
		return null;
	}
	
	return username;
}
	
//myDb.logoutUser(CurrentUser);
public void logoutUser(String user){
	String username = null;
	try{
		String userinfo[] = null;
		
		SQLiteDatabase db;
		db = this.getReadableDatabase();
		
		//Cursor cursor = db.query(TABLE_STATUS, new String[] {"*"}, ""
	} catch (Exception e){
		//return null;
	}
	
	//return username;
}

//check user info if got -> insert status table(name,date) ,no -> message Toast 
//checkUser = myDb.checkUserInfo(username);
public Boolean checkUserInfo(String name){
	Boolean username = null;
	try{
		String userinfo[] = null;
		
		SQLiteDatabase db;
		db = this.getReadableDatabase();
		
		//Cursor cursor = db.query(TABLE_STATUS, new String[] {"*"}, ""
	} catch (Exception e){
		return null;
	}
	
	return username;
}

//myDb.InsertCurrent(CurrentUser,d,continueLoginState);
public void InsertCurrent(String CurrentUser,Date d,Boolean continueLoginState){
	String username = null;
	try{
		String userinfo[] = null;
		
		SQLiteDatabase db;
		db = this.getReadableDatabase();
		
		//Cursor cursor = db.query(TABLE_STATUS, new String[] {"*"}, ""
	} catch (Exception e){
		//return null;
	}
	
	//return username;
}

//yDb.InsertUser(CurrentUser,age,chooseSex);
public void InsertUser(String CurrentUser,int age,char chooseSex){
	String username = null;
	try{
		String userinfo[] = null;
		
		SQLiteDatabase db;
		db = this.getReadableDatabase();
		
		//Cursor cursor = db.query(TABLE_STATUS, new String[] {"*"}, ""
	} catch (Exception e){
		//return null;
	}
	
	//return username;
}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//TODO Auto-generated method stub

}

}