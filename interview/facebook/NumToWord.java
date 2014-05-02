/**
 * number to word
 * input: 231 - output: two hundred and thirty one
 */

public class NumToWord{

  private static String[] oneTable = new String[10];
  private static String[] tenTable = new String[10];
  private static String[] twentyTable = new String[10];
  private static String[] unites = new String[5]; 
  public static String numToWord(int num){
    initial();
    StringBuilder sb = new StringBuilder();
    int i = 0;
    int reminder = 0;
    while(num != 0){
      reminder = num % 1000;
      num /= 1000;
      sb.insert(0, numLessThanThousand(reminder) + unites[i]);
      sb.insert(0, " ");
      i++;
    }
    return sb.toString();
  }

  private static void initial(){
    unites[0] = "";
    unites[1] = "thousand";
    unites[2] = "million";
    unites[3] = "billion";
    unites[4] = "trillion";
    oneTable[0] = "";
    oneTable[1] = "one";
    oneTable[2] = "two";
    oneTable[3] = "three";
    oneTable[4] = "four";
    oneTable[5] = "five";
    oneTable[6] = "six";
    oneTable[7] = "seven";
    oneTable[8] = "eight";
    oneTable[9] = "nine";
    tenTable[0] = "";
    tenTable[1] = "";
    tenTable[2] = "twenty";
    tenTable[3] = "thirty";
    tenTable[4] = "fourty";
    tenTable[5] = "fifty";
    tenTable[6] = "sixty";
    tenTable[7] = "seventy";
    tenTable[8] = "eighty";
    tenTable[9] = "nighty";
    twentyTable[0] = "ten";
    twentyTable[1] = "eleven";
    twentyTable[2] = "twelve";
    twentyTable[3] = "thirteen";
    twentyTable[4] = "fourteen";
    twentyTable[5] = "fifteen";
    twentyTable[6] = "sixteen";
    twentyTable[7] = "seventeen";
    twentyTable[8] = "eighteen";
    twentyTable[9] = "nineteen";
  }
  private static String numLessThanThousand(int num){
    StringBuilder sb = new StringBuilder();
    int ones = num % 10;
    int tens = num / 10 % 10;
    int hunds = num / 100;
    
    if(hunds != 0){
      sb.append(oneTable[hunds] + " hundred");
    }
    int reminder = num % 100;
    if(reminder > 0){
      if(hunds != 0){
        sb.append(" and");
      }
      if(reminder >= 20){
        sb.append(" " + tenTable[tens]);
        sb.append(" " + oneTable[ones]);
      }else if(reminder < 10){
        sb.append(" " + oneTable[ones]);
      }else{
        sb.append(" " + twentyTable[ones]);
      }
    }
    sb.append(" ");
    return sb.toString();
  }
  
  public static void main(String[] args){
    System.out.println(numToWord(100100100));
  }
}
