package com.example.demo.repository.reservation;

import com.example.demo.entity.Reservation;
import com.example.demo.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>, ReservationRepositoryQueryDsl {

    List<Reservation> findByUserIdAndItemId(Long userId, Long itemId);

    List<Reservation> findByUserId(Long userId);

    List<Reservation> findByItemId(Long itemId);

    @Query("SELECT r FROM Reservation r " +
            "WHERE r.item.id = :id " +
            "AND NOT (r.endAt <= :startAt OR r.startAt >= :endAt) " +
            "AND r.status = 'APPROVED'")
    List<Reservation> findConflictingReservations(
            @Param("id") Long id,
            @Param("startAt") LocalDateTime startAt,
            @Param("endAt") LocalDateTime endAt
    );

    @Query("select distinct r from Reservation  r join fetch r.item join fetch r.user")
    List<Reservation> findAllItemAndUser();

    default Reservation findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 ID에 맞는 값이 존재하지 않습니다."));
    }
}
