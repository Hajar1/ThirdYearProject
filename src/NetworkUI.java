import java.util.Random;
import java.util.Scanner;

/**
 * Class Network UI is responsible for allowing the user to edit, display, and run the network simulator *
 */
public class NetworkUI {
	private static Scanner scanner;			// For reading user input
	private static Model model;				// Top level view of our graph/network
	private static boolean allDone = false;	// To exit the program when it's done
	private static Node temp1, temp2;		// Temporary Node variables which might be used later
	private static char char1, char2; 		// Temporary char variables which might be used later 

	/**
	 * Constructor for Network UI that initializes the scanner and the model
	 */
	public NetworkUI(){
		scanner = new Scanner (System.in);
		// Make the network and call the Model function to make the graph
		model = new Model();
	}

	/**
	 * Displays the Nodes present in the network and their neighbors
	 */
	public static void displayNetwork(){
		System.out.println(model.toString());
		model.getListOfNodes().forEach(node -> System.out.println(node.toString()));
	}
	
	/**
	 * Displays the Nodes present in the network, their neighbors and the current model metrics
	 */
	public static void displayNetworkAndMetrics(){
		displayNetwork();
		System.out.println("The Avg number of Hops = " + model.getHopsAverage() + "\n");
		System.out.println("The total number of Packets = " + model.getNumOfPackets() + "\n");
	}

	
	/**
	 * Checks and connects the Nodes if the Connect 2 nodes command was selected 
	 */
	public static void connectNodes(){
		System.out.println("Connecting 2 nodes - will reprompt if nodes don't exist!\n");
		do {
			System.out.print("Enter the first node: ");	
			char1 = scanner.next().charAt(0);
			temp1 = model.findNode(char1);
			System.out.print("Enter the second node: ");
			char2 = scanner.next().charAt(0);
			temp2 = model.findNode(char2);
		} while(temp1 == null || temp2 == null);
		if(model.connect(char1, char2))
			System.out.println("Nodes " + temp1.getName() + " and " + temp2.getName() + " are now connected\n");
		else
			System.out.println("Nodes are already connected!\n");
	}

	
	
