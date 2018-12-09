//File: AttributeHandle.java

package hla.rti1516;

/**
 * Type-safe handle for an attribute. Generally these are created by the
 * RTI and passed to the user.
 */

public interface AttributeHandle extends java.io.Serializable {

  /**
   * @return true if this refers to the same attribute as other handle
   */
  public boolean equals(Object otherAttributeHandle);

  /**
   * @return int. All instances that refer to the same attribute should return the
   * same hashcode.
   */
  public int hashCode();

   public int encodedLength();
   public void encode(byte[] buffer, int offset);

   public String toString();

}
//end AttributeHandle
