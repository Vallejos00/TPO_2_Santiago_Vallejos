package modelo;

/** Bruto: ataca al azar, pero si está muy herido se cubre con su escudo. */
public class Orco extends Enemigo {

    private static final long serialVersionUID = 1L;

    public Orco() {
        super("Orco", 180, 0, 24, 8, 5);
    }

    @Override
    public Accion elegirAccion(Batalla batalla) {
        boolean herido = getVida() * 100 / getVidaMaxima() < 40;
        if (herido && !estaDefendiendo() && tiraDado(50)) {
            return new Defender(this);
        }
        return new Atacar(this, personajeAleatorio(batalla));
    }
}
