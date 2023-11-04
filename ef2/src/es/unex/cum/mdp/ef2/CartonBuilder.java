package es.unex.cum.mdp.ef2.Carton;

import es.unex.cum.mdp.ef2.Usuario;

public class CartonBuilder {
	protected int id;
	protected int numeroFilas;
	protected int numeroColumnas;
	protected int numeroMaximo;
	protected int numeroAciertosBingo;
	protected int numeroAciertosLinea;
	protected int numEspeciales;
	private int contEspeciales;
	protected EstadoCarton estado;
	protected int aciertos; // Para cantar bingo
	protected float premio;
	
	protected Usuario user;

	public CartonBuilder() {
	}	

	public CartonBuilder withId(final int id) {
		this.id= id;
		return this;
	}

	
	public CartonBuilder withNumeroFilas(final int nf) {
		this.numeroFilas = nf;
		return this;
	}
	
	public CartonBuilder withNumeroColumnas(final int nc) {
		this.numeroColumnas = nc;
		return this;
	}
	
	public CartonBuilder withNumeroMaximo(final int nm) {
		this.numeroMaximo = nm;
		return this;
	}
	
	public CartonBuilder withNumeroAciertosBingo(final int nab) {
		this.numeroAciertosBingo= nab;
		return this;
	}
	
	
	public CartonBuilder withNumeroAciertosLineas(final int nal) {
		this.numeroAciertosLinea= nal;
		return this;
	}

	
	public CartonBuilder withNumeroEspeciales(final int ne) {
		this.numEspeciales= ne;
		return this;
	}


	public CartonBuilder withUser(final Usuario u) {
		this.user= u;
		return this;
	}


	public ICarton build(String tipo) {
		ICarton b=null;
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
		b.repartir();
		return b;
		
	}


}
