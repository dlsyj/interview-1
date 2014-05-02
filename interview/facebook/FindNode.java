import java.util.Stack;

public class FindNode{
  public static class TreeNode{
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int v){
      this.val = v;
    }
  }

  public static TreeNode findKthNode(TreeNode root, int k){
    if(root == null){
      return null;
    }
    Stack<TreeNode> stack = new Stack<TreeNode>();
    TreeNode curr = root;
    while(!stack.isEmpty() || curr != null){
      if(curr == null){
        curr = stack.pop();
        k--;
        if(k == 0){
          return curr;
        }
        curr = curr.right;
      }else{
        stack.push(curr);
        curr = curr.left;
      }
    }
    return null;
  }

  public static void main(String[] args){
    TreeNode root = new TreeNode(2);
    TreeNode left = new TreeNode(1);
    TreeNode right = new TreeNode(3);
    root.left = left;
    root.right = right;
    TreeNode one = findKthNode(root, 1);
    TreeNode two = findKthNode(root, 2);
    TreeNode three = findKthNode(root, 3);
    TreeNode four = findKthNode(root, 4);
    System.out.println(one.val);
    System.out.println(two.val);
    System.out.println(three.val);
    System.out.println(four.val);
  }


}
