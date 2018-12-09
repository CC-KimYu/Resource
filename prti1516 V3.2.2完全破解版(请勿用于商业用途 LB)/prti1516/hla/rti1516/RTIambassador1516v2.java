
//File: RTIambassador.java
// TMN: (New upcoming) Version: 2, DLC (Dynamic Link Compatibility)

package hla.rti1516;

/**
Memory Management Conventions for Parameters

All Java parameters, including object references, are passed by value.
Therefore there is no need to specify further conventions for primitive types.

Unless otherwise noted, reference parameters adhere to the following convention:
The referenced object is created (or acquired) by the caller. The callee must
copy during the call anything it wishes to save beyond the completion of the
call.

Unless otherwise noted, a reference returned from a method represents a new
object created by the callee. The caller is free to modify the object whose
reference is returned.


*/

/**
 * The RTI presents this interface to the federate.
 * RTI implementer must implement this.
*/

public interface RTIambassador1516v2 {

////////////////////////////////////
// Federation Management Services //
////////////////////////////////////

  //4.2
  public void createFederationExecution (
    String       federationExecutionName,
    java.net.URL fdd)
  throws
    FederationExecutionAlreadyExists,
    CouldNotOpenFDD,
    ErrorReadingFDD,
    RTIinternalError;

  //4.3
  public void destroyFederationExecution (
    String federationExecutionName)
  throws
    FederatesCurrentlyJoined,
    FederationExecutionDoesNotExist,
    RTIinternalError;

  //4.4
  public FederateHandle
  joinFederationExecution(
    String                 federateType,
    String                 federationExecutionName,
    FederateAmbassador     federateReference,
    MobileFederateServices serviceReferences)
  throws
    FederateAlreadyExecutionMember,
    FederationExecutionDoesNotExist,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  //4.5
  public void resignFederationExecution (ResignAction resignAction)
  throws
    OwnershipAcquisitionPending,
    FederateOwnsAttributes,
    FederateNotExecutionMember,
    RTIinternalError;

  //4.6
  public void registerFederationSynchronizationPoint (
    String synchronizationPointLabel,
    byte[] userSuppliedTag)
  throws
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  public void registerFederationSynchronizationPoint (
    String            synchronizationPointLabel,
    byte[]            userSuppliedTag,
    FederateHandleSet synchronizationSet)
  throws
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  //4.9
  public void synchronizationPointAchieved (
    String synchronizationPointLabel)
  throws
    SynchronizationPointLabelNotAnnounced,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 4.11
  public void requestFederationSave (
    String label)
  throws
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  public void requestFederationSave (
    String         label,
    LogicalTime    time)
  throws
    LogicalTimeAlreadyPassed,
    InvalidLogicalTime,
    FederateUnableToUseTime,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 4.13
  public void federateSaveBegun ()
  throws
    SaveNotInitiated,
    FederateNotExecutionMember,
    RestoreInProgress,
    RTIinternalError;

  // 4.14
  public void federateSaveComplete ()
  throws
    FederateHasNotBegunSave,
    FederateNotExecutionMember,
    RestoreInProgress,
    RTIinternalError;

  public void federateSaveNotComplete ()
  throws
    FederateHasNotBegunSave,
    FederateNotExecutionMember,
    RestoreInProgress,
    RTIinternalError;

  // 4.16
  public void queryFederationSaveStatus ()
  throws
    FederateNotExecutionMember,
    RestoreInProgress,
    RTIinternalError;

  // 4.18
  public void requestFederationRestore (
    String label)
  throws
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 4.22
  public void federateRestoreComplete ()
  throws
    RestoreNotRequested,
    FederateNotExecutionMember,
    SaveInProgress,
    RTIinternalError;

  public void federateRestoreNotComplete ()
  throws
    RestoreNotRequested,
    FederateNotExecutionMember,
    SaveInProgress,
    RTIinternalError;

  // 4.24
  public void queryFederationRestoreStatus ()
  throws
    FederateNotExecutionMember,
    SaveInProgress,
    RTIinternalError;

/////////////////////////////////////
// Declaration Management Services //
/////////////////////////////////////

