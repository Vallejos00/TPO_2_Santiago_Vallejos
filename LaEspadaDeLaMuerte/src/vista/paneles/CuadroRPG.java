package vista.paneles;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import util.Estilo;

/** Cuadro oscuro con doble borde, como las ventanas de un RPG clásico. */
public class CuadroRPG extends JPanel {

    private static final long serialVersionUID = 1L;

    private Color colorBorde = Estilo.BORDE;

    public CuadroRPG(LayoutManager layout) {
        super(layout);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
    }

    public void setColorBorde(Color color) {
        this.colorBorde = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        Estilo.suavizar(g2);
        int w = getWidth();
        int h = getHeight();

        g2.setColor(Estilo.CUADRO);
        g2.fillRoundRect(1, 1, w - 3, h - 3, 16, 16);

        g2.setColor(colorBorde);
        g2.setStroke(new BasicStroke(2f));
        g2.drawRoundRect(1, 1, w - 3, h - 3, 16, 16);

        g2.setColor(Estilo.BORDE_SUAVE);
        g2.setStroke(new BasicStroke(1f));
        g2.drawRoundRect(5, 5, w - 11, h - 11, 10, 10);
        g2.dispose();
        super.paintComponent(g);
    }
}
