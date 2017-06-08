package com.greenfox.malachit;

import com.greenfox.malachit.model.HealthCheck;
import com.greenfox.malachit.repository.HealthCheckRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.nio.charset.Charset;
import java.util.ArrayList;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HearthbeatEndpointTest {

  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
          MediaType.APPLICATION_JSON.getSubtype(),
          Charset.forName("utf8"));

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private HealthCheckRepository healthCheckRepository;

  @Test
  public void getEndpointDatabaseOk() throws Exception {
    ArrayList<HealthCheck> toReturn = new ArrayList<>();
    toReturn.add(new HealthCheck(true));
    BDDMockito.given(healthCheckRepository.findAllByOrderById())
            .willReturn(
                   toReturn
            );
    mockMvc.perform(get("/hearthbeat")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.database", is("ok")));
  }

  @Test
  public void getEndpointDatabaseError() throws Exception {
    ArrayList<HealthCheck> toReturn = new ArrayList<>();
    BDDMockito.given(healthCheckRepository.findAllByOrderById())
            .willReturn(
                    toReturn
            );
    mockMvc.perform(get("/hearthbeat")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType))
            .andExpect(jsonPath("$.database", is("error")));
  }

}
