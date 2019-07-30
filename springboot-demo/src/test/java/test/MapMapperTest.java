package test;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.my.springboot.demo.SpringBootDemoApplication;
import com.my.springboot.demo.dao.MapMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =  SpringBootDemoApplication.class)
public class MapMapperTest {
	@Autowired
	MapMapper mapMapper;
	
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;
	
//	@Test
	public void testName() throws Exception {
//		@SuppressWarnings("serial")
//		Map<String, Object> map = new HashMap<String, Object>(){{
//			put("name", "name-1");
//			put("gender", 1);
//		}};
//		mapMapper.create(map);
//		System.out.println(mapMapper.select(null));//SELECT * FROM t_user 
		System.out.println(mapMapper.select(0));//SELECT * FROM t_user 
		System.out.println(mapMapper.select(1));//SELECT * FROM t_user WHERE t_user.`name` IS NULL 
	}
	
	@Test
	public void testRedis() {
		String key = MapMapperTest.class.getSimpleName();
		String value = MapMapperTest.class.getName();
		redisTemplate.opsForValue().set(key, value, 40, TimeUnit.SECONDS);
		System.out.println("----------------------------------------------------------------");
		System.out.println(redisTemplate.opsForValue().get(key));;
	}
}
