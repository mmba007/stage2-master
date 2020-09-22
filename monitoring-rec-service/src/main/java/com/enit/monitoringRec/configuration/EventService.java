package com.enit.monitoringRec.configuration;




import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.enit.monitoringRec.entity.ConsumerRequest;
import com.enit.monitoringRec.events.Event;

@Service
public class EventService {
    private final MyStream myStreams;

    public EventService(MyStream myStreams) {
        this.myStreams = myStreams;

    }

    // public void sendUserRequest(final ConsumerRequest request) {
    //     MessageChannel messageChannel = myStreams.outboundEvent();
    //     System.out.println("######################  SEND REQUEST ########################");
    //     messageChannel.send(MessageBuilder
    //             .withPayload(request)
    //             .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
    //             .build());
    // }

}
