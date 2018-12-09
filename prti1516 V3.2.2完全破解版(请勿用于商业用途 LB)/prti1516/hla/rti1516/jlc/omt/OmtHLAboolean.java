package hla.rti1516.jlc.omt;

import hla.rti1516.jlc.HLAboolean;
import hla.rti1516.jlc.ByteWrapper;

public class OmtHLAboolean extends AbstractDataElement implements HLAboolean
{
   private OmtHLAinteger32BE _value;

   public OmtHLAboolean()
   {
      _value = new OmtHLAinteger32BE(0);
   }

   public OmtHLAboolean(boolean value)
   {
      _value = new OmtHLAinteger32BE(value ? 1 : 0);
   }

   public int getOctetBoundary()
   {
      return _value.getOctetBoundary();
   }

   public void encode(ByteWrapper byteWrapper)
   {
      _value.encode(byteWrapper);
   }

   public int getEncodedLength()
   {
      return _value.getEncodedLength();
   }

   public final void decode(ByteWrapper byteWrapper)
   {
      _value.decode(byteWrapper);
   }

   public boolean getValue()
   {
      return _value.getValue() != 0;
   }
}
