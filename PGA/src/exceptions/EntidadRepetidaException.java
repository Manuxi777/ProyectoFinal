package exceptions;

import pga.Entidad;

/**
 * Clase que representa un tipo de las excepciones con que trabaja el sistema.
 * Su lanzamiento ocurre cuando se intenta agregar a alguna colecci�n una entidad
 * que ya esta incluida en la misma.
 */
public class EntidadRepetidaException extends Exception
{
    private Entidad entidad; // Entidad que sirve de informaci�n en la zona donde se atrapa a la excepci�n.
    
    /**
    * Constructor de la clase que crea una nueva instancia de EntidadRepetidaException.<br>
    * 
    * <b>Pre:</b> La entidad existe en el contexto del sistema.<br>
    * <b>Post:</b> Se crea una nueva instancia de la clase.
    * 
    * @param entidad Entidad que sirve como informaci�n. Entidad != null.
    * @param mensaje Mensaje que ser� mostrado en el bloque catch informando el problema ocurrido. Mensaje != null y mensaje != ""
    */
    public EntidadRepetidaException(Entidad entidad, String mensaje)
    {
        super(mensaje);
        this.entidad = entidad;
    }

    public Entidad getEntidad()
    {
        return entidad;
    }
}
