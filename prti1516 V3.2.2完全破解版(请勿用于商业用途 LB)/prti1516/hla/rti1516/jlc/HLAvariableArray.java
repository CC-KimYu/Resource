package hla.rti1516.jlc;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: micke
 * Date: 2004-apr-28
 * Time: 14:07:11
 * To change this template use File | Settings | File Templates.
 */
public interface HLAvariableArray extends DataElement
{
   void encode(ByteWrapper byteWrapper);

   void decode(ByteWrapper byteWrapper);

   int getEncodedLength();

   int getOctetBoundary();

   /**
    * Adds an element to this variable array.
    *
    * @param dataElement
    */
   void addElement(DataElement dataElement);

   /**
    * Returns the number of elements in this variable array.
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
    * Returns an iterator for the elements in this variable array.
    *
    * @return
    */
   Iterator iterator();
}
