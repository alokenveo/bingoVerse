package es.unex.cum.mdp.ef2.carton;

import es.unex.cum.mdp.ef2.Usuario;

/**
 * Constructor de cartones que permite la construccion de diferentes tipos de cartones.
 */
public class CartonBuilder {
	private ICarton b;

	/**
     * Construye un CartonBuilder para un tipo especifico de carton.
     *
     * @param tipo Tipo de carton.
     */
	public CartonBuilder(String tipo) {
		if (tipo.equals("75M")) {
			b = new Carton75M();
		}
		if (tipo.equals("75V")) {
			b = new Carton75V();
		} else if (tipo.equals("75H")) {
			b = new Carton75H();
		} else if (tipo.equals("80H")) {
			b = new Carton80H();
		} else if (tipo.equals("80V")) {
			b = new Carton80V();
		} else if (tipo.equals("80M")) {
			b = new Carton80M();
		} else if (tipo.equals("90M")) {
			b = new Carton90M();
		} else if (tipo.equals("90H")) {
			b = new Carton90H();
		} else if (tipo.equals("90V")) {
			b = new Carton90V();
		}

	}

	/**
     * Establece el identificador del carton en construccion.
     *
     * @param id Identificador del carton.
     * @return La instancia actual de CartonBuilder.
     */
	public CartonBuilder withId(final int id) {
		this.b.setId(id);
		return this;
	}

	/**
     * Establece el usuario asociado al carton en construccion.
     *
     * @param u Usuario asociado al carton.
     * @return La instancia actual de CartonBuilder.
     */
	public CartonBuilder withUser(final Usuario u) {
		this.b.setUser(u);
		return this;
	}

	/**
     * Construye y reparte los números en el carton.
     *
     * @return El carton construido.
     */
	public ICarton build() {
		if (b != null) {
			b.repartir();
		}
		return b;

	}

}
