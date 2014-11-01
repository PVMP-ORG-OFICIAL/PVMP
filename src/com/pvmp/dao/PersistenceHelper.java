package com.pvmp.dao;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PersistenceHelper extends SQLiteOpenHelper 
{

	public static final String DATABASE_NAME = "PVMPdatabase";
	public static final int VERSION = 1;
	public static final String DATABASE_DIR = "/data/data/com.pvmp/databases/";
	private static Context context;
	private static int gambira;
	
	private static PersistenceHelper instance = null;
	
	private PersistenceHelper(Context _context) 
	{
		super(_context, DATABASE_NAME, null, VERSION);
		context = _context;
	}


	public static void createDatabase()
	{
		if (gambira == 0)
		{
			try 
			{
			  copyDatabase(context);
			  gambira++;
			}
			 catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


  public static void copyDatabase(Context context) throws IOException
  {
      Log.d("entrouuuu","entrouu");
      int length;
      InputStream myInput;
      myInput = context.getAssets().open(DATABASE_NAME);
      String moveBDtoDir = DATABASE_DIR + DATABASE_NAME;
      OutputStream moveLikeStream = new FileOutputStream(moveBDtoDir);
      byte[] buffer = new byte[1024];

      while ((length = myInput.read(buffer)) > 0)
      {
    	Log.d("log","size + " + length);
        moveLikeStream.write(buffer,0,length);
      }

      moveLikeStream.flush();
      moveLikeStream.close();
      myInput.close();
  }

	
	public static PersistenceHelper getInstance(Context context) 
	{
		if(instance == null)
		{
			gambira = 0;
			instance = new PersistenceHelper(context.getApplicationContext());
		}
		return instance;
	}

	
	public void onCreate(SQLiteDatabase db) 
	{
		//createDatabase();
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		//db.execSQL(UserDAO.SCRIPT_TABLE_DELETION);
		//onCreate(db);
	}
}
