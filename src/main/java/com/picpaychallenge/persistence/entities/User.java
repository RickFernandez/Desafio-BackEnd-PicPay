package com.picpaychallenge.persistence.entities;

import com.picpaychallenge.dtos.requests.UserRequest;
import com.picpaychallenge.enums.UserTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserTypeEnum userType;

    public User(UserRequest userRequest) {
        this.firstName = userRequest.firstName();
        this.lastName = userRequest.lastName();
        this.document = userRequest.document();
        this.email = userRequest.email();
        this.password = userRequest.password();
        this.balance = userRequest.balance();
        this.userType = userRequest.userType();
    }
}
