package main.java.askDM;

import java.util.HashSet;
import java.util.Set;


import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

/**
 * Hello world!
 *
 */
public class ChkWithDMSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler 
{
	private static final Set<String> supportedApplicationIds;

    static {
        /*
         * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit" the relevant
         * Alexa Skill and put the relevant Application Ids in this Set.
         */
        supportedApplicationIds = new HashSet<String>();
        supportedApplicationIds.add("amzn1.ask.skill.af941ed2-c0db-45df-b81d-080ab1857c88");
    }
    public ChkWithDMSpeechletRequestStreamHandler() {
        super(new ChkWithDMSpeechlet(), supportedApplicationIds);
    }
   	
}
