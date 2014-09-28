package com.example.pvmp;

import helpers.ParserHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.Proposition;

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


		
class HttpRequestXml {
	public static Context tmp_context = null;
	
	
	public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
	    Reader reader = null;
	    reader = new InputStreamReader(stream, "UTF-8");        
	    char[] buffer = new char[len];
	    reader.read(buffer);
	    return new String(buffer);
	}	
	
	public void generateUrl(){
		
	}
	
	
	public static Integer loadData(String test_url,Context context) throws IOException, XmlPullParserException, ParserConfigurationException, SAXException{
		tmp_context = context;
		Integer test = 0;
		URL url = new URL(test_url);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setReadTimeout(100000);
		connection.setConnectTimeout(100000);
		connection.setDoInput(true);
		connection.connect();
		int response = connection.getResponseCode();
		Log.d(null,"The response is:" + response);
		
		InputStream is = connection.getInputStream();
		org.w3c.dom.Document name = parse(is);
		org.w3c.dom.Element docEle = (org.w3c.dom.Element) name.getDocumentElement();
		org.w3c.dom.NodeList nodeList = docEle.getChildNodes();
		Log.d("SIZE:" + nodeList.getLength(),"node size");
		List<Proposition> tmp_propList = ParserHelper.propList(nodeList);
		Log.d("Size:" + tmp_propList.size(), "prop size");
		salveProp(tmp_propList, context);
		return 1;
	}
	
	private static Document parse (InputStream in) throws XmlPullParserException, IOException, ParserConfigurationException, SAXException {
		try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse(in);
			return doc;
		}
		finally{
			in.close();
		}
		
	}
	
		public static void salveProp(List<Proposition> propList,Context context){
	/*
	public static final Integer ANOPROP = 0;
	public static final String EMENTAPROP = "EMENTAPROP";
	public static final String AUTORPROP = "AUTORPROP";
	/* TIPOPROP é a sigla da proposicao 
	public static final String TIPOPROP = "TIPOPROP";
	public static final String SITUACAOPROP = "SITUACAOPROP";
	*/
			PropositionDAO helper = new PropositionDAO(context);
			SQLiteDatabase db = helper.getWritableDatabase();
			ContentValues values = new ContentValues();
			for (int i = 0; i < propList.size(); i++){
			Cursor c = db.rawQuery("SELECT * FROM PROPOSITION WHERE  IDPROP= ' " + propList.get(i).getIdProp() + "'", null);
			if (c == null){
				values.put("IDPROP", propList.get(i).getIdProp());
				long log_res = db.insert("PROPOSITION", null, values);
				if (log_res != -1 ){
					//Toast.makeText(v, "Proposição salva", Toast.LENGTH_SHORT).show();
					Log.d("prop ", "Proposição salva");
				}
				else{
					//		Toast.makeText(v, "Erro ao salvar", Toast.LENGTH_SHORT).show();
					Log.d("prop ", "Proposição não salva");
				}
			}
			}
		}

}
