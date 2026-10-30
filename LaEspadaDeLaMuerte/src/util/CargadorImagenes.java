package util;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public final class CargadorImagenes {

    private static final Map<String, ImageIcon> CACHE = new HashMap<>();

    private static final String[] CARPETAS = {
        "src/recursos/sprites/",
        "LaEspadaDeLaMuerte/src/recursos/sprites/",
        "recursos/sprites/"
    };

    private CargadorImagenes() {
    }

    /** Busca la imagen en recursos/sprites. Devuelve null si el archivo no existe. */
    public static ImageIcon cargar(String nombreArchivo) {
        if (CACHE.containsKey(nombreArchivo)) {
            return CACHE.get(nombreArchivo);
        }

        ImageIcon icono = null;

        URL recurso = CargadorImagenes.class.getResource("/recursos/sprites/" + nombreArchivo);
        if (recurso != null) {
            icono = new ImageIcon(recurso);
        } else {
            for (String carpeta : CARPETAS) {
                File archivo = new File(carpeta + nombreArchivo);
                if (archivo.exists()) {
                    icono = new ImageIcon(archivo.getPath());
                    break;
                }
            }
        }

        CACHE.put(nombreArchivo, icono);
        return icono;
    }
}
