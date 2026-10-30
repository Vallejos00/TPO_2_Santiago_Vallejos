package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Entidad implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String nombre;
    private int vida;
    private int vidaMaxima;
    private int mana;
    private int manaMaximo;
    private int ataque;
    private int defensa;
    private final int velocidad;
    private final List<Habilidad> habilidades = new ArrayList<>();
    private boolean defendiendo;

    public Entidad(String nombre, int vidaMaxima, int manaMaximo,
                   int ataque, int defensa, int velocidad) {
        this.nombre = nombre;
        this.vidaMaxima = vidaMaxima;
        this.vida = vidaMaxima;
        this.manaMaximo = manaMaximo;
        this.mana = manaMaximo;
        this.ataque = ataque;
        this.defensa = defensa;
        this.velocidad = velocidad;
    }

    public void recibirDanio(int cantidad) {
        if (defendiendo) {
            cantidad = Math.max(1, cantidad / 2);
        }
        vida = Math.max(0, vida - cantidad);
    }

    public boolean estaViva() {
        return vida > 0;
    }

    public void curar(int cantidad) {
        vida = Math.min(vidaMaxima, vida + cantidad);
    }

    public boolean tieneMana(int cantidad) {
        return mana >= cantidad;
    }

    public void consumirMana(int cantidad) {
        mana = Math.max(0, mana - cantidad);
    }

    public void recuperarMana(int cantidad) {
        mana = Math.min(manaMaximo, mana + cantidad);
    }

    public void agregarHabilidad(Habilidad habilidad) {
        habilidades.add(habilidad);
    }

    protected void aumentarEstadisticas(int extraVida, int extraMana,
                                        int extraAtaque, int extraDefensa) {
        vidaMaxima += extraVida;
        vida += extraVida;
        manaMaximo += extraMana;
        mana += extraMana;
        ataque += extraAtaque;
        defensa += extraDefensa;
    }

    public void setDefendiendo(boolean defendiendo) {
        this.defendiendo = defendiendo;
    }

    public boolean estaDefendiendo() {
        return defendiendo;
    }

    public String getNombre() { return nombre; }
    public int getVida() { return vida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getMana() { return mana; }
    public int getManaMaximo() { return manaMaximo; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getVelocidad() { return velocidad; }

    public List<Habilidad> getHabilidades() {
        return Collections.unmodifiableList(habilidades);
    }
}
