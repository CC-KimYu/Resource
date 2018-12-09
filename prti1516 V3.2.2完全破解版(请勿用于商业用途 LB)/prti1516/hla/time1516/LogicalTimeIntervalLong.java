package hla.time1516;

import hla.rti1516.LogicalTimeInterval;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


/**
 * LogicalTimeIntervalLong implements LogicalTimeInterval
 * using a long to represent intervals.
 */

public class LogicalTimeIntervalLong implements LogicalTimeInterval, NormalizedInterval
{
   final long _value;
   private static final long EPSILON_VALUE = 1;
   private transient byte[] _serialization;
   
   static final LogicalTimeIntervalLong ZERO =
      new LogicalTimeIntervalLong(0);
   static final LogicalTimeIntervalLong EPSILON =
      new LogicalTimeIntervalLong(EPSILON_VALUE);

   public LogicalTimeIntervalLong(long value)
   {
      _value = value;
   }
   
   public long getValue()
   {
      return _value;
   }

   public boolean isZero()
   {
      return _value == 0;
   }
      
   public boolean isEpsilon()
   {
      return _value == EPSILON_VALUE;
   }

   /**
    * Returns a LogicalTimeInterval whose value is (this - subtrahend).
    */
   public LogicalTimeInterval subtract(LogicalTimeInterval subtrahend)
   {
      return new LogicalTimeIntervalLong(
        _value - ((LogicalTimeIntervalLong)subtrahend)._value);
   }
   
   public int compareTo(Object other)
   {
      long otherValue = ((LogicalTimeIntervalLong)other)._value;
      if (_value == otherValue) {
         return 0;
      } else if (_value < otherValue) {
         return -1;
      } else {
         return 1;
      }
   }
   
   /**
    * Returns true iff this and other represent the same time interval.
    */
   public boolean equals(Object other)
   {
      if (other instanceof LogicalTimeIntervalLong) {
         return equals((LogicalTimeIntervalLong)other);
      } else {
         return false;
      }
   }
   
   public boolean equals(LogicalTimeIntervalLong other)
   {
      return _value == other._value;
   }
   
   /**
    * Two LogicalTimes for which equals() is true should yield
    * same hash code
    */
   public int hashCode()
   {
      return (new Long(_value)).hashCode();
   }
   
   public String toString()
   {
      return "LogicalTimeIntervalLong<" + _value + ">";
   }
   
   public int encodedLength()
   {
      updateSerialization();
      return _serialization.length;
   }
   
   public void encode(byte[] buffer, int offset)
   {
      updateSerialization();
      System.arraycopy(
         _serialization, 
         0, 
         buffer, 
         offset,
         _serialization.length);
   }

   private void updateSerialization ( ) {
      if (_serialization != null) {
         return;
      }
      try {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         DataOutputStream dos = new DataOutputStream(baos);
         dos.writeLong(_value);
         _serialization = baos.toByteArray();
      } catch (IOException e) {
         e.printStackTrace();
      }		
   }

   public double getNormalizedInterval()
   {
      return getValue();
   }
}
