/**
 *Printer class shows the live Time Tracker results through the console.
 *It prints continuously until it's interrupted. It calls for every 
 *activity inside the root project the function printerElements from the 
 *Project class (in order to get inside the interior projects of the root 
 *recursively, avoiding the tasks to prevent nullPointer errors). 

 *Also, it implements the logger to show the console results with more detail.
 *
 * @author Adonis Gonzalez
 * @version 28 Nov 2017
 * 
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.Thread;
import java.util.Iterator;


public class Printer extends Thread {
  
  private int ticker;
  private Activity rootProject;
  static Logger logger = LoggerFactory.getLogger("Printer");

  /**
   * Class constructor.
   */
  public Printer(Activity rootProject, int ticker) {
    this.rootProject = rootProject;
    this.ticker = ticker * 1000; 
  }

  /**
   * Function that prints the time results.
   */
  public void run() {
    //Necessary to end this thread correctly
    boolean interrupt = false;
    //Exterior while will print results header
    while (!interrupt) {
      logger.info("Nom   Temps inici                      Temps final        " 
      + "        +" + "      Durada (mm:ss)");
      logger.info("-----|--------------------------------|--------------------"
      + "------------|-----------------");
      Iterator<Activity> it = rootProject.getActivitiesList().iterator();
      //Interior while prints the results 
      while (it.hasNext()) {
        Activity activity = it.next();
        logger.info(activity.getName() + "    " + activity.converterInit(
        		activity) + "              " + activity.converterEnd(activity) 
        		+ "              " + activity.timeConverter(activity));
        activity.printerElements(activity); 
        /*Need to recursively use this Project method*/
        logger.info(" ");
      }
      
      try {
        Thread.sleep(this.ticker);
      } catch (InterruptedException e) {
        logger.debug("Printer thread ended");
        interrupt = true; /*End thread*/
      } //end catch
    } //end exterior while
  } //end run()
} //end class
