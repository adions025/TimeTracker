/**This abstract class provides the declaration of the Visitor Pattern and 
 * allows the subclasses to  work as intended: creating documents in HTML or 
 * text format.
 *
 * @author Adonis Gonzalez
 * @version 28 Nov 2017
 * 
 */

import java.io.BufferedWriter;
import java.util.Collection;

public class Format {
  /**
   * Declaration of the visitor part for the Visitor Pattern to get the title.
   */
  public String visitTitle(Title title) {
    return null;
  }

  /**
   * Declaration of the visitor part for the Visitor Pattern to get the 
   * introduction info.
   */
  public Collection<String> visitsubTitle(Subtitle subtitle, Report report,
      Collection<Activity> roots) {
    return null;
  }
  /**
   * Declaration of the visitor part for the Visitor Pattern to get the table.
   * It's actually unused but it's left here for futures expansions of the 
   * program.
   */
  public void visitTable(Table table) {
    table.accept(this);
  }
  
  /**
   * Properties of this abstract class and its getters and setters used by 
   * projects/tasks
   */
  
  /**
   * @uml.property name="report"
   * @uml.associationEnd inverse="formats:Report"
   */
  private Report report;

  /**
   * Getter of the property <tt>report</tt>
   * 
   * @return Returns the report.
   * @uml.property name="report"
   */
  public Report getReport() {
    return report;
  }

  /**
   * Setter of the property <tt>report</tt>
   * 
   * @param report
   *          The report to set.
   * @uml.property name="report"
   */
  public void setReport(Report report) {
    this.report = report;
  }

  /**
   * @uml.property name="title"
   */
  protected Title title;

  /**
   * Getter of the property <tt>title</tt>
   * 
   * @return Returns the title.
   * @uml.property name="title"
   */
  public Title getTitle() {
    return title;
  }

  /**
   * Setter of the property <tt>title</tt>
   * 
   * @param title
   *          The title to set.
   * @uml.property name="title"
   */
  public void setTitle(Title title) {
    this.title = title;
  }

  /**
   * @uml.property name="Subtitle"
   */
  protected Subtitle subtitle;

  /**
   * Getter of the property <tt>Subtitle</tt>
   * 
   * @return Returns the subtitle.
   * @uml.property name="Subtitle"
   */
  protected Subtitle getSubtitle() {
    return subtitle;
  }

  /**
   * Setter of the property <tt>Subtitle</tt>
   * 
   * @param Subtitle
   *          The subtitle to set.
   * @uml.property name="Subtitle"
   */
  protected void setSubtitle(Subtitle subtitle) {
    this.subtitle = subtitle;
  }

  /**
   * @uml.property name="table"
   */
  protected Table table;

  /**
   * Getter of the property <tt>table</tt>
   * 
   * @return Returns the table.
   * @uml.property name="table"
   */
  protected Table getTable() {
    return table;
  }

  /**
   * Setter of the property <tt>table</tt>
   * 
   * @param table
   *          The table to set.
   * @uml.property name="table"
   */
  protected void setTable(Table table) {
    this.table = table;
  }
  
  
  /**
   * Declarations of methods of this abstract class that are implemented and 
   * used by projects/tasks
   */
public PaginaWeb createIntroHtml(
	PaginaWeb webFormat, Collection<String> dates) {
    return webFormat;
  }

 public void createIntroText(Collection<String> dates, BufferedWriter out) {
  }

 public PaginaWeb createSubprojectHtml(PaginaWeb webFormat,
    Collection<String> dates) {
    return null;
  }

public PaginaWeb createTaskHtml(
	PaginaWeb webFormat, Collection<String> dates) {
    return null;
  }

public PaginaWeb createIntervalHtml(
		 PaginaWeb webFormat, Collection<String> dates) {
    return null;
  }

public void createSubprojectText(
		Collection<String> dates, BufferedWriter out) {
  }

public void createTaskText(
		Collection<String> dates, BufferedWriter out) {
  }

public void createIntervalText(
		Collection<String> dates, BufferedWriter out) {
  }

}
