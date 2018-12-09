package hla.rti1516.jlc.omt;

import hla.rti1516.jlc.ByteWrapper;
import hla.rti1516.jlc.HLAoctetPairLE;


public class OmtHLAoctetPairLE extends AbstractDataElement implements HLAoctetPairLE
{
   private short _value;

   public OmtHLAoctetPairLE(short value)
   {
      _value = value;
   }

   public OmtHLAoctetPairLE()
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
      byteWrapper.put((_value >>> 0) & 0xFF);
      byteWrapper.put((_value >>> 8) & 0xFF);
   }

   public int getEncodedLength()
   {
      return 2;
   }

   public final void decode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(getOctetBoundary());
      short value = 0;
      value += (short)byteWrapper.get() << 0;
      value += (short)byteWrapper.get() << 8;
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
      if (!(o instanceof OmtHLAoctetPairLE)) {
         return false;
      }

      final OmtHLAoctetPairLE omtHLAoctetPairLE = (OmtHLAoctetPairLE)o;

      if (_value != omtHLAoctetPairLE._value) {
         return false;
      }

      return true;
   }

   public int hashCode()
   {
      return (int)_value;
   }
}
