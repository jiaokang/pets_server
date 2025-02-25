package com.youlai.boot.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response <T>{

    private T data;
}
