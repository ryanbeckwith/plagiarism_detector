/**
 * Represents the interface for all classes
 * that can be considered Readers. A Reader
 * is a class that interprets a file type by
 * reading its contents.
 * 
 * 5/8/18, CSC 500, Ms. B,
 * Plagiarism Lab.
 * @author Ryan Beckwith
 */
public interface Reader
{
  /**
   * The readFile method must return a String
   * containing the contents of a file in a
   * given Reader class.
   * @return fileContents, the entire contents of the file.
   */
  String readFile();
}
