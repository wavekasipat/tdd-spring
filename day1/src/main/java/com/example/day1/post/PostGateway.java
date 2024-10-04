package com.example.day1.post;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PostGateway {

  @Autowired
  private RestTemplate restTemplate;

  public Optional<PostResponse> get(int id) {
    return Optional.of(new PostResponse());
  }
}
