package hla.rti1516.jlc.omt;

import hla.rti1516.jlc.ByteWrapper;
import hla.rti1516.jlc.HLAASCIIchar;

/**
 * Utility class for MOM simple datatype HLAbyte.
 *
 * @author Mikael Karlsson, Pitch AB
 */
public class OmtHLAASCIIchar extends AbstractDataElement implements HLAASCIIchar
{
   private OmtHLAoctet _value;

   public OmtHLAASCIIchar(byte value)
   {
      _value = new OmtHLAoctet(value);
   }

   public OmtHLAASCIIchar()
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
