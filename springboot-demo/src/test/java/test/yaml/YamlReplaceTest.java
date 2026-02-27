package test.yaml;

import com.my.springboot.demo.utils.PresetYamlReader;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class YamlReplaceTest {

    @Test
    public void test() {
        // 定义占位符和它们对应的值
        Map<String, String> secretData = new HashMap<>();
        secretData.put("userId", "admin");
        secretData.put("userKey", "AQBvwT9ppGwlEhAA/QaB/JXlSDsVDGCDrMeRUA==");
        secretData.put("app", "ceph-rbd");
        secretData.put("managerBy", "clout-platform");
        secretData.put("cephClusterId", "dbd92dce-d98c-11f0-8a4e-525400c7831f");
        secretData.put("namespace", "default");
        secretData.put("secretName", "ceph-secret-dbd92dce-d98c-11f0-8a4e-525400c7831f");
        secretData.put("type", "Opaque");
        try {
            // 1. 读取 YAML 文件内容
            // 2. 替换占位符
            String replacedYamlContent = PresetYamlReader.readYamlAndReplace("yaml/ceph-secret.yaml", secretData);

            log.info("replacedYamlContent: {}", replacedYamlContent);

            String jsonFromYaml = PresetYamlReader.getJsonFromYaml(replacedYamlContent);

            log.info("jsonFromYaml: {}", jsonFromYaml);

        } catch (IOException e) {
            log.error("处理 YAML 文件时发生错误", e);
        }
    }
}
