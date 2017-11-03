package pga;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Clase que representa a las Asignaturas que existen en la universidad.
 */
public class Asignatura implements Comparable, Entidad
{
    private static int numAsignatura = 0; // N�mero de id de la �ltima asignatura registrada
    private static final String ID_LEGAJO = "ASI"; // Parte del formato con que se arma el id de la asignatura
    
    private String id; // Identificaci�n de la asignatura
    private String nombre; // Nombre de la asignatura
    private HashMap<String, Asignatura> correlatividades; // Colecci�n de las asignaturas correlativas con la asignatura en cuesti�n
    // Las claves ser�n las IDs de la asignaturas
    
    /**
     * Constructor vac�o necesario para la serializaci�n en XML.
     */
    public Asignatura()
    {
        super();
    }

    /**
     * Constructor que crea una instancia de Asignatura.<br>
     * 
     * <b>Pre:</b> El nombre ya se encuentra validado.<br>
     * <b>Post:</b> Se crea una nueva instancia de la Asignatura.
     * 
     * @param nombre Nombre de la asignatura. Nombre != null y nombre != "".
     */
    public Asignatura(String nombre)
    {
        this.id = ID_LEGAJO + String.format("%04d", ++numAsignatura);
        this.nombre = nombre;
        this.correlatividades = new HashMap<String, Asignatura>();
    }

    public static void setNumAsignatura(int numAsignatura)
    {
        Asignatura.numAsignatura = numAsignatura;
    }

    public static int getNumAsignatura()
    {
        return numAsignatura;
    }

    public String getId()
    {
        return id;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getNombre()
    {
        return nombre;
    }

    /**
     * M�todo que devuelve la clave con que est� hasheada la asignatura.
     * En este caso la clave es el nombre de la asignatura.
     * 
     * <b>Pre:</b> La asignatura debe poseer un nombre no nulo ni vac�o.
     * <b>Post:</b> Se devuelve la clave de la asignatura.
     * 
     * @return Nombre de la asignatura.
     */
    @Override
    public String getClave()
    {
        return this.nombre;
    }

    public void setCorrelatividades(HashMap<String, Asignatura> correlatividades)
    {
        this.correlatividades = correlatividades;
    }

    public HashMap<String, Asignatura> getCorrelatividades()
    {
        return correlatividades;
    }

    /**
     * M�todo que compara dos asignaturas. El criterio de igualdad ser� por id.
     * 
     * <b>Pre:</b> El objeto que viene por par�metro debe ser una Asignatura.<br>
     * <b>Post:</b> Se devuelve verdadero o falso dependiendo si las asignaturas son iguales o no.
     * 
     * @param object Objeto a comparar con la asignatura actual. Object != null.
     * @return Verdadero si los objetos cumplen el criterio de igualdad o falso en caso contrario.
     */
    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Asignatura))
        {
            return false;
        }
        final Asignatura other = (Asignatura) object;
        if (!(id == null ? other.id == null : id.equals(other.id)))
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
        result = PRIME * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * M�todo que compara asignaturas a trav�s de sus ids.<br>
     * 
     * <b>Pre:</b> El objeto que viene por par�metro no es nulo y debe ser una asignatura.<br>
     * <b>Post:</b> Se devuelve un entero en funci�n de qu� objeto sea mayor en base a la comparaci�n.
     * 
     * @param object objeto a comparar con la instancia actual. Object != null.
     * @return Entero menor, igual o mayor a 0 seg�n el resultado de la comparaci�n.
     */
    @Override
    public int compareTo(Object object) {
        return this.id.compareTo(((Asignatura) object).getId()); // Las asignaturas ser�n ordenadas por su ID
    }    

    /**
     * M�todo que devuelve una cadena con la informaci�n completa de la asignatura, tanto su nombre como sus 
     * correlatividades.<br>
     * 
     * <b>Pre:</b> La colecci�n de correlatividades ya se encuentra inicializada.<br>
     * <b>Post:</b> Se devuelve un String con la informaci�n de la asignatura.
     * 
     * @return String con la informaci�n de la asignatura.
     */
    public String infoAsignatura()
    {
        Iterator<Asignatura> it = this.correlatividades.values().iterator();
        String cad;
        Asignatura asignatura;
        
        cad = "Id: " + this.id + "\nNombre: " + this.nombre + "\nCorrelatividades: ";
        
        while(it.hasNext())
        {
            asignatura = it.next();
            cad += "\n\tId: " + asignatura.getId() + ", Nombre: " + asignatura.getNombre();
        }
        
        return cad;
    }

    /**
     * M�todo que devuelve una cadena con la informaci�n b�sica de la asignatura: su id y su nombre.<br>
     * 
     * <b>Pre:</b> La asignatura cuenta con un id y un nombre.<br>
     * <b>Post:</b> Se devuelve la informaci�n de la asignatura.
     * 
     * @return String con la informaci�n b�sica de la materia.
     */
    @Override
    public String toString()
    {
        return "Id: " + this.id + "\nNombre: " + this.nombre;
    }
}
