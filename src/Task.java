/**
 *Task class initiates and ends intervals and store them.
 *It allows to its project father to update its start and end time. Also, it's
 *the responsible of adding and deleting observers to the clock. It also knows 
 *the total time that the task was active.
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


public class Task extends Activity implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Class constructor.
   */
  public Task(String name, String description, Activity father) {
    this.name = name;
    this.description = description;
    this.father = father;
    this.father.addActivity(this); //Implementing the tree structure
    //Checking the task invariant to avoid creation errors
    taskInvariant();
  }
  
  /**
   * Methods to initiate the clock. Notice how observers are created.
   */
  public void start(Clock clock) {
    String intervalName = "Interval from task " + this.name;
    Interval interval = new Interval(this, clock, intervalName);
    clock.addObserver(interval);
  
    interval.start();
    getIntervals().add(interval);
  }
  /*
   * Methods to stop the clock. Notice how observers are deleted.
   */
  public void stop(Clock clock) {
    final Iterator<Interval> it = intervals.iterator(); 
    /*
     * To get the last interval calculated.
     */
    Interval lastElement = it.next();
  
    while (it.hasNext()) {
      lastElement = it.next();
    }
    lastElement.stop();
    clock.deleteObserver(lastElement);
  }
  
  /**
   * Iteration through the intervals to get the total time of a task to 
   * print as a result.
   */
  public long calculateTime() {
    long totalTime = 0;
    Iterator<Interval> it = getIntervals().iterator();
    while (it.hasNext()) {
      Interval interval = it.next();
      totalTime += interval.getTotalTime() / 1000; /*Time in seconds*/
    }
    return totalTime;
  }

  /**
  * Function to check the task invariant.
  */
  protected void taskInvariant() {
    assert ((intervals != null) && (name != null) && (description != null) && (
    		father != null)) : "Error creating task";
  }
  
  /*
   * Properties of the class and its getters and setters.
   */
  /** 
  * @uml.property name="intervals"
  * @uml.associationEnd multiplicity="(0 -1)" aggregation="composite" 
  * inverse="task:Interval"
  */
  private Collection<Interval> intervals = new ArrayList<Interval>();

  /**
  * Getter of the property <tt>intervals</tt>.
  * @return  Returns the intervals.
  * @uml.property  name="intervals"
  */
  public Collection<Interval> getIntervals() {
    return intervals;
  }

  /**
  * Setter of the property <tt>intervals</tt>.
  * @param intervals  The intervals to set.
  * @uml.property  name="intervals"
  */
  public void setIntervals(Collection<Interval> intervals) {
    this.intervals = intervals;
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
  * @param initDate  The initDate to set.
  * @uml.property  name="initDate"
  */
  public void setInitDate(Calendar initDate) {
    if (this.initDate == null) {
      this.initDate = initDate;
    }
    this.father.setInitDate(initDate);  /*Updating father initDate*/
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
  * @param endDate  The endDate to set.
  * @uml.property  name="endDate"
  */
  public void setEndDate(Calendar finishDate) {
    this.endDate = finishDate;
    this.father.setEndDate(finishDate);  //Updating father endDate
  }
}
