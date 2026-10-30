package dto;

public class HabilidadDTO {

    private final String nombre;
    private final int costoMana;
    private final boolean curacion;
    private final boolean utilizable;

    public HabilidadDTO(String nombre, int costoMana, boolean curacion, boolean utilizable) {
        this.nombre = nombre;
        this.costoMana = costoMana;
        this.curacion = curacion;
        this.utilizable = utilizable;
    }

    public String getNombre() { return nombre; }
    public int getCostoMana() { return costoMana; }
    /** true si se aplica sobre un aliado (hay que elegir a quién). */
    public boolean isCuracion() { return curacion; }
    /** false si el personaje actual no tiene maná suficiente. */
    public boolean isUtilizable() { return utilizable; }
}