  // 5.2
  public void publishObjectClassAttributes (
    ObjectClassHandle  objectClass,
    AttributeHandleSet attributes)
  throws
    ObjectClassNotDefined,
    AttributeNotDefined,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 5.3
  public void unpublishObjectClass (
    ObjectClassHandle objectClass)
  throws
    ObjectClassNotDefined,
    OwnershipAcquisitionPending,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  public void unpublishObjectClassAttributes (
    ObjectClassHandle  objectClass,
    AttributeHandleSet attributes)
  throws
    ObjectClassNotDefined,
    AttributeNotDefined,
    OwnershipAcquisitionPending,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 5.4
  public void publishInteractionClass (
    InteractionClassHandle interactionClass)
  throws
    InteractionClassNotDefined,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 5.5
  public void unpublishInteractionClass (
    InteractionClassHandle interactionClass)
  throws
    InteractionClassNotDefined,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 5.6
  public void subscribeObjectClassAttributes (
    ObjectClassHandle   objectClass,
    AttributeHandleSet  attributes)
  throws
    ObjectClassNotDefined,
    AttributeNotDefined,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  public void subscribeObjectClassAttributesPassively (
    ObjectClassHandle   objectClass,
    AttributeHandleSet  attributes)
  throws
    ObjectClassNotDefined,
    AttributeNotDefined,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 5.7
  public void unsubscribeObjectClass (
    ObjectClassHandle objectClass)
  throws
    ObjectClassNotDefined,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  public void unsubscribeObjectClassAttributes (
    ObjectClassHandle  objectClass,
    AttributeHandleSet attributes)
  throws
    ObjectClassNotDefined,
    AttributeNotDefined,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 5.8
  public void subscribeInteractionClass (
    InteractionClassHandle interactionClass)
  throws
    InteractionClassNotDefined,
    FederateServiceInvocationsAreBeingReportedViaMOM,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  public void subscribeInteractionClassPassively (
    InteractionClassHandle interactionClass)
  throws
    InteractionClassNotDefined,
    FederateServiceInvocationsAreBeingReportedViaMOM,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 5.9
  public void unsubscribeInteractionClass (
    InteractionClassHandle interactionClass)
  throws
    InteractionClassNotDefined,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

////////////////////////////////
// Object Management Services //
////////////////////////////////

  // 6.2
  public void reserveObjectInstanceName (
    String objectName)
  throws
    IllegalName,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 6.4
  public ObjectInstanceHandle
  registerObjectInstance (
    ObjectClassHandle objectClass)
  throws
    ObjectClassNotDefined,
    ObjectClassNotPublished,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  public ObjectInstanceHandle
  registerObjectInstance (
    ObjectClassHandle objectClass,
    String            objectName)
  throws
    ObjectClassNotDefined,
    ObjectClassNotPublished,
    ObjectInstanceNameNotReserved,
    ObjectInstanceNameInUse,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 6.6
  public void updateAttributeValues (
    ObjectInstanceHandle        objectInstance,
    AttributeHandleValueMap     attributes,
    byte[]                      userSuppliedTag)
  throws
    ObjectInstanceNotKnown,
    AttributeNotDefined,
    AttributeNotOwned,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  public MessageRetractionReturn
  updateAttributeValues (
    ObjectInstanceHandle        objectInstance,
    AttributeHandleValueMap     attributes,
    byte[]                      userSuppliedTag,
    LogicalTime                 time)
  throws
    ObjectInstanceNotKnown,
    AttributeNotDefined,
    AttributeNotOwned,
    InvalidLogicalTime,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 6.8
  public void sendInteraction (
    InteractionClassHandle      interactionClass,
    ParameterHandleValueMap     parameters,
    byte[]                      userSuppliedTag)
  throws
    InteractionClassNotPublished,
    InteractionClassNotDefined,
    InteractionParameterNotDefined,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  public MessageRetractionReturn
  sendInteraction (
    InteractionClassHandle      interactionClass,
    ParameterHandleValueMap     parameters,
    byte[]                      userSuppliedTag,
    LogicalTime                 time)
  throws
    InteractionClassNotPublished,
    InteractionClassNotDefined,
    InteractionParameterNotDefined,
    InvalidLogicalTime,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 6.10
  public void deleteObjectInstance (
    ObjectInstanceHandle objectHandle,
    byte[]               userSuppliedTag)
  throws
    DeletePrivilegeNotHeld,
    ObjectInstanceNotKnown,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  public MessageRetractionReturn
  deleteObjectInstance (
    ObjectInstanceHandle objectHandle,
    byte[]               userSuppliedTag,
    LogicalTime          time)
  throws
    DeletePrivilegeNotHeld,
    ObjectInstanceNotKnown,
    InvalidLogicalTime,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 6.12
  public void localDeleteObjectInstance (
    ObjectInstanceHandle objectHandle)
  throws
    ObjectInstanceNotKnown,
    FederateOwnsAttributes,
    OwnershipAcquisitionPending,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 6.13
  public void changeAttributeTransportationType (
    ObjectInstanceHandle objectInstance,
    AttributeHandleSet   attributes,
    TransportationType   transportationType)
  throws
    ObjectInstanceNotKnown,
    AttributeNotDefined,
    AttributeNotOwned,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 6.14
  public void changeInteractionTransportationType (
    InteractionClassHandle interactionClass,
    TransportationType     transportationType)
  throws
    InteractionClassNotDefined,
    InteractionClassNotPublished,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 6.17
  public void requestAttributeValueUpdate (
    ObjectInstanceHandle objectInstance,
    AttributeHandleSet   attributes,
    byte[]               userSuppliedTag)
  throws
    ObjectInstanceNotKnown,
    AttributeNotDefined,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  public void requestAttributeValueUpdate (
    ObjectClassHandle  objectClass,
    AttributeHandleSet attributes,
    byte[]             userSuppliedTag)
  throws
    ObjectClassNotDefined,
    AttributeNotDefined,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

///////////////////////////////////
// Ownership Management Services //
///////////////////////////////////

