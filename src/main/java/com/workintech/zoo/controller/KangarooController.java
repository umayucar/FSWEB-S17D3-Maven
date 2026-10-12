package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    private final Map<Integer, Kangaroo> kangaroos = new HashMap<>();

    @GetMapping
    public List<Kangaroo> getAllKangaroos() {
        return new ArrayList<>(kangaroos.values());
    }

    @GetMapping("/{id}")
    public Kangaroo getKangarooById(@PathVariable int id) {
        Kangaroo kangaroo = kangaroos.get(id);

        if (kangaroo == null) {
            throw new ZooException(
                    "Kangaroo not found with id: " + id,
                    HttpStatus.NOT_FOUND
            );
        }

        return kangaroo;
    }

    @PostMapping
    public Kangaroo addKangaroo(@RequestBody Kangaroo kangaroo) {
        if (kangaroo.getId() <= 0 || kangaroo.getName() == null
                || kangaroo.getGender() == null
                || kangaroo.getHeight() <= 0
                || kangaroo.getWeight() <= 0) {

            throw new ZooException(
                    "Invalid kangaroo data",
                    HttpStatus.BAD_REQUEST
            );
        }

        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo updateKangaroo(
            @PathVariable int id,
            @RequestBody Kangaroo kangaroo) {

        if (!kangaroos.containsKey(id)) {
            throw new ZooException(
                    "Kangaroo not found with id: " + id,
                    HttpStatus.NOT_FOUND
            );
        }

        if (kangaroo.getName() == null || kangaroo.getGender() == null
                || kangaroo.getHeight() <= 0
                || kangaroo.getWeight() <= 0) {

            throw new ZooException(
                    "Invalid kangaroo data",
                    HttpStatus.BAD_REQUEST
            );
        }

        kangaroo.setId(id);
        kangaroos.put(id, kangaroo);

        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo deleteKangaroo(@PathVariable int id) {
        Kangaroo kangaroo = kangaroos.remove(id);

        if (kangaroo == null) {
            throw new ZooException(
                    "Kangaroo not found with id: " + id,
                    HttpStatus.NOT_FOUND
            );
        }

        return kangaroo;
    }
}