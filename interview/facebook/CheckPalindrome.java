
/**
 *
 *
 * 判断一个string是否回文，要求ignore cases, spaces and punctuation，O(1) space
 */

public class CheckPalindrome{
  public static boolean isPalindrome(String str){
    if(null == str){
      throw new IllegalArgumentException();
    }
    int len = str.length();
    int start = 0;
    int end = len - 1;
    while(start < end){
      while(start < end && !isLetterOrNum(str.charAt(start))){
        start++;
      }
      while(start < end && !isLetterOrNum(str.charAt(end))){
        end--;
      }
      if(start >= end){
        break;
      }
      if(Character.toLowerCase(str.charAt(start)) != Character.toLowerCase(str.charAt(end))){
        return false;
      }else{
        start++;
        end--;
      }
    }
    return true;
  }

  private static boolean isLetterOrNum(char c){
    return (c > 47 && c < 58) || (c > 64 && c < 91)
      || (c > 96 && c < 123);
  }

  public static void main(String[] args){
    String str1 = "abcdefedc ba";
    System.out.println(str1 + " " + isPalindrome(str1));
    String str2 = "daD";
    System.out.println(str2 + " " + isPalindrome(str2));
    String str3 = "Hol;oh..";
    System.out.println(str3 + " " + isPalindrome(str3));
    String str4 = " Good";
    System.out.println(str4 + " " + isPalindrome(str4));
  }
}
