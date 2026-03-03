package com.trafficsignal.domain;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Intersection {

    private final String id;
    private final Map<Direction, TrafficLight> lights =
            new EnumMap<>(Direction.class);

    private final List<StateChangeRecord> history = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();

    private boolean paused = false;

    public Intersection(String id) {
        this.id = id;
        for (Direction d : Direction.values()) {
            lights.put(d, new TrafficLight(d));
        }
    }

    public String getId() {
        return id;
    }

    public Map<Direction, TrafficLight> getLights() {
        return lights;
    }

    public List<StateChangeRecord> getHistory() {
        return history;
    }

    public boolean isPaused() {
        return paused;
    }

    public void pause() {
        lock.lock();
        try {
            paused = true;
        } finally {
            lock.unlock();
        }
    }

    public void resume() {
        lock.lock();
        try {
            paused = false;
        } finally {
            lock.unlock();
        }
    }

    public void changeState(Direction direction, LightState newState) {
        lock.lock();
        try {
            lights.get(direction).setState(newState);
            history.add(new StateChangeRecord(direction, newState, Instant.now()));
        } finally {
            lock.unlock();
        }
    }

    public ReentrantLock getLock() {
        return lock;
    }
}