import java.util.Stack;
import java.util.ArrayList;

public class Process_Resource_Tree 
{
	private Process_Resource_Node root;//the root node for the tree
	private char[][] table;//the job resource table 
	
	Process_Resource_Node[] processNodes;//array of process nodes
	Process_Resource_Node[] resourceNodes;//array of resource nodes
	
	private boolean deadLockFound = false;//determines if there is a deadlock
	
	Stack<Process_Resource_Node> cycle = new Stack<Process_Resource_Node>();//holds the nodes that are currently being traversed
	ArrayList<String> processes = new ArrayList<String>();
	
	/**
	 * default constructor
	 */
	public Process_Resource_Tree()
	{
		//calls superclass constructor
		super();
	}
	
	/**
	 * Overloaded constructor
	 * @param table
	 */
	public Process_Resource_Tree(char[][] table)
	{
		//calls default constructor
		this();
		
		//instantiates the local tables size
		for(int i = 0; i < table.length; i++)
		{
			this.table = new char[table.length][table[i].length];
		}
		
		//instantiates process and resource size
		resourceNodes = new Process_Resource_Node[table[0].length];
		processNodes = new Process_Resource_Node[table.length];
		
		//copies over the job/process table data
		for(int i=0; i<table.length; i++)
		{
			for(int j=0; j< table[i].length; j++)
			{
				this.table[i][j] = table[i][j];
			}
		}
		
		//generates all the process and resource nodes
		generateNodes();
	}
	
	/**
	 * Accessor for the root node
	 * @return	root	the root/starting node of the tree
	 */
	public Process_Resource_Node getRoot()
	{
		return root;
	}
	
	/**
	 * Accessor for the array of process nodes
	 * @return	ProcessNodes	an array of processes for the job/resource table
	 */
	public Process_Resource_Node[] getProccess()
	{
		return processNodes;
	}
	
	/**
	 * Mutator for the root node
	 * @param rootNode	the new node to set the root too
	 */
	public void setRoot(Process_Resource_Node rootNode)
	{
		root = rootNode;
	}
	
	/**
	 * Counts for a given node the number of children that it will have
	 * @param node		the node the determine number of children for
	 * @return			the number of children which acts as the length for children array
	 */
	private int getNumChildren(Process_Resource_Node node)
	{
		int numChildren = 0;
		
		//if the node is a process node
		if(node.getType() == ProcRes.PROCESS)
		{
			for(int i = 0; i < table[node.getID()-1].length; i++)
			{
				//count all of the resources the proccess holds
				if(table[node.getID()-1][i] == 'x')
				{
					numChildren++;
				}
			}
		}
		
		//if the node is a resource node
		else
		{
			for(int i = 0; i < table.length; i++)
			{
				//count all of the proccesses waiting for the resource
				if(table[i][node.getID()-1] == 'w')
				{
					numChildren++;
				}
			}
		}
		return numChildren;
	}
	
	/**
	 * Creates process and resource nodes based on the table passed in
	 */
	public void generateNodes()
	{
		//generates all of the nodes of type process
		for(int i = 0; i < processNodes.length; i++)
		{
			//sets type to proccess
			processNodes[i] = new Process_Resource_Node((i+1), ProcRes.PROCESS);
			
			//generates the number of children for that node
			//processNodes[i].setChildrenSize(getNumChildren(processNodes[i]));
			
			//locates the indeces for those children
			processNodes[i].setChildIndex(new int[getNumChildren(processNodes[i])]);
		}
		
		//generates all of the nodes of the type resource 
		for(int i = 0; i < resourceNodes.length; i++)
		{
			//sets type to resource
			resourceNodes[i] = new Process_Resource_Node((i+1), ProcRes.RESOURCE);
			
			//locates the indeces for those children
			resourceNodes[i].setChildIndex(new int[getNumChildren(resourceNodes[i])]);
		}
	}
	
	/**
	 * creates an integer array that contains the indeces for all of the parameter nodes children nodes
	 * @param node		the node to determine the indeces for
	 * @return			an int array containing node indeces
	 */
	public int[] getChildrenIndex(Process_Resource_Node node)
	{
		int[] childrenIndex = new int[getNumChildren(node)];
		//if the current node type is a process
		if(node.getType() == ProcRes.PROCESS)
		{
			int indexIndex = 0;
			for(int i = 0; i < table[0].length; i++)
			{
				//search for each resource that is being held
				if(table[node.getID()-1][i] == 'x')
				{
					childrenIndex[indexIndex] = i;
					indexIndex++;
				}
			}
		}
		//if the current node is a resource
		else
		{
			int indexIndex = 0;
			for(int i = 0; i < table.length; i++)
			{
				//search for each process that is waiting for it
				if(table[i][node.getID()-1] == 'w')
				{
					childrenIndex[indexIndex] = i;
					indexIndex++;
				}
			}
		}
		return childrenIndex;
	}
	
	/**
	 * Accessor for deadLockFound
	 * @return	deadLockFound	false if no deadlock, true if deadlock
	 */
	public boolean getDeadLockDetected()
	{
		return deadLockFound;
	}
	
	/**
	 * Builds a tree starting from the root node.
	 * Mixes iteration and recursion to traverse all of the paths
	 * @param node
	 */
	public ArrayList<String> buildTree(Process_Resource_Node node)
	{
		//checks if the current node is already in the search path
		//represents deadlock presence
		if(cycle.contains(node))
		{
			//adds the node to the process resource cycle
			processes.add(node.toString());
			deadLockFound = true;//sets deadlock to true
			System.out.println(new DeadLockException().getMessage());//prints out
			processes.remove(processes.size()-1);//removes the second occurence of last node for presentation
			return processes;//returns arraylist of node information
		}

		//If there is no deadlock
		if(!deadLockFound)
		{
			cycle.push(node);//add the current node to the stack
			processes.add(node.toString());//add the node information to the 
		}
		
		//iterate through all of the current node's children nodes
		for(int i = 0; i < node.getChildIndex().length; i++)
		{
			//if its a process node
			if(node.getType() == ProcRes.PROCESS)
			{
				//recursively searches through each child node
				buildTree(resourceNodes[getChildrenIndex(node)[i]]);
				
				//breaks if a deadlock is detected
				if(deadLockFound)
				{
					break;
				}
			}
			//if its a resource node
			else
			{
				//recursivly searches each child node
				buildTree(processNodes[getChildrenIndex(node)[i]]);
				
				//breaks if a deadlock is detected
				if(deadLockFound)
				{
					break;
				}
			}
		}
		/*
		 *	If this point is reached, the current node is not part of a deadlock and is removed from the  
		 */
		cycle.pop();
		return processes;
	}
}