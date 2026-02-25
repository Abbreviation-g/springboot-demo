package com.my.springboot.demo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageItemsResponse<T> {
    /**
     * 总数
     */
    private long total;

    /**
     * 当前页
     */
    private int current;

    /**
     * 每页大小
     */
    private int size;

    /**
     * 总页数
     */
    private long pages;

    /**
     * 当前页数据
     */
    List<T> items;

    public PageItemsResponse(List<T> items, long total, int current, int size) {
        this.setItems(items);
        this.setTotal(total);
        this.setCurrent(current);
        this.setSize(size);
        this.setPages(total % size == 0 ? total / size : total / size + 1);
    }
}
