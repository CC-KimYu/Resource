package hla.rti1516.jlc.omt;

import hla.rti1516.jlc.ByteWrapper;
import hla.rti1516.jlc.HLAinteger32BE;

public class OmtHLAinteger32BE extends AbstractDataElement implements HLAinteger32BE
{
   private int _value;

   public OmtHLAinteger32BE()
   {
      _value = 0;
   }

   public OmtHLAinteger32BE(int value)
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
      byteWrapper.putInt(_value);
   }

   public int getEncodedLength()
   {
      return 4;
   }

   public void decode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(getOctetBoundary());
      _value = byteWrapper.getInt();
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
      if (!(o instanceof OmtHLAinteger32BE)) {
         return false;
      }

      final OmtHLAinteger32BE other = (OmtHLAinteger32BE)o;

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
