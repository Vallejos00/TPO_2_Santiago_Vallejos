package util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;

/** Colores, fuentes y dibujo de imágenes pixel art para que todas las pantallas se vean parecidas. */
public final class Estilo {

    public static final Color FONDO = new Color(10, 16, 32);
    public static final Color CUADRO = new Color(8, 12, 26, 232);
    public static final Color CUADRO_SOLIDO = new Color(14, 20, 40);
    public static final Color BORDE = new Color(196, 206, 228);
    public static final Color BORDE_SUAVE = new Color(92, 106, 140);
    public static final Color TEXTO = new Color(238, 240, 250);
    public static final Color TEXTO_SUAVE = new Color(150, 162, 194);
    public static final Color ACENTO = new Color(236, 196, 92);
    public static final Color SELECCION = new Color(70, 84, 130, 200);

    public static final Color VIDA = new Color(210, 44, 52);
    public static final Color VIDA_OSCURA = new Color(86, 14, 22);
    public static final Color MANA = new Color(54, 110, 230);
    public static final Color MANA_OSCURO = new Color(14, 30, 86);

    public static final Font TITULO = new Font(Font.SERIF, Font.BOLD, 58);
    public static final Font ENCABEZADO = new Font(Font.SERIF, Font.BOLD, 34);
    public static final Font MENU = new Font(Font.SANS_SERIF, Font.PLAIN, 19);
    public static final Font NORMAL = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
    public static final Font NEGRITA = new Font(Font.SANS_SERIF, Font.BOLD, 14);
    public static final Font PEQUENIA = new Font(Font.SANS_SERIF, Font.PLAIN, 12);

    private Estilo() {
    }

    public static void suavizar(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    /** Dibuja una imagen llenando todo el rectángulo (recorta los bordes si hace falta), sin suavizado. */
    public static void dibujarCubriendo(Graphics2D g, ImageIcon icono, int ancho, int alto) {
        if (icono == null) {
            return;
        }
        Image img = icono.getImage();
        double escala = Math.max(ancho / (double) img.getWidth(null), alto / (double) img.getHeight(null));
        int w = (int) Math.ceil(img.getWidth(null) * escala);
        int h = (int) Math.ceil(img.getHeight(null) * escala);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g.drawImage(img, (ancho - w) / 2, (alto - h) / 2, w, h, null);
    }

    /**
     * Dibuja un sprite dentro de un rectángulo, centrado en horizontal y apoyado abajo,
     * manteniendo la proporción y sin suavizar (para que se vea pixel art).
     */
    public static void dibujarSprite(Graphics2D g, ImageIcon icono, int x, int y, int ancho, int alto) {
        Image img = icono.getImage();
        double escala = Math.min(ancho / (double) img.getWidth(null), alto / (double) img.getHeight(null));
        int w = (int) (img.getWidth(null) * escala);
        int h = (int) (img.getHeight(null) * escala);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g.drawImage(img, x + (ancho - w) / 2, y + alto - h, w, h, null);
    }

    /** Texto con sombra, centrado en x. */
    public static void textoCentrado(Graphics2D g, String texto, int centroX, int baseY, Color color, Color sombra) {
        int ancho = g.getFontMetrics().stringWidth(texto);
        int x = centroX - ancho / 2;
        g.setColor(sombra);
        g.drawString(texto, x + 3, baseY + 3);
        g.setColor(color);
        g.drawString(texto, x, baseY);
    }
}
