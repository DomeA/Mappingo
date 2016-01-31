package com.domeastudio.application.dto.resultSet;

import com.domeastudio.mappingo.application.util.serialization.JacksonMapper;

import java.io.Serializable;

/**
 * Created by domea on 16-1-23.
 * {
 *     code:{
 *         message:{
 *             dateTime:"",
 *             logLevel:"",
 *             text:""
 *         },
 *         number:"",
 *     },
 *     resultObject:{
 *
 *     }
 * }
 */
public abstract class OutputParameters implements Serializable {
    private SystemCode code;
    private JacksonMapper jacksonMapper;
    public SystemCode getCode() {
        return code;
    }

    public void setCode(SystemCode code) {
        this.code = code;
    }

    public String getOutputParameters(Object object){
        return jacksonMapper.toJson(object);
    }

    public JacksonMapper getJacksonMapper() {
        return jacksonMapper;
    }

    public void setJacksonMapper(JacksonMapper jacksonMapper) {
        this.jacksonMapper = jacksonMapper;
    }
}
