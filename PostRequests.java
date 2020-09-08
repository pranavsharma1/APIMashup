import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.simple.JSONObject;

public class PostRequests {

	public static String triggerPostRequest(JSONObject jsonPayload, String urlEndpoint) throws IOException
	{
		URL url = new URL(urlEndpoint);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "*/*");
	
		OutputStream os = connection.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os);
		if(jsonPayload != null)
		{
			osw.write(jsonPayload.toString());
		}
	    osw.flush();
	    osw.close();
	    String responseString = null;
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) 
	    {
	    	StringBuilder response = new StringBuilder();
		    String responseLine = null;
		    while ((responseLine = br.readLine()) != null) 
		    {
		    	response.append(responseLine.trim());
		    }
		    responseString = response.toString();
		     
	    }
	    catch(IOException e)
	    {
	    	System.out.println("There has been an IO Exception");
	    }
		return responseString;
	}
	
	public static String triggerSBPostRequest(String urlEndpoint, String authToken) throws IOException
	{
		URL url = new URL(urlEndpoint);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestProperty("Authorization","Bearer "+authToken);
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Accept", "*/*");
	    String responseString = null;
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) 
	    {
	    	StringBuilder response = new StringBuilder();
		    String responseLine = null;
		    while ((responseLine = br.readLine()) != null) 
		    {
		    	response.append(responseLine.trim());
		    }
		    responseString = response.toString();
		     
	    }
	    catch(IOException e)
	    {
	    	System.out.println("There has been an IO Exception");
	    }
		return responseString;
	}

}
