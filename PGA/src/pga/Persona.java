package pga;


/**
 * Clase que re�ne las caracter�sticas comunes entre las clases Alumno y Profesor.
 */
public class Persona implements Comparable, Entidad
{
    private String nombre; // Nombre de la persona
    private String apellido; // Apellido de la persona
    private String legajo; // Legajo de la persona
    private String domicilio; // Domicilio de la persona
    private String telefono; // Telefono de la persona
    private String mail; // Mail de la persona

    /**
     * Constructor vac�o necesario para la serializaci�n en XML.
     */
    public Persona()
    {
        super();
    }

    /**
     * Constructor que crea una nueva instancia de Persona.
     *
     * <b> Pre:</b> Los par�metros vienen ya siendo v�lidos. Hay una asignaci�n directa.
     * El mail ya cuenta con el formato correcto.<br>
     * <b> Post:</b> Se crea una nueva instancia de Persona con los par�metros validados.
     *
     * @param nombre Nombre de la persona. Nombre != null y nombre != ""
     * @param apellido Apellido de la persona. Apellido != null y apellido != ""
     * @param legajo Legajo de la persona. Legajo != null y legajo != "".
     * @param domicilio Domicilio de la persona. Domicilio != null y domicilio != ""
     * @param telefono Telefono de la persona. Telefono != null y telefono != ""
     * @param mail Mail de la persona. Mail != null y mail != ""
     */
    public Persona(String nombre, String apellido, String legajo, String domicilio, String telefono, String mail)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.mail = mail;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    public String getApellido()
    {
        return apellido;
    }
    
    /**
     * M�todo que devuelve la clave con que est� hasheada la persona.
     * En este caso la clave es la concatenaci�n del nombre y apellido de la persona.
     * 
     * <b>Pre:</b> La persona debe poseer un nombre y apellido no nulos ni vac�os.
     * <b>Post:</b> Se devuelve la clave de la persona.
     * 
     * @return Clave de la persona.
     */
    @Override
    public String getClave()
    {
        return this.nombre + this.apellido;
    }

    public void setLegajo(String legajo)
    {
        this.legajo = legajo;
    }

    public String getLegajo()
    {
        return legajo;
    }

    public void setDomicilio(String domicilio)
    {
        this.domicilio = domicilio;
    }

    public String getDomicilio()
    {
        return domicilio;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getMail()
    {
        return mail;
    }

    /**
     * M�todo que compara dos personas. El criterio de igualdad ser� por el legajo.
     * 
     * <b>Pre:</b> El objeto que viene por par�metro debe ser una Persona.<br>
     * <b>Post:</b> Se devuelve verdadero o falso dependiendo si las personas son iguales o no.
     * 
     * @param object Objeto a comparar con la persona actual. Object != null.
     * @return Verdadero si los objetos cumplen el criterio de igualdad o falso en caso contrario.
     */
    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Persona))
        {
            return false;
        }
        final Persona other = (Persona) object;
        if (!(legajo == null ? other.legajo == null : legajo.equals(other.legajo)))
        {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        final int PRIME = 37;
        int result = 1;
        result = PRIME * result + ((legajo == null) ? 0 : legajo.hashCode());
        return result;
    }

    /**
     * M�todo que compara dos personas a partir de sus legajos.<br>
     * 
     * <b>Pre:</b> El objeto que viene por par�metro no es nulo y debe ser una persona.<br>
     * <b>Post:</b> Se devuelve un entero en funci�n de qu� objeto sea mayor en base a la comparaci�n.
     * 
     * @param object objeto a comparar con la instancia actual. Object != null.
     * @return Entero menor, igual o mayor a 0 seg�n el resultado de la comparaci�n.
     */
    @Override
    public int compareTo(Object object) {
        return this.legajo.compareTo(((Persona) object).getLegajo());
    }
    
    /**
     * M�todo que devuelve una cadena con la informaci�n de la persona.<br>
     * 
     * <b>Pre:</b> La persona cuenta con todos sus atributos no nulos.<br>
     * <b>Post:</b> Se devuelve la informaci�n de la persona.
     * 
     * @return String con la informaci�n de la persona.
     */
    @Override
    public String toString()
    {
        return "Nombre y apellido: " + this.nombre + " " + this.apellido + " Legajo: " + this.legajo + " Domicilio: " 
               + this.domicilio + " Telefono: " + this.telefono + " Mail: " + this.mail;
    }
}
