/**The text class contains all the methods to create both long and short text 
 * reports.
 * All the methods use a collection of strings to just print the results 
 * over the corresponding document. It also implements the visitor part of 
 * the Visitor Pattern.
 *
 * @author Adonis Gonzalez
 * @version 28 Nov 2017
 * 
 */

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

public class Text extends Format {
  /**
   * Class constructor.
   */
  public Text(Project project, Title title, Subtitle subtitle, Table table) {
    this.project = project;
    this.title = title;
    this.subtitle = subtitle;
    this.table = table;
  }
  
  /**
   * This method is used to receive the title that is equal with the type 
   * of report.
   * Its the implementation of the visitor part of the Visitor Pattern.
   */
  public String visitTitle(Title title) {
    Title returnTitle = title.accept(this);
    return returnTitle.getShortTitle();
  }
  
  /**
   * This method is used to receive the report and root projects information to 
   * create the intro.
   * Its the implementation of the visitor part of the Visitor Pattern.
   */
  public Collection<String> visitsubTitle(Subtitle subtitle, Report report,
      Collection<Activity> roots) {
    Subtitle returnSubtitle = subtitle.accept(this);
    return returnSubtitle.getSubtitleIntro(report, roots);
  }
  
  /**
   * This method implements the visitor part of the Visitor Pattern for the 
   * Table object.
   * Although it's implemented, it isn't used but it's left here in case of 
   * future expansions.
   */
  public void visitTable(Table table) {
    table.accept(this);
  }


  /**
   * This function creates both general information about the report times and
   * the root projects information sections on both long and short reports and 
   * print them to the report document.
  */
  public void createIntroText(Collection<String> dates, BufferedWriter out) {
    try {

      final Iterator<String> it = dates.iterator();

      out.write("---------------------------------------------------------" 
            + "---------------------------------------------\n");
      out.write(visitTitle(title) + "\n");
      out.write("---------------------------------------------------------" 
            + "---------------------------------------------\n");
      //Report information
      out.write("Date Information\n");
      out.write("From:  " + it.next() + "\n");
      out.write("To:  " + it.next() + "\n");
      out.write("Report generation date:  " + it.next() + "\n");
      out.write("--------------------------------------------------------" 
          + "----------------------------------------------\n");
      //Roots information
      out.write("Roots projects\n");
      out.write("#  Project Start date          End Date             " 
      + "Total Time\n");
      /**
       * The following for divides by 4 because every 4 Strings it changes to 
       * another root project
       */
      for (int i = 0; i < (dates.size() / 4); i++) {
        out.write(i + 1 + "  " + it.next() + "      " + it.next() + " "
            + it.next() + " " + it.next() + "\n");
      }
      out.write("--------------------------------------------------------" 
            + "----------------------------------------------\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This function creates the subproject section at the long reports and 
   * print it to the report document.
  */
  public void createSubprojectText(Collection<String> dates, 
		  BufferedWriter out) {
    try {

      final Iterator<String> it = dates.iterator();

      out.write("Subprojects\n");
      out.write("Father    Subproject   Start date          End Date        " 
      + "Total Time\n");
      /**
       * The following for divides by 5 because every 5 Strings it changes to 
       * another subproject
       */
      for (int i = 0; i < (dates.size() / 5); i++) {
        out.write(it.next() + "   " + it.next() + "      " + it.next() + ""
            + it.next() + " " + it.next() + "\n");
      }
      out.write("------------------------------------------------------" 
          + "------------------------------------------------\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This function creates the Tasks section at the long reports and print 
   * it to the report document.
  */
  public void createTaskText(Collection<String> dates, BufferedWriter out) {
    try {

      final Iterator<String> it = dates.iterator();

      out.write("Tasks\n");
      out.write("Father  Task Start date          End Date             " 
      + "Total Time\n");
      /**
       * The following for divides by 5 because every 5 Strings it changes 
       * to another task
       */
      for (int i = 0; i < (dates.size() / 5); i++) {
        out.write(it.next() + it.next() + "      " + it.next() + "" + it.next()
            + " " + it.next() + "\n");
      }
      out.write("-----------------------------------------------------------" 
      + "-------------------------------------------\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * This function creates the interval section at the long reports and print 
   * it to the report document.
  */
  public void createIntervalText(Collection<String> dates, BufferedWriter out) {
    try {

      final Iterator<String> it = dates.iterator();

      out.write("Roots projects\n");
      out.write("Father Interval Start date          End Date             " 
      + "Total Time\n");
      /**
       * The following for divides by 5 because every 5 Strings it changes to 
       * another interval
       */
      for (int i = 0; i < (dates.size() / 5); i++) {
        out.write(it.next() + it.next() + "      " + it.next() + "" + it.next()
            + " " + it.next() + "\n");
      }
      out.write("-------------------------------------------------------" 
            + "-----------------------------------------------\n");
    } catch (IOException e) {
      e.printStackTrace();
    } //end catch
  } //end function
  
  //Properties of this abstract class and its getters and setters
  /**
   * @uml.property name="project"
   */
  private Project project;

  /**
   * Getter of the property <tt>project</tt>.
   * 
   * @return Returns the project.
   * @uml.property name="project"
   */
  public Project getProject() {
    return project;
  }

  /**
   * Setter of the property <tt>project</tt>.
   * 
   * @param project
   *          The project to set.
   * @uml.property name="project"
   */
  public void setProject(Project project) {
    this.project = project;
  }
} //end Class
