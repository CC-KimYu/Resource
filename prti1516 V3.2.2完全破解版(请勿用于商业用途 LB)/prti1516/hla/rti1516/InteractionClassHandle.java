//File: InteractionClassHandle.java

package hla.rti1516;

/**
 * Type-safe handle for an interaction class. Generally these are created by the
 * RTI and passed to the user.
 */

public interface InteractionClassHandle extends java.io.Serializable {

  /**
   * @return true if this refers to the same interaction class as other handle
   */
  public boolean equals(Object otherInteractionClassHandle);

  /**
   * @return int. All instances that refer to the same interaction class should
   * return the same hashcode.
   */
  public int hashCode();

   public int encodedLength();
   public void encode(byte[] buffer, int offset);

   public String toString();

}
//end InteractionClassHandle
