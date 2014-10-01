package helpers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.Proposition;
import model.Voting;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class ParserHelper {
	
	
	private String OBTER_VOTACAO_PROPSICAO = "http://www.camara.gov.br/SitCamaraWS/Proposicoes.asmx/ObterVotacaoProposicao?";
	
	
	public static ArrayList<Proposition> propList(NodeList  nodeList){
		ArrayList<Proposition> propList = new ArrayList<Proposition>();
		for (int i = 0; i < nodeList.getLength(); i++){
			if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE){
				Proposition prop = new Proposition();

				Element tmp = (Element) nodeList.item(i);

				String id = tmp.getElementsByTagName("id").item(0).getTextContent();
				prop.setIdProp(Integer.parseInt(id));

				String ano = tmp.getElementsByTagName("ano").item(0).getTextContent();
				prop.setAnoProp(Integer.parseInt(ano));

				String ementa = tmp.getElementsByTagName("txtEmenta").item(0).getTextContent();
				prop.setEmentaProp(ementa);

				Element el_autor = (Element) tmp.getElementsByTagName("autor1").item(0);
				String autor = el_autor.getElementsByTagName("txtNomeAutor").item(0).getTextContent();
				prop.setAutorProp(autor);

				Element el_situacao = (Element) tmp.getElementsByTagName("situacao").item(0);
				String situacao = el_situacao.getElementsByTagName("descricao").item(0).getTextContent();
				prop.setSituacaoProp(situacao);

				Element el_tipoProp = (Element) tmp.getElementsByTagName("tipoProposicao").item(0);
				String siglaProp = el_tipoProp.getElementsByTagName("sigla").item(0).getTextContent();
				prop.setSiglaProp(siglaProp);
				
				String numeroProp = tmp.getElementsByTagName("numero").item(0).getTextContent();
				prop.setNumeroProp(numeroProp);
				
				propList.add(prop);
			}
		}
		Log.d("size" + propList.size(), "test");
		return propList;
	}
	
    public static ArrayList<HashMap<String, String>> nameProposition (Element element){
    	
    	ArrayList<HashMap<String, String>>  list_proposition = new ArrayList<>(); 
    	HashMap<String, String> prop_hash = null; 
    	NodeList plenario = element.getChildNodes();

       for (int i = 0; i<plenario.getLength(); i++){
    	   if(element.getElementsByTagName("nomeProposicao").item(i) != null){
    	   String nameProp = element.getElementsByTagName("nomeProposicao").item(i).getTextContent();
    	   prop_hash = token(nameProp);
    	   list_proposition.add(prop_hash);
    	   }
       }
       return list_proposition;
    }
    
    private static HashMap<String, String> token(String nameProp){
    	
    	HashMap<String, String> prop_hash = new HashMap<>();
    	String[] tmp_sigla = nameProp.split(" ");
    	String [] new_nameProp = tmp_sigla[1].split("/");
    	String sigla = tmp_sigla[0];
    	String numero = new_nameProp[0];
    	String ano = new_nameProp[1];

    	prop_hash.put("sigla", sigla);
    	prop_hash.put("numero", numero);
    	prop_hash.put("ano", ano);
    	return prop_hash;
    }
    
    public static ArrayList<Voting> votingList (NodeList votingList){
		ArrayList<Voting> votList = new ArrayList<Voting>();
		for (int i = 0; i < votingList.getLength(); i++){
			if (votingList.item(i).getNodeType() == Node.ELEMENT_NODE){
				Voting obj_voting = new Voting();

				Element prop = (Element) votingList.item(i);
				Log.d("Entrou aqui:" + prop.getNodeName(), "BLABLA");
				if(prop.getElementsByTagName("Votacao").item(0) != null){
				NamedNodeMap voting = prop.getElementsByTagName("Votacao").item(0).getAttributes();

				String codSessao = voting.getNamedItem("codSessao").getTextContent();
				String resumo = voting.getNamedItem("Resumo").getTextContent();
				String data = voting.getNamedItem("Data").getTextContent();

				obj_voting.setCodSessaoVot(Integer.parseInt(codSessao));
				obj_voting.setResumoVot(resumo);
				obj_voting.setDataVot(data);
				
				Log.d("Code:" + codSessao, "Vote");
				Log.d("Resumo:" + resumo, "Vote");
				Log.d("Data:" + data, "Vote");
				votList.add(obj_voting);
				}
    }
}
		return votList;
    }
}
