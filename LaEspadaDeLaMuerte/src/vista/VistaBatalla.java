package vista;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controlador.ControladorBatalla;
import dto.EntidadDTO;
import dto.EstadoBatallaDTO;
import util.Estilo;
import vista.paneles.PanelAcciones;
import vista.paneles.PanelEscenaBatalla;
import vista.paneles.PanelEstadoEntidad;
import vista.paneles.PanelFondo;
import vista.paneles.PanelRegistro;

/** Pantalla de batalla: mazmorra, party vs enemigo, mensajes arriba, estado y menú de acciones abajo. */
public class VistaBatalla extends PanelFondo {

    private static final long serialVersionUID = 1L;

    private final PanelRegistro panelRegistro = new PanelRegistro();
    private final PanelEscenaBatalla panelEscena = new PanelEscenaBatalla();
    private final JLabel lblInfo = new JLabel(" ", SwingConstants.RIGHT);
    private final List<PanelEstadoEntidad> panelesParty = new ArrayList<>();
    private final JPanel sur = new JPanel(new GridBagLayout());

    private PanelAcciones panelAcciones;

    public VistaBatalla() {
        super("fondo_batalla.png", 0);
        setLayout(new BorderLayout());

        lblInfo.setFont(Estilo.NEGRITA.deriveFont(16f));
        lblInfo.setForeground(Estilo.TEXTO);

        JPanel norte = new JPanel(new BorderLayout(16, 0));
        norte.setOpaque(false);
        norte.setBorder(BorderFactory.createEmptyBorder(14, 14, 0, 18));
        norte.add(panelRegistro, BorderLayout.WEST);
        norte.add(lblInfo, BorderLayout.EAST);
        panelRegistro.setPreferredSize(new java.awt.Dimension(560, 104));

        JPanel estados = new JPanel(new GridLayout(1, 4, 10, 0));
        estados.setOpaque(false);
        for (int i = 0; i < 4; i++) {
            PanelEstadoEntidad panel = new PanelEstadoEntidad();
            panelesParty.add(panel);
            estados.add(panel);
        }

        sur.setOpaque(false);
        sur.setBorder(BorderFactory.createEmptyBorder(0, 14, 14, 14));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.SOUTH;
        sur.add(estados, c);

        add(norte, BorderLayout.NORTH);
        add(panelEscena, BorderLayout.CENTER);
        add(sur, BorderLayout.SOUTH);
    }

    public void conectarControlador(ControladorBatalla controlador) {
        panelAcciones = new PanelAcciones(controlador);
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.weightx = 0;
        c.insets = new Insets(0, 14, 0, 0);
        c.anchor = GridBagConstraints.SOUTH;
        sur.add(panelAcciones, c);
    }

    public void mostrarBatalla(EstadoBatallaDTO estado) {
        lblInfo.setText("Batalla " + estado.getNumeroBatalla() + " de " + estado.getTotalBatallas()
            + (estado.isBatallaTerminada() ? "" : "   -   Turno de " + estado.getNombreTurnoActual()));

        List<EntidadDTO> party = estado.getParty();
        for (int i = 0; i < party.size() && i < panelesParty.size(); i++) {
            panelesParty.get(i).actualizar(party.get(i), i == estado.getIndiceTurnoParty());
        }
        panelEscena.actualizar(estado);
        panelRegistro.actualizar(estado.getRegistro());

        if (panelAcciones != null) {
            panelAcciones.actualizar(estado);
        }
    }

    public void mostrarMensaje(String mensaje) {
        panelRegistro.agregarLinea(mensaje);
    }
}
