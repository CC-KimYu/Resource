//File: ParameterHandleFactory.java

package hla.rti1516;

/**
 * The factory is used only (outside RTI) to create ParameterHandle
 * received as an attribute value or parameter value.
 */

public interface ParameterHandleFactory extends java.io.Serializable {
	public ParameterHandle decode(byte[] buffer, int offset)
	  throws CouldNotDecode, FederateNotExecutionMember;
}
