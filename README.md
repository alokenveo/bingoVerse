# 🎰 BingoVerse - Sistema de Casino y Bingo en Java

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![JavaFX](https://img.shields.io/badge/JavaFX-UI-blue?style=for-the-badge&logo=java)
![JUnit](https://img.shields.io/badge/JUnit-5-green?style=for-the-badge&logo=junit5)
![License](https://img.shields.io/badge/License-MIT-yellow?style=for-the-badge)

**Sistema completo de gestión de casino virtual con múltiples variantes de Bingo**

[Características](#-características) • [Instalación](#-instalación) • [Uso](#-uso) • [Arquitectura](#-arquitectura) • [Testing](#-testing)

</div>

---

## 📋 Descripción

**BingoVerse** es un sistema integral de casino virtual desarrollado en Java que implementa múltiples variantes del juego de Bingo (75, 80 y 90 números). El proyecto incluye una arquitectura robusta basada en patrones de diseño, gestión completa de usuarios, estadísticas de juego y una interfaz gráfica construida con JavaFX.

## ✨ Características

### 🎮 Variantes de Bingo
- **Bingo 75**: Cartón 5x5 con números del 1 al 75
- **Bingo 80**: Cartón 4x8 con números del 1 al 80
- **Bingo 90**: Cartón 3x9 con números del 1 al 90

### 🏗️ Estructuras de Datos
Cada variante puede implementarse con tres estructuras diferentes:
- **HashMap (H)**: Búsqueda optimizada O(1)
- **Vector/Array (V)**: Acceso directo por índice
- **Matriz (M)**: Representación bidimensional natural

### 👥 Sistema de Usuarios
- Registro y autenticación segura
- Gestión de monedero virtual
- Historial completo de transacciones
- Sistema de ranking por logros (bingos, líneas, especiales)

### 🎯 Mecánicas de Juego
- **Premios Especiales**: Números marcados especiales en cada cartón
- **Premio Línea**: Completar una fila horizontal
- **Premio Bingo**: Completar todo el cartón
- Reparto proporcional de premios entre ganadores
- Sistema de bote acumulativo

### 📊 Estadísticas
- Seguimiento de números más cantados
- Números premiados por categoría
- Historial de partidas jugadas
- Análisis de frecuencia de sorteos

### 💰 Sistema Económico
- Gestión de monedero personal
- Historial de movimientos financieros
- Compra de cartones
- Distribución de premios:
  - 10% para la casa
  - 2€ fijos por especial
  - 30% del bote para líneas
  - 70% del bote para bingos

## 🚀 Instalación

### Requisitos Previos
- **Java JDK 17** o superior
- **Eclipse IDE** (recomendado) o cualquier IDE compatible con Java
- **JUnit 5** para testing
- **JavaFX SDK** (incluido en el proyecto)

### Pasos de Instalación

1. **Clonar el repositorio**
```bash
git clone https://github.com/alokenveo/bingoVerse.git
cd bingoverse
```

2. **Importar en Eclipse**
   - File → Import → Existing Projects into Workspace
   - Seleccionar la carpeta `ef2`
   - Finish

3. **Configurar JavaFX** (si es necesario)
   - Project → Properties → Java Build Path
   - Verificar que JRE System Library esté configurado con Java 17

4. **Ejecutar Tests**
   - Clic derecho en la carpeta `test` → Run As → JUnit Test

## 💻 Uso

### Ejemplo Básico: Crear y Jugar un Bingo
```java
// Crear el casino
Casino casino = new Casino();

// Registrar usuarios
casino.register("jugador1", "Juan", "password123", 100.0f);
casino.register("jugador2", "María", "pass456", 150.0f);

// Crear un bingo de 80 números
Date fecha = new Date();
Bingo bingo = casino.crearBingo("80H", fecha, 50.0f, 3.0f);

// Los jugadores compran cartones
ICarton carton1 = casino.adherirseCarton("jugador1", "password123", fecha, "80H");
ICarton carton2 = casino.adherirseCarton("jugador2", "pass456", fecha, "80H");

// Jugar el bingo
casino.jugar(fecha);

// Ver ranking
List<Usuario> ranking = casino.verRanking();
```

### Ejemplo: Crear Cartón Personalizado
```java
// Usar el builder para crear cartones
CartonBuilder builder = new CartonBuilder("80H");
Usuario usuario = new Usuario("player1", "Player One", "pass", 100.0f);

ICarton carton = builder
    .withUser(usuario)
    .withId(1)
    .build();

// El método build() automáticamente reparte números aleatorios
```

### Ejemplo: Sistema de Premios
```java
// Después de jugar, consultar resultados
Bingo bingo = casino.consultarBingo(fecha);
Reparto reparto = bingo.getReparto();

System.out.println("Ganadores de Bingo: " + reparto.getNumBingo());
System.out.println("Ganadores de Línea: " + reparto.getNumLineas());
System.out.println("Ganadores Especial: " + reparto.getNumEspeciales());
System.out.println("Premio Bingo: " + reparto.getRepartoBingo());
System.out.println("Premio Línea: " + reparto.getRepartoLinea());
```

## 🏛️ Arquitectura

### Patrones de Diseño Implementados

#### 1. **Factory Pattern** (`FactoriaBingo`)
```java
Bingo bingo = FactoriaBingo.buildBingo("80H", 3.0f);
```
Crea instancias de diferentes tipos de Bingo de forma centralizada.

#### 2. **Builder Pattern** (`CartonBuilder`)
```java
ICarton carton = new CartonBuilder("80H")
    .withUser(usuario)
    .withId(1)
    .build();
```
Construcción flexible de cartones con diferentes configuraciones.

#### 3. **Strategy Pattern** (Implementaciones de `ICarton`)
Diferentes estrategias de almacenamiento (HashMap, Vector, Matriz) para los números del cartón.

#### 4. **Template Method** (`Bingo` abstracta)
Define el esquema del juego, las subclases implementan detalles específicos.

### Diagrama de Clases Principal
```
┌─────────────┐
│   Casino    │
├─────────────┤
│ usuarios    │◇───► HashMap<String, Usuario>
│ bingos      │◇───► HashMap<Date, Bingo>
│ estadistica │◇───► Estadistica[]
└─────────────┘
       │
       │ crea
       ▼
┌─────────────┐         ┌──────────────┐
│    Bingo    │◆───────►│   ICarton    │
├─────────────┤         ├──────────────┤
│ cartones    │         │ Carton75H    │
│ reparto     │         │ Carton80H    │
│ bolsaBingo  │         │ Carton90H    │
└─────────────┘         │ Carton75V    │
       △                │ Carton80V    │
       │                │ Carton90V    │
    ┌──┴──┐            │ Carton75M    │
  Bingo75  │            │ Carton80M    │
  Bingo80  │            │ Carton90M    │
  Bingo90  │            └──────────────┘
```

### Estructura de Paquetes
```
es.unex.cum.mdp.ef2/
├── 📦 bingo/
│   ├── Bingo.java (abstracta)
│   ├── Bingo75.java
│   ├── Bingo80.java
│   ├── Bingo90.java
│   ├── BolsaBingo.java
│   ├── FactoriaBingo.java
│   └── Reparto.java
├── 📦 carton/
│   ├── ICarton.java (interfaz)
│   ├── Carton75H/M/V.java
│   ├── Carton80H/M/V.java
│   ├── Carton90H/M/V.java
│   ├── CartonBuilder.java
│   └── EstadoCarton.java (enum)
├── 📦 celda/
│   ├── Celda.java (abstracta)
│   ├── CeldaCarton.java
│   └── EstadoCelda.java (enum)
├── Casino.java
├── Usuario.java
├── Movimiento.java
├── Estadistica.java
└── UsuarioNoAutenticado.java (excepción)
```

## 🧪 Testing

El proyecto incluye una suite completa de tests con **JUnit 5**:

### Tests Unitarios
- `BolsaBingoTest`: Pruebas del sistema de sorteo
- `CartonTest75/80/90`: Validación de cartones
- `TestBingo`: Mecánicas de juego
- `TestCasino`: Integración completa del sistema

### Ejecutar Tests
```bash
# Desde Eclipse
Run → Run As → JUnit Test

# Desde línea de comandos (con Maven/Gradle configurado)
mvn test
# o
gradle test
```

### Cobertura de Tests
- ✅ Creación y validación de cartones
- ✅ Sistema de premios y reparto
- ✅ Autenticación de usuarios
- ✅ Gestión de monederos
- ✅ Estadísticas de juego
- ✅ Ranking de jugadores

## 📁 Estructura del Proyecto
```
bingoverse/
├── ef2/
│   ├── src/
│   │   └── es/unex/cum/mdp/ef2/
│   │       ├── bingo/          # Lógica del juego
│   │       ├── carton/         # Gestión de cartones
│   │       ├── celda/          # Celdas individuales
│   │       ├── Casino.java     # Controlador principal
│   │       └── Usuario.java    # Gestión de usuarios
│   ├── test/
│   │   └── es/unex/cum/mdp/ef2/
│   │       ├── BolsaBingoTest.java
│   │       ├── CartonTest75.java
│   │       ├── CartonTest80.java
│   │       ├── CartonTest90.java
│   │       ├── TestBingo.java
│   │       └── TestCasino.java
│   └── bin/                    # Compilados
├── .classpath
├── .project
└── README.md
```

## 🎯 Próximas Mejoras

- [ ] Interfaz gráfica completa con JavaFX
- [ ] Persistencia de datos (base de datos)
- [ ] Sistema de chat entre jugadores
- [ ] Modo multijugador en red
- [ ] Animaciones de sorteo en tiempo real
- [ ] Sistema de torneos
- [ ] Logros y badges
- [ ] Integración con sistema de pago

## 👨‍💻 Autor

**Alfredo Mituy Okenve**


## 🤝 Contribuir

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request


<div align="center">

**¿Te gustó el proyecto? ¡Dale una ⭐!**

Hecho con ❤️ y mucho ☕

</div>
