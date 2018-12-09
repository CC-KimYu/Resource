//File: DimensionHandle.java

package hla.rti1516;

/**
 * Type-safe handle for a dimension. Generally these are created by the
 * RTI and passed to the user.
 */

public interface DimensionHandle extends java.io.Serializable {

  /**
   * @return true if this refers to the same dimension as other handle
   */
  public boolean equals(Object otherDimensionHandle);

  /**
   * @return int. All instances that refer to the same dimension should return the
   * same hascode.
   */
  public int hashCode();

   public int encodedLength();
   public void encode(byte[] buffer, int offset);

   public String toString();

}
//end DimensionHandle
