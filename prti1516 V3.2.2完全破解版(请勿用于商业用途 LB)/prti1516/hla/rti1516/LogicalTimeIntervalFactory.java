
package hla.rti1516;

public interface LogicalTimeIntervalFactory extends java.io.Serializable {

	public LogicalTimeInterval decode(byte[] buffer, int offset)
	  throws CouldNotDecode;
	public LogicalTimeInterval makeZero();
	public LogicalTimeInterval makeEpsilon();
}
