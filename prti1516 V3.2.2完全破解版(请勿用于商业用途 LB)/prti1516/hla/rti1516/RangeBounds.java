//File: RangeBounds.java

/**
 * Record returned by (10.31) getRangeBounds
 */

package hla.rti1516;

public final class RangeBounds
   implements java.io.Serializable {
   public RangeBounds(long l, long u)
   {
      lower = l;
      upper = u;
   }

   public long lower;
   public long upper;

   public boolean equals(Object other)
   {
      if (other != null && other instanceof RangeBounds) {
         RangeBounds otherRangeBounds = (RangeBounds)other;
         return lower == otherRangeBounds.lower && upper == otherRangeBounds.upper;
      } else {
         return false;
      }
   }

   public int hashCode()
   {
      return (int)(lower + upper);
   }
}

//end RangeBounds
