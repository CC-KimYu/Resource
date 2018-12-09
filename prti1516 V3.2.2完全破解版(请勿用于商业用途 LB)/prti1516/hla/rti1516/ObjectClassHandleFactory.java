//File: ObjectClassHandleFactory.java

package hla.rti1516;

/**
 * The factory is used only (outside RTI) to create ObjectClassHandle
 * received as an attribute value or parameter value.
 */

public interface ObjectClassHandleFactory extends java.io.Serializable {
	public ObjectClassHandle decode(byte[] buffer, int offset)
	  throws CouldNotDecode, FederateNotExecutionMember;
}
