package hla.time1516;

import hla.rti1516.IllegalTimeArithmetic;
import hla.rti1516.LogicalTime;
import hla.rti1516.LogicalTimeInterval;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


/**
 * LogicalTimeLong implements the interface LogicalTime using a
 * long to represent time.
 */

public class LogicalTimeLong implements LogicalTime, NormalizedTime
{
   private final long _value;
   private static final long INITIAL_VALUE = 0;
   private static final long FINAL_VALUE = Long.MAX_VALUE;
   private transient byte[] _serialization;

   static final LogicalTimeLong INITIAL = new LogicalTimeLong(INITIAL_VALUE);
   static final LogicalTimeLong FINAL = new LogicalTimeLong(FINAL_VALUE);

   public LogicalTimeLong(long value)
   {
      _value = value;
   }

   public long getValue()
   {
      return _value;
   }

   public boolean isInitial()
   {
      return _value == INITIAL_VALUE;
   }
      
   public boolean isFinal()
   {
      return _value == FINAL_VALUE;
   }

   /**
    * Returns a LogicalTime whose value is (this + val).
    */
   public LogicalTime add(LogicalTimeInterval val)
   throws IllegalTimeArithmetic
   {
      return
        new LogicalTimeLong(_value + ((LogicalTimeIntervalLong)val)._value);
   }

   /**
    * Returns a LogicalTime whose value is (this - val).
    */
   public LogicalTime subtract(LogicalTimeInterval val)
   throws IllegalTimeArithmetic
   {
      return
        new LogicalTimeLong(_value - ((LogicalTimeIntervalLong)val)._value);
   }
   
   /**
    * Returns a LogicalTimeInterval whose value is the time
    * interval between this and val.
    */
   public LogicalTimeInterval distance(LogicalTime val)
   {
      return new LogicalTimeIntervalLong(
        Math.abs(_value - ((LogicalTimeLong)val)._value));
   }

   public int compareTo(Object other)
   {
      if (other instanceof LogicalTimeLong) {
         LogicalTimeLong logicalTimeLong = (LogicalTimeLong)other;
         long otherValue = logicalTimeLong._value;
         if (_value == otherValue) {
            return 0;
         } else if (_value < otherValue) {
            return -1;
         } else {
            return 1;
         }
      } else {
         throw new ClassCastException("Cannot compare with " + other);
      }
   }
   
   /**
    * Returns true iff this and other represent the same time interval.
    */
   public boolean equals(Object other)
   {
      if (other instanceof LogicalTimeLong) {
         return equals((LogicalTimeLong)other);
      } else {
         return false;
      }
   }
   
   public boolean equals(LogicalTimeLong other)
   {
      return _value == other._value;
   }
   
   /**
    * Two LogicalTimes for which equals() is true should yield
    * same hash code.
    */
   public int hashCode()
   {
      return (new Long(_value)).hashCode();
   }
   
   public String toString()
   {
      return "LogicalTimeLong<" + _value + ">";
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

   public double getNormalizedTime()
   {
      return getValue();
   }
}
