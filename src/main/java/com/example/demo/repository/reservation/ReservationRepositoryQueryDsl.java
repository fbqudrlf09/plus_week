package com.example.demo.repository.reservation;

import com.example.demo.entity.Reservation;

import java.util.List;

public interface ReservationRepositoryQueryDsl {

    List<Reservation> searchReservations(Long userId, Long itemId);
}
