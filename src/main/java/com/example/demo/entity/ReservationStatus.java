package com.example.demo.entity;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    PENDING("PENDING"), APPROVED("APPROVED"), CANCELED("CANCELED"), EXPIRED("EXPIRED");

    private final String name;

    ReservationStatus(String name) {
        this.name = name;
    }

    public static ReservationStatus of(String status) {
        for (ReservationStatus reservationStatus : values()) {
            if (reservationStatus.getName().equals(status)) {
                return reservationStatus;
            }
        }

        throw new IllegalArgumentException("해당하는 예약 상태를 찾을 수 없습니다: " + status);
    }
}
