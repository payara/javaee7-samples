package org.javaee7.jms.temp.destination;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.inject.Inject;
import jakarta.jms.Destination;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

/**
 * @author Patrik Dudits
 */
@MessageDriven(activationConfig = { 
	@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = Resources.REQUEST_QUEUE),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"), })
public class RequestResponseOverJMS implements MessageListener {

	@Inject
	private JMSContext jms;

	@Override
	public void onMessage(Message message) {
		try {
			Destination replyTo = message.getJMSReplyTo(); // <1> get the destination for the response
			if (replyTo == null) {
				return;
			}
			
			TextMessage request = (TextMessage) message;
			String payload = request.getText(); // <2> read the payload

			System.out.println("Got request: " + payload);

			String response = "Processed: " + payload; // <3> process the request
			jms.createProducer().send(replyTo, response); // <4> send the response
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
