package controlador;

import java.util.Arrays;

import dto.EstadoPartidaDTO;
import modelo.Arquero;
import modelo.Curador;
import modelo.GestorPartida;
import modelo.Guerrero;
import modelo.Mago;
import modelo.Partida;
import modelo.Party;
import modelo.Personaje;
import vista.VentanaPrincipal;
import vista.VistaInicio;
import vista.VistaParty;

public class ControladorPartida {

    private final GestorPartida gestor;
    private final VentanaPrincipal ventana;
    private final VistaInicio vistaInicio;
    private final VistaParty vistaParty;

    private ControladorBatalla controladorBatalla;
    private Partida partida;

    public ControladorPartida(GestorPartida gestor, VentanaPrincipal ventana,
                              VistaInicio vistaInicio, VistaParty vistaParty) {
        this.gestor = gestor;
        this.ventana = ventana;
        this.vistaInicio = vistaInicio;
        this.vistaParty = vistaParty;
    }

    public void setControladorBatalla(ControladorBatalla controladorBatalla) {
        this.controladorBatalla = controladorBatalla;
    }

    public void iniciarPartida() {
        Party party = new Party(Arrays.<Personaje>asList(
            new Guerrero("Guerrero"),
            new Mago("Mago"),
            new Arquero("Arquero"),
            new Curador("Curador")));

        partida = new Partida(party);
        partida.iniciarPartida();
        controladorBatalla.setPartida(partida);
        mostrarParty();
    }

    /** Se llama al tocar "Continuar" en la pantalla de resultados. */
    public void avanzarBatalla() {
        if (partida.getParty().estaDerrotada()) {
            volverAlInicio("Tu party fue derrotada. Fin de la partida.");
            return;
        }
        partida.avanzarBatalla();
        if (partida.estaTerminada()) {
            volverAlInicio("¡Derrotaste a La Muerte! Completaste La Espada de la Muerte.");
            return;
        }
        mostrarParty();
    }

    public void comenzarBatalla() {
        controladorBatalla.iniciarBatalla();
    }

    public void guardarPartida() {
        try {
            gestor.guardar(partida);
            vistaParty.mostrarMensaje("Partida guardada.");
        } catch (RuntimeException e) {
            vistaParty.mostrarMensaje(e.getMessage());
        }
    }

    public void cargarPartida() {
        try {
            partida = gestor.cargar();
            controladorBatalla.setPartida(partida);
            mostrarParty();
        } catch (RuntimeException e) {
            vistaInicio.mostrarMensaje(e.getMessage());
        }
    }

    public boolean hayPartidaGuardada() {
        return gestor.existePartidaGuardada();
    }

    public void volverAlInicio(String mensaje) {
        vistaInicio.mostrarMenu();
        vistaInicio.mostrarMensaje(mensaje);
        ventana.mostrarInicio();
    }

    private void mostrarParty() {
        vistaParty.mostrarParty(new EstadoPartidaDTO(
            ConversorDTO.entidades(partida.getParty().getPersonajes()),
            ConversorDTO.entidad(partida.getBatallaActual().getEnemigo()),
            partida.getOro(),
            partida.getNumeroBatallaActual(),
            partida.getTotalBatallas()));
        vistaParty.mostrarMensaje(" ");
        ventana.mostrarParty();
    }
}
