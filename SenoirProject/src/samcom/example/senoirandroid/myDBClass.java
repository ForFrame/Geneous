package samcom.example.senoirandroid;

import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteCursor;
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
private static final String TABLE_GAME001 = "Game001";

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
	" Username TEXT(100),"+" Status INTEGER,"+" Checkbox INTEGER,"+" Home INTEGER,"+" Date TEXT(30));");
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
	db.execSQL("CREATE TABLE "+ TABLE_ScItem +" (No INTEGER PRIMARY KEY,"+
	" Username TEXT(100), "+" GameNo TEXT(5), "+"Round INTEGER, "+"Item INTEGER, "+
	"Score INTEGER, "+"Time FLOAT);");
	//Create score of each game table
	db.execSQL("CREATE TABLE "+ TABLE_ScGame +" (No INTEGER PRIMARY KEY AUTOINCREMENT,"+
	" Username TEXT(100), "+" GameNo TEXT(5), "+"Round INTEGER, "+
	"AvgScore FLOAT, "+"AvgTime FLOAT);");
	//Create Personal high score table
	db.execSQL("CREATE TABLE "+ TABLE_PhighScore +" (No INTEGER PRIMARY KEY AUTOINCREMENT,"+
	" Username TEXT(100), "+" GameNo TEXT(5), "+"HighScore FLOAT);");
	//Create data into game001 (Count table) level1 table
	db.execSQL("CREATE TABLE "+ TABLE_GAME001 +" (No INTEGER PRIMARY KEY ,"+
	"RanNum INTEGER);");
	
	Log.d("CREATE TABLE","Create Table Successfully.");
}

// select current user -> return username / guest
public String SelectCurrentUser(){
	String username = "Logout";
	
	
	try{
			
	    SQLiteDatabase db;
		
	    db = this.getReadableDatabase();
		
	    SQLiteCursor cur = (SQLiteCursor)db.rawQuery("select * from loginStatus",null);
		cur.moveToFirst();
		
	    while (cur.isAfterLast() == false) {
		    username = cur.getString(1);
	        cur.moveToNext();
	    }
		
	    cur.close();

	}catch (Exception e){
		return "Logout";
	}
	
	return username;
}

//change home state if 1 = from home button , 0 = from restart state
public void ChangeHome(int homec){
	try{
				
		SQLiteDatabase db;
		db = this.getWritableDatabase();
				
		ContentValues Val = new ContentValues();
		Val.put("Home", homec);
		
		if(homec == 1){
			int id = db.update(TABLE_STATUS,Val,"Home = '0'",null);
		}
		else{
			int id = db.update(TABLE_STATUS,Val,"Home = '1'",null);
		}
		
		db.close();
		
	} catch (Exception e){
		
	}
}
//check (1,1) -> true
public Boolean isCurrentContinue(){
	Boolean boo=false;
	try{
		
	 	
	    int statuslogin = 0;
		int checkContinue = 0;
		
	    SQLiteDatabase db;
		db = this.getReadableDatabase();
		SQLiteCursor cur = (SQLiteCursor)db.rawQuery("select * from loginStatus",null);
		cur.moveToFirst();
		
	      while (cur.isAfterLast() == false) {
		
	        statuslogin = cur.getInt(2);
	        checkContinue = cur.getInt(3);
	        cur.moveToNext();
		  }
		
	      cur.close();
			
	     if((checkContinue == 1)&&(statuslogin == 1)){
	            boo = true;
		
	       }
	}catch (Exception e){
		return false;
	}
	return boo;
}

// check if from home or restart state
public Boolean notFromHome(){
	Boolean boo=false;
	int summ = 0;
	try{
		
	    SQLiteDatabase db;
		db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT SUM(Home) FROM loginStatus", null);
	    	if(cursor.moveToFirst()) {
	    	    summ = cursor.getInt(0);
	    	}
	    
	}catch (Exception e){
		summ = 2;
	}
	if(summ == 0){
        boo = true;
	}
	return boo;
}
	
//myDb.logoutUser(CurrentUser);
public void logoutUser(String user){
	//String username = null;
	try{
		
		SQLiteDatabase db;
		db = this.getWritableDatabase();
				
		ContentValues Val = new ContentValues();
		Val.put("Status", 0);
		
		int id = db.update(TABLE_STATUS,Val,"Status = '1'",null);
		
		db.close();
		
	} catch (Exception e){
		
	}

}

