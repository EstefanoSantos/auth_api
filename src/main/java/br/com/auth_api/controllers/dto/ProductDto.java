package br.com.auth_api.controllers.dto;

import java.math.BigDecimal;

public record ProductDto(String productName, BigDecimal price, String description) {
}
