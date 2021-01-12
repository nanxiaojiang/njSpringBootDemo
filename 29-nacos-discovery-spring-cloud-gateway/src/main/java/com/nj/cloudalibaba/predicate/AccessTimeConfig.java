package com.nj.cloudalibaba.predicate;

import lombok.Data;

import java.time.LocalTime;

/**
 * @author 南江
 * @Description: ${todo}
 * @date 2021/1/12 23:29
 */
@Data
public class AccessTimeConfig {

    private LocalTime start;
    private LocalTime end;
}
