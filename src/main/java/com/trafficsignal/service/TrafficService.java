package com.trafficsignal.service;

import com.trafficsignal.domain.Direction;
import com.trafficsignal.domain.Intersection;
import com.trafficsignal.domain.LightState;
import com.trafficsignal.repository.IntersectionRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TrafficService {

    private final IntersectionRepository repository;

    // Example conflict rule: NS conflict with EW
    private static final Set<Direction> NS =
            Set.of(Direction.NORTH, Direction.SOUTH);

    private static final Set<Direction> EW =
            Set.of(Direction.EAST, Direction.WEST);

    public TrafficService(IntersectionRepository repository) {
        this.repository = repository;
    }

    public Intersection create(String id) {
        return repository.save(new Intersection(id));
    }

    public Intersection get(String id) {
        return repository.find(id);
    }

    public void pause(String id) {
        repository.find(id).pause();
    }

    public void resume(String id) {
        repository.find(id).resume();
    }

    public void changeLight(String id, Direction direction, LightState state) {
        Intersection intersection = repository.find(id);

        intersection.getLock().lock();
        try {
            if (state == LightState.GREEN) {
                validateNoConflict(intersection, direction);
            }

            intersection.changeState(direction, state);

        } finally {
            intersection.getLock().unlock();
        }
    }

    private void validateNoConflict(Intersection intersection,
                                    Direction direction) {

        boolean isNS = NS.contains(direction);
        Set<Direction> conflicting = isNS ? EW : NS;

        conflicting.forEach(d -> {
            if (intersection.getLights()
                    .get(d).getState() == LightState.GREEN) {
                throw new IllegalStateException(
                        "Conflict detected: " + d + " already GREEN");
            }
        });
    }
}
