package se.pitch.prti1516;

import hla.rti1516.*;

import java.io.*;

import hla.time1516.NormalizedTime;


/**
 * LogicalTimeDouble implements the interface LogicalTime using a 
 * double to represent time.
 */

public class LogicalTimeDouble implements LogicalTime, NormalizedTime, Serializable
{
   private final long _value;
   private static final int MULTIPLIER = 1000000;
   private static final long INITIAL_VALUE = 0;
   private static final long FINAL_VALUE = Long.MAX_VALUE;
   private transient byte[] _serialization;

   static final LogicalTimeDouble INITIAL = new LogicalTimeDouble(INITIAL_VALUE);
   static final LogicalTimeDouble FINAL = new LogicalTimeDouble(FINAL_VALUE);
    static final long serialVersionUID = 646822112889264230L;

    public LogicalTimeDouble(double value)
   {
      this((long)Math.floor(value), (int)((value * MULTIPLIER) % MULTIPLIER));
   }

   public LogicalTimeDouble(long seconds, int micros)
   {
      _value = seconds * MULTIPLIER + micros;
   }

   private LogicalTimeDouble(long value)
   {
      _value = value;
   }

   LogicalTimeDouble(byte[] buffer, int offset)
      throws CouldNotDecode
   {
      try {
         ByteArrayInputStream bais = new ByteArrayInputStream(buffer, offset, 100);
         DataInputStream dis = new DataInputStream(bais);
         _value = dis.readLong();
      } catch (IOException e) {
         throw new CouldNotDecode("Unable to decode LogicalTimeDouble (909001001)");
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
      return (int)(_value % MULTIPLIER);
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
         new LogicalTimeDouble(_value + ((LogicalTimeIntervalDouble)val)._value);
   }
   
   /**
    * Returns a LogicalTime whose value is (this - val).
    */
   public LogicalTime subtract(LogicalTimeInterval val)
   throws IllegalTimeArithmetic
   {
      return
         new LogicalTimeDouble(_value - ((LogicalTimeIntervalDouble)val)._value);
   }
   
   /**
    * Returns a LogicalTimeInterval whose value is the time
    * interval between this and val.
    */
   public LogicalTimeInterval distance(LogicalTime val)
   {
      return LogicalTimeIntervalDouble.construct(
         Math.abs(_value - ((LogicalTimeDouble)val)._value));
   }

   public int compareTo(Object other)
   {
      long otherValue = ((LogicalTimeDouble)other)._value;
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
      if (other instanceof LogicalTimeDouble) {
         return equals((LogicalTimeDouble)other);
      } else {
         return false;
      }
   }
   
   public boolean equals(LogicalTimeDouble other)
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
      if (isFinal()) {
         return "LogicalTimeDouble<INF>";
      } else {
         String decimals = new String("000000" + getMicros());
         decimals = decimals.substring(decimals.length() - 6);
         return "LogicalTimeDouble<" + getSeconds() + "." + decimals + ">";
      }
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
