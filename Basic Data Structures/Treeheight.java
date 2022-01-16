package basic;

import java.util.*;
import java.io.*;

public class Treeheight 
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

	public class TreeHeight 
	{
		int n;
		int parent[];
		
		void read() throws IOException 
		{
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) 
			{
				parent[i] = in.nextInt();
			}
		}
		
		void fillHeight(int parent[], int i, int height[]) 
		{   
	        if (height[i] != 0) 
	        { 
	            return; 
	        } 
	        if (parent[i] == -1) 
	        { 
	            height[i] = 1; 
	            return; 
	        } 
	        if (height[parent[i]] == 0) 
	        { 
	            fillHeight(parent, parent[i], height); 
	        } 
	        height[i] = height[parent[i]] + 1; 
	    } 

		int computeHeight() 
		{
	        int height[] = new int[n]; 	        
	        for (int i = 0; i < n; i++) 
	        { 
	            height[i] = 0; 
	        } 
	        
	        for (int i = 0; i < n; i++) 
	        { 
	            fillHeight(parent, i, height); 
	        } 
	   
	        int ht = height[0]; 
	        for (int i = 1; i < n; i++) 
	        { 
	            if (ht < height[i]) 
	            { 
	                ht = height[i]; 
	            } 
	        } 
	        return ht; 
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
                          new Treeheight().run();
                        } 
                        catch (IOException e) 
                        {}
                    }
                }, "1", 1 << 26).start();
	}
	
	public void run() throws IOException 
	{
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}