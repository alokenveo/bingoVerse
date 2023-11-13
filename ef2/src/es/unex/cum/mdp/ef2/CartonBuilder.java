package es.unex.cum.mdp.ef2;

import es.unex.cum.mdp.ef2.Usuario;

public class CartonBuilder {
	private ICarton b;

	public CartonBuilder(String tipo) {
		if (tipo.equals("75M")) {
			b= new Carton75M();
		} if (tipo.equals("75V")) {
			b= new Carton75V();
		} else if (tipo.equals("75H")) {
			b= new Carton75H();
		} else if (tipo.equals("80H")) {
			b= new Carton80H();
		} else if (tipo.equals("80V")) {
			b= new Carton80V();
		} else if (tipo.equals("80M")) {
			b= new Carton80M();
		} else if (tipo.equals("90M")) {
			b= new Carton90M();
		} else if (tipo.equals("90H")) {
			b= new Carton90H();
		} else if (tipo.equals("90V")) {
			b= new Carton90V();
		}

	}	

	public CartonBuilder withId(final int id) {
		this.b.setId(id);
		return this;
	}

	public CartonBuilder withUser(final Usuario u) {
		this.b.setUser(u);
		return this;
	}
	


	public ICarton build() {
		b.repartir();
		return b;
		
	}


}
