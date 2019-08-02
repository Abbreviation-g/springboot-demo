package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.my.springboot.demo.SpringBootDemoApplication;
import com.my.springboot.demo.dao.ClassMapper2;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
public class ClassMapper2Test {
	@Autowired
	ClassMapper2 classMapper;

	@Test
	public void testSelectClass() throws Exception {
		System.out.println("----------------------------------------------------------------"+"ClassMapperTest.testSelectClass()" +"----------------------------------------------------------------");
		System.out.println(JSON.toJSONString(classMapper.selectClass(11000L)));
//		System.out.println(JSON.toJSONString(studentMapper.selectStudentExtra(21000L)));
	}
}
