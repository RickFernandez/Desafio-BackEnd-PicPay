package com.picpaychallenge.dtos.requests;

import com.picpaychallenge.enums.UserType;

import java.math.BigDecimal;

public record UserRequest(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
}
