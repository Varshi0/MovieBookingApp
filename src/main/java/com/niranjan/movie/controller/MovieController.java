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

import com.niranjan.movie.entity.Movie;
import com.niranjan.movie.repository.MovieRepository;

//Movie Controller
@RestController
@RequestMapping("/admin/movies")
public class MovieController {
 @Autowired
 private MovieRepository movieRepo;

 @PostMapping
 public Movie create(@RequestBody Movie movie) {
     return movieRepo.save(movie);
 }

 @GetMapping
 public List<Movie> list() {
     return movieRepo.findAll();
 }

 @PutMapping("/{id}")
 public ResponseEntity<Movie> update(@PathVariable Long id, @RequestBody Movie updated) {
     return movieRepo.findById(id).map(m -> {
         m.setTitle(updated.getTitle());
         m.setGenre(updated.getGenre());
         m.setLanguage(updated.getLanguage());
         return ResponseEntity.ok(movieRepo.save(m));
     }).orElse(ResponseEntity.notFound().build());
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<?> delete(@PathVariable Long id) {
     if (!movieRepo.existsById(id)) return ResponseEntity.notFound().build();
     movieRepo.deleteById(id);
     return ResponseEntity.ok().build();
 }
}
