package com.domeastudio.application.dto.resultSet;

import java.io.Serializable;

/**
 * Created by domea on 16-1-23.
 */
public class SystemCode implements Serializable {
    private MessageTemplate message;
    private String number;

    public MessageTemplate getMessage() {
        return message;
    }

    public void setMessage(MessageTemplate message) {
        this.message = message;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
