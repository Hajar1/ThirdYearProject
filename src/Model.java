/**
 * This class holds information about the logic of the entire simulator system.  
 */
import java.util.ArrayList;
import java.util.Random;

public class Model {	
	private ArrayList<Node> listOfNodes;
	private ArrayList<Integer> listOfHops;
	//	private ArrayList<Attribute> attributes;
	//private Strategy strategy;
	private String s;
	private int rate;
	private int Nbofpackets =0;
	private int currentNumOfHops =0 ;
	private int nbofattributes = 0  ;

	/**
	 * Model Constructor.
	 */
	public Model() {		
		listOfNodes = new ArrayList<Node>();
		listOfHops = new ArrayList<Integer>();
		//rate = 3;
	}

	/**
	 * Constructor with a preset list of nodes given with their connection.
	 * @param listOfNodes List of nodes
	 */
	public Model(ArrayList<Node> listOfNodes) {
		this(listOfNodes,null,null);
	}

	/**
	 * Constructor with both the list and the specific attribute needed.
	 * @param listOfNodes List of nodes
	 * @param attribute Attribute
	 */
	public Model(ArrayList<Node> listOfNodes, ArrayList<Attribute> attribute) {
		this(listOfNodes,attribute,null);
	}

	/**
	 * Constructor with the strategy decision added to it.
	 * @param listOfNodes List of Nodes
	 * @param attribute Attribute
	 * @param strategy Strategy
	 */
	
	public Model(ArrayList<Node> listOfNodes,ArrayList<Attribute> attribute, Strategy strategy) {
		this.listOfNodes = listOfNodes;
		//	this.attributes = attribute;
		//	this.strategy = strategy;
	}

	/**
	 * Calculate the average nb of hops so we can output it at the end.
	 * @return Returns the the total number of hops.
	 */
	public double getHopsAverage(){
		double Totalnbofhops =0;
		if(listOfHops.isEmpty()) return 0;
		for(Integer hops:listOfHops)
			Totalnbofhops += hops;
		return Totalnbofhops/(double)listOfHops.size();
	}

	//
	/**
	 * @param name Name of node.
	 * @return Loops through all of it s nodes until it find a match and then returns it.
	 */
	public Node findNode(char name){
		Node tempNode = null;
		for(Node n:listOfNodes)
			if(n.getName() == Character.toUpperCase(name)) tempNode = n;
		return tempNode;
	}

	/**
	 * Create an attribute with specific message to be send and start and end positions
	 * @param message Message at the node.
	 * @param start Current node.
	 * @param end Destination node.
	 */
	public void createAttribute(String message,char start,char end){
		incrementNBofattributes();
		Node currentNode =findNode(start);
		Node DestinationNode= findNode(end);
		currentNode.addAttribute(new Attribute(message,currentNode,DestinationNode,getNumofattributes()));
		//	listOfNodes.forEach(node -> node.getAttributes().add(new Attribute(message,currentNode,DestinationNode,getNumofattributes())));
		//	attributes.add( new Attribute(message,currentNode,DestinationNode));
	}

	/**
	 * Increments the number of attributes by one.
	 */
	public void incrementNBofattributes(){
		this.nbofattributes++;
	}
	
	/**
	 * Checks the attributes list.
	 */
	public void att_complete(){
		for(int j=0; j<listOfNodes.size();j++){
		if(listOfNodes.get(j).getAttributes().isEmpty() == false){
			for(int i=0 ; i<listOfNodes.get(j).getAttributes().size();i++){
				if (listOfNodes.get(j).getAttributes().get(i).getCurrentNode() == listOfNodes.get(j).getAttributes().get(i).getDestinationNode()){
					listOfHops.add(listOfNodes.get(j).getAttributes().get(i).getNumOfHops());
					listOfNodes.get(j).getAttributes().remove(i);
				}
				}
			}		
		}
	}
		


	


