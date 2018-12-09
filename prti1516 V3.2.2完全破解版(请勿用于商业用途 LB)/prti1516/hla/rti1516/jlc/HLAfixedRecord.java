package hla.rti1516.jlc;

import java.util.Iterator;

/**
 * Interface for the HLA data type HLAfixedRecord.
 */
public interface HLAfixedRecord extends DataElement
{
   int getOctetBoundary();

   void encode(ByteWrapper byteWrapper);

   int getEncodedLength();

   void decode(ByteWrapper byteWrapper);

   /**
    * Adds an element to this fixed record.
    *
    * @param dataElement
    */
   void add(DataElement dataElement);

   /**
    * Returns the number of elements in this fixed record.
    *
    * @return
    */
   int size();

   /**
    * Returns element at the specified index.
    *
    * @param index
    * @return
    */
   DataElement get(int index);

   /**
    * Returns an iterator for the elements in this fixed record.
    *
    * @return
    */
   Iterator iterator();
}
