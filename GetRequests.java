import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetRequests {

	public static String triggerGetRequest(String getRequestUrl) throws Exception {
    	StringBuffer response = new StringBuffer();
    	
		URL url = new URL(getRequestUrl);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept","*/*");
        connection.setRequestProperty("Content-Type","application/json");
        try {
        InputStream stream       = connection.getInputStream();
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader buffer    = new BufferedReader(reader);
        
        String line;
        while((line = buffer.readLine()) != null) 
        {
        	response.append(line);
        }
        buffer.close();
        connection.disconnect();
        }
        catch(IOException e) {
        	System.out.println("System Error: There has been an IO Exception");
        }
        
    	return response.toString();
    	}
	
	public static String triggerSBGetRequest(String getRequestUrl, String authToken) throws Exception {
    	StringBuffer response = new StringBuffer();
    	
		URL url = new URL(getRequestUrl);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization","Bearer "+authToken);
        connection.setRequestProperty("Accept","*/*");
        connection.setRequestProperty("Content-Type","application/json");
        try {
        InputStream stream       = connection.getInputStream();
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader buffer    = new BufferedReader(reader);
        
        String line;
        while((line = buffer.readLine()) != null) 
        {
        	response.append(line);
        }
        buffer.close();
        connection.disconnect();
        }
        catch(IOException e) {
        	System.out.println("System Error: There has been an IO Exception");
        }
        
    	return response.toString();
    	}
}
