import java.io.*;

/**
 * Represents an object that can read
 * a text (.txt) file. The name of the
 * text file is passed into the constructor.
 * 
 * 5/8/18, CSC 500, Ms. B,
 * Plagiarism Lab.
 * @author Ryan Beckwith
 */
public class TxtReader implements Reader
{
  //Represents the name of the file to be read.
  private String fileName;
  
  //1-arg constructor, takes the name of the file as a parameter.
  public TxtReader(String fileName)
  {
    this.fileName = fileName;
  }
  
  /**
   * Reads the text file and returns a
   * String with the contents. A newline
   * (\n) character is appended to the
   * end of each line read.
   * @return totalFile, the entire contents of the file.
   */
  public String readFile()
  {
    String totalFile = "";
    String line = null;
    try
    {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      line = bufferedReader.readLine();
      while(line != null) 
      {
        totalFile += line + "\n";
        line = bufferedReader.readLine();
      }
      bufferedReader.close();
    }
    catch (FileNotFoundException e)
    {
      System.err.println("Couldn't find file.");
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return totalFile;
  }
  
  //Main method testing.
  public static void main(String[] args)
  {
    String file = "Resources\\TestText.txt";
    TxtReader textReader = new TxtReader(file);
    System.out.println(textReader.readFile());
  }

}
