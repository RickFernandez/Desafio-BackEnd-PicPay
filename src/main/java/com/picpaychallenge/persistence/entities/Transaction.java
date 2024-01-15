package com.picpaychallenge.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    @ManyToMany
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToMany
    @JoinColumn(name = "receiver_id")
    private User receiver;

    private LocalDateTime timeStamp;
}
