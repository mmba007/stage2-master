package com.enit.usercrud.service;

import com.enit.usercrud.events.DeleteUserEvent;
import com.enit.usercrud.events.Event;
import com.enit.usercrud.events.RegisterUserEvent;
import com.enit.usercrud.model.EventName;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ProgramDeserializer extends JsonDeserializer<Event> {
  @Override
  public Event deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    ObjectCodec oc = jp.getCodec();
    JsonNode node = oc.readTree(jp);
    final String type = node.get("type").asText();
    if(type.equals("REGISTER_USER")){
      final String username = node.get("username").asText();
      final String lastname = node.get("lastname").asText();
      final String firstname = node.get("firstname").asText();
      final String password = node.get("password").asText();
      final String email = node.get("email").asText();
      final Set<String> rolesSet = new HashSet<>();
      JsonNode roles= node.get("role");
      for(JsonNode n:roles )
      {

        rolesSet.add(n.asText());


      }

      return new RegisterUserEvent(username,email,rolesSet,password,firstname,lastname) ;
    }

     return new DeleteUserEvent(node.asText("username"));
  }
}
