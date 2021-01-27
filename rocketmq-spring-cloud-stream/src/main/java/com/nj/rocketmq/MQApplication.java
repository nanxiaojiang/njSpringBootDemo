package com.nj.rocketmq;

import com.nj.rocketmq.consumer.Model.User;
import com.nj.rocketmq.consumer.MySink;
import com.nj.rocketmq.consumer.ReceiveService;
import com.nj.rocketmq.producer.MySource;
import com.nj.rocketmq.producer.SenderService;
import com.nj.rocketmq.transaction.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/26 0:47
 */
@EnableBinding({Source.class, Sink.class, MySource.class, MySink.class})
@SpringBootApplication
public class MQApplication implements CommandLineRunner {

    @Autowired
    private SenderService senderService;

    @Autowired
    private ReceiveService receiveService;

    @Autowired
    private Sender sender;

    public static void main(String[] args) {
        SpringApplication.run(MQApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
//        senderService.sendMessage("hello rocketmq-srping-cloud-stream2");
//        receiveService.getMessage();
//        senderService.muliMessage("hello 发送自定义！！！");
        sender.sendMessageTag(User.builder().id(1).username("nj").age(18).build(),"myTag");
//        sender.sendTransactionalMessage("transactionMessage",1);
//        sender.sendTransactionalMessage("transactionMessage",2);
//        sender.sendTransactionalMessage("transactionMessage",3);
    }
}