  // 7.2
  public void unconditionalAttributeOwnershipDivestiture (
    ObjectInstanceHandle objectInstance,
    AttributeHandleSet   attributes)
  throws
    ObjectInstanceNotKnown,
    AttributeNotDefined,
    AttributeNotOwned,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 7.3
  public void negotiatedAttributeOwnershipDivestiture (
    ObjectInstanceHandle objectInstance,
    AttributeHandleSet   attributes,
    byte[]               userSuppliedTag)
  throws
    ObjectInstanceNotKnown,
    AttributeNotDefined,
    AttributeNotOwned,
    AttributeAlreadyBeingDivested,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 7.6
  public void confirmDivestiture (
    ObjectInstanceHandle objectInstance,
    AttributeHandleSet   attributes,
    byte[]               userSuppliedTag)
  throws
    ObjectInstanceNotKnown,
    AttributeNotDefined,
    AttributeNotOwned,
    AttributeDivestitureWasNotRequested,
    NoAcquisitionPending,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 7.8
  public void attributeOwnershipAcquisition (
    ObjectInstanceHandle objectInstance,
    AttributeHandleSet   desiredAttributes,
    byte[]               userSuppliedTag)
  throws
    ObjectInstanceNotKnown,
    ObjectClassNotPublished,
    AttributeNotDefined,
    AttributeNotPublished,
    FederateOwnsAttributes,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 7.9
  public void attributeOwnershipAcquisitionIfAvailable (
    ObjectInstanceHandle objectInstance,
    AttributeHandleSet   desiredAttributes)
  throws
    ObjectInstanceNotKnown,
    ObjectClassNotPublished,
    AttributeNotDefined,
    AttributeNotPublished,
    FederateOwnsAttributes,
    AttributeAlreadyBeingAcquired,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 7.12
  public AttributeHandleSet
  attributeOwnershipDivestitureIfWanted (
    ObjectInstanceHandle objectInstance,
    AttributeHandleSet   attributes)
  throws
    ObjectInstanceNotKnown,
    AttributeNotDefined,
    AttributeNotOwned,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 7.13
  public void cancelNegotiatedAttributeOwnershipDivestiture (
    ObjectInstanceHandle objectInstance,
    AttributeHandleSet   attributes)
  throws
    ObjectInstanceNotKnown,
    AttributeNotDefined,
    AttributeNotOwned,
    AttributeDivestitureWasNotRequested,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 7.14
  public void cancelAttributeOwnershipAcquisition (
    ObjectInstanceHandle objectInstance,
    AttributeHandleSet   attributes)
  throws
    ObjectInstanceNotKnown,
    AttributeNotDefined,
    AttributeAlreadyOwned,
    AttributeAcquisitionWasNotRequested,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 7.16
  public void queryAttributeOwnership (
    ObjectInstanceHandle objectInstance,
    AttributeHandle      attribute)
  throws
    ObjectInstanceNotKnown,
    AttributeNotDefined,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 7.18
  public boolean
  isAttributeOwnedByFederate (
    ObjectInstanceHandle objectInstance,
    AttributeHandle      attribute)
  throws
    ObjectInstanceNotKnown,
    AttributeNotDefined,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

//////////////////////////////
// Time Management Services //
//////////////////////////////

