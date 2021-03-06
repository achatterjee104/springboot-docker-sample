package org.thoughtworks.induction.sample.endpoint;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.thoughtworks.induction.sample.bean.Greeting;
import org.thoughtworks.induction.sample.endpoint.SampleEndpoint;
import org.thoughtworks.induction.sample.service.SampleService;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest(SampleEndpoint.class)
public class SampleEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SampleService sampleService;

    //@Test
    public void greet_happypath() throws Exception {

        when(this.sampleService.greet(anyString())).thenReturn(new Greeting(anyInt(), anyString()));

        MvcResult result = this.mockMvc
                .perform(get("/greet?name=abhishek").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").isString())
                .andReturn();


    }
}
