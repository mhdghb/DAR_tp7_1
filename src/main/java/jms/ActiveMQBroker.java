package jms;

import org.apache.activemq.broker.BrokerService;

public class ActiveMQBroker {
    public static void main(String[]args){

        try {
            BrokerService brokerService=new BrokerService();
           // brokerService.setPersistent(false);// tnjm t5ali elpartieyt eli kifkif fl cosumer w elproducer
            brokerService.addConnector("tcp://0.0.0.0:61616");
            brokerService.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
