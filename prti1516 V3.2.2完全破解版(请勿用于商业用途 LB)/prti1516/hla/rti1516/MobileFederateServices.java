
//File: MobileFederateServices.java

package hla.rti1516;

/**
 * Conveys the interfaces for all services that a federate
 * must supply and which may not execute in the federate's
 * space.
 *
 */
public final class MobileFederateServices
    implements java.io.Serializable
{
        public hla.rti1516.LogicalTimeFactory _timeFactory;
        public hla.rti1516.LogicalTimeIntervalFactory _intervalFactory;

    /**
     * @param timeFactory hla.rti1516.LogicalTimeFactory
     * @param intervalFactory hla.rti1516.LogicalTimeIntervalFactory
     */
    public MobileFederateServices (
      LogicalTimeFactory timeFactory,
      LogicalTimeIntervalFactory intervalFactory)
    {
            _timeFactory = timeFactory;
            _intervalFactory = intervalFactory;
    }
}

//end MobileFederateServices


