/**
 *Clock class it's the only observable. Implements a timer to synchronise all 
 *tasks dates.
 *It contains an inside class named Ticker, commented in some lines ahead.
 *It starts one Ticker instance and, when asked, stops it and closes itself.
 *
 * @author Adonis Gonzalez
 * @version 28 Nov 2017
 * 
 */

//import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Observable;


public class Clock extends Observable implements Runnable {

  private static Clock objClock;
  static Logger logger = LoggerFactory.getLogger("Clock");
  /**
  *The inside Ticker class updates continuously the actual time and notifies 
  *the Clock observers.
  *Only one Ticker object exists at the same time (created by the only 
  *Clock object).
  */
  public class Ticker extends Thread {
    private Clock tickerClock = null;

    /*
     * Inner class constructor.
     */
    public Ticker(Clock tickerClock) {
      this.tickerClock = tickerClock;
      //Checking the ticker invariant to avoid creation errors
      taskInvariant();
    }

    /**
     * Starts the clock runtime and updates every tickerClock interval 
     * (2 seconds in this project).
     */
    public void run() {
      //Necessary to end this thread correctly
      boolean interrupt = false;
        
      while (!interrupt) {
        try {
          this.tickerClock.setActualDate();
          //Checking the interval invariant to avoid updating errors
          taskInvariant();
          setChanged();
          notifyObservers(this.tickerClock);
          Thread.sleep(this.tickerClock.getUpdateTime());
        } catch (InterruptedException e) {
          logger.debug("Clock thread ended");
          interrupt = true; /*End thread*/
        }
      } //end while
    } //end run
    //Function to check the ticker invariant
    protected void taskInvariant() {
      assert (tickerClock != null) : "Error creating clock";
    }
  } //end inside class


  /**
   * Clock class private constructor.
   */
  private Clock(int updateTime) {
    this.setUpdateTime(updateTime);
  }

  /**
   * Clock singleton function.
   */
  public static Clock getInstanceClock(int updateTime) {
    if (objClock == null) {
      objClock = new Clock(updateTime);
    } else {
      logger.warn("Another clock is already running!");
    }
    return objClock;
  }
  
  /**
  * Method to start the timer defined before at the Ticker class.
  */
  public void run() {
    this.ticker = new Ticker(this);
    this.ticker.start();
  }
  
  /**
  * Method to stop the timer defined before at the Ticker class.
  */
  public void stop() {
    this.ticker.interrupt();
  }
  
  
  //Properties of the class and its getters and setters
  /**
  * @uml.property  name="updateTime"
  */
  private int updateTime;

  /**
  * Getter of the property <tt>updateTime</tt>.
  * @return  Returns the updateTime.
  * @uml.property  name="updateTime"
  */
  public int getUpdateTime() {
    return updateTime;
  }

  /**
  * Setter of the property <tt>updateTime</tt>.
  * @param updateTime  The updateTime to set.
  * @uml.property  name="updateTime"
  */
  public void setUpdateTime(int updateTime) {
    this.updateTime = updateTime * 1000;  //to set it in milliseconds
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
  public void setActualDate() {
    this.actualDate = Calendar.getInstance();
  }
  
  
  
  /**
  * @uml.property  name="ticker"
  */
  private Ticker ticker;

  /**
  * Getter of the property <tt>ticker</tt>.
  * @return  Returns the ticker.
  * @uml.property  name="ticker"
  */
  public Ticker getTicker() {
    return ticker;
  }

  /**
  * Setter of the property <tt>ticker</tt>.
  * @param ticker  The ticker to set.
  * @uml.property  name="ticker"
  */
  public void setTicker(Ticker ticker) {
    this.ticker = ticker;
  }
}
