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
        supportedApplicationIds.add("amzn1.ask.skill.112e499d-53b2-41f6-9678-d21c4e139f78");
    }
    public ChkWithDMSpeechletRequestStreamHandler() {
        super(new ChkWithDMSpeechlet(), supportedApplicationIds);
    }
   	
}
