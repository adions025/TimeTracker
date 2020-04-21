/**Activity class is an abstract class that implements common methods in Project
 * or Task and declares the methods of those tasks. It also declares the common
 * properties and implements its getters/setters.
 *
 * @author Adonis Gonzalez
 * @version 28 Nov 2017
 * 
 */

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;


public abstract class Activity implements Serializable {

  private static final long serialVersionUID = 1L;
   
  /*
   * This method adapts the initial date of an activity to the desired output.
   */
  public String converterInit(Activity activity) {
    if (activity.getInitDate() == null) {
      return null; /*Used to avoid nullPointer errors*/
    } else {
      SimpleDateFormat dateFormat = new SimpleDateFormat("d-M-yyyy HH:mm:ss ");
      String formatedDate = dateFormat.format(activity.getInitDate().getTime());
      return formatedDate;
    }
  }

  /*
   * This method adapts the end date of an activity to the desired output.
   */
  public String converterEnd(Activity activity) {
    if (activity.getEndDate() == null) {
      return null; /*Used to avoid nullPointer errors*/
    } else {
      SimpleDateFormat dateFormat = new SimpleDateFormat("d-M-yyyy HH:mm:ss ");
      String formatedDate = dateFormat.format(activity.getEndDate().getTime());
      return formatedDate;
    }
  }

  /**
   * This method adapts the total time of execution of an activity to the 
   * desired output.
   */
  public String timeConverter(Activity activity) {
    long milliseconds = activity.calculateTime() * 1000; //time in milliseconds
    Date convertDate = new Date(milliseconds);
    SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
    String formatedDate = dateFormat.format(convertDate);
    return formatedDate;
  }

  /**
   * Properties of this abstract class and its getters and setters used by 
   * projects/tasks.
   */
  
  /**
  * @uml.property  name="name"
  */
  protected String name;

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
  

  /**
  * @uml.property  name="description"
  */
  protected String description;

  /**
  * Getter of the property <tt>description</tt>.
  * @return  Returns the description.
  * @uml.property  name="description"
  */
  public String getDescription() {
    return description;
  }

  /**
  * Setter of the property <tt>description</tt>.
  * @param description  The description to set.
  * @uml.property  name="description"
  */
  public void setDescription(String description) {
    this.description = description;
  }

  
  /**
  * @uml.property  name="father"
  */
  protected Activity father;

 /**
  * Getter of the property <tt>father</tt>.
  * @return  Returns the father.
  * @uml.property  name="father"
  */
  public Activity getFather() {
    return father;
  }

  /**
  * Setter of the property <tt>father</tt>.
  * @param father  The father to set.
  * @uml.property  name="father"
  */
  public void setFather(Activity father) {
    this.father = father;
  }

  /**
   * Declarations of methods of this abstract class that are implemented and 
   * used by projects/tasks.
   */
  public long calculateTime() {
    return (Long) null;
  }

  public Collection<Activity> getActivitiesList() {
    return null;
  }
  
  public void addActivity(Activity activity) {
  }

  public void start(Clock clock) {
  }

  public void stop(Clock clock) {
  }

  public void setInitDate(Calendar initInterval) {
  }

  public void setEndDate(Calendar initInterval) {
  }

  public Calendar getInitDate() {
    return null;
  }

  public Calendar getEndDate() {
    return null;
  }

  public void printerElements(Activity element) {
  }

  public Collection<Interval> getIntervals() {
    return null;
  }
}
