package dao;

import org.w3c.dom.Text;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

public class PropositionDAO extends SQLiteOpenHelper {

	public static final String TABLE_NAME = "PROPOSITION";
	public static final String IDPROP = "IDPROP";
	public static final String ANOPROP = "ANOPROP";
	public static final String EMENTAPROP = "EMENTAPROP";
	public static final String AUTORPROP = "AUTORPROP";
	/* TIPOPROP é a sigla da proposicao */
	public static final String SIGLAPROP = "SIGLAPROP";
	public static final String NUMEROPROP = "NUMEROPROP";
	public static final String SITUACAOPROP = "SITUACAOPROP";
	
	public static final String CREATE_TABLE_PROPOSITION = 
			"CREATE TABLE " + TABLE_NAME + "(" 
		    + IDPROP + " INTEGER NOT NULL PRIMARY KEY, " 
			+ ANOPROP + " INTEGER NOT NULL, "
			+ EMENTAPROP + " TEXT, "
			+ AUTORPROP + " TEXT, "
			+ SIGLAPROP + " TEXT, "
			+ SITUACAOPROP + " TEXT, "
			+ NUMEROPROP + " TEXT "
			+ ");";
	
	public static final String BANCO_DADOS = "PVMP";
	private static int VERSAO = 1; 

	
	public PropositionDAO(Context context) {
		super(context, BANCO_DADOS, null, VERSAO);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_PROPOSITION);
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	} 
	
		
	
	
	
}
