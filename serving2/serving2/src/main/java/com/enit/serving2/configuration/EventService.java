package com.enit.serving2.configuration;




import com.enit.serving2.entity.ConsumerRequest;
import com.enit.serving2.events.Event;
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

    public void sendUserRequest(final ConsumerRequest request) {
        MessageChannel messageChannel = myStreams.outboundEvent();
        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        messageChannel.send(MessageBuilder
                .withPayload(request)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

}
