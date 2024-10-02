package com.example.day1.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.day1.global.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(UserController.class)
class UserControllerMvcFailureTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private UserService userService;

  @Test
  void getByIdNotFound() throws Exception {
    // Arrange
    when(userService.get(2)).thenThrow(new UserNotFoundException(2));

    // Call API
    MvcResult mvcResult =
      this.mvc.perform(get("/user/2").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andReturn();

    // Convert response to JSON object
    String response = mvcResult.getResponse().getContentAsString();
    ObjectMapper mapper = new ObjectMapper();
    ErrorResponse errorResponse = mapper.readValue(
      response,
      ErrorResponse.class
    );

    // Assert
    assertEquals("User id 2 not found", errorResponse.getMessage());
  }
}
