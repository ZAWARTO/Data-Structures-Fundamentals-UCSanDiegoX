package binarySearchTrees;

import java.util.*;
import java.io.*;

public class Tree_orders 
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

		void inOrder() 
		{
			inOrder(0);
		}
		
		private void inOrder(int n)
		{
			if(this.left[n] != -1) 
			{
				this.inOrder(this.left[n]);
			}
			System.out.print(this.key[n] + " ");
			if(this.right[n] != -1) 
			{ 
				this.inOrder(this.right[n]);
			}
		}

		void preOrder() 
		{               
			preOrder(0);
		}
		
		private void preOrder(int n)
		{
			System.out.print(this.key[n] + " ");
			if(this.left[n] != -1) 
			{
				this.preOrder(this.left[n]);
			}
			if(this.right[n] != -1) 
			{ 
				this.preOrder(this.right[n]);
			}
		}

		void postOrder() 
		{                
			postOrder(0);
		}
		
		private void postOrder(int n)
		{	
			if(this.left[n] != -1) 
			{
				this.postOrder(this.left[n]);
			}
			if(this.right[n] != -1) 
			{ 
				this.postOrder(this.right[n]);
			}
         	System.out.print(this.key[n] + " ");
		}
	}

	static public void main(String[] args) throws IOException 
    {
            new Thread(null, new Runnable() 
                       {
                    public void run() 
                    {
                        try {
                            new Tree_orders().run();
                        } 
                     catch (IOException e) 
                        {}
                    }
                }, "1", 1 << 26).start();
	}

	public void print(List<Integer> x) {
		for (Integer a : x) {
			System.out.print(a + " ");
		}
		System.out.println();
	}

	public void run() throws IOException {
		TreeOrders tree = new TreeOrders();
		tree.read();
		tree.inOrder();
     	System.out.println();
		tree.preOrder();
     	System.out.println();
		tree.postOrder();
     	System.out.println();
	}
}
