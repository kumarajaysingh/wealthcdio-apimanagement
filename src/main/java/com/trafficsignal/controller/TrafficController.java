package com.trafficsignal.controller;

import com.trafficsignal.domain.Direction;
import com.trafficsignal.domain.Intersection;
import com.trafficsignal.domain.LightState;
import com.trafficsignal.service.TrafficService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/intersections")
public class TrafficController {

    private final TrafficService service;

    public TrafficController(TrafficService service) {
        this.service = service;
    }

    @PostMapping("/{id}")
    public Intersection create(@PathVariable String id) {
        return service.create(id);
    }

    @GetMapping("/{id}")
    public Intersection get(@PathVariable String id) {
        return service.get(id);
    }

    @PostMapping("/{id}/pause")
    public void pause(@PathVariable String id) {
        service.pause(id);
    }

    @PostMapping("/{id}/resume")
    public void resume(@PathVariable String id) {
        service.resume(id);
    }

    @PutMapping("/{id}/lights/{direction}")
    public void change(
            @PathVariable String id,
            @PathVariable Direction direction,
            @RequestParam LightState state) {

        service.changeLight(id, direction, state);
    }

    @GetMapping("/{id}/history")
    public Object history(@PathVariable String id) {
        return service.get(id).getHistory();
    }
}
