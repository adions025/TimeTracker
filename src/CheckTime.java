/**
 *This class is designated to assure that all the reports times are between the 
 *start
 *time and end time. It also returns the correct time format for the reports.
 *
 * @author Adonis Gonzalez
 * @version 28 Nov 2017
 * 
 */

import java.util.Date;

public class CheckTime {

  /**
  * This function checks and returns if a date it's between the report dates 
  * (initial one).
  * It returns the earliest one that it's in the interval.
  */
  public Date initCheck(Date initDate, Date activityDate) {
    //Preconditions
    assert ((initDate instanceof Date) && (initDate != null)) : "Error " 
    		+ "checking init report date";
    assert ((activityDate instanceof Date) && (activityDate != null)) : "Error" 
    		+ " checking init activty date";
    
    if ((initDate.compareTo(activityDate)) > 0) {
      return initDate;
    } else {
      return activityDate;
    }
  }

  /**
  * This function checks and returns if a date it's between the report dates 
  * (end one).
  * It returns the latest one that it's in the interval.
  */
  public Date endCheck(Date endDate, Date activityDate) {
    //Preconditions
    assert ((endDate instanceof Date) && (endDate != null)) : "Error " 
    		+ "checking end report date";
    assert ((activityDate instanceof Date) && (activityDate != null)) : "Error " 
    		+ "checking ending activty date";
    
    if ((endDate.compareTo(activityDate)) < 0) {
      return endDate;
    } else {
      return activityDate;
    }

  }

  /**
  * This function calculates the time between two dates and returns it in the 
  * wanted format.
  */
  public String timeCheck(Date initialTime, Date endTime) {
    //Preconditions
    assert ((initialTime instanceof Date) && (endTime instanceof Date) 
    		&& (initialTime != null) && (endTime != null)) : "Error checking " 
    		+ "dates to compare and get the time";
    
    long diff = (endTime.getTime() - initialTime.getTime());
    long hours = diff / (60 * 60 * 1000) % 24;
    long minutes = diff / (60 * 1000) % 60;
    long seconds = diff / 1000 % 60;
    /**
     * Calling the timeTranslator function to prevent errors at 
     * the representation
     */
    String translatedTime = timeTranslator(hours) + ":" 
     + timeTranslator(minutes) + ":" + timeTranslator(seconds);
    //Postconditions
    assert (translatedTime.getClass().equals(String.class)) : "Time doesn't " 
    		+ "return a string";
    return translatedTime;
  }

 /*
  * This function transforms a number to the expected hour format.
  */
  public String timeTranslator(long time) {
    //Preconditions
    assert (time >= 0) : "Error translating the time (negative time)";
    //Case exact hour
    if (time == 0) {
      return "00";
    }
    //Case hour between 01 and 09
    if ((time / 10) == 0) {
      return "0" + time;
    } else {
      return String.valueOf(time);
    } //end else
  } //end timeTranslator
} //end Class
