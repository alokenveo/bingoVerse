package es.unex.cum.mdp.ef2;

import es.unex.cum.mdp.ef2.Usuario;

public class MainCarton {
	public static void main(String[] args) {
		CartonBuilder cb = new CartonBuilder("75M");
		ICarton c = cb.withId(1)
				.withUser(new Usuario())
				.build(); //75H o 75M
		System.out.println(c.toString());
		
		cb = new CartonBuilder("80H");
		c = cb.withId(2)
				.withUser(new Usuario())
				.build(); //80V o 80M
		System.out.println(c.toString());
		
		cb = new CartonBuilder("90H");
		c = cb.withUser(new Usuario())
				.withId(3)
				.build();	//90H o 90M 
		System.out.println(c.toString());
	}
}
