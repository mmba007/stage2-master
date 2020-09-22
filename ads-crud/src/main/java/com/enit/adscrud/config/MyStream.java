package com.enit.adscrud.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

interface MyStream {

    String OUTPUT = "events-out";

    String OUTPUT_VIEW = "viewed-ads-out";

    @Output(OUTPUT)
    MessageChannel outboundEvent();

    @Output(OUTPUT_VIEW)
    MessageChannel outboundViewEvent();
}
