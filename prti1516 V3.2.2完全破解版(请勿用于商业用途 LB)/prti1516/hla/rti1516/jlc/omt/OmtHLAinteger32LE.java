package hla.rti1516.jlc.omt;

import hla.rti1516.jlc.ByteWrapper;
import hla.rti1516.jlc.HLAinteger32LE;

public class OmtHLAinteger32LE extends AbstractDataElement implements HLAinteger32LE
{
   private int _value;

   public OmtHLAinteger32LE()
   {
      _value = 0;
   }

   public OmtHLAinteger32LE(int value)
   {
      _value = value;
   }

   public int getOctetBoundary()
   {
      return 4;
   }

   public void encode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(getOctetBoundary());
      final int value = _value;
      byteWrapper.put((value >>> 0) & 0xFF);
      byteWrapper.put((value >>> 8) & 0xFF);
      byteWrapper.put((value >>> 16) & 0xFF);
      byteWrapper.put((value >>> 24) & 0xFF);
   }

   public int getEncodedLength()
   {
      return 4;
   }

   public void decode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(getOctetBoundary());
      int value = 0;
      value += (short)byteWrapper.get() << 0;
      value += (short)byteWrapper.get() << 8;
      value += (short)byteWrapper.get() << 16;
      value += (short)byteWrapper.get() << 24;
      _value = value;
   }

   public int getValue()
   {
      return _value;
   }

   public boolean equals(Object o)
   {
      if (this == o) {
         return true;
      }
      if (!(o instanceof OmtHLAinteger32LE)) {
         return false;
      }

      final OmtHLAinteger32LE other = (OmtHLAinteger32LE)o;

      if (_value != other._value) {
         return false;
      }

      return true;
   }

   public int hashCode()
   {
      return _value;
   }
}
