import java.util.ArrayList;
import java.util.Collections;
import java.io.File;

/**
 * Represents the PlagiarismDetector class.
 * A PlagiarismDetector object compares every
 * possible combination of files in two folders,
 * a Sources folder and a Submissions folder,
 * and detects whether or not plagiarism has
 * occurred. It also checks every possible
 * combination in just the Submissions folder
 * for plagiarism.
 * 
 * 5/8/18, CSC 500, Ms. B,
 * Plagiarism Lab
 * @author Ryan Beckwith
 */
public class PlagiarismDetector
{
  //Field representing the Submissions folder.
  private String submissions;
  //Field representing the Sources folder.
  private String sources;
  //Static final field representing the lowest possible
  //similarityScore that still constitutes plagiarism.
  public static final double PLAGIARISM_THRESHOLD = 0.25;
  //Field that holds all the PairReport combinations.
  private ArrayList<PairReport> reports;
  
  //2-args constructor, initializes fields and calls generateReports
  //in order to populate the reports field.
  public PlagiarismDetector(String folderName1, String folderName2)
  {
    submissions = folderName1;
    sources = folderName2;
    reports = new ArrayList<PairReport>();
    generateReports();
  }
  
  /**
   * Helper method that returns an ArrayList of Strings
   * containing every file in the Submissions folder.
   * @return fileNames, the files in the Submissions folder.
   */
  private ArrayList<String> getSubmissionsFiles()
  {
    ArrayList<String> fileNames = new ArrayList<String>();
    File dir = new File(submissions);
    File[] dirListing = dir.listFiles();
    if (dirListing != null)
    {
      for (File file : dirListing)
      {
        fileNames.add(file.getName());
      }
    }
    else
    {
      System.out.println("Something's wrong with the directory reading.");
    }
    return fileNames;
  }
  
  /**
   * Helper method that returns an ArrayList of Strings
   * containing every file in the Sources folder.
   * @return fileNames, the files in the Sources folder.
   */
  private ArrayList<String> getSourcesFiles()
  {
    ArrayList<String> fileNames = new ArrayList<String>();
    File dir = new File(sources);
    File[] dirListing = dir.listFiles();
    if (dirListing != null)
    {
      for (File file : dirListing)
      {
        fileNames.add(file.getName());
      }
    }
    else
    {
      System.out.println("Something's wrong with the directory reading.");
    }
    return fileNames;
  }
  
  /**
   * Helper method that populates the reports field.
   * The default Cleaner Code for each PairReport object
   * is "X", which purges XML metadata.
   */
  private void generateReports()
  {
    ArrayList<String> submissionsFiles = getSubmissionsFiles();
    ArrayList<String> sourcesFiles = getSourcesFiles();
    
    for (String sourceFile : sourcesFiles)
    {
      for (String submissionFile: submissionsFiles)
      {
        String fullNameSource = sources + "\\" + sourceFile;
        String fullNameSubmission = submissions + "\\" + submissionFile;
        reports.add(new PairReport(fullNameSource, fullNameSubmission, "X", "X"));
      }
    }
    
    for (int i = 0; i < submissionsFiles.size() - 1; i++)
    {
      for (int j = i + 1; j < submissionsFiles.size(); j++)
      {
        String fullNameSubmission1 = submissions + "\\" + submissionsFiles.get(i);
        String fullNameSubmission2 = submissions + "\\" + submissionsFiles.get(j);
        reports.add(new PairReport(fullNameSubmission1, fullNameSubmission2, "X", "X"));
      }
    }
  }
  
  /**
   * Returns an ArrayList of PairReport objects
   * with a similarityScore that is higher than
   * the plagiarism threshold.
   * @return offenders
   */
  public ArrayList<PairReport> getAllPotentialOffenders()
  {
    ArrayList<PairReport> offenders = new ArrayList<PairReport>();
    for (PairReport p : reports)
    {
      if(p.getSimilarityScore() > PLAGIARISM_THRESHOLD)
      {
        offenders.add(p);
      }
    }
    return offenders;
  }
  
  /**
   * Prints out each PairReport object
   * in reports using PairReport's default
   * toString method.
   */
  public void printReports()
  {
    Collections.sort(reports);
    for (PairReport p : reports)
    {
      System.out.println(p);
    }
  }
}