  // 8.2
  public void enableTimeRegulation (
    LogicalTimeInterval lookahead)
  throws
    TimeRegulationAlreadyEnabled,
    InvalidLookahead,
    InTimeAdvancingState,
    RequestForTimeRegulationPending,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 8.4
  public void disableTimeRegulation ()
  throws
    TimeRegulationIsNotEnabled,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 8.5
  public void enableTimeConstrained ()
  throws
    TimeConstrainedAlreadyEnabled,
    InTimeAdvancingState,
    RequestForTimeConstrainedPending,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 8.7
  public void disableTimeConstrained ()
  throws
    TimeConstrainedIsNotEnabled,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 8.8
  public void timeAdvanceRequest (
    LogicalTime    requestedTime)
  throws
    InvalidLogicalTime,
    LogicalTimeAlreadyPassed,
    InTimeAdvancingState,
    RequestForTimeRegulationPending,
    RequestForTimeConstrainedPending,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 8.9
  public void timeAdvanceRequestAvailable (
    LogicalTime    requestedTime)
  throws
    InvalidLogicalTime,
    LogicalTimeAlreadyPassed,
    InTimeAdvancingState,
    RequestForTimeRegulationPending,
    RequestForTimeConstrainedPending,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 8.10
  public void nextMessageRequest (
    LogicalTime    requestedTime)
  throws
    InvalidLogicalTime,
    LogicalTimeAlreadyPassed,
    InTimeAdvancingState,
    RequestForTimeRegulationPending,
    RequestForTimeConstrainedPending,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 8.11
  public void nextMessageRequestAvailable (
    LogicalTime    requestedTime)
  throws
    InvalidLogicalTime,
    LogicalTimeAlreadyPassed,
    InTimeAdvancingState,
    RequestForTimeRegulationPending,
    RequestForTimeConstrainedPending,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 8.12
  public void flushQueueRequest (
    LogicalTime    requestedTime)
  throws
    InvalidLogicalTime,
    LogicalTimeAlreadyPassed,
    InTimeAdvancingState,
    RequestForTimeRegulationPending,
    RequestForTimeConstrainedPending,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 8.14
  public void enableAsynchronousDelivery()
  throws
    AsynchronousDeliveryAlreadyEnabled,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 8.15
  public void disableAsynchronousDelivery()
  throws
    AsynchronousDeliveryAlreadyDisabled,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 8.16
  public TimeQueryReturn
  queryGALT ()
  throws
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 8.17
  public LogicalTime queryLogicalTime ()
  throws
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 8.18
  public TimeQueryReturn
  queryLITS ()
  throws
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 8.19
  public void modifyLookahead (
    LogicalTimeInterval lookahead)
  throws
    TimeRegulationIsNotEnabled,
    InvalidLookahead,
    InTimeAdvancingState,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 8.20
  public LogicalTimeInterval queryLookahead ()
  throws
    TimeRegulationIsNotEnabled,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 8.21
  public void retract (
    MessageRetractionHandle retractionHandle)
  throws
    InvalidMessageRetractionHandle,
    TimeRegulationIsNotEnabled,
    MessageCanNoLongerBeRetracted,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 8.23
  public void changeAttributeOrderType (
    ObjectInstanceHandle objectInstance,
    AttributeHandleSet   attributes,
    OrderType            orderType)
  throws
    ObjectInstanceNotKnown,
    AttributeNotDefined,
    AttributeNotOwned,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 8.24
  public void changeInteractionOrderType (
    InteractionClassHandle interactionClass,
    OrderType              orderType)
  throws
    InteractionClassNotDefined,
    InteractionClassNotPublished,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

//////////////////////////////////
// Data Distribution Management //
//////////////////////////////////

