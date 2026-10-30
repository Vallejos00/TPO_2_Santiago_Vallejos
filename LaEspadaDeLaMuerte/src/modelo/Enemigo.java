package modelo;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Enemigo extends Entidad {

    private static final long serialVersionUID = 1L;

    public Enemigo(String nombre, int vidaMaxima, int manaMaximo,
                   int ataque, int defensa, int velocidad) {
        super(nombre, vidaMaxima, manaMaximo, ataque, defensa, velocidad);
    }

    /** Cada enemigo decide su acción a su manera (comportamiento propio). */
    public abstract Accion elegirAccion(Batalla batalla);

    protected Personaje personajeAleatorio(Batalla batalla) {
        List<Personaje> vivos = batalla.getParty().getPersonajesVivos();
        return vivos.get(ThreadLocalRandom.current().nextInt(vivos.size()));
    }

    protected Personaje personajeMasDebil(Batalla batalla) {
        Personaje debil = null;
        for (Personaje p : batalla.getParty().getPersonajesVivos()) {
            if (debil == null || p.getVida() < debil.getVida()) {
                debil = p;
            }
        }
        return debil;
    }

    protected Personaje personajeMenosDefensa(Batalla batalla) {
        Personaje objetivo = null;
        for (Personaje p : batalla.getParty().getPersonajesVivos()) {
            if (objetivo == null || p.getDefensa() < objetivo.getDefensa()) {
                objetivo = p;
            }
        }
        return objetivo;
    }

    protected boolean tiraDado(int porcentaje) {
        return ThreadLocalRandom.current().nextInt(100) < porcentaje;
    }
}
