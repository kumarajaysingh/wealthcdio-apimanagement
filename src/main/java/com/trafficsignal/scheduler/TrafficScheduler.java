package com.trafficsignal.scheduler;

import com.trafficsignal.domain.Direction;
import com.trafficsignal.domain.LightState;
import com.trafficsignal.repository.IntersectionRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TrafficScheduler {

    private final IntersectionRepository repository;

    public TrafficScheduler(IntersectionRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedRate = 8000)
    public void cycle() {
        repository.findAll().values().forEach(intersection -> {

            if (intersection.isPaused()) return;

            intersection.getLock().lock();
            try {
                intersection.getLights().values()
                        .forEach(l -> l.setState(LightState.RED));

                intersection.changeState(Direction.NORTH, LightState.GREEN);
                intersection.changeState(Direction.SOUTH, LightState.GREEN);

            } finally {
                intersection.getLock().unlock();
            }
        });
    }
}
