package com.example.day1.post;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9999)
class PostGatewayTest {

  @Autowired
  private PostGateway postGateway;

  @Test
  @DisplayName("Success - PostGateway getById 1")
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
    Optional<PostResponse> postResponse = postGateway.getById(1);
    // Assert
    assertEquals(11, postResponse.get().getId());
    assertEquals(11, postResponse.get().getUserId());
    assertEquals("Test Title", postResponse.get().getTitle());
  }

  public static String read(String filePath) throws IOException {
    File file = ResourceUtils.getFile(filePath);
    return new String(Files.readAllBytes(file.toPath()));
  }
}
