package test.kubernetes;

import com.my.springboot.demo.kubernetes.K8sStatefulSetService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static test.kubernetes.TestScopeConstant.*;
import static test.kubernetes.TestScopeUtils.getJsonFromYaml;
import static test.kubernetes.TestScopeUtils.getNginxStatefulSetYaml;

public class K8sStatefulSetServiceTest {
    private K8sStatefulSetService service;

    @Before
    public void setUp() {
        service = new K8sStatefulSetService();
    }

    @Test
    public void testCreate() throws IOException {
        String nginxStatefulSetYaml = getNginxStatefulSetYaml();
        String nginxStatefulSetJsonData = getJsonFromYaml(nginxStatefulSetYaml);
        System.out.println(nginxStatefulSetJsonData);
        Map<String, String> requestMap = Map.of("pretty", "true", "limit", "2");
        System.out.println(service.createStatefulSet(address, token, namespace, nginxStatefulSetJsonData, requestMap));
    }

    @Test
    public void testGet() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true", "limit", "2");
        String statefulSetName = "nginx-statefulset";
        System.out.println(service.getStatefulSet(address, token, namespace, statefulSetName, requestMap));
    }

    @Test
    public void testGetStatus() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true", "limit", "2");
        String statefulSetName = "nginx-statefulset";
        System.out.println(service.getStatefulSetStatus(address, token, namespace, statefulSetName, requestMap));
    }

    @Test
    public void testList() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true", "limit", "2");
        System.out.println(service.listStatefulSets(address, token, namespace, requestMap));
    }

    @Test
    public void testListWithoutNamespace() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true", "limit", "2");
        System.out.println(service.listStatefulSetsWithoutNamespace(address, token, requestMap));
    }

    @Test
    public void testUpdate() throws IOException {
        String nginxStatefulSetYaml = getNginxStatefulSetYaml();
        String nginxStatefulSetJsonData = getJsonFromYaml(nginxStatefulSetYaml);
        Map<String, String> requestMap = Map.of("pretty", "true", "limit", "2");

        System.out.println(service.updateStatefulSet(address, token, namespace, "nginx-statefulset", nginxStatefulSetJsonData, requestMap));
    }

    @Test
    public void testDelete() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true", "limit", "2");
        System.out.println(service.deleteStatefulSet(address, token, namespace, "nginx-statefulset", requestMap));
    }
}
