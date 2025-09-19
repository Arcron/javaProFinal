package learning.java.pro.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import learning.java.pro.service.IMaxLimitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MaxLimitController.class)
public class MaxLimitControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IMaxLimitService maxLimitService;

    @Test
    public void updateCurrentMaxLimit_givenValue_shouldResponse200() throws Exception {
        //given
        Double limit = 1000.0;

        //when
        mockMvc.perform(get("/max-limits/v1/limit/max/" + limit))
                .andExpect(status().isOk());

        //then
        verify(maxLimitService, times(1)).updateCurrentMaxLimit(limit);
    }
}
