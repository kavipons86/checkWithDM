package main.java.askDM;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.amazon.speech.ui.SsmlOutputSpeech;

public class ChkWithDMSpeechlet implements SpeechletV2 {
	 private static final Logger log = LoggerFactory.getLogger(ChkWithDMSpeechlet.class);
	 

	
	public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
		// TODO Auto-generated method stub
		IntentRequest request = requestEnvelope.getRequest();
        Session session = requestEnvelope.getSession();
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(), session.getSessionId());
      //  System.out.println("onIntent requestId={}, "+ request.getRequestId()+"sessionId={}"+session.getSessionId());
        Intent intent = request.getIntent();
        String intentName = intent.getName();
        System.out.println("intentName="+intentName);
        if ("orderCommandIntent".equals(intentName)) {
            return handlePromotionRequest(intent, session);
        
        }else {
            String errorSpeech = "This is unsupported.  Please try something else.";
            return newAskResponse(errorSpeech, errorSpeech);
        }
	}

	private SpeechletResponse handlePromotionRequest(Intent intent, Session session) {
		// TODO Auto-generated method stub
		log.info("handlePromotionRequest="+intent.getName());
		String orderObject = null;
       
        // all slots filled, either from the user or by default values. Move to final request
        try {
			return getPromotionResponse(orderObject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 String speechOutput =
	                    "Sorry, I am not able to find your manifest ";
	                        

			return newAskResponse(speechOutput, speechOutput);
		}
	}
	
	
	private SpeechletResponse getPromotionResponse(String orderObject) throws IOException {
		// TODO Auto-generated method stub
		
		
		StringBuffer buffer=new StringBuffer();
		buffer.append("Getting the route plan information. You have total ");
		buffer.append(" customer delivery today; The");
		buffer.append(" customers are  ");
		
		
		
		String speechOutput=buffer.toString();
		log.info("Response is="+speechOutput);
		
		 // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("Promotion");
        card.setContent(speechOutput);

        // Create the plain text output
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText(speechOutput);

        return SpeechletResponse.newTellResponse(outputSpeech, card);
		
	}

	public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
		// TODO Auto-generated method stub
		
		log.info("onLaunch requestId={}, sessionId={}", requestEnvelope.getRequest().getRequestId(),
	                requestEnvelope.getSession().getSessionId());
		
	    return getWelcomeResponse();
	}

	public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> arg0) {
		// TODO Auto-generated method stub

	}

	public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> arg0) {
		// TODO Auto-generated method stub

	}
	private SpeechletResponse getWelcomeResponse() {
         String speechOutput = "<speak>"
                           + "</speak>";
        String repromptText = " ";

        return newAskResponse(speechOutput, true, repromptText, false);
    }

	private SpeechletResponse newAskResponse(String speechOutput, boolean isOutputSsml, String repromptText, boolean isRepromptSsml) {
		// TODO Auto-generated method stub
        OutputSpeech outputSpeech, repromptOutputSpeech;
        if (isOutputSsml) {
            outputSpeech = new SsmlOutputSpeech();
            ((SsmlOutputSpeech) outputSpeech).setSsml(speechOutput);
        } else {
            outputSpeech = new PlainTextOutputSpeech();
            ((PlainTextOutputSpeech) outputSpeech).setText(speechOutput);
        }

        if (isRepromptSsml) {
            repromptOutputSpeech = new SsmlOutputSpeech();
            ((SsmlOutputSpeech) repromptOutputSpeech).setSsml(speechOutput);
        } else {
            repromptOutputSpeech = new PlainTextOutputSpeech();
            ((PlainTextOutputSpeech) repromptOutputSpeech).setText(repromptText);
        }

        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(repromptOutputSpeech);
        return SpeechletResponse.newAskResponse(outputSpeech, reprompt);
	}
	private SpeechletResponse newAskResponse(String stringOutput, String repromptText) {
        return newAskResponse(stringOutput, false, repromptText, false);
    }
	
}
