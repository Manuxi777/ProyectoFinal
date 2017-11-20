package exceptions;

import pga.Entidad;

/**
 * Clase que representa un tipo de las excepciones con que trabaja el sistema.
 * Su lanzamiento ocurre cuando al buscar en una colecci�n un elemento que
 * debiera estar presente, �ste no existe en la misma.
 */
public class NoEstaEntidadException extends Exception
{
    private Entidad entidad; // Entidad que sirve de informaci�n en la zona donde se atrapa a la excepci�n.
    
    /**
     * Constructor de la clase que crea una nueva instancia de NoEstaEntidadException.<br>
     * 
     * <b>Pre:</b> La entidad existe en el contexto del sistema.<br>
     * <b>Post:</b> Se crea una nueva instancia de la clase.
     * 
     * @param entidad Entidad que sirve como informaci�n. Entidad != null.
     * @param mensaje Mensaje que ser� mostrado en el bloque catch informando el problema ocurrido. Mensaje != null y mensaje != ""
     */
    public NoEstaEntidadException(Entidad entidad, String mensaje)
    {
        super(mensaje);
        this.entidad = entidad;
    }

    /**
    * Constructor de la clase que crea una nueva instancia de NoEstaEntidadException.<br>
    * 
    * <b>Pre:</b> El mensaje que viene por par�metro no es nulo ni vac�o.<br>
    * <b>Post:</b> Se crea una nueva instancia de la clase.
    * 
    * @param mensaje Mensaje que ser� mostrado en el bloque catch informando el problema ocurrido. Mensaje != null y mensaje != ""
    */
    public NoEstaEntidadException(String mensaje)
    {
        super(mensaje);
    }
    
    public Entidad getEntidad()
    {
        return entidad;
    }
}
