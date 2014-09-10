public class Node{
  private String ip;
  private String name;
  private double load;

  public Node(String _ip, String _name, double _load){
    this.ip = _ip;
    this.name = _name;
    this.load = _load;
  }

  public String getIp(){
    return this.ip;
  }

  public void setIp(String _ip){
    this.ip = _ip;
  }

  public String getName(){
    return this.name;
  }

  public void setName(String _name){
    this.name = _name;
  }

  public double getLoad(){
    return this.load;
  }

  public void setLoad(double _load){
    this.load = _load;
  }
}
