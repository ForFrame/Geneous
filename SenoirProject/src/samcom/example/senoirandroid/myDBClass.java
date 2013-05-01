package samcom.example.senoirandroid;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
	" Age INTEGER,"+" Sex INTEGER,"+" Status INTEGER,"+" Password TEXT(20));");
	//Create Login status table
	db.execSQL("CREATE TABLE "+ TABLE_STATUS +" (No INTEGER PRIMARY KEY AUTOINCREMENT,"+
	" Username TEXT(100),"+" Status INTEGER,"+" Checkbox INTEGER,"+" Home INTEGER,"+" LoginTime Text(20),"+" LogoutTime Text(20));");
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
	int i=0;
	//long rows;
	try{
	
		db = this.getWritableDatabase();
		//String gameName[];
		
		String[] gameName = {"เกมส์มานับโต๊ะกันเถอะ","เกมส์สีประจำวันทั้ง 7 สีมีอะไรบ้างน้า","มารู้จักขนาดสั้น-ยาวกันเถอะ","มารู้จักการใช้ถนนกันเถอะ",
				"ใกล้-ไกลคืออะไรน้า","มารู้จักยานพาหนะประเภทต่างๆกันเถอะ","ควรเลือกรับประทานอาหารอะไรดีน้า","มานับเหรียญกันเถอะ",
				"มารู้จักการตาชั่งกันเถอะ","มารู้จักรูปทรงต่างๆกันเถอะ","มารู้จักความสัมพันธ์ของอาชีพกันเถอะ","มารู้จักอาชีพต่างๆในชุมชนกันน้า",
				"มารู้จักร่างการของเรากันเถอะ","เมื่อเราป่วยควรทำยังไงเอ่ย","แต่ละฤดูต่างกันยังไงน้า","มานับจำนวนสัตว์กันเถอะ","เงานี้เป็นของใครน้า","รู้ไหมเอ่ยว่าสัตว์มีกี่ประเภท"};
		
		int level=1;

		ContentValues Val = new ContentValues();
		for(i=0;i<18;i++){
			if(level>3){
				level = 1;
			}
			Val.put("GameNo", ("00"+(i+1)));
			Val.put("GameName", gameName[i]);
			Val.put("Level", level);
			
			db.insert(TABLE_LEVEL, null, Val);
			level++;
		}
		
		db.close();
	
	} catch (Exception e){
	
	}
	
	// insert admin into user table set(username = admin && password = admin)
	
}

