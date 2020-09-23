package com.enit.monitoringRec.configuration;




import org.springframework.stereotype.Service;

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
