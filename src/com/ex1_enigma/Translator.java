package com.ex1_enigma;

public class Translator extends Substitutor {

	
	protected char [] arrPermutation;
	protected char [] arrReversePermutation;
	
	Translator()
	{
		this.arrPermutation = new char[26];
		this.arrReversePermutation = new char [26];
	}
	
	//simple forward and automatically computed reverse permutations:
	// 1. forward - get and return integer
	public int forwardPermutations(int index)
	{
		int temp = this.letter_index_conversations(this.arrPermutation[index]);
		return temp;
	}
	
	// 2. reverse - get and return integer
	public int reversePermutations(int index)
	{
		int temp = this.letter_index_conversations(this.arrReversePermutation[index]);
		return temp;
	}
	
	// create the Reverse Permutation
	public void setarrReversePermutation()
	{
		this.arrReversePermutation = new char [this.arrPermutation.length];
		for(int i=0; i<this.arrPermutation.length; i++)
		{
			int temp = this.letter_index_conversations(this.arrPermutation[i]);
			this.arrReversePermutation[temp]= this.alphabit[i];
		}
	}
	

	
//	@Override
//	public char letter_translation(int index) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	
	

}
