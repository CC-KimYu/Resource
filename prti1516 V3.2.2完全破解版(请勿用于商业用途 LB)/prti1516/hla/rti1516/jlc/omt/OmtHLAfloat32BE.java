package hla.rti1516.jlc.omt;

import hla.rti1516.jlc.ByteWrapper;
import hla.rti1516.jlc.HLAinteger32BE;
import hla.rti1516.jlc.HLAfloat32BE;

public class OmtHLAfloat32BE extends AbstractDataElement implements HLAfloat32BE
{
   private float _value;

   public OmtHLAfloat32BE()
   {
      _value = 0.0f;
   }

   public OmtHLAfloat32BE(float value)
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
      int intBits = Float.floatToIntBits(_value);
      byteWrapper.putInt(intBits);
   }

   public int getEncodedLength()
   {
      return 4;
   }

   public void decode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(getOctetBoundary());
      int intBits = byteWrapper.getInt();
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
      if (!(o instanceof OmtHLAfloat32BE)) {
         return false;
      }

      final OmtHLAfloat32BE omtHLAfloat32BE = (OmtHLAfloat32BE)o;

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
