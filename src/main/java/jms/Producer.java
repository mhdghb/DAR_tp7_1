package jms;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
public class Producer {
    public static void main(String[] args){
        try {
            //etablissement d'une connexion ou broker ActiveMQ
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection= connectionFactory.createConnection();
            connection.start();
            //creation d'un session
            Session session = ((javax.jms.Connection) connection).createSession(true,Session.AUTO_ACKNOWLEDGE);
            Destination destination= session.createTopic("myTopic.topic");
            MessageProducer producer=session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            TextMessage textMessage= session.createTextMessage();
            textMessage.setText("Hello world!");
            producer.send(textMessage);
            //commit the transaction
            session.commit();
            System.out.println("Envoi du message...");
            session.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
