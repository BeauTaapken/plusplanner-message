package plus.planner.messageservice;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import plus.planner.messageservice.Models.Message;

import javax.ws.rs.core.MediaType;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
@SpringBootTest
public class PlusplannermessageApplicationTests {
    private final Gson gson = new Gson();

    private MockMvc mockMvc;

    private final Message message = new Message("1", "1", "1", "test", "2020");

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/message/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(message))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void createMessageCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/message/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(message))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void readMessageCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/message/read/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void updateMessageCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/message/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(message))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void deleteMessageCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/message/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content("1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }
}
