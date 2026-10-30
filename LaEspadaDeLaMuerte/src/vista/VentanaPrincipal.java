package vista;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import util.Estilo;

/** Ventana única: cada pantalla del juego (vista) es una "carta" que se muestra por turnos. */
public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final String INICIO = "INICIO";
    private static final String PARTY = "PARTY";
    private static final String BATALLA = "BATALLA";
    private static final String RESULTADOS = "RESULTADOS";

    private final CardLayout cartas = new CardLayout();
    private final JPanel contenedor = new JPanel(cartas);

    public VentanaPrincipal(VistaInicio inicio, VistaParty party,
                            VistaBatalla batalla, VistaResultados resultados) {
        setTitle("La Espada de la Muerte");
        setSize(1200, 780);
        setMinimumSize(new Dimension(1024, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        contenedor.setBackground(Estilo.FONDO);
        contenedor.add(inicio, INICIO);
        contenedor.add(party, PARTY);
        contenedor.add(batalla, BATALLA);
        contenedor.add(resultados, RESULTADOS);
        setContentPane(contenedor);
    }

    public void mostrarInicio() { cartas.show(contenedor, INICIO); }
    public void mostrarParty() { cartas.show(contenedor, PARTY); }
    public void mostrarBatalla() { cartas.show(contenedor, BATALLA); }
    public void mostrarResultados() { cartas.show(contenedor, RESULTADOS); }
}
