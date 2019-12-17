package com.ex1_enigma;

import java.util.HashSet;
import java.util.Scanner;

public class User {
	
//	private static Enigma e;
	private static int [] rotors;	
	
	
	public static void main(String[] args) 
	{		
		System.out.println("Enigma M3!");

		System.out.println("Options: Enigma - press 1 | Task 5 - press 2 | Task 6 - press 3 | Exit - press 0 :");
		int choose = 0;
		Scanner in = new Scanner(System.in); 
		choose = in.nextInt(); 
		if (choose ==1)
		{
			//input rotors
			setArrRotors();
			System.out.println("Now insert the ring offset for the rotors - 3 letters A-Z: ");
			String stroffset = StrRingPosition();
			//System.out.println(stroffset);
			char [] offsets = new char [3];
			offsets[0]=stroffset.charAt(0);
			offsets[1]=stroffset.charAt(2);
			offsets[2]=stroffset.charAt(4);
//			for (int i=0; i< 3 ; i++)
//			{
//				System.out.println(offsets[i]);
//			}
			System.out.println("Now insert the ring setting for the rotors - 3 letters A-Z: ");
			String strSetting = StrRingPosition();
			//System.out.println(strSetting);
			char [] settings = new char [3];
			settings[0]=strSetting.charAt(0);
			settings[1]=strSetting.charAt(2);
			settings[2]=strSetting.charAt(4);
//			for (int i=0; i< 3 ; i++)
//			{
//				System.out.println(settings[i]);
//			}
			
			// input plugboard
			int numPairs = numOfPairs();
			boolean flag = false;
	    	String s, upper=null ;
			if (numPairs != 0)
	    	{
	    		do
		    	{
		    		System.out.println("Please enter " + numPairs + " different Pairs:");
		        	Scanner sc = new Scanner(System.in); 
		        	s = sc.nextLine(); 
		        	System.out.println(s);
		        	upper = s.toUpperCase(); //stores "CRICKET!"

		        	flag = checkInputPairs(upper,numPairs);
		    	}
		    	while (flag == false);  // validation check
	    	}
			
			Enigma e = new Enigma(rotors,offsets,settings,numPairs,upper);
			String s1 = inputFunc();
	    	//String[] arrOfStr = s.split(" "); 
	    	char[] arr = s1.toCharArray(); 
	    	char [] arresult = new char [arr.length];
	    	for (int i=0 ; i<arr.length; i++)
	    	{
	    		if(arr[i] != ' ')
	    		{
//	        		System.out.print(arr[i]);
	        		arresult[i] = e.playEnigma(arr[i]);
	    		}
	    		else
	    			arresult[i] = ' ';

	    	}
	    	System.out.println("The result:");
			for(int i=0; i <arresult.length; i++)
				System.out.print(arresult[i]);
		}
		else if (choose == 2)
		{
			System.out.println("Task 5:");
			task5();
		}
		else if (choose == 3)
		{
			int [] arr_r ={1,2,3};
			char [] arr_o ={'F','D','V'};
			char [] arr_s ={'A','A','A'};
			
			Enigma e;
			for (int j=0; j<1000; j++)
			{
				e = new Enigma(arr_r,arr_o,arr_s,0,null);
				String s1 = "ENIGMA";
				char[] arr = s1.toCharArray(); 
		    	char [] arresult = new char [arr.length];
		    	for (int i=0 ; i<arr.length; i++)
		    	{
		    		if(arr[i] != ' ')
		    		{
//		        		System.out.print(arr[i]);
		        		arresult[i] = e.playEnigma(arr[i]);
		    		}
		    		else
		    			arresult[i] = ' ';

		    	}
			}
		}
		else
			System.exit(0);
		System.exit(0);

		
		//clearHash();
		
	}
	
	// Input for the plugboard
	public static int numOfPairs()
	{
		int a = -1;
    	boolean flag = false;
    	String s, upper ;
    	
    	while (!(a>=0 && a<11))
    	{
    		System.out.println("how many pairs of plugs do you want?");
    		Scanner in = new Scanner(System.in); 
    		a = in.nextInt(); 
    		if (a>11)
    			System.out.println("you can to enter maximun 10 pairs");    
    	}
    	System.out.println("num of pairs: " + a);

    	return a;
	}
    
