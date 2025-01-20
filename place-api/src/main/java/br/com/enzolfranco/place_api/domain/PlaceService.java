package br.com.enzolfranco.place_api.domain;

import br.com.enzolfranco.place_api.api.PlaceRequest;
import com.github.slugify.Slugify;
import reactor.core.publisher.Mono;

public class PlaceService {
    private PlaceRepository placeRepository;
    public Slugify slg;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
        this.slg = Slugify.builder().build();
    }

    public Mono<Place> create(PlaceRequest placeRequest){
        var place = new Place(null, placeRequest.name(), slg.slugify(placeRequest.name()), placeRequest.state(),
                placeRequest.createdAt(), placeRequest.updatedAt());
        return placeRepository.save(place);
    }
}
