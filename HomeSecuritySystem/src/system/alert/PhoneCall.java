package system.alert;
import java.net.URI;
import java.net.URISyntaxException;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.type.PhoneNumber;


/**
 * The Class PhoneCall.
 */
public class PhoneCall {
	
	/** The Constant ACCOUNT_SID. */
	public static final String ACCOUNT_SID = "ACba385f1929624289b55ae06ae8b335a9";
	  
  	/** The Constant AUTH_TOKEN. */
  	public static final String AUTH_TOKEN = "cf56dff4997d4969d8a6634344ee78aa";

	  /**
  	 * Call.
  	 */
  	public static void call(){
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	    Call call;
		try {
			call = Call.creator(new PhoneNumber("+16502007576"), new PhoneNumber("+19797394235"),
			    new URI("http://demo.twilio.com/docs/voice.xml")).create();
			 System.out.println(call.getSid());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	   
	}

}
