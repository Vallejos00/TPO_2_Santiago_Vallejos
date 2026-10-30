package modelo;

public class PocionMana extends Item {

    private static final long serialVersionUID = 1L;

    public PocionMana() {
        super("Poción de Maná");
    }

    @Override
    public void aplicarEfecto(Entidad entidad) {
        entidad.recuperarMana(30);
    }
}
