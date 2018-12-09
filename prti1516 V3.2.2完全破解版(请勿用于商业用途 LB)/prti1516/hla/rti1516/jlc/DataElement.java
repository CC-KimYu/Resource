package hla.rti1516.jlc;

/**
 * Created by IntelliJ IDEA.
 * User: micke
 * Date: 2004-apr-28
 * Time: 13:47:22
 * To change this template use File | Settings | File Templates.
 */
public interface DataElement
{
   /**
    * Returns the octet boundary of this element.
    *
    * @return
    */
   int getOctetBoundary();

   /**
    * Encodes this element into the specified ByteWrapper.
    *
    * @param byteWrapper
    */
   void encode(ByteWrapper byteWrapper);

   /**
    * Returns the size in bytes of this element's encoding.
    *
    * @return size
    */
   int getEncodedLength();

   /**
    * Returns a byte array with this element encoded.
    *
    * @return byte array with encoded element
    */
   byte[] toByteArray();

   /**
    * Decodes this element from the ByteWrapper.
    *
    * @param byteWrapper
    */
   void decode(ByteWrapper byteWrapper);
}
