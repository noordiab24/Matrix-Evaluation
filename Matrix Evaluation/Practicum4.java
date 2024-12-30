/*
 * Name: Noor Diab
 * Instructor: Mr. Reaser
 * I, Noor Diab, pledge by the academic integrity policy
 * 
 */
public class Practicum4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//reflexive, not symmetric, not transitive
		int [] [] M1= {
				{1,0,0,0,1},
				{1,1,0,0,0},
				{0,1,1,1,0},
				{0,0,0,1,1},
				{0,0,0,0,1}
				};
	
		//anti-reflexive, symmetric, not transitive
		int [] [] M2= {
				{0,1,0,0,1},
				{1,0,1,0,0},
				{0,1,0,1,0},
				{0,0,1,0,1},
				{1,0,0,1,0}
				};

	
		//not reflexive, not anti-reflexive, transitive
		int [] [] M3= {
				{1,1,0,0,1},
				{1,1,0,0,1},
				{1,1,1,0,1},
				{0,0,0,0,1},
				{0,0,0,0,1}
				};
		
		//prints out Matrix 1
		System.out.println("M1: ");
		display(M1);
		System.out.println();
		//print out Matrix 1 result
		System.out.println("M1 result: ");
		display(transitiveClosure(M1));
		System.out.println();
		
		//prints out Matrix 2
		System.out.println("M2: ");
		display(M2);
		System.out.println();
		//prints out Matrix 2 result
		System.out.println("M2 result: ");
		display(transitiveClosure(M2));
		System.out.println();
		
		//prints out Matrix 3
		System.out.println("M3: ");
		display(M3);
		System.out.println();
		//prints out Matrix 3 result
		System.out.println("M3 result: ");
		display(transitiveClosure(M3));
		System.out.println();
		
			
	}
	
	
	public static void display(int [][] matrix)
	{
		//outer loop, loops through the rows
		for(int i=0; i< matrix.length; i++)
		{
			//inner loop loops through the columns
			for(int j=0; j<matrix[0].length; j++)
			{
				System.out.print(matrix[i][j]); //prints out the value at the provided column and row
			}
			System.out.println(); 
		}
	}
	
	public static int [][] sum(int [] [] leftM, int [][] rightM)
	{
		//creates a new adjacency matrix to return as the result
		int [][] matrix= new int [leftM.length][leftM[0].length];
		
		//loops through the # of rows
		for(int i=0; i<leftM.length; i++)
		{
			//loops through the # of columns
			for(int j=0; j<leftM[0].length; j++)
			{
				//adds together the certain element in leftM and rightM at the provided row and column
				int num= leftM[i][j]+rightM[i][j];
				if(num==2) //there are instances where 1+1=2, but in boolean addition, this would just be 1
				{
					num=1; //set num to 1
				}
				matrix [i][j]= num; //set the value at the specified row and column to num 
			}
		}
		
		return matrix; //returns the result matrix
	}
	
	
	public static int [][] product(int [] [] leftM, int [][] rightM)
	{
		//creates a new matrix to return as the result
		int [][] matrix= new int [leftM.length][leftM[0].length];
		
		//loops through the # of rows
		for(int i=0; i<leftM.length; i++)
		{
			//loops through the # of columns
			for(int j=0; j<leftM[0].length; j++)
			{
				int num=0; //num serves to add the products together
				//loops through the rows
				for(int k=0; k<leftM.length; k++)
				{
					//leftM will have the same row index, with a changing column index
					//rightM will have the same column index, with a changing row index
					int product= leftM[i][k]*rightM[k][j]; //calculates the product
					num+=product; //adds the product to num
				}
				 
				if(num>=1) //there are instances where 1+1=2, but in boolean addition, this would just be 1
				{
					num=1;
				}
				matrix [i][j]= num; //set the value at the specified row and column to num 
			}
		}
		
		return matrix; //returns the matrix result
	}
	
	public static int [][] transitiveClosure(int [] [] M)
	{
		//creates a new matrix to return as the result
		int [][]matrix= new int [M.length] [M[0].length];
		int n= M.length; //since this is a square matrix, the number of vertices is the length of the row or column
		
		
		int [] [] multiple= copyHelper(M); //calls the helper method to copy M to multiple
		matrix= copyHelper(M); //calls the helper method to copy M to matrix
		for(int i=0; i<n-1; i++)
		{
			multiple= product(M, multiple); //calls the product method: M x M^n
			matrix= sum(matrix, multiple); //calls the sum method, so matrix would become M1+M2+...Mn
		}
		
		return matrix; //returns the matrix result
	}
	//helper method
	private static int [][] copyHelper(int [] [] M)
	{
		//creates a new matrix to return as the result
		int [] [] copy= new int [M.length] [M[0].length]; 
		//loops through the rows
		for(int i=0; i<M.length; i++)
		{
			//loops through the columns
			for(int j=0; j<M[0].length; j++)
			{
				copy[i][j]= M[i][j]; //copies the value at the assigned index 
			}
		}
		return copy; //returns the copy result
	}
}

