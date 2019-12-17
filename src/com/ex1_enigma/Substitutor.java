package com.ex1_enigma;

public abstract class Substitutor {
	
	protected char [] alphabit = {'A', 'B', 'C', 'D', 'E', 'F','G','H','I','J',
			'K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
	// helper function from letter to the index in the alphabit arr
	public int letter_index_conversations(char l)
	{
		for (int i=0; i<26; i++)
		{
			if(this.alphabit[i] == l)
				return i;   // return the index from 0 !!!
		}
		return 0;
	}

	public char index_letter_conversations(int index)
	{
		return this.alphabit[index];
	}
	
	// the number up than 26 or less from 0
	public int circular_shifts(int num)
	{
		if (num > 25)
			return num%26;
		if(num < 0)
			return num + 26;
		return num;
	}
	
	// abstract letter translation method
//	public abstract char letter_translation(int index);

	
	// to check this function  !  !  !
	// default reverse translation method that maps to the regular translation method - symmetric mapping plugboards, reflectors, and Enigma machines
//	public abstract char [] forward_translation(int [] arr);
//
//	public abstract char [] reverse_translation(int [] arr);
	
	public abstract int forwardPermutations(int index);
	
	public abstract int reversePermutations(int index);
	
	

//	public int reverse_translation(int index)  // example: index = z , a-z symmetric -> return a !
//	{
//		return this.alphabit[index];
//	}
	
	
	
}
