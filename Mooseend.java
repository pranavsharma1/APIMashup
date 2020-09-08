
public class Mooseend {
	
	public static String ReplaceMooseUrl(String me_getSubUrl,String mailingListId, String Status, String format, String apikey) 
	{
		String requestUrl = me_getSubUrl.replaceAll("MAILINGLISTID", mailingListId).replaceAll("STATUS", Status).replaceAll("FORMAT", format).replaceAll("APIKEY",apikey);
		return requestUrl;
  	}

}
