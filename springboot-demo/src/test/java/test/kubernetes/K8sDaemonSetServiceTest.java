package test.kubernetes;

import com.my.springboot.demo.kubernetes.K8sDaemonSetService;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static test.kubernetes.TestScopeConstant.*;
import static test.kubernetes.TestScopeUtils.getJsonFromYaml;
import static test.kubernetes.TestScopeUtils.getNginxDaemonSetYaml;

public class K8sDaemonSetServiceTest {
    public static final String DAEMON_SET_NAME = "nginx-daemonset";
    private K8sDaemonSetService service;

    @Before
    public void setUp() {
        service = new K8sDaemonSetService();
    }

    @Test
    public void testCreate() throws IOException {
        String nginxStatefulSetYaml = getNginxDaemonSetYaml();
        String nginxStatefulSetJsonData = getJsonFromYaml(nginxStatefulSetYaml);
        System.out.println(nginxStatefulSetJsonData);
        Map<String, String> requestMap = Map.of("pretty", "true", "limit", "2");
        System.out.println(service.createDaemonSet(address, token, namespace, nginxStatefulSetJsonData, requestMap));
    }

    @Test
    public void testGet() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true", "limit", "2");

        System.out.println(service.getDaemonSet(address, token, namespace, DAEMON_SET_NAME, requestMap));
    }

    @Test
    public void testGetStatus() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true", "limit", "2");
        System.out.println(service.getDaemonSetStatus(address, token, namespace, DAEMON_SET_NAME, requestMap));
    }

    @Test
    public void testList() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true", "limit", "2");
        System.out.println(service.listDaemonSets(address, token, namespace, requestMap));
    }

    @Test
    public void testListWithoutNamespace() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true", "limit", "2");
        System.out.println(service.listDaemonSetsWithoutNamespace(address, token, requestMap));
    }

    @Test
    public void testUpdate() throws IOException {
        String nginxStatefulSetYaml = getNginxDaemonSetYaml();
        String nginxStatefulSetJsonData = getJsonFromYaml(nginxStatefulSetYaml);
        Map<String, String> requestMap = Map.of("pretty", "true", "limit", "2");
        System.out.println(service.updateDaemonSet(address, token, namespace, DAEMON_SET_NAME, nginxStatefulSetJsonData, requestMap));
    }

    @Test
    public void testDelete() throws IOException {
        Map<String, String> requestMap = Map.of("pretty", "true");
        System.out.println(service.deleteDaemonSet(address, token, namespace, DAEMON_SET_NAME, requestMap));
    }
}
