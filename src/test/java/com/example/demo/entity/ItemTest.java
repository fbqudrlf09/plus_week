package com.example.demo.entity;

import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemTest {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Item Entity Test 추가하기")
    void itemEntityTest() {
        // given
        User user = new User("user", "이메일1", "닉네임", "비밀번호");
        Item item = new Item("이름", "설명", user, user);
        // when
        userRepository.save(user);
        itemRepository.save(item);

        Item findItem = itemRepository.findByIdOrElseThrow(1L);

        // then
        Assertions.assertThat(findItem.getStatus()).isEqualTo("PENDING");
    }
}