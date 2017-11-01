package pga;

import exceptions.EntidadRepetidaException;

import java.util.HashMap;
import java.util.Iterator;
/*
 * Clase que representa a los alumnos de la facultad.
 */
public class Alumno extends Persona
{
    private static int legajoAlumno = 0; // N�mero de legajo del �ltimo alumno registrado
    private static final String ID_LEGAJO = "ALU"; // Parte del formato con que se arma el legajo del alumno
    
    private HashMap <String, Asignatura> historiaAcademica; // Colecci�n con las asignaturas aprobadas por el alumno
    // Las claves ser�n las IDs de la asignaturas
    
    /**
     * Constructor vac�o necesario para la serializaci�n en XML
     */
    public Alumno()
    {
        super();
    }

    /**
     * Constructor que crea una nueva instancia de alumno.<br>
     * 
     * <b> Pre:</b> Los par�metros vienen ya siendo v�lidos. Hay una asignaci�n directa. 
     * El mail ya cuenta con el formato correcto.<br>
     * <b> Post:</b> Se crea una nueva instancia de alumno con los par�metros validados.
     * 
     * @param nombre Nombre del alumno. Nombre != null && nombre != ""
     * @param apellido Apellido del alumno. Apellido != null && apellido != ""
     * @param domicilio Domicilio del alumno. Domicilio != null && domicilio != ""
     * @param telefono Telefono del alumno. Telefono != null && telefono != ""
     * @param mail Mail del alumno. Mail != null && mail != ""
     */
    public Alumno(String nombre, String apellido, String domicilio, String telefono, String mail)
    {
        super(nombre, apellido, ID_LEGAJO + String.format("%04d", ++legajoAlumno), domicilio, telefono, mail);
        this.historiaAcademica = new HashMap <String, Asignatura>();
    }

    /**
     * Se a�ade a la historia acad�mica del alumno la nueva asignatura aprobada por el alumno.<br>
     * 
     * <b>Pre:</b> La asignatura es una asignatura v�lida que existe en el sistema.<br>
     * <b>Post:</b> El alumno posee la asignatura en su historia acad�mica o, en el caso que ya la tuviese se lanza
     * una excepci�n.
     * 
     * @param asignatura Asignatura aprobada por el alumno. Asignatura != null.
     * @throws EntidadRepetidaException Excepci�n con la entidad repetida y el mensaje de error.
     */
    public void aprobarAsignatura(Asignatura asignatura) throws EntidadRepetidaException
    {
        if (this.historiaAcademica.containsKey(asignatura.getId()))
            throw new EntidadRepetidaException("El alumno ya ha aprobado la asignatura.");
        else
            this.historiaAcademica.put(asignatura.getId(), asignatura);
    }

    public static void setLegajoAlumno(int legajoAlumno)
    {
        Alumno.legajoAlumno = legajoAlumno;
    }

    public static int getLegajoAlumno()
    {
        return legajoAlumno;
    }

    public void setHistoriaAcademica(HashMap<String, Asignatura> historiaAcademica)
    {
        this.historiaAcademica = historiaAcademica;
    }

    public HashMap<String, Asignatura> getHistoriaAcademica()
    {
        return historiaAcademica;
    }

    /**
     * M�todo que muestra la informaci�n completa del alumno, tanto sus atributos personales como
     * su historia acad�mica.<br>
     * 
     * <b>Post:</b> Se devuelve un String con la informaci�n del alumno.
     * 
     * @return String con la informaci�n del alumno.
     */
    public String infoAlumno()
    {
        Iterator<Asignatura> it = this.historiaAcademica.values().iterator();
        String cad;
        
        cad = super.toString() + "\nHistoria academica:";
        
        while(it.hasNext())
            cad += "\n\t" + it.next().getNombre();
        
        return cad;
    }
}
