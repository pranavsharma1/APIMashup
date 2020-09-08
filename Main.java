import org.json.simple.JSONObject;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) throws Exception {
		
		final String MS_GET_SUBSCRIBERS_URL    = ReadPropertyFile.getProperties().getProperty("MEGetSubscribers");
		final String ML_SEARCH_SUBSCRIBERS_URL = ReadPropertyFile.getProperties().getProperty("MLSearchSubscribers");
		final String ML_SUBSCRIBER_URL         = ReadPropertyFile.getProperties().getProperty("MLSubscriber");
		final String SF_CREATE_CONTACT_URL     = ReadPropertyFile.getProperties().getProperty("SFCreateContact");
		final String SF_GET_CONTACT_URL        = ReadPropertyFile.getProperties().getProperty("SFGetContacts");
	    final String SF_AUTH_TOKEN             = ReadPropertyFile.getProperties().getProperty("SendFoxToken");
	    final String ML_API_KEY                = ReadPropertyFile.getProperties().getProperty("MLapikey");
	    final String ME_API_KEY                = ReadPropertyFile.getProperties().getProperty("MEapikey");
		
	    
	    
		char choice = 0;
		
		Scanner input = new Scanner(System.in);
		
		while(choice != 'q') {
		
		
		System.out.println("\n");
		System.out.println("*****************Welcome to Menu Driven API Mashup********************");
		
		System.out.println("Enter 1 to Show All Moosend Subscribers");
		System.out.println("Enter 2 to Check Moosend Subscriber is in MailerList");
		System.out.println("Enter 3 to Show All MailerList Subscribers");
		System.out.println("Enter 4 to Check MailerList Subscriber is in SendFox");
		System.out.println("Enter q to exit");
		System.out.println("\n");
		
		String s = input.next();
		choice = s.charAt(0);
		
		switch(choice)
		{
			case '1':
				//Get All Moosend Subs
				String ME_getAllSubscribersUrl         = Mooseend.ReplaceMooseUrl(MS_GET_SUBSCRIBERS_URL,"e1d115a8-0440-4dcb-8231-35ee5b06ee73", "Subscribed", "json", ME_API_KEY);
				String ME_getAllSubscribersResponse    = GetRequests.triggerGetRequest(ME_getAllSubscribersUrl);
				System.out.println("Mooseend Subscribers List: "+ME_getAllSubscribersResponse);
				System.out.println("\n");
				break;
				
			case '2':
				
				System.out.print("Please enter email to search for: ");
				Scanner em = new Scanner(System.in);
				String email = em.next();
				
				System.out.println("Email entered: "+email);
				
				//Search MailerLite
				String ML_searchSubscribersUrl         = MailerLite.searchMlSubUrl(ML_SEARCH_SUBSCRIBERS_URL,email,ML_API_KEY);
				String ML_searchSubscribersResponse    = GetRequests.triggerGetRequest(ML_searchSubscribersUrl);
				if(ML_searchSubscribersResponse.contains(email))
				{
					System.out.println("MailerLite Subscribers Found ");
				}
				else 
				{
					char choice1;
					System.out.print("Subscriber Not Found. Create a new subscriber(y/n)? ");
					Scanner yORn = new Scanner(System.in);
					String yORnString = yORn.next();
					choice1 = yORnString.charAt(0); 
					
					if(choice1 == 'q') {
						break;
					}
					
					else if(choice1 == 'y') {
						
						//Create subscriber
						System.out.println("\n");
						System.out.println("Enter Email Id: ");
						Scanner email1 = new Scanner(System.in);
						String emailforMLSub = email1.next();
						System.out.println("Enter Name: ");
						Scanner name1 = new Scanner(System.in);
						String nameforMLSub = name1.next();
						System.out.println("Enter Company: ");
						Scanner company1 = new Scanner(System.in);
						String companyforMLSub = company1.next();
						
						JSONObject createMLSubscriberPayload  = MailerLite.creatSubscriberRequest(emailforMLSub,nameforMLSub,companyforMLSub);
						String createMlSubURl                 = MailerLite.createMlSubURl(ML_SUBSCRIBER_URL,ML_API_KEY);
						String createMLSubscriberResponse     = PostRequests.triggerPostRequest(createMLSubscriberPayload,createMlSubURl );
						System.out.println("MailerLite Create Subscriber Response: " + createMLSubscriberResponse);
						
					}
					else if(choice1 == 'n') {
						System.out.println("Have a nice day!");
						break;
					}
					else {
						System.out.println("Sorry, Invalid Input");
					}
					
				}
				
				break;
				
				
				
			case '3':
				String ML_getAllSubscribersUrl      = MailerLite.createMlSubURl(ML_SUBSCRIBER_URL,ML_API_KEY);
				String ML_getAllSubscribersResponse = GetRequests.triggerGetRequest(ML_getAllSubscribersUrl);
				System.out.println("MailerLite Get All Subscribers Response: "+ML_getAllSubscribersResponse);
				break;
				
				
			case '4':
				
				System.out.println("Please enter an email to search for MailerList Subscriber: ");
				Scanner em1 = new Scanner(System.in);
				String email2 = em1.next();
				System.out.println("Email Entered: "+email2);
				
				//Get SendFox Contacts
				System.out.println("\n");
				String SF_GetContactsResponse = GetRequests.triggerSBGetRequest(SF_GET_CONTACT_URL, SF_AUTH_TOKEN);
				
				if(SF_GetContactsResponse.contains(email2))
				{
					System.out.println("MailerLite Subscriber already exists");
				}
				else 
				{
					char choice2;
					System.out.println("MailerLite subscriber not found. Create a new subscriber(y/n)?");
					Scanner yORn = new Scanner(System.in);
					String yORnString = yORn.next();
					choice2 = yORnString.charAt(0);
					if(choice2 == 'q') {
						break;
					}
					else if(choice2 == 'y') {
						//Create subscriber
						System.out.println("\n");
						System.out.println("Enter Email Id: ");
						Scanner email1 = new Scanner(System.in);
						String emailforSFSub = email1.next();
						System.out.println("Enter First Name: ");
						Scanner f_name = new Scanner(System.in);
						String f_nameforSFSub = f_name.next();
						System.out.println("Enter Last Name: ");
						Scanner l_name = new Scanner(System.in);
						String l_nameforSFSub = l_name.next();
						System.out.println("\n");
						
						String SF_getAllSubscribersUrl     = SendBox.createSBContactUrl(SF_CREATE_CONTACT_URL, emailforSFSub, f_nameforSFSub, l_nameforSFSub);
						String createSFContactResponse     = PostRequests.triggerSBPostRequest(SF_getAllSubscribersUrl,SF_AUTH_TOKEN);
						System.out.println("Send Fox Create Contact Response: "+createSFContactResponse);
						
					}
					else if(choice2 == 'n'){
						System.out.println("See you later, have a nice day!");
					}
					else {
						System.out.println("Sorry, invalid input.");
					}
				
				}
				break;
				
			case 'q':
				System.out.println("Thank you for using this service. Good bye :)");
				break;
			default :
	            System.out.println("Invalid input. Choose a number from the Menu");
		}
		}
			
	}

}
