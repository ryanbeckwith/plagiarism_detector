import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.nio.charset.Charset;

/**
 * Represents the DocXReader class for use in
 * the Plagiarism Lab. A DocXReader object
 * interprets and reads a .docx file whose
 * name is passed into the constructor.
 * 
 * 5/8/18, CSC 500, Ms. B,
 * Plagiarism Lab.
 * (Original code sourced from Dr. Zufelt).
 * @author Ryan Beckwith
 */
public class DocXReader implements Reader
{
  //Represents the name of the file to be read.
  private String fileName;
  
  //1-arg constructor, takes the name of the file as a parameter. 
  public DocXReader(String fileName)
  {
    this.fileName = fileName;
  }
  
  /**
   * Reads the file, returning the contents of the
   * document.xml file. XML metadata is included
   * in the returned string.
   * @return docText, the entire contents of the document.xml file.
   */
  public String readFile()
  {
    System.out.println("Reading file...");
    String docText = "";
    try
    {
        // A ZipInputStream reads in the zip file.
        ZipInputStream zis = new ZipInputStream(new FileInputStream(fileName),
                                                Charset.forName("UTF-8"));
        // Iterates through the files in the .docx zipped folder.
        ZipEntry ze = zis.getNextEntry();
        while(ze != null) {
          if (ze.getName().equals("word/document.xml"))
          {
            docText += readInFileContents(zis);
            break;
          }
          ze = zis.getNextEntry();
        }
        // Close the stream to release the file handle.
        zis.closeEntry();
        zis.close();
    }
    catch(IOException ex)
    {
        ex.printStackTrace();
    }
    return docText;
  }

  /**
   * Helper method for the readFile method. Takes a 
   * ZipInputStream as a parameter and returns a String
   * that contains the contents of the file.
   * @param zis, a ZipInputStream object.
   * @return document, the entire document.
   * @throws IOException
   */
  private String readInFileContents(ZipInputStream zis) throws IOException
  {
    String document = "";
    final byte[] buffer = new byte[8192];
    int bytesRead = 0;
    while((bytesRead = zis.read(buffer)) != -1) 
    {
      document += new String(buffer, 0, bytesRead);
    }
    return document;
  }
  
  //Main method testing.
  public static void main(String[] args)
  {
    String name = "Submissions\\Doc9.docx";
    DocXReader docReader = new DocXReader(name);
    System.out.println(docReader.readFile());
  }
}
