package com.example.demo.repository.reservation;

import com.example.demo.entity.QReservation;
import com.example.demo.entity.Reservation;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepositoryQueryDslImpl implements ReservationRepositoryQueryDsl{

    private final JPAQueryFactory jpaQueryFactory;

    public ReservationRepositoryQueryDslImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Reservation> searchReservations(Long userId, Long itemId) {
        QReservation reservation = QReservation.reservation;
        BooleanBuilder builder = new BooleanBuilder();

        if (userId != null) {
            builder.and(reservation.user.id.eq(userId));
        }
        if (itemId != null) {
            builder.and(reservation.item.id.eq(itemId));
        }

        return jpaQueryFactory.selectFrom(reservation)
                .leftJoin(reservation.user).fetchJoin()
                .leftJoin(reservation.item).fetchJoin()
                .where(builder)
                .fetch();
    }
}
