package modelo;

public abstract class Accion {

    private String resultado = "";

    public abstract void ejecutar();

    protected void setResultado(String resultado) {
        this.resultado = resultado;
    }

    /** Texto que describe lo que pasó al ejecutar la acción (para el registro). */
    public String getResultado() {
        return resultado;
    }
}
