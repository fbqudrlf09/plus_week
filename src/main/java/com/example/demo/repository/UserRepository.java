package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);


    @Modifying(clearAutomatically = true)
    @Query("update User u set u.status = 'BLOCKED' where u.id in (:idList)")
    int updateStatusBlock(@Param("idList") List<Long> idList);

    List<User> findByStatus(String status);

    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 ID에 맞는 값이 존재하지 않습니다.")
        );
    }
}
