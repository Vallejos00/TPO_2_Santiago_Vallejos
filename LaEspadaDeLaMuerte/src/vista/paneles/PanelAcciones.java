package vista.paneles;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;

import controlador.ControladorBatalla;
import dto.EntidadDTO;
import dto.EstadoBatallaDTO;
import dto.HabilidadDTO;

/** Menú de acciones de la batalla: Atacar, Habilidad, Item, Defender (con submenús). */
public class PanelAcciones extends CuadroRPG {

    private static final long serialVersionUID = 1L;

    private final ControladorBatalla controlador;
    private final ListaMenu menu = new ListaMenu();

    private EstadoBatallaDTO estado;

    public PanelAcciones(ControladorBatalla controlador) {
        super(new BorderLayout());
        this.controlador = controlador;
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(290, 214));
        add(menu, BorderLayout.NORTH);
    }

    public void actualizar(EstadoBatallaDTO estado) {
        this.estado = estado;
        mostrarPrincipal();
    }

    private void mostrarPrincipal() {
        menu.limpiar();
        if (estado.isBatallaTerminada()) {
            menu.agregar("Continuar", true, controlador::finalizarBatalla);
        } else {
            boolean turno = estado.isTurnoJugador();
            menu.agregar("Atacar", turno, controlador::atacar);
            menu.agregar("Habilidad", turno && !estado.getHabilidades().isEmpty(), this::mostrarHabilidades);
            menu.agregar("Item", turno && !estado.getItems().isEmpty(), this::mostrarItems);
            menu.agregar("Defender", turno, controlador::defender);
        }
        menu.terminar();
    }

    private void mostrarHabilidades() {
        menu.limpiar();
        List<HabilidadDTO> habilidades = estado.getHabilidades();
        for (int i = 0; i < habilidades.size(); i++) {
            final int indice = i;
            HabilidadDTO h = habilidades.get(i);
            menu.agregar(h.getNombre() + "  " + h.getCostoMana() + " PM", h.isUtilizable(),
                () -> habilidadElegida(indice));
        }
        menu.agregar("Volver", true, this::mostrarPrincipal);
        menu.terminar();
    }

    private void habilidadElegida(int indice) {
        if (estado.getHabilidades().get(indice).isCuracion()) {
            mostrarAliados(indice);
        } else {
            controlador.usarHabilidad(indice, 0);
        }
    }

    private void mostrarAliados(int indiceHabilidad) {
        menu.limpiar();
        List<EntidadDTO> party = estado.getParty();
        for (int i = 0; i < party.size(); i++) {
            final int aliado = i;
            EntidadDTO p = party.get(i);
            if (p.isViva()) {
                menu.agregar(p.getNombre() + "  " + p.getVidaActual() + "/" + p.getVidaMaxima(), true,
                    () -> controlador.usarHabilidad(indiceHabilidad, aliado));
            }
        }
        menu.agregar("Volver", true, this::mostrarHabilidades);
        menu.terminar();
    }

    private void mostrarItems() {
        menu.limpiar();
        List<String> items = estado.getItems();
        for (int i = 0; i < items.size(); i++) {
            final int indice = i;
            menu.agregar(items.get(i), true, () -> controlador.usarItem(indice));
        }
        menu.agregar("Volver", true, this::mostrarPrincipal);
        menu.terminar();
    }
}
