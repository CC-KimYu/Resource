package hla.rti1516.jlc.omt;

import hla.rti1516.jlc.ByteWrapper;
import hla.rti1516.jlc.HLAunicodeChar;


public class OmtHLAunicodeChar extends AbstractDataElement implements HLAunicodeChar
{
   private short _value;

   public OmtHLAunicodeChar(short value)
   {
      _value = value;
   }

   public OmtHLAunicodeChar()
   {
      _value = 0;
   }

   public int getOctetBoundary()
   {
      return 2;
   }

   public void encode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(getOctetBoundary());
      byteWrapper.put((_value >>> 8) & 0xFF);
      byteWrapper.put((_value >>> 0) & 0xFF);
   }

   public int getEncodedLength()
   {
      return 2;
   }

   public final void decode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(getOctetBoundary());
      short value = 0;
      value += (short)byteWrapper.get() << 8;
      value += (short)byteWrapper.get() << 0;
      _value = value;
   }

   public short getValue()
   {
      return _value;
   }

   public boolean equals(Object o)
   {
      if (this == o) {
         return true;
      }
      if (!(o instanceof OmtHLAunicodeChar)) {
         return false;
      }

      final OmtHLAunicodeChar omtHLAoctetPairBE = (OmtHLAunicodeChar)o;

      if (_value != omtHLAoctetPairBE._value) {
         return false;
      }

      return true;
   }

   public int hashCode()
   {
      return (int)_value;
   }
}
