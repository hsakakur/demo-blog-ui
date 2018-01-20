package com.example.blog;

import am.ik.blog.entry.Entry;
import am.ik.blog.entry.EntryId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class UiController {
	private final WebClient webClient;
	private final Logger log = LoggerFactory.getLogger(UiController.class);

	public UiController(WebClient.Builder builder, BlogProperties props) {
		this.webClient = builder.baseUrl(props.getApi().getUri()).build();
	}

	@GetMapping(path = "/")
	public String index(Model model) {
		log.info("index");
		Flux<Entry> entries = Flux.empty(); // TODO: 演習3
		model.addAttribute("entries", entries);
		return "index";

	}

	@GetMapping(path = "entries/{entryId}")
	public String entry(@PathVariable EntryId entryId, Model model) {
		log.info("entry");
		Mono<Entry> entry = Mono.empty(); // TODO: 演習3
		model.addAttribute("entry", entry);
		return "entry";
	}
}
