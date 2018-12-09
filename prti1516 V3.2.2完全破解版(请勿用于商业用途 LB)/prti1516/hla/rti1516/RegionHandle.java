//File: RegionHandle.java

package hla.rti1516;


public interface RegionHandle extends java.io.Serializable {

  /**
   * @return true if this refers to the same Region as other handle
   */
  public boolean equals(Object otherRegionHandle);

  /**
   * @return int. All instances that refer to the same Region should return the
   * same hashcode.
   */
  public int hashCode();

  public String toString();

}
//end RegionHandle
