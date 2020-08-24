//package com.enit.randomrecommandationservice.entity;
//
//import java.io.IOException;
//import java.util.List;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.SerializerProvider;
//
//class ListOfStringsSerializer extends JsonSerializer<List<String>> {
//
//	@Override
//	public void serialize(List<String> value, JsonGenerator jgen, SerializerProvider provider)
//			throws IOException, JsonProcessingException {
//		jgen.writeStartArray();
//		for (String string : value) {
//			jgen.writeString(string);
////            jgen.writeStartObject();
////            jgen.writeObjectField("model", string);
////            jgen.writeEndObject();
//		}
//		jgen.writeEndArray();
//	}
//
//}