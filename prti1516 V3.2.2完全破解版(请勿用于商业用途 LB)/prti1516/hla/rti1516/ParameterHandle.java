//File: ParameterHandle.java

package hla.rti1516;

/**
 * Type-safe handle for a parameter. Generally these are created by the
 * RTI and passed to the user.
 */

public interface ParameterHandle extends java.io.Serializable {

  /**
   * @return true if this refers to the same parameter as other handle
   */
  public boolean equals(Object otherParameterHandle);

  /**
   * @return int. All instances that refer to the same parameter should return the
   * same hascode.
   */
  public int hashCode();

   public int encodedLength();
   public void encode(byte[] buffer, int offset);

   public String toString();

}
//end ParameterHandle
