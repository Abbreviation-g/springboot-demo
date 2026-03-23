package com.my.springboot.demo.utils;

import java.util.Collections;
import java.util.List;

public interface SortableResponseInterface<T extends SortableResponseInterface<T, P, Q>, P, Q>
        extends TreeResponseInterface<T, P>, Comparable<T> {
    Q getSort();

    /**
     * 排序，遍历整棵树，对树上的每个层级排序
     *
     * @param nodes
     */
    static <T extends SortableResponseInterface<T, P, Q>, P, Q> void sort(List<T> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            return;
        }
        Collections.sort(nodes);
        for (T node : nodes) {
            SortableResponseInterface.sort(node.getChildren());
        }
    }
}
