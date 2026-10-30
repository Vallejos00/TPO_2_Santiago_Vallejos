package modelo;

public class HabilidadOfensiva extends Habilidad {

    private static final long serialVersionUID = 1L;

    private final int danio;

    public HabilidadOfensiva(String nombre, int costoMana, int danio) {
        super(nombre, costoMana);
        this.danio = danio;
    }

    @Override
    public boolean afectaAliados() {
        return false;
    }

    @Override
    public String aplicarEfecto(Entidad usuario, Entidad objetivo) {
        int total = Math.max(1, danio + usuario.getAtaque() / 2 - objetivo.getDefensa());
        int antes = objetivo.getVida();
        objetivo.recibirDanio(total);
        int real = antes - objetivo.getVida();

        String mensaje = usuario.getNombre() + " usó " + getNombre() + " sobre "
                + objetivo.getNombre() + " y causó " + real + " de daño.";
        if (!objetivo.estaViva()) {
            mensaje += " " + objetivo.getNombre() + " fue derrotado.";
        }
        return mensaje;
    }

    public int getDanio() { return danio; }
}
