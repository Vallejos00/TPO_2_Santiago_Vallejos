package dto;

import java.util.List;

public class EstadoPartidaDTO {

    private final List<EntidadDTO> personajes;
    private final EntidadDTO proximoEnemigo;
    private final int oro;
    private final int numeroBatalla;
    private final int totalBatallas;

    public EstadoPartidaDTO(List<EntidadDTO> personajes, EntidadDTO proximoEnemigo,
                            int oro, int numeroBatalla, int totalBatallas) {
        this.personajes = personajes;
        this.proximoEnemigo = proximoEnemigo;
        this.oro = oro;
        this.numeroBatalla = numeroBatalla;
        this.totalBatallas = totalBatallas;
    }

    public List<EntidadDTO> getPersonajes() { return personajes; }
    public EntidadDTO getProximoEnemigo() { return proximoEnemigo; }
    public int getOro() { return oro; }
    public int getNumeroBatalla() { return numeroBatalla; }
    public int getTotalBatallas() { return totalBatallas; }
}