	/**
	 * Search will first set the attribute to the specified strategy that we have
	 * then we'll call on the strategy the algorithm interface making it search for the specific
	 * node that we need at the end we get the number of hops from the search
	 * and then add it to the list of hops so we can calculate the average later.
	 */
	public void stepSimulate(){
		Random index = new Random();
		if(!listOfNodes.isEmpty())	{listOfNodes.forEach(node -> node.step());
		currentNumOfHops++;
		System.out.println("Current number of hops: " +this.currentNumOfHops);
		//	listOfHops.add(strategy.setAttribute(attributes).algorithmInterface().getNumOfHops());
		att_complete();
		
		if(currentNumOfHops% rate == 0){
			//currentNumOfHops =0;
			System.out.print("Adding a new message\n");
			Nbofpackets++;
			System.out.print("Incremented number of attributes\n");
			createAttribute("hi",listOfNodes.get(index.nextInt(listOfNodes.size())).getName(),listOfNodes.get(index.nextInt(listOfNodes.size())).getName());
		}


		}

		else{ System.out.println(" You havent chosen a specific strategy yet. can 't execute your search."); }
	}

	/**
	 * 
	 * @return Returns the number of attributes.
	 */
	public int getNumofattributes(){
		return this.nbofattributes;

	}


	/**
	 * 
	 * @return Returns the number of packets.
	 */
	public int getNumOfPackets(){

		//listOfNodes.forEach( node -> Nbofpackets += node.getAttributes().size());
		return Nbofpackets;	
	}



	/**
	 * @param A First node.
	 * @param B Second node.
	 * @return Checks if two nodes are neighbors then connect them both ways.
	 */
	public boolean connect(char A, char B){
		//check for: make sure that the two nodes aren't already connected
		if(findNode(A).getNeighbours().contains(findNode(B)))
			//System.out.println("Nodes are already connected");
			return false;
		else{		findNode(A).addNeighbour(findNode(B));
		findNode(B).addNeighbour(findNode(A));
		return true;}
	}


	//
	/**
	 * 
	 * @param A First node.
	 * @param B Second node.
	 * @return Disconnects both nodes from each other.
	 */
	public boolean disconnect(char A, char B){
		if(findNode(A).getNeighbours().contains(findNode(B))){
			findNode(A).removeNeighbour(findNode(B));
			findNode(B).removeNeighbour(findNode(A));
			return true;
		}else{return false;}

	}


	/**
	 * Sets the rate of generation
	 * @param Rate Rate of generation.
	 */
	public void setRate(int Rate){
		this.rate = Rate;


	}


	/*public void setSearchStrategy(Strategy strategy){
		this.strategy=strategy;
	}*/

	//
	/**
	 * Prints out the graph of nodes that we have.
	 */
	@Override
	public String toString(){
		s= "Graph: Contains the following Nodes {";
		for(int i=0; i<listOfNodes.size();i++){
			if(listOfNodes.size()==0)
				s = " ";
			else if(i == listOfNodes.size()-1)
				s+= listOfNodes.get(i).getName();
			else
				s+= listOfNodes.get(i).getName() + ",";
		}
		s+= "}";
		return s;
	}


	/**
	 * 
	 * @return Returns a list of all the nodes.
	 */
	public ArrayList<Node> getListOfNodes(){
		return listOfNodes;
	}


	/**
	 * 
	 * @param c Name of the node.
	 * @return Inserts the specified node into the graph.
	 */
	public boolean insertNode(char c){
		// Check if node already exists
		if(findNode(c) == null){
			listOfNodes.add(new Node(Character.toUpperCase(c)));
			return true;
		}
		else
			return false;	
	}


	/**
	 * 
	 * @param c Name of the node.
	 * @return Deletes the specified node from the graph.
	 */
	public boolean deleteNode(char c){
		// Check if node doesn't exist
		if(findNode(c) == null) 
			return false;
		else{
			listOfNodes.remove(findNode(Character.toUpperCase(c)).removeAllNeighbours());
			return true;
		}
	}


	/**
	 * Inserts a set of predefined nodes into the graph and connects them.
	 */
	public void testPopulate(){
		insertNode('A');
		insertNode('B');
		insertNode('C');
		insertNode('D');
		insertNode('E');
		connect('A','B');
		connect('A','C');
		connect('A','E');
		connect('C','D');
		connect('B','D');
		connect('B','E');
	}

}
