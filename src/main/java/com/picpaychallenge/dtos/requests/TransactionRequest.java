package com.picpaychallenge.dtos.requests;

import java.math.BigDecimal;

public record TransactionRequest(BigDecimal value, Long senderId, Long receiverId) {
}
