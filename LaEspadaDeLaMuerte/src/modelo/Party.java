package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Party implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int TAMANIO = 4;

    private final List<Personaje> personajes;

    public Party(List<Personaje> personajes) {
        if (personajes.size() != TAMANIO) {
            throw new IllegalArgumentException("La party debe tener exactamente " + TAMANIO + " personajes.");
        }
        this.personajes = new ArrayList<>(personajes);
    }

    public boolean estaDerrotada() {
        for (Personaje p : personajes) {
            if (p.estaViva()) {
                return false;
            }
        }
        return true;
    }

    public List<Personaje> getPersonajes() {
        return Collections.unmodifiableList(personajes);
    }

    public List<Personaje> getPersonajesVivos() {
        List<Personaje> vivos = new ArrayList<>();
        for (Personaje p : personajes) {
            if (p.estaViva()) {
                vivos.add(p);
            }
        }
        return vivos;
    }
}
