package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.my.springboot.demo.SpringBootDemoApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class InferenceLogControllerTest {
    @Autowired
    protected MockMvc mockMvc;
    @Test
    public void testBySession() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/inference/log/detail")
                                .param("sessionId", "session_id_1")
                                .param("pageNo", "1")
                                .param("pageSize", "10")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        System.out.println("LoginControllerTest.testRoles");
        System.out.println(content);
    }

    @Test
    public void testBySessionAndRequest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/inference/log/detail")
                                .param("sessionId", "session_id_1")
                                .param("requestId", "request_id_21")
                                .param("pageSize", "10")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        System.out.println("LoginControllerTest.testRoles");
        System.out.println(content);
    }
}
