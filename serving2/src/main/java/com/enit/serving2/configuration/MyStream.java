package com.enit.serving2.configuration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MyStream {

    String OUTPUT = "request-out";
    String INPUT_LOGIN = "login-in";
    String INPUT_RECOMMANDATION="recommandation-in";

    @Output(OUTPUT)
    MessageChannel outboundEvent();

    @Input(INPUT_LOGIN)
    SubscribableChannel inboundLogin();

    @Input(INPUT_RECOMMANDATION)
    SubscribableChannel inboundRecommandation();
}