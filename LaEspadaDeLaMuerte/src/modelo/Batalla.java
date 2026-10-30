package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Batalla implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Party party;
    private final Enemigo enemigo;
    private final Recompensa recompensa;
    private Entidad turnoActual;

    private final List<String> registro = new ArrayList<>();
    private List<Entidad> ordenRonda = new ArrayList<>();
    private int indiceTurno;
    private boolean finRegistrado;

    public Batalla(Party party, Enemigo enemigo, Recompensa recompensa) {
        this.party = party;
        this.enemigo = enemigo;
        this.recompensa = recompensa;
    }

    public void iniciar() {
        registro.clear();
        finRegistrado = false;
        registro.add(enemigo.getNombre() + " aparece...");
        nuevaRonda();
    }

    /** Resuelve el turno de la entidad actual si es un enemigo (su IA elige la acción). */
    public void ejecutarTurno() {
        if (estaTerminada() || !(turnoActual instanceof Enemigo)) {
            return;
        }
        Accion accion = ((Enemigo) turnoActual).elegirAccion(this);
        accion.ejecutar();
        registrar(accion.getResultado());
        terminarTurno();
    }

    /** Pasa al siguiente turno; si la ronda se agotó, arma una nueva según la velocidad. */
    public void terminarTurno() {
        if (estaTerminada()) {
            registrarFin();
            return;
        }
        indiceTurno++;
        while (indiceTurno < ordenRonda.size() && !ordenRonda.get(indiceTurno).estaViva()) {
            indiceTurno++;
        }
        if (indiceTurno >= ordenRonda.size()) {
            nuevaRonda();
        } else {
            comenzarTurno(ordenRonda.get(indiceTurno));
        }
    }

    public boolean estaTerminada() {
        return party.estaDerrotada() || !enemigo.estaViva();
    }

    /** Entidades vivas ordenadas de más rápida a más lenta. */
    public List<Entidad> ordenTurnos() {
        List<Entidad> orden = new ArrayList<>(party.getPersonajesVivos());
        if (enemigo.estaViva()) {
            orden.add(enemigo);
        }
        orden.sort((a, b) -> b.getVelocidad() - a.getVelocidad());
        return orden;
    }

    public void registrar(String mensaje) {
        registro.add(mensaje);
    }

    private void nuevaRonda() {
        ordenRonda = ordenTurnos();
        indiceTurno = 0;
        if (!ordenRonda.isEmpty()) {
            comenzarTurno(ordenRonda.get(0));
        }
    }

    private void comenzarTurno(Entidad entidad) {
        turnoActual = entidad;
        entidad.setDefendiendo(false);
    }

    private void registrarFin() {
        if (finRegistrado) {
            return;
        }
        finRegistrado = true;
        if (enemigo.estaViva()) {
            registro.add("Toda la party cayó en combate...");
        } else {
            registro.add("¡" + enemigo.getNombre() + " fue derrotado! Victoria.");
        }
    }

    public Party getParty() { return party; }
    public Enemigo getEnemigo() { return enemigo; }
    public Entidad getTurnoActual() { return turnoActual; }
    public Recompensa getRecompensa() { return recompensa; }

    public List<String> getRegistro() {
        return Collections.unmodifiableList(registro);
    }
}
