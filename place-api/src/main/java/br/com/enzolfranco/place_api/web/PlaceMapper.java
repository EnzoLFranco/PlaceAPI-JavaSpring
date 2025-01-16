package br.com.enzolfranco.place_api.web;

import br.com.enzolfranco.place_api.api.PlaceResponse;
import br.com.enzolfranco.place_api.domain.Place;

public class PlaceMapper {
    public static PlaceResponse fromPlaceToResponse(Place place){
        return new PlaceResponse(place.name(), place.slug(), place.state(), place.createdAt(), place.updatedAt());
    }
}
