package com.ex1_enigma;

public class Enigma extends Substitutor {
	
	private Reflector ref;
	private Plugboard plug;
	private Rotor R;
	private Rotor M;
	private Rotor L;
	
	
	Enigma(int [] namesrotor, char [] offsets, char [] settings, int numP , String str)
	{
		this.ref = new Reflector();
		
		
		//needs to change the ctor plugboard the input ! 
		this.plug = new Plugboard(numP,str);
		// example for the direction input: 1 2 3 so the Right=3, Middle=2, Left =1
		this.R = new Rotor(namesrotor[2],offsets[2],settings[2]);
		this.M = new Rotor(namesrotor[1],offsets[1],settings[1]);
		this.L = new Rotor(namesrotor[0],offsets[0],settings[0]);
		
	}
	
	public Plugboard getPlug()
	{
		return this.plug;
	}
	
	// needs to get & return char - to change ! ! ! 1
	public char playEnigma(char letter)
	{
		//print check if the offset change
		//System.out.println("BEFORE:  R.offset   " + this.R.getOffset() + "    M.offset   " + this.M.getOffset()
		//		+ "    L.offset    " + this.L.getOffset());
		
		
		//System.out.println("R.NOTCH     " + this.R.getNotch()
		//		+ "     M.NOTCH    " + this.M.getNotch() + "    L.NOTCH    " + this.L.getNotch());
		// checking if the rotor complete a circle
		this.R.checkTurnoverNotch();
		this.M.checkTurnoverNotch();
		
		if (this.R.getNotch() == true || this.M.getNotch() == true)
		{
			if(this.M.getNotch() == true)
				this.L.changeOffset();
			this.M.changeOffset();
		}
		this.R.changeOffset(); 

		
//		this.R.checkTurnoverNotch();
//		this.M.checkTurnoverNotch();

		
		//System.out.println("R.NOTCH     " + this.R.getNotch()
		//		+ "     M.NOTCH    " + this.M.getNotch() + "    L.NOTCH    " + this.L.getNotch());
		
		//System.out.println("AFTER:  R.offset    " + this.R.getOffset() + "    M.offset    " + this.M.getOffset()
		//		+ "    L.offset    " + this.L.getOffset());
		
		
		//Plugboard  -  1  - letter going to the plugboard
		char p = this.plug.getValue(letter);
		//System.out.println("FORWARD : Plug board result: "+ p);
		
		boolean flag = false; // false mean forward direction
		

		
		
		// Right Rotor - 2 - letter output from plugboard change in the R rotor
		char right = this.R.actionRotorFormula(p, flag);
		//System.out.println("FORWARD : Right Rotor result: "+ right);
		
		// Middle Rotor - 3 - letter output from Right Rotor change in the M rotor
		char middle = this.M.actionRotorFormula(right, flag);
		//System.out.println("FORWARD : Middle Rotor result: "+ middle);
		
		// Left Rotor - 4 - letter output from Middle Rotor change in the L rotor
		char left = this.L.actionRotorFormula(middle, flag);
		//System.out.println("FORWARD : Left Rotor result: " + left);
		
		//Reflector -  5  - letter output from Left Rotor change in the Reflector
		char r = this.ref.symmetricPermutation(left);
		//System.out.println("FORWARD : Reflector result: " + r);
		
		flag = true; // false mean reverse direction
		
		// Left Rotor - 6 - letter output from Reflector change in the L rotor
		left = this.L.actionRotorFormula(r, flag);
		//System.out.println("REVERSE : Left Rotor result: "+ left);
		
		// Middle Rotor - 7 - letter output from Left Rotor change in the M rotor		
		middle = this.M.actionRotorFormula(left, flag);
		//System.out.println("REVERSE : Middle Rotor  result: "+ middle);

		
		// Right Rotor - 8 - letter output from Middle change in the R rotor
		right = this.R.actionRotorFormula(middle, flag);
		//System.out.println("REVERSE : Right Rotor result: "+ right);

		//Plugboard -  9  - letter output from Right Rotor change in the Plugboard
		p = this.plug.getValue(right);
		//System.out.println("REVERSE : Plug board result: "+ p);
		
		return p;
	}
	
	
	// change the offset in the first step in R rotor
	public void changeOffsetFirseStep(char letter)
	{
		int index = this.R.letter_index_conversations(letter) + 1;
		char temp = this.R.index_letter_conversations(index);
		this.R.setOffset(temp);
	}
	
	
	
//	@Override
//	public char letter_translation(int index) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	@Override
	public int forwardPermutations(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int reversePermutations(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
