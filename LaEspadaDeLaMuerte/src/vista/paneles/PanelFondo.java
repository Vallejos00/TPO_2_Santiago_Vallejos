package vista.paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import util.CargadorImagenes;
import util.Estilo;

/** Panel que dibuja una imagen de fondo (pixel art) ocupando todo su tamaño. */
public class PanelFondo extends JPanel {

    private static final long serialVersionUID = 1L;

    private final String imagen;
    private final int oscurecer;

    /** @param oscurecer 0 a 255: cuánto negro se superpone a la imagen (0 = nada). */
    public PanelFondo(String imagen, int oscurecer) {
        this.imagen = imagen;
        this.oscurecer = oscurecer;
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(Estilo.FONDO);
        g2.fillRect(0, 0, getWidth(), getHeight());
        Estilo.dibujarCubriendo(g2, CargadorImagenes.cargar(imagen), getWidth(), getHeight());
        if (oscurecer > 0) {
            g2.setColor(new Color(0, 0, 0, oscurecer));
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
        g2.dispose();
    }
}
