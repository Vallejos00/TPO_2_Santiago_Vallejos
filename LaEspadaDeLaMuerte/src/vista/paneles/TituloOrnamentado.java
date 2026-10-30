package vista.paneles;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JComponent;

import util.Estilo;

/** Título centrado con líneas y rombos a los costados: —◆— Personajes —◆— */
public class TituloOrnamentado extends JComponent {

    private static final long serialVersionUID = 1L;

    private final String texto;

    public TituloOrnamentado(String texto) {
        this.texto = texto;
        setPreferredSize(new Dimension(400, 64));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        Estilo.suavizar(g2);
        int w = getWidth();
        int cy = getHeight() / 2;

        g2.setFont(Estilo.ENCABEZADO);
        int anchoTexto = g2.getFontMetrics().stringWidth(texto);
        int base = (getHeight() + g2.getFontMetrics().getAscent() - g2.getFontMetrics().getDescent()) / 2;
        Estilo.textoCentrado(g2, texto, w / 2, base, Estilo.TEXTO, Estilo.CUADRO_SOLIDO);

        int separacion = 26;
        int largo = Math.min(150, (w - anchoTexto) / 2 - separacion - 20);
        int izqFin = w / 2 - anchoTexto / 2 - separacion;
        int derIni = w / 2 + anchoTexto / 2 + separacion;

        g2.setColor(Estilo.BORDE_SUAVE);
        g2.drawLine(izqFin - largo, cy, izqFin, cy);
        g2.drawLine(derIni, cy, derIni + largo, cy);

        g2.setColor(Estilo.BORDE);
        rombo(g2, izqFin - largo, cy);
        rombo(g2, derIni + largo, cy);
        g2.dispose();
    }

    private void rombo(Graphics2D g2, int cx, int cy) {
        g2.fill(new Polygon(new int[] {cx - 5, cx, cx + 5, cx}, new int[] {cy, cy - 5, cy, cy + 5}, 4));
    }
}
