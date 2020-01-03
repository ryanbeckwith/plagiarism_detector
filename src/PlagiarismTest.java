
public class PlagiarismTest
{
  private static String folder1 = "Submissions";
  private static String folder2 = "Sources";
  private static long time;

  public static void main(String[] args)
  {
    time = System.currentTimeMillis();
    PlagiarismDetector p = new PlagiarismDetector(folder1, folder2);
    p.printReports();
    System.out.println("\n\n\n");
    System.out.println(p.getAllPotentialOffenders());
    System.out.println((System.currentTimeMillis() - time) / 1000.0);
  }
}
