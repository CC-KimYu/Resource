package hla.rti1516;

/**
 * Public exception class NoAcquisitionPending. This is deliberately
 * not a final class.
*/
public class NoAcquisitionPending extends RTIexception {
  public NoAcquisitionPending(String msg) {
    super(msg);
  }
}
