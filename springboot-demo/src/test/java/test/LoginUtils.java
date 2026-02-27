package test;

import com.alibaba.fastjson.JSON;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class LoginUtils {
    public static String login(MockMvc mockMvc) throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.
                post("/login")
                .param("username","zhang")
                .param("password","123456")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        System.out.println(mvcResult.getResponse().getStatus());
        String content = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        System.out.println(content);
        return JSON.parseObject(content).getString("satoken");
    }
}
