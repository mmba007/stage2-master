package com.enit.usercrud.events;


import com.enit.usercrud.model.EventName;

public class EventWrapper<T extends Event> {

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
