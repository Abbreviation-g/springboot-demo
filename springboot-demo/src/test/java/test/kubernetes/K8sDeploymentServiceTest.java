package test.kubernetes;

import com.my.springboot.demo.kubernetes.K8sDeploymentService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static test.kubernetes.TestScopeConstant.*;
import static test.kubernetes.TestScopeUtils.getJsonFromYaml;
import static test.kubernetes.TestScopeUtils.getNginxDeploymentYaml;

public class K8sDeploymentServiceTest {
    private K8sDeploymentService service;

    @Before
    public void setUp() {
        service = new K8sDeploymentService();
    }

    @Test
    public void testCreateDeployment() throws IOException {
        String nginx_deployment_yaml = getNginxDeploymentYaml();
        String nginx_deployment_json_data = getJsonFromYaml(nginx_deployment_yaml);
        System.out.println(nginx_deployment_json_data);
        Map<String, String> requestMap = Map.of("pretty", "true", "limit", "2");
        System.out.println(service.createDeployment(address, token, namespace, nginx_deployment_json_data, requestMap));
    }

    @Test
    public void testGetDeployment() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true", "limit", "2");
        String deploymentName = "nginx";
        System.out.println(service.getDeployment(address, token, namespace, deploymentName, requestMap));
    }

    @Test
    public void testGetDeploymentStatus() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true", "limit", "2");
        String deploymentName = "nginx";
        System.out.println(service.getDeploymentStatus(address, token, namespace, deploymentName, requestMap));
    }
}
