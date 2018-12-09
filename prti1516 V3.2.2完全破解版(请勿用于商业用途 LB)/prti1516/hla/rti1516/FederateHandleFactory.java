//File: FederateHandleFactory.java

package hla.rti1516;

/**
 * The factory is used only (outside RTI) to create FederateHandle
 * received as an attribute value or parameter value.
 */

public interface FederateHandleFactory extends java.io.Serializable {
	public FederateHandle decode(byte[] buffer, int offset)
	  throws CouldNotDecode, FederateNotExecutionMember;
}
