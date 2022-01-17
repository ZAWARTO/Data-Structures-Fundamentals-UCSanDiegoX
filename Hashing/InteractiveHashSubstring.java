package hashing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class InteractiveHashSubstring 
{  
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
    	//String pattern = in.next();
        //String text = in.next();
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter the pattern you want to search for");
        String pattern = sc.nextLine();
        System.out.println("Enter the text where you want to search for the introduced pattern");
        String text = sc.nextLine();
        sc.close();
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
        int prime = 49157;
        int x = ThreadLocalRandom.current().nextInt(0, prime + 1);
        long pHash = polyHash(p, prime, x);
        long tHash;
        List<Integer> occurrences = new ArrayList<Integer>();
        long h[] = preComputeHashes(t, pl, prime, x);
        
        for (int i = 0; i <= tl-pl; i++) 
        {
        	tHash = polyHash(t.substring(i, i + pl), prime, x);
        	System.out.println("Kar-Rabin recurrence value " + h[i] + " = " + " Hash value " + tHash);
        	if(h[i] != tHash) 
        	{
        		System.out.println("ERROR: Karp-Rabin algorithm failed due to a problem of overflow");
        		System.exit(1);
        	}
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
    	System.out.println("Apparitions indexes: ");
        for (Integer cur : ans) 
        {
            System.out.print(cur);
            System.out.print(" ");
        }
    }

    public static void main(String[] args) throws IOException 
    {
        printOccurrences(getOccurrences(readInput()));
    }
}