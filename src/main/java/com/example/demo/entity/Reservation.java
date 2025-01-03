package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

//    private String status; // PENDING, APPROVED, CANCELED, EXPIRED
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    public Reservation(Item item, User user, ReservationStatus status, LocalDateTime startAt, LocalDateTime endAt) {
        this.item = item;
        this.user = user;
        this.status = status;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public Reservation() {}

    public void updateStatus(ReservationStatus status) {
        this.status = status;
    }
}
