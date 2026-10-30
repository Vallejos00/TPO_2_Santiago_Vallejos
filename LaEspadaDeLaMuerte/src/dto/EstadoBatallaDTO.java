package dto;

import java.util.List;

public class EstadoBatallaDTO {

    private final List<EntidadDTO> party;
    private final EntidadDTO enemigo;
    private final String nombreTurnoActual;
    private final int indiceTurnoParty;
    private final List<HabilidadDTO> habilidades;
    private final List<String> items;
    private final List<String> registro;
    private final boolean batallaTerminada;
    private final int numeroBatalla;
    private final int totalBatallas;

    public EstadoBatallaDTO(List<EntidadDTO> party, EntidadDTO enemigo,
                            String nombreTurnoActual, int indiceTurnoParty,
                            List<HabilidadDTO> habilidades, List<String> items,
                            List<String> registro, boolean batallaTerminada,
                            int numeroBatalla, int totalBatallas) {
        this.party = party;
        this.enemigo = enemigo;
        this.nombreTurnoActual = nombreTurnoActual;
        this.indiceTurnoParty = indiceTurnoParty;
        this.habilidades = habilidades;
        this.items = items;
        this.registro = registro;
        this.batallaTerminada = batallaTerminada;
        this.numeroBatalla = numeroBatalla;
        this.totalBatallas = totalBatallas;
    }

    public List<EntidadDTO> getParty() { return party; }
    public EntidadDTO getEnemigo() { return enemigo; }
    public String getNombreTurnoActual() { return nombreTurnoActual; }
    /** Posición en la party del personaje que juega ahora, o -1 si no es turno de un personaje. */
    public int getIndiceTurnoParty() { return indiceTurnoParty; }
    public List<HabilidadDTO> getHabilidades() { return habilidades; }
    public List<String> getItems() { return items; }
    public List<String> getRegistro() { return registro; }
    public boolean isBatallaTerminada() { return batallaTerminada; }
    public int getNumeroBatalla() { return numeroBatalla; }
    public int getTotalBatallas() { return totalBatallas; }
    public boolean isTurnoJugador() { return indiceTurnoParty >= 0 && !batallaTerminada; }
}