//if username same as in table return true/if not return false
public Boolean checkUserInfo(String name){
	Boolean userSame = false;
	try{
		String userinfo[] = null;
		
		SQLiteDatabase db;
		db = this.getReadableDatabase();
		Cursor c = db.query(TABLE_USER, new String[] {"*"}, "Username=?", new String[] { String.valueOf(name) }, null, null, null, null);
		
		if(c != null){
			if(c.moveToFirst()){
				userinfo = new String[c.getColumnCount()];
				userinfo[0] = c.getString(0);
				
			}
			c.close();
			db.close();
			if(userinfo[0].equals(name)){
				userSame = true;
			}
			
		}
	} catch (Exception e){
		return false;
	}
	
	return userSame;
}

// insert user into status table
public void InsertCurrent(String CurrentUser,Date d,int continueLoginState){
	
	try{
	
		
		SQLiteDatabase db;
		db = this.getWritableDatabase();
		String datetime = d.toString();
		int state;
		int homee = 0;
		//if(CurrentUser.equals("Guest")){
		//	state = 0;
		//}
		//else{
			state = 1;
			if(continueLoginState == 1){
				homee = 1;
			}
		//}
		ContentValues Val = new ContentValues();
		Val.put("Username", CurrentUser);
		Val.put("Status", state);
		Val.put("Date", datetime);
		Val.put("Home", homee);
		Val.put("Checkbox", continueLoginState);
		
		long rows = db.insert(TABLE_STATUS, null, Val);
		
		db.close();
	
	} catch (Exception e){
	
	}
	
	
}

// insert new user into userinfo table
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
		
		
	} catch (Exception e){
		//return null;
	}
	
}

//add Game name
void addGameNo(String GNo,String GName,int Glevel){

	try{
	
		
		SQLiteDatabase db;
		db = this.getWritableDatabase();
		
		ContentValues Val = new ContentValues();
		Val.put("GameNo", GNo);
		Val.put("GameName", GName);
		Val.put("Level", Glevel);
		
		long rows = db.insert(TABLE_LEVEL, null, Val);
		
		db.close();
	
	} catch (Exception e){
	
	}
}


//-------------------------------------GAME001 Count Table Level 1 Mode: School-------------------------------------
// count number in table random
int CountNumRan(){

	int num = 0;
	try{
		
	    SQLiteDatabase db;
		db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_GAME001+" ;", null);
	    if(cursor != null){
			if(cursor.moveToFirst()) {
	    	    num = cursor.getCount();
	    	}
			
	    }
	    else{
	    	num = 0;
	    }
	    
	}catch (Exception e){
		num = 0;
	}
	
	return num;
	
}

//get last random number from game001 table
int getLastNum(int end){
	int indexLast;
	int RanNum = 0;
	try{
		
	    SQLiteDatabase db;
		db = this.getReadableDatabase();
		indexLast = CountNumRan();
		if(indexLast > 1){
			indexLast--;
			if(end == 10){
				indexLast = 10;
			}
			//Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_GAME001+" WHERE No = "+(Integer.toString(indexLast))+";", null);
			Cursor cursor = db.rawQuery("SELECT No, RanNum FROM "+TABLE_GAME001+" WHERE No = '"+indexLast+"' ;", null);
				if(cursor.moveToFirst()) {
		    	    RanNum = cursor.getInt(1);
		    	}
		}
		
	    
	}catch (Exception e){
		RanNum = 0;
	}
	
	return RanNum;
}

