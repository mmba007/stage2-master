package com.enit.adscrud.config;


import com.enit.adscrud.events.Event;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class EventService {
    private final MyStream myStreams;

    public EventService(MyStream myStreams) {
        this.myStreams = myStreams;

    }

    public void sendEvent(final Event event) {
        MessageChannel messageChannel = myStreams.outboundEvent();
        messageChannel.send(MessageBuilder
                .withPayload(event)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}