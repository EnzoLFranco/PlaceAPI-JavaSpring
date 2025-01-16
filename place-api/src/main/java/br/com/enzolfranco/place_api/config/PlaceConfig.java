package br.com.enzolfranco.place_api.config;

import br.com.enzolfranco.place_api.domain.PlaceRepository;
import br.com.enzolfranco.place_api.domain.PlaceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlaceConfig {

    @Bean
    PlaceService placeService(PlaceRepository placeRepository){
        return new PlaceService(placeRepository);
    }
}