	public static boolean checkInputPairs(String s, int num)  // validation function
	    {
	    	boolean flag = false;
	    	String[] arrOfStr = s.split(" "); 
	    	
	    	// first we need to check the pairs
	    	// check each pair is contain 2 letters
	    	for (int i=0; i<arrOfStr.length; i++)
	    	{
	    		if(arrOfStr[i].length() != 2)
	    		{
	        		System.out.println("Each pair only 2 letters");
	    			return false;
	    		}
	    	}
	    	// check that number of pairs equal to num
	    	if (arrOfStr.length != num)
	    	{
	    		System.out.println("number of pairs not equal to " + num);
	    		return false;
	    	}
	    		
	    	// check duplicate character in the string
	    	char[] arr = s.toCharArray(); 
	    	flag = firstRepeating(arr);
	    	if (flag == false)
	    	{
	    		System.out.println("Duplicate letters");
	    		//this.hashtable.clear();
	    		return false;
	    	}
	    	// In the end we need to insert all the letters to one table - insert to hash table
	    	 
	    	
	    	
	    	///////////insertToHashTable(s);///////
	    	
	    	
	    	return true;
	    }
    	
	// This function prints the first repeated , character in str[] 
	public static boolean firstRepeating(char str[]) 
	    { 
	        // Creates an empty hashset 
	        HashSet<Character> h = new HashSet<>(); 
	  
	        // Traverse the input array from left to right 
	        for (int i=0; i<=str.length-1; i++) 
	        { 
	        	if (str[i] != ' ')
	        	{
	        		char c = str[i]; 
		            //System.out.println("str[i]    " +str[i]);
		            // If element is already in hash set, update x 
		            // and then break 
		            if (h.contains(c)) 
		                return false;
		            	//return c; 
		  
		            else // Else add element to hash set 
		                h.add(c); 
	        	}
	            
	        } 
	  
	        return true; 
	    } 
    
	// Input User text
	public static String inputFunc()
	{
		String o,upper;
		boolean flag = true;
		System.out.println("Enter your input:");
		do
		{
			Scanner sc = new Scanner(System.in); 
	    	o = sc.nextLine(); 
	    	upper = o.toUpperCase(); //stores "CRICKET!
	        char[] chars = upper.toCharArray();

	        for (char c : chars) 
	        {
	        	//System.out.println(c);
	            if(!(Character.isLetter(c)) && c != ' ')
	            {
	            	System.out.println("THE INPUT MUST CONTAIN ONLY LETTERS");
	            	flag = false;
	            	break;
	            }
	        }
	        
		}while(flag == false);
		
		return upper;
	}
	
//	public static void clearHash()
//	{
//		e.getPlug().getHash().clear();
//	}
	
	// Input for rotors
	public static void setArrRotors()
	{
		boolean flag = false;
		do
		{
			System.out.println("Please choose 3 rotors options: 1 / 2 / 3 / 4 / 5 :");
			rotors = new int [3];
			for (int i=0; i<3; i++)
			{
				System.out.println("The "+ (i+1) + " Rotor: ");
				Scanner in = new Scanner(System.in); 
				rotors[i] = in.nextInt(); 
			}
			flag = checkRotorsNames(rotors);
			
		}while(flag == false);
	}
	
	public static boolean checkRotorsNames(int [] s)
	{
		
    	//System.out.println(s[0] + " " +s[1]+ " " + s[2]);
    	for(int i=0; i <3; i++)
    	{
    		if(s[i] != 1 && s[i] != 2 && s[i] != 3 && s[i] != 4 && s[i] != 5)
    		{
    			//System.out.println(s[i]);
    			System.out.println("YOU MUSE CHOOSE THE ROTORS NUMBER 1/2/3/4/5");
    			return false;
    		}
    	}
    	if(s[0] != s[1] && s[0] != s[2] && s[1] !=s[2])
    		return true;
    	System.out.println("YOU MUST CHOOSE DIFFERENT ROTORS !!!");
    	return false;
	}

