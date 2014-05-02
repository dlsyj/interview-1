import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * 给你一个list of words, group anagram together
 * ['abc','god','dog'] => [['abc'],['god','dog']]， 如果dog出现两次结果里面只加一次
 *  Write a function that takes a list of words as input, and returns a list of those words bucketized by anagrams.
 *  Example: Input: ["star", "rats", "car", "arc", "arts"]
 *  Output: [["star", "rats", "arts"], ["car", "arc"]] 
 */ 
public class GroupAnagram{
  static List<List<String>> generateAnagram(List<String> input){
    Map<String, List<String>> map = new HashMap<String, List<String>>();
    for(String str : input){
      String key = getKey(str);
      if(!map.containsKey(key)){
        List<String> val = new ArrayList<String>();
        val.add(str);
        map.put(key, val);
      }else{
        List<String> val = map.get(key);
        val.add(str);
      }
      
    }
    List<List<String>> ret = new ArrayList<List<String>>();
    for(String str: map.keySet()){
      ret.add(map.get(str));
    }
    return ret;
  }
  
  static String getKey(String str){
    int[] temp = new int[256];
    for(int i = 0; i < str.length(); i++){
      temp[str.charAt(i)]++;
    }
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < 256; i++){
      if(temp[i] != 0){
        int num = temp[i];
        while(num > 0){
          sb.append(Character.toChars(i));
          num--;
        }
      }
    }
    return sb.toString();
  }

  public static void main(String[] args){
    List<String> input = new ArrayList<String>();
    input.add("star");
    input.add("rats");
    input.add("car");
    input.add("arc");
    input.add("arts");
    List<List<String>> ret = generateAnagram(input);
    for(List<String> one : ret){
      for(String str : one){
        System.out.print(str + " ");
      }
      System.out.println();
    }
  }
}