package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controlador.ControladorPartida;
import util.Estilo;
import vista.paneles.CuadroRPG;
import vista.paneles.ListaMenu;
import vista.paneles.PanelFondo;

/** Menú de inicio: título sobre el paisaje y menú Nueva Partida / Cargar Partida / Salir. */
public class VistaInicio extends PanelFondo {

    private static final long serialVersionUID = 1L;

    private final CuadroRPG cuadroMenu = new CuadroRPG(new BorderLayout());
    private final ListaMenu menu = new ListaMenu();
    private final JLabel lblMensaje = new JLabel(" ", SwingConstants.CENTER);

    private ControladorPartida controlador;

    public VistaInicio() {
        super("fondo_inicio.png", 0);
        setLayout(null);

        cuadroMenu.add(menu, BorderLayout.CENTER);
        lblMensaje.setFont(Estilo.NEGRITA.deriveFont(16f));
        lblMensaje.setForeground(Estilo.ACENTO);

        add(cuadroMenu);
        add(lblMensaje);
    }

    public void conectarControlador(ControladorPartida controlador) {
        this.controlador = controlador;
        mostrarMenu();
    }

    /** Arma el menú: "Cargar Partida" solo se habilita si existe una partida guardada. */
    public void mostrarMenu() {
        menu.limpiar();
        menu.agregar("Nueva Partida", true, controlador::iniciarPartida);
        menu.agregar("Cargar Partida", controlador.hayPartidaGuardada(), controlador::cargarPartida);
        menu.agregar("Salir", true, () -> System.exit(0));
        menu.terminar();
        lblMensaje.setText(" ");
    }

    public void mostrarMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
    }

    @Override
    public void doLayout() {
        int w = getWidth();
        int h = getHeight();
        int centro = (int) (w * 0.33);
        int ancho = 320;
        int alto = 3 * 36 + 34;
        cuadroMenu.setBounds(centro - ancho / 2, (int) (h * 0.46), ancho, alto);
        lblMensaje.setBounds(centro - 260, (int) (h * 0.46) + alto + 16, 520, 26);
    }

    @Override
    protected void paintChildren(Graphics g) {
        dibujarTitulo((Graphics2D) g);
        super.paintChildren(g);
    }

    private void dibujarTitulo(Graphics2D g) {
        Graphics2D g2 = (Graphics2D) g.create();
        Estilo.suavizar(g2);
        int centro = (int) (getWidth() * 0.33);
        int y = (int) (getHeight() * 0.20);

        g2.setFont(Estilo.TITULO);
        Estilo.textoCentrado(g2, "La Espada", centro, y, new Color(236, 240, 252), new Color(10, 14, 36));
        Estilo.textoCentrado(g2, "de la Muerte", centro, y + 64, new Color(236, 240, 252), new Color(10, 14, 36));

        // espada ornamental bajo el título
        int cy = y + 100;
        g2.setColor(new Color(176, 188, 214));
        g2.fillRect(centro - 120, cy - 1, 240, 3);
        g2.setColor(new Color(236, 240, 252));
        g2.fillRect(centro - 18, cy - 7, 36, 4);
        g2.fillRect(centro - 3, cy - 14, 6, 28);
        g2.fill(new Polygon(new int[] {centro - 128, centro - 118, centro - 118}, new int[] {cy, cy - 4, cy + 4}, 3));
        g2.fill(new Polygon(new int[] {centro + 128, centro + 118, centro + 118}, new int[] {cy, cy - 4, cy + 4}, 3));
        g2.dispose();
    }
}
