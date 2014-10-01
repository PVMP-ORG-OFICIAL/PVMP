package com.example.pvmp;

import helpers.ParserHelper;

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

import model.Proposition;
import model.Voting;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;

import dao.PropositionDAO;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

class HttpRequestXml {

	private static final String PROP_VOTADAS_PLENARIO = "http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarProposicoesVotadasEmPlenario?";
	private static final String LISTAR_PROPOSICAO = "http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarProposicoes?";
	private static final String COMP_LISTAR_URL = "&datApresentacaoIni=&datApresentacaoFim=&parteNomeAutor=&idTipoAutor=&siglaPartidoAutor=&siglaUFAutor=&generoAutor=&codEstado=&codOrgaoEstado=&emTramitacao=";
	private static final String OBTER_VOTACAO_PROPOSICAO = "http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ObterVotacaoProposicao?";
	private final static  String [] ANO_PROPOSICAO = {"2013","2014"};
	public  final static  String [] TIPO_PROPOSICAO = {"PL","PLP","PDC","MPV","PEC"};

	public static Context tmp_context = null;

	public String readIt(InputStream stream, int len) throws IOException,
			UnsupportedEncodingException {
		Reader reader = null;
		reader = new InputStreamReader(stream, "UTF-8");
		char[] buffer = new char[len];
		reader.read(buffer);
		return new String(buffer);
	}

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
	
	public static void propositionList(ArrayList<HashMap<String, String>> propList) throws IOException, XmlPullParserException, ParserConfigurationException, SAXException{
		
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
		propArrayObject = ParserHelper.propList(propElement.getChildNodes());
		votArrayObject = ParserHelper.votingList(votElement.getChildNodes());
		saveProp(propArrayObject,votArrayObject, tmp_context);
	   }
	}
	

	private static Element createConnection(String s_url) throws IOException, XmlPullParserException, ParserConfigurationException, SAXException{
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

	public static void saveProp(ArrayList<Proposition> propList,ArrayList<Voting> votingList,Context context) {

		saveVoting(votingList, propList.get(0).getIdProp(),context);
		PropositionDAO helper = new PropositionDAO(context);
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		for (int i = 0; i < propList.size(); i++) {
			Cursor c = db.rawQuery(
					"SELECT * FROM PROPOSITION WHERE  IDPROP= ' "
							+ propList.get(i).getIdProp() + "'", null);
			Log.d("ENTROU2", "ENTROU2");
			if (c.getCount() == 0) {
				Log.d("ENTROU", "ENTROU");
				values.put("IDPROP", propList.get(i).getIdProp());
				values.put("ANOPROP", propList.get(i).getAnoProp());
				values.put("AUTORPROP", propList.get(i).getAnoProp());
				values.put("EMENTAPROP", propList.get(i).getEmentaProp());
				values.put("SIGLAPROP", propList.get(i).getSiglaProp());
				values.put("NUMEROPROP", propList.get(i).getNumeroProp());
				values.put("SITUACAOPROP", propList.get(i).getSituacaoProp());
				long log_res = db.insert("PROPOSITION", null, values);
				if (log_res != -1) {
					// Toast.makeText(v, "Proposição salva",
					// Toast.LENGTH_SHORT).show();
					Log.d("prop ", "Proposição salva");
				} else {
					// Toast.makeText(v, "Erro ao salvar",
					// Toast.LENGTH_SHORT).show();
					Log.d("prop ", "Proposição não salva");
				}
			}
		}
		db.close();
	}
	
	
	public static void saveVoting(ArrayList<Voting> votList,Integer idProp, Context context) {

		PropositionDAO helper = new PropositionDAO(context);
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		for (int i = 0; i < votList.size(); i++) {
			//Cursor c = db.rawQuery(
			//		"SELECT * FROM VOTING WHERE CODSESSAO = ' "
			//				+ votList.get(i).getCodSessaoVot() + "'", null);
			Log.d("ENTROU2", "ENTROU2");
			//if (c.getCount() == 0) {
				values.put("DATA_VOTACAO", votList.get(i).getDataVot());
				values.put("RESUMO", votList.get(i).getResumoVot());
				values.put("CODSESSAO", votList.get(i).getCodSessaoVot());
				values.put("IDPROP", idProp);
				long log_res = db.insert("VOTING", null, values);
				if (log_res != -1) {
					// Toast.makeText(v, "Proposição salva",
					// Toast.LENGTH_SHORT).show();
					Log.d("prop ", "Votação salva");
				} else {
					// Toast.makeText(v, "Erro ao salvar",
					// Toast.LENGTH_SHORT).show();
					Log.d("prop ", "Votação não salva");
				}
		//	}
		}
		db.close();
	}
}
