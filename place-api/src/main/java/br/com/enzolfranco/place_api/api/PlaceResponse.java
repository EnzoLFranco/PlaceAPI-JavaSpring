package br.com.enzolfranco.place_api.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Response object representing a place")
public record PlaceResponse(
        @Schema(description = "The name of the place", example = "São Paulo")
        String name,
        @Schema(description = "An abbreviation of the place's name to the URL", example = "sao-paulo")
        String slug,
        @Schema(description = "The state where the place is located", example = "SP")
        String state,
        @Schema(description = "The datetime that the place was created")
        LocalDateTime createdAt,
        @Schema(description = "The last datetime that the place was updated")
        LocalDateTime updatedAt
) {
}
