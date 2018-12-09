//File: ObjectInstanceHandleFactory.java

package hla.rti1516;

/**
 * The factory is used only (outside RTI) to create ObjectInstanceHandle
 * received as an attribute value or parameter value.
 */

public interface ObjectInstanceHandleFactory extends java.io.Serializable {
	public ObjectInstanceHandle decode(byte[] buffer, int offset)
	  throws CouldNotDecode, FederateNotExecutionMember;
}
