package com.example.day1.post;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.jupiter.api.Assertions.*;

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
import org.springframework.util.ResourceUtils;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9999)
class PostGatewayFailureTest {

  @Autowired
  private PostGateway postGateway;

  @Test
  @DisplayName("Failure - PostGateway getById 1")
  void getById() throws IOException {
    // Arrange
    stubFor(
      get(urlPathEqualTo("/posts/1"))
        .willReturn(
          aResponse()
            .withStatus(404)
        )
    );
    // Act
    Optional<PostResponse> postResponse = postGateway.getById(1);
    // Assert
    assertTrue(postResponse.isEmpty());
  }

  public static String read(String filePath) throws IOException {
    File file = ResourceUtils.getFile(filePath);
    return new String(Files.readAllBytes(file.toPath()));
  }
}
