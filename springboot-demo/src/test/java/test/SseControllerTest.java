package test;

import com.my.springboot.demo.SpringBootDemoApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
@AutoConfigureMockMvc
public class SseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHandleSse1() throws Exception {
        // 测试文本消息SSE端点
        mockMvc.perform(MockMvcRequestBuilders.get("/sse/1")
                .accept(MediaType.TEXT_EVENT_STREAM))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_EVENT_STREAM))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testHandleSse2() throws Exception {
        // 测试通过EventSource转发的SSE端点
        mockMvc.perform(MockMvcRequestBuilders.get("/sse/2")
                .accept(MediaType.TEXT_EVENT_STREAM))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_EVENT_STREAM))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testHandleSse3() throws Exception {
        // 测试二进制数据SSE端点
        mockMvc.perform(MockMvcRequestBuilders.get("/sse/3")
                .accept(MediaType.APPLICATION_OCTET_STREAM))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testHandleSse4() throws Exception {
        // 测试通过EventSource转发二进制数据的SSE端点
        mockMvc.perform(MockMvcRequestBuilders.get("/sse/4")
                .accept(MediaType.TEXT_EVENT_STREAM))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_EVENT_STREAM))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testHandleSse5() throws Exception {
        // 测试音频PCM格式的SSE端点
        mockMvc.perform(MockMvcRequestBuilders.get("/sse/5")
                .accept(MediaType.valueOf("audio/pcm")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testHandleSse6() throws Exception {
        // 测试通过EventSource转发音频PCM数据的SSE端点
        mockMvc.perform(MockMvcRequestBuilders.get("/sse/6")
                .accept(MediaType.TEXT_EVENT_STREAM))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.TEXT_EVENT_STREAM))
                .andDo(MockMvcResultHandlers.print());
    }
}
