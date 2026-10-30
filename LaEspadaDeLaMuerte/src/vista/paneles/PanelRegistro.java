package vista.paneles;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

import util.Estilo;

/** Cuadro de mensajes de la batalla (arriba a la izquierda): muestra las últimas líneas del registro. */
public class PanelRegistro extends CuadroRPG {

    private static final long serialVersionUID = 1L;
    private static final int LINEAS = 4;

    private final JTextArea texto = new JTextArea();
    private final List<String> lineas = new ArrayList<>();

    public PanelRegistro() {
        super(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));

        texto.setOpaque(false);
        texto.setEditable(false);
        texto.setFocusable(false);
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        texto.setFont(Estilo.NORMAL.deriveFont(15f));
        texto.setForeground(Estilo.TEXTO);
        texto.setRows(LINEAS);
        add(texto, BorderLayout.CENTER);
    }

    public void actualizar(List<String> registro) {
        lineas.clear();
        lineas.addAll(registro);
        refrescar();
    }

    public void agregarLinea(String linea) {
        lineas.add(linea);
        refrescar();
    }

    private void refrescar() {
        int desde = Math.max(0, lineas.size() - LINEAS);
        texto.setText(String.join("\n", lineas.subList(desde, lineas.size())));
    }
}
