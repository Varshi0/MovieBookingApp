package com.niranjan.movie.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.niranjan.movie.entity.Booking;
import com.niranjan.movie.entity.Movie;
import com.niranjan.movie.entity.Theatre;
import com.niranjan.movie.entity.User;
import com.niranjan.movie.repository.BookingRepository;
import com.niranjan.movie.repository.MovieRepository;
import com.niranjan.movie.repository.TheatreRepository;
import com.niranjan.movie.repository.UserRepository;

@RestController
@RequestMapping("/user/bookings")
public class BookingController {
    @Autowired
    private BookingRepository bookingRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private MovieRepository movieRepo;
    @Autowired
    private TheatreRepository theatreRepo;

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestParam Long userId, @RequestParam Long movieId, @RequestParam Long theatreId) {
        Optional<User> user = userRepo.findById(userId);
        Optional<Movie> movie = movieRepo.findById(movieId);
        Optional<Theatre> theatre = theatreRepo.findById(theatreId);
        if (user.isEmpty() || movie.isEmpty() || theatre.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid ID");
        }
        Booking b = new Booking();
        b.setUser(user.get());
        b.setMovie(movie.get());
        b.setTheatre(theatre.get());
        b.setBookingTime(LocalDateTime.now());
        return ResponseEntity.ok(bookingRepo.save(b));
    }

    @GetMapping("/{userId}")
    public List<Booking> getBookings(@PathVariable Long userId) {
        return bookingRepo.findByUserId(userId);
    }
}