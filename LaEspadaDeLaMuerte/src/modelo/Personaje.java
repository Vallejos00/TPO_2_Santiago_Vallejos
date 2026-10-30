package modelo;

public abstract class Personaje extends Entidad {

    private static final long serialVersionUID = 1L;

    private int nivel;
    private int experiencia;
    private final Inventario inventario;

    public Personaje(String nombre, int vidaMaxima, int manaMaximo,
                     int ataque, int defensa, int velocidad) {
        super(nombre, vidaMaxima, manaMaximo, ataque, defensa, velocidad);
        this.nivel = 1;
        this.experiencia = 0;
        this.inventario = new Inventario();
    }

    public void ganarExperiencia(int cantidad) {
        experiencia += cantidad;
        while (experiencia >= experienciaParaSubir()) {
            subirNivel();
        }
    }

    public void subirNivel() {
        experiencia = Math.max(0, experiencia - experienciaParaSubir());
        nivel++;
        aumentarEstadisticas(10, 5, 2, 1);
    }

    private int experienciaParaSubir() {
        return nivel * 100;
    }

    public int getNivel() { return nivel; }
    public int getExperiencia() { return experiencia; }
    public Inventario getInventario() { return inventario; }
}
