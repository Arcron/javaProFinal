package learning.java.pro.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import learning.java.pro.dto.UserLimitRequestDto;
import learning.java.pro.service.IUserLimitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserLimitController.class)
public class UserLimitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserLimitService userLimitService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void increaseDailyLimit_givenValue_shouldResponse200() throws Exception {
        //given
        UserLimitRequestDto requestDto = new UserLimitRequestDto(1L, 500.0);

        //when
        mockMvc.perform(post("/user-limits/v1/limit/daily/increase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        verify(userLimitService, times(1)).increaseDailyLimit(requestDto);
    }

    @Test
    public void decreaseDailyLimit_givenValue_shouldResponse200() throws Exception {
        //given
        UserLimitRequestDto requestDto = new UserLimitRequestDto(1L, 500.0);

        //when
        mockMvc.perform(post("/user-limits/v1/limit/daily/decrease")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        verify(userLimitService, times(1)).decreaseDailyLimit(requestDto);
    }
}
