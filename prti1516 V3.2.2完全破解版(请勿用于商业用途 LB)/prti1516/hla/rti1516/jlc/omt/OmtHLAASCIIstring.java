package hla.rti1516.jlc.omt;

import hla.rti1516.jlc.ByteWrapper;
import hla.rti1516.jlc.HLAunicodeString;
import hla.rti1516.jlc.HLAASCIIstring;

import java.io.UnsupportedEncodingException;

public class OmtHLAASCIIstring extends AbstractDataElement implements HLAASCIIstring
{
   private String _string;
   private static final String CHARSET_NAME = "ISO-8859-1";

   public OmtHLAASCIIstring()
   {
      _string = "";
   }

   public OmtHLAASCIIstring(String s)
   {
      _string = (s != null) ? s : "";
   }

   public void encode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(getOctetBoundary());
      byte[] bytes;
      try {
         bytes = _string.getBytes(CHARSET_NAME);
      } catch (UnsupportedEncodingException e) {
         bytes = _string.getBytes();
      }
      byteWrapper.putInt(_string.length());
      byteWrapper.put(bytes);
   }

   public final void decode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(getOctetBoundary());
      int length = byteWrapper.getInt();
      byte[] bytes = new byte[length];
      byteWrapper.get(bytes);
      try {
         _string = new String(bytes, CHARSET_NAME);
      } catch (UnsupportedEncodingException e) {
         _string = "<INVALID>";
         throw new IllegalArgumentException("Cannot decode ASCII string");
      }
   }

   public int getEncodedLength()
   {
      return 4 + _string.length();
   }

   public int getOctetBoundary()
   {
      return 4;
   }

   public String toString()
   {
      return _string;
   }

   public String getValue()
   {
      return _string;
   }

   public boolean equals(Object o)
   {
      if (this == o) {
         return true;
      }
      if (!(o instanceof HLAunicodeString)) {
         return false;
      }

      final OmtHLAASCIIstring omtAunicodeString = (OmtHLAASCIIstring)o;

      if (!_string.equals(omtAunicodeString._string)) {
         return false;
      }

      return true;
   }

   public int hashCode()
   {
      return _string.hashCode();
   }
}
