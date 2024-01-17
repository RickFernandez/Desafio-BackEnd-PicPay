package com.picpaychallenge.dtos.requests;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record TransactionRequest(
        @NotNull
        BigDecimal value,
        @NotNull
        Long senderId,
        @NotNull
        Long receiverId
) { }
