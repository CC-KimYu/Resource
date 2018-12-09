package hla.rti1516.jlc.omt;

import hla.rti1516.jlc.ByteWrapper;
import hla.rti1516.jlc.HLAunicodeString;

public class OmtHLAunicodeString extends AbstractDataElement implements HLAunicodeString
{
   private String _string;

   public OmtHLAunicodeString()
   {
      _string = "";
   }

   public OmtHLAunicodeString(String s)
   {
      _string = (s != null) ? s : "";
   }

   public void encode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(getOctetBoundary());
      byteWrapper.putInt(_string.length());
      int len = _string.length();
      for (int i = 0; i < len; i++) {
         int v = (int)_string.charAt(i);
         byteWrapper.put((v >>> 8) & 0xFF);
         byteWrapper.put((v >>> 0) & 0xFF);
      }
   }

   public final void decode(ByteWrapper byteWrapper)
   {
      byteWrapper.align(getOctetBoundary());
      int length = byteWrapper.getInt();
      char[] chars = new char[length];
      for (int i = 0; i < length; i++) {
         int hi = byteWrapper.get();
         int lo = byteWrapper.get();
         chars[i] = (char)((hi << 8) + (lo << 0));
      }
      _string = new String(chars);
   }

   public int getEncodedLength()
   {
      return 4 + _string.length() * 2;
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

      final OmtHLAunicodeString omtAunicodeString = (OmtHLAunicodeString)o;

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
