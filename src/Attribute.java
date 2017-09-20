/**
 * This class holds information about the message, the source and destination of each node.
 *
 */
public class Attribute {
    private Node currentNode;
    private Node DestinationNode;
    private int numOfHops;
    private int numOfPackets;
    private Strategy strategy;
    private String message;
	private int attributeNum;
    /**
     * Attribute constructor
     * @param message Message stored in the node.
     * @param currentNode Current Node
     * @param DestinationNode Destination Node
     */
    public Attribute(String message, Node currentNode, Node DestinationNode, int nbofattributes){
        this.message= message;
        this.numOfHops= 0;
        this.numOfPackets=0;
        this.currentNode = currentNode;
        this.DestinationNode = DestinationNode;
        this.attributeNum = nbofattributes;
    }
    
    /**
     * @return Increments the number of hops by one.
     */
    public void incrementHops(){
        numOfHops++;
    }
    
    /**
     * @return Increments the number of packets sent by one.
     */
    public void incrementPackets(){
        this.numOfPackets++;
    }
    
    /**
     * 
     * @return Returns the current message stored in the specified node.
     */
    public String getMessage(){
        return this.message;
    }
    
    /**
     * 
     * @return Returns the current node.
     */
    public Node getCurrentNode(){
        
        return currentNode;
    }
    
    /**
     * 
     * @param currentNode Node to be set as the current node.
     * @return Set the specified parameter to become the current node.
     */
    public void setCurrentNode(Node currentNode){
        this.currentNode = currentNode;
        
    }
    
    /**
     * 
     * @return Returns the destination node.
     */
    public Node getDestinationNode(){
        
        return DestinationNode;
    }
    
    public void setDestination(Node DestinationNode){
		
		this.DestinationNode= DestinationNode;
	}
    
    /**
     * 
     * @return Returns the total number of hops.
     */
    public int getNumOfHops(){
        
        return numOfHops;
    }
    
    public int getnum(){
    	return this.attributeNum;
    	
    }
    
    /**
     * 
     * @return Returns the total number of packets sent.
     */
    public int getNumOfPackets(){
        
        return numOfPackets;
    }
    
    /**
     * @return Returns a string representing which node the message is at.
     */
    @Override
    public String toString(){
        return "Message " + getnum()+ ": message is at Node " + currentNode.getName() +" now" ;
        
    }
    
    /**
     * Sets the searching algorithm.
     */
    public void setStrategy(){
    	//remember to change this in milestone two
    	strategy=new RandomRoutingAlgorithm(this);
    	
    	
    }
    
    
    /**
     * Steps through the nodes.
     */
    public void step(){
    	
    	setStrategy();
    	strategy.algorithmInterface();
    	if(currentNode==DestinationNode){
    		System.out.println("Packet has reached the destination");
    		
    	}else{
    		//System.out.println("Packet has reached the destination");
    	}
    }
}