  // 9.2
  public RegionHandle
  createRegion (DimensionHandleSet dimensions)
  throws
    InvalidDimensionHandle,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 9.3
  public void commitRegionModifications (
    RegionHandleSet regions)
  throws
    InvalidRegion,
    RegionNotCreatedByThisFederate,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 9.4
  public void deleteRegion (
    RegionHandle  region)
  throws
    InvalidRegion,
    RegionNotCreatedByThisFederate,
    RegionInUseForUpdateOrSubscription,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  //9.5
  public ObjectInstanceHandle
  registerObjectInstanceWithRegions (
    ObjectClassHandle             objectClass,
    AttributeSetRegionSetPairList attributesAndRegions)
  throws
    ObjectClassNotDefined,
    ObjectClassNotPublished,
    AttributeNotDefined,
    AttributeNotPublished,
    InvalidRegion,
    RegionNotCreatedByThisFederate,
    InvalidRegionContext,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  public ObjectInstanceHandle
  registerObjectInstanceWithRegions (
    ObjectClassHandle             objectClass,
    AttributeSetRegionSetPairList attributesAndRegions,
    String                        objectName)
  throws
    ObjectClassNotDefined,
    ObjectClassNotPublished,
    AttributeNotDefined,
    AttributeNotPublished,
    InvalidRegion,
    RegionNotCreatedByThisFederate,
    InvalidRegionContext,
    ObjectInstanceNameNotReserved,
    ObjectInstanceNameInUse,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 9.6
  public void associateRegionsForUpdates (
    ObjectInstanceHandle          objectInstance,
    AttributeSetRegionSetPairList attributesAndRegions)
  throws
    ObjectInstanceNotKnown,
    AttributeNotDefined,
    InvalidRegion,
    RegionNotCreatedByThisFederate,
    InvalidRegionContext,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 9.7
  public void unassociateRegionsForUpdates (
    ObjectInstanceHandle          objectInstance,
    AttributeSetRegionSetPairList attributesAndRegions)
  throws
    ObjectInstanceNotKnown,
    AttributeNotDefined,
    InvalidRegion,
    RegionNotCreatedByThisFederate,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 9.8
  public void subscribeObjectClassAttributesWithRegions (
    ObjectClassHandle             objectClass,
    AttributeSetRegionSetPairList attributesAndRegions)
  throws
    ObjectClassNotDefined,
    AttributeNotDefined,
    InvalidRegion,
    RegionNotCreatedByThisFederate,
    InvalidRegionContext,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  public void subscribeObjectClassAttributesPassivelyWithRegions (
    ObjectClassHandle             objectClass,
    AttributeSetRegionSetPairList attributesAndRegions)
  throws
    ObjectClassNotDefined,
    AttributeNotDefined,
    InvalidRegion,
    RegionNotCreatedByThisFederate,
    InvalidRegionContext,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 9.9
  public void unsubscribeObjectClassAttributesWithRegions (
    ObjectClassHandle             objectClass,
    AttributeSetRegionSetPairList attributesAndRegions)
  throws
    ObjectClassNotDefined,
    AttributeNotDefined,
    InvalidRegion,
    RegionNotCreatedByThisFederate,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 9.10
  public void subscribeInteractionClassWithRegions (
    InteractionClassHandle interactionClass,
    RegionHandleSet        regions)
  throws
    InteractionClassNotDefined,
    InvalidRegion,
    RegionNotCreatedByThisFederate,
    InvalidRegionContext,
    FederateServiceInvocationsAreBeingReportedViaMOM,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  public void subscribeInteractionClassPassivelyWithRegions (
    InteractionClassHandle interactionClass,
    RegionHandleSet        regions)
  throws
    InteractionClassNotDefined,
    InvalidRegion,
    RegionNotCreatedByThisFederate,
    InvalidRegionContext,
    FederateServiceInvocationsAreBeingReportedViaMOM,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 9.11
  public void unsubscribeInteractionClassWithRegions (
    InteractionClassHandle interactionClass,
    RegionHandleSet        regions)
  throws
    InteractionClassNotDefined,
    InvalidRegion,
    RegionNotCreatedByThisFederate,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  //9.12
  public void sendInteractionWithRegions (
    InteractionClassHandle      interactionClass,
    ParameterHandleValueMap     parameters,
    RegionHandleSet             regions,
    byte[]                      userSuppliedTag)
  throws
    InteractionClassNotDefined,
    InteractionClassNotPublished,
    InteractionParameterNotDefined,
    InvalidRegion,
    RegionNotCreatedByThisFederate,
    InvalidRegionContext,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  public MessageRetractionReturn
  sendInteractionWithRegions (
    InteractionClassHandle      interactionClass,
    ParameterHandleValueMap     parameters,
    RegionHandleSet             regions,
    byte[]                      userSuppliedTag,
    LogicalTime                 timeStamp)
  throws
    InteractionClassNotDefined,
    InteractionClassNotPublished,
    InteractionParameterNotDefined,
    InvalidRegion,
    RegionNotCreatedByThisFederate,
    InvalidRegionContext,
    InvalidLogicalTime,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 9.13
  public void requestAttributeValueUpdateWithRegions (
    ObjectClassHandle             objectClass,
    AttributeSetRegionSetPairList attributesAndRegions,
    byte[]                        userSuppliedTag)
  throws
    ObjectClassNotDefined,
    AttributeNotDefined,
    InvalidRegion,
    RegionNotCreatedByThisFederate,
    InvalidRegionContext,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

//////////////////////////
// RTI Support Services //
//////////////////////////

