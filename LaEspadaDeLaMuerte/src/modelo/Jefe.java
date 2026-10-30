package modelo;

/**
 * La Muerte, jefe final. Tiene dos fases: con más de la mitad de vida ataca
 * al azar y a veces usa la Guadaña; por debajo de la mitad entra en furia
 * y ataca al más herido con sus habilidades más fuertes.
 */
public class Jefe extends Enemigo {

    private static final long serialVersionUID = 1L;

    public Jefe() {
        super("La Muerte", 400, 150, 32, 12, 13);
        agregarHabilidad(new HabilidadOfensiva("Guadaña", 15, 40));
        agregarHabilidad(new HabilidadOfensiva("Llamado de la Muerte", 25, 60));
    }

    @Override
    public Accion elegirAccion(Batalla batalla) {
        Habilidad guadania = getHabilidades().get(0);
        Habilidad llamado = getHabilidades().get(1);
        boolean furia = getVida() * 2 <= getVidaMaxima();

        if (furia) {
            Personaje objetivo = personajeMasDebil(batalla);
            if (llamado.puedeUsarse(this)) {
                return new UsarHabilidad(this, llamado, objetivo);
            }
            if (guadania.puedeUsarse(this)) {
                return new UsarHabilidad(this, guadania, objetivo);
            }
            return new Atacar(this, objetivo);
        }

        Personaje objetivo = personajeAleatorio(batalla);
        if (guadania.puedeUsarse(this) && tiraDado(40)) {
            return new UsarHabilidad(this, guadania, objetivo);
        }
        return new Atacar(this, objetivo);
    }
}
