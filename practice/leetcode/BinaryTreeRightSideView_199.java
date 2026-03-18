class Solution {
    public void solve(List<Integer> ans, TreeNode root, int depth) {
        if (root == null) return;
        if (depth == ans.size()) ans.add(root.val);
        solve(ans, root.right, depth + 1);
        solve(ans, root.left, depth + 1);
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        solve(ans, root, 0);
        return ans;
    }
}