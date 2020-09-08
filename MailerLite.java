import java.io.IOException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXParseException;

public class MailerLite {
	
	
	public static String searchMlSubUrl(String ml_searchUrl,String emailId, String apikey) 
	{
		String requestUrl = ml_searchUrl.replaceAll("EMAILID", emailId).replaceAll("APIKEY",apikey);
		return requestUrl;
  	}
	public static String createMlSubURl(String ml_createSubUrl, String apikey) 
	{
		String requestUrl = ml_createSubUrl.replaceAll("APIKEY",apikey);
		return requestUrl;
  	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject creatSubscriberRequest(String email, String name, String company) throws ParseException, IOException, java.text.ParseException, SAXParseException 
	{
		JSONObject requestPayload = new JSONObject();
		requestPayload.put("email",email);
		requestPayload.put("name",name);
		
		JSONObject subJson = new JSONObject();
		requestPayload.put("fields",subJson);
		subJson.put("company",company);
		
		JSONObject JsonPayload = requestPayload;
		System.out.println("JsonPayload is: "+JsonPayload );
	
		return requestPayload;	
	}

}
