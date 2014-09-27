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

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.net.Uri;
import android.os.AsyncTask;
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
	
	
	public static String loadData(String test_url) throws IOException, XmlPullParserException{
		
		URL url = new URL(test_url);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setReadTimeout(100000);
		connection.setConnectTimeout(100000);
		connection.setDoInput(true);
		connection.connect();
		int response = connection.getResponseCode();
		Log.d(null,"The response is:" + response);
		
		InputStream is = connection.getInputStream();
		String name = parse(is);
		Log.d("TESTE:" + name, "returning jsonList");
		return name;
	}
	
	private static String parse (InputStream in) throws XmlPullParserException, IOException {
		try{
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(in,null);
			parser.nextTag();
			return parser.getName();
		}
		finally{
			in.close();
		}
		
	}
	

}
