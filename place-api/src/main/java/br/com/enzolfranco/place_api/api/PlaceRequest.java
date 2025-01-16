package br.com.enzolfranco.place_api.api;

import java.time.LocalDateTime;

public record PlaceRequest(
        String name, String slug, String state, LocalDateTime createdAt, LocalDateTime updatedAt
) {
}
