package hla.rti1516.jlc.omt;

import hla.rti1516.jlc.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class OmtHLAvariableArray extends AbstractDataElement implements HLAvariableArray
{
   private final List _elements = new ArrayList(10);
   private final DataElementFactory _elementFactory;

   public OmtHLAvariableArray()
   {
      _elementFactory = null;
   }

   public OmtHLAvariableArray(DataElementFactory elementFactory)
   {
      _elementFactory = elementFactory;
   }

   public OmtHLAvariableArray(DataElement[] momElements)
   {
      _elementFactory = null;
      _elements.addAll(Arrays.asList(momElements));
   }

   /**
    * Appends the specified element to the end of this array.
    *
    * @param dataElement AbstractDataElement to append.
    */
   public void addElement(DataElement dataElement)
   {
      _elements.add(dataElement);
   }

   /**
    * Returns the number of components in this array.
    *
    * @return the number of components in this array.
    */
   public int size()
   {
      return _elements.size();
   }

   /**
    * Returns the AbstractDataElement at the specified position in this array.
    *
    * @param index index of AbstractDataElement to return.
    * @return AbstractDataElement at the specified index
    */
   public DataElement get(int index)
   {
      return (DataElement)_elements.get(index);
   }

   /**
    * Returns an iterator over the elements in this array in proper sequence.
    *
    * @return an iterator over the elements in this array in proper sequence.
    */
   public Iterator iterator()
   {
      return _elements.iterator();
   }

   /**
    * Encodes this element into the specified ByteWrapper.
    *
    * @param byteWrapper
    */
   public void encode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(4);
      byteWrapper.putInt(_elements.size());
      for (Iterator iterator = _elements.iterator(); iterator.hasNext();) {
         DataElement dataElement = (DataElement)iterator.next();
         dataElement.encode(byteWrapper);
      }
   }

   public void decode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(4);
      int elements = byteWrapper.getInt();
      if (_elements.size() != 0) {
         for (Iterator i = _elements.iterator(); i.hasNext();) {
            DataElement dataElement = (DataElement)i.next();
            dataElement.decode(byteWrapper);
         }
      } else {
         for (int i = 0; i < elements; i++) {
            _elements.add(_elementFactory.createElement(i));
         }
      }
   }

   public int getEncodedLength()
   {
      int size = 4;
      for (Iterator iterator = _elements.iterator(); iterator.hasNext();) {
         DataElement dataElement = (DataElement)iterator.next();
         while ((size % dataElement.getOctetBoundary()) != 0) {
            size += 1;
         }
         size += dataElement.getEncodedLength();
      }
      return size;
   }

   public int getOctetBoundary()
   {
      int boundary = 4; // Minimum is 4 for the count-element
      for (Iterator iterator = _elements.iterator(); iterator.hasNext();) {
         DataElement dataElement = (DataElement)iterator.next();
         boundary = Math.max(boundary, dataElement.getOctetBoundary());
      }
      return boundary;
   }

   public boolean equals(Object o)
   {
      if (this == o) {
         return true;
      }
      if (!(o instanceof HLAvariableArray)) {
         return false;
      }

      final OmtHLAvariableArray omtAvariableArray = (OmtHLAvariableArray)o;

      if (!_elements.equals(omtAvariableArray._elements)) {
         return false;
      }

      return true;
   }

   public int hashCode()
   {
      return _elements.hashCode();
   }
}
