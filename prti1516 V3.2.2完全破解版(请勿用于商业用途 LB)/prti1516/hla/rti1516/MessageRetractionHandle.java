
//File: MessageRetractionHandle.java

package hla.rti1516;

/**
 * The user can do nothing with these but employ them as keys.
 * Implementers should provide equals, hashCode and toString
 * rather than settling for the defaults.
 * 
 */
public interface MessageRetractionHandle extends java.io.Serializable {

  /**
   * @return true if this refers to the same Message as other handle
   */
  public boolean equals(Object otherMRHandle);

  /**
   * @return int. All instances that refer to the same Message should return the
   * same hashcode.
   */
  public int hashCode();

  public String toString();
}
//end MessageRetractionHandle


