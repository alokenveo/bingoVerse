package es.unex.cum.mdp.ef2;

import java.util.*;

public class Carton80H implements ICarton {
	private int id;
	private Usuario user;
	private float precio;
	private float premio;
	private EstadoCarton estado;
	private HashMap<Integer, CeldaCarton> numeros;

	private int numeroFilas;
	private int numeroColumnas;
	private int numeroMaximo;
	private int numeroAciertosBingo;
	private int numeroAciertosLinea;
	private int numEspeciales;

	public Carton80H() {
		numeroFilas = 4;
		numeroColumnas = 8;
		numeroMaximo = 80;
		numeroAciertosBingo = 20;
		numeroAciertosLinea = 5;
		numEspeciales = 3;
		estado = EstadoCarton.NADA;
		numeros = new HashMap<>();
	}

	public Carton80H(int numeroFilas, int numeroColumnas, int numeroMaximo, int numeroAciertosBingo,
			int numeroAciertosLinea, int numEspeciales) {
		super();
		this.numeroFilas = numeroFilas;
		this.numeroColumnas = numeroColumnas;
		this.numeroMaximo = numeroMaximo;
		this.numeroAciertosBingo = numeroAciertosBingo;
		this.numeroAciertosLinea = numeroAciertosLinea;
		this.numEspeciales = numEspeciales;
		this.numeros = new HashMap<>();
	}

	@Override
	public EstadoCarton getEstado() {
		return estado;
	}

	@Override
	public int getAciertos() {
		int aciertos = 0;
		Iterator<Map.Entry<Integer, CeldaCarton>> it = numeros.entrySet().iterator();
		while (it.hasNext()) {
			CeldaCarton c = it.next().getValue();
			if (c.getEstado() == EstadoCelda.CANTADO) {
				aciertos++;
			}
		}
		return aciertos;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;

	}

	@Override
	public Usuario getUser() {
		return user;
	}

	@Override
	public void setUser(Usuario user) {
		this.user = user;

	}

