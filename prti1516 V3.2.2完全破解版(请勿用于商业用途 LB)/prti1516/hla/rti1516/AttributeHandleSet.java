
//File: AttributeHandleSet.java

package hla.rti1516;

/**
All Set operations are required, none are optional.
add() and remove() should throw IllegalArgumentException if the argument is not
an AttributeHandle.
addAll(), removeAll() and retainAll() should throw IllegalArgumentException if
the argument is not an AttributeHandleSet
*/

public interface AttributeHandleSet
  extends java.util.Set, Cloneable, java.io.Serializable {
}

//end AttributeHandleSet


