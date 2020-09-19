package com.enit.usercrud.events;


import com.enit.usercrud.model.EventName;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;

//@JsonDeserialize( using = ProgramDeserializer.class )
public class Event {

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
