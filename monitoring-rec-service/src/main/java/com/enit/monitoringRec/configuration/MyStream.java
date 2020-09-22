package com.enit.monitoringRec.configuration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MyStream {

    // String OUTPUT = "request-out";
    String INPUT_VIEWED_ADS = "viewed-ads-in";
    String INPUT_RECOMMANDATION="recommandation-in";

    // @Output(OUTPUT)
    // MessageChannel outboundEvent();

    @Input(INPUT_VIEWED_ADS)
    SubscribableChannel inboundViewedAds();

    @Input(INPUT_RECOMMANDATION)
    SubscribableChannel inboundRecommandation();
}
