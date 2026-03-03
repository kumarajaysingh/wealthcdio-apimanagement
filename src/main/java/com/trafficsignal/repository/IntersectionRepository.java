package com.trafficsignal.repository;

import com.trafficsignal.domain.Intersection;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Repository
public class IntersectionRepository {

    private final Map<String, Intersection> store = new ConcurrentHashMap<>();

    public Intersection save(Intersection intersection) {
        store.put(intersection.getId(), intersection);
        return intersection;
    }

    public Intersection find(String id) {
        return store.get(id);
    }

    public Map<String, Intersection> findAll() {
        return store;
    }
}
