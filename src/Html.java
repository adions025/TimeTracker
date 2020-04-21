/**The HTML class contains all the methods to create both long and short HTML 
 * reports.
 * All the methods use a collection of strings to just print the results over 
 * the corresponding document. It also implements the visitor part of the 
 * Visitor Pattern.
 *
 * @author Adonis Gonzalez
 * @version 28 Nov 2017
 * 
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Html extends Format {

  /**
   *Class constructor.
   */
  public Html(Project project, Title title, Subtitle subtitle, Table table) {
    this.project = project;
    this.title = title;
    this.subtitle = subtitle;
    this.table = table;
  }
  
  /**
   * This method is used to receive the title that is equal with the type of 
   * report.
   * Its the implementation of the visitor part of the Visitor Pattern.
   */
  public String visitTitle(Title title) {
    Title returnTitle = title.accept(this);
    return returnTitle.getShortTitle();
  }

  /**
   * This method is used to receive the report and root projects information 
   * to create the intro.
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
  @SuppressWarnings("unchecked")
  public PaginaWeb createIntroHtml(PaginaWeb webFormat, Collection<String> dates) {
    //Creation of the two tables (no interactive, in sake of format structure and testing)
    Table table = new Table(4, 2);
    final Table table2 = new Table(3, 4);
    
    final Iterator<String> it = dates.iterator();
    String next = it.next();
    //Creation of the introduction table
    table.setPosicio(1, 1, "");
    table.setPosicio(1, 2, "Date");
    table.setPosicio(2, 1, "From");
    table.setPosicio(2, 2, next);
    table.setPosicio(3, 1, "To");
    table.setPosicio(3, 2, it.next());
    table.setPosicio(4, 1, "Report generation date");
    table.setPosicio(4, 2, it.next());
    //Creation of the root project table
    table2.setPosicio(1, 1, "Project");
    table2.setPosicio(1, 2, "Start time");
    table2.setPosicio(1, 3, "Ending time");
    table2.setPosicio(1, 4, "Total time");
    //By nesting loops, the talbe gets autocreated iterating over the collection
    //of strings
    //created before
    for (int i = 2; i <= 3; i++) {
      for (int j = 1; j <= 4; j++) {
        table2.setPosicio(i, j, it.next());
      }
    }
    //Starting the HTML code creation
    webFormat.afegeixLiniaSeparacio();
    webFormat.afegeixHeader(visitTitle(title), 1, true);
    webFormat.afegeixLiniaSeparacio();
    webFormat.afegeixHeader("Date Information", 2, false);
    //Adding first table to the HTML
    ArrayList<String> tables = new ArrayList<String>();
    tables = table.getTaula();
    webFormat.afegeixTaula(tables, true, true);
    //Adding the root project information table
    webFormat.afegeixLiniaSeparacio();
    webFormat.afegeixHeader("Root Projects", 2, false);
    tables = table2.getTaula();
    webFormat.afegeixTaula(tables, true, false);

    webFormat.afegeixLiniaSeparacio();

    return webFormat;
  }
  
  /**
   * This function creates the subproject section at the long reports and print
   *  it to the report document.
  */
  @SuppressWarnings("unchecked")
  public PaginaWeb createSubprojectHtml(PaginaWeb webFormat,
      Collection<String> dates) {
    //Adding HTML code to present to the user what it's going to see
    webFormat.afegeixHeader("Subprojects", 2, false);
    webFormat.afegeixTextNormal("Only subprojects with some active task in the intervals"
            + " duration are included");
    //Creation of the table (no interactive, in sake of format structure 
    //and testing)
    Table table = new Table(2, 5);

    final Iterator<String> it = dates.iterator();
    //Creation of the subproject table (also with nested loops)
    table.setPosicio(1, 1, "Project father");
    table.setPosicio(1, 2, "Subproject");
    table.setPosicio(1, 3, "Start date");
    table.setPosicio(1, 4, "Ending date");
    table.setPosicio(1, 5, "Total time");
    for (int i = 2; i <= 2; i++) {
      for (int j = 1; j <= 5; j++) {
        table.setPosicio(i, j, it.next());
      }
    }
    //Adding the tables to the HTML code
    ArrayList<String> tables = new ArrayList<String>();
    tables = table.getTaula();
    webFormat.afegeixTaula(tables, true, false);
    webFormat.afegeixLiniaSeparacio();

    return webFormat;
  }

  /**
   * This function creates the Tasks section at the long reports and print it to the
   * report document.
  */
  @SuppressWarnings("unchecked")
  public PaginaWeb createTaskHtml(PaginaWeb webFormat, Collection<String> dates) {
    //Adding HTML code to present to the user what it's going to see
    webFormat.afegeixHeader("Tasks", 2, false);
    webFormat.afegeixTextNormal("Those are the tasks which are part of their respective fathers");
    //Creation of the table (no interactive, in sake of format structure and testing)
    Table table = new Table(4, 5);

    final Iterator<String> it = dates.iterator();
    //Creation of the task table (also with nested loops)
    table.setPosicio(1, 1, "Project father");
    table.setPosicio(1, 2, "Task");
    table.setPosicio(1, 3, "Start date");
    table.setPosicio(1, 4, "Ending date");
    table.setPosicio(1, 5, "Total time");
    for (int i = 2; i <= 4; i++) {
      for (int j = 1; j <= 5; j++) {
        table.setPosicio(i, j, it.next());
      }
    }
    //Adding the tables to the HTML code
    ArrayList<String> tables = new ArrayList<String>();
    tables = table.getTaula();
    webFormat.afegeixTaula(tables, true, false);
    webFormat.afegeixLiniaSeparacio();
    
    return webFormat;
  }

  /**
   * This function creates the interval section at the long reports and print 
   * it to the report document.
  */
  @SuppressWarnings("unchecked")
  public PaginaWeb createIntervalHtml(PaginaWeb webFormat, Collection<String> dates) {
    //Adding HTML code to present to the user what it's going to see
    webFormat.afegeixHeader("Intervals", 2, false);
    webFormat
        .afegeixTextNormal("Those are the intervals which are part of their respective fathers");
    //Creation of the table (no interactive, in sake of format structure and testing)
    Table table = new Table(4, 5);

    final Iterator<String> it = dates.iterator();
    //Creation of the interval table (also with nested loops)
    table.setPosicio(1, 1, "Father Project");
    table.setPosicio(1, 2, "Interval");
    table.setPosicio(1, 3, "Start date");
    table.setPosicio(1, 4, "Ending date");
    table.setPosicio(1, 5, "Total time");
    for (int i = 2; i <= 4; i++) {
      for (int j = 1; j <= 5; j++) {
        table.setPosicio(i, j, it.next());
      }
    }
    //Adding the tables to the HTML code
    ArrayList<String> tables = new ArrayList<String>();
    tables = table.getTaula();
    webFormat.afegeixTaula(tables, true, false);
    webFormat.afegeixLiniaSeparacio();
    
    return webFormat;
  }  
  
  
  //Properties of this abstract class and its getters and setters
  /**
   * @uml.property name="project"
   */
  private Project project;

  /**
   * Getter of the property <tt>project</tt>
   * 
   * @return Returns the project.
   * @uml.property name="project"
   */
  public Project getProject() {
    return project;
  }

  /**
   * Setter of the property <tt>project</tt>
   * 
   * @param project
   *          The project to set.
   * @uml.property name="project"
   */
  public void setProject(Project project) {
    this.project = project;
  }
}
