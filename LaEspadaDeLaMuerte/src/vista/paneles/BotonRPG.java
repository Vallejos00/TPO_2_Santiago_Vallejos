package vista.paneles;

import java.awt.BasicStroke;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import util.Estilo;

/** Botón con el mismo estilo de los cuadros del juego. */
public class BotonRPG extends JButton {

    private static final long serialVersionUID = 1L;

    private boolean encima;

    public BotonRPG(String texto) {
        super(texto);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setFont(Estilo.MENU);
        setForeground(Estilo.TEXTO);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Dimension tamanio = new Dimension(230, 44);
        setPreferredSize(tamanio);
        setMinimumSize(tamanio);
        setMaximumSize(tamanio);

        addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { encima = true; repaint(); }
            @Override public void mouseExited(MouseEvent e) { encima = false; repaint(); }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        Estilo.suavizar(g2);
        int w = getWidth();
        int h = getHeight();

        g2.setColor(encima && isEnabled() ? Estilo.SELECCION : Estilo.CUADRO);
        g2.fillRoundRect(1, 1, w - 3, h - 3, 14, 14);
        g2.setColor(isEnabled() ? Estilo.BORDE : Estilo.BORDE_SUAVE);
        g2.setStroke(new BasicStroke(2f));
        g2.drawRoundRect(1, 1, w - 3, h - 3, 14, 14);

        g2.setFont(getFont());
        g2.setColor(isEnabled() ? Estilo.TEXTO : new java.awt.Color(110, 120, 150));
        int x = (w - g2.getFontMetrics().stringWidth(getText())) / 2;
        int y = (h + g2.getFontMetrics().getAscent() - g2.getFontMetrics().getDescent()) / 2;
        g2.drawString(getText(), x, y);
        g2.dispose();
    }
}
