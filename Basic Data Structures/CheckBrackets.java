package basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket 
{
    Bracket(char type, int position) 
    {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) 
    {
        if (this.type == '[' && c == ']') 
        {
            return true;
        }
        if (this.type == '{' && c == '}') 
        {
            return true;
        }
        if (this.type == '(' && c == ')') 
        {
            return true;
        }
        return false;
    }
    char type;
    int position;
}

class CheckBrackets 
{
    public static void main(String[] args) throws IOException 
    {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();
        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
     
         if (text.length()>1 && text.length()<= Math.pow(10, 5)) 
         {				
        	for (int position = 0; position < text.length(); position++) 
        	{
        		char next = text.charAt(position);

        		if (next == '(' || next == '[' || next == '{') 
        		{
        			// Process opening bracket
        			Bracket b = new Bracket(next, position);
        			opening_brackets_stack.push(b);
        		}
        		if (next == ')' || next == ']' || next == '}') 
        		{
        			// Process closing bracket
        			if (opening_brackets_stack.isEmpty()) { 
        				position++;
                    	System.out.println(position);
        				System.exit(0);
        			}
        			else
        			{
        				Bracket aux = opening_brackets_stack.peek();
                 
        				if (aux.Match(next)) 
        				{
        					opening_brackets_stack.pop();
        				}
                    	else 
                    	{
                     		position++;
                    		System.out.println(position);
                    		System.exit(0);
                    	}
                    }
        		}
        	}
        	
          	if (!opening_brackets_stack.isEmpty())
          	{
           		System.out.println(opening_brackets_stack.peek().position + 1);
                System.exit(0);
            }
        }
     	else if (text.length() == 1)
     	{
        	System.out.println(1);
         	System.exit(0);
        }
     	else if (text.length() > Math.pow(10, 5))
     	{
        	System.out.println("Parameter too long");
         	System.exit(0);
        }
    	System.out.println("Success");
    }
}