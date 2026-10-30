package modelo;

import java.io.Serializable;

public abstract class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String nombre;

    public Item(String nombre) {
        this.nombre = nombre;
    }

    public abstract void aplicarEfecto(Entidad entidad);

    public String getNombre() { return nombre; }
}
