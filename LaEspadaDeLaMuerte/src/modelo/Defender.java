package modelo;

public class Defender extends Accion {

    private final Entidad entidad;

    public Defender(Entidad entidad) {
        this.entidad = entidad;
    }

    @Override
    public void ejecutar() {
        entidad.setDefendiendo(true);
        setResultado(entidad.getNombre() + " se defiende: recibirá menos daño hasta su próximo turno.");
    }
}
