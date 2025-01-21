package br.com.enzolfranco.place_api.api;

import jakarta.validation.constraints.NotBlank;


public record PlaceRequest(
        @NotBlank String name,
        @NotBlank String state
) {
}
