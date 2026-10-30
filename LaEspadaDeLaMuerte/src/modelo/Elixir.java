package modelo;

public class Elixir extends Item {

    private static final long serialVersionUID = 1L;

    public Elixir() {
        super("Elixir");
    }

    @Override
    public void aplicarEfecto(Entidad entidad) {
        entidad.curar(40);
        entidad.recuperarMana(40);
    }
}
