package com.ex1_enigma;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Scanner;

public class Plugboard extends Translator{

		private Hashtable<Character, Character> hashtable;
		
		
	    Plugboard(int a, String upper)
	    {
	    	hashtable = new Hashtable<Character, Character>();
	    	// initial plugboard 
	    	for (int i=0; i<26; i++)
				hashtable.put(this.alphabit[i],this.alphabit[i]); 
	    	
	    	// Displaying the Hashtable 
	       //System.out.println("Initial table is: " + hashtable); 
	       //System.out.println("The Value is: " + hashtable.get(25)); 

	    	if (a!=0)
	    		insertToHashTable(upper);
		    //System.out.println("End table is: " + hashtable); 

		    //this.hashtable.clear();
	        //System.out.println("End table is: " + hashtable); 
	    }
	    
    
	    public void insertToHashTable(String s)
	    {
	    	String[] arrOfStr = s.split(" "); 
	    	char[] arr = s.toCharArray(); 
	    	
	    	for (int i=0;i<26;i++)
	    	{
	    		for(int j=0; j<arrOfStr.length; j++)
	    		{

	    			char[] array = arrOfStr[j].toCharArray();
			        //System.out.println(array[0] + "  " + array[1] + "    i:  " + i); 
			        
	    			if (getindexletter(array[0]) == i)   // example AZ - A
	    			{
	    				int tmp = getindexletter(array[1]);
	    		        //System.out.println(getindexletter(array[0]) + "    " + this.alphabit[i]); 
	    		        hashtable.replace(this.alphabit[i], array[0], array[1]);
	    		        hashtable.replace(array[1], array[1], this.alphabit[i]);
//	    				hashtable.put(this.alphabit[i],array[1]); 
//	    				hashtable.put(array[1],this.alphabit[i]); 
	    		        //System.out.println("1 table is: " + hashtable); 

	    			}
	    			if (getindexletter(array[1]) == i)   // example AZ - Z
	    			{
	    				int tmp = getindexletter(array[0]);
	    		        //System.out.println(getindexletter(array[1]) + "    " + this.alphabit[i]); 
	    		        hashtable.replace(this.alphabit[i], array[1], array[0]);
	    		        hashtable.replace(array[0], array[0], this.alphabit[i]);
//	    				hashtable.put(array[1],this.alphabit[i]); 
//	    				hashtable.put(this.alphabit[i],array[1]); 
	    		        //System.out.println("2 table is: " + hashtable); 

	    			}
	    		}	

	    	}
	    	
	    		

	    	}
	    	
	    public int getindexletter(char c)
	    {
	    	for(int i=0; i<26; i++)
	    	{
	    		if(c == this.alphabit[i])
	    			return i;
	    	}
	    	return 0;
	    }
		
	    public Hashtable getHash()
	    {
	    	return this.hashtable;
	    }
	    
	    // get a vlue from Hash table
	    public char getValue(char letter)
	    {
	    	return this.hashtable.get(letter);
	    }
	
}
