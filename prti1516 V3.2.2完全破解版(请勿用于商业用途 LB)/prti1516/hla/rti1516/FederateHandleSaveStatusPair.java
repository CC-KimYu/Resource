//File: FederateHandleSaveStatusPair.java

/**
 * Array of these records returned by (4.17) federationSaveStatusResponse
 */

package hla.rti1516;

public final class FederateHandleSaveStatusPair
    implements java.io.Serializable
{
  public FederateHandleSaveStatusPair(FederateHandle fh, SaveStatus ss) {
    handle = fh;
    status = ss;
  }
  public FederateHandle handle;
  public SaveStatus     status;
}
//end FederateHandleSaveStatusPair
