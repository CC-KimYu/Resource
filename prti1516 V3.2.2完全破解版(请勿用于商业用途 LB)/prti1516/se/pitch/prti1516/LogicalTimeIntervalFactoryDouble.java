package se.pitch.prti1516;

import hla.rti1516.LogicalTimeInterval;
import hla.rti1516.LogicalTimeIntervalFactory;
import hla.rti1516.CouldNotDecode;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.Serializable;

public class LogicalTimeIntervalFactoryDouble implements LogicalTimeIntervalFactory
{
   private static final long serialVersionUID = -6708765780464991803L;
   
   public LogicalTimeInterval decode(byte[] buffer, int offset)
      throws CouldNotDecode
   {
      return new LogicalTimeIntervalDouble(buffer, offset);
   }

   public LogicalTimeInterval makeZero()
   {
      return LogicalTimeIntervalDouble.ZERO;
   }

   public LogicalTimeInterval makeEpsilon()
   {
      return LogicalTimeIntervalDouble.EPSILON;
   }
}
