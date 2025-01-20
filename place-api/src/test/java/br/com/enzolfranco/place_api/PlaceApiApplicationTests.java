package br.com.enzolfranco.place_api;

import br.com.enzolfranco.place_api.api.PlaceRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PlaceApiApplicationTests {
	@Autowired
	WebTestClient webTestClient;

	@Test
	public void testCreatePlaceSucess(){
		var name = "New Name";
		var state = "State";
		var slug = "new-name";

		webTestClient
				.post()
				.uri("/places")
				.bodyValue(
						new PlaceRequest(name, state))
				.exchange()
				.expectBody()
				.jsonPath("name").isEqualTo(name)
				.jsonPath("slug").isEqualTo(slug)
				.jsonPath("state").isEqualTo(state)
				.jsonPath("createdAt").isNotEmpty()
				.jsonPath("updatedAt").isNotEmpty();
	}

	@Test
	public void testCreatePlaceFailure(){
		var name = "";
		var state = "";

		webTestClient
				.post()
				.uri("/places")
				.bodyValue(
						new PlaceRequest(name, state))
				.exchange()
				.expectStatus().isBadRequest();
	}

}
