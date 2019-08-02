package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.my.springboot.demo.SpringBootDemoApplication;
import com.my.springboot.demo.dao.StudentMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
public class StudentMapperTest {
	@Autowired
	StudentMapper studentMapper;

	@Test
	public void testSelectStudent() throws Exception {
		System.out.println("----------------------------------------------------------------"+"StudentMapperTest.testSelectStudent()" +"----------------------------------------------------------------");
//		System.out.println(JSON.toJSONString(studentMapper.selectStudent(21000L)));
		System.out.println(JSON.toJSONString(studentMapper.selectStudentsByClass(11000L)));
		
//		System.out.println(JSON.toJSONString(studentMapper.selectStudentExtra(21000L)));
	}
}
