package br.com.enzolfranco.place_api;

import br.com.enzolfranco.place_api.api.PlaceRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;

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

	@Test
	public void testList(){
		webTestClient
				.get()
				.uri("/places")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray();
	}

	@Test
	public void testUpdateSucess(){
		var id = 1;
		var updatedName = "New name";
		var updatedSlug = "new-name";
		var updatedState = "New state";

		webTestClient
				.put()
				.uri("/places/{id}", id)
				.bodyValue(new PlaceRequest(updatedName, updatedState))
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("name").isEqualTo(updatedName)
				.jsonPath("slug").isEqualTo(updatedSlug)
				.jsonPath("state").isEqualTo(updatedState)
				.jsonPath("updatedAt").isEqualTo(LocalDateTime.now());
	}

	@Test
	public void testUpdateFailure(){
		var id = 999;
		var updatedName = "New name";
		var updatedState = "New state";

		webTestClient
				.put()
				.uri("/places/{id}", id)
				.bodyValue(new PlaceRequest(updatedName, updatedState))
				.exchange()
				.expectStatus().isNotFound()
				.expectBody()
				.jsonPath("message").isEqualTo("Place not found");
	}

}
