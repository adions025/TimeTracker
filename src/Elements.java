/**
 *This abstract class provides the visited node structure of the visitor
 * pattern.
 *All the subclasses that depend of this one provide the report one part of it
 *(like the title, introduction...).
 *
 * @author Adonis Gonzalez
 * @version 28 Nov 2017
 * 
 */

public class Elements {

  /**
   * This method provides the abstraction method of accept (visitor pattern) 
   * for all its subclasses.
  */
  public Elements accept(Format format) {
    return this;
  }
  
  /**
   * Properties of this abstract class and its getters and setters used by 
   * projects/tasks
   */
  
  /** 
  * @uml.property name="report"
  * @uml.associationEnd inverse="elements:Report"
  */
  private Report report;

  /** 
  * Getter of the property <tt>report</tt>
  * @return  Returns the report.
  * @uml.property  name="report"
  */
  public Report getReport() {
    return report;
  }

  /** 
  * Setter of the property <tt>report</tt>
  * @param report  The report to set.
  * @uml.property  name="report"
  */
  public void setReport(Report report) {
    this.report = report;
  }
}
