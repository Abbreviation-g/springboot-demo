package com.my.springboot.demo.utils;

import java.util.Collections;
import java.util.List;

public interface SortableTreeResponseInterface<T extends SortableTreeResponseInterface<T, P>, P> extends TreeResponseInterface<T, P>, Comparable<T> {
    Integer getSort();

    @Override
    default int compareTo(T o) {
        return Integer.compare(this.getSort(), o.getSort());
    }

    /**
     * 排序，遍历整棵树，对树上的每个层级排序
     *
     * @param nodes
     */
    static <T extends SortableTreeResponseInterface<T, P>, P> void sort(List<T> nodes) {
        if (nodes == null || nodes.isEmpty()) {
            return;
        }
        Collections.sort(nodes);
        for (T node : nodes) {
            SortableTreeResponseInterface.sort(node.getChildren());
        }
    }
}
