
/**
 * This class is an abstract class that is implemented by all the algorithm subclasses.
 *
 */
public abstract class Strategy {
	
	protected Attribute attribute;

	public abstract Attribute algorithmInterface();
	/**
	 * 
	 * @param attribute Information about the node.
	 * @return Returns the specified strategy.
	 */
	public Strategy setAttribute(Attribute attribute){	
		this.attribute = attribute;
		return this;
	}
	
	/**
	 * Gets the attribute.
	 * @return Returns the attribute of the current node.
	 */
	public Attribute getAttribute() {
		return attribute;
	} 
	
}
