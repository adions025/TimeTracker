/**
 *Project class contains tasks and other projects, creating the necessary tree 
 *structure.
 *It also allows to calculate the total time of itself 
 *(through its tasks and sub projects).
 * Also, it has a function that helps the Printer class to show the activities 
 * times recursively.
 *
 * @author Adonis Gonzalez
 * @version 28 Nov 2017
 * 
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;


public class Project extends Activity implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * Class constructor.
   */
  public Project(String name, String description, Activity father) {
    this.name = name;
    this.description = description;
    this.father = father;

    if (this.father != null) {
      this.father.addActivity(this); /*Avoiding nullPointer errors*/
    }
    //Checking the project invariant to avoid creation errors
    assert projectInvariant() : "Error creating project";
  }

  /**
   * Iteration through the tasks to get the total time of a project to print 
   * as a result.
   */
  public long calculateTime() {
    long totalTime = 0;
    Iterator<Activity> it = getActivities().iterator();
    while (it.hasNext()) {
      Activity activity = it.next();
      totalTime += activity.calculateTime();
    }
    return totalTime;
  }

  /**
   * This function prints recursively all the dates and runtime of both projects
   * and tasks inside the main project "fatherActivity". Called by Printer.
   * Uses the Printer logger to show better the information through the console
   */
  public void printerElements(Activity fatherActivity) {
    Iterator<Activity> it = fatherActivity.getActivitiesList().iterator();
    Activity activity = null;

    while (it.hasNext()) {
      activity = it.next();
      Printer.logger.info(activity.getName() + "    " + activity.converterInit(
    		  activity) + "              " + activity.converterEnd(activity) 
    		  + "              " + activity.timeConverter(activity));
      activity.printerElements(activity);
    }
  }
  /*
   * Function to check the project invariant.
   */
  protected boolean projectInvariant() {
    if ((activities == null) && (name != null) && (description != null)) {
      return false;
    } else {
      return true;
    }
  }
  
  /*
   * Properties of the class and its getters and setters.
   */
  /**
  * @uml.property  name="activities"
  * @uml.associationEnd  multiplicity="(1 -1)" aggregation="composite" inverse="project:Activity"
  */
  private Collection<Activity> activities = new ArrayList<Activity>();
  
  /**
  * Getter of the property <tt>activities</tt>.
  * @return  Returns the activities.
  * @uml.property  name="activities"
  */
  public Collection<Activity> getActivities() {
    return activities;
  }

  /**
  * Setter of the property <tt>activities</tt>.
  * @param activities  The activities to set.
  * @uml.property  name="activities"
  */
  public void setActivities(Collection<Activity> activities) {
    this.activities = activities;
  }
  
  //Aditionals methods to the activities property (setters/getters don't work)
  public void addActivity(Activity activity) {
    this.activities.add(activity);
  }
  
  public Collection<Activity> getActivitiesList() {
    return activities;
  }

  /**
  * @uml.property  name="initDate"
  */
  private Calendar initDate = null;

  /**
  * Getter of the property <tt>initDate</tt>.
  * @return  Returns the initDate.
  * @uml.property  name="initDate"
  */
  public Calendar getInitDate() {
    return initDate;
  }

  /**
  * Setter of the property <tt>initDate</tt>.
  *   * @uml.property  name="initDate"
  */
  public void setInitDate(Calendar startDate) {
    if (this.initDate == null) {
      this.initDate = startDate;
    }
    if (this.father != null) {
      this.father.setInitDate(initDate);
    }
  }

  /**
  * @uml.property  name="endDate"
  */
  private Calendar endDate = null;
  
  /**
  * Getter of the property <tt>endDate</tt>.
  * @return  Returns the endDate.
  * @uml.property  name="endDate"
  */
  public Calendar getEndDate() {
    return endDate;
  }
  
  /**
  * Setter of the property <tt>endDate</tt>.
  * @uml.property  name="endDate"
  */
  public void setEndDate(Calendar finishDate) {
    this.endDate = finishDate;
    if (this.father != null) {
      this.father.setEndDate(finishDate);
    }
  }


  /**
  * @uml.property  name="report"
  * @uml.associationEnd  inverse="project:Report"
  */
  private Report report;

  /**
  * Getter of the property <tt>report</tt>.
  * @return  Returns the report.
  * @uml.property  name="report"
  */
  public Report getReport() {
    return report;
  }

  /**
  * Setter of the property <tt>report</tt>.
  * @param report  The report to set.
  * @uml.property  name="report"
  */
  public void setReport(Report report) {
    this.report = report;
  }
}
