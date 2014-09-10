import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class MaxScore{
  public void calMaxScore(String path){
    BufferedReader br = null;
    try {
      String line;
      br = new BufferedReader(new FileReader(inputName));
      while((line = br.readLine()) != null){
        calEachCase(line);
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } finally {
      if (br != null) {
        br.close();
      }
    }
  }

  private void calEachCase(String str){
    String[] tmp = str.trim().split(";");
    String[] customers = tmp[0].trime().split(",");
    String[] products = tmp[1].trime().split(",");

    int amountOfCus = customers.length;
    int amountOfPro = products.length;
    double[][] scoreMap = double[amountOfCus][amountOfPro];

    for(int i = 0; i < amountOfCus; i++){
      for(int j = 0; )
    }

  }
}