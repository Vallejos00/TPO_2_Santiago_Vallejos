package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controlador.ControladorPartida;
import dto.EntidadDTO;
import dto.EstadoPartidaDTO;
import util.Estilo;
import vista.paneles.BotonRPG;
import vista.paneles.PanelFondo;
import vista.paneles.TarjetaPersonaje;
import vista.paneles.TituloOrnamentado;

/** Pantalla "Personajes": la party de 4 y el próximo enemigo, con sus estadísticas. */
public class VistaParty extends PanelFondo {

    private static final long serialVersionUID = 1L;

    private final JLabel lblInfo = new JLabel(" ", SwingConstants.CENTER);
    private final JLabel lblMensaje = new JLabel(" ", SwingConstants.CENTER);

    private final List<TarjetaPersonaje> tarjetas = new ArrayList<>();
    private final TarjetaPersonaje tarjetaEnemigo = new TarjetaPersonaje(true);

    private final BotonRPG btnBatalla = new BotonRPG("Comenzar batalla");
    private final BotonRPG btnGuardar = new BotonRPG("Guardar partida");
    private final BotonRPG btnMenu = new BotonRPG("Menú principal");

    public VistaParty() {
        super("fondo_party.png", 0);
        setLayout(new BorderLayout(0, 10));
        setBorder(BorderFactory.createEmptyBorder(14, 24, 18, 24));

        lblInfo.setFont(Estilo.NEGRITA.deriveFont(16f));
        lblInfo.setForeground(Estilo.ACENTO);
        lblMensaje.setFont(Estilo.NORMAL);
        lblMensaje.setForeground(Estilo.TEXTO_SUAVE);

        JPanel norte = new JPanel(new BorderLayout());
        norte.setOpaque(false);
        norte.add(new TituloOrnamentado("Personajes"), BorderLayout.CENTER);
        norte.add(lblInfo, BorderLayout.SOUTH);

        JPanel centro = new JPanel(new GridLayout(1, 5, 14, 0));
        centro.setOpaque(false);
        centro.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        for (int i = 0; i < 4; i++) {
            TarjetaPersonaje tarjeta = new TarjetaPersonaje(false);
            tarjetas.add(tarjeta);
            centro.add(tarjeta);
        }
        centro.add(tarjetaEnemigo);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 16, 0));
        botones.setOpaque(false);
        botones.add(btnBatalla);
        botones.add(btnGuardar);
        botones.add(btnMenu);

        JPanel sur = new JPanel();
        sur.setOpaque(false);
        sur.setLayout(new BoxLayout(sur, BoxLayout.Y_AXIS));
        botones.setAlignmentX(CENTER_ALIGNMENT);
        lblMensaje.setAlignmentX(CENTER_ALIGNMENT);
        sur.add(botones);
        sur.add(javax.swing.Box.createVerticalStrut(8));
        sur.add(lblMensaje);

        add(norte, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
        add(sur, BorderLayout.SOUTH);
    }

    public void conectarControlador(ControladorPartida controlador) {
        btnBatalla.addActionListener(e -> controlador.comenzarBatalla());
        btnGuardar.addActionListener(e -> controlador.guardarPartida());
        btnMenu.addActionListener(e -> controlador.volverAlInicio(" "));
    }

    public void mostrarParty(EstadoPartidaDTO estado) {
        lblInfo.setText("Oro: " + estado.getOro()
            + "     Batalla " + estado.getNumeroBatalla() + " de " + estado.getTotalBatallas()
            + "     Próximo rival: " + estado.getProximoEnemigo().getNombre());

        List<EntidadDTO> personajes = estado.getPersonajes();
        for (int i = 0; i < personajes.size() && i < tarjetas.size(); i++) {
            tarjetas.get(i).actualizar(personajes.get(i));
        }
        tarjetaEnemigo.actualizar(estado.getProximoEnemigo());
    }

    public void mostrarMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
    }
}
