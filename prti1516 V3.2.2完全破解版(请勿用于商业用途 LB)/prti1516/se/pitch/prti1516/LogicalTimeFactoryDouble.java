package se.pitch.prti1516;

import hla.rti1516.LogicalTimeFactory;
import hla.rti1516.LogicalTime;
import hla.rti1516.CouldNotDecode;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.Serializable;

public class LogicalTimeFactoryDouble implements LogicalTimeFactory, Serializable
{
   private static final long serialVersionUID = 6253922219682721868L;

   public LogicalTime makeInitial()
   {
      return LogicalTimeDouble.INITIAL;
   }

   public LogicalTime makeFinal()
   {
      return LogicalTimeDouble.FINAL;
   }

   public LogicalTime decode(byte[] buffer, int offset)
      throws CouldNotDecode
   {
      return new LogicalTimeDouble(buffer, offset);
   }
}
