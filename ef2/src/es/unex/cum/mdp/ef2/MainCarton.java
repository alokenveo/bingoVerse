package es.unex.cum.mdp.ef2.Carton;

public class MainCarton {
	public static void main(String[] args) {
		CartonBuilder cb = new CartonBuilder();
		ICarton c = cb.withId(1)
				.build("75M"); //75H o 75M
		System.out.println(c.toString());
		
		c = cb.withId(2)
				.build("80M"); //80H o 80M
		System.out.println(c.toString());
		
		c = cb.withId(3)
				.build("90M");	//90H o 90M 
		System.out.println(c.toString());
	}
}
