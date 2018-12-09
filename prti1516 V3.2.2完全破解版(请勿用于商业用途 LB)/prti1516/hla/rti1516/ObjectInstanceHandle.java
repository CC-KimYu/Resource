//File: ObjectInstanceHandle.java

package hla.rti1516;

/**
 * Type-safe handle for an object instance. Generally these are created by the
 * RTI and passed to the user.
 */

public interface ObjectInstanceHandle extends java.io.Serializable {

  /**
   * @return true if this refers to the same instance as other handle
   */
  public boolean equals(Object otherObjectInstanceHandle);

  /**
   * @return int. All instances that refer to the same instance should return the
   * same hascode.
   */
  public int hashCode();

   public int encodedLength();
   public void encode(byte[] buffer, int offset);

   public String toString();

}
//end ObjectInstanceHandle
