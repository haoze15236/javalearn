package com.javase;
 
public class TestMusician {

	public static void main(String[] args) {
		Musician m = new Musician();
		Erhu e = new Erhu();
		Piano p = new Piano();
		m.play(p);
		m.play(e);
	}

}

interface Instrument{
	public void makeSound();
}

class Musician{
	public void play(Instrument s){
		s.makeSound();
	}
}

class Erhu implements Instrument{
	public void makeSound() {
		System.out.println("二胡响了");
	}	
}

class Piano implements Instrument{
	public void makeSound() {
		System.out.println("肖邦来了");
	}
	
}
