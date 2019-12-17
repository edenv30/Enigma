package com.ex1_enigma;

public class Reflector extends Translator {
	
	private char nameref;
	
	public Reflector() {
		
		this.nameref = 'B';
		//this.arrPermutation = new int [] {24,17,20,7,16,18,11,3,15,23,13,6,14,10,12,8,4,1,5,25,2,22,21,9,0,19};
		//Y R U H Q S L D P X N G O K M I E B F Z C W V J A T
		this.arrPermutation = new char [] {'Y','R','U','H','Q','S','L','D','P','X','N','G',
				'O','K','M','I','E','B','F','Z','C','W','V','J','A','T'};
		this.arrReversePermutation = new char [] {'A', 'B', 'C', 'D', 'E', 'F','G','H','I','J',
				'K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
		
//		this.setarrReversePermutation();
//		System.out.println("reflector reverse: " );
//		for (int i=0; i<26; i++)
//			System.out.println(this.arrReversePermutation[i]);
//		System.out.println("reflector : " );
//		for (int i=0; i<26; i++)
//			System.out.println(this.arrPermutation[i]);
		
	}
	
	
	public char symmetricPermutation(char letter)
	{
		int index = this.letter_index_conversations(letter);
		if (this.arrPermutation[index] == letter)
			return this.arrReversePermutation[index];
		return this.arrPermutation[index];
	}
	
}
