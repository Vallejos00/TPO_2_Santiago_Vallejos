package modelo;

import java.io.Serializable;

public class Recompensa implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int experiencia;
    private final int oro;

    public Recompensa(int experiencia, int oro) {
        this.experiencia = experiencia;
        this.oro = oro;
    }

    /** El oro va a la partida; la experiencia, a cada personaje que sigue vivo. */
    public void otorgarRecompensa(Partida partida) {
        partida.agregarOro(oro);
        for (Personaje p : partida.getParty().getPersonajesVivos()) {
            p.ganarExperiencia(experiencia);
        }
    }

    public int getExperiencia() { return experiencia; }
    public int getOro() { return oro; }
}
