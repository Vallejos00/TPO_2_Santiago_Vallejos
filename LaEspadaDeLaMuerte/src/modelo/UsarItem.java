package modelo;

public class UsarItem extends Accion {

    private final Entidad usuario;
    private final Item item;

    public UsarItem(Entidad usuario, Item item) {
        this.usuario = usuario;
        this.item = item;
    }

    @Override
    public void ejecutar() {
        item.aplicarEfecto(usuario);
        if (usuario instanceof Personaje) {
            ((Personaje) usuario).getInventario().eliminarItem(item);
        }
        setResultado(usuario.getNombre() + " usó " + item.getNombre() + ".");
    }
}
