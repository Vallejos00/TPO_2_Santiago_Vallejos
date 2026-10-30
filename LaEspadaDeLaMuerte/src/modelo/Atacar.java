package modelo;

public class Atacar extends Accion {

    private final Entidad atacante;
    private final Entidad objetivo;

    public Atacar(Entidad atacante, Entidad objetivo) {
        this.atacante = atacante;
        this.objetivo = objetivo;
    }

    @Override
    public void ejecutar() {
        int danio = Math.max(1, atacante.getAtaque() - objetivo.getDefensa());
        int antes = objetivo.getVida();
        objetivo.recibirDanio(danio);
        int real = antes - objetivo.getVida();

        String mensaje = atacante.getNombre() + " atacó a " + objetivo.getNombre()
                + " por " + real + " de daño.";
        if (!objetivo.estaViva()) {
            mensaje += " " + objetivo.getNombre() + " fue derrotado.";
        }
        setResultado(mensaje);
    }
}
