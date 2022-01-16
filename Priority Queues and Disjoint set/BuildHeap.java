package priorityQueuesAndDisjointSets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap 
{
    private int[] data;
    private List<Swap> swaps;
    private FastScanner in;
    private PrintWriter out;
	 
    public static void main(String[] args) throws IOException 
    {
        new BuildHeap().solve();
    }

    private void readData() throws IOException 
    {
        int n = in.nextInt();
        data = new int[n];
        for (int i = 0; i < n; ++i) 
        {
          data[i] = in.nextInt();
        }
    }

    private void writeResponse() 
    {
        out.println(swaps.size());
        for (Swap swap : swaps) 
        {
          out.println(swap.index1 + " " + swap.index2);
        }
    }
 
    public void makeHeap() 
    {
    	for (int i = data.length / 2 - 1; i >= 0; i--) 
    	{
    		SiftDown(i);
    	}
    }
    
    public void HeapSort() 
    {
    	makeHeap();
    	for (int i = data.length-1; i > 0; i--) 
    	{
    		int tmp = data[0];
    		data[0] = data[i];
    		data[0] = tmp;
    		SiftDown(0);
    	}
    }
    
    public void SiftDown(int i) 
    {
    	int maxIndex = i;
        int l = 2*i+1;
        int r = 2*i+2;
  
        if (l < data.length && data[l] < data[maxIndex]) 
        {
            maxIndex = l; 
        }
  
        if (r < data.length && data[r] < data[maxIndex]) 
        {
            maxIndex = r; 
        }
   
        if (maxIndex != i) 
        { 
        	generateSwaps(i, maxIndex);
            SiftDown(maxIndex); 
        } 
    }

    private void generateSwaps(int i, int j) 
    {
            swaps.add(new Swap(i, j));
            int tmp = data[i];
            data[i] = data[j];
            data[j] = tmp;
    }

    public void solve() throws IOException 
    {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        swaps = new ArrayList<Swap>();
        readData();
        HeapSort();
        writeResponse();
        out.close();
    }

    static class Swap 
    {
        int index1;
        int index2;

        public Swap(int index1, int index2) 
        {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner 
    {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() 
        {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }
              
        public String next() throws IOException 
        {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) 
            {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException 
        {
            return Integer.parseInt(next());
        }
    }
}