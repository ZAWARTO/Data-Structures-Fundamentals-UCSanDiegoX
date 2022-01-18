package priorityQueuesAndDisjointSets;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JobQueue 
{
    private int numWorkers;
    private int[] jobs;
    private int[] assignedWorker;
    private long[] startTime;
    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException 
    {
        new JobQueue().solve();
    }

    private void readData() throws IOException 
    {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) 
        {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() 
    {
        for (int i = 0; i < jobs.length; ++i) 
        {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }
    
    private static class Worker 
    {
        int id;
        long nextFreeTime;
        public Worker (int id) 
        {
            this.id = id;
            nextFreeTime = 0;
        }
    }

    private void assignJobs() 
    {
     	assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
    	PriorityQueue<Worker> threads = new PriorityQueue<Worker>(numWorkers, new Comparator<Worker>()
    	{
            @Override
            public int compare (Worker w1, Worker w2)
            {
            	if (w1.nextFreeTime == w2.nextFreeTime)
            	{
            		return w1.id - w2.id;
            	}
            	else
            	{
            		return (int) (w1.nextFreeTime - w2.nextFreeTime);
            	}
            }
        });
    	
    	for (int i = 0; i < numWorkers; i++) 
    	{
            threads.add(new Worker(i));
        }
        for (int i = 0; i < jobs.length; i++) 
        {
            Worker freeThread = threads.poll();
            assignedWorker[i] = freeThread.id;
            startTime[i] = freeThread.nextFreeTime;
            freeThread.nextFreeTime += jobs[i];
            threads.add(freeThread);
        }
    }

    public void solve() throws IOException 
    {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
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
