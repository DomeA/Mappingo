package com.domeastudio.application.dto.resultSet;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by domea on 16-1-23.
 */
public class MessageTemplate implements Serializable {
    private Date dateTime;
    private String logLevel;
    private String text;

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
