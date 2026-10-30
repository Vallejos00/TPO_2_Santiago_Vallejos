package modelo;

import java.io.Serializable;

public abstract class Habilidad implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String nombre;
    private final int costoMana;

    public Habilidad(String nombre, int costoMana) {
        this.nombre = nombre;
        this.costoMana = costoMana;
    }

    public boolean puedeUsarse(Entidad entidad) {
        return entidad.tieneMana(costoMana);
    }

    /** true si el objetivo es un aliado (curación), false si es un rival. */
    public abstract boolean afectaAliados();

    /** Aplica el efecto sobre el objetivo y devuelve el mensaje para el registro. */
    public abstract String aplicarEfecto(Entidad usuario, Entidad objetivo);

    public String getNombre() { return nombre; }
    public int getCostoMana() { return costoMana; }
}
