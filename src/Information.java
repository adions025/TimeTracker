/**This class gets all the information of the the root tree structure and 
 * returns all its information (per level) as an ArrayList of strings to make 
 * it easier for the HTMl/text writter to understand and print.
 *
 * @author Adonis Gonzalez
 * @version 28 Nov 2017
 * 
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class Information {

  /**
   * Class constructor. 
   */
  public Information(Report report, Collection<Activity> roots) {
    this.report = report;
    this.roots = roots;
  }
  
  /**
   * This function gets the information of all the subproject that exist. The
   * information it's returned as a collection of strings, so it uses iterators.
   */
  public Collection<String> getProjectInfo() {
    //Creation of the project list and necessary objects to export the results 
	//correctly
    Collection<String> projectList = new ArrayList<String>();
    CheckTime checkTime = new CheckTime();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
    //In order to compare the report intervals, the start and end time are 
    //created
    Date initDate = report.getReportInit().getTime();
    Date endDate = report.getReportEnd().getTime();

    Iterator<Activity> rootIt = roots.iterator();
    //External while at the level of root projects
    while (rootIt.hasNext()) {
      Activity root = rootIt.next();

      Iterator<Activity> rootAct = root.getActivitiesList().iterator();
      //Internal while at the level of subprojects
      while (rootAct.hasNext()) {
        Activity activity = rootAct.next();
        //Comprovation that the activity isn't a task
        if (activity.getClass() == Project.class) {
          //Confirming that the subproject is inside the report's interval
          Date first = checkTime.initCheck(initDate, activity.getInitDate().getTime());
          Date last = checkTime.endCheck(endDate, activity.getEndDate().getTime());
          String time = checkTime.timeCheck(first, last);
          //Adding the project's name information
          projectList.add(activity.getFather().getName());
          projectList.add(activity.getName());
          //Adding both start and end time as Strings with the correct 
          //expected format
          String initialTime = dateFormat.format(first);
          projectList.add(initialTime);
          String endTime = dateFormat.format(last);
          projectList.add(endTime);
          //Adding the project total time with the correct format
          projectList.add(time);
        }
      }
    }
    return projectList;
  }

  /**
  * This function gets the information of all the tasks that exist. The
  * information it's returned as a collection of strings, so it uses iterators.
  * The commentary it's simplified respect the previous function because 
  * it shares a lot of the algorithm structure.
  */
  public Collection<String> getTaskInfo() {
    
    Collection<String> taskList = new ArrayList<String>();
    CheckTime checkTime = new CheckTime();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");

    Date initDate = report.getReportInit().getTime();
    Date endDate = report.getReportEnd().getTime();

    Iterator<Activity> rootIt = roots.iterator();
    //External while at the level of root projects
    while (rootIt.hasNext()) {
      Activity root = rootIt.next();

      Iterator<Activity> rootAct = root.getActivitiesList().iterator();
      //Internal level at the level of activities
      while (rootAct.hasNext()) {
        Activity activity = rootAct.next();
        //Comprovation that the activity IS a task
        if (activity.getClass() == Task.class) {
          //Avoiding a bug report (explained at the header of report class)
          Iterator<Interval> intAct = activity.getIntervals().iterator();
          Collection<Interval> intervals = activity.getIntervals();
          Date endingDate = null;
          if (intervals.size() > 1) {
            endingDate = intAct.next().getEndDate().getTime();
          } else {
            endingDate = activity.getEndDate().getTime();
          }
          //Creating the time to be passed as strings
          Date first = checkTime.initCheck(initDate, activity.getInitDate().getTime());
          Date last = checkTime.endCheck(endDate, endingDate);
          String time = checkTime.timeCheck(first, last);
          //Checking that the time of the task isn't 0 during the reports 
          //interval
          if (!time.equals("00:00:00")) {
            taskList.add(activity.getFather().getName());
            taskList.add(activity.getName());
  
            String initialTime = dateFormat.format(first);
            taskList.add(initialTime);
  
            String endTime = dateFormat.format(last);
            taskList.add(endTime);
  
            taskList.add(time);
          }
        } else { /*In case that it's a subproject, it can contain internal tasks*/
          Iterator<Activity> projectAct = activity.getActivitiesList().iterator();
          Activity task = projectAct.next();
          taskList.add(task.getFather().getName());
          taskList.add(task.getName());

          Date first = checkTime.initCheck(initDate, task.getInitDate().getTime());
          String initialTime = dateFormat.format(first);
          taskList.add(initialTime);

          Date last = checkTime.endCheck(endDate, task.getEndDate().getTime());
          String endTime = dateFormat.format(last);
          taskList.add(endTime);

          taskList.add(checkTime.timeCheck(first, last));
        }
      }
    }
    return taskList;
  }

  /**
  * This function gets the information of all the intervals that exist. The
  * information it's returned as a collection of strings, so it uses iterators.
  * The commentary it's simplified respect the previous functions because it shares 
  * a lot of the algorithm structure.
  */
  public Collection<String> getIntervalInfo() {
    Collection<String> intervalList = new ArrayList<String>();
    CheckTime checkTime = new CheckTime();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");

    Date initDate = report.getReportInit().getTime();
    Date endDate = report.getReportEnd().getTime();

    Iterator<Activity> rootIt = roots.iterator();
    //External while at the level of root projects
    while (rootIt.hasNext()) {
      Activity root = rootIt.next();

      Iterator<Activity> rootAct = root.getActivitiesList().iterator();
      //Internal level at the level of activities
      while (rootAct.hasNext()) {
        Activity activity = rootAct.next();
        //Comprovation that the activity IS a task
        if (activity.getClass() == Task.class) {
          Iterator<Interval> intAct = activity.getIntervals().iterator();
          //Internal level of tasks that gets the intervals results
          Interval interval = intAct.next();
          //Creating the time to be passed as strings
          Date first = checkTime.initCheck(initDate, interval.getInitDate().getTime());
          Date last = checkTime.endCheck(endDate, interval.getEndDate().getTime());
          String time = checkTime.timeCheck(first, last);
          //Checking that the time of the task isn't 0 during the reports interval
          if (!time.equals("00:00:00")) {
            intervalList.add(interval.getFatherTask().getName());
            intervalList.add(interval.getName());
  
            String initialTime = dateFormat.format(first);
            intervalList.add(initialTime);
  
            String endTime = dateFormat.format(last);
            intervalList.add(endTime);
  
            intervalList.add(time);
          }
        } else { /*In case that it's a subproject, it can contain internal tasks*/
          Iterator<Activity> projectAct = activity.getActivitiesList()
              .iterator();
          Activity prova = projectAct.next();
          Iterator<Interval> intAct = prova.getIntervals().iterator();
          //Same as before, assuring that all the subproject's tasks intervals 
          //are correct
          while (intAct.hasNext()) {
            Interval interval = intAct.next();
            Date first = checkTime.initCheck(initDate, interval.getInitDate()
                .getTime());
            Date last = checkTime.endCheck(endDate, interval.getEndDate()
                .getTime());
            String time = checkTime.timeCheck(first, last);

            intervalList.add(interval.getFatherTask().getName());
            intervalList.add(interval.getName());

            String initialTime = dateFormat.format(first);
            intervalList.add(initialTime);

            String endTime = dateFormat.format(last);
            intervalList.add(endTime);

            intervalList.add(time);
          } //end while
        } //end else
      } //end activity while
    } //end root while
    return intervalList;
  }

  //Properties of this abstract class and its getters and setters used by 
  //projects/tasks
  /**
   * @uml.property name="roots"
   */
  private Collection<Activity> roots;

  /**
   * Getter of the property <tt>roots</tt>
   * 
   * @return Returns the roots.
   * @uml.property name="roots"
   */
  public Collection<Activity> getRoots() {
    return roots;
  }

  /**
   * Setter of the property <tt>roots</tt>
   * 
   * @param roots
   *          The roots to set.
   * @uml.property name="roots"
   */
  public void setRoots(Collection<Activity> roots) {
    this.roots = roots;
  }

  /**
   * @uml.property name="report"
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

}
