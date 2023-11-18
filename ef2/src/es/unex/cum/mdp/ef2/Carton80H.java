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
		for (int i = 0; i < numeroFilas; i++) {
			if (comprobarLinea(i)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean comprobarLinea(int fila) {
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
		CeldaCarton[][] m = new CeldaCarton[numeroFilas][numeroColumnas];
		Set<Integer> numerosGenerados = new HashSet<>();
		Random r = new Random();
		
		//Rellenamos los números del cartón
		int aleatorio;
		
		for(int j=0;j<m[0].length;j++) {
			for(int i=0;i<m.length;i++) {
				do {
					switch(j) {
					case 0:
						aleatorio=generarAleatorio(1,10);
						break;
					case 7:
						aleatorio=generarAleatorio(71,80);
						break;
					default:
						aleatorio=generarAleatorio((10*j)+1, (10*j)+10);
						break;
					}
				}while(!numerosGenerados.add(aleatorio));//while(repetido);
				m[i][j]=new CeldaCarton(aleatorio,1,i,j);
			}
		}
		
		//Ahora vamos a ordenar las columnas de los cartones
		CeldaCarton[] celdas=new CeldaCarton[numeroFilas];
		for(int j=0;j<m[0].length;j++) {
			for(int i=0;i<m.length;i++) {
				celdas[i]=m[i][j];
			}
			Arrays.sort(celdas, new Comparator<CeldaCarton>() {
	            @Override
	            public int compare(CeldaCarton celda1, CeldaCarton celda2) {
	                // Comparar las celdas basándonos en el número
	                return Integer.compare(celda1.getNumero(), celda2.getNumero());
	            }
	        });
			for(int i=0;i<celdas.length;i++) {
				m[i][j]=celdas[i];
			}
		}
		
		//Ahora marcaremos los huecos
		int maxFila=3;
		int minCol=1;
		
		for (int i = 0; i < m.length; i++) {
            int celdasEnFila = 0;

            while (celdasEnFila < maxFila) {
                int j = r.nextInt(m[0].length);
                int celdasEnColumna = comprobarColumna(m, j);

                if (m[i][j].getEstado() !=EstadoCelda.VACIO && celdasEnColumna >= minCol) {
                    // Verificar que la fila no esté llena
                        m[i][j] = new CeldaCarton(0,0,i,j);
                        if(comprobarFila(m,i)<=3) {
                        	celdasEnFila++;
                        }
                }
            }
        }

		// Añado los números de la matriz al hashMap
		for (int i = 0; i < numeroFilas; i++) {
			for (int j = 0; j < numeroColumnas; j++) {
				if (m[i][j].getEstado() != EstadoCelda.VACIO) {
					m[i][j].setFila(i);
					m[i][j].setColumna(j);
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
		int clave = c.getNumero();
		if (clave >= 1 && clave <= numeroMaximo && !numeros.containsKey(clave)) {
			numeros.put(clave, c);
			return true;
		}
		return false;
	}

	@Override
	public boolean addEspecial(int num) {
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
		user.addMovimiento(new Movimiento("Premio",valor,user.getMonedero()));

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
				if (m[fila][i].getEstado()==EstadoCelda.VACIO) {
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
				if (m[i][col].getEstado()!=EstadoCelda.VACIO) {
					libres++;
				}
			}
		}
		return libres;
	}
	private int generarAleatorio(int min, int max) {
		return (int) (Math.random()*(max-min+1)+(min));
	}

	@Override
	public void setPrecio(float p) {
		this.precio=p;
		
	}

	@Override
	public void setNumeros(Object o) {
		this.numeros=(HashMap<Integer, CeldaCarton>) o;
		
	}

}
