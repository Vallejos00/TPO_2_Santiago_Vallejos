package vista.paneles;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import util.Estilo;

/** Menú vertical con flecha ▶ en la opción elegida. Se maneja con el mouse o con ↑ ↓ Enter. */
public class ListaMenu extends JPanel {

    private static final long serialVersionUID = 1L;

    private final List<Fila> filas = new ArrayList<>();
    private int seleccion = -1;

    public ListaMenu() {
        setOpaque(false);
        setLayout(new GridLayout(0, 1, 0, 2));

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "arriba");
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "abajo");
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "activar");
        getActionMap().put("arriba", new AbstractAction() {
            private static final long serialVersionUID = 1L;
            @Override public void actionPerformed(ActionEvent e) { mover(-1); }
        });
        getActionMap().put("abajo", new AbstractAction() {
            private static final long serialVersionUID = 1L;
            @Override public void actionPerformed(ActionEvent e) { mover(1); }
        });
        getActionMap().put("activar", new AbstractAction() {
            private static final long serialVersionUID = 1L;
            @Override public void actionPerformed(ActionEvent e) { activar(); }
        });
    }

    public void limpiar() {
        removeAll();
        filas.clear();
        seleccion = -1;
    }

    public void agregar(String texto, boolean habilitado, Runnable accion) {
        Fila fila = new Fila(filas.size(), texto, habilitado, accion);
        filas.add(fila);
        add(fila);
        if (seleccion < 0 && habilitado) {
            seleccion = fila.indice;
        }
    }

    /** Llamar después de agregar todas las opciones. */
    public void terminar() {
        revalidate();
        repaint();
    }

    private void mover(int delta) {
        if (filas.isEmpty()) {
            return;
        }
        int n = filas.size();
        int actual = seleccion < 0 ? (delta > 0 ? -1 : 0) : seleccion;
        for (int i = 0; i < n; i++) {
            actual = (actual + delta + n) % n;
            if (filas.get(actual).habilitado) {
                seleccion = actual;
                repaint();
                return;
            }
        }
    }

    private void activar() {
        if (seleccion >= 0 && seleccion < filas.size() && filas.get(seleccion).habilitado) {
            filas.get(seleccion).accion.run();
        }
    }

    private class Fila extends JComponent {

        private static final long serialVersionUID = 1L;

        private final int indice;
        private final String texto;
        private final boolean habilitado;
        private final Runnable accion;

        Fila(int indice, String texto, boolean habilitado, Runnable accion) {
            this.indice = indice;
            this.texto = texto;
            this.habilitado = habilitado;
            this.accion = accion;

            addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) {
                    if (Fila.this.habilitado) {
                        seleccion = Fila.this.indice;
                        ListaMenu.this.repaint();
                    }
                }
                @Override public void mouseClicked(MouseEvent e) {
                    if (Fila.this.habilitado) {
                        Fila.this.accion.run();
                    }
                }
            });
        }

        @Override
        public Dimension getPreferredSize() {
            int ancho = 46 + getFontMetrics(Estilo.MENU).stringWidth(texto) + 14;
            return new Dimension(ancho, 34);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            Estilo.suavizar(g2);
            boolean elegida = seleccion == indice && habilitado;

            if (elegida) {
                g2.setColor(Estilo.SELECCION);
                g2.fillRoundRect(0, 1, getWidth(), getHeight() - 2, 10, 10);
                int cy = getHeight() / 2;
                g2.setColor(Estilo.TEXTO);
                g2.fill(new Polygon(new int[] {14, 14, 26}, new int[] {cy - 7, cy + 7, cy}, 3));
            }

            g2.setFont(Estilo.MENU);
            g2.setColor(habilitado ? Estilo.TEXTO : new java.awt.Color(110, 120, 150));
            int base = (getHeight() + g2.getFontMetrics().getAscent() - g2.getFontMetrics().getDescent()) / 2;
            g2.drawString(texto, 38, base);
            g2.dispose();
        }
    }
}
