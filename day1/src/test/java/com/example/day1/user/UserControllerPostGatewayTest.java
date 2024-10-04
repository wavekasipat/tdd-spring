package com.example.day1.user;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9999)
class UserControllerPostGatewayTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  @DisplayName("Success - UserController PostGateway sampleGetPost Id 1")
  void getById() throws IOException {
    // Arrange
    stubFor(
      get(urlPathEqualTo("/posts/1"))
        .willReturn(
          aResponse()
            .withBody(read("classpath:postApiResponse.json"))
            .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .withStatus(200)
        )
    );
    // Act
    UserResponse userResponse = restTemplate.getForObject(
      "/post/1",
      UserResponse.class
    );
    // Assert
    assertEquals(11, userResponse.getId());
    assertEquals("TEST TITLE", userResponse.getFname());
    assertNull(userResponse.getLname());
  }

  public static String read(String filePath) throws IOException {
    File file = ResourceUtils.getFile(filePath);
    return new String(Files.readAllBytes(file.toPath()));
  }
}
