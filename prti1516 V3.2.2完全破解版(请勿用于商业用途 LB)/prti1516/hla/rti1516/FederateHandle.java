//File: FederateHandle.java

package hla.rti1516;

/**
 * Type-safe handle for a federate handle. Generally these are created by the
 * RTI and passed to the user.
 */

public interface FederateHandle extends java.io.Serializable {

  /**
   * @return true if this refers to the same federate as other handle
   */
  public boolean equals(Object otherFederateHandle);

  /**
   * @return int. All instances that refer to the same federate should return the
   * same hashcode.
   */
  public int hashCode();

   public int encodedLength();
   public void encode(byte[] buffer, int offset);

   public String toString();

}
//end FederateHandle
