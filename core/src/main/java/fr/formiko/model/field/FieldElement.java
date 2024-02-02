package fr.formiko.model.field;

/**
 * A FieldElement represents the objects that are on the board. It is a base class for more
 * specifics elements that will constitute the Field.
 */
public abstract class FieldElement {

  /**
   *  Location of this FieldElement
   */
  private final int location;

    /**
     * Name of this FieldElement
     */
  private final String name;

  /**
   * Constructor of the class FieldElement
   * @param location the final location of this FieldElement
   * @param name the final name of this FieldElement
   */
  public FieldElement(int location, String name)
  {
    this.location = location;
    this.name = name;
  }

  /**
   * Gets the location of this FieldElement
   * @return the attribute location of this FieldElement
   */
  public int getLocation()
  {
    return this.location;
  }

  /**
   * Gets the name of this FieldElement
   * @return the attribute name of this FieldElement
   */
  public String getName()
  {
    return this.name;
  }
}
