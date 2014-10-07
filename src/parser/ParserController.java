package parser;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.Party;
import model.Proposition;
import model.Vote;
import model.Voting;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;

import parser.helpers.ParserHelper;

import android.util.Log;

public class ParserController {

	private static final String PROP_VOTADAS_PLENARIO = "http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarProposicoesVotadasEmPlenario?";
	private static final String LISTAR_PROPOSICAO = "http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarProposicoes?";
	private static final String COMP_LISTAR_URL = "&datApresentacaoIni=&datApresentacaoFim=&parteNomeAutor=&idTipoAutor=&siglaPartidoAutor=&siglaUFAutor=&generoAutor=&codEstado=&codOrgaoEstado=&emTramitacao=";
	private static final String OBTER_VOTACAO_PROPOSICAO = "http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ObterVotacaoProposicao?";
	private final static  String [] ANO_PROPOSICAO = {"2013","2014"};
	public  final static  String [] TIPO_PROPOSICAO = {"PL","PLP","PDC","MPV","PEC"};
	private static final String PARTY_FILE = "party.txt";

	public static Context tmp_context = null;


	public static Integer requestPlenario(Context context) throws IOException, XmlPullParserException, ParserConfigurationException, SAXException {
		tmp_context = context;
		for(String ano:ANO_PROPOSICAO){
			for(String tipo:TIPO_PROPOSICAO){
				String plenario_url = PROP_VOTADAS_PLENARIO + "ano=" + ano + "&tipo="+tipo;
				Element plenario_element =  createConnection(plenario_url);
				Log.d("Ano da Requisicao:" + ano, "URL");
				propositionList(ParserHelper.nameProposition(plenario_element));
				//Log.d("Plenario:" + plenario_element.getChildNodes().getLength(), "Plenario");
			}
		}
		return 1;
	}

	private static void propositionList(ArrayList<HashMap<String, String>> propList) throws IOException, XmlPullParserException, ParserConfigurationException, SAXException{

		ArrayList<Proposition> propArrayObject = null;
		ArrayList<Voting> votArrayObject = null;
		for(int i = 0; i < propList.size(); i++){
			String ano = propList.get(i).get("ano");
			String sigla = propList.get(i).get("sigla");
			String numero = propList.get(i).get("numero");
			String listPropUrl = LISTAR_PROPOSICAO + "sigla="+ sigla + "&numero="+numero + "&ano="+ano + COMP_LISTAR_URL;
			String listVotUrl = OBTER_VOTACAO_PROPOSICAO + "tipo="+ sigla + "&numero="+numero + "&ano="+ano;
			Element propElement = createConnection(listPropUrl);
			Element votElement = createConnection(listVotUrl);
			propArrayObject = ParserHelper.returnPropList(propElement.getChildNodes());
			votArrayObject = ParserHelper.returnVotingList(votElement.getChildNodes());
			DatabaseInterface.saveProp(propArrayObject,votArrayObject, tmp_context);
		}
	}


	private static Element createConnection(String s_url) 
			throws IOException, XmlPullParserException, 
			ParserConfigurationException, SAXException{
		URL url = new URL(s_url);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setReadTimeout(100000);
		connection.setConnectTimeout(100000);
		connection.setDoInput(true);
		connection.connect();
		int response = connection.getResponseCode();
		Log.d(null, "The response is:" + response);
		InputStream is = connection.getInputStream();
		org.w3c.dom.Document document = parse(is);
		return document.getDocumentElement();

	}
	private static Document parse(InputStream in)
			throws XmlPullParserException, IOException,
			ParserConfigurationException, SAXException {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			org.w3c.dom.Document doc = db.parse(in);
			return doc;
		} finally {
			in.close();
		}
	}

	public static void importPartyFile (AssetManager getAssets, Context context) throws IOException{
		tmp_context = context;
		try{
			InputStream inputStream =  getAssets.open(PARTY_FILE);
			if(inputStream != null){
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferR = new BufferedReader(inputStreamReader);
				String receiveString = "";
				StringBuilder stringBuilder = new StringBuilder();
				while ((receiveString = bufferR.readLine()) != null){
					Party party = new Party();
					String [] name = receiveString.split(" ");
					party.setNumParty(Integer.parseInt(name[1]));
					party.setAccParty(name[0]);
					DatabaseInterface.saveParty(party, tmp_context);
				}
			}
		}
		catch (FileNotFoundException e){
			Log.e("", "File not found: " + e.toString());	
		}
		catch (IOException e){
			Log.e("", "Can not read file: " + e.toString());	
		}
	}
}