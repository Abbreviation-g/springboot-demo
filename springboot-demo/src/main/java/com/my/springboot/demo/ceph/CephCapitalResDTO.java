package com.my.springboot.demo.ceph;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CephCapitalResDTO {

    @JsonProperty("total_avail_bytes")
    private Double totalAvailBytes;

    @JsonProperty("total_bytes")
    private Double totalBytes;

    @JsonProperty("total_used_raw_bytes")
    private Double totalUsedRawBytes;

    @JsonProperty("total_objects")
    private Long totalObjects;

    @JsonProperty("total_pool_bytes_used")
    private Double totalPoolBytesUsed;

    @JsonProperty("average_object_size")
    private Double averageObjectSize;
}
