package se.pitch.prti1516;

import hla.rti1516.CouldNotDecode;
import hla.rti1516.LogicalTimeInterval;
import hla.time1516.NormalizedInterval;

import java.io.*;


/**
 * LogicalTimeIntervalDouble implements LogicalTimeInterval 
 * using a double to represent intervals.
 */

public class LogicalTimeIntervalDouble implements LogicalTimeInterval, NormalizedInterval, Serializable
{
   final long _value;
   private static final long EPSILON_VALUE = 1;
   private transient byte[] _serialization;
   
   static final LogicalTimeIntervalDouble ZERO =
      new LogicalTimeIntervalDouble(0);
   static final LogicalTimeIntervalDouble EPSILON =
      new LogicalTimeIntervalDouble(0, 1);
   private static final int MULTIPLIER = 1000000;
   static final long serialVersionUID = 4671212165269227566L;

   public LogicalTimeIntervalDouble(double value)
   {
      this((long)Math.floor(value), (int)((value * MULTIPLIER) % MULTIPLIER));
   }
   
   public LogicalTimeIntervalDouble(long seconds, int micros)
   {
      _value = seconds * MULTIPLIER + micros;
   }

   private LogicalTimeIntervalDouble(long value)
   {
      _value = value;
   }

   static LogicalTimeIntervalDouble construct(long value)
   {
      return new LogicalTimeIntervalDouble(value);
   }

   LogicalTimeIntervalDouble(byte[] buffer, int offset)
      throws CouldNotDecode
   {
      try {
         ByteArrayInputStream bais = new ByteArrayInputStream(buffer, offset, 100);
         DataInputStream dis = new DataInputStream(bais);
         _value = dis.readLong();
      } catch (IOException e) {
         throw new CouldNotDecode("Unable to decode LogicalTimeIntervalDouble (909001001)");
      }
   }

   public double getValue()
   {
      return _value / (double)MULTIPLIER;
   }

   public long getSeconds()
   {
      return _value / MULTIPLIER;
   }

   public int getMicros()
   {
      return ((int)(_value % MULTIPLIER));
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
      return new LogicalTimeIntervalDouble(
        _value - ((LogicalTimeIntervalDouble)subtrahend)._value);
   }
   
   public int compareTo(Object other)
   {
      long otherValue = ((LogicalTimeIntervalDouble)other)._value;
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
      if (other instanceof LogicalTimeIntervalDouble) {
         return equals((LogicalTimeIntervalDouble)other);
      } else {
         return false;
      }
   }
   
   public boolean equals(LogicalTimeIntervalDouble other)
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
      String decimals = new String("000000" + getMicros());
      decimals = decimals.substring(decimals.length() - 6);
      return "LogicalTimeIntervalDouble<" + getSeconds() + "." + decimals + ">";
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
