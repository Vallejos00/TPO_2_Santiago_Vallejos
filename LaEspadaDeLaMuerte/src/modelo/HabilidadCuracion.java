package modelo;

public class HabilidadCuracion extends Habilidad {

    private static final long serialVersionUID = 1L;

    private final int curacion;

    public HabilidadCuracion(String nombre, int costoMana, int curacion) {
        super(nombre, costoMana);
        this.curacion = curacion;
    }

    @Override
    public boolean afectaAliados() {
        return true;
    }

    @Override
    public String aplicarEfecto(Entidad usuario, Entidad objetivo) {
        int antes = objetivo.getVida();
        objetivo.curar(curacion);
        int real = objetivo.getVida() - antes;
        return usuario.getNombre() + " usó " + getNombre() + " y curó " + real
                + " de vida a " + objetivo.getNombre() + ".";
    }

    public int getCuracion() { return curacion; }
}