	public static String StrRingPosition()
	{
		String o,upper;
		boolean flag = false;
		String[] arrOfStr;
		do
		{
			Scanner sc = new Scanner(System.in); 
	    	o = sc.nextLine(); 
	    	upper = o.toUpperCase(); //stores "CRICKET!"
	    	arrOfStr = upper.split(" "); 
	    	
	    	if((arrOfStr.length)!=3)
	    	{
	    		System.out.println("YOU MUST INSERT 3 LETTERS ! TRY AGAIN: ");
	    		flag = false;
	    	}
	    	else
	    	{
	    		flag = checkstring(arrOfStr);
	    		
	    	}
//	    	for (int i=0; i<arrOfStr.length; i++)
//	    		System.out.println(arrOfStr[i]);
		}while (flag == false);
		
		return upper;
		
	}
	
	// checking for alphabit
	public static boolean checkstring(String[] arrOfStr)
	{
//		if (input_char >= 65 && input_char <= 90)

		for(int i=0; i<arrOfStr.length ;i++)
		{
			//char[] arr = arrOfStr[i].toCharArray(); 
			if(arrOfStr[i].length() > 1)
			{
	    		System.out.println("ONE CHARACTER !");
	    		return false;
			}
		}
		
		return true;
		
	}
	
	// Task 5
	public static void task5()
	{
		char [] setting = {'S','I','X'};
		int [] rotor = {2,5,4};
		String plugboard = "ZU HL CQ WM OA PY EB TR DN VI";
		int numpairs = 10;
		String input = "CON MLD RNYHP UMDPQ CUAQN LVVSP IARKC TTRJQ KCFPT OKRGO ZXALD RLPUH AUZSO SZFSU GWFNF DZCUG VEXUU LQYXO TCYRP SYGGZ HQMAG PZDKC KGOJM MYYDD H";
    	
		String[] arrOfStr = input.split(" "); 
    	
		char [] offset = arrOfStr[0].toCharArray();   // CON
    	
    	Enigma e = new Enigma(rotor,offset,setting,numpairs,plugboard);
    	//String[] arrOfStr = s.split(" "); 
    	System.out.println("The M: " + arrOfStr[1]);
    	char[] arr = arrOfStr[1].toCharArray();   // MLD
    	char [] arresult = new char [arr.length];
    	for (int i=0 ; i<arr.length; i++)
    	{
    		if(arr[i] != ' ')
    		{
//        		System.out.print(arr[i]);
        		arresult[i] = e.playEnigma(arr[i]);
    		}
    		else
    			arresult[i] = ' ';

    	}
    	System.out.println("The result:");
		for(int i=0; i <arresult.length; i++)   //DOR
			System.out.print(arresult[i]);
		System.out.println("");
		
		// -------------------------------
		// junp arrOfStr[2]  = clear text
		Enigma e1 = new Enigma(rotor,arresult,setting,numpairs,plugboard);
    	//String[] arrOfStr = s.split(" "); 
    	//String s2 ="UMDPQ CUAQN LVVSP IARKC TTRJQ KCFPT OKRGO ZXALD RLPUH AUZSO SZFSU GWFNF DZCUG VEXUU LQYXO TCYRP SYGGZ HQMAG PZDKC KGOJM MYYDD H";
    	String s2=arrOfStr[3];
    	s2 = s2 + " ";
    	for (int i=4; i<arrOfStr.length ; i++)
    	{
    		s2 = s2 + arrOfStr[i];
    		s2 = s2 + " ";
    	}
    		
    	
    	System.out.println("The M: " + s2);
    		
    	

    	char[] arr2 = s2.toCharArray();   // MLD
    	char [] arresult2 = new char [arr2.length];
    	for (int i=0 ; i<arr2.length; i++)
    	{
    		if(arr2[i] != ' ')
    		{
//        		System.out.print(arr[i]);
        		arresult2[i] = e1.playEnigma(arr2[i]);
    		}
    		else
    			arresult2[i] = ' ';

    	}
    	System.out.println("The result:");
		for(int i=0; i <arresult2.length; i++)   
			System.out.print(arresult2[i]);
		System.out.println("");

	}
	
	
}
