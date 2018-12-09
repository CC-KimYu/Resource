package hla.rti1516.jlc;

/**
 * Interface used to populate arrays.
 */
public interface DataElementFactory
{
   /**
    * Creates an element appropriate for the specified index.
    *
    * @param index Position in array that this element will take.
    * @return Element
    */
   public DataElement createElement(int index);
}
