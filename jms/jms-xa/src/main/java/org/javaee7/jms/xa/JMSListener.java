package org.javaee7.jms.xa;

import static java.util.logging.Level.SEVERE;
import static org.javaee7.jms.xa.DeliveryStats.countDownLatch;

import java.util.logging.Logger;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

@MessageDriven(
    activationConfig = { 
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:app/jms/queue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"), }
)
public class JMSListener implements MessageListener {

    private static final Logger logger = Logger.getLogger(JMSListener.class.getName());

    @EJB
    private DeliveryStats deliveryStats;

    @Override
    public void onMessage(Message message) {
        try {
            logger.info("Message received (async): " + message.getBody(String.class));
            
            deliveryStats.messageDelivered();
            countDownLatch.countDown();
        } catch (JMSException ex) {
            logger.log(SEVERE, null, ex);
        }
    }
}
