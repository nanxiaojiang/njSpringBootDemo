package com.nj.rocketmq;

import com.nj.rocketmq.consumer.ReceiveService;
import com.nj.rocketmq.producer.SenderService;
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
@EnableBinding({Source.class, Sink.class})
@SpringBootApplication
public class MQApplication implements CommandLineRunner {

    @Autowired
    private SenderService senderService;

    @Autowired
    private ReceiveService receiveService;

    public static void main(String[] args) {
        SpringApplication.run(MQApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        senderService.sendMessage("hello rocketmq-srping-cloud-stream2");
        receiveService.getMessage();
    }
}
