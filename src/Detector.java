import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the Detector class. A Detector
 * object compares two Strings and finds their
 * similarity score in order to determine how
 * similar they are.
 * 
 * 5/8/18, CSC 500, Ms. B,
 * Plagiarism Lab.
 * @author Ryan Beckwith
 */
public class Detector
{
  //Field for the first String.
  private final String string1;
  //Field for the second String.
  private final String string2;
  //Field that holds the value of the similarity score.
  private final double similarityScore;
  //Field that holds all C strings, or strings that
  //are in common between string1 and string2.
  //Sorted by length.
  private final ArrayList<String> cList;
  //Score field for calculating the similarity score.
  private int score = 0;
  
  //2-arg constructor, creates a Detector object.
  public Detector(String a, String b)
  {
    System.out.println("Calculating similarity scores...");
    string1 = a;
    string2 = b;
    cList = new ArrayList<String>();
    setSimilarityScore(a, b);
    similarityScore = (double)score / (double)(a.length() + b.length());
    sortCList();
  }
  
  /**
   * Calculates the similarity score between
   * two Strings, and sets the field similarityScore
   * to the value calculated.
   * @param a, the first String.
   * @param b, the second String.
   */
  private void setSimilarityScore(String a, String b)
  {
    String c = largestSubstringInCommon(a, b);
    if (c.equals(""))
      return;
    else
    {
      cList.add(c);
      score += c.length() * 2;
      String a0 = a.substring(0, a.indexOf(c));
      String b0 = b.substring(0, b.indexOf(c));
      String a1 = a.substring(a.indexOf(c) + c.length());
      String b1 = b.substring(b.indexOf(c) + c.length());
      setSimilarityScore(a0, b0);
      setSimilarityScore(a1, b1);
    }
  }
  
  /**
   * Finds the longest common substring between two Strings.
   * @param a, the first String.
   * @param b, the second String.
   * @return largestSubstring, the largest common substring.
   */
  private String largestSubstringInCommon(String a, String b)
  {
    int maxLength = 0;
    String largestSubstring = "";
    for (int i = 0; i < a.length(); i++)
    {
      for (int j = i + 1; j < a.length() + 1; j++)
      {
        String s = a.substring(i, j);
        if (b.contains(s) && s.length() > maxLength)
        {
          largestSubstring = s;
          maxLength = s.length();
        }
      }
    }
    return largestSubstring;
  }
  
  /**
   * Sorts the field cList by length.
   */
  private void sortCList()
  {
    for (int i = 1; i < cList.size(); i++)
    {
      String tempString = cList.get(i);
      int temp = cList.get(i).length();
      int j = i - 1;
      
      while (j >= 0 && cList.get(j).length() > temp)
      {
        cList.set(j + 1, cList.get(j));
        j--;
      }
      
      cList.set(j + 1, tempString);
    }
  }
  
  /**
   * Getter method for string1.
   * @return string1
   */
  public String getString1()
  {
    return string1;
  }
  
  /**
   * Getter method for string2.
   * @return string2
   */
  public String getString2()
  {
    return string2;
  }
  
  /**
   * Getter method for cList.
   * @return cList
   */
  public ArrayList<String> getCList()
  {
    return cList;
  }
  
  /**
   * Getter method for similarityScore.
   * @return similarityScore
   */
  public double getSimilarityScore()
  {
    return similarityScore;
  }
  
  //Main method testing.
  public static void main(String[] args)
  {
    Detector d = new Detector("Pennsylvania", "Pencilvaneya");
    Detector d2 = new Detector("Hello", "Hell");
    Detector d3 = new Detector("Youre", "Goodbye");
    Detector d4 = new Detector("Zyzzyva", "Enter");
    Detector d5 = new Detector("They", "Yeht");
    Detector d6 = new Detector("Interesting", "Interested");
    Detector d7 = new Detector("q$#TGFDGAw4", "%HWTEHWWFQWR95tq4$#%#451");
    System.out.println(d.similarityScore);
    System.out.println(d.cList);
    System.out.println(d2.similarityScore);
    System.out.println(d2.cList);
    System.out.println(d3.similarityScore);
    System.out.println(d3.cList);
    System.out.println(d4.similarityScore);
    System.out.println(d4.cList);
    System.out.println(d5.similarityScore);
    System.out.println(d5.cList);
    System.out.println(d6.similarityScore);
    System.out.println(d6.cList);
    System.out.println(d7.similarityScore);
    System.out.println(d7.cList);
  }
}
