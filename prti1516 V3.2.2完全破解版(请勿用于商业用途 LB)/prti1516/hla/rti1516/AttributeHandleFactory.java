//File: AttributeHandleFactory.java

package hla.rti1516;

/**
 * The factory is used only (outside RTI) to create AttributeHandle
 * received as an attribute value or parameter value.
 */

public interface AttributeHandleFactory extends java.io.Serializable {
	public AttributeHandle decode(byte[] buffer, int offset)
	  throws CouldNotDecode, FederateNotExecutionMember;
}
