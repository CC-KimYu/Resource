package hla.rti1516.jlc;

import java.util.Iterator;

public interface HLAopaqueData extends DataElement
{
   int size();

   byte get(int index);

   Iterator iterator();

   void encode(ByteWrapper byteWrapper);

   void decode(ByteWrapper byteWrapper);

   int getEncodedLength();

   int getOctetBoundary();

   byte[] getValue();
}
