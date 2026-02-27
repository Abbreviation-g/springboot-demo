package com.my.springboot.demo.kubernetes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.my.springboot.demo.utils.K8sHttpUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class K8sService {
    public static JSONObject getNamespaces(String url, Map<String, String> header) {
        return JSON.parseObject(K8sHttpUtils.getUrl(url, header));
    }

    public static void main(String[] args) {
        String url = "https://172.16.31.155:16443/api/v1/namespaces";
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IjI2VU9GM2ZvRlR1T1hQNHpuR2V5eHVlWXZySjVrOWpzTFFLa2Q0REREYncifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJrdWJlcm5ldGVzLWRhc2hib2FyZCIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VjcmV0Lm5hbWUiOiJhZG1pbi11c2VyLXRva2VuLXE4c2syIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQubmFtZSI6ImFkbWluLXVzZXIiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC51aWQiOiIyMTc4ODU5Yy05MWY3LTQ1MDUtYWZjOS1jY2Y5YmFiM2Q3MmMiLCJzdWIiOiJzeXN0ZW06c2VydmljZWFjY291bnQ6a3ViZXJuZXRlcy1kYXNoYm9hcmQ6YWRtaW4tdXNlciJ9.YFi5V5KaiwvUxCbngF0k8UQlkmF6mANo_rFzYp6qWXOF40aTLcap5j7DIWaxckRmkxYFTjXpdL7zjX-_glVq7JTaPxj2vxb9FLNoGogNuVKNe_pKJcprhfGfWlf5nmrQ5i6MJIZs1tMR3kZ_rgeHUlOKginnm3NaB-VxXpm70lhckI-Iyz1a-JFM__7FUszTxW7WEoon1Xq-HS4m_HDmZfNVlr6PtjsX374fswD8n-nXwduBXo-5lVg9i8ltAQ0mF4CxNHyE1RTzm-GH_evA2bmFQRI0E2BAjcbHowPdqb2qheZ6CxnIG6ZNu8XN-X5zeVaDHjAvTOCkZYUw-aYIew");
        System.out.println(K8sService.getNamespaces(url, headers));
    }

}