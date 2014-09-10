
public class KChildren{
  private static int ind;
  public static KTreeNode constructTree(String str, int k){
    if(str == null || 0 == str.length()){
      return null;
    }
    KTreeNode root = new KTreeNode('i');
    ind = 1;
    construct(str, k, root);
    return root;
  }

  private static void construct(String str, int k, KTreeNode par){
    if(ind >= str.length()){
      return;
    }
    for(int i = 0; i < k; i++){
      if(str.charAt(ind) == 'l'){
        par.addChild(new KTreeNode('l'));
        ind++;
      }else{
        KTreeNode curr = new KTreeNode('i');
        par.addChild(curr);
        ind++;
        construct(str, k, curr);
      }
    }
  }
  
  public static void print(KTreeNode root){
    if(root != null){
      System.out.println(root.val);
    }
    for(KTreeNode child:root.children){
      if(child.val == 'l'){
        System.out.println('l');
      }else{
        print(child);
      }
    }
  }

  public static void main(String[] args){
    String str = "ill";
    KTreeNode root = constructTree(str, 2);
    print(root);
  }
}
