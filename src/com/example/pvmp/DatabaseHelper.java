package com.example.pvmp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	
	public static final String BANCO_DADOS = "PVMP";
	private static int VERSAO = 1; 
	
	
	
	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, BANCO_DADOS, null, VERSAO);
		// TODO Auto-generated constructor stub
	}

	@Override
	/** Create tables in data, in the first access to the database **/
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE user (_id INTEGER PRIMARY KEY,"+
		"name TEXT" + "mail TEXT" + "idade INTEGER" + "escolaridade TEXT" + "sexo TEXT" + "senha TEXT");
	} 
	
	@Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