void writeLoginToFile(String user,String dateLN){
	/*** Write Text File to SD Card ***/

	try {

		String path = "/mnt/sdcard/Android/data/login.txt";
		File file = new File(path);
	
		/*** if exists create text file ***/
	
		if(!file.exists()){
			file.createNewFile();
		}
	
		/*** Write Text File ***/
		FileWriter writer = new FileWriter(file, true); //True = Append to file, false = Overwrite
		writer.write("Login -> "+user+" :: "+dateLN+ "\n");
		writer.close();
		
		} catch (IOException e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

void writeLogoutToFile(String user,String dateLN){
	/*** Write Text File to SD Card ***/

	try {

		String path = "/mnt/sdcard/Android/data/login.txt";
		File file = new File(path);
	
		/*** if exists create text file ***/
	
		if(!file.exists()){
			file.createNewFile();
		}
	
		/*** Write Text File ***/
		FileWriter writer = new FileWriter(file, true); //True = Append to file, false = Overwrite
		writer.write("<- Logout  ~ "+user+" :: "+dateLN+ "\n");
		writer.close();
		
		} catch (IOException e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

void writeScoreToFile(String GNo,String user,String Round,String scores,String times){
	/*** Write Text File to SD Card ***/
	
	try {

		String path = "/mnt/sdcard/Android/data/scores.txt";
		File file = new File(path);
	
		/*** if exists create text file ***/
	
		if(!file.exists()){
			file.createNewFile();
		}
	
		/*** Write Text File ***/
		FileWriter writer = new FileWriter(file, true); //True = Append to file, false = Overwrite
		writer.write("\n"+GNo+" "+user+" ( "+Round+" / "+scores+" ) : "+times + "\n");
		writer.write("------------------------------------------------------------- \n");
		writer.close();
		
		} catch (IOException e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

void writeMemberToFile(String user,int age,int sex){
	/*** Write Text File to SD Card ***/
	String sAge = String.valueOf(age);
	String sSex = String.valueOf(sex);
	try {

		String path = "/mnt/sdcard/Android/data/member.txt";
		File file = new File(path);
	
		/*** if exists create text file ***/
	
		if(!file.exists()){
			file.createNewFile();
		}
	
		/*** Write Text File ***/
		FileWriter writer = new FileWriter(file, true); //True = Append to file, false = Overwrite
		writer.write(user+" :: "+sSex+" :: "+sAge+ "\n");
		writer.close();
		
		} catch (IOException e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

void writeGameToFile(String user,int Round,int item,int times,int correct){
	/*** Write Text File to SD Card ***/
	String sRound = String.valueOf(Round);
	String sItem = String.valueOf(item);
	String sTimes = String.valueOf(times);
	String sCorrect = String.valueOf(correct);
	try {

		String path = "/mnt/sdcard/Android/data/scores.txt";
		File file = new File(path);
	
		/*** if exists create text file ***/
	
		if(!file.exists()){
			file.createNewFile();
		}
	
		/*** Write Text File ***/
		FileWriter writer = new FileWriter(file, true); //True = Append to file, false = Overwrite
		writer.write("\t "+user+" | "+sRound+" | "+sItem+" | "+sTimes+" | "+sCorrect+" | "+ "\n");
		writer.close();
		
		} catch (IOException e) {
		
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
/*public Boolean isCurrentContinue(){
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
}*/

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

public Date getLastLoginTime(){
	String loginTime = null;
	Date d = null;
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
	        loginTime = cur.getString(4);
	        cur.moveToNext();
		  }
		
	      cur.close();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			d = sdf.parse(loginTime);
	    
	}catch (Exception e){
		//return false;
	}
		
	return d;
}
//myDb.logoutUser(CurrentUser);
public void logoutUser(String user){
	//String username = null;
	try{
		
		SQLiteDatabase db;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String currentDate = dateFormat.format(new Date());
		
		Date loginTime = getLastLoginTime();
		String Times = loginTime.toString();
		db = this.getWritableDatabase();
				
		ContentValues Val = new ContentValues();
		Val.put("Status", 0);
		Val.put("LogoutTime",currentDate);
		String sqlWhere = "Username = '"+user+"' and LoginTime = '"+loginTime+"'";
		int id = db.update(TABLE_STATUS,Val,sqlWhere,null);
		writeLogoutToFile(user,currentDate);
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

public void insertAdmin(){
	
	Boolean IsExited = checkUserInfo("admin");
	if(!IsExited){
		try{
			SQLiteDatabase db;
			db = this.getWritableDatabase();
			
			ContentValues Val = new ContentValues();
			Val.put("Username", "admin");
			Val.put("Age", 0);
			Val.put("Sex", 0);
			Val.put("Status", 1);
			Val.put("Password", "admin");
			
			long rows = db.insert(TABLE_USER, null, Val);
			
			db.close();
			
			
		} catch (Exception e){
			//return null;
		}
	}
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
		Val.put("LoginTime", datetime);
		Val.put("Home", homee);
		Val.put("Checkbox", continueLoginState);
		
		long rows = db.insert(TABLE_STATUS, null, Val);
		writeLoginToFile(CurrentUser,datetime);
		db.close();
	
	} catch (Exception e){
	
	}
	
	
}

// insert new user into userinfo table
void InsertUser(String CurrentUser,int age,int chooseSex){
	
	Boolean IsExited = false;
	String pass = "-";
	int status = 0;
	if(CurrentUser.equals("admin")){
		status = 1;
		pass = "admin";
		IsExited = checkUserInfo("admin");
	}
	//String sage = age.toString();
	
	
	
	if(!IsExited){
		
		try{
			
			SQLiteDatabase db;
			db = this.getWritableDatabase();
			
			
			ContentValues Val = new ContentValues();
			Val.put("Username", CurrentUser);
			Val.put("Age", age);
			Val.put("Sex", chooseSex);
			Val.put("Status", 0);
			Val.put("Password", pass);
			
			long rows = db.insert(TABLE_USER, null, Val);
			writeMemberToFile(CurrentUser,age,chooseSex);
			db.close();
			
			
		} catch (Exception e){
			//return null;
		}
	}
	
}


/*
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
*/

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
		
		int times = (int)time;
		long rows = db.insert(TABLE_ScItem, null, Val);
		writeGameToFile(user,round,item,times,correct);
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
	int times = 0; 
	try{
		
	    SQLiteDatabase db;
		db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT Sum(Score), Sum(Time) FROM scItem WHERE Username = '"+user+"' and GameNo = '"+GNo+"' and Round = '"+Round+"' ;", null);
	    	
		if(cursor != null){
			if(cursor.moveToFirst()) {
	    	    scoree = cursor.getInt(0);
	    	    times = cursor.getInt(1);
	    	    //scoree = (float) (scoree*5)/ItemNo;
	    	    //keepPlayingScore(GNo,user,Round,scoree,times);
	    	}
		}
		else{
			scoree = 0;
		}
	    
	}catch (Exception e){
		scoree = 0;
	}
	keepPlayingScore(GNo,user,Round,scoree,times);
	return scoree;
}


void keepPlayingScore(String GNo,String user,int Round,int scores,int times){

	String cRound = Integer.toString(Round);
	String cScores = Integer.toString(scores);
	String cTimes = Integer.toString(times);
	writeScoreToFile(GNo,user,cRound,cScores,cTimes);
	try{
		
		SQLiteDatabase db;
		db = this.getWritableDatabase();
		
		ContentValues Val = new ContentValues();
		Val.put("Username", user);
		Val.put("GameNo", GNo);
		Val.put("Round", Round);
		Val.put("AvgScore", scores);
		Val.put("AvgTime", times);
		
		long rows = db.insert(TABLE_ScGame, null, Val);
		
		db.close();
	
	} catch (Exception e){
	
	}
	
	
}

String passAdmin(){
	
	String getPassword = null;
	try{
		
	    SQLiteDatabase db;
		db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT Password FROM userinfo WHERE Username = 'admin' ;", null);
	    	
		if(cursor != null){
			if(cursor.moveToFirst()) {
	    	    getPassword = cursor.getString(0);
	    	  
	    	}
		}
		
	}catch (Exception e){
		//scoree = 0;
	}
	return getPassword;
}

void changePass(String password){
	
	try{
		
	    SQLiteDatabase db;
		db = this.getWritableDatabase();
				
		ContentValues values=new ContentValues();
		values.put("Password",password);
		
		int id = db.update("userinfo",values,"Username='admin'",null);
		
	}catch (Exception e){
		//scoree = 0;
	}
	
}

int getIdvHighScore(String game,String name,String value[][]){
	//String value[][] = new String[15][5];
	int i=0;
	try{
		int totalscore,roundd,min,sec;
		float totaltime;
		String getUsername;
	    SQLiteDatabase db;
		db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT Round, Username, Sum(Score), Sum(Time) FROM scItem WHERE GameNo = '"+game+"'and Username = '"+name+"' Group by Round,Username Order by Round DESC;", null);
		//SELECT Round, Username, Sum(Time), Sum(Score) FROM scItem WHERE GameNo = '001' Group by Round;
	      
		if(cursor != null){
			i=0;
			cursor.moveToFirst();
			while (cursor.isAfterLast() == false) {
				roundd = cursor.getInt(0);
				value[i][0] = String.valueOf(roundd);
				getUsername = cursor.getString(1);
				value[i][1] = "  "+getUsername;
		        totaltime = cursor.getFloat(3);
		        min = (int)totaltime/60;
		        sec = (int)totaltime%60;
		        value[i][3] = String.valueOf(min)+"."+String.valueOf(sec)+" นาที";
		        totalscore = cursor.getInt(2);
		        value[i][2] = String.valueOf(totalscore)+"/10";
		        
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

@SuppressWarnings("null")
int getIdvGraphScore(String game,String name,String[] Round,double[] score){
	
	int i=0;
	
	
	try{
		int roundd,scores;
	    SQLiteDatabase db;
		db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT Round, Sum(Score) FROM scItem WHERE GameNo = '"+game+"'and Username = '"+name+"' Group by Round Order by Round DESC;", null);
		//SELECT Round, Username, Sum(Time), Sum(Score) FROM scItem WHERE GameNo = '001' Group by Round;
	   i = 0;
		if(cursor != null){
			
			cursor.moveToFirst();
			while (i < 5) {
				roundd = cursor.getInt(0);
				Round[i] = String.valueOf(roundd);
				scores = cursor.getInt(1);
				double ansNum = scores;
				score[i] = ansNum;
				i++;
		        cursor.moveToNext();
		       
			}
			cursor.close();
		}
	    
	}catch (Exception e){
		//i = 0;
	}
	return i;
}

int getGameHighScore(String game,String value[][]){
	//String value[][] = new String[15][5];
	int i=0;
	try{
		int totalscore,min,sec;
		float totaltime;
		String getUsername;
	    SQLiteDatabase db;
		db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT Round, Username, Sum(Time), Sum(Score) FROM scItem WHERE GameNo = '"+game+"' Group by Username, Round Order by Sum(Score) DESC , Sum(Time);", null);
		//SELECT Round, Username, Sum(Time), Sum(Score) FROM scItem WHERE GameNo = '001' Group by Round;
	      
		if(cursor != null){
			i=0;
			cursor.moveToFirst();
			while (cursor.isAfterLast() == false) {
				getUsername = cursor.getString(1);
				value[i][0] = "  "+getUsername;
		        totaltime = cursor.getFloat(2);
		        min = (int)totaltime/60;
		        sec = (int)totaltime%60;
		        value[i][1] = String.valueOf(min)+"."+String.valueOf(sec)+" นาที";
		        totalscore = cursor.getInt(3);
		        value[i][2] = String.valueOf(totalscore)+"/10";
		        
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

/*void deleteGuest(){
	try{
		
		SQLiteDatabase db;
		db = this.getWritableDatabase();
		
		db.delete(TABLE_ScItem, "Username = 'Guest'", null);
		
		db.close();
	
	} catch (Exception e){
	
	}
}*/
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//TODO Auto-generated method stub

}

}
