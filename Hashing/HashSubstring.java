package hashing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

public class HashSubstring 
{
    private static FastScanner in;
    private static PrintWriter out;
    
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
    
    static class Data 
    {
        String pattern;
        String text;
        public Data(String pattern, String text) 
        {
            this.pattern = pattern;
            this.text = text;
        }
    }
    
    private static Data readInput() throws IOException 
    {
        String pattern = in.next();
        String text = in.next();
        if(pattern.length() >= text.length())
        {
        	System.out.println("JJJAJAJAJAJ");
        }
        return new Data(pattern, text);
    }
    
    private static long polyHash(String s, int prime, int x) 
    {
    	long hash = 0;
    	for(int i = s.length() - 1; i >= 0; i--) 
    	{
    		hash = (hash * x + s.charAt(i)) % prime;
    	}
    	return hash;
    }
    
    private static long[] preComputeHashes(String t, int pl, int prime, int x) 
    {
    	int tminusp = t.length() - pl;
    	long h[] = new long[tminusp+1];
    	String s = t.substring(tminusp, t.length());
    	h[tminusp] = polyHash(s, prime, x);
    	int y = 1;
    	for(int i = 1; i <= pl; i++) 
    	{
    		y = (y*x) % prime;
    	}

    	for(int i = tminusp-1; i >= 0; i--) 
    	{
    		double a =  ((x*h[i+1] + t.charAt(i) - y*t.charAt(i+pl))%prime);
    		h[i] =  (int) (((a%prime) + prime)%prime);
    	}
    	return h;
    }
    
    private static List<Integer> getOccurrences(Data input) 
    {
        String p = input.pattern, t = input.text;
        int pl = p.length(), tl = t.length();
        int prime = 24593;
        int x = ThreadLocalRandom.current().nextInt(0, prime + 1);
        long pHash = polyHash(p, prime, x);
        List<Integer> occurrences = new ArrayList<Integer>();
        long h[] = preComputeHashes(t, pl, prime, x);
        
        for (int i = 0; i <= tl-pl; i++) 
        {
        	if(pHash == h[i]) 
        	{
        		if(t.substring(i, i + pl).equals(p)) 
        		{
        			occurrences.add(i);
        		}
        	}
        }
        return occurrences;
    }
    
    private static void printOccurrences(List<Integer> ans) throws IOException 
    {
        for (Integer cur : ans) 
        {
            out.print(cur);
            out.print(" ");
        }
    }

    public static void main(String[] args) throws IOException 
    {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }
}