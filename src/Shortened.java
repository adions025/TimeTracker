/**This class allows to create short reports in text and HTML formats. 
 * It deppends of the constructor reportFormat initialitzation that the 
 * client will call.
 *
 * @author Adonis Gonzalez
 * @version 28 Nov 2017
 * 
 */

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Collection;      

public class Shortened extends Report {

  /**
  * Class constructor.
  */
  public Shortened(String reportFormat, Calendar reportInit, Calendar reportEnd) {
    this.reportFormat = reportFormat;
    this.reportInit = reportInit;
    this.reportEnd = reportEnd;
    this.reportDate = getReportDate();
  }
  
  /**
   * This method creates a short report from the information received from 
   * the root project. Depending of the constructor initialitzaion, it can 
   * create the report on HTML or text format.
   */
  public void createShortReport(Collection<Activity> roots) {
    /**
     * Depending of the text introduced, a HTML report or text one will 
     * be created
     */
    if (reportFormat == "HTML") {
      //Creation of the necessary objects to create the HTML code
      Title title = new Title();
      Subtitle subtitle = new Subtitle();
      //Important to notice that no table it's created and passed
      Format shortHtml = new Html(getProject(), title, subtitle, null);
      
      //Receiving the necessary information from the root tree and using it to
      //create the necessary content
      PaginaWeb webFormat = new PaginaWeb();
      Collection<String> intro = shortHtml.visitsubTitle(subtitle, this, roots);
      webFormat = shortHtml.createIntroHtml(webFormat, intro);
      
      webFormat.afegeixTextNormal("Time Tracker v2.0");
      try {
        //Redirecting the console printing to a document to save the HTML
        PrintStream fileStream = new PrintStream("ShortHtmlReport.html");
        System.setOut(fileStream);
        webFormat.escriuPagina();
        fileStream.close();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    } else if (reportFormat == "Text") {

      try {
        //Creation of the necessary objects to create the text code
        Title title = new Title();
        Subtitle subtitle = new Subtitle();
        Format shortText = new Text(getProject(), title, subtitle, null);
        //Receiving the necessary information
        Collection<String> info = shortText.visitsubTitle(subtitle, this, 
        		roots);
        
        /**
         * Creating the document and its FileWriter, which will be passed to 
         * the text creators to edit over the same document
         */
       
        BufferedWriter out = new BufferedWriter(new FileWriter("ShortTextReport.txt", false));
        shortText.createIntroText(info, out);

        out.write("TimeTracker V2.0");
         //Closing document writing
        out.close();
      } catch (IOException e) {
        e.printStackTrace();
      } //end catch
    } //end else if
  } //end method
} //end Class
