package pga;

import java.util.HashMap;

/**
 * Clase que representa a las cursadas existentes dentro de la facultad.
 */
public class Cursada implements Comparable, Entidad
{
    private static int numCursada = 0; // N�mero de id de la �ltima cursada registrada
    private static final String ID_LEGAJO = "CUR"; // Parte del formato con que se arma el id de la cursada
    
    private String id; // Identificaci�n de la cursada
    private String nombre; // Nombre de la cursada
    private Asignatura asignatura; // Asignatura a la cual pertenece la cursada
    private String periodo; // Periodo en que se dicta la cursada (incluye a�o y cuatrimestre)
    private String dia; // D�a de la semana en que se dicta la cursada
    private String horaInicio; // Hora de inicio de la cursada
    private String horaFin; // Hora de finalizaci�n de la cursada
    private HashMap<String, Alumno> alumnos; // Colecci�n de los alumnos inscriptos en la cursada
    // Las claves ser�n los legajos de los alumnos
    private HashMap<String, Profesor> profesores; // Colecci�n de los profesores que ense�an en la cursada
    // Las claves ser�n los legajos de los profesores
    
    /**
     * Constructor vac�o necesario para la serializaci�n en XML.
     */
    public Cursada()
    {
        super();    
    }

    /**
     * Constructor que crea una nueva instancia de la Cursada.<br>
     * 
     * <b>Pre:</b> Los par�metros ya se encuentran validados. Tanto el per�odo
     * como las horas de inicio y fin cumplen con los formatos estipulados.<br>
     * <b>Post:</b> Se crea una nueva instancia de Cursada.
     * 
     * @param nombre Nombre de la cursada. Nombre != null y nombre != "".
     * @param asignatura Asignatura a la cual pertenece la cursada. Asignatura != null.
     * @param periodo Per�odo en que se dicta la cursada. Periodo != null y periodo != "".
     * @param dia D�a en que se dicta la cursada. Dia != null y dia != "".
     * @param horaInicio Hora de inicio de la cursada. HoraInicio != null y HoraInicio != "".
     * @param horaFin Hora de finalizaci�n de la cursada. HoraFin != null y HoraFin != "".
     */
    public Cursada(String nombre, Asignatura asignatura, String periodo, String dia, String horaInicio, String horaFin)
    {
        this.id = ID_LEGAJO + String.format("%04d", ++numCursada);
        this.nombre = nombre;
        this.asignatura = asignatura;
        this.periodo = periodo;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.alumnos = new HashMap<String, Alumno>(); // Los alumnos estar�n en una tabla hash cuya clave ser�
                                                      // el legajo del alumno
        this.profesores = new HashMap<String, Profesor>(); // Los profesores estar�n en una tabla hash cuya clave ser�
                                                           // el legajo del profesor
    }

    public static void setNumCursada(int numCursada)
    {
        Cursada.numCursada = numCursada;
    }

    public static int getNumCursada()
    {
        return numCursada;
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
     * M�todo que devuelve la clave con que est� hasheada la cursada.
     * En este caso la clave es el nombre de la cursada.
     * 
     * <b>Pre:</b> La cursada debe poseer un nombre no nulo ni vac�o.
     * <b>Post:</b> Se devuelve la clave de la cursada.
     * 
     * @return Nombre de la cursada.
     */
    @Override
    public String getClave()
    {
        return this.nombre;
    }
    
    public void setAsignatura(Asignatura asignatura)
    {
        this.asignatura = asignatura;
    }

    public Asignatura getAsignatura()
    {
        return asignatura;
    }

    public void setPeriodo(String periodo)
    {
        this.periodo = periodo;
    }

    public String getPeriodo()
    {
        return periodo;
    }

    public void setDia(String dia)
    {
        this.dia = dia;
    }

    public String getDia()
    {
        return dia;
    }

    public void setHoraInicio(String horaInicio)
    {
        this.horaInicio = horaInicio;
    }

    public String getHoraInicio()
    {
        return horaInicio;
    }

    public void setHoraFin(String horaFin)
    {
        this.horaFin = horaFin;
    }

    public String getHoraFin()
    {
        return horaFin;
    }

    public HashMap<String, Alumno> getAlumnos()
    {
        return alumnos;
    }

    public HashMap<String, Profesor> getProfesores()
    {
        return profesores;
    }

    /**
     * M�todo que compara dos cursadas. El criterio de igualdad ser� por id.
     * 
     * <b>Pre:</b> El objeto que viene por par�metro debe ser una Cursada.<br>
     * <b>Post:</b> Se devuelve verdadero o falso dependiendo si las cursadas son iguales o no.
     * 
     * @param object Objeto a comparar con la cursada actual. Object != null.
     * @return Verdadero si los objetos cumplen el criterio de igualdad o falso en caso contrario.
     */
    @Override
    public boolean equals(Object object)
    {
        if (this == object)
        {
            return true;
        }
        if (!(object instanceof Cursada))
        {
            return false;
        }
        final Cursada other = (Cursada) object;
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
     * M�todo que compara cursadas a trav�s de sus ids.<br>
     * 
     * <b>Pre:</b> El objeto que viene por par�metro no es nulo y debe ser una cursada.<br>
     * <b>Post:</b> Se devuelve un entero en funci�n de qu� objeto sea mayor en base a la comparaci�n.
     * 
     * @param object objeto a comparar con la instancia actual. Object != null.
     * @return Entero menor, igual o mayor a 0 seg�n el resultado de la comparaci�n.
     */
    @Override
    public int compareTo(Object object) {   
        return this.id.compareTo(((Cursada) object).getId()); // Las cursadas ser�n ordenadas por su ID
    }

    /**
     * M�todo que devuelve una cadena con la informaci�n de la cursada.<br>
     * 
     * <b>Pre:</b> La cursada cuenta con todos sus atributos no nulos (exceptuando las colecciones que
     * no son pertinentes para este m�todo).<br>
     * <b>Post:</b> Se devuelve la informaci�n de la cursada.
     * 
     * @return String con la informaci�n de la cursada.
     */
    @Override
    public String toString()
    {
        return "Id: " + this.id + "\nAsignatura: " + this.getAsignatura().getNombre() + "\nPeriodo: " +
               this.periodo + "\nDia: " + this.dia + "\nHora de inicio: " + this.horaInicio +
               "\nHora de fin: " + this.horaFin;
    }
}
