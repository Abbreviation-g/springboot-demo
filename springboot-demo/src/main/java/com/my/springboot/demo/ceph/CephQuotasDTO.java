package com.my.springboot.demo.ceph;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CephQuotasDTO {

    @JSONField(name = "application_metadata")
    private List<Object> applicationMetadata = new ArrayList<>();

    @JSONField(name = "quota_max_bytes")
    private Long quotaMaxBytes;

    @JSONField(name = "quota_max_objects")
    private Long quotaMaxObjects;
}
