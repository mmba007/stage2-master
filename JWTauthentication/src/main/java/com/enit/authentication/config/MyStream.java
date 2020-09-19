package com.enit.authentication.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

interface MyStream {

    String OUTPUT = "events-out";
    String OUTPUT_LOGIN = "login-out";
    @Output(OUTPUT)
    MessageChannel outboundEvent();

    @Output(OUTPUT_LOGIN)
    MessageChannel outboundLogin();
}