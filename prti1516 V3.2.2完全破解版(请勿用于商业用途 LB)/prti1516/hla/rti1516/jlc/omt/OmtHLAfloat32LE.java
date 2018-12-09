package hla.rti1516.jlc.omt;

import hla.rti1516.jlc.*;

public class OmtHLAfloat32LE extends AbstractDataElement implements HLAfloat32LE
{
   private float _value;

   public OmtHLAfloat32LE()
   {
      _value = 0.0f;
   }

   public OmtHLAfloat32LE(float value)
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
      final int intBits = Float.floatToIntBits(_value);
      byteWrapper.put((intBits >>> 0) & 0xFF);
      byteWrapper.put((intBits >>> 8) & 0xFF);
      byteWrapper.put((intBits >>> 16) & 0xFF);
      byteWrapper.put((intBits >>> 24) & 0xFF);
   }

   public int getEncodedLength()
   {
      return 4;
   }

   public void decode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(getOctetBoundary());
      int intBits = 0;
      intBits += (short)byteWrapper.get() << 0;
      intBits += (short)byteWrapper.get() << 8;
      intBits += (short)byteWrapper.get() << 16;
      intBits += (short)byteWrapper.get() << 24;
      _value = Float.intBitsToFloat(intBits);
   }

   public float getValue()
   {
      return _value;
   }

   public boolean equals(Object o)
   {
      if (this == o) {
         return true;
      }
      if (!(o instanceof OmtHLAfloat32LE)) {
         return false;
      }

      final OmtHLAfloat32LE omtHLAfloat32BE = (OmtHLAfloat32LE)o;

      if (_value != omtHLAfloat32BE._value) {
         return false;
      }

      return true;
   }

   public int hashCode()
   {
      return _value != +0.0f ? Float.floatToIntBits(_value) : 0;
   }
}
