//File: AttributeRegionAssociation.java

/**
 * Record stored in AttributeSetRegionSetPairList
 */

package hla.rti1516;

public final class AttributeRegionAssociation
    implements java.io.Serializable
{
  public AttributeRegionAssociation(AttributeHandleSet ahs, RegionHandleSet rhs) {
    ahset = ahs;
    rhset = rhs;
  }

  public AttributeHandleSet ahset;
  public RegionHandleSet    rhset;
}
//end AttributeRegionAssociation
