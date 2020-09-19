package com.enit.randomrecommandationservice.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MyStream {

    String INPUT_EVENT = "event-in";
    String INPUT_REQUEST = "request-in";
    String OUTPUT_RECOMMANDATION="recommandation_out";

    @Output(OUTPUT_RECOMMANDATION)
    MessageChannel outboundRecommandations();

    @Input(INPUT_EVENT)
    SubscribableChannel inboundEvent();

    @Input(INPUT_REQUEST)
    SubscribableChannel inboundRequest();
}