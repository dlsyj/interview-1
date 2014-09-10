import java.io.FileWriter;
import java.io.IOException;

public class Logger implements Runnable{
  private Cluster cluster;

  public Logger(Cluster c){
    this.cluster = c;
  }

  @Override
  public void run(){
    while(true){
      log();
      Thread.sleep(1000);
    }
  }

  private void log(){
    FileWriter fw = null;
    for(Node node : cluster.getNodes()){
      String str;
      synchronized(node){
        BufferedWriter bw = new BufferedWriter();
        str = node.getName() + ": ip: " + node.getIp() 
                       + " load: " + node.getLoad();
      }
    }

    try{
      fw = new FileWriter("status.log");
      fw.write();
    }catch(IOException ioe){
      ioe.printStackTrace();
    }
  }
    

