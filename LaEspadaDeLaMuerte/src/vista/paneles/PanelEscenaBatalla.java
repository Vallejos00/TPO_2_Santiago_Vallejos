package vista.paneles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import dto.EntidadDTO;
import dto.EstadoBatallaDTO;
import util.CargadorImagenes;
import util.Estilo;

/** Dibuja a la party (a la izquierda) y al enemigo (a la derecha) sobre el fondo de la mazmorra. */
public class PanelEscenaBatalla extends JPanel {

    private static final long serialVersionUID = 1L;

    private EstadoBatallaDTO estado;

    public PanelEscenaBatalla() {
        setOpaque(false);
    }

    public void actualizar(EstadoBatallaDTO estado) {
        this.estado = estado;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (estado == null) {
            return;
        }
        Graphics2D g2 = (Graphics2D) g.create();
        Estilo.suavizar(g2);
        int w = getWidth();
        int h = getHeight();

        dibujarParty(g2, w, h);
        dibujarEnemigo(g2, w, h);
        g2.dispose();
    }

    private void dibujarParty(Graphics2D g2, int w, int h) {
        List<EntidadDTO> party = estado.getParty();
        int cajaAncho = (int) (w * 0.125);
        int cajaAlto = (int) (h * 0.62);

        for (int i = 0; i < party.size(); i++) {
            EntidadDTO p = party.get(i);
            int x = (int) (w * 0.05) + i * (int) (w * 0.118);
            int y = (int) (h * 0.30) + (i % 2 == 0 ? 0 : (int) (h * 0.05));

            sombra(g2, x + cajaAncho / 2, y + cajaAlto - 2, cajaAncho);
            dibujarFigura(g2, p, x, y, cajaAncho, cajaAlto, p.isViva() ? 1f : 0.35f);

            if (i == estado.getIndiceTurnoParty()) {
                int cx = x + cajaAncho / 2;
                g2.setColor(Estilo.ACENTO);
                g2.fill(new Polygon(new int[] {cx - 9, cx + 9, cx}, new int[] {y - 20, y - 20, y - 6}, 3));
            }
        }
    }

    private void dibujarEnemigo(Graphics2D g2, int w, int h) {
        EntidadDTO e = estado.getEnemigo();
        int cajaAncho = (int) (w * 0.27);
        int cajaAlto = (int) (h * 0.70);
        int x = (int) (w * 0.64);
        int y = (int) (h * 0.30);

        sombra(g2, x + cajaAncho / 2, y + cajaAlto - 2, cajaAncho * 3 / 4);
        dibujarFigura(g2, e, x, y, cajaAncho, cajaAlto, e.isViva() ? 1f : 0.3f);

        // nombre y barra de vida del enemigo
        int barraAncho = 230;
        int bx = x + (cajaAncho - barraAncho) / 2;
        int by = Math.max(4, y - 44);
        g2.setFont(Estilo.NEGRITA.deriveFont(16f));
        Estilo.textoCentrado(g2, e.getNombre(), x + cajaAncho / 2, by + 14, Estilo.TEXTO, Color.BLACK);

        BarraStat barra = new BarraStat("PV", Estilo.VIDA, Estilo.VIDA_OSCURA);
        barra.setValores(e.getVidaActual(), e.getVidaMaxima());
        barra.setSize(barraAncho, 20);
        Graphics2D gb = (Graphics2D) g2.create(bx, by + 22, barraAncho, 20);
        barra.paint(gb);
        gb.dispose();
    }

    private void dibujarFigura(Graphics2D g2, EntidadDTO e, int x, int y, int ancho, int alto, float opacidad) {
        ImageIcon icono = CargadorImagenes.cargar(e.getSprite());
        java.awt.Composite anterior = g2.getComposite();
        g2.setComposite(java.awt.AlphaComposite.getInstance(java.awt.AlphaComposite.SRC_OVER, opacidad));
        if (icono != null) {
            Estilo.dibujarSprite(g2, icono, x, y, ancho, alto);
        } else {
            g2.setColor(new Color(20, 26, 48, 200));
            g2.fillRoundRect(x, y + alto / 4, ancho, alto * 3 / 4, 12, 12);
            g2.setFont(Estilo.PEQUENIA);
            Estilo.textoCentrado(g2, "SIN SPRITE", x + ancho / 2, y + alto / 2, Estilo.TEXTO_SUAVE, Color.BLACK);
            Estilo.textoCentrado(g2, e.getNombre(), x + ancho / 2, y + alto / 2 + 16, Estilo.TEXTO, Color.BLACK);
        }
        g2.setComposite(anterior);
    }

    private void sombra(Graphics2D g2, int cx, int cy, int ancho) {
        g2.setColor(new Color(0, 0, 0, 90));
        g2.fillOval(cx - ancho / 2, cy - 8, ancho, 16);
    }
}
