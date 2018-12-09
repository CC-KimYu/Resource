//File: InteractionClassHandleFactory.java

package hla.rti1516;

/**
 * The factory is used only (outside RTI) to create InteractionClassHandle
 * received as an attribute value or parameter value.
 */

public interface InteractionClassHandleFactory extends java.io.Serializable {
	public InteractionClassHandle decode(byte[] buffer, int offset)
	  throws CouldNotDecode, FederateNotExecutionMember;
}
