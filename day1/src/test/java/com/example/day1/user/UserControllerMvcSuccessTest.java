package com.example.day1.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(UserController.class)
class UserControllerMvcSuccessTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private UserService userService;

  @Test
  void getById() throws Exception {
    // Arrange
    UserResponse mock = new UserResponse();
    mock.setId(1);
    mock.setFname("John");
    mock.setLname("Doe");
    when(userService.get(1)).thenReturn(mock);

    // Call API
    MvcResult mvcResult =
      this.mvc.perform(get("/user/1").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();

    // Convert response to JSON object
    String response = mvcResult.getResponse().getContentAsString();
    ObjectMapper mapper = new ObjectMapper();
    UserResponse userResponse = mapper.readValue(response, UserResponse.class);

    // Assert
    assertEquals(1, userResponse.getId());
    assertEquals("John", userResponse.getFname());
    assertEquals("Doe", userResponse.getLname());
  }
}
