package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.my.springboot.demo.SpringBootDemoApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =  SpringBootDemoApplication.class)
public class TestJDBC {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Test
	public void testName() throws Exception {
		//2019-07-30 17:59:53.0 -->> &serverTimezone=Asia/Shanghai
		//2019-07-31 02:02:23.0 -->> &serverTimezone=UTC
		System.out.println(jdbcTemplate.queryForObject("SELECT NOW()", Object.class));
	}
}
