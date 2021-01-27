package com.nj.rocketmq.consumer.Model;

import lombok.Builder;
import lombok.Data;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/28 0:55
 */
@Builder
@Data
public class User {
    private Integer id;
    private String username;
    private Integer age;
}