	/**
	 * Provides menu for performing edits to the Network model or displaying it
	 */
	public static void editNodes(){
		while(true){
			System.out.print("Would you like to:\n"
					+ "		1- Connect 2 nodes\n"
					+ "		2- Disconnect 2 nodes\n"
					+ "		3- Add a node\n"
					+ "		4- Remove a node\n"
					+ "		5- Display network\n"
					+ "Your choice: ");
			switch(scanner.next().charAt(0)){	
			case '1':	// Connect 2 nodes
				connectNodes();
				break;
			
			case '2':	// Disconnect 2 nodes		
				do {
					System.out.println("Disonnecting 2 nodes - will reprompt if nodes don't exist!\n");
					System.out.print("Enter the first node: ");	
					char1 = scanner.next().charAt(0);
					temp1 = model.findNode(char1);
					System.out.print("Enter the second node: ");
					char2 = scanner.next().charAt(0);
					temp2 = model.findNode(char2);
				} while(temp1 == null || temp2 == null);
				if(model.disconnect(char1, char2))
					System.out.println("Nodes " + temp1.getName() + " and " + temp2.getName() + " are now disconnected\n");	
				else
					System.out.println("Nodes weren't connected to disconnect them!\n");
				break;
				
			case '3':	// Add a node
				System.out.println("Adding a node\n");		
				System.out.print("Enter the name of the Node to insert: ");
				if(model.insertNode(scanner.next().charAt(0)))
					System.out.println("Node added!");
				else
					System.out.println("The node requested already exists!");
				break;

			case '4' :	// Remove a node
				System.out.println("Removing a node\n");
				System.out.print("Enter the name of the Node to delete: ");
				if(model.deleteNode(scanner.next().charAt(0)))
					System.out.println("Node deleted!");
				else
					System.out.println("Node requested for deletion doesn't exist!");
				break;

			case '5': 	// prints all the nodes
				displayNetwork();			
			break;
			
			default:	// Invalid entry
				System.out.println("That's chill, no edits performed\n");
				break;
			}
			System.out.print("Type 'Y' to perform more edits to the network?: ");
			if(scanner.next().toUpperCase().charAt(0) == 'N')
				break;
		}
	}

	
	/**
	 * Provides menu for choosing an algorithm and sets the algorithm in the model
	 */
	public static boolean algorithms() {
		// Choose the strategy and finally run
		System.out.print("Choose one of the following strategies:\n"
				+ "		1- Random Routing Algorithm\n"
				+ "		2- Flooding Routing Algorithm\n"
				+ "		3- Shortest path Algorithm\n"
				+ "		4- Team Algorithm \n"
				+ "Your choice: ");
		switch(scanner.next().charAt(0)){	
		case '1':
			//model.getListOfNodes().forEach(node-> node.getAttributes().forEach( attribute -> attribute.setStrategy()));
			return true;
			
		default:
			System.out.println("Algorithm not implemented yet\n");
			return false;
		}
	}

	
	/**
	 * Runs the Network Simulator and and calls all the appropriate UI functions
	 * @param args
	 */
	public static void main (String[] args){
		NetworkUI network = new NetworkUI();
		System.out.println("Welcome to the Awesome Network Routing Simulator\n");

		// Want to populate the graph for quick testing purposes
		System.out.print("Type 'Y' to populate the network for quick testing purposes?: ");
		if(scanner.next().toUpperCase().charAt(0) == 'Y')
			model.testPopulate();		
		else
			System.out.println("Continuing normally");

		System.out.println("\nPrinting current Network Map");
		NetworkUI.displayNetwork();	// prints all the nodes

		do{
			// Ask the User for edits
			System.out.print("\nType 'Y' to perform edits to the network?: ");
			if(scanner.next().toUpperCase().charAt(0) == 'Y')
				editNodes();
			else
				System.out.println("No edits performed!\n");

			/**
			 * Not needed until the next the next milestone
			 * 
			// Making the Attribute
			System.out.println("\nLet's make a attribute (the packet)!\n");
			do {
				System.out.print("Enter starting node: ");	
				char1 = scanner.next().charAt(0);
				temp1 = model.findNode(char1);
				System.out.print("Enter destination node: ");
				char2 = scanner.next().charAt(0);
				temp2 = model.findNode(char2);	
				if(temp1 == null || temp2 == null)
					System.out.println("The nodes that you entered are not in the graph\n");
			} while(temp1 == null || temp2 == null);
			System.out.print("Enter attributes' message: ");
			model.createAttribute(scanner.next(), char1, char2);	// (String message, Node currentNode, Node DestinationNode)
			System.out.println("Attribute ready to rock and roll!\n");
			*/
			
			System.out.print("Rate of packet generation is (1 attribute/rate steps): ");
			model.setRate(scanner.nextInt());

			// Choose the strategy and finally run
			while(!algorithms());
			
			System.out.print("Type 'S' to step through the simulation (20 steps)");
			Random index = new Random();
			model.createAttribute("hi",model.getListOfNodes().get(index.nextInt(model.getListOfNodes().size())).getName(),model.getListOfNodes().get(index.nextInt(model.getListOfNodes().size())).getName());
			//Create a Attribute
			for(int step = 0; step < 20; step++){	// Hard coded steps - number of steps the simulations runs for
				if(scanner.next().toUpperCase().charAt(0) == 'S'){
				model.stepSimulate();			// Steps through the algorithm selected
				displayNetworkAndMetrics();
				}
			}
			

			// Any last words?
			displayNetworkAndMetrics();
			System.out.print("End of Routing Algorithm, type 'Y' to do another compilation?: ");
			if(scanner.next().toUpperCase().charAt(0) == 'N')
				allDone = true;
			
		} while(!allDone);
		
		scanner.close();
	}
	
}
