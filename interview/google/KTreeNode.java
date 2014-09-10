import java.util.List;
import java.util.ArrayList;

public class KTreeNode{
  char val;
  List<KTreeNode> children;
  public KTreeNode(char v){
    this.val = v;
    this.children = new ArrayList<KTreeNode>();
  }

  public void addChild(KTreeNode child){
    this.children.add(child);
  }

}
