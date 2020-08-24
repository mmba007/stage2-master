package com.enit.authentication.events;

import com.enit.authentication.model.EventName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


public   class Event {
    
	private EventName type;
	public Event(EventName type) {
		super();
		this.type = type;
	}

	public EventName getType() {
		return type;
	}

	public void setType(EventName type) {
		this.type = type;
	}

	public Event() {
		
		
	}
	
	
}
