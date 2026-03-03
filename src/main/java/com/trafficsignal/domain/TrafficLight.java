package com.trafficsignal.domain;

public class TrafficLight {

    private final Direction direction;
    private LightState state = LightState.RED;

    public TrafficLight(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public LightState getState() {
        return state;
    }

    public void setState(LightState state) {
        this.state = state;
    }
}