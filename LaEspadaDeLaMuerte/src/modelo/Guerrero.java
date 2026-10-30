package modelo;

public class Guerrero extends Personaje {

    private static final long serialVersionUID = 1L;

    public Guerrero(String nombre) {
        super(nombre, 120, 40, 18, 10, 8);
        agregarHabilidad(new HabilidadOfensiva("Golpe Brutal", 10, 20));
    }
}
