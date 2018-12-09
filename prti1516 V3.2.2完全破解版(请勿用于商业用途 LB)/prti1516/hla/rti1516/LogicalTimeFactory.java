
package hla.rti1516;

public interface LogicalTimeFactory extends java.io.Serializable {

	public LogicalTime decode(byte[] buffer, int offset)
	  throws CouldNotDecode;
	public LogicalTime makeInitial();
	public LogicalTime makeFinal();
}
