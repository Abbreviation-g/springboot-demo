package test;

import com.my.springboot.demo.utils.SortableResponseInterface;
import com.my.springboot.demo.utils.TreeResponseInterface;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TreeResponseInterfaceTest {
    @Test
    public void test() {
        List<?> ts = TreeResponseInterface.buildTree(null);
        assert ts.isEmpty();

        TreeNode node1 = new TreeNode("1", null, "1");
        TreeNode node2 = new TreeNode("2", "1", "2");
        TreeNode node3 = new TreeNode("3", "1", "3");
        TreeNode node4 = new TreeNode("4", "2", "4");
        TreeNode node5 = new TreeNode("5", "2", "5");
        TreeNode node6 = new TreeNode("6", "3", "6");
        List<TreeNode> nodes = new ArrayList<>(List.of(node1, node2, node3, node4, node5, node6));
        List<TreeNode> ts1 = TreeResponseInterface.buildTree(nodes);
        assert ts1.size() == 1;
    }

    @Data
    private static class TreeNode implements TreeResponseInterface<TreeNode, String> {
        private String id;
        private String parentId;
        private String name;
        private List<TreeNode> children = new ArrayList<>();

        public TreeNode(String id, String parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
        }
    }

    @Test
    public void test2() {
        SortableTreeNode node1 = new SortableTreeNode("1", null, "1");
        SortableTreeNode node2 = new SortableTreeNode("2", "1", "2");
        SortableTreeNode node3 = new SortableTreeNode("3", "1", "3");
        SortableTreeNode node4 = new SortableTreeNode("4", "2", "4");
        SortableTreeNode node5 = new SortableTreeNode("5", "2", "5");
        SortableTreeNode node6 = new SortableTreeNode("6", "3", "6");
        List<SortableTreeNode> nodes = new ArrayList<>(List.of(node1, node2, node3, node4, node5, node6));
        List<SortableTreeNode> ts1 = TreeResponseInterface.buildTree(nodes);
        SortableResponseInterface.sort(ts1);
        assert ts1.size() == 1;
    }


    @Data
    private static class SortableTreeNode implements SortableResponseInterface<SortableTreeNode, String, String> {
        private String id;
        private String parentId;
        private String name;
        private List<SortableTreeNode> children = new ArrayList<>();

        public SortableTreeNode(String id, String parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
        }

        @Override
        public String getSort() {
            return id;
        }

        @Override
        public int compareTo(SortableTreeNode o) {
            return String.CASE_INSENSITIVE_ORDER.compare(this.getSort(), o.getSort());
        }
    }
}
