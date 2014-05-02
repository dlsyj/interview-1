

public class FindFirstFalse{
  public static int firstInd(boolean[] array){
    if(null == array){
      throw new IllegalArgumentException();
    }
    
    int left = 0;
    int right = array.length - 1;
    while(left <= right){
      int middle = (left + right) >>> 1;
      if(!array[middle]){
        if(middle == 0 || array[middle - 1]){
          return middle;
        }else{
          right = middle - 1;
        }
      }else{
        left = middle + 1;
      }
    }
    return -1;
  }

  public static void main(String[] args){
    boolean[] array = new boolean[]{true, true, false, false};
    boolean[] two = {false};
    boolean[] three = {true};
    boolean[] four = {true, true, true, true};
    boolean[] five = {true, true, true, true, false};
    System.out.println(firstInd(array));
    System.out.println(firstInd(two));
    System.out.println(firstInd(three));
    System.out.println(firstInd(four));
    System.out.println(firstInd(five));
  }
}
