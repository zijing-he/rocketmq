package com.zj.test.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class MyProducer {
    public static void main(String[] args) throws MQClientException {
        /*
         * Instantiate with a producer group name.
         */
        DefaultMQProducer producer = new DefaultMQProducer("my_producer_group");
        /*
         * Launch the instance.
         */
        producer.setNamesrvAddr("127.0.0.1:9876"); // <x> 哈哈哈哈
        producer.setVipChannelEnabled(false);
        producer.setSendMsgTimeout(15000);
        producer.start();

        for (int i = 0; i < 1; i++){
            try {
                Message msg = new Message("topicTest",//topic
                        "tagA",//tag
                        ("I am body -- " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)//body
                );

                SendResult sendResult = producer.send(msg);

                System.out.printf("%s%nsendResult:", sendResult);
            }  catch (Exception e) {
                e.printStackTrace();
            }
        }
        /*
         * Shut down once the producer instance is not longer in use.
         */
        producer.shutdown();
    }
}
