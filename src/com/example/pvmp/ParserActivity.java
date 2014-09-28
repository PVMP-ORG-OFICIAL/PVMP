package com.example.pvmp;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import dao.PropositionDAO;


public class ParserActivity extends Activity {

	private static final String test_url = 
			"http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarProposicoes?sigla=PL&numero=&ano=2011&datApresentacaoIni=14/11/2011&datApresentacaoFim=16/11/2011&parteNomeAutor=&idTipoAutor=&siglaPartidoAutor=&siglaUFAutor=&generoAutor=&codEstado=&codOrgaoEstado=&emTramitacao=";
	
	private PropositionDAO helper;
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_parser);
		helper = new PropositionDAO(this);
	}

	public void salveProp(View view,Integer id){
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("IDPROP", id);
		db.insert("PVMP", null, values);
	}
	public void loadParser(View view){
		ConnectivityManager connecManager = (ConnectivityManager)
				getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connecManager.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
            new Download().execute();
        } else {
            //textView.setText("No network connection available.");
        }
		
	}

	class Download extends AsyncTask<String, Void, String>{
		@Override
		protected String doInBackground(String... params) {
			String value = "David";
			try{
				value = HttpRequestXml.loadData(test_url);
			}	
			catch (IOException e ){
				return "Error retriving data";
			} catch (XmlPullParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return value;
		}
		
		@Override
		protected void onPostExecute(String result) {
			Log.d("Show:" + result, "Current id");
		}
	}
	
}
