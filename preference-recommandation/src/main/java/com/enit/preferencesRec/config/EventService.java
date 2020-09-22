package com.enit.preferencesRec.config;



import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.enit.preferencesRec.entity.ListRecommandation;

@Service
public class EventService {
    private final MyStream myStreams;

    public EventService(MyStream myStreams) {
        this.myStreams = myStreams;

    }

    public void sendUserRecommandation(final ListRecommandation recommandations) {
        MessageChannel messageChannel = myStreams.outboundRecommandations();
        messageChannel.send(MessageBuilder
                .withPayload(recommandations)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }

}
