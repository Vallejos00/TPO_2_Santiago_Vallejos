package vista.paneles;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import dto.EntidadDTO;
import util.CargadorImagenes;
import util.Estilo;

/** Tarjeta de un personaje (o del próximo enemigo): sprite, nombre, descripción y estadísticas. */
public class TarjetaPersonaje extends JPanel {

    private static final long serialVersionUID = 1L;

    private final boolean enemigo;
    private EntidadDTO entidad;

    public TarjetaPersonaje(boolean enemigo) {
        this.enemigo = enemigo;
        setOpaque(false);
    }

    public void actualizar(EntidadDTO entidad) {
        this.entidad = entidad;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        Estilo.suavizar(g2);
        int w = getWidth();
        int h = getHeight();

        Color borde = enemigo ? new Color(176, 130, 236) : Estilo.BORDE_SUAVE;
        g2.setColor(enemigo ? new Color(22, 16, 44, 235) : new Color(12, 18, 38, 225));
        g2.fillRoundRect(0, 0, w - 1, h - 1, 14, 14);
        g2.setColor(borde);
        g2.setStroke(new BasicStroke(2f));
        g2.drawRoundRect(0, 0, w - 1, h - 1, 14, 14);

        if (entidad == null) {
            g2.dispose();
            return;
        }

        // sprite
        int zonaAlto = (int) (h * 0.63);
        ImageIcon icono = CargadorImagenes.cargar(entidad.getSprite());
        if (icono != null) {
            Estilo.dibujarSprite(g2, icono, 14, 16, w - 28, zonaAlto - 8);
        } else {
            g2.setFont(Estilo.PEQUENIA);
            Estilo.textoCentrado(g2, "SIN SPRITE", w / 2, zonaAlto / 2, Estilo.TEXTO_SUAVE, Color.BLACK);
        }

        // separador
        int y = 16 + zonaAlto + 6;
        g2.setColor(Estilo.BORDE_SUAVE);
        g2.setStroke(new BasicStroke(1f));
        g2.drawLine(16, y, w - 16, y);

        // nombre
        y += 28;
        g2.setFont(Estilo.NEGRITA.deriveFont(20f));
        Estilo.textoCentrado(g2, entidad.getNombre(), w / 2, y, Estilo.TEXTO, Color.BLACK);

        // descripción (hasta 2 líneas)
        g2.setFont(Estilo.PEQUENIA.deriveFont(13f));
        g2.setColor(Estilo.TEXTO_SUAVE);
        FontMetrics fm = g2.getFontMetrics();
        for (String linea : partir(entidad.getDescripcion(), fm, w - 28)) {
            y += 17;
            g2.drawString(linea, (w - fm.stringWidth(linea)) / 2, y);
        }

        // estadísticas
        y = h - 46;
        g2.setFont(Estilo.PEQUENIA);
        g2.setColor(Estilo.ACENTO);
        String l1 = entidad.getNivel() > 0
            ? "Nv. " + entidad.getNivel() + "   PV " + entidad.getVidaActual() + "/" + entidad.getVidaMaxima()
            : "PV " + entidad.getVidaActual() + "/" + entidad.getVidaMaxima();
        String l2 = "PM " + entidad.getManaActual() + "/" + entidad.getManaMaximo()
            + "   ATQ " + entidad.getAtaque() + "   DEF " + entidad.getDefensa();
        FontMetrics fm2 = g2.getFontMetrics();
        g2.drawString(l1, (w - fm2.stringWidth(l1)) / 2, y);
        g2.setColor(Estilo.TEXTO_SUAVE);
        g2.drawString(l2, (w - fm2.stringWidth(l2)) / 2, y + 18);
        g2.dispose();
    }

    private static List<String> partir(String texto, FontMetrics fm, int ancho) {
        List<String> lineas = new ArrayList<>();
        StringBuilder actual = new StringBuilder();
        for (String palabra : texto.split(" ")) {
            String prueba = actual.length() == 0 ? palabra : actual + " " + palabra;
            if (fm.stringWidth(prueba) > ancho && actual.length() > 0) {
                lineas.add(actual.toString());
                actual = new StringBuilder(palabra);
            } else {
                actual = new StringBuilder(prueba);
            }
        }
        if (actual.length() > 0) {
            lineas.add(actual.toString());
        }
        return lineas;
    }
}
