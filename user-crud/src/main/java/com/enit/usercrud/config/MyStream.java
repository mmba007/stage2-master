package com.enit.usercrud.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MyStream {

    String INPUT_USER_EVENT = "user-event-in";
    String OUTPUT_USER_EVENT="user-event-out";


    @Input(INPUT_USER_EVENT)
    SubscribableChannel inboundUserEvent();

    @Output(OUTPUT_USER_EVENT)
    MessageChannel outboundUserEvent();


}