package microsoft.middle;





public class InOrderSuccessor2 {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    /**
     * node is input
     * @param node
     * @return node's successor
     */
    public Node inorderSuccessor(Node node) {

        if(node.right != null) {
            node = node.right;
            while(node.left != null) node = node.left;
            return node;
        }

        while(node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        return node.parent;
    }

    public static void main(String[] args) {

    }
}
