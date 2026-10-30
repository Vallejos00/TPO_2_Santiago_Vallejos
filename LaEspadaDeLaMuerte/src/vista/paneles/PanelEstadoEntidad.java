package vista.paneles;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import dto.EntidadDTO;
import util.Estilo;

/** Cuadro con el nombre, la barra de PV y la de PM de un personaje. */
public class PanelEstadoEntidad extends CuadroRPG {

    private static final long serialVersionUID = 1L;

    private final JLabel lblNombre = new JLabel("-");
    private final BarraStat barraVida = new BarraStat("PV", Estilo.VIDA, Estilo.VIDA_OSCURA);
    private final BarraStat barraMana = new BarraStat("PM", Estilo.MANA, Estilo.MANA_OSCURO);

    public PanelEstadoEntidad() {
        super(null);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(9, 14, 9, 14));

        lblNombre.setFont(Estilo.NEGRITA.deriveFont(15f));
        lblNombre.setForeground(Estilo.TEXTO);
        lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        barraVida.setAlignmentX(Component.LEFT_ALIGNMENT);
        barraMana.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(lblNombre);
        add(javax.swing.Box.createVerticalStrut(4));
        add(barraVida);
        add(javax.swing.Box.createVerticalStrut(3));
        add(barraMana);
    }

    public void actualizar(EntidadDTO entidad, boolean turnoActual) {
        String nombre = entidad.getNombre();
        if (!entidad.isViva()) {
            nombre += "  (KO)";
        } else if (entidad.isDefendiendo()) {
            nombre += "  (defiende)";
        }
        lblNombre.setText(nombre);
        lblNombre.setForeground(entidad.isViva() ? Estilo.TEXTO : Estilo.TEXTO_SUAVE);

        barraVida.setValores(entidad.getVidaActual(), entidad.getVidaMaxima());
        barraMana.setValores(entidad.getManaActual(), entidad.getManaMaximo());

        setColorBorde(turnoActual ? Estilo.ACENTO : Estilo.BORDE);
    }

    /** Para dar un toque de color al borde cuando el personaje no puede jugar. */
    public void setApagado(boolean apagado) {
        setColorBorde(apagado ? new Color(90, 96, 120) : Estilo.BORDE);
    }
}
