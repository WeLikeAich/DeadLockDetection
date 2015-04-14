/*
 *  
 */

public class Aichler_PA3
{
	//sample table 1
	static final char[][] jvr1 = {{'x','n','n','n','n','n','n'},        
								  {'w','x','n','n','n','n','n'},       
								  {'n','w','x','n','n','n','n'},   
								  {'n','n','w','x','n','n','n'},     
								  {'n','n','n','w','x','n','n'},     
								  {'n','n','n','n','w','x','n'},      
								  {'n','n','n','n','n','w','x'}};
	//sample table 2
	static final char[][] jvr2 = {{'w','n','x','x','x','n','n'},        
	         					  {'x','w','n','n','n','x','n'},       
	         					  {'n','x','w','n','n','n','n'},   
	         					  {'n','n','n','n','n','n','x'},     
	         					  {'n','n','n','n','n','n','n'},     
	         					  {'n','n','w','n','n','n','w'},      
	         					  {'w','w','w','w','w','w','w'}};
	//sample table 3
	static final char[][] jvr3 = {{'n','n','n','n','n','n','n'},        
								  {'n','n','n','n','n','n','n'},       
								  {'n','n','n','n','n','n','n'},   
								  {'n','n','n','n','n','n','n'},     
								  {'n','n','n','n','x','w','n'},     
								  {'n','n','n','n','w','x','n'},      
								  {'n','n','n','n','n','n','n'}};
		
	public static void main(String[] args)
	{

		Process_Resource_Node startNode = null;
		Process_Resource_Tree tree1 = new Process_Resource_Tree(jvr1);
		
		//displays the sample job vs resource table
		for(int i = 0; i < jvr1.length; i++)
		{
			for(int j = 0; j < jvr1[i].length; j++)
			{
				System.out.print(jvr1[i][j] + "\t");
			}
			System.out.println();
		}
		
		//block that finds the first node that has children
		for(int i = 0; i < tree1.getProccess().length; i++)
		{
			if(tree1.getProccess()[i].getChildIndex() != null && tree1.getProccess()[i].getChildIndex().length>0)
			{
				startNode = tree1.getProccess()[i];
				break;
			}
		}
		
		tree1.setRoot(startNode);//sets the trees root node to the starting node found above
		
		/*
		 * build tree method creates tree and locates deadlock
		 *as well as generates a string arrayList off all proccesses
		 *and resources involved
		 */
		for(String node : tree1.buildTree(tree1.getRoot()))
		{
			//if a deadlock was detected
			if(tree1.getDeadLockDetected())
			{
				if(node.contains("PROCESS"))
				{
					//print out all of the processes involved
					System.out.println(node);
				}
			}	
			else//no deadlock has been detected
			{
				
				System.out.println("Congratulations. No Dead Lock Has Been Detected!");
				break;
			}
		}

		System.out.println("");
		
		
		
		//displays the sample job vs resource table
		for(int i = 0; i < jvr2.length; i++)
		{
			for(int j = 0; j < jvr2[i].length; j++)
			{
				System.out.print(jvr2[i][j] + "\t");
			}
			System.out.println();
		}
		
		//block that finds the first node that has children
		Process_Resource_Tree tree2 = new Process_Resource_Tree(jvr2);
		for(int i = 0; i < tree2.getProccess().length; i++)
		{
			if(tree2.getProccess()[i].getChildIndex() != null && tree2.getProccess()[i].getChildIndex().length>0)
			{
				startNode = tree2.getProccess()[i];
				break;
			}
		}
		
		/*
		 * build tree method creates tree and locates deadlock
		 *as well as generates a string arrayList off all processes
		 *and resources involved
		 */
		tree2.setRoot(startNode);
		for(String node : tree2.buildTree(tree2.getRoot()))
		{
			//if a deadlock was detected
			if(tree2.getDeadLockDetected())
			{
				if(node.contains("PROCESS"))
				{
					//print out all of the processes involved
					System.out.println(node);
				}
			}	
			else//no deadlock has been detected
			{
				
				System.out.println("Congratulations. No Dead Lock Has Been Detected!");
				break;
			}
		}
	
		System.out.println("");
		
		
		//prints out the sample table
		for(int i = 0; i < jvr3.length; i++)
		{
			for(int j = 0; j < jvr3[i].length; j++)
			{
				System.out.print(jvr3[i][j] + "\t");
			}
			System.out.println();
		}
		
		
		//finds the starting node in the sample
		Process_Resource_Tree tree3 = new Process_Resource_Tree(jvr3);
		for(int i = 0; i < tree2.getProccess().length; i++)
		{
			if(tree3.getProccess()[i].getChildIndex() != null && tree3.getProccess()[i].getChildIndex().length>0)
			{
				startNode = tree3.getProccess()[i];
				break;
			}
		}
		
		/*
		 * Build tree method creates tree and locates deadlock
		 *as well as generates a string arrayList off all proccesses
		 *and resources involved
		 */
		tree3.setRoot(startNode);
		for(String node : tree3.buildTree(tree3.getRoot()))
		{
			//if a deadlock was detected
			if(tree3.getDeadLockDetected())
			{
				if(node.contains("PROCESS"))
				{
					//print out all of the processes involved
					System.out.println(node);
				}
			}	
			else//no deadlock has been detected
			{
				
				System.out.println("Congratulations. No Dead Lock Has Been Detected!");
				break;
			}
		}

	}	
}