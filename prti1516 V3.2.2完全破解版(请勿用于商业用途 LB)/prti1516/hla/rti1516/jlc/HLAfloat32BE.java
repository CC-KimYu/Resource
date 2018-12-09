package hla.rti1516.jlc;

/**
 * Interface for the HLA data type HLAinteger32BE.
 */
public interface HLAfloat32BE extends DataElement
{
   int getOctetBoundary();

   void encode(ByteWrapper byteWrapper);

   int getEncodedLength();

   void decode(ByteWrapper byteWrapper);

   /**
    * Returns the float value of this element.
    *
    * @return float value
    */
   float getValue();
}
