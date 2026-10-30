package modelo;

/** Oportunista: siempre ataca al personaje que está más herido. */
public class Goblin extends Enemigo {

    private static final long serialVersionUID = 1L;

    public Goblin() {
        super("Goblin", 70, 0, 14, 3, 12);
    }

    @Override
    public Accion elegirAccion(Batalla batalla) {
        return new Atacar(this, personajeMasDebil(batalla));
    }
}
