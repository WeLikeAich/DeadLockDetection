public class Process_Resource_Node
{
	private int[] childIndex;
	private ProcRes type;
	private int id;
	
	/**
	 * Default Constructor for Process/Resource Node
	 */
	public Process_Resource_Node()
	{
		super();
	}
	
	/**
	 * Overloaded constructor for Process/Resource Node, inserting id, parent and node type
	 * @param value		node id
	 * @param myType	node type Process or Resource
	 * @param myParent	parent node
	 */
	public Process_Resource_Node(int value, ProcRes myType)
	{
		this();
		id = value;
		type = myType;
	}
	
	/**
	 * Accessor for Process/Node Type
	 * @return	type 	Enumerator value identifying the node as a Process or Resource
	 */
	public ProcRes getType()
	{
		return type;
	}
	
	/**
	 * Accessor for Process/Node id
	 * @return	id		node identification number
	 */
	public int getID() 
	{
		return id;
	}
		
	/**
	 * Mutator for Process/Resource Node type
	 * @param newType	Enumerator value represents Process or Resource Node
	 */
	public void setType(ProcRes newType)
	{
		type = newType;
	}
	
	/**
	 * Mutator for Process/Resource Node id
	 * @param newID		int value used to id the node 
	 */
	public void setID(int newID)
	{
		id = newID;
	}
	
	/**
	 * Overridden toString, takes type and id to be put into string for unique identification
	 */
	public String toString()
	{
		String nodeInfo = type + " " + id;
		return nodeInfo; 
	}

	/**
	 * acccessor for array that holds the index of child nodes
	 * @return		the array of child indeces
	 */
	public int[] getChildIndex() {
		return childIndex;
	}

	/**
	 * Mutator for the childIndex array
	 * @param childIndex	the array of child indeces
	 */
	public void setChildIndex(int[] childIndex) {
		this.childIndex = childIndex;
	}
}
