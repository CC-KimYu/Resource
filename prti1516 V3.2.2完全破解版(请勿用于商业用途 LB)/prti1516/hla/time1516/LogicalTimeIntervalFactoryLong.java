package hla.time1516;

import hla.rti1516.LogicalTimeInterval;
import hla.rti1516.LogicalTimeIntervalFactory;
import hla.rti1516.CouldNotDecode;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.Serializable;

public class LogicalTimeIntervalFactoryLong implements LogicalTimeIntervalFactory
{
   private static final int DUMMY_LENGTH = 100;
   
   public LogicalTimeInterval decode(byte[] buffer, int offset)
      throws CouldNotDecode
   {
      try {
         ByteArrayInputStream bais = new ByteArrayInputStream(buffer, offset, DUMMY_LENGTH);
         DataInputStream dis = new DataInputStream(bais);
         long d = dis.readLong();
         return new LogicalTimeIntervalLong(d);
      } catch (IOException e) {
         throw new CouldNotDecode("Unable to decode LogicalTimeInterval (909001001)");
      }
   }

   public LogicalTimeInterval makeZero()
   {
      return LogicalTimeIntervalLong.ZERO;
   }

   public LogicalTimeInterval makeEpsilon()
   {
      return LogicalTimeIntervalLong.EPSILON;
   }
}
