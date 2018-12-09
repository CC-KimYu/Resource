
//File: LogicalTimeInterval.java
package hla.rti1516;

/**
 * LogicalTimeInterval declares an interface to an immutable time interval value
 */

public interface LogicalTimeInterval extends Comparable, java.io.Serializable 
{
   public boolean isZero();
   public boolean isEpsilon();

   /**
    * Returns a LogicalTimeInterval whose value is (this - subtrahend).
    */
   public LogicalTimeInterval subtract(LogicalTimeInterval subtrahend);
   
   public int compareTo(Object other);
   
   /**
    * Returns true iff this and other represent the same time interval.
    */
   public boolean equals(Object other);
   
   /**
    * Two LogicalTimeIntervals for which equals() is true should yield
    * same hash code
    */
   public int hashCode();
   
   public String toString();
   public int encodedLength();
   public void encode(byte[] buffer, int offset);
}
//end LogicalTimeInterval

