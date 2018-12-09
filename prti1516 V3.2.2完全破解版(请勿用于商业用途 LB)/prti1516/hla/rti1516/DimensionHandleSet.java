
//File: DimensionHandleSet.java

package hla.rti1516;

/**
All Set operations are required, none are optional.
add() and remove() should throw IllegalArgumentException if the argument is not
a DimensionHandle.
addAll(), removeAll() and retainAll() should throw IllegalArgumentException if
the argument is not a DimensionHandleSet
*/

public interface DimensionHandleSet
  extends java.util.Set, Cloneable, java.io.Serializable {
}
//end DimensionHandleSet

