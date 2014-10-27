package parser;

import java.util.ArrayList;

import model.Deputy;
import model.Party;
import model.Proposition;
import model.Vote;
import model.Voting;

import org.w3c.dom.Text;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

public class DatabaseInterface extends SQLiteOpenHelper 
{

	public static final String PROP_TABLE_NAME = "PROPOSITION";
	public static final String IDPROP = "IDPROP";
	public static final String ANOPROP = "ANOPROP";
	public static final String EMENTAPROP = "EMENTAPROP";
	public static final String AUTORPROP = "AUTORPROP";
	/* TIPOPROP é a sigla da proposicao */
	public static final String SIGLAPROP = "SIGLAPROP";
	public static final String NUMEROPROP = "NUMEROPROP";
	public static final String SITUACAOPROP = "SITUACAOPROP";

	public static final String CREATE_PROPPOSITION_TABLE = 
			"CREATE TABLE " + PROP_TABLE_NAME + "(" 
					+ IDPROP + " INTEGER NOT NULL PRIMARY KEY, " 
					+ ANOPROP + " INTEGER NOT NULL, "
					+ EMENTAPROP + " TEXT, "
					+ AUTORPROP + " TEXT, "
					+ SIGLAPROP + " TEXT, "
					+ SITUACAOPROP + " TEXT, "
					+ NUMEROPROP + " TEXT "
					+ ");";


	public static final String VOTING_TABLE_NAME = "VOTING";
	public static final String COD_SESSAO = "CODSESSAO";
	public static final String RESUMO = "RESUMO";
	public static final String DATA_VOTACAO = "DATA_VOTACAO";

	public static final String CREATE_VOTATING_TABLE = 
			"CREATE TABLE " + VOTING_TABLE_NAME + "(" 
					+ COD_SESSAO + " INTEGER NOT NULL PRIMARY KEY, " 
					+ RESUMO + " TEXT, "
					+ DATA_VOTACAO + " TEXT, "
					+ IDPROP + " INTEGER, "
					+ "FOREIGN KEY(IDPROP) REFERENCES PROPOSITION(IDPROP) "
					+ ");";



	public static final String PARTY_TABLE_NAME = "PARTY";
	public static final String SIGLA = "SIGLA";
	public static final String NUMERO = "NUMERO";
	public static final String CREATE_PARTY_TABLE = 
			"CREATE TABLE " + PARTY_TABLE_NAME + "(" 
					+ NUMERO + " INTEGER NOT NULL PRIMARY KEY, " 
					+ SIGLA + " TEXT "
					+ ");";
	public static final String DEPUTY_TABLE_NAME = "DEPUTY";
	public static final String NOME = "NOME";
	public static final String UF = "UF";
	public static final String IDCADASTRO = "IDCADASTRO";
	public static final String NUMPARTIDO = "NUMPARTIDO";
	public static final String CREATE_DEPUTY_TABLE = 
			"CREATE TABLE " + DEPUTY_TABLE_NAME + "(" 
					+ IDCADASTRO + " INTEGER NOT NULL PRIMARY KEY, " 
					+ UF + " TEXT, "
					+ NOME + " TEXT, "
					+ NUMPARTIDO + " INTEGER NOT NULL, "
					+"FOREIGN KEY(NUMPARTIDO) REFERENCES PARTY(NUMERO) "
					+ ");";

	public static final String VOTE_TABLE_NAME = "VOTE";
	public static final String VOTO = "VOTO";
	public static final String CREATE_VOTE_TABLE = 
			"CREATE TABLE " + VOTE_TABLE_NAME + "(" 
					+ VOTO + " TEXT, " 
					+ COD_SESSAO + " INTEGER, "
					+ IDCADASTRO + " INTEGER, "
					+ "FOREIGN KEY(CODSESSAO) REFERENCES VOTING(CODSESSAO), "
					+ "FOREIGN KEY(IDCADASTRO) REFERENCES DEPUTY(IDCADASTRO) "
					+ ");";



	public static final String BANCO_DADOS = "PVMP";
	private static int VERSAO = 1; 


	public DatabaseInterface(Context context) 
	{
		super(context, BANCO_DADOS, null, VERSAO);
		// TODO Auto-generated constructor stub
	}


  @Override
  public void onCreate(SQLiteDatabase db) 
  {
    db.execSQL(CREATE_PARTY_TABLE);
    db.execSQL(CREATE_PROPPOSITION_TABLE);
    db.execSQL(CREATE_VOTATING_TABLE);
    db.execSQL(CREATE_VOTE_TABLE);
    db.execSQL(CREATE_DEPUTY_TABLE);
  }


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
  {
		// TODO Auto-generated method stub

	} 

