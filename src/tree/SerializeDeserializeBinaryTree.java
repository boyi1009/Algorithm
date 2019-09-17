package tree;

public class SerializeDeserializeBinaryTree {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public String serialize(TreeNode root) { //DFS, pre order traverse
        StringBuilder data = new StringBuilder();
        if(root == null) return data.toString();
        dfsSerialize(root, data);
        return data.toString();
    }
    private void dfsSerialize(TreeNode root, StringBuilder data) {
        if(root == null) {
            data.append("#" + ",");
            return;
        }
        data.append(root.val + ",");
        dfsSerialize(root.left, data);
        dfsSerialize(root.right, data);
    }

    //private int idx = 0;
    public TreeNode deserialize(String data) {
        if(data == null || data.length() == 0) return null;
        String[] dataArray = data.split(",");
        int idx[] = new int[1];
        TreeNode root = dfsDeserialize(dataArray, idx);
        return root;
    }
    private TreeNode dfsDeserialize(String[] dataArray, int[] idx){
        if(idx[0] == dataArray.length || dataArray[idx[0]].equals("#")) {
            idx[0]++;
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(dataArray[idx[0]++]));
        //idx++;
        node.left = dfsDeserialize(dataArray, idx);
        node.right = dfsDeserialize(dataArray, idx);
        return node;
    }

    public static void main(String[] args) {
        SerializeDeserializeBinaryTree solution = new SerializeDeserializeBinaryTree();
        String data = "1,#,2,3,#,#,4,#,#";
        String ans = solution.serialize(solution.deserialize(data));
        System.out.println(ans);
    }
}
