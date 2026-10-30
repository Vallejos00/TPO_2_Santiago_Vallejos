package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Partida implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Party party;
    private final List<Batalla> batallas = new ArrayList<>();
    private Batalla batallaActual;
    private int oro;

    public Partida(Party party) {
        this.party = party;
    }

    /** Arma la secuencia de batallas y reparte los ítems iniciales. */
    public void iniciarPartida() {
        for (Personaje p : party.getPersonajes()) {
            p.getInventario().agregarItem(new PocionVida());
            p.getInventario().agregarItem(new PocionVida());
            p.getInventario().agregarItem(new PocionMana());
            p.getInventario().agregarItem(new Elixir());
        }

        batallas.clear();
        batallas.add(new Batalla(party, new Goblin(), new Recompensa(60, 20)));
        batallas.add(new Batalla(party, new Orco(), new Recompensa(100, 40)));
        batallas.add(new Batalla(party, new MagoOscuro(), new Recompensa(150, 60)));
        batallas.add(new Batalla(party, new Jefe(), new Recompensa(300, 200)));
        batallaActual = batallas.get(0);
        oro = 0;
    }

    /** Pasa a la siguiente batalla; la party descansa un poco (y revive a los caídos). */
    public void avanzarBatalla() {
        int siguiente = batallas.indexOf(batallaActual) + 1;
        batallaActual = siguiente < batallas.size() ? batallas.get(siguiente) : null;

        for (Personaje p : party.getPersonajes()) {
            p.curar(p.getVidaMaxima() * 30 / 100);
            p.recuperarMana(p.getManaMaximo() * 30 / 100);
        }
    }

    public void agregarOro(int cantidad) {
        oro += cantidad;
    }

    public boolean estaTerminada() {
        return party.estaDerrotada() || batallaActual == null;
    }

    public int getNumeroBatallaActual() {
        return batallaActual == null ? batallas.size() : batallas.indexOf(batallaActual) + 1;
    }

    public int getTotalBatallas() {
        return batallas.size();
    }

    public Party getParty() { return party; }
    public Batalla getBatallaActual() { return batallaActual; }
    public int getOro() { return oro; }

    public List<Batalla> getBatallas() {
        return Collections.unmodifiableList(batallas);
    }
}
