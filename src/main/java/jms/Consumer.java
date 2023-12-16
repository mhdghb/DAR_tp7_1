package jms;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
public class Consumer {
    public static void main(String[] args){
        try {
            //etablissement d'une connexion ou broker ActiveMQ
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection= connectionFactory.createConnection();
            connection.start();
            //creation d'un session
            Session session = ((javax.jms.Connection) connection).createSession(true,Session.AUTO_ACKNOWLEDGE);
            Destination destination= session.createTopic("myTopic.topic");

            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if(message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        try {
                            System.out.println("Message recu :"+textMessage.getText());
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}