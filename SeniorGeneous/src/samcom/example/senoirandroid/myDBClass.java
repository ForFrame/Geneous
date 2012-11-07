package samcom.example.senoirandroid;

import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteCursor;
//import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
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
	" Age INTEGER,"+" Sex INTEGER);");
	//Create Login status table
	db.execSQL("CREATE TABLE "+ TABLE_STATUS +" (No INTEGER PRIMARY KEY AUTOINCREMENT,"+
	" Username TEXT(100),"+" Status INTEGER,"+" Checkbox INTEGER,"+" Date TEXT(30));");
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
	
	
	try{
		
		String username = "Logout";
		int statuslogin = 0;
		int checkContinue = 0;
		SQLiteDatabase db;
		db = this.getReadableDatabase();
		SQLiteCursor cur = (SQLiteCursor)db.rawQuery("select * from loginStatus",null);
		 cur.moveToFirst();
		
		  while (cur.isAfterLast() == false) {
			  username = cur.getString(2);
			  statuslogin = cur.getInt(3);
			  checkContinue = cur.getInt(4);

		      cur.moveToNext();
		  }
		  cur.close();

				
		 if((checkContinue == 1)&&(statuslogin == 1)||(username.equals("Guest"))){
	        	return username;
	     }
		 
		/*try{
		//String userinfo[] = null;
		
		SQLiteDatabase db;
		db = this.getReadableDatabase();
		
		Cursor c = db.rawQuery("SELECT MAX(No) as No,Username,Status,Checkbox" +
                   " FROM " + TABLE_STATUS+ " ;", null);
		//Cursor cursor = db.query(TABLE_STATUS, new String[] {"*"}, ""
		  Get the indices of the Columns we will need 
        
        int UsernameColumn = c.getColumnIndex("Username");
        int StatusColumn = c.getColumnIndex("Status");
        int CheckColumn = c.getColumnIndex("Checkbox");
       
        username = c.getString(UsernameColumn);
        int Status = c.getInt(StatusColumn);
        int Check = c.getInt(CheckColumn);
                
        
        if((Check == 1)&&(Status == 1)||(username.equals("Guest"))){
        	return username;
        }
              
         
	} */
	}catch (Exception e){
			return "Logout";
	}
	
	return "Logout";
}
	
//myDb.logoutUser(CurrentUser);
public void logoutUser(String user){
	//String username = null;
	try{
		//String userinfo[] = null;
		
		SQLiteDatabase db;
		db = this.getWritableDatabase();
		
		// insertCmd ;
		String strSQL = "UPDATE " + TABLE_STATUS + " SET Status = 0 WHERE Status == 1";
		
		SQLiteStatement insertCmd = db.compileStatement(strSQL);
		
		//long check = insertCmd.executeUpdateDelete();
		
		db.close();
		//Cursor cursor = db.query(TABLE_STATUS, new String[] {"*"}, ""
	} catch (Exception e){
		//return null;
	}
	
	//return username;
}

//check user info if got -> insert status table(name,date) ,no -> message Toast 
//checkUser = myDb.checkUserInfo(username);
public Boolean checkUserInfo(String name){
	//Boolean username = null;
	try{
		String userinfo[] = null;
		
		SQLiteDatabase db;
		db = this.getReadableDatabase();
		Cursor c = db.query(TABLE_USER, new String[] {"*"}, "Username=?", new String[] { String.valueOf(name) }, null, null, null, null);
		
		//Cursor c = db.Query("SELECT *" + " FROM " + TABLE_USER+ " WHERE Username == " + name + " ;", null);
		//Cursor cursor = db.query(TABLE_STATUS, new String[] {"*"}, ""
		
		if(c != null){
			if(c.moveToFirst()){
				userinfo = new String[c.getColumnCount()];
				userinfo[0] = c.getString(0);
				
			}
			c.close();
			db.close();
			if(userinfo[0].equals(name)){
				return true;
			}
			else{
				return false;
			}
			
		}
	} catch (Exception e){
		return false;
	}
	
	return false;
}

//myDb.InsertCurrent(CurrentUser,d,continueLoginState);
//INSERT INTO loginStatus (No,Username,Status,Date,Checkbox) VALUES (2,"Pond",'Y',null,0);
public void InsertCurrent(String CurrentUser,Date d,int continueLoginState){
	
	try{
	
		
		SQLiteDatabase db;
		db = this.getWritableDatabase();
		String datetime = d.toString();
		int state;
		if(CurrentUser.equals("Guest")){
			state = 0;
		}
		else{
			state = 1;
		}
		
		
		/*ContentValues Val = new ContentValues();
		Val.put("Username", CurrentUser);
		Val.put("Status", state);
		Val.put("Date", datetime);
		Val.put("Checkbox", continueLoginState);
		
		long rows = db.insert(TABLE_STATUS, null, Val);*/
				
		String sql ="insert into loginStatus values(null,'"+CurrentUser+"','"+state+"','"+continueLoginState+"',"+datetime+")";
		db.execSQL(sql);

		//db.close();
	
	} catch (Exception e){
	
	}
	
	
}

//yDb.InsertUser(CurrentUser,age,chooseSex);
public void InsertUser(String CurrentUser,int age,int chooseSex){
	
	try{
	
		
		SQLiteDatabase db;
		db = this.getWritableDatabase();
		
		
		ContentValues Val = new ContentValues();
		Val.put("Username", CurrentUser);
		Val.put("Age", age);
		Val.put("Sex", chooseSex);
		
		long rows = db.insert(TABLE_USER, null, Val);
		
		db.close();
		
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