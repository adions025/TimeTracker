/**
 *This abstract class provides all the methods and common properties of 
 *both long and short formats.
 *
 *@bug: A bug has been observed: It seems that the clock updating time is not 
 *		instantaneously, as some milliseconds pass between tasks. It seems that 
 *		this causes an error that makes the report sometimes be 2 seconds past 
 *		or before its real time. The group is testing the results, but after 
 *		a thorough and extensive bug research, it was impossible to determine a
 *		possible solution for this error. Another possible cause of this 2 
 *		second disparity may be the stat time: reports that are initiated 
 *		between the seconds 22 and the 28 of the minute are more exact and less 
 *      incorrect. This may be affected by the Calendar or Date class.
 *
 * @author Adonis Gonzalez
 * @version 28 Nov 2017
 * 
 */

import java.util.Calendar;
import java.util.Collection;

public class Report {

  /**
   * Function that creates a long report (can be HTML or text).
   */
  public void createLongReport(Collection<Activity> roots) {
  }
  
  /**
   * Function that creates a long report (can be HTML or text).
   */
  public void createShortReport(Collection<Activity> roots) {
  }
  
  //Declarations of methods of this abstract class that are implemented
  /**
  * @uml.property name="elements"
  * @uml.associationEnd multiplicity="(0 -1)" aggregation="composite"
  *                     inverse="report:Elements"
  */
  private Collection<Elements> elements;

  /**
  * Getter of the property <tt>elements</tt>
  * 
  * @return Returns the elements.
  * @uml.property name="elements"
  */
  public Collection<Elements> getElements() {
    return elements;
  }

  /**
  * Setter of the property <tt>elements</tt>
  * 
  * @param elements
  *            The elements to set.
  * @uml.property name="elements"
  */
  public void setElements(Collection<Elements> elements) {
    this.elements = elements;
  }

  /**
  * @uml.property name="formats"
  * @uml.associationEnd multiplicity="(0 -1)" aggregation="composite"
  *                     inverse="report:Format"
  */
  private Collection<Format> format;

  /**
  * Getter of the property <tt>formats</tt>
  * 
  * @return Returns the format.
  * @uml.property name="formats"
  */
  public Collection<Format> getFormats() {
    return format;
  }

  /**
  * Setter of the property <tt>formats</tt>
  * 
  * @param formats
  *            The format to set.
  * @uml.property name="formats"
  */
  public void setFormats(Collection<Format> formats) {
    format = formats;
  }

  /**
  * @uml.property name="reportInit"
  */
  protected Calendar reportInit;

  /**
  * Getter of the property <tt>reportInit</tt>
  * 
  * @return Returns the reportInit.
  * @uml.property name="reportInit"
  */
  public Calendar getReportInit() {
    return reportInit;
  }

  /**
  * Setter of the property <tt>reportInit</tt>
  * 
  * @param reportInit
  *            The reportInit to set.
  * @uml.property name="reportInit"
  */
  public void setReportInit(Calendar reportInit) {
    this.reportInit = reportInit;
  }

  /**
  * @uml.property name="reportEnd"
  */
  protected Calendar reportEnd;

 /**
  * Getter of the property <tt>reportEnd</tt>
  * 
  * @return Returns the reportEnd.
  * @uml.property name="reportEnd"
  */
  public Calendar getReportEnd() {
    return reportEnd;
  }

  /**
  * Setter of the property <tt>reportEnd</tt>
  * 
  * @param reportEnd
  *            The reportEnd to set.
  * @uml.property name="reportEnd"
  */
  public void setReportEnd(Calendar reportEnd) {
    this.reportEnd = reportEnd;
  }

  /**
  * @uml.property name="reportDate"
  */
  protected Calendar reportDate;

  /**
  * Getter of the property <tt>reportDate</tt>
  * 
  * @return Returns the reportDate.
  * @uml.property name="reportDate"
  */
  public Calendar getReportDate() {
    setReportDate();
    return reportDate;
  }

  /**
  * Setter of the property <tt>reportDate</tt>
  * 
  * @uml.property name="reportDate"
  */
  public void setReportDate() {
    this.reportDate = Calendar.getInstance();
  }

  /**
  * @uml.property name="project"
  * @uml.associationEnd multiplicity="(1 1)" aggregation="composite"
  *                     inverse="report:Project"
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
  *            The project to set.
  * @uml.property name="project"
  */
  public void setProject(Project project) {
    this.project = project;
  }

  /**
  * @uml.property name="reportFormat"
  */
  protected String reportFormat;

  /**
  * Getter of the property <tt>reportFormat</tt>
  * 
  * @return Returns the reportFormat.
  * @uml.property name="reportFormat"
  */
  public String getReportFormat() {
    return reportFormat;
  }

  /**
  * Setter of the property <tt>reportFormat</tt>
  * 
  * @param reportFormat
  *            The reportFormat to set.
  * @uml.property name="reportFormat"
  */
  public void setReportFormat(String reportFormat) {
    this.reportFormat = reportFormat;
  }
}
