package modelo;

public class Mago extends Personaje {

    private static final long serialVersionUID = 1L;

    public Mago(String nombre) {
        super(nombre, 80, 100, 14, 4, 10);
        agregarHabilidad(new HabilidadOfensiva("Rayo", 8, 18));
        agregarHabilidad(new HabilidadOfensiva("Bola de Fuego", 15, 32));
    }
}
