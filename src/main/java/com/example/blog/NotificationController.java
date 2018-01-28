package com.example.blog;

import com.fasterxml.jackson.databind.JsonNode;
import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
public class NotificationController {
	Flux<JsonNode> events;

	public NotificationController(WebClient.Builder builder, BlogProperties props) {
		WebClient webClient = builder.baseUrl(props.getApi().getUri()).build();
		this.events = webClient.get() //
				.uri("/v1/notification") //
				.header(ACCEPT, TEXT_EVENT_STREAM_VALUE) //
				.retrieve() //
				.bodyToFlux(JsonNode.class) //
				.share();
	}

	@GetMapping("notification")
	public Flux<JsonNode> notification() {
		return this.events;
	}
}
