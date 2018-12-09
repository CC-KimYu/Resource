package hla.rti1516.jlc.omt;

import hla.rti1516.jlc.ByteWrapper;
import hla.rti1516.jlc.HLAoctet;


public class OmtHLAoctet extends AbstractDataElement implements HLAoctet
{
   private byte _value;

   public OmtHLAoctet(byte value)
   {
      _value = value;
   }

   public OmtHLAoctet()
   {
      _value = 0;
   }

   public int getOctetBoundary()
   {
      return 1;
   }

   public void encode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(getOctetBoundary());
      byteWrapper.put((_value >>> 0) & 0xFF);
   }

   public int getEncodedLength()
   {
      return 1;
   }

   public final void decode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(getOctetBoundary());
      _value = (byte)byteWrapper.get();
   }

   public byte getValue()
   {
      return _value;
   }
}
