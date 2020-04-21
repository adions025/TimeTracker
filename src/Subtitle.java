/**This class gets all the information of the most external root tree structure 
 * and returns all its information as an ArrayList of strings to make it easier 
 * for the HTMl/text writter to understand and print. It also provides the 
 * report times dates.
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

public class Subtitle extends Elements {
  
  /**
  * This function is part of the visitor pattern, it's the accept part.
  */
  public Subtitle accept(Format format) {
    return this;
  }
  
  /**
   * This function gets the information of both report times and root projects. 
   * The information it's returned as a collection of strings, so it uses 
   * iterators.
   */
  public Collection<String> getSubtitleIntro(Report report,
      Collection<Activity> roots) {
    /**
     * Creation of the project list and necessary objects to export the 
     * results correctly
     */
    Collection<String> rootList = new ArrayList<String>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss");
    CheckTime checkTime = new CheckTime();
    /**
     * In order to compare the report intervals, the start and end time 
     * are created
     */
    Date initDate = report.getReportInit().getTime();
    Date endDate = report.getReportEnd().getTime();
    Date startDate = report.getReportDate().getTime();
    //Adding report time information to the collection
    String formInit = dateFormat.format(initDate);
    rootList.add(formInit);
    String formEnd = dateFormat.format(endDate);
    rootList.add(formEnd);
    String reportDate = dateFormat.format(startDate);
    rootList.add(reportDate);

    Iterator<Activity> it = roots.iterator();
    //External while at the level of root projects
    while (it.hasNext()) {
      Activity activity = it.next();
      //Adding root project name information
      rootList.add(activity.getName());
      //Adding both start and ending times of root projects
      Date first = checkTime.initCheck(initDate, activity.getInitDate().getTime());
      String initialTime = dateFormat.format(first);
      
      rootList.add(initialTime);
      
      Date last = checkTime.endCheck(endDate, activity.getEndDate().getTime());
      String endTime = dateFormat.format(last);
      
      rootList.add(endTime);
      /*
       * Adding total time of the root project
       */
      rootList.add(checkTime.timeCheck(first, last));
    }
    return rootList;
  }
}
