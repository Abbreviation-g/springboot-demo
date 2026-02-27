package test.kubernetes;

import com.my.springboot.demo.kubernetes.K8sNamespaceService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static test.kubernetes.TestScopeConstant.*;

public class K8sNamespaceServiceTest {
    private K8sNamespaceService service;

    @Before
    public void setUp() {
        service = new K8sNamespaceService();
    }

    @Test
    public void testListNamespaces() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true", "limit", "2");

        System.out.println(service.listNamespaces(address, token, requestMap));
    }

    @Test
    public void testGetNamespace() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true");

        System.out.println(service.getNamespace(address, token, namespace, requestMap));
    }

    @Test
    public void testGetNamespaceStatus() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true");

        System.out.println(service.getNamespaceStatus(address, token, namespace, requestMap));
    }


    @Test
    public void testCreateNamespace() throws IOException {
        String namespaceJsonData = TestScopeUtils.getJsonFromYaml(TestScopeUtils.getTestNamespaceYaml());
        Map<String, String> requestMap = Map.of("pretty", "true");

        System.out.println(service.createNamespace(address, token, namespaceJsonData, requestMap));
    }

    @Test
    public void testUpdateNamespace() throws IOException {
        String namespaceJsonData = TestScopeUtils.getJsonFromYaml(TestScopeUtils.getTestNamespaceYaml());
        Map<String, String> requestMap = Map.of("pretty", "true");
        System.out.println(service.updateNamespace(address, token, namespace, namespaceJsonData, requestMap));
    }

    @Test
    public void testUpdateNamespaceStatus() throws IOException {
        String namespaceJsonData = TestScopeUtils.getJsonFromYaml(TestScopeUtils.getTestNamespaceYaml());
        Map<String, String> requestMap
                = Map.of("pretty", "true");
        System.out.println(service.updateNamespaceStatus(address, token, namespace, namespaceJsonData, requestMap));
    }

    @Test
    public void testDeleteNamespace() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true");
        System.out.println(service.deleteNamespace(address, token, namespace, requestMap));
    }
}
