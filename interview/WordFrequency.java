package interview;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
* Write a function that takes two parameters: (1) a String representing a text document and 
* (2) an integer providing the number of items to return. 
* Implement the function such that it returns a list of Strings ordered by word frequency, 
* the most frequently occurring word first. Use your best judgement to decide how words are separated. 
* Your solution should run in O(n) time where n is the number of characters in the document. 
* Implement this function as you would for a production/commercial system. 
* You may use any standard data structures. 
*/

public class WordFrequency{
  public List<String> getMostFreq(String document, int number){
    String[] array = document.splits("\\+");
    List<List<String>> cashList = new ArrayList<List<String>>();
    for(int i = 0; i < array.length; i++){
      cashList.add(new ArrayList<String>());
    }

    Map<String, Integer> freqMap = new HashMap<String, Integer>();
    for(String str:array){
      if(freqMap.contains(str)){
        freqMap.put(str, freqMap.get(str) + 1);
      }else{
        freqMap.put(str, 1);
      }
    }

    for(String str:freqMap.keySet()){
      int freq = freqMap.get(str);
      cashList.get(freq).add(str);
    }

    for(int i = number; i > 0; i--){
      cashList.
    }

  }
}