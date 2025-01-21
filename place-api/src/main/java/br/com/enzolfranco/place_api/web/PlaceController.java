package br.com.enzolfranco.place_api.web;

import br.com.enzolfranco.place_api.api.PlaceRequest;
import br.com.enzolfranco.place_api.api.PlaceResponse;
import br.com.enzolfranco.place_api.domain.Place;
import br.com.enzolfranco.place_api.domain.PlaceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/places")
public class PlaceController {
    private PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping
    public ResponseEntity<Mono<PlaceResponse>> create(@Valid @RequestBody PlaceRequest request) {
        var placeResponse = placeService.create(request).map(PlaceMapper::fromPlaceToResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(placeResponse);
    }

    @GetMapping
    public Flux<Place> list(){
        return placeService.list();
    }

}
