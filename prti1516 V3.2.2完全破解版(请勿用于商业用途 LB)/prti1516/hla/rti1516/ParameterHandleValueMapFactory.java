
//File: ParameterHandleValueMapFactory.java
package hla.rti1516;

/**
 * Factory for ParameterHandleValueMap instances.
 */
public interface ParameterHandleValueMapFactory extends java.io.Serializable {

  /**
   * Creates a new ParameterHandleValueMap instance with specified initial capacity.
   */
  public ParameterHandleValueMap create ( int capacity);
}
//end ParameterHandleValueMapFactory

