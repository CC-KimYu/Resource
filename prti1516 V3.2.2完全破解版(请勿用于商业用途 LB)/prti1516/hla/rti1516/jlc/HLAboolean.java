package hla.rti1516.jlc;

/**
 * Interface for the HLA data type HLAinteger32BE.
 */
public interface HLAboolean extends DataElement
{
   int getOctetBoundary();

   void encode(ByteWrapper byteWrapper);

   int getEncodedLength();

   void decode(ByteWrapper byteWrapper);

   /**
    * Returns the boolean value of this element.
    *
    * @return value
    */
   boolean getValue();
}
