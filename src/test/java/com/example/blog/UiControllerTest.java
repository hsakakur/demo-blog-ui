package com.example.blog;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okio.Buffer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "blog.api.uri=http://localhost:54437")
public class UiControllerTest {
	MockWebServer server = new MockWebServer();
	WebTestClient webClient;
	@Autowired
	ConfigurableApplicationContext context;

	@Before
	public void setup() throws Exception {
		this.server.start(54437);
		this.webClient = WebTestClient.bindToApplicationContext(context).build();
	}

	@After
	public void shutdown() throws Exception {
		this.server.shutdown();
	}

	@Test
	public void index() throws Exception {
		this.server.enqueue(new MockResponse()
				.setHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
				.setBody(new Buffer().readFrom(
						new ClassPathResource("get-entries.json").getInputStream())));

		this.webClient.get() //
				.uri("/") //
				.exchange() //
				.expectStatus().isOk() //
				.expectBody(String.class) //
				.consumeWith(b -> {
					String body = b.getResponseBody();
					assertThat(body).contains("Title2");
					assertThat(body).contains("Title1");
				});
	}

	@Test
	public void entry() throws Exception {
		this.server.enqueue(new MockResponse()
				.setHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
				.setBody(new Buffer().readFrom(
						new ClassPathResource("get-entry.json").getInputStream())));

		this.webClient.get() //
				.uri("/entries/100") //
				.exchange() //
				.expectStatus().isOk() //
				.expectBody(String.class) //
				.consumeWith(b -> {
					String body = b.getResponseBody();
					assertThat(body).contains("Title1");
					assertThat(body).contains("Hello1");
				});
	}
}