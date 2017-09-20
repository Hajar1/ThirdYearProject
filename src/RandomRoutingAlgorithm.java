/**
 * Class of Random Routing Algorithm 
 */
import java.util.Random;


public class RandomRoutingAlgorithm extends Strategy {
	private Random index;
	
	/**
	 * Constructor of Random Routing Algorithm 
	 * @param attribute Information about the node. E.g. message
	 */
	public RandomRoutingAlgorithm(Attribute attribute){
		System.out.println("Message is starting from " + attribute.getCurrentNode().getName()); // first print
		this.attribute=attribute;	
	}
	
	/**
	 * Implementation of the Random Routing Algorithm.
	 */
	public Attribute algorithmInterface(){
		
		index = new Random();
		attribute.setCurrentNode(attribute.getCurrentNode().getNeighbours().get(index.nextInt(getAttribute().getCurrentNode().getNeighbours().size())));
		attribute.incrementHops();
		attribute.incrementPackets();
		System.out.println(getAttribute().toString());
		attribute.getCurrentNode().setMessage(getAttribute().getMessage());
	//	System.out.println("Message in Dest:" + getAttribute().getDestinationNode().getMessage()); // Destination print
		return attribute;
	}
}