  // 10.2
  public ObjectClassHandle
  getObjectClassHandle (
    String objectClassName)
  throws
    NameNotFound,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.3
  public String
  getObjectClassName (
    ObjectClassHandle objectClass)
  throws
    InvalidObjectClassHandle,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.4
  public AttributeHandle
  getAttributeHandle (
    ObjectClassHandle objectClass,
    String            attributeName)
  throws
    InvalidObjectClassHandle,
    NameNotFound,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.5
  public String
  getAttributeName (
    ObjectClassHandle objectClass,
    AttributeHandle   attribute)
  throws
    InvalidObjectClassHandle,
    InvalidAttributeHandle,
    AttributeNotDefined,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.6
  public InteractionClassHandle
  getInteractionClassHandle (
    String interactionClassName)
  throws
    NameNotFound,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.7
  public String
  getInteractionClassName (
    InteractionClassHandle interactionClass)
  throws
    InvalidInteractionClassHandle,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.8
  public ParameterHandle
  getParameterHandle (
    InteractionClassHandle interactionClass,
    String                 parameterName)
  throws
    InvalidInteractionClassHandle,
    NameNotFound,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.9
  public String
  getParameterName (
    InteractionClassHandle interactionClass,
    ParameterHandle        parameterHandle)
  throws
    InvalidInteractionClassHandle,
    InvalidParameterHandle,
    InteractionParameterNotDefined,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.10
  public ObjectInstanceHandle
  getObjectInstanceHandle (
    String objectName)
  throws
    ObjectInstanceNotKnown,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.11
  public String
  getObjectInstanceName (
    ObjectInstanceHandle objectInstance)
  throws
    ObjectInstanceNotKnown,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.12
  public DimensionHandle
  getDimensionHandle (
    String dimensionName)
  throws
    NameNotFound,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.13
  public String
  getDimensionName (
    DimensionHandle    dimensionHandle)
  throws
    InvalidDimensionHandle,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.14
  public long
  getDimensionUpperBound (
    DimensionHandle    dimensionHandle)
  throws
    InvalidDimensionHandle,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.15
  public DimensionHandleSet
  getAvailableDimensionsForClassAttribute (
    ObjectClassHandle objectClass,
    AttributeHandle   attribute)
  throws
    InvalidObjectClassHandle,
    InvalidAttributeHandle,
    AttributeNotDefined,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.16
  public ObjectClassHandle
  getKnownObjectClassHandle (
    ObjectInstanceHandle objectInstance)
  throws
    ObjectInstanceNotKnown,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.17
  public DimensionHandleSet
  getAvailableDimensionsForInteractionClass (
    InteractionClassHandle interactionClass)
  throws
    InvalidInteractionClassHandle,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.18
  public TransportationType
  getTransportationType (
    String transportationName)
  throws
    InvalidTransportationName,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.19
  public String
  getTransportationName (
    TransportationType transportationType)
  throws
    InvalidTransportationType,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.20
  public OrderType
  getOrderType (
    String orderName)
  throws
    InvalidOrderName,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.21
  public String
  getOrderName (
    OrderType orderType)
  throws
    InvalidOrderType,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.22
  public void enableObjectClassRelevanceAdvisorySwitch()
  throws
    FederateNotExecutionMember,
    ObjectClassRelevanceAdvisorySwitchIsOn,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 10.23
  public void disableObjectClassRelevanceAdvisorySwitch()
  throws
    ObjectClassRelevanceAdvisorySwitchIsOff,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 10.24
  public void enableAttributeRelevanceAdvisorySwitch()
  throws
    AttributeRelevanceAdvisorySwitchIsOn,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 10.25
  public void disableAttributeRelevanceAdvisorySwitch()
  throws
    AttributeRelevanceAdvisorySwitchIsOff,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 10.26
  public void enableAttributeScopeAdvisorySwitch()
  throws
    AttributeScopeAdvisorySwitchIsOn,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 10.27
  public void disableAttributeScopeAdvisorySwitch()
  throws
    AttributeScopeAdvisorySwitchIsOff,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 10.28
  public void enableInteractionRelevanceAdvisorySwitch()
  throws
    InteractionRelevanceAdvisorySwitchIsOn,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 10.29
  public void disableInteractionRelevanceAdvisorySwitch()
  throws
    InteractionRelevanceAdvisorySwitchIsOff,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 10.30
  public DimensionHandleSet
  getDimensionHandleSet(RegionHandle region)
  throws
    InvalidRegion,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 10.31
  public RangeBounds
  getRangeBounds(
    RegionHandle    region,
    DimensionHandle dimension)
  throws
    InvalidRegion,
    RegionDoesNotContainSpecifiedDimension,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 10.32
  public void setRangeBounds(
    RegionHandle    region,
    DimensionHandle dimension,
    RangeBounds     bounds)
  throws
    InvalidRegion,
    RegionNotCreatedByThisFederate,
    RegionDoesNotContainSpecifiedDimension,
    InvalidRangeBound,
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 10.33
  public long
  normalizeFederateHandle(
    FederateHandle federateHandle)
  throws
    InvalidFederateHandle,
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.34
  public long
  normalizeServiceGroup(
    ServiceGroup group)
  throws
    InvalidServiceGroup,
    FederateNotExecutionMember,
    RTIinternalError;

