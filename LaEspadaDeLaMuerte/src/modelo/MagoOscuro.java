package modelo;

/** Hechicero: usa su habilidad contra el personaje con menos defensa mientras tenga maná. */
public class MagoOscuro extends Enemigo {

    private static final long serialVersionUID = 1L;

    public MagoOscuro() {
        super("Mago Oscuro", 110, 60, 16, 5, 10);
        agregarHabilidad(new HabilidadOfensiva("Rayo Sombrío", 10, 30));
    }

    @Override
    public Accion elegirAccion(Batalla batalla) {
        Habilidad rayo = getHabilidades().get(0);
        if (rayo.puedeUsarse(this)) {
            return new UsarHabilidad(this, rayo, personajeMenosDefensa(batalla));
        }
        return new Atacar(this, personajeAleatorio(batalla));
    }
}
