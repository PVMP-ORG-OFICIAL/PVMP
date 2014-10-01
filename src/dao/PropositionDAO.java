package dao;

import org.w3c.dom.Text;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

public class PropositionDAO extends SQLiteOpenHelper {

	public static final String PROP_TABLE_NAME = "PROPOSITION";
	public static final String IDPROP = "IDPROP";
	public static final String ANOPROP = "ANOPROP";
	public static final String EMENTAPROP = "EMENTAPROP";
	public static final String AUTORPROP = "AUTORPROP";
	/* TIPOPROP é a sigla da proposicao */
	public static final String SIGLAPROP = "SIGLAPROP";
	public static final String NUMEROPROP = "NUMEROPROP";
	public static final String SITUACAOPROP = "SITUACAOPROP";
	
	public static final String CREATE_TABLE_PROPOSITION = 
			"CREATE TABLE " + PROP_TABLE_NAME + "(" 
		    + IDPROP + " INTEGER NOT NULL PRIMARY KEY, " 
			+ ANOPROP + " INTEGER NOT NULL, "
			+ EMENTAPROP + " TEXT, "
			+ AUTORPROP + " TEXT, "
			+ SIGLAPROP + " TEXT, "
			+ SITUACAOPROP + " TEXT, "
			+ NUMEROPROP + " TEXT "
			+ ");";
	
	
	public static final String VOT_TABLE_NAME = "VOTING";
	public static final String COD_SESSAO = "CODSESSAO";
	public static final String RESUMO = "RESUMO";
	public static final String DATA_VOTACAO = "DATA_VOTACAO";

	public static final String CREATE_TABLE_VOTATING = 
			"CREATE TABLE " + VOT_TABLE_NAME + "(" 
		    + COD_SESSAO + " INTEGER NOT NULL PRIMARY KEY, " 
			+ RESUMO + " TEXT, "
			+ DATA_VOTACAO + " TEXT, "
			+ IDPROP + " INTEGER, "
			+ "FOREIGN KEY(IDPROP) REFERENCES PROPOSITION(IDPROP) "
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
		db.execSQL(CREATE_TABLE_VOTATING);
	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	} 
	
		
	
	
	
}
