//File: MessageRetractionReturn.java

/**
 * Record returned by updateAttributeValues, sendInteraction, and deleteObject
 */

package hla.rti1516;

public final class MessageRetractionReturn
    implements java.io.Serializable
{
  public MessageRetractionReturn(boolean rhiv, MessageRetractionHandle mrh) {
    retractionHandleIsValid = rhiv;
    handle = mrh;
  }

  public boolean                 retractionHandleIsValid;
  public MessageRetractionHandle handle;
}
//end MessageRetractionReturn
