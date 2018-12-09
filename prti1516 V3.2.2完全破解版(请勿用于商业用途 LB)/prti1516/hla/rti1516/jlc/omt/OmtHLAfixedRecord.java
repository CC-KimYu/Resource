package hla.rti1516.jlc.omt;

import hla.rti1516.jlc.ByteWrapper;
import hla.rti1516.jlc.DataElement;
import hla.rti1516.jlc.HLAfixedRecord;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OmtHLAfixedRecord extends AbstractDataElement implements HLAfixedRecord
{
   private List _elements = new ArrayList(10);

   public OmtHLAfixedRecord()
   {
   }

   public OmtHLAfixedRecord(DataElement dataElement)
   {
      add(dataElement);
   }

   public OmtHLAfixedRecord(DataElement dataElement1, DataElement dataElement2)
   {
      add(dataElement1);
      add(dataElement2);
   }

   public final void add(DataElement dataElement)
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
    * Returns the DataElement at the specified position in this array.
    *
    * @param index index of DataElement to return.
    * @return DataElement at the specified index
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

   public int getOctetBoundary()
   {
      int boundary = 4; // Default to 4
      for (Iterator iterator = _elements.iterator(); iterator.hasNext();) {
         DataElement dataElement = (DataElement)iterator.next();
         boundary = Math.max(boundary, dataElement.getOctetBoundary());
      }
      return boundary;
   }

   public void encode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(getOctetBoundary());
      for (Iterator iterator = _elements.iterator(); iterator.hasNext();) {
         DataElement dataElement = (DataElement)iterator.next();
         dataElement.encode(byteWrapper);
      }
   }

   public int getEncodedLength()
   {
      int size = 0;
      for (Iterator iterator = _elements.iterator(); iterator.hasNext();) {
         DataElement dataElement = (DataElement)iterator.next();
         while ((size % dataElement.getOctetBoundary()) != 0) {
            size += 1;
         }
         size += dataElement.getEncodedLength();
      }
      return size;
   }

   public void decode(ByteWrapper byteWrapper)
   {
      for (Iterator iterator = _elements.iterator(); iterator.hasNext();) {
         DataElement dataElement = (DataElement)iterator.next();
         dataElement.decode(byteWrapper);
      }
   }
}
