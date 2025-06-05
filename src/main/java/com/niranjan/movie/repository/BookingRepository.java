package com.niranjan.movie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.niranjan.movie.entity.Booking;

public interface BookingRepository extends JpaRepository <Booking,Long>{

	List<Booking> findByUserId(Long userId);
}
