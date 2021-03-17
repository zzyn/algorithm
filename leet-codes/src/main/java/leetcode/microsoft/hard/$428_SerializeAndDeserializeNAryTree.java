package leetcode.microsoft.hard;

import java.util.ArrayList;
import java.util.List;

public class $428_SerializeAndDeserializeNAryTree {

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    // Encodes a tree to a single string.
    public static String serialize(Node root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        serializeDfs(root, sb);
        return sb.toString();
    }

    private static void serializeDfs(Node root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(Integer.toString(root.val));
        List<Node> children = root.children;
        if (children != null && children.size() > 0) {
            sb.append('[');
            for (Node child : children) {
                serializeDfs(child, sb);
                sb.append(',');
            }
            sb.append(']');
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if ("".equals(data)) {
            return null;
        }
        char[] chars = data.toCharArray();
        int index = 0, num = 0;
        while (index < chars.length && chars[index] != '[') {
            num = num * 10 + chars[index] - '0';
            ++index;
        }
        Node root = new Node(num, new ArrayList<>());
        deserializeDfs(chars, root, index + 1);
        return root;
    }

    public int deserializeDfs(char[] chars, Node p, int start) {
        List<Node> childs = p.children;
        int num = 0;
        while (start < chars.length) {
            if (chars[start] == ',') {
                childs.add(new Node(num, new ArrayList<>()));
                num = 0;
            }else if (chars[start] == '[') {
                childs.add(new Node(num, new ArrayList<>()));
                start = deserializeDfs(chars, childs.get(childs.size() - 1), start + 1);
                num = 0;
            }else if (chars[start] == ']') {
                return start + 1;
            }else {
                num = num * 10 + chars[start] - '0';
            }
            ++start;
        }
        return start;
    }


}
