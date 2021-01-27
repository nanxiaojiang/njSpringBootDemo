package com.nj.rocketmq.consumer;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/27 23:24
 */
public interface MySink {

    String INPUT1 = "input1";

    @Input(MySink.INPUT1)
    SubscribableChannel input1();

    String INPUT2= "input2";

    @Input(MySink.INPUT2)
    SubscribableChannel input2();

    String INPUTTX= "inputTX";

    @Input(MySink.INPUTTX)
    SubscribableChannel inputTX();
}
