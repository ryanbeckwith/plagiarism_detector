import java.util.ArrayList;

/**
 * Represents the PairReport class. A
 * PairReport object represents two cleaned
 * files that have been compared using the
 * Detector class. It contains getters and
 * setters for the majority of its private
 * fields.
 * 
 * 5/8/18, CSC 500, Ms. B,
 * Plagiarism Lab.
 * @author Ryan Beckwith
 */
public class PairReport implements Comparable<PairReport>
{
  //Field for the first file name.
  private String file1;
  //Field for the second file name.
  private String file2;
  //Field for the first file's Cleaner Code.
  private String cleanerFile1;
  //Field for the second file's Cleaner Code.
  private String cleanerFile2;
  //Field for the Detector object.
  private Detector detector;
  
  //4-arg constructor, extracts the data from the file, cleans it,
  //and calculates the similarity score using the Detector object.
  public PairReport(String a, String b, String cleaner1, String cleaner2)
  {
    System.out.println("Creating PairReports...");
    file1 = a;
    file2 = b;
    cleanerFile1 = cleaner1;
    cleanerFile2 = cleaner2;
    cleanAndInitialize();
  }
  
  /**
   * Helper method to initialize the Detector
   * object and clean the contents of the files.
   */
  private void cleanAndInitialize()
  {      
    DocXReader reader1 = new DocXReader(file1);
    DocXReader reader2 = new DocXReader(file2);
    
    String file1Contents = reader1.readFile();
    String file2Contents = reader2.readFile();
    
    if (cleanerFile1.equals("X"))
      file1Contents = Cleaner.cleanXML(file1Contents);
    else if (cleanerFile1.equals("W"))
      file1Contents = Cleaner.cleanWhiteSpace(file1Contents);
    else if (cleanerFile1.equals("F"))
      file1Contents = Cleaner.cleanFull(file1Contents);
    else if (cleanerFile1.equals("XF"))
    {
      file1Contents = Cleaner.cleanXML(file1Contents);
      file1Contents = Cleaner.cleanFull(file1Contents);
    }
    else if (cleanerFile1.equals("XW"))
    {
      file1Contents = Cleaner.cleanXML(file1Contents);
      file1Contents = Cleaner.cleanWhiteSpace(file1Contents);
    }
    else
      System.out.println("Unrecognized Cleaner Code for File 1.");
    
    if (cleanerFile2.equals("X"))
      file2Contents = Cleaner.cleanXML(file2Contents);
    else if (cleanerFile2.equals("W"))
      file2Contents = Cleaner.cleanWhiteSpace(file2Contents);
    else if (cleanerFile2.equals("F"))
      file2Contents = Cleaner.cleanFull(file2Contents);
    else if (cleanerFile2.equals("XF"))
    {
      file2Contents = Cleaner.cleanXML(file2Contents);
      file2Contents = Cleaner.cleanFull(file2Contents);
    }
    else if (cleanerFile2.equals("XW"))
    {
      file2Contents = Cleaner.cleanXML(file2Contents);
      file2Contents = Cleaner.cleanWhiteSpace(file2Contents);
    }
    else
      System.out.println("Unrecognized Cleaner Code for File 2.");
    
    detector = new Detector(file1Contents, file2Contents);
  }
  
  /**
   * Getter for file1.
   * @return file1
   */
  public String getFile1()
  {
    return file1;
  }
  
  /**
   * Setter for file1.
   * @param s, the name of the file.
   */
  public void setFile1(String s)
  {
    file1 = s;
  }
  
  /**
   * Getter for file2.
   * @return file2
   */
  public String getFile2()
  {
    return file2;
  }
  
  /**
   * Setter for file2.
   * @param s, the name of the file.
   */
  public void setFile2(String s)
  {
    file2 = s;
  }
  
  /**
   * Getter for the first file's Cleaner Code.
   * @return cleanerFile1
   */
  public String getCleanerFile1()
  {
    return cleanerFile1;
  }
  
  /**
   * Setter for the first file's Cleaner Code.
   * @param s, the Cleaner Code.
   */
  public void setCleanerFile1(String s)
  {
    cleanerFile1 = s;
  }
  
  /**
   * Getter for the second file's Cleaner Code.
   * @return cleanerFile2
   */
  public String getCleanerFile2()
  {
    return cleanerFile2;
  }
  
  /**
   * Setter for the second file's Cleaner Code.
   * @param s, the Cleaner Code.
   */
  public void setCleanerFile2(String s)
  {
    cleanerFile2 = s;
  }
  
  /**
   * Getter for the first string in the Detector
   * object (the contents of the first file).
   * @return detector.getString1()
   */
  public String getString1()
  {
    return detector.getString1();
  }
  
  /**
   * Getter for the second string in the Detector
   * object (the contents of the second file).
   * @return detector.getString2()
   */
  public String getString2()
  {
    return detector.getString2();
  }
  
  /**
   * Getter for the cList field in the
   * Detector object.
   * @return detector.getCList()
   */
  public ArrayList<String> getCList()
  {
    return detector.getCList();
  }
  
  /**
   * Getter for the similarityScore field in
   * the Detector object.
   * @return detector.getSimilarityScore()
   */
  public double getSimilarityScore()
  {
    return detector.getSimilarityScore();
  }

  /**
   * Overridden compareTo method, compares a PairReport
   * object to another PairReport object based on their
   * similarity scores. If <code>this</code> object's
   * similarity score is greater than the similarity score
   * of the <code>other</code> object, this method returns
   * a positive integer value. If <code>this</code> object's
   * similarity score is smaller, a negative integer is
   * returned. If the two similarity scores are the same,
   * 0 is returned.
   * @param other, the other PairReport object.
   */
  public int compareTo(PairReport other)
  {
    return (int)(getSimilarityScore() * 10000) - (int)(other.getSimilarityScore() * 10000); 
  }
  
  /**
   * Returns a String representation of the
   * PairReport object.
   * @return s, the full String representation.
   */
  public String toString()
  {
    String s = "";
    s += "File 1: " + file1 + "\n";
    s += "File 2: " + file2 + "\n";
    s += "Similarity Score: " + getSimilarityScore() + "\n";
    s += "Number of C's: " + getCList().size() + "\n";
    s += "Largest C: " + getCList().get(getCList().size() - 1) + "\n";
    return s;
  }
