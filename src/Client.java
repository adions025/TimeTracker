/**
 *Client class shows the main functionality of the Time Tracker project.
 *It creates a clock that will be unique for all the program.
 *All the projects and activities are created and then simulated as if the
 *user inputed them to pass the test. Finally it creates the necessary reports.
 *
 * @author Adonis Gonzalez
 * @version 28 Nov 2017
 * 
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;


public class Client {

  private static Logger logger = LoggerFactory.getLogger("Client");

  /*
   * Main function.
   */
  public static void main(String[] args)  throws InterruptedException {

    logger.info("Starting milestone 2 test");
    //Setting the update time for both clock and the printer
    int updateTime = 2;
    logger.debug("Update time: " + updateTime + " seconds");

    //Creation of all the uniques objects to control time and serialization
    Clock clock = Clock.getInstanceClock(updateTime);
    clock.run();

    //Creation of all the objects to be used
    logger.info(" ");
    Activity p1 = new Project("R1", "Root description 1", null);
    final Activity t1 = new Task("T1", "Task 1 description", p1);
    final Activity t2 = new Task("T2", "Task 2 description", p1);
    Activity p12 = new Project("P1", "P1_2 description", p1);
    final Activity t4 = new Task("T4", "Task 4 description", p12);
    
    Activity p2 = new Project("R2", "Root description 2", null);
    final Activity t3 = new Task("T3", "Task 3 description", p2);

    //Adding all the root projects into an ArrayList to create the reports
    Collection<Activity> roots = new ArrayList<Activity>();
    roots.add(p1);
    roots.add(p2);
  
    //Start printing results for both root projects
    Printer printer = new Printer(p1, updateTime);
    Printer printer2 = new Printer(p2, updateTime);
    printer.start();
    printer2.start();
    
    //Simulation of times introduced by the final user
    t1.start(clock);
    t4.start(clock);
    Thread.sleep(4000);
    t1.stop(clock);
    t2.start(clock);
    //Getting simulated user project's start
    final Calendar startReport = Calendar.getInstance();
    Thread.sleep(6000);
    t2.stop(clock);
    t4.stop(clock);
    t3.start(clock);
    Thread.sleep(4000);
    t2.start(clock);
    t3.stop(clock);
    //Getting simulated user project's ending
    final Calendar endReport = Calendar.getInstance();
    Thread.sleep(2000);
    t3.start(clock);
    Thread.sleep(4000);
    t2.stop(clock);
    t3.stop(clock);
    //Stopping the projects roots printed results
    Thread.sleep(2000);
    printer.interrupt();
    printer2.interrupt();
    //Stopping the clock
    clock.stop();
    
    //Creating both short reports (HTML & text formats)
    Report reportShortHtml = new Shortened("HTML", startReport, endReport);
    reportShortHtml.createShortReport(roots);
    Report reportShortText = new Shortened("Text", startReport, endReport);
    reportShortText.createShortReport(roots);
    
    //Creating both detailed reports (HTML & text formats)
    Report reportLongHtml = new Detailed("HTML", startReport, endReport);
    reportLongHtml.createLongReport(roots);
    Report reportLongText = new Detailed("Text", startReport, endReport);
    reportLongText.createLongReport(roots);
  }
}