	public static void saveProp(ArrayList<Proposition> propList,ArrayList<Voting> votingList,Context context) 
  {

    saveVoting(votingList, propList.get(0).getIdProp(),context);
    DatabaseInterface helper = new DatabaseInterface(context);
    SQLiteDatabase db = helper.getWritableDatabase();
    ContentValues values = new ContentValues();
    for(int i = 0; i < propList.size(); i++) {
      Cursor c = db.rawQuery(
          "SELECT * FROM PROPOSITION WHERE  IDPROP= ' "
          + propList.get(i).getIdProp() + "'", null);
      Log.d("ENTROU2", "ENTROU2");
      if (c.getCount() == 0) {
        Log.d("ENTROU", "ENTROU");
        values.put("IDPROP", propList.get(i).getIdProp());
        values.put("ANOPROP", propList.get(i).getYearProp());
        values.put("AUTORPROP", propList.get(i).getAuthorProp());
        values.put("EMENTAPROP", propList.get(i).getMenuProp());
        values.put("SIGLAPROP", propList.get(i).getAccProp());
        values.put("NUMEROPROP", propList.get(i).getNumProp());
        values.put("SITUACAOPROP", propList.get(i).getSituationProp());
        long log_res = db.insert("PROPOSITION", null, values);
        if (log_res != -1) {
          Log.d("prop ", "Proposição salva");
        } else {
          Log.d("prop ", "Proposição não salva");
        }
      }
    }
    db.close();
  }


	public static void saveVoting(ArrayList<Voting> votList, Integer idProp, Context context) {

		saveDeputy(votList.get(0).getDeputy(),votList.get(0).getVote(), context);
		DatabaseInterface helper = new DatabaseInterface(context);
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		for (int i = 0; i < votList.size(); i++) {
			Cursor c = db.rawQuery(
					"SELECT * FROM VOTING WHERE CODSESSAO = ' "
							+ votList.get(i).getCodSessionVot() + "'", null);
			if (c.getCount() == 0) {
				values.put("DATA_VOTACAO", votList.get(i).getDateVot());
				values.put("RESUMO", votList.get(i).getSummaryVot());
				values.put("CODSESSAO", votList.get(i).getCodSessionVot());
				values.put("IDPROP", idProp);
				long log_res = db.insert("VOTING", null, values);
				if (log_res != -1) {
					Log.d("prop ", "Votação salva");
				} else {
					Log.d("prop ", "Votação não salva");
				}
			}
		}
		db.close();
	}

	public static void saveDeputy(ArrayList<Deputy> deputy,ArrayList<Vote> voteList, Context context){
		DatabaseInterface helper = new DatabaseInterface(context);
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		for(int i = 0; i < deputy.size(); i++){
			Cursor c = db.rawQuery(
					"SELECT * FROM DEPUTY WHERE IDCADASTRO = ' "
							+ deputy.get(i).getIdRegistrtion() + "'", null);
			if (c.getCount() == 0) {
				values.put("IDCADASTRO", deputy.get(i).getIdRegistrtion());
				values.put("UF", deputy.get(i).getUf());
				values.put("NOME", deputy.get(i).getName());
				Cursor partyID = db.rawQuery("SELECT NUMERO FROM PARTY WHERE SIGLA = '" 
						+ deputy.get(i).getPartyName() + "'",null);
				if(partyID.moveToFirst()){
					values.put("NUMPARTIDO", partyID.getInt(0));
				}
				long log_res = db.insert("DEPUTY", null, values);
				if (log_res != -1) {
					Log.d("prop ", "Deputado salvo");
				} else {
					Log.d("prop ", "Deputado salvo");
				}

			}
		}
		db.close();
		saveVote(voteList,deputy,context);
	}
	
	
	public static void saveVote (ArrayList<Vote>vote,ArrayList<Deputy> deputy,Context context){

		DatabaseInterface helper = new DatabaseInterface(context);
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		for(int i = 0; i < vote.size(); i++){
			values.put("VOTO", vote.get(i).getResVote());
			values.put("CODSESSAO", vote.get(i).getCodSession());
			values.put("IDCADASTRO", deputy.get(i).getIdRegistrtion());
			long log_res = db.insert("VOTE", null, values);
			if (log_res != -1) {
				Log.d("Voto", "Voto salvo");
			} else {
				Log.d("prop ", "Voto não salvo");
			}
		}
		db.close();
	}

	public static void saveParty(Party party,Context context){
		DatabaseInterface helper = new DatabaseInterface(context);
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		Cursor c = db.rawQuery(
				"SELECT * FROM PARTY WHERE NUMERO = ' "
						+ party.getNumParty() + "'", null);
		if (c.getCount() == 0) {
			values.put("NUMERO", party.getNumParty());
			values.put("SIGLA", party.getAccParty());
			long log_res = db.insert("PARTY", null, values);
			if (log_res != -1) {
				Log.d("prop ", "Partido salvo");
			} else {
				Log.d("prop ", "Partido não salvo");
			}
		}
		db.close();
	}
}
