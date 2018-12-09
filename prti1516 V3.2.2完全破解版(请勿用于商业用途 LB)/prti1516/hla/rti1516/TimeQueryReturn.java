//File: TimeQueryReturn.java

/**
 * Record returned by (8.16) queryLBTS and (8.18) queryMinimumNextEventTime
 */

package hla.rti1516;

public final class TimeQueryReturn
    implements java.io.Serializable
{
  public TimeQueryReturn (boolean tiv, LogicalTime lt) {
    timeIsValid = tiv;
    time = lt;
  }

  public boolean     timeIsValid;
  public LogicalTime time;
  
  public boolean equals(Object other)
  {
     if (other instanceof TimeQueryReturn) {
        TimeQueryReturn tqrOther = (TimeQueryReturn)other;
        if (timeIsValid == false && tqrOther.timeIsValid == false) {
           return true;
        } else if (timeIsValid == true && tqrOther.timeIsValid == true) {
           return time.equals(tqrOther.time);
        } else {
           return false;
        }
     } else {
        return false;
     }
  }

   public int hashCode()
   {
      return (timeIsValid ? time.hashCode() : 7);
   }

   public String toString()
  {
     return "" + timeIsValid + " " + time;
  }
}
//end TimeQueryReturn
