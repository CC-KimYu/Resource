//File: DimensionHandleFactory.java

package hla.rti1516;

/**
 * The factory is used only (outside RTI) to create DimensionHandle
 * received as an attribute value or parameter value.
 */

public interface DimensionHandleFactory extends java.io.Serializable {
	public DimensionHandle decode(byte[] buffer, int offset)
	  throws CouldNotDecode, FederateNotExecutionMember;
}
