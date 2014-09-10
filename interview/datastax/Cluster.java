import java.util.List;
import java.util.ArrayList;

public class Cluster{
  private String name;
  private List<Node> nodes;
  
  public Cluster(String name){
    this.name = name;
    this.nodes = new ArrayList<Node>();
  }

}
