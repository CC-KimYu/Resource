
//File: FederateHandleSet.java

package hla.rti1516;

/**
All Set operations are required, none are optional.
add() and remove() should throw IllegalArgumentException if the argument is not
a FederateHandleHandle.
addAll(), removeAll() and retainAll() should throw IllegalArgumentException if
the argument is not a FederateHandleSet
*/

public interface FederateHandleSet
  extends java.util.Set, java.io.Serializable, Cloneable {
}
//end FederateHandleSet

