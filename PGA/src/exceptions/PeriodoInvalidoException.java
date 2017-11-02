package exceptions;


/**
 * Clase que representa un tipo de las excepciones con que trabaja el sistema.
 * Su lanzamiento ocurre cuando el formato del periodo ingresado no cumple con el
 * est�ndar previsto en la aplicaci�n.
 */
public class PeriodoInvalidoException extends Exception
{
    private String periodo; // Periodo con formato incorrecto.
    
    /**
     * Constructor de la clase que crea una nueva instancia de PeriodoInvalidoException.<br>
     * 
     * <b>Pre:</b> El periodo es un String no nulo ni vac�o cuyo formato es inv�lido.<br>
     * <b>Post:</b> Se crea una nueva instancia de la clase.
     * 
     * @param periodo Periodo de la cursada con un formato no v�lido. Periodo != null && periodo != "".
     * @param mensaje Mensaje que ser� mostrado en el bloque catch informando el problema ocurrido. Mensaje != null && mensaje != ""
     */
    public PeriodoInvalidoException(String periodo, String mensaje)
    {
        super(mensaje);
        this.periodo = periodo;
    }

    public String getPeriodo()
    {
        return periodo;
    }
}
