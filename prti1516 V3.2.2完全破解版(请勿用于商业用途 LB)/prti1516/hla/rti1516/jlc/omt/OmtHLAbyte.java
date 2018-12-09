package hla.rti1516.jlc.omt;

import hla.rti1516.jlc.ByteWrapper;
import hla.rti1516.jlc.HLAbyte;

/**
 * Utility class for MOM simple datatype HLAbyte.
 *
 * @author Mikael Karlsson, Pitch AB
 */
public class OmtHLAbyte extends AbstractDataElement implements HLAbyte
{
   private OmtHLAoctet _value;

   public OmtHLAbyte(byte value)
   {
      _value = new OmtHLAoctet(value);
   }

   public OmtHLAbyte()
   {
      _value = new OmtHLAoctet();
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

   public void decode(ByteWrapper byteWrapper)
   {
      _value.decode(byteWrapper);
   }

   public byte getValue()
   {
      return _value.getValue();
   }
}
