package test.kubernetes;

import com.my.springboot.demo.kubernetes.K8sCommonService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class K8sCommonServiceTest {
    private K8sCommonService service;

    @Before
    public void setUp() {
        service = new K8sCommonService();
    }

    @Test
    public void testVersion() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true");

        service.version(TestScopeConstant.address, TestScopeConstant.token, requestMap);
    }
}