//empty all row in table
void emptyNumberTable(){
	
	try{
		
	    SQLiteDatabase db;
		db = this.getWritableDatabase();
		
		String deleteSQL = "DELETE FROM "+ TABLE_GAME001 +" ;";
		db.execSQL(deleteSQL);
	    
	}catch (Exception e){
		//RanNum = 0;
	}
	
}
//check random number is exited?? -> return true, false if not exited
Boolean checkNumber(int RandomNo){
	Boolean found = false;
	try{
		
	    SQLiteDatabase db;
		db = this.getReadableDatabase();
		int equal = 0;
		Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_GAME001+" WHERE RanNum = "+Integer.toString(RandomNo)+" ;", null);
		
		if(cursor != null){
	
			if(cursor.moveToFirst()){
				 equal = cursor.getInt(1);
			}
			cursor.close();
			db.close();
			if(RandomNo == equal){
				found = true;
			}
		}	
		
	  }catch(Exception e){
			found = true;
	  }
	
	return found;	
}
//add random number into game001 table
void insertRanNumber(int RandomNum){

	try{
	
		int Nos = CountNumRan();
		SQLiteDatabase db;
		db = this.getWritableDatabase();
		
		ContentValues Val = new ContentValues();
		Val.put("RanNum", RandomNum);
		Val.put("No", Nos+1);
		
		long rows = db.insert(TABLE_GAME001, null, Val);
		
		db.close();
	
	} catch (Exception e){
	
	}
}

//set score for popup
//myDb.addItemScore(001,username,1);

void addItemScore(String GNo,String user,int round,int item,int correct,float time){
	//int round = getNumRound(GNo,user);
	
	try{
			
		SQLiteDatabase db;
		db = this.getWritableDatabase();
		
		ContentValues Val = new ContentValues();
		Val.put("Username", user);
		Val.put("GameNo", GNo);
		Val.put("Round", round);
		Val.put("Item", item);
		Val.put("Score", correct);
		Val.put("Time", time);
		
		long rows = db.insert(TABLE_ScItem, null, Val);
		
		db.close();
	
	} catch (Exception e){
	
	}
}
int getNumRound(String GNo,String user){
	
	int round = 0;
	//if(user.equals("Guest")){
	//	round = 1;
	//}
	//else{
		try{
			
		    SQLiteDatabase db;
			db = this.getReadableDatabase();
			Cursor cursor = db.rawQuery("SELECT Round FROM scItem WHERE Username = '"+user+"' and GameNo = '"+GNo+"' ;", null);
		    	
			
			if(cursor != null){
				cursor.moveToFirst();
				
			      while (cursor.isAfterLast() == false) {
			    	  round = cursor.getInt(0);
			    	  cursor.moveToNext();
				  }
			    round++;
			}
			else{
				round = 1;
			}
		    
		}catch (Exception e){
			round = 0;
		}
	//}
	return round;
}

//count score
int countScore(String GNo,String user,int Round){
	
	int scoree = 0;
	try{
		
	    SQLiteDatabase db;
		db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT Sum(Score) FROM scItem WHERE Username = '"+user+"' and GameNo = '"+GNo+"' and Round = '"+Round+"' ;", null);
	    	
		if(cursor != null){
			if(cursor.moveToFirst()) {
	    	    scoree = cursor.getInt(0);
	    	    //scoree = (float) (scoree*5)/ItemNo;
	    	}
		}
		else{
			scoree = 0;
		}
	    
	}catch (Exception e){
		scoree = 0;
	}
	return scoree;
}

//@SuppressWarnings("null")
int getGameHighScore(String game,String value[][]){
	//String value[][] = new String[15][5];
	int i=0;
	try{
		int totalscore;
		float totaltime;
		String getUsername;
	    SQLiteDatabase db;
		db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT Round, Username, Sum(Time), Sum(Score) FROM scItem WHERE GameNo = '"+game+"' Group by Round Order by Sum(Score) DESC , Sum(Time);", null);
		//SELECT Round, Username, Sum(Time), Sum(Score) FROM scItem WHERE GameNo = '001' Group by Round;
	      
		if(cursor != null){
			i=0;
			cursor.moveToFirst();
			while (cursor.isAfterLast() == false) {
				getUsername = cursor.getString(1);
				value[i][0] = getUsername;
		        totaltime = cursor.getFloat(2);
		        value[i][1] = String.valueOf(totaltime/10);
		        totalscore = cursor.getInt(3);
		        value[i][2] = String.valueOf(totalscore);
		        
		        cursor.moveToNext();
		        i++;
			}
			cursor.close();
		}
		else{
			i = 0;
		}
	    
	}catch (Exception e){
		i = 0;
	}
	return i;
}

void deleteGuest(){
	try{
		
		SQLiteDatabase db;
		db = this.getWritableDatabase();
		
		db.delete(TABLE_ScItem, "Username = 'Guest'", null);
		
		db.close();
	
	} catch (Exception e){
	
	}
}
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//TODO Auto-generated method stub

}

}
