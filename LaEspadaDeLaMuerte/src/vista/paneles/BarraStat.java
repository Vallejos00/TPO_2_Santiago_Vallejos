package vista.paneles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import util.Estilo;

/** Barra de PV / PM: etiqueta a la izquierda y "actual/máximo" a la derecha, como en el ejemplo. */
public class BarraStat extends JComponent {

    private static final long serialVersionUID = 1L;

    private final String etiqueta;
    private final Color relleno;
    private final Color fondo;

    private int valor;
    private int maximo = 1;

    public BarraStat(String etiqueta, Color relleno, Color fondo) {
        this.etiqueta = etiqueta;
        this.relleno = relleno;
        this.fondo = fondo;
        setPreferredSize(new Dimension(150, 20));
    }

    public void setValores(int valor, int maximo) {
        this.valor = Math.max(0, valor);
        this.maximo = Math.max(1, maximo);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        Estilo.suavizar(g2);
        int w = getWidth();
        int h = getHeight();

        g2.setColor(fondo);
        g2.fillRoundRect(0, 0, w, h, 8, 8);

        int ancho = (int) Math.round(w * Math.min(1.0, valor / (double) maximo));
        if (ancho > 0) {
            g2.setClip(0, 0, ancho, h);
            g2.setColor(relleno);
            g2.fillRoundRect(0, 0, w, h, 8, 8);
            g2.setColor(new Color(255, 255, 255, 60));
            g2.fillRect(0, 2, w, h / 3);
            g2.setClip(null);
        }

        g2.setColor(new Color(0, 0, 0, 160));
        g2.drawRoundRect(0, 0, w - 1, h - 1, 8, 8);

        g2.setFont(Estilo.NEGRITA.deriveFont(13f));
        int base = (h + g2.getFontMetrics().getAscent() - g2.getFontMetrics().getDescent()) / 2;
        String numeros = valor + "/" + maximo;
        int xNumeros = w - 6 - g2.getFontMetrics().stringWidth(numeros);

        g2.setColor(Color.BLACK);
        g2.drawString(etiqueta, 7, base + 1);
        g2.drawString(numeros, xNumeros + 1, base + 1);
        g2.setColor(Estilo.TEXTO);
        g2.drawString(etiqueta, 6, base);
        g2.drawString(numeros, xNumeros, base);
        g2.dispose();
    }
}
