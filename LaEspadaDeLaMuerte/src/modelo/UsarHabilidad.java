package modelo;

public class UsarHabilidad extends Accion {

    private final Entidad usuario;
    private final Habilidad habilidad;
    private final Entidad objetivo;

    public UsarHabilidad(Entidad usuario, Habilidad habilidad, Entidad objetivo) {
        this.usuario = usuario;
        this.habilidad = habilidad;
        this.objetivo = objetivo;
    }

    @Override
    public void ejecutar() {
        if (!habilidad.puedeUsarse(usuario)) {
            setResultado(usuario.getNombre() + " no tiene maná para " + habilidad.getNombre() + ".");
            return;
        }
        usuario.consumirMana(habilidad.getCostoMana());
        setResultado(habilidad.aplicarEfecto(usuario, objetivo));
    }
}
