package modelo;

public class Arquero extends Personaje {

    private static final long serialVersionUID = 1L;

    public Arquero(String nombre) {
        super(nombre, 90, 50, 20, 6, 14);
        agregarHabilidad(new HabilidadOfensiva("Disparo Certero", 8, 22));
    }
}
