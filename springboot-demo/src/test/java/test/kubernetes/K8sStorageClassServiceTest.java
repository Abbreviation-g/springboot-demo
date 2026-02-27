package test.kubernetes;

import com.my.springboot.demo.kubernetes.K8sStorageClassService;
import com.my.springboot.demo.utils.PresetYamlReader;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static test.kubernetes.TestScopeConstant.address;
import static test.kubernetes.TestScopeConstant.token;

public class K8sStorageClassServiceTest {
    private K8sStorageClassService service;

    @Before
    public void setUp() {
        service = new K8sStorageClassService();
    }

    @Test
    public void testListStorageClasses() throws Exception {
        Map<String, String> requestMap = Map.of("pretty", "true", "limit", "2");
        String result = service.listStorageClasses(address, token, requestMap);
        System.out.println(result);
    }

    @Test
    public void testGetStorageClass() throws Exception {
        String result = service.getStorageClass(address, token, "standard", Map.of("pretty", "true"));
        System.out.println(result);
    }

    @Test
    public void testGetStorageYaml() throws IOException {
        String cephClusterId = "ceph-cluster-id";
        String storageClassName = "ceph-rbd-" + cephClusterId;
        String cephPool = "rbd-pool";
        // 生成对应 Ceph 集群的 Secret 名称
        String secretName = "ceph-secret-" + cephClusterId;

        Map<String, String> storageClassData = new HashMap<>();
        storageClassData.put("allowVolumeExpansion", Boolean.toString(true));
        storageClassData.put("app", "ceph-rbd");
        storageClassData.put("managedBy", "clout-platform");
        storageClassData.put("storageClassName", storageClassName);
        storageClassData.put("clusterID", cephClusterId);
        storageClassData.put("csi.storage.k8s.io/provisioner-secret-name", secretName);
        storageClassData.put("csi.storage.k8s.io/provisioner-secret-namespace", "default");
        storageClassData.put("csi.storage.k8s.io/controller-expand-secret-name", secretName);
        storageClassData.put("csi.storage.k8s.io/controller-expand-secret-namespace", "default");
        storageClassData.put("csi.storage.k8s.io/node-stage-secret-name", secretName);
        storageClassData.put("csi.storage.k8s.io/node-stage-secret-namespace", "default");
        storageClassData.put("csi.storage.k8s.io/fstype", "ext4");
        storageClassData.put("imageFeatures", "layering");
        storageClassData.put("cephPoolName", cephPool);
        storageClassData.put("provisioner", "rbd.csi.ceph.com");
        storageClassData.put("reclaimPolicy", "Delete");
        storageClassData.put("volumeBindingMode", "Immediate");

        String yaml = PresetYamlReader.readYamlAndReplace("ceph-storageclass.yaml", storageClassData);
        System.out.println(yaml);
        String jsonFromYaml = PresetYamlReader.getJsonFromYaml(yaml);
        System.out.println(jsonFromYaml);
    }

}
