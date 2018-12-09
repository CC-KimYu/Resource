
//File: RegionHandleSet.java

package hla.rti1516;

/**
All Set operations are required, none are optional.
add() and remove() should throw IllegalArgumentException if the argument is not
a RegionHandle.
addAll(), removeAll() and retainAll() should throw IllegalArgumentException if
the argument is not a RegionHandleSet
*/

public interface RegionHandleSet
  extends java.util.Set, Cloneable, java.io.Serializable {
}
//end RegionHandleSet

