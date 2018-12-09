//File: FederateHandleRestoreStatusPair.java

/**
 * Array of these records returned by (4.25) federationRestoreStatusResponse
 */

package hla.rti1516;

public final class FederateHandleRestoreStatusPair
    implements java.io.Serializable
{
  public FederateHandleRestoreStatusPair(FederateHandle fh, RestoreStatus rs) {
    handle = fh;
    status = rs;
  }

  public FederateHandle handle;
  public RestoreStatus  status;
}
//end FederateHandleRestoreStatusPair
