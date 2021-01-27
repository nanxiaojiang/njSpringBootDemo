package com.nj.rocketmq.producer;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author 南江
 * @Description: 自定义Source
 * @date 2021/1/27 23:22
 */
public interface MySource {

    String OUTPUT1 = "output1";

    @Output(MySource.OUTPUT1)
    MessageChannel output1();



    String OUTPUT2 = "output2";

    @Output(MySource.OUTPUT2)
    MessageChannel output2();



    String OUTPUTTX = "outputTX";

    @Output(MySource.OUTPUTTX)
    MessageChannel outputTX();
}
