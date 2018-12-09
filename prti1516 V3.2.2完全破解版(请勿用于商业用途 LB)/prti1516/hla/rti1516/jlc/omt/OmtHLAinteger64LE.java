package hla.rti1516.jlc.omt;

import hla.rti1516.jlc.ByteWrapper;
import hla.rti1516.jlc.HLAinteger64LE;

public class OmtHLAinteger64LE extends AbstractDataElement implements HLAinteger64LE
{
   private long _value;

   public OmtHLAinteger64LE()
   {
      _value = 0L;
   }

   public OmtHLAinteger64LE(long value)
   {
      _value = value;
   }

   public int getOctetBoundary()
   {
      return 8;
   }

   public void encode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(getOctetBoundary());
      long value = _value;
      byteWrapper.put((int)(value >>> 0) & 0xFF);
      byteWrapper.put((int)(value >>> 8) & 0xFF);
      byteWrapper.put((int)(value >>> 16) & 0xFF);
      byteWrapper.put((int)(value >>> 24) & 0xFF);
      byteWrapper.put((int)(value >>> 32) & 0xFF);
      byteWrapper.put((int)(value >>> 40) & 0xFF);
      byteWrapper.put((int)(value >>> 48) & 0xFF);
      byteWrapper.put((int)(value >>> 56) & 0xFF);
   }

   public int getEncodedLength()
   {
      return 8;
   }

   public void decode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(getOctetBoundary());
      long value = 0L;
      value += (long)byteWrapper.get() << 0;
      value += (long)byteWrapper.get() << 8;
      value += (long)byteWrapper.get() << 16;
      value += (long)byteWrapper.get() << 24;
      value += (long)byteWrapper.get() << 32;
      value += (long)byteWrapper.get() << 40;
      value += (long)byteWrapper.get() << 48;
      value += (long)byteWrapper.get() << 56;
      _value = value;
   }

   public long getValue()
   {
      return _value;
   }

   public boolean equals(Object o)
   {
      if (this == o) {
         return true;
      }
      if (!(o instanceof OmtHLAinteger64LE)) {
         return false;
      }

      final OmtHLAinteger64LE other = (OmtHLAinteger64LE)o;

      if (_value != other._value) {
         return false;
      }

      return true;
   }

   public int hashCode()
   {
      return (int)(_value ^ (_value >>> 32));
   }
}
