package br.com.enzolfranco.place_api.web;

import br.com.enzolfranco.place_api.api.PlaceRequest;
import br.com.enzolfranco.place_api.api.PlaceResponse;
import br.com.enzolfranco.place_api.domain.Place;
import br.com.enzolfranco.place_api.domain.PlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a new place", description = "Creates a new place with the provided name and state.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Place created successfully",
                    content = @Content(schema = @Schema(implementation = PlaceResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request payload", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Mono<PlaceResponse>> create(@Valid @RequestBody PlaceRequest request) {
        var placeResponse = placeService.create(request).map(PlaceMapper::fromPlaceToResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(placeResponse);
    }

    @Operation(summary = "List all places", description = "Returns a list of all places.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List retrieved successfully",
                    content = @Content(schema = @Schema(implementation = PlaceResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping
    public Flux<Place> list(){
        return placeService.list();
    }

    @Operation(summary = "Update an existing place", description = "Updates the details of an existing place.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Place updated successfully",
                    content = @Content(schema = @Schema(implementation = PlaceResponse.class))),
            @ApiResponse(responseCode = "404", description = "Place not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Mono<PlaceResponse>> update(@PathVariable Long id,@RequestBody PlaceRequest request){
        var placeResponse = placeService.updateById(id, request).map(PlaceMapper::fromPlaceToResponse);
        return ResponseEntity.status(HttpStatus.OK).body(placeResponse);
    }

    @Operation(summary = "Delete a place", description = "Deletes a place by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Place deleted successfully",
                    content = @Content(schema = @Schema(implementation = PlaceResponse.class))),
            @ApiResponse(responseCode = "404", description = "Place not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<PlaceResponse>> deleteById(@PathVariable Long id){
        var placeResponse = placeService.deleteById(id).map(PlaceMapper::fromPlaceToResponse);
        return ResponseEntity.status(HttpStatus.OK).body(placeResponse);
    }

    @Operation(summary = "Find a place by ID", description = "Retrieves a specific place by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Place retrieved successfully",
                    content = @Content(schema = @Schema(implementation = PlaceResponse.class))),
            @ApiResponse(responseCode = "404", description = "Place not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Mono<PlaceResponse>> findById(@PathVariable Long id){
        var placeResponse = placeService.findById(id).map(PlaceMapper::fromPlaceToResponse);
        return ResponseEntity.status(HttpStatus.OK).body(placeResponse);
    }

}