   // 10.37
  public boolean
  evokeCallback(
    double minimumWaitSeconds)
  throws
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.38
  public boolean
  evokeMultipleCallbacks(
    double minimumWaitSeconds,
    double maximumWaitSeconds)
  throws
    FederateNotExecutionMember,
    RTIinternalError;

  // 10.39
  public void enableCallbacks()
  throws
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;

  // 10.40
  public void disableCallbacks()
  throws
    FederateNotExecutionMember,
    SaveInProgress,
    RestoreInProgress,
    RTIinternalError;


  //API-specific services
  public AttributeHandleFactory getAttributeHandleFactory()
  throws
    FederateNotExecutionMember;

  public AttributeHandleSetFactory getAttributeHandleSetFactory()
   throws
     FederateNotExecutionMember;

  public AttributeHandleValueMapFactory getAttributeHandleValueMapFactory()
   throws
     FederateNotExecutionMember;

  public AttributeSetRegionSetPairListFactory getAttributeSetRegionSetPairListFactory()
   throws
     FederateNotExecutionMember;

  public DimensionHandleFactory getDimensionHandleFactory()
   throws
     FederateNotExecutionMember;

  public DimensionHandleSetFactory getDimensionHandleSetFactory()
   throws
     FederateNotExecutionMember;

  public FederateHandleFactory getFederateHandleFactory()
   throws
     FederateNotExecutionMember;

  public FederateHandleSetFactory getFederateHandleSetFactory()
   throws
     FederateNotExecutionMember;

  public InteractionClassHandleFactory getInteractionClassHandleFactory()
   throws
     FederateNotExecutionMember;

  public ObjectClassHandleFactory getObjectClassHandleFactory()
   throws
     FederateNotExecutionMember;

  public ObjectInstanceHandleFactory getObjectInstanceHandleFactory()
   throws
     FederateNotExecutionMember;

  public ParameterHandleFactory getParameterHandleFactory()
   throws
     FederateNotExecutionMember;

  public ParameterHandleValueMapFactory getParameterHandleValueMapFactory()
   throws
     FederateNotExecutionMember;

  public RegionHandleSetFactory getRegionHandleSetFactory()
   throws
     FederateNotExecutionMember;

  public String getHLAversion();

// TMN: Dummy
class ObjectInstanceHandle {}
class ObjectClassHandle    {}

}
//end RTIambassador


