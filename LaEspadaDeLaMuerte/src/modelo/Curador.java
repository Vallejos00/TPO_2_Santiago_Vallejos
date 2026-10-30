package modelo;

public class Curador extends Personaje {

    private static final long serialVersionUID = 1L;

    public Curador(String nombre) {
        super(nombre, 70, 80, 8, 6, 11);
        agregarHabilidad(new HabilidadCuracion("Curación", 12, 40));
        agregarHabilidad(new HabilidadCuracion("Gran Curación", 25, 80));
    }
}
