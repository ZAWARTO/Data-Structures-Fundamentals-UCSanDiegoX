package binarySearchTrees;

import java.util.*;
import java.io.*;

/*
    This version does not finish in the 
    required time and uses more memory 
    since it uses combinations of partial 
    result lists to store the data.
**/

public class TreeOrdersSlowVersion 
{
    class FastScanner 
    {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() 
		{
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException 
		{
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
	
		int nextInt() throws IOException
		{
			return Integer.parseInt(next());
		}
	}

	public class TreeOrders 
	{
		int n;
		int[] key, left, right;
		
		void read() throws IOException
		{
			FastScanner in = new FastScanner();
			n = in.nextInt();
			key = new int[n];
			left = new int[n];
			right = new int[n];
			for (int i = 0; i < n; i++) 
			{ 
				key[i] = in.nextInt();
				left[i] = in.nextInt();
				right[i] = in.nextInt();
			}
		}

		List<Integer> inOrder() 
		{
			return this.inOrder(0);
		}
		
		private List<Integer> inOrder(int n)
		{
			LinkedList<Integer> result = new LinkedList<Integer>();
			if(this.left[n] != -1) 
			{
				result.addAll(this.inOrder(this.left[n]));
			}
			result.addLast(this.key[n]);
			if(this.right[n] != -1) 
			{ 
				result.addAll(this.inOrder(this.right[n]));
			}
			return result;
		}

		List<Integer> preOrder() 
		{               
			return preOrder(0);
		}
		
		private List<Integer> preOrder(int n)
		{
			LinkedList<Integer> result = new LinkedList<Integer>();
			result.addLast(this.key[n]);
			if(this.left[n] != -1) 
			{
				result.addAll(this.preOrder(this.left[n]));
			}
			if(this.right[n] != -1) 
			{ 
				result.addAll(this.preOrder(this.right[n]));
			}
			return result;
		}

		List<Integer> postOrder() 
		{                
			return postOrder(0);
		}
		
		private List<Integer> postOrder(int n)
		{
			LinkedList<Integer> result = new LinkedList<Integer>();	
			if(this.left[n] != -1) 
			{
				result.addAll(this.postOrder(this.left[n]));
			}
			if(this.right[n] != -1) 
			{ 
				result.addAll(this.postOrder(this.right[n]));
			}
			result.addLast(this.key[n]);
			return result;
		}
		
	}

	static public void main(String[] args) throws IOException
	{
            new Thread(null, new Runnable() 
            {
                    public void run() 
                    {
                        try 
                        {
                            new TreeOrdersSlowVersion().run();
                        } 
                        catch (IOException e) 
                        {}
                    }
                }, "1", 1 << 26).start();
	}

	public void print(List<Integer> x) 
	{
		for (Integer a : x) 
		{
			System.out.print(a + " ");
		}
		System.out.println();
	}

	public void run() throws IOException 
	{
		TreeOrders tree = new TreeOrders();
		tree.read();
		print(tree.inOrder());
		print(tree.preOrder());
		print(tree.postOrder());
	}
}
