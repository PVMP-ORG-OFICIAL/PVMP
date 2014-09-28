package com.example.pvmp;

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

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.net.Uri;
import android.os.AsyncTask;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import android.util.Log;
import android.util.Xml;


		
class HttpRequestXml {

	public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
	    Reader reader = null;
	    reader = new InputStreamReader(stream, "UTF-8");        
	    char[] buffer = new char[len];
	    reader.read(buffer);
	    return new String(buffer);
	}	
	
	
	public static void loadData(String test_url) throws IOException, XmlPullParserException, ParserConfigurationException, SAXException{
		
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
		//org.w3c.dom.Element prop = (org.w3c.dom.Element) docEle.getElementsByTagName("proposicoes");
		org.w3c.dom.NodeList nodeList = docEle.getChildNodes();
		Log.d("SIZE:" + nodeList.getLength(),"node size");
		for (int i = 0; i < nodeList.getLength(); i++){
			//org.w3c.dom.Element el =  (org.w3c.dom.Element) nodeList.item(i);
			//String id =  nodeList.item(i).getAttributes().getNamedItem("id").getTextContent();
			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
				Element tmp = (Element) nodeList.item(i);
				String id = tmp.getElementsByTagName("id").item(0).getTextContent();
				Log.d("TESTE:" + id,"prop id");
			}
		}
	}
	
	private static Document parse (InputStream in) throws XmlPullParserException, IOException, ParserConfigurationException, SAXException {
		try{
			//XmlPullParser parser = Xml.newPullParser();
			//parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			//parser.setInput(in,null);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document doc = db.parse(in);
            org.w3c.dom.NodeList prop = doc.getElementsByTagName("proposições");
			return doc;
		}
		finally{
			in.close();
		}
		
	}
	

}
