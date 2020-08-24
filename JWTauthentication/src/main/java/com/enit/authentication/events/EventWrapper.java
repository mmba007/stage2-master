package com.enit.authentication.events;

import com.enit.authentication.model.EventName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As.*;
import org.hibernate.annotations.Type;



class EventWrapper <T extends Event> {

    public T getData() {
        return data;
    }

    public EventWrapper(T data, EventName type) {
        this.data = data;
        this.type = type;
    }

    public EventWrapper() {
        
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setType(EventName type) {
        this.type = type;
    }

    public EventName getType() {
        return type;
    }

    private T data;
    private EventName type;

}
