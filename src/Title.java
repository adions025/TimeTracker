/**
 *Title class extends the Elements class and it's designed to provide the title
 *to the reports (short report or detailed report).
 *
 * @author Adonis Gonzalez
 * @version 28 Nov 2017
 * 
 */

public class Title extends Elements {

  /*
  * This function is part of the visitor pattern, it's the accept part.
  */
  public Title accept(Format format) {
    return this;
  }

  /*
  * This method provides the title for short reports.
  */
  public String getShortTitle() {
    String title = "Short report";
    return title;
  }

  /*
  * This method provides the title for detailed reports.
  */
  public String getDetailed() {
    String title = "Detailed report";
    return title;
  }
}