package hla.rti1516.jlc.omt;

import hla.rti1516.jlc.*;

public class OmtHLAfloat64BE extends AbstractDataElement implements HLAfloat64BE
{
   private double _value;

   public OmtHLAfloat64BE()
   {
      _value = 0.0;
   }

   public OmtHLAfloat64BE(double value)
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
      final long longBits = Double.doubleToLongBits(_value);
      byteWrapper.put((int)(longBits >>> 56) & 0xFF);
      byteWrapper.put((int)(longBits >>> 48) & 0xFF);
      byteWrapper.put((int)(longBits >>> 40) & 0xFF);
      byteWrapper.put((int)(longBits >>> 32) & 0xFF);
      byteWrapper.put((int)(longBits >>> 24) & 0xFF);
      byteWrapper.put((int)(longBits >>> 16) & 0xFF);
      byteWrapper.put((int)(longBits >>> 8) & 0xFF);
      byteWrapper.put((int)(longBits >>> 0) & 0xFF);
   }

   public int getEncodedLength()
   {
      return 8;
   }

   public void decode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(getOctetBoundary());
      long longBits = 0L;
      longBits += (long)byteWrapper.get() << 56;
      longBits += (long)byteWrapper.get() << 48;
      longBits += (long)byteWrapper.get() << 40;
      longBits += (long)byteWrapper.get() << 32;
      longBits += (long)byteWrapper.get() << 24;
      longBits += (long)byteWrapper.get() << 16;
      longBits += (long)byteWrapper.get() << 8;
      longBits += (long)byteWrapper.get() << 0;
      _value = Double.longBitsToDouble(longBits);
   }

   public double getValue()
   {
      return _value;
   }

   public boolean equals(Object o)
   {
      if (this == o) {
         return true;
      }
      if (!(o instanceof OmtHLAfloat64BE)) {
         return false;
      }

      final OmtHLAfloat64BE omtHLAfloat64LE = (OmtHLAfloat64BE)o;

      if (_value != omtHLAfloat64LE._value) {
         return false;
      }

      return true;
   }

   public int hashCode()
   {
      final long temp = _value != +0.0d ? Double.doubleToLongBits(_value) : 0L;
      return (int)(temp ^ (temp >>> 32));
   }
}
