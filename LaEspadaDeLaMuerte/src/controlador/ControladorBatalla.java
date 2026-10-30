package controlador;

import java.util.ArrayList;
import java.util.List;

import dto.EstadoBatallaDTO;
import dto.HabilidadDTO;
import dto.ResultadoDTO;
import modelo.Accion;
import modelo.Atacar;
import modelo.Batalla;
import modelo.Defender;
import modelo.Enemigo;
import modelo.Entidad;
import modelo.Habilidad;
import modelo.Item;
import modelo.Partida;
import modelo.Personaje;
import modelo.Recompensa;
import modelo.UsarHabilidad;
import modelo.UsarItem;
import vista.VentanaPrincipal;
import vista.VistaBatalla;
import vista.VistaResultados;

public class ControladorBatalla {

    private final VentanaPrincipal ventana;
    private final VistaBatalla vistaBatalla;
    private final VistaResultados vistaResultados;

    private Partida partida;
    private Batalla batalla;

    public ControladorBatalla(VentanaPrincipal ventana, VistaBatalla vistaBatalla,
                              VistaResultados vistaResultados) {
        this.ventana = ventana;
        this.vistaBatalla = vistaBatalla;
        this.vistaResultados = vistaResultados;
    }

    /** Lo llama ControladorPartida cada vez que se crea o se carga una partida. */
    public void setPartida(Partida partida) {
        this.partida = partida;
        this.batalla = null;
    }

    public void iniciarBatalla() {
        batalla = partida.getBatallaActual();
        batalla.iniciar();
        procesarTurnosEnemigos();
        refrescarVista();
        ventana.mostrarBatalla();
    }

    // ---- Selección de acción (en Swing cada opción del menú crea la acción y la ejecuta) ----

    public void atacar() {
        if (!esTurnoDelJugador()) {
            return;
        }
        ejecutarAccion(new Atacar(batalla.getTurnoActual(), batalla.getEnemigo()));
    }

    public void defender() {
        if (!esTurnoDelJugador()) {
            return;
        }
        ejecutarAccion(new Defender(batalla.getTurnoActual()));
    }

    /**
     * @param indiceHabilidad posición de la habilidad en el personaje actual
     * @param indiceAliado    posición en la party del objetivo (solo importa si cura)
     */
    public void usarHabilidad(int indiceHabilidad, int indiceAliado) {
        if (!esTurnoDelJugador()) {
            return;
        }
        Entidad usuario = batalla.getTurnoActual();
        Habilidad habilidad = usuario.getHabilidades().get(indiceHabilidad);
        if (!habilidad.puedeUsarse(usuario)) {
            vistaBatalla.mostrarMensaje(usuario.getNombre() + " no tiene maná para " + habilidad.getNombre() + ".");
            return;
        }
        Entidad objetivo = habilidad.afectaAliados()
            ? batalla.getParty().getPersonajes().get(indiceAliado)
            : batalla.getEnemigo();
        ejecutarAccion(new UsarHabilidad(usuario, habilidad, objetivo));
    }

    public void usarItem(int indiceItem) {
        if (!esTurnoDelJugador()) {
            return;
        }
        Personaje usuario = (Personaje) batalla.getTurnoActual();
        Item item = usuario.getInventario().getItems().get(indiceItem);
        ejecutarAccion(new UsarItem(usuario, item));
    }

    // ---- Flujo del turno ----

    public void ejecutarAccion(Accion accion) {
        accion.ejecutar();
        batalla.registrar(accion.getResultado());
        batalla.terminarTurno();
        procesarTurnosEnemigos();
        refrescarVista();
    }

    public void finalizarBatalla() {
        if (batalla == null || !batalla.estaTerminada()) {
            return;
        }
        boolean victoria = !batalla.getEnemigo().estaViva();
        Recompensa recompensa = batalla.getRecompensa();
        List<String> mensajes = new ArrayList<>();

        if (victoria) {
            List<Personaje> personajes = partida.getParty().getPersonajes();
            int[] nivelesAntes = new int[personajes.size()];
            for (int i = 0; i < personajes.size(); i++) {
                nivelesAntes[i] = personajes.get(i).getNivel();
            }

            recompensa.otorgarRecompensa(partida);

            for (int i = 0; i < personajes.size(); i++) {
                Personaje p = personajes.get(i);
                if (p.getNivel() > nivelesAntes[i]) {
                    mensajes.add(p.getNombre() + " subió al nivel " + p.getNivel() + ".");
                }
            }
        }

        boolean ultima = partida.getNumeroBatallaActual() >= partida.getTotalBatallas();
        vistaResultados.mostrarResultado(new ResultadoDTO(
            victoria, ultima,
            victoria ? recompensa.getExperiencia() : 0,
            victoria ? recompensa.getOro() : 0,
            mensajes));
        ventana.mostrarResultados();
        batalla = null;
    }

    public void refrescarVista() {
        vistaBatalla.mostrarBatalla(crearEstadoBatallaDTO());
    }

    // ---- Auxiliares ----

    private boolean esTurnoDelJugador() {
        return batalla != null
            && !batalla.estaTerminada()
            && batalla.getTurnoActual() instanceof Personaje;
    }

    private void procesarTurnosEnemigos() {
        while (!batalla.estaTerminada() && batalla.getTurnoActual() instanceof Enemigo) {
            batalla.ejecutarTurno();
        }
    }

    private EstadoBatallaDTO crearEstadoBatallaDTO() {
        Entidad turno = batalla.getTurnoActual();
        boolean turnoPersonaje = !batalla.estaTerminada() && turno instanceof Personaje;

        int indiceTurno = -1;
        List<HabilidadDTO> habilidades = new ArrayList<>();
        List<String> items = new ArrayList<>();

        if (turnoPersonaje) {
            Personaje actual = (Personaje) turno;
            indiceTurno = batalla.getParty().getPersonajes().indexOf(actual);
            for (Habilidad h : actual.getHabilidades()) {
                habilidades.add(new HabilidadDTO(
                    h.getNombre(), h.getCostoMana(), h.afectaAliados(), h.puedeUsarse(actual)));
            }
            for (Item item : actual.getInventario().getItems()) {
                items.add(item.getNombre());
            }
        }

        return new EstadoBatallaDTO(
            ConversorDTO.entidades(batalla.getParty().getPersonajes()),
            ConversorDTO.entidad(batalla.getEnemigo()),
            turno == null ? "-" : turno.getNombre(),
            indiceTurno,
            habilidades,
            items,
            new ArrayList<>(batalla.getRegistro()),
            batalla.estaTerminada(),
            partida.getNumeroBatallaActual(),
            partida.getTotalBatallas());
    }
}
