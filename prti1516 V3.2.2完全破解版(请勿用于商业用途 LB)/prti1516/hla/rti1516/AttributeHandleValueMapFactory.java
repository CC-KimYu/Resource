
//File: AttributeHandleValueMapFactory.java
package hla.rti1516;

  /**
   * Factory for AttributeHandleValueMap instances.
   */
  public interface AttributeHandleValueMapFactory extends java.io.Serializable {

  /**
   * Creates a new AttributeHandleValueMap instance with specified initial capacity.
   */
  public AttributeHandleValueMap create ( int capacity);
}
//end AttributeHandleValueMapFactory

