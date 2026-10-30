package dto;

import java.util.List;

public class ResultadoDTO {

    private final boolean victoria;
    private final boolean ultimaBatalla;
    private final int experiencia;
    private final int oro;
    private final List<String> mensajes;

    public ResultadoDTO(boolean victoria, boolean ultimaBatalla, int experiencia,
                        int oro, List<String> mensajes) {
        this.victoria = victoria;
        this.ultimaBatalla = ultimaBatalla;
        this.experiencia = experiencia;
        this.oro = oro;
        this.mensajes = mensajes;
    }

    public boolean isVictoria() { return victoria; }
    public boolean isUltimaBatalla() { return ultimaBatalla; }
    public int getExperiencia() { return experiencia; }
    public int getOro() { return oro; }
    public List<String> getMensajes() { return mensajes; }
}
