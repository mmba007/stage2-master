package com.enit.adscrud.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

interface MyStream {

    String OUTPUT = "events-out";

    @Output(OUTPUT)
    MessageChannel outboundEvent();
}