package controlador;

import java.util.ArrayList;
import java.util.List;

import dto.EntidadDTO;
import modelo.Entidad;
import modelo.Personaje;

/** Convierte objetos del modelo en DTOs para que las vistas no dependan del modelo. */
final class ConversorDTO {

    private ConversorDTO() {
    }

    static EntidadDTO entidad(Entidad e) {
        int nivel = e instanceof Personaje ? ((Personaje) e).getNivel() : 0;
        return new EntidadDTO(
            e.getNombre(), sprite(e), descripcion(e),
            e.getVida(), e.getVidaMaxima(),
            e.getMana(), e.getManaMaximo(),
            e.getAtaque(), e.getDefensa(), e.getVelocidad(),
            nivel, e.estaDefendiendo());
    }

    static List<EntidadDTO> entidades(List<? extends Entidad> lista) {
        List<EntidadDTO> dtos = new ArrayList<>();
        for (Entidad e : lista) {
            dtos.add(entidad(e));
        }
        return dtos;
    }

    /** Nombre del archivo de imagen: nombre de la clase en minúsculas (guerrero.png, goblin.png...). */
    static String sprite(Entidad e) {
        return e.getClass().getSimpleName().toLowerCase() + ".png";
    }

    /** Texto corto para las tarjetas de personajes. */
    static String descripcion(Entidad e) {
        switch (e.getClass().getSimpleName()) {
            case "Guerrero":   return "Tanque y daño físico";
            case "Mago":       return "Daño mágico y control";
            case "Arquero":    return "Daño a distancia y velocidad";
            case "Curador":    return "Apoyo y sanación";
            case "Goblin":     return "Oportunista: ataca al más herido";
            case "Orco":       return "Bruto: golpea fuerte y se cubre";
            case "MagoOscuro": return "Hechicero de rayos sombríos";
            case "Jefe":       return "Jefe final";
            default:           return "";
        }
    }
}
