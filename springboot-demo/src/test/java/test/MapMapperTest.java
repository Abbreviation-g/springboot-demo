package test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.my.springboot.demo.SpringBootDemoApplication;
import com.my.springboot.demo.dao.MapMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =  SpringBootDemoApplication.class)
public class MapMapperTest {
	@Autowired
	MapMapper mapMapper;
	
	@Test
	public void testName() throws Exception {
		@SuppressWarnings("serial")
		Map<String, Object> map = new HashMap<String, Object>(){{
			put("name", "name-1");
			put("gender", 1);
		}};
		
		mapMapper.create(map);
	}
}
