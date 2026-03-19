package test;

import com.my.springboot.demo.SpringBootDemoApplication;
import com.my.springboot.demo.dao.QaLogDetailMapper;
import com.my.springboot.demo.entity.QaSession;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QaLogDetailMapperTest {
    @Resource
    private QaLogDetailMapper qaLogDetailMapper;

    @Test
    public void testQuerySession() {
        log.info("testQuerySession");
        List<QaSession> qaSessions = qaLogDetailMapper.querySession();
        log.info("qaSessions: {}", qaSessions);
    }
}
