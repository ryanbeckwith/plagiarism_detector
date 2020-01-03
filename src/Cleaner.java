/**
 * Contains methods used to "clean" Strings,
 * or remove unwanted text such as XML metadata
 * or whitespace from a String.
 * 
 * 5/8/18, CSC 500, Ms. B,
 * Plagiarism Lab.
 * @author Ryan Beckwith
 */
public class Cleaner
{
  /**
   * Purges the input String of any XML metadata
   * and returns the cleaned String.
   * @param input, the String to be cleaned.
   * @return The cleaned String.
   */
  public static String cleanXML(String input)
  {
    System.out.println("Purging file contents of XML metadata...");
    return input.replaceAll("<[^<]*>", "");
  }
  
  /**
   * Purges the input String of any whitespace
   * and returns the cleaned String. Leaves
   * single spaces alone, but replaces instances
   * of spaces 2 or greater with a single space.
   * @param input, the String to be cleaned.
   * @return The cleaned String.
   */
  public static String cleanWhiteSpace(String input)
  {
    String s = input;
    s = s.replaceAll("[\n\t\r]*", "");
    s = s.replaceAll(" {2,}", " ");
    return s;
  }
  
  /**
   * Purges the input String of all whitespace
   * and non-alphanumerical characters.
   * @param input, the String to be cleaned.
   * @return The cleaned String.
   */
  public static String cleanFull(String input)
  {
    String s = input;
    s = s.replaceAll("[^\\w]|[\\s]", "");
    s = s.toLowerCase();
    return s;
  }
  
  //Main method testing.
  public static void main(String[] args)
  {
    System.out.println(cleanXML("<hello>my name is</hello>"));
    System.out.println(cleanWhiteSpace("Hello        my name is    Ryan\r\n" + 
        "\r\n" + 
        " Good morning\r\n" + 
        "    Hello   new york    \r\n" + 
        "  goodbye"));
    System.out.println(cleanFull("!@#%@#^@$%^#%!@#!@#$!@#\r\n" + 
        "314 134 #$! %1 51 451#%!@#4 \r\n" + 
        "\r\n" + 
        "   1  w342$233$#4#213Fsjalejae\r\n" + 
        "j14;324i$ 4%!$%J$%!32 jifQI1 #!$3$Rj q%$!@#% !gMN?:?<>?<:??"));
  }
}
