//File: ParameterHandleValueMap.java
package hla.rti1516;

/**
Keys are ParameterHandles; values are byte[].
All operations are required, none optional.
Null mappings are not allowed.
put(), putAll(), and remove() should throw IllegalArgumentException to enforce
types of keys and mappings.
 */
public interface ParameterHandleValueMap
  extends java.util.Map, Cloneable, java.io.Serializable {
}
//end ParameterHandleValueMap
