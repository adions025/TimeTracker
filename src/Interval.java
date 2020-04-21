/**
 *Interval class controls the initiation and ending time of a task.
 *It observes the clock to receive its updates of actual and sends them
 *to its father task every time it starts or ends. It also send the total
 *time the task was activated.
 *
 * @author Adonis Gonzalez
 * @version 28 Nov 2017
 * 
 */

import java.io.Serializable;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;


public class Interval implements Observer, Serializable {

  private static final long serialVersionUID = 1L;
  
  /**
   * Class constructor.
   */
  public Interval(Activity fatherTask, Clock clock, String name) {
    this.fatherTask = fatherTask;
    this.actualDate = clock.getActualDate();
    this.name = name;
    //Checking the interval invariant to avoid creation errors
    intervalInvariant();
  }

  /**
   * Method to start an interval, sending to its father the actual date.
   */
  public void start() {
    setInitDate(getActualDate());
    this.fatherTask.setInitDate(getActualDate());
  }
  
  /**
   * Method to stop an interval, sending to its father the actual date.
   */
  public void stop() {
    setEndDate(getActualDate());
    this.fatherTask.setEndDate(getActualDate());
  }

  /**
   * Update method which responds to every clock notification and updates the 
   * date and total task time.
   */
  public void update(Observable clock, Object arg) {
    this.setActualDate(((Clock) clock).getActualDate());
    this.setTotalTime(((Clock) clock).getUpdateTime());
  }

  /**
   * Function to check the task invariant.
   */
  protected void intervalInvariant() {
    assert ((name != null) && (actualDate != null) && (fatherTask != null)) 
    : "Error" + " creating the interval";
  }

  //Properties of the class and its getters and setters
  /**
  * @uml.property  name="fatherTask"
  */
  private Activity fatherTask;

  /**
  * Getter of the property <tt>fatherTask</tt>.
  * @return  Returns the fatherTask.
  * @uml.property  name="fatherTask"
  */
  public Activity getFatherTask() {
    return fatherTask;
  }

  /**
  * Setter of the property <tt>fatherTask</tt>.
  * @param fatherTask  The fatherTask to set.
  * @uml.property  name="fatherTask"
  */
  public void setFatherTask(Activity fatherTask) {
    this.fatherTask = fatherTask;
  }

  
  /**
  * @uml.property  name="actualDate"
  */
  private Calendar actualDate;

  /**
  * Getter of the property <tt>actualDate</tt>.
  * @return  Returns the actualDate.
  * @uml.property  name="actualDate"
  */
  public Calendar getActualDate() {
    return actualDate;
  }

  /**
  * Setter of the property <tt>actualDate</tt>.
  * @param actualDate  The actualDate to set.
  * @uml.property  name="actualDate"
  */
  public void setActualDate(Calendar actualDate) {
    this.actualDate = actualDate;
  }

  
  /**
  * @uml.property  name="totalTime"
  */
  private long totalTime;

  /**
  * Getter of the property <tt>totalTime</tt>.
  * @return  Returns the totalTime.
  * @uml.property  name="totalTime"
  */
  public long getTotalTime() {
    return totalTime;
  }

  /**
  * Setter of the property <tt>totalTime</tt>.
  * @param totalTime  The totalTime to set.
  * @uml.property  name="totalTime"
  */
  public void setTotalTime(long totalTime) {
    this.totalTime += totalTime;
  }

  /**
  * @uml.property  name="initDate"
  */
  private Calendar initDate;

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
    this.initDate = initDate;
  }

  /**
  * @uml.property  name="endDate"
  */
  private Calendar endDate;

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
  public void setEndDate(Calendar endDate) {
    this.endDate = endDate;
  }

  /**
  * @uml.property  name="name"
  */
  private String name;

  /**
  * Getter of the property <tt>name</tt>.
  * @return  Returns the name.
  * @uml.property  name="name"
  */
  public String getName() {
    return name;
  }

  /**
  * Setter of the property <tt>name</tt>.
  * @param name  The name to set.
  * @uml.property  name="name"
  */
  public void setName(String name) {
    this.name = name;
  }
}
