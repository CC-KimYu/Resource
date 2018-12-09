
//File: RTIexception.java
package hla.rti1516;

/**
 * Superclass of all exceptions thrown by the RTI.
 * All RTI exceptions must be caught or specified.
*/
public class RTIexception extends Exception {
  public RTIexception(String msg) {
    super(msg);
  }
}
//end RTIexception


