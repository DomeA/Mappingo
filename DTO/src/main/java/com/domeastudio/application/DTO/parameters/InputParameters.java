package com.domeastudio.application.DTO.parameters;

import com.domeastudio.mappingo.application.util.serialization.JacksonMapper;

import java.io.Serializable;

/**
 * Created by domea on 16-1-23.
 */
public abstract class InputParameters implements Serializable {
    private JacksonMapper jacksonMapper;
    public  <T> T getInputParameters(String object, Class<T> clazz){
        return jacksonMapper.fromJson(object,clazz);
    }

    public JacksonMapper getJacksonMapper() {
        return jacksonMapper;
    }

    public void setJacksonMapper(JacksonMapper jacksonMapper) {
        this.jacksonMapper = jacksonMapper;
    }
}
