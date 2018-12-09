package hla.rti1516.jlc;

/**
 * Created by IntelliJ IDEA.
 * User: micke
 * Date: 2004-apr-30
 * Time: 08:44:54
 * To change this template use File | Settings | File Templates.
 */
public interface HLAinteger16LE extends DataElement
{
   int getOctetBoundary();

   void encode(ByteWrapper byteWrapper);

   int getEncodedLength();

   void decode(ByteWrapper byteWrapper);

   short getValue();
}
