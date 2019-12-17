package com.ex1_enigma;

public class Rotor extends Translator{
	
	private int name;
	private char offset;
	private char setting;
	private char[] turnoverNotch;
	private boolean notch;
	
	Rotor(int name, char offset, char setting)
	{
		setOffset(offset);
		this.setting = setting;
		setRotor(name);
		setNotch(false);
		
	}
	
	// set to the notch boolean variable - needs to change if such a rotor finish circler 
	public void setNotch(boolean flag)
	{
		this.notch = flag;
	}
	
	public boolean getNotch()
	{
		return this.notch;
	}
	
	// set the offset - because when the input is in the rotor move one step or when one rotor finish circle we need to cahnge the next rotor offset
	public void setOffset(char o)
	{
		this.offset = o;
	}
	
	public char getOffset()
	{
		return this.offset;
	}
	
	// set rotor static details 
	public void setRotor(int name)
	{
		this.name = name;
		if (name == 1)  
		{
			// rotor 1 turnover notch = Q -> R
			this.turnoverNotch = new char [] {'Q','R'};
			//this.arrRotorPermutation = new int [] {4,10,12,5,11,6,3,16,21,25,13,19,14,22,24,7,23,20,18,15,0,8,1,17,2,9};
			//E K M F L G D Q V Z N T O W Y H X U S P A I B R C J
			this.arrPermutation = new char [] {'E','K','M','F','L','G','D','Q','V','Z',
					'N','T','O','W','Y','H','X','U','S','P','A','I','B','R','C','J'};
			setarrReversePermutation();
		}
		if (name ==2)
		{
			// rotor 2 turnover notch = E -> F
			this.turnoverNotch = new char [] {'E','F'};
			//this.arrRotorPermutation = new int [] {0,9,3,10,18,8,17,20,23,1,11,7,22,19,12,2,16,6,25,13,15,24,5,21,14,4};
			//A J D K S I R U X B L H W T M C Q G Z N P Y F V O E
			this.arrPermutation = new char[]{'A','J','D','K','S','I','R','U','X','B',
					'L','H','W','T','M','C','Q','G','Z','N','P','Y','F','V','O','E'};
			setarrReversePermutation();
		}
		if (name == 3) 
		{
			// rotor 3 turnover notch = V -> W
			this.turnoverNotch = new char [] {'V','W'};
			//this.arrRotorPermutation = new int [] {1,3,5,7,9,11,2,15,17,19,23,21,25,13,24,4,8,22,6,0,10,12,20,18,16,14};
			//B D F H J L C P R T X V Z N Y E I W G A K M U S Q O
			this.arrPermutation = new char[]{'B','D','F','H','J','L','C','P','R','T',
					'X','V','Z','N','Y','E','I','W','G','A','K','M','U','S','Q','O'};
			setarrReversePermutation();
//			System.out.println("rotor 3 reverse: " );
//			for (int i=0; i<26; i++)
//				System.out.println(this.arrReversePermutation[i]);
		}
		if (name == 4)
		{
			// rotor 4 turnover notch = J -> K
			this.turnoverNotch = new char [] {'J','K'};
			//this.arrRotorPermutation = new int [] {4,18,14,21,15,25,9,0,24,16,20,8,17,7,23,11,13,5,19,6,10,3,2,12,22,1};
			//E S O V P Z J A Y Q U I R H X L N F T G K D C M W B
			this.arrPermutation = new char[]{'E','S','O','V','P','Z','J','A','Y','Q',
					'U','I','R','H','X','L','N','F','T','G','K','D','C','M','W','B'};
			setarrReversePermutation();
		}
		if (name == 5)
		{
			// rotor 5 turnover notch = Z -> A
			this.turnoverNotch = new char [] {'Z','A'};
			//this.arrRotorPermutation = new int [] {21,25,1,17,6,8,19,24,20,15,18,3,13,7,11,23,0,22,12,9,16,14,5,4,2,10};
			//V Z B R G I T Y U P S D N H L X A W M J Q O F E C K
			this.arrPermutation = new char[]{'V','Z','B','R','G','I','T','Y','U','P',
					'S','D','N','H','L','X','A','W','M','J','Q','O','F','E','C','K'};
			setarrReversePermutation();
		}
	}
	
	// Implement : result is given by P(A+B−C)−B+C - forward & reverse
	public char actionRotorFormula(char input, boolean flag)
	{
		// calculate A - input
		int indexInput = this.letter_index_conversations(input) + 1;
		//System.out.println("indexInput " + indexInput);

		// calculate B - offset  (in the Enigma i need to check the offset depend if is the 1st rotor)
		int indexOffset = this.letter_index_conversations(this.offset);
		//System.out.println("indexOffset " + indexOffset);
		
		// calculate C - setting
		int indexSetting = this.letter_index_conversations(this.setting);
		//System.out.println("indexSetting " + indexSetting);
		
		// the value in the permutation: (A + B - C)
		int valueForPermutation = indexInput + indexOffset;
		valueForPermutation = valueForPermutation - indexSetting;
		//System.out.println("valueForPermutation " + valueForPermutation);
		// check if valueForPermutation > 25 ot valueForPermutation < 0
		valueForPermutation = this.circular_shifts(valueForPermutation - 1);  // -1 to get the index
		//System.out.println("valueForPermutation >26 or <0: " + valueForPermutation);
		
		// the result from the permutation P(A + B - C)
		int resP;
		if (flag == false)
			resP = this.forwardPermutations(valueForPermutation);
		else
			resP = this.reversePermutations(valueForPermutation);
		
		//System.out.println("resP before : " + resP);
		
		// for calculation the final result
		resP = resP + 1;
		resP = resP - indexOffset;
		resP = resP + indexSetting;
		//System.out.println("resP: " + resP);
		// check if resP > 25 or resP < 0
		resP = this.circular_shifts(resP - 1);  // -1 to get the index
		//System.out.println("resP >26 or <0: " + resP);

		char res = this.index_letter_conversations(resP);
		//System.out.println("resP of value in Permutation: " + res);
		
		return res;
	}
	
	// When the input in example: ENIGMA - E the first letter press - the R rotor move one step
	public void changeOffset()
	{
		int temp = this.letter_index_conversations(this.offset) + 1;
		temp = this.circular_shifts(temp);
		setOffset(this.index_letter_conversations(temp));
		//this.checkTurnoverNotch();
	}
	
	// checking if the rotor complete a circle
	public void checkTurnoverNotch()
	{
		//System.out.println("offset in rotor: " +this.offset+ " turn notch: "+ this.turnoverNotch[0] );
		if (this.offset == this.turnoverNotch[0])
		{
			this.notch = true;
			return;
		}
		this.setNotch(false);
	}
	
	
}
