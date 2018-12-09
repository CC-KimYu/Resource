package hla.rti1516.jlc.omt;

import hla.rti1516.jlc.ByteWrapper;
import hla.rti1516.jlc.HLAinteger16LE;


public class OmtHLAinteger16LE extends AbstractDataElement implements HLAinteger16LE
{
   private short _value;

   public OmtHLAinteger16LE(short value)
   {
      _value = value;
   }

   public OmtHLAinteger16LE()
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
      if (!(o instanceof OmtHLAinteger16LE)) {
         return false;
      }

      final OmtHLAinteger16LE omtHLAinteger16LE = (OmtHLAinteger16LE)o;

      if (_value != omtHLAinteger16LE._value) {
         return false;
      }

      return true;
   }

   public int hashCode()
   {
      return (int)_value;
   }
}
