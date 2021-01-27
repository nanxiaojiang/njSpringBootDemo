package com.nj.rocketmq.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * @author 南江
 * @Description: 接收消息
 * @date 2021/1/26 0:59
 */
@Service
public class ReceiveService {

    //spring cloud stream里面发消息通过 Sink 发送
    @Autowired
    private Sink sink;

    @Autowired
    private MySink mySink;

    public void getMessage(){
        // SubscribableChannel = sink.input() 消息订阅的信道
        sink.input().subscribe((Message<?> message) -> {
            System.out.println(message.getPayload());
        });
    }

    @StreamListener(value = Sink.INPUT)
    public void getListener(String message){
        System.out.println("test-group="+message);
    }

    @StreamListener(value = MySink.INPUT1)
    public void getListener1(String message){
        System.out.println("test-group1="+message);
    }

    @StreamListener(value = MySink.INPUT2)
    public void getListener2(String message){
        System.out.println("test-group2="+message);
    }

    @StreamListener(value = MySink.INPUTTX)
    public void getListenerINPUTTX(String message){
        System.out.println("接收事务消息transaction-group="+message);
    }
}
