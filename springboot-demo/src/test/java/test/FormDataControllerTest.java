package test;

import com.my.springboot.demo.SpringBootDemoApplication;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FormDataControllerTest {
    @Resource
    private MockMvc mockMvc;

    @Test
    public void testUpload() throws Exception {
        log.info("开始测试");
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
                .file("byteData", "123".getBytes())
                .param("stringParam1", "stringParam1")
                .param("stringParam2", "stringParam2")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
        log.info("结束测试");
        resultActions.andDo(result -> log.info(result.getResponse().getContentAsString()));
    }
}
