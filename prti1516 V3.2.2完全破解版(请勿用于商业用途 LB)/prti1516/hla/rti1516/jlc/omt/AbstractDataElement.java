package hla.rti1516.jlc.omt;

import hla.rti1516.jlc.DataElement;
import hla.rti1516.jlc.ByteWrapper;

/**
 * Base class for HLA 1516 data elements.
 *
 * @author Mikael Karlsson, Pitch AB
 */
public abstract class AbstractDataElement implements DataElement
{
   /**
    * Returns a byte array with this element encoded.
    *
    * @return byte array with encoded element
    */
   public byte[] toByteArray()
   {
      ByteWrapper byteWrapper = new ByteWrapper(getEncodedLength());
      encode(byteWrapper);
      return byteWrapper.array();
   }
}
