package com.example.blog;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "blog")
@Validated
public class BlogProperties {
	@Valid
	private Service api;

	public Service getApi() {
		return api;
	}

	public void setApi(Service api) {
		this.api = api;
	}

	public static class Service {
		@NotEmpty
		private String uri;

		public String getUri() {
			return uri;
		}

		public void setUri(String uri) {
			this.uri = uri;
		}
	}
}
