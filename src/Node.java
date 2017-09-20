/**
 * This class has information about the Router node. It contains an array list of all the neighbours in the router for each router node and is primarily responsible for adding and removing them.
 */
import java.util.ArrayList;

public class Node {

	private char name;
	private ArrayList<Node> neighbours;
	private ArrayList<Attribute> attributes;
	private String s;
	private String message;

	/**
	 * Node Constructor
	 * @param name Name of the specified node.
	 */
	public Node(char name){
		this.name = name;
		
		neighbours = new ArrayList<Node>();
		attributes = new ArrayList<Attribute>();
	}
	
	/**
	 * 
	 * @return Returns the name of the node.
	 */
	public char getName() {
		return name;
	}

	/**
	 * 
	 * @return Returns an arraylist containing all a node's neighbors.
	 */
	public ArrayList<Node> getNeighbours() {
		return neighbours;
	}

	/**
	 * 
	 * @param neighbour
	 * @Return Adds a neighbor to the specified node.
	 */
	public void addNeighbour(Node neighbour) {
		neighbours.add(neighbour);
	}
	
	/**
	 * 
	 * @param neighbour
	 * @return Removes the specified node from the list of neighbors that the specified node has.
	 */
	public void removeNeighbour(Node neighbour){
		neighbours.remove(neighbour);
	}

	/**
	 * 
	 * @return Returns the message stored in the node.
	 */
	public String getMessage(){
		return message;
	}
	
	/**
	 * 
	 * @return Removes all a node's neighbors and returns a node with no neighbors.
	 */
	public Node removeAllNeighbours(){
        for(Node n: neighbours){
            n.removeNeighbour(this);
        }
        this.neighbours.clear();
        
        return this;
    }
	
	/**
	 * 
	 * @param message The message to be stored in the specified node.
	 * @return Set the message for the specified node.
	 */
	public void setMessage(String message){
		this.message= message;
	}
	
	
	
	
	/**
	 * @return Returns a string representing the node and all of its neighbors.
	 */
	@Override
	public String toString(){
		
		s= "Node" + name + ": neighbors{" ;
		for(int i=0; i<neighbours.size();i++){
			
			if(neighbours.size()==0){
				s = " ";
			}else if(i == neighbours.size()-1){
				s+= neighbours.get(i).getName();
			
			}else{
				s+= neighbours.get(i).getName() + "," ;
			}
		}
		s+= "}";
		
		s+= "\t\t Messages{";
			for(int i=0; i<attributes.size();i++){
			
			if(attributes.size()==0){
				s = " ";
			}else if(i == attributes.size()-1){
				s+= attributes.get(i).getnum();
			
			}else{
				s+= attributes.get(i).getnum() + "," ;
			}
		}
		
			s+= "}";
				
				
		return s ;
	}
	
	public void addAttribute(Attribute attribute){
		attributes.add(attribute);
		
	}
	
	public void removeAttribute(Attribute attribute){
		attributes.remove(attribute);
	}
	
	public ArrayList<Attribute> getAttributes(){
		return attributes;
	}
	
	//loop thru all atrributes
		//if currentattribute destination == ending destination
		//remove from the attribute list
		
	/*public void att_complete(){
		if(attributes.isEmpty() == false){
			for(int i=0 ; i<attributes.size();i++){
				if (attributes.get(i).getCurrentNode() == attributes.get(i).getDestinationNode()) attributes.remove(i);
			}		
		}
	}
*/
	
	/**
	 * Steps through the nodes.
	 *
	 */
	public void step(){
		
		attributes.forEach(attribute -> attribute.step());
		//att_complete();
	}
	
}
