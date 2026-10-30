package app;

import javax.swing.SwingUtilities;

import controlador.ControladorBatalla;
import controlador.ControladorPartida;
import modelo.GestorPartida;
import vista.VentanaPrincipal;
import vista.VistaBatalla;
import vista.VistaInicio;
import vista.VistaParty;
import vista.VistaResultados;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::iniciar);
    }

    private static void iniciar() {
        // Vistas
        VistaInicio vistaInicio = new VistaInicio();
        VistaParty vistaParty = new VistaParty();
        VistaBatalla vistaBatalla = new VistaBatalla();
        VistaResultados vistaResultados = new VistaResultados();
        VentanaPrincipal ventana =
            new VentanaPrincipal(vistaInicio, vistaParty, vistaBatalla, vistaResultados);

        // Controladores
        GestorPartida gestor = new GestorPartida();
        ControladorPartida controladorPartida =
            new ControladorPartida(gestor, ventana, vistaInicio, vistaParty);
        ControladorBatalla controladorBatalla =
            new ControladorBatalla(ventana, vistaBatalla, vistaResultados);
        controladorPartida.setControladorBatalla(controladorBatalla);

        // Las vistas reciben a su controlador
        vistaInicio.conectarControlador(controladorPartida);
        vistaParty.conectarControlador(controladorPartida);
        vistaBatalla.conectarControlador(controladorBatalla);
        vistaResultados.conectarControlador(controladorPartida);

        ventana.mostrarInicio();
        ventana.setVisible(true);
    }
}
