package com.picpaychallenge.persistence.repositories;

import com.picpaychallenge.dtos.requests.UserRequest;
import com.picpaychallenge.enums.UserTypeEnum;
import com.picpaychallenge.persistence.entities.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;


@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("Should get User successfully from DB")
    void findUserByDocumentCase1() {
        String document = "123.456.789-01";
        UserRequest data = new UserRequest("Henrique", "Teste", document, new BigDecimal(10), "teste@email.com", "Senha123", UserTypeEnum.COMMON);
        createUser(data);

        Optional<User> result = userRepository.findUserByDocument(document);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get User from DB when User not exist")
    void findUserByDocumentCase2() {
        String document = "123.456.789-01";

        Optional<User> result = userRepository.findUserByDocument(document);

        assertThat(result.isEmpty()).isTrue();
    }

    private User createUser(UserRequest data) {
        User newUser = new User(data);
        entityManager.persist(newUser);
        return newUser;
    }
}