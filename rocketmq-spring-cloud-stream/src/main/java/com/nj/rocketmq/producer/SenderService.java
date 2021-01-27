package com.nj.rocketmq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @author 南江
 * @Description: 发送消息
 * @date 2021/1/26 0:49
 */
@Service
public class SenderService {

    //spring cloud stream里面发消息通过 Source 发送
    @Autowired
    private Source source;

    @Autowired
    private MySource mySource;

    /**
     * 发送消息的方法
     * @param message
     */
    public void sendMessage(String message){
        boolean send = source.output().send(MessageBuilder.withPayload(message).build());
        System.out.println(send);
    }

    /**
     * 自定义发送消息
     * @param message
     */
    public void muliMessage(String message){
        //源码发送消息
        boolean send = source.output().send(MessageBuilder.withPayload(message).build());
        System.out.println(send);

        //自定义MySource.output1发送消息
        boolean send1 = mySource.output1().send(MessageBuilder.withPayload(message).build());
        System.out.println(send1);

        //自定义MySource.output1发送消息
        boolean send2 = mySource.output2().send(MessageBuilder.withPayload(message).build());
        System.out.println(send2);


    }
}
