package modelo;

public class PocionVida extends Item {

    private static final long serialVersionUID = 1L;

    public PocionVida() {
        super("Poción de Vida");
    }

    @Override
    public void aplicarEfecto(Entidad entidad) {
        entidad.curar(50);
    }
}
