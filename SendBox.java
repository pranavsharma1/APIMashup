
public class SendBox {
	
	public static String createSBContactUrl(String sb_createContactUrl,String email, String f_name,String l_name) 
	{
		String requestUrl = sb_createContactUrl.replaceAll("EMAIL",email).replaceAll("FIRSTNAME",f_name).replaceAll("LASTNAME",l_name);
		return requestUrl;
  	}

}
