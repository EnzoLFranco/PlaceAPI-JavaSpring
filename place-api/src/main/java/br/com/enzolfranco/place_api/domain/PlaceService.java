package br.com.enzolfranco.place_api.domain;

import br.com.enzolfranco.place_api.api.PlaceRequest;
import com.github.slugify.Slugify;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class PlaceService {
    private PlaceRepository placeRepository;
    public Slugify slg;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
        this.slg = Slugify.builder().build();
    }

    public Mono<Place> create(PlaceRequest placeRequest){
        var place = new Place(null, placeRequest.name(), slg.slugify(placeRequest.name()), placeRequest.state(),
                null, null);
        return placeRepository.save(place);
    }

    public Flux<Place> list(){
        return placeRepository.findAll();
    }

    public Mono<Place> updateById(Long id, PlaceRequest placeRequest){
        return placeRepository.findById(id).flatMap(existingPlace ->{
                var updatedPlace = new Place(
                        existingPlace.id(),
                        placeRequest.name(),
                        placeRequest.state(),
                        slg.slugify(placeRequest.name()),
                        existingPlace.createdAt(),
                        LocalDateTime.now()
                );
            return placeRepository.save(updatedPlace);
        })
                .switchIfEmpty(Mono.error(new RuntimeException("Place not found")));
    }

    public Mono<Place> deleteById(Long id){
        return placeRepository.findById(id).flatMap(existingPlace ->
            placeRepository.deleteById(id).
                thenReturn(existingPlace)
        )
                .switchIfEmpty(Mono.error(new RuntimeException("Place not found")));
    }

}
