package modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestorPartida {

    private static final Path ARCHIVO = Paths.get("partida.sav");

    public void guardar(Partida partida) {
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(ARCHIVO))) {
            out.writeObject(partida);
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo guardar la partida.", e);
        }
    }

    public Partida cargar() {
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(ARCHIVO))) {
            return (Partida) in.readObject();
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo cargar la partida.", e);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("El archivo de partida no es válido.", e);
        }
    }

    public boolean existePartidaGuardada() {
        return Files.exists(ARCHIVO);
    }
}
