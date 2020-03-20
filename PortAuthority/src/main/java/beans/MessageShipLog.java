package beans;

import javax.jms.MessageListener;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName="jms/myLog")
public class MessageShipLog implements MessageListener {
    public MessageShipLog() {}

    @Override
    public void onMessage(Message msg) {
        if (msg instanceof TextMessage) {
            TextMessage tm = (TextMessage) msg;
            try {
                String text = tm.getText();
                System.out.println("Message received: " + text);
            } catch (Exception e) {
                System.out.println("Message bean error: " + e);
            }
        }
    }
}
