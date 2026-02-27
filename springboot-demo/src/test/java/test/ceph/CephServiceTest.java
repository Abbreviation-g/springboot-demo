package test.ceph;

import com.my.springboot.demo.SpringBootDemoApplication;
import com.my.springboot.demo.ceph.CephService;
import com.my.springboot.demo.ceph.SSLValidation;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import static test.ceph.CephTestConstant.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
public class CephServiceTest {
    @Resource
    private CephService cephService;

    @Test
    public void testAuth() throws NoSuchAlgorithmException, KeyManagementException {
        SSLValidation.turnOffSslChecking();
        String token = cephService.auth(CEPH_URL, CEPH_USER, CEPH_PASSWORD);
        System.out.println(token);
    }
}
