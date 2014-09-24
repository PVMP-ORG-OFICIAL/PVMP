package com.example.pvmp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;


		
class HttpRequestXml {

	private static final String test_url = 
			"http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ListarProposicoes?sigla=PL&numero=&ano=2011&datApresentacaoIni=14/11/2011&datApresentacaoFim=16/11/2011&parteNomeAutor=&idTipoAutor=&siglaPartidoAutor=&siglaUFAutor=&generoAutor=&codEstado=&codOrgaoEstado=&emTramitacao=";
	
	public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
	    Reader reader = null;
	    reader = new InputStreamReader(stream, "UTF-8");        
	    char[] buffer = new char[len];
	    reader.read(buffer);
	    return new String(buffer);
	}	
	
	
	public static String loadData() throws IOException{
		
		URL url = new URL(test_url);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setReadTimeout(100000);
		connection.setConnectTimeout(100000);
		connection.setDoInput(true);
		connection.connect();
		int response = connection.getResponseCode();
		Log.d(null,"The response is:" + response);
		
		InputStream is = connection.getInputStream();
		String contentAsString = is.toString();
		return contentAsString;
	}
	

}
