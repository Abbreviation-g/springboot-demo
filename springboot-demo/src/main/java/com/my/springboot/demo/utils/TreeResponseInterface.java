package com.my.springboot.demo.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public interface TreeResponseInterface<T extends TreeResponseInterface<T, P>, P> {

    P getId();

    P getParentId();

    List<T> getChildren();

    /**
     * 构建树形结构
     *
     * @param nodes
     * @return
     */
    static <T extends TreeResponseInterface<T, P>, P> List<T> buildTree(List<T> nodes) {
        if (nodes == null) {
            return new ArrayList<>();
        }
        List<T> root = new ArrayList<>();
        Iterator<T> iterator = nodes.iterator();
        while (iterator.hasNext()) {
            T node = iterator.next();
            if (node.getParentId() == null
                    || Objects.equals("0", node.getParentId())
                    || Objects.equals(node.getParentId(), node.getId())) {
                root.add(node);
                iterator.remove();
            }
        }
        buildChildren(root, nodes);
        return root;
    }

    private static <T extends TreeResponseInterface<T,P>, P> void buildChildren(List<T> parents, List<T> nodes) {
        for (T parent : parents) {
            P pid = parent.getId();
            for (T node : nodes) {
                if (Objects.equals(pid, node.getParentId())) {
                    parent.getChildren().add(node);
                }
            }
        }
        for (T parent : parents) {
            List<T> children = parent.getChildren();
            buildChildren(children, nodes);
        }
    }
}
