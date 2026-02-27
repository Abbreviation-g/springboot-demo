package test.kubernetes;

import com.my.springboot.demo.kubernetes.K8sNodeService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class K8sNodeServiceTest {
    private K8sNodeService service;

    @Before
    public void setUp() {
        service = new K8sNodeService();
    }

    @Test
    public void testListNodes() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true", "limit", "2");

        service.listNodes(TestScopeConstant.address, TestScopeConstant.token, requestMap);
    }

    @Test
    public void testGetNode() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true");

        service.getNode(TestScopeConstant.address, TestScopeConstant.token, "bjsw-s02", requestMap);
    }

    @Test
    public void testGetNodeStatus() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true");
        service.getNodeStatus(TestScopeConstant.address, TestScopeConstant.token, "bjsw-s02", requestMap);
    }
}
