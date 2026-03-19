package test;

import com.my.springboot.demo.SpringBootDemoApplication;
import com.my.springboot.demo.dao.ParentChildrenMapper;
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
public class ParentChildrenMapperTest {
    @Resource
    private ParentChildrenMapper parentChildrenMapper;

    @Test
    public void testSelectRecursiveCurrentAndParentById() {
        List<Integer> currentAndParentIdList = parentChildrenMapper.selectRecursiveCurrentAndParentById(10201);
        log.info("currentAndParentIdList: {}", currentAndParentIdList);
    }

    @Test
    public void testSelectRecursiveCurrentAndChildrenById() {
        List<Integer> currentAndChildrenIdList = parentChildrenMapper.selectRecursiveCurrentAndChildrenById(1);
        log.info("currentAndChildrenIdList: {}", currentAndChildrenIdList);
    }
}
