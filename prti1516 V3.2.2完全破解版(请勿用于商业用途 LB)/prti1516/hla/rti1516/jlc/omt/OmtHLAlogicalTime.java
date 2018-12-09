package hla.rti1516.jlc.omt;

import hla.rti1516.LogicalTime;
import hla.rti1516.jlc.ByteWrapper;
import hla.rti1516.jlc.HLAlogicalTime;

import java.util.Arrays;
import java.util.Iterator;

public class OmtHLAlogicalTime extends AbstractDataElement implements HLAlogicalTime
{
   private byte[] _elements;

   public OmtHLAlogicalTime()
   {
      _elements = new byte[0];
   }

   public OmtHLAlogicalTime(byte[] bytes)
   {
      _elements = bytes;
   }

   public OmtHLAlogicalTime(LogicalTime logicalTime)
   {
      final int size = logicalTime.encodedLength();
      _elements = new byte[size];
      logicalTime.encode(_elements, 0);
   }

   /**
    * Returns the number of components in this array.
    *
    * @return the number of components in this array.
    */
   public int size()
   {
      return _elements.length;
   }

   /**
    * Returns the DataElement at the specified position in this array.
    *
    * @param index index of DataElement to return.
    * @return DataElement at the specified index
    */
   public byte get(int index)
   {
      return _elements[index];
   }

   /**
    * Returns an iterator over the elements in this array in proper sequence.
    *
    * @return an iterator over the elements in this array in proper sequence.
    */
   public Iterator iterator()
   {
      return new Iterator()
      {
         private int _index = 0;

         public void remove()
         {
            throw new UnsupportedOperationException();
         }

         public boolean hasNext()
         {
            return _index < _elements.length;
         }

         public Object next()
         {
            return new Byte(_elements[_index++]);
         }
      };
   }

   public void encode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(4);
      byteWrapper.putInt(_elements.length);
      byteWrapper.put(_elements);
   }

   public void decode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(4);
      int encodedLength = byteWrapper.getInt();
      _elements = new byte[encodedLength];
      byteWrapper.get(_elements);
   }

   public int getEncodedLength()
   {
      return 4 + _elements.length;
   }

   public int getOctetBoundary()
   {
      return 4;
   }

   public byte[] getValue()
   {
      return _elements;
   }

   public boolean equals(Object o)
   {
      if (this == o) {
         return true;
      }
      if (!(o instanceof OmtHLAlogicalTime)) {
         return false;
      }

      final OmtHLAlogicalTime omtHLAlogicalTime = (OmtHLAlogicalTime)o;

      if (!Arrays.equals(_elements, omtHLAlogicalTime._elements)) {
         return false;
      }

      return true;
   }

   public int hashCode()
   {
      return 0;
   }
}
