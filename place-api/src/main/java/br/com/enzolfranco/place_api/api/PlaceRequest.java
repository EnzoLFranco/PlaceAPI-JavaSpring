package br.com.enzolfranco.place_api.api;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request object for creating or updating a place")
public record PlaceRequest(
        @Schema(description = "The name of the place", example = "São Paulo", required = true)
        @NotBlank
        String name,
        @Schema(description = "The state where the place is located", example = "SP", required = true)
        @NotBlank
        String state
) {
}
