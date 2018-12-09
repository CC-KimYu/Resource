package hla.rti1516.jlc;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: micke
 * Date: 2004-apr-30
 * Time: 08:51:30
 * To change this template use File | Settings | File Templates.
 */
public interface HLAhandle extends DataElement
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
