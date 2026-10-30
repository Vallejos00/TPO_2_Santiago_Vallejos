package dto;

public class EntidadDTO {

    private final String nombre;
    private final String sprite;
    private final String descripcion;
    private final int vidaActual;
    private final int vidaMaxima;
    private final int manaActual;
    private final int manaMaximo;
    private final int ataque;
    private final int defensa;
    private final int velocidad;
    private final int nivel;
    private final boolean defendiendo;

    public EntidadDTO(String nombre, String sprite, String descripcion,
                      int vidaActual, int vidaMaxima, int manaActual, int manaMaximo,
                      int ataque, int defensa, int velocidad, int nivel, boolean defendiendo) {
        this.nombre = nombre;
        this.sprite = sprite;
        this.descripcion = descripcion;
        this.vidaActual = vidaActual;
        this.vidaMaxima = vidaMaxima;
        this.manaActual = manaActual;
        this.manaMaximo = manaMaximo;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
        this.nivel = nivel;
        this.defendiendo = defendiendo;
    }

    public String getNombre() { return nombre; }
    public String getSprite() { return sprite; }
    public String getDescripcion() { return descripcion; }
    public int getVidaActual() { return vidaActual; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getManaActual() { return manaActual; }
    public int getManaMaximo() { return manaMaximo; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getVelocidad() { return velocidad; }
    /** 0 si la entidad no es un personaje (los enemigos no tienen nivel). */
    public int getNivel() { return nivel; }
    public boolean isDefendiendo() { return defendiendo; }
    public boolean isViva() { return vidaActual > 0; }
}