	@Override
	public boolean comprobarLinea() {
		// TODO Auto-generated method stub
		for (int i = 0; i < numeroFilas; i++) {
			if (comprobarLinea(i)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean comprobarLinea(int fila) {
		// TODO Auto-generated method stub
		for (CeldaCarton c : numeros.values()) {
	        if (c.getFila() == fila && c.getEstado() != EstadoCelda.CANTADO) {
	            return false;  // Si alguna celda no ha sido cantada, la línea no está completa
	        }
	    }
	    cambiarEstado("linea");  // Si todas las celdas de la fila han sido cantadas, cambia el estado
	    return true;  // Indica que la línea está completa
	}

	@Override
	public boolean recibirNumero(int numero) {
		CeldaCarton celda = numeros.get(numero);
		if (celda != null && celda.getEstado() == EstadoCelda.NUMERO) {
			celda.setEstado(EstadoCelda.CANTADO);
			return true;
		}
		return false;
	}

	@Override
	public boolean comprobarBingo() {
		// TODO Auto-generated method stub
		Iterator<Map.Entry<Integer, CeldaCarton>> it = numeros.entrySet().iterator();
		while (it.hasNext()) {
			CeldaCarton c = it.next().getValue();
			if (c.getEstado() == EstadoCelda.NUMERO) {
				return false;
			}
		}
		cambiarEstado("bingo");
		return true;
	}

	@Override
	public boolean comprobarEspeciales() {
		// TODO Auto-generated method stub
		Iterator<Map.Entry<Integer, CeldaCarton>> it = numeros.entrySet().iterator();
		while (it.hasNext()) {
			CeldaCarton c = it.next().getValue();
			if (c.isEspecial() && c.getEstado() == EstadoCelda.NUMERO) {
				return false;
			}
		}
		cambiarEstado("especial");
		return true;
	}

	@Override
	public boolean repartir() {
		// TODO Auto-generated method stub
		CeldaCarton[][] m = new CeldaCarton[numeroFilas][numeroColumnas];
		Set<Integer> numerosGenerados = new HashSet<>();
		boolean[] filaLlena = new boolean[numeroFilas];
		Random r = new Random();
		for (int i = 0; i < numeroAciertosBingo; i++) {
			// Obtenemos el números
			int num = obtenerAleatorio(r, numerosGenerados);

			// Obtenermos el lugar para meterlo
			int fila;
			int col = (int) Math.ceil(num / 10.0) - 1;
			do {
				fila = r.nextInt(numeroFilas);
				while (comprobarColumna(m, col) <= 1) {
					num = obtenerAleatorio(r, numerosGenerados);
					col = (int) Math.ceil(num / 10.0) - 1;
					fila = r.nextInt(numeroFilas);
				}
			} while (m[fila][col] != null || filaLlena[fila]);

			// Creamos la celda y la metemos la celda en la matriz
			CeldaCarton c = new CeldaCarton(num, 1);
			c.setFila(fila);
			c.setColumna(col);
			m[fila][col] = c;

			if (comprobarFila(m, fila) <= 3) {
				filaLlena[fila] = true;
			}
		}

		// Rellenamos los huecos que faltan en la matriz
		for (int i = 0; i < numeroFilas; i++) {
			for (int j = 0; j < numeroColumnas; j++) {
				if (m[i][j] == null) {
					m[i][j] = new CeldaCarton(0, 0, i, j);
				}
			}
		}
		

		// Añado los números de la matriz al hashMap
		for (int i = 0; i < numeroFilas; i++) {
			for (int j = 0; j < numeroColumnas; j++) {
				if (m[i][j].getEstado() != EstadoCelda.VACIO) {
					addNumero(m[i][j]);
				}
			}
		}
		// Añado los especiales de forma aleatoria
		for (int i = 0; i < numEspeciales; i++) {
			int especial = r.nextInt(79) + 1;
			while (numeros.get(especial) == null) {
				especial = r.nextInt(79) + 1;
			}
			addEspecial(especial);
		}

		return true;

	}

	@Override
	public boolean addNumero(CeldaCarton c) {
		// TODO Auto-generated method stub
		int clave = c.getNumero();
		if (clave >= 1 && clave <= numeroMaximo && !numeros.containsKey(clave)) {
			numeros.put(clave, c);
			return true;
		}
		return false;
	}

	@Override
	public boolean addEspecial(int num) {
		// TODO Auto-generated method stub
		if (num > 0 && num <= numeroMaximo) {
			CeldaCarton c = numeros.get(num);
			if (c == null) {
				return false;
			}
			c.setEspecial(true);
			return true;
		}
		return false;

	}

	@Override
	public float getPrecio() {
		return precio;
	}

	@Override
	public void setPremio(float valor) {
		this.premio = valor;

	}

	@Override
	public float getPremio() {
		return premio;
	}

	@Override
	public String toString() {
		return "Carton80H [id=" + id + ", user=" + user + ", precio=" + precio + ", premio=" + premio + ", estado="
				+ estado + ", numeroFilas=" + numeroFilas + ", numeroColumnas=" + numeroColumnas + ", numeroMaximo="
				+ numeroMaximo + ", numeroAciertosBingo=" + numeroAciertosBingo + ", numeroAciertosLinea="
				+ numeroAciertosLinea + ", numEspeciales=" + numEspeciales + "]";
	}

	@Override
	public Object getNumeros() {
		return numeros;
	}

	// MÉTODOS PRIVADOS
	private void cambiarEstado(String comp) {
		// Actualización de estados
		if (comp.equals("linea")) {
			switch (estado) {
			case NADA:
				estado = EstadoCarton.LINEA;
				break;
			case ESPECIAL:
				estado = EstadoCarton.ESPECIAL_LINEA;
				break;
			default:
				break;
			}
		} else if (comp.equals("especial")) {
			switch (estado) {
			case NADA:
				estado = EstadoCarton.ESPECIAL;
				break;
			case LINEA:
				estado = EstadoCarton.ESPECIAL_LINEA;
				break;
			default:
				break;
			}
		} else if (comp.equals("bingo")) {
			switch (estado) {
			case NADA:
				estado = EstadoCarton.BINGO;
				break;
			case LINEA:
				estado = EstadoCarton.LINEA_BINGO;
				break;
			case ESPECIAL:
				estado = EstadoCarton.ESPECIAL_BINGO;
				break;
			case ESPECIAL_LINEA:
				estado = EstadoCarton.ESPECIAL_LINEA_BINGO;
				break;
			default:
				break;
			}
		}
	}

	private int comprobarFila(CeldaCarton[][] m, int fila) {
		int libres = 0;
		if (m != null && fila >= 0 && fila < m.length) {
			for (int i = 0; i < m[fila].length; i++) {
				if (m[fila][i] == null) {
					libres++;
				}
			}
		}
		return libres;
	}

	private int comprobarColumna(CeldaCarton[][] m, int col) {
		int libres = 0;
		if (m != null && m.length > 0 && col >= 0 && col < m[0].length) {
			for (int i = 0; i < m.length; i++) {
				if (m[i][col] == null) {
					libres++;
				}
			}
		}
		return libres;
	}

	private int obtenerAleatorio(Random r, Set<Integer> s) {
		int num;
		do {
			num = r.nextInt(numeroMaximo) + 1;
		} while (!s.add(num));
		return num;
	}

}
