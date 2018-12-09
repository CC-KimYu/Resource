package hla.rti1516.jlc.omt;

import hla.rti1516.jlc.ByteWrapper;
import hla.rti1516.jlc.HLAinteger64BE;

public class OmtHLAinteger64BE extends AbstractDataElement implements HLAinteger64BE
{
   private long _value;

   public OmtHLAinteger64BE()
   {
      _value = 0L;
   }

   public OmtHLAinteger64BE(long value)
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
      byteWrapper.put((int)(value >>> 56) & 0xFF);
      byteWrapper.put((int)(value >>> 48) & 0xFF);
      byteWrapper.put((int)(value >>> 40) & 0xFF);
      byteWrapper.put((int)(value >>> 32) & 0xFF);
      byteWrapper.put((int)(value >>> 24) & 0xFF);
      byteWrapper.put((int)(value >>> 16) & 0xFF);
      byteWrapper.put((int)(value >>> 8) & 0xFF);
      byteWrapper.put((int)(value >>> 0) & 0xFF);
   }

   public int getEncodedLength()
   {
      return 8;
   }

   public void decode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(getOctetBoundary());
      long value = 0L;
      value += (long)byteWrapper.get() << 56;
      value += (long)byteWrapper.get() << 48;
      value += (long)byteWrapper.get() << 40;
      value += (long)byteWrapper.get() << 32;
      value += (long)byteWrapper.get() << 24;
      value += (long)byteWrapper.get() << 16;
      value += (long)byteWrapper.get() << 8;
      value += (long)byteWrapper.get() << 0;
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
      if (!(o instanceof OmtHLAinteger64BE)) {
         return false;
      }

      final OmtHLAinteger64BE other = (OmtHLAinteger64BE)o;

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
