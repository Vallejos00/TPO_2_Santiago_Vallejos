package vista;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.ControladorPartida;
import dto.ResultadoDTO;
import util.Estilo;
import vista.paneles.BotonRPG;
import vista.paneles.CuadroRPG;
import vista.paneles.PanelFondo;

public class VistaResultados extends PanelFondo {

    private static final long serialVersionUID = 1L;

    private final JLabel lblTitulo = new JLabel(" ");
    private final JLabel lblRecompensa = new JLabel(" ");
    private final JPanel panelMensajes = new JPanel();
    private final BotonRPG btnContinuar = new BotonRPG("Continuar");

    public VistaResultados() {
        super("fondo_party.png", 60);
        setLayout(new GridBagLayout());

        CuadroRPG cuadro = new CuadroRPG(null);
        cuadro.setLayout(new BoxLayout(cuadro, BoxLayout.Y_AXIS));
        cuadro.setBorder(BorderFactory.createEmptyBorder(30, 60, 30, 60));

        lblTitulo.setFont(Estilo.TITULO.deriveFont(52f));
        lblRecompensa.setFont(Estilo.ENCABEZADO.deriveFont(24f));
        lblRecompensa.setForeground(Estilo.TEXTO);

        panelMensajes.setOpaque(false);
        panelMensajes.setLayout(new BoxLayout(panelMensajes, BoxLayout.Y_AXIS));

        centrar(lblTitulo, cuadro);
        cuadro.add(Box.createRigidArea(new Dimension(0, 18)));
        centrar(lblRecompensa, cuadro);
        cuadro.add(Box.createRigidArea(new Dimension(0, 14)));
        centrar(panelMensajes, cuadro);
        cuadro.add(Box.createRigidArea(new Dimension(0, 28)));
        centrar(btnContinuar, cuadro);

        add(cuadro);
    }

    public void conectarControlador(ControladorPartida controlador) {
        btnContinuar.addActionListener(e -> controlador.avanzarBatalla());
    }

    public void mostrarResultado(ResultadoDTO resultado) {
        panelMensajes.removeAll();

        if (resultado.isVictoria()) {
            lblTitulo.setText("¡Victoria!");
            lblTitulo.setForeground(Estilo.ACENTO);
            lblRecompensa.setText("Recompensa: +" + resultado.getExperiencia()
                + " XP y +" + resultado.getOro() + " de oro");
            btnContinuar.setText(resultado.isUltimaBatalla() ? "Finalizar" : "Continuar");
        } else {
            lblTitulo.setText("Derrota");
            lblTitulo.setForeground(Estilo.VIDA);
            lblRecompensa.setText("Tu party cayó en combate.");
            btnContinuar.setText("Volver al menú");
        }

        for (String mensaje : resultado.getMensajes()) {
            JLabel linea = new JLabel(mensaje);
            linea.setFont(Estilo.NEGRITA.deriveFont(16f));
            linea.setForeground(Estilo.ACENTO);
            linea.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelMensajes.add(linea);
        }

        panelMensajes.revalidate();
        panelMensajes.repaint();
    }

    public void mostrarMensaje(String mensaje) {
        lblRecompensa.setText(mensaje);
    }

    private void centrar(java.awt.Component componente, JPanel padre) {
        ((javax.swing.JComponent) componente).setAlignmentX(Component.CENTER_ALIGNMENT);
        padre.add(componente);
    }
}
