package com.trafficsignal.domain;

import java.time.Instant;

public record StateChangeRecord(Direction direction,
                                LightState state,
                                Instant timestamp) {

}
