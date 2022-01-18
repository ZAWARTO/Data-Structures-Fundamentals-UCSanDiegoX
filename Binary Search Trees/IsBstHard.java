package binarySearchTrees;

import java.util.*;
import java.io.*;

public class IsBstHard 
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

    public class IsBST 
    {
        class Node 
        {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) 
            {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;

        void read() throws IOException 
        {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) 
            {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        boolean isBinarySearchTree(Node n, long min, long max) 
        {
            if (n.key < min || n.key >= max) 
            {
                return false;
            }
            if(n.left != -1 && n.right != -1) 
            {
            	return (isBinarySearchTree(tree[n.left], min, n.key) &&
            			isBinarySearchTree(tree[n.right], n.key, max));
            }
            else if(n.left != -1 && n.right == -1) 
            {
            	return (isBinarySearchTree(tree[n.left], min, n.key));
            }
            else if(n.left == -1 && n.right != -1) 
            {
            	return (isBinarySearchTree(tree[n.right], n.key, max));
            }
            return true;    
        }

		public boolean solve() 
		{
			if(nodes == 0) 
			{
				return true;
			}
			return isBinarySearchTree(tree[0], Long.MIN_VALUE, Long.MAX_VALUE);
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
                    new IsBstHard().run();
                } 
                catch (IOException e) 
                {}
            }
        }, "1", 1 << 26).start();
    }
    
    public void run() throws IOException 
    {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.solve()) 
        {
            System.out.println("CORRECT");
        } 
        else 
        {
            System.out.println("INCORRECT");
        }
    }
}