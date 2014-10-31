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

	public static final String PROP_TABLE_NAME = "Proposition";
	public static final String ID_PROP = "id_prop";
	public static final String ANO_PROP = "year";
	public static final String EMENTA_PROP = "menu";
	public static final String AUTHOR_PROP = "author";
	/* TIPOPROP é a sigla da proposicao */
	public static final String ACRONYM_PROP = "acronym";
	public static final String NUMBER_PROP = "number";
	public static final String SITUATITION_PROP = "situation";
	public static final String CATEGORY_PROP = "category";

	public static final String CREATE_PROPPOSITION_TABLE = 
			"CREATE TABLE " + PROP_TABLE_NAME + "(" 
					+ ID_PROP + " INTEGER NOT NULL PRIMARY KEY, " 
					+ ANO_PROP + " INTEGER NOT NULL, "
					+ EMENTA_PROP + " TEXT, "
					+ AUTHOR_PROP + " TEXT, "
					+ ACRONYM_PROP + " TEXT, "
					+ SITUATITION_PROP + " TEXT, "
					+ NUMBER_PROP + " TEXT, "
					+ CATEGORY_PROP + " TEXT "
					+ ");";


	public static final String VOTING_TABLE_NAME = "Voting";
	public static final String COD_SESSAO = "code_session";
	public static final String SUMMARY = "summary";
	public static final String DATE = "date";

	public static final String CREATE_VOTATING_TABLE = 
			"CREATE TABLE " + VOTING_TABLE_NAME + "(" 
					+ COD_SESSAO + " INTEGER NOT NULL PRIMARY KEY, " 
					+ SUMMARY + " TEXT, "
					+ DATE + " TEXT, "
					+ ID_PROP + " INTEGER, "
					+ "FOREIGN KEY(id_prop) REFERENCES Proposition(id_prop) "
					+ ");";



	public static final String PARTY_TABLE_NAME = "Party";
	public static final String ACRONYM_PARTY = "acronym";
	public static final String NUMBER = "number_party";
	public static final String CREATE_PARTY_TABLE = 
			"CREATE TABLE " + PARTY_TABLE_NAME + "(" 
					+ NUMBER + " INTEGER NOT NULL PRIMARY KEY, " 
					+ ACRONYM_PARTY + " TEXT "
					+ ");";
	public static final String DEPUTY_TABLE_NAME = "Deputy";
	public static final String NOME = "name";
	public static final String UF = "uf";
	public static final String IDCADASTRO = "id_registration";
	public static final String NUMPARTIDO = "number_party";
	public static final String CREATE_DEPUTY_TABLE = 
			"CREATE TABLE " + DEPUTY_TABLE_NAME + "(" 
					+ IDCADASTRO + " INTEGER NOT NULL PRIMARY KEY, " 
					+ UF + " TEXT, "
					+ NOME + " TEXT, "
					+ NUMPARTIDO + " INTEGER NOT NULL, "
					+"FOREIGN KEY(number_party) REFERENCES Party(number_party) "
					+ ");";

	public static final String VOTE_TABLE_NAME = "Vote";
	public static final String VOTO = "vote_result";
	public static final String CREATE_VOTE_TABLE = 
			"CREATE TABLE " + VOTE_TABLE_NAME + "(" 
					+ VOTO + " TEXT, " 
					+ COD_SESSAO + " INTEGER, "
					+ IDCADASTRO + " INTEGER, "
					+ "FOREIGN KEY(code_session) REFERENCES Voting(code_session), "
					+ "FOREIGN KEY(id_registration) REFERENCES Deputy(id_registration) "
					+ ");";

	
	public static final String USER_TABLE_NAME = "User";
	public static final String NAME = "name";
	public static final String USER_NAME = "user_name";
	public static final String EMAIL = "email";
	public static final String AGE = "age";
	public static final String EDUCATION = "education";
	public static final String SEX = "sex";
	public static final String DEFAULT_USER = "default_User";
	public static final String CREATE_USER_TABLE = 
					"CREATE TABLE " + USER_TABLE_NAME + "(" 
					+ USER_NAME + " TEXT, "
					+ NAME + " TEXT, " 
					+ EMAIL + " TEXT, "
					+ AGE + " INTEGER, "
					+ EDUCATION + " TEXT, "
					+ SEX + " TEXT, "
					+ DEFAULT_USER + " TEXT "
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
    db.execSQL(CREATE_USER_TABLE);
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
          "SELECT * FROM Proposition WHERE  id_prop= ' "
          + propList.get(i).getIdProp() + "'", null);
      Log.d("ENTROU2", "ENTROU2");
      if (c.getCount() == 0) {
        Log.d("ENTROU", "ENTROU");
        values.put("id_prop", propList.get(i).getIdProp());
        values.put("year", propList.get(i).getYearProp());
        values.put("author", propList.get(i).getAuthorProp());
        values.put("menu", propList.get(i).getMenuProp());
        values.put("acronym", propList.get(i).getAccProp());
        values.put("number", propList.get(i).getNumProp());
        values.put("situation", propList.get(i).getSituationProp());
        values.put("category", propList.get(i).getCategoryProp());
        long log_res = db.insert("Proposition", null, values);
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
					"SELECT * FROM Voting WHERE code_session = ' "
							+ votList.get(i).getCodSessionVot() + "'", null);
			if (c.getCount() == 0) {
				values.put("date", votList.get(i).getDateVot());
				values.put("summary", votList.get(i).getSummaryVot());
				values.put("code_session", votList.get(i).getCodSessionVot());
				values.put("id_prop", idProp);
				long log_res = db.insert("VOTING", null, values);
				if (log_res != -1) {
					Log.d("voting", "Votação salva");
				} else {
					Log.d("voting", "Votação não salva");
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
					"SELECT * FROM Deputy WHERE id_registration = ' "
							+ deputy.get(i).getIdRegistrtion() + "'", null);
			if (c.getCount() == 0) {
				values.put("id_registration", deputy.get(i).getIdRegistrtion());
				values.put("uf", deputy.get(i).getUf());
				values.put("name", deputy.get(i).getName());
				Cursor partyID = db.rawQuery("SELECT number_party FROM Party WHERE acronym = '" 
						+ deputy.get(i).getPartyName() + "'",null);
				if(partyID.moveToFirst()){
					values.put("number_party", partyID.getInt(0));
				}
				long log_res = db.insert("Deputy", null, values);
				if (log_res != -1) {
					Log.d("deputy ", "Deputado salvo");
				} else {
					Log.d("deputy ", "Deputado salvo");
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
			values.put("vote_result", vote.get(i).getResVote());
			values.put("code_session", vote.get(i).getCodSession());
			values.put("id_registration", deputy.get(i).getIdRegistrtion());
			long log_res = db.insert("Vote", null, values);
			if (log_res != -1) {
				Log.d("Vote", "Voto salvo");
			} else {
				Log.d("Vote ", "Voto não salvo");
			}
		}
		db.close();
	}

	public static void saveParty(Party party,Context context){
		DatabaseInterface helper = new DatabaseInterface(context);
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		Cursor c = db.rawQuery(
				"SELECT * FROM Party WHERE number_party = ' "
						+ party.getNumParty() + "'", null);
		if (c.getCount() == 0) {
			values.put("number_party", party.getNumParty());
			values.put("acronym", party.getAccParty());
			long log_res = db.insert("Party", null, values);
			if (log_res != -1) {
				Log.d("party ", "Partido salvo");
			} else {
				Log.d("prop ", "Partido não salvo");
			}
		}
		db.close();
	}
}
