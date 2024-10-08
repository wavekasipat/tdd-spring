package com.example.day1.post;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class PostGateway {

  private final RestTemplate restTemplate;

  private final String postApiUrl;

  public PostGateway(
    final RestTemplate restTemplate,
    @Value("${post.api.url}") final String postApiUrl
  ) {
    this.restTemplate = restTemplate;
    this.postApiUrl = postApiUrl;
  }

  public Optional<PostResponse> getById(int id) {
    String url = postApiUrl + "/posts/" + id;
    try {
      PostResponse postResponse = restTemplate.getForObject(
        url,
        PostResponse.class
      );
      return Optional.ofNullable(postResponse);
    } catch (HttpClientErrorException e) {
      System.out.println(e.getStatusText());
      if ("Not Found".equals(e.getStatusText())) {
        return Optional.empty();
      }
      throw new RuntimeException("PostGateway getById error");
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new RuntimeException("PostGateway getById error");
    }
  }
}
