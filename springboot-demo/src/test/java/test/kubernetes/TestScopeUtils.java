package test.kubernetes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class TestScopeUtils {
    @Test
    public void testGetDeploymentAsJson() throws IOException {
        String nginx_deployment_yaml = getNginxDeploymentYaml();
        String nginx_deployment_json_data = getJsonFromYaml(nginx_deployment_yaml);
        System.out.println(nginx_deployment_json_data);
    }

    public static String getNginxDeploymentYaml() throws IOException {
        InputStream inputStream = TestScopeUtils.class.getClassLoader().getResourceAsStream("nginx-deployment.yaml");
        return new String(inputStream.readAllBytes());
    }

    public static String getNginxStatefulSetYaml() throws IOException {
        InputStream inputStream = TestScopeUtils.class.getClassLoader().getResourceAsStream("nginx-statefulset.yaml");
        return new String(inputStream.readAllBytes());
    }

    public static String getNginxDaemonSetYaml() throws IOException {
        InputStream inputStream = TestScopeUtils.class.getClassLoader().getResourceAsStream("nginx-daemonset.yaml");
        return new String(inputStream.readAllBytes());
    }


    public static String getTestNamespaceYaml() throws IOException {
        InputStream inputStream = TestScopeUtils.class.getClassLoader().getResourceAsStream("test-namespace.yaml");
        return new String(inputStream.readAllBytes());
    }

    public static String getJsonFromYaml(String yamlString) {
        try {
            // 将YAML转换为JSON
            ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
            Object yamlObject = yamlMapper.readValue(yamlString, Object.class);
            ObjectMapper jsonMapper = new ObjectMapper();
            String json = jsonMapper.writeValueAsString(yamlObject);
            System.out.println(json);
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
