package test;

import com.alibaba.fastjson.JSON;
import com.my.springboot.demo.SpringBootDemoApplication;
import com.my.springboot.demo.request.StudentAddRequest;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
@AutoConfigureMockMvc
public class StudentControllerTest {
    @Resource
    private MockMvc mockMvc;

    @Test
    public void testSelectStudent() throws Exception {
        log.info("----------------------------------------------------------------");
        log.info("StudentControllerTest.testSelectStudent()");
        ResultActions resultActions = mockMvc.perform(put("/student/update").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        resultActions.andDo(result -> log.info("return: {}",result.getResponse().getContentAsString()));
    }

    @Test
    public void testAddStudent() throws Exception {
        log.info("----------------------------------------------------------------");
        log.info("StudentControllerTest.testAddStudent()");

        StudentAddRequest request = new StudentAddRequest();
        request.setName("test");
        request.setClassId(11000L);

        ResultActions resultActions = mockMvc.perform(
                post("/student/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON.toJSONString(request))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        resultActions.andDo(result -> log.info("return: {}",result.getResponse().getContentAsString()));
    }
}
