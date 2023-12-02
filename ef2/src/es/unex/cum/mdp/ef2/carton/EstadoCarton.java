package es.unex.cum.mdp.ef2.carton;

/**
 * Enumerado que representa los posibles estados de un carton.
 */
public enum EstadoCarton {
    NADA,               // Es el por defecto, el cartón no ha cantado Nada
    ESPECIAL,           // Se ha descubierto un especial
    LINEA,              // Se ha cantado linea
    ESPECIAL_LINEA,     // Se ha descubierto un especial y se ha cantado linea
    ESPECIAL_BINGO,     // Se ha descubierto un especial y se ha cantado Bingo
    LINEA_BINGO,        // Se ha cantado linea y Bingo
    ESPECIAL_LINEA_BINGO, // Se ha descubierto un especial, se ha cantado linea y Bingo
    BINGO               // Se ha cantado Bingo
}
