package com.nj.rocketmq.transaction;

import com.nj.rocketmq.producer.MySource;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import javax.xml.ws.handler.MessageContext;

/**
 * @author 南江
 * @Description: 发送事务消息
 * @date 2021/1/28 0:31
 */
@Component
public class Sender {

    @Autowired
    private MySource mySource;

    /**
     * 发送事务消息
     * @param msg   消息内容
     * @param num   次数
     * @param <T>
     */
    public <T> void sendTransactionalMessage(T msg, int num){
        MessageBuilder<T> builder = MessageBuilder.withPayload(msg)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .setHeader("test", String.valueOf(num));
        Message message = builder.build();
        boolean flag = mySource.outputTX().send(message);
        System.out.println(flag);
    }

    /**
     * 发送事务消息
     * @param msg   消息内容
     * @param tag   标签
     * @param <T>
     */
    public <T> void sendMessageTag(T msg, String tag){
        MessageBuilder<T> builder = MessageBuilder.withPayload(msg)
                .setHeader(MessageConst.PROPERTY_TAGS,tag)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);
        Message message = builder.build();
        boolean flag = mySource.output1().send(message);
        System.out.println(flag);
    }
}
