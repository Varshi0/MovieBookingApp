package com.niranjan.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niranjan.movie.entity.Theatre;
import com.niranjan.movie.repository.TheatreRepository;

@RestController
@RequestMapping("/admin/theatres")
public class TheatreController {
    @Autowired
    private TheatreRepository theatreRepo;

    @PostMapping
    public Theatre create(@RequestBody Theatre theatre) {
        return theatreRepo.save(theatre);
    }

    @GetMapping
    public List<Theatre> list() {
        return theatreRepo.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Theatre> update(@PathVariable Long id, @RequestBody Theatre updated) {
        return theatreRepo.findById(id).map(t -> {
            t.setName(updated.getName());
            t.setLocation(updated.getLocation());
            return ResponseEntity.ok(theatreRepo.save(t));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!theatreRepo.existsById(id)) return ResponseEntity.notFound().build();
        theatreRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
