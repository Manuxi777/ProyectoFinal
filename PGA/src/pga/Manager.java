package pga;

import exceptions.EmailInvalidoException;
import exceptions.EntidadNoAptaParaCursadaException;
import exceptions.EntidadRepetidaException;
import exceptions.HoraInvalidaException;
import exceptions.NoEstaEntidadException;
import exceptions.PeriodoInvalidoException;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Clase que se encarga de administrar todas las colecciones de la instituci�n.
 * Engloba a  la gran mayor�a de las clases del modelo.
 */
public class Manager
{
    private static Manager instancia = null;
    
    // Colecci�n con todos los alumnos de la facultad
    // Las claves ser�n los nombres y apellidos de los alumnos
    private HashMap<String, HashMap<String, Alumno>> alumnos = new HashMap<String, HashMap<String, Alumno>>();
    
    // Colecci�n con todos los profesores de la facultad
    // Las claves ser�n los nombres y apellidos de los profesores
    private HashMap<String, HashMap<String, Profesor>> profesores = new HashMap<String, HashMap<String, Profesor>>(); 
    
    // Colecci�n con todas las asignaturas de la facultad
    // Las claves ser�n los nombres de las asignaturas
    private HashMap<String, HashMap<String, Asignatura>> asignaturas = new HashMap<String, HashMap<String, Asignatura>>(); 
    
    // Colecci�n con todas las cursadas de la facultad
    // Las claves ser�n los nombres de las cursadas
    private HashMap<String, HashMap<String, Cursada>> cursadas = new HashMap<String, HashMap<String, Cursada>>();

    /**
     * Constructor vac�o que crea una nueva instancia de Manager. 
     * Su modificador de acceso es privado para poder aplicar el patr�n Singleton correctamente y
     * evitar inicializaciones que no pasen por el m�todo getInstance().<br>
     * 
     * <b>Pre:</b> No existen precondiciones al momento de llamar al constructor.<br>
     * <b>Post:</b> Se crea una nueva instancia de la clase Manager.
     */
    private Manager()
    {
        super();
    }

    /**
     * M�todo est�tico que permite aplicar el Patr�n Singleton y obtener siempre la misma instancia de
     * la clase Manager.<br>
     * 
     * <b>Pre:</b> No existen precondiciones al momento de llamar al m�todo.<br>
     * <b>Post:</b> Se devuelve una nueva instancia de Manager si no existiese o la misma instancia si lo hace.
     * 
     * @return Nueva instancia de la clase Manager si nunca fue instanciada o la misma y �nica instancia en caso 
     * contrario. 
     */
    public static Manager getInstancia()
    {
        if (instancia == null)
            instancia = new Manager();
        
        return instancia;
    }

    public void setAlumnos(HashMap<String, HashMap<String, Alumno>> alumnos)
    {
        this.alumnos = alumnos;
    }

    public HashMap<String, HashMap<String, Alumno>> getAlumnos()
    {
        return alumnos;
    }

    public void setProfesores(HashMap<String, HashMap<String, Profesor>> profesores)
    {
        this.profesores = profesores;
    }

    public HashMap<String, HashMap<String, Profesor>> getProfesores()
    {
        return profesores;
    }

    public void setAsignaturas(HashMap<String, HashMap<String, Asignatura>> asignaturas)
    {
        this.asignaturas = asignaturas;
    }

    public HashMap<String, HashMap<String, Asignatura>> getAsignaturas()
    {
        return asignaturas;
    }

    public void setCursadas(HashMap<String, HashMap<String, Cursada>> cursadas)
    {
        this.cursadas = cursadas;
    }

    public HashMap<String, HashMap<String, Cursada>> getCursadas()
    {
        return cursadas;
    }

    /*
     * ***************************************************************************************************************
     * ***************************************************************************************************************
     * ***************************************************************************************************************
     */

    /**
     * M�todo que realiza un alta de un alumno en el sistema.<br>
     * 
     * <b>Pre:</b> La colecci�n de alumnos ya fue inicializada.<br>
     * <b>Post:</b> El alumno es dado de alta o se lanza una excepci�n en el caso que el formato del mail sea incorrecto.
     * 
     * @param nombre Nombre del alumno. Nombre != null y nombre != "".
     * @param apellido Apellido del alumno. Apellido != null y apellido != "".
     * @param domicilio Domicilio del alumno. Domicilio != null y domicilio != "".
     * @param telefono Telefono del alumno. Telefono != null y telefono != "".
     * @param mail Mail del alumno. Mail != null y mail != "".
     * @throws EmailInvalidoException Excepci�n lanzada si el formato del mail no es v�lido.
     */
    public void altaAlumno(String nombre, String apellido, String domicilio, String telefono, String mail)
        throws EmailInvalidoException // RF01
    {
        Formato.verificaMail(mail);
        Alumno alumno = (Alumno) Factory.getPersona(Factory.ALUMNO, nombre, apellido, domicilio, telefono, mail);
        String nombreCompleto = (alumno.getNombre() + alumno.getApellido()).toUpperCase(); // El hash est� en may�scula
    
        if (this.alumnos.containsKey(nombreCompleto))
            this.alumnos.get(nombreCompleto).put(alumno.getLegajo(), alumno); // Agregamos al HashMap el alumno cuya clave
                                                                              // ser� su legajo
        else
        {
            HashMap<String, Alumno> hash = new HashMap<String, Alumno>(); // Creamos una nueva cubeta y depositamos 
                                                                          // al alumno all�
            hash.put(alumno.getLegajo(), alumno);
            this.alumnos.put(nombreCompleto, hash);
        }
    }

    /**
     * M�todo que da de baja un alumno del sistema.<br>
     * 
     * <b>Pre:</b> La colecci�n de alumnos ya fue inicializada.<br>
     * <b>Post:</b> Se da de baja al alumno de la instituci�n o se lanza una excepci�n si el mismo no fue
     * encontrado en la colecci�n.
     * 
     * @param alumno Alumno a dar de baja. Alumno != null.
     * @throws NoEstaEntidadException Excepci�n lanzada si el alumno no es encontrado en el sistema.
     */
    public void bajaAlumno(Alumno alumno) throws NoEstaEntidadException // RF02
    {
        Iterator<HashMap<String, Cursada>> itH;
        Iterator<Cursada> itC;
        String nombreCompleto = alumno.getClave().toUpperCase(); // El hash est� en may�scula
        
        if (this.alumnos.containsKey(nombreCompleto) && this.alumnos.get(nombreCompleto).remove(alumno.getLegajo(), alumno))
            // Si el alumno existe en la facultad y fue posible eliminarlo proseguimos a eliminarlo de todas las cursadas
        {
            if (this.alumnos.get(nombreCompleto).isEmpty()) // El HashMap ha quedado vac�o, sin alumnos
                this.alumnos.remove(nombreCompleto);
            
            // Eliminamos al alumno de todas las cursadas inscripto
            itH = this.cursadas.values().iterator();
            // La sentencia anterior nos devuelve un iterator de HashMap 
            while(itH.hasNext())
            {
                itC = itH.next().values().iterator();
                while (itC.hasNext())
                    itC.next().getAlumnos().remove(alumno.getLegajo()); // Se lo busca por legajo aqu�
                    // El remove puede dar T o F, logre eliminarlo o no seguimos avanzando.
            }
        }
        else
            throw new NoEstaEntidadException(alumno, "Alumno no encontrado en el sistema.");
    }

    /**
     * M�todo que modifica los atributos del alumno si este ya se encuentra en el sistema.<br>
     * 
     * <b>Pre:</b> El alumno es una entidad v�lida.<br>
     * <b>Post:</b> Los atributos del alumno son modificados en el caso que se lo encuentre y cumplan con el formato.
     * Si el mail no es v�lido se lanza una excepci�n notificando esto y si el alumno no es encontrado en la colecci�n
     * tambi�n se lanza una excepci�n para mostrar este problema.
     * 
     * @param alumno Alumno al cual se le modificar�n los atributos. Alumno != null.
     * @param nombre Nombre del alumno. Nombre != null y nombre != "".
     * @param apellido Apellido del alumno. Apellido != null y apellido != "".
     * @param domicilio Domicilio del alumno. Domicilio != null y domicilio != "".
     * @param telefono Telefono del alumno. Telefono != null y telefono != "".
     * @param mail Mail del alumno. Mail != null y mail != "".
     * @throws NoEstaEntidadException Excepci�n lanzada si el alumno no es encontrado en la colecci�n.
     * @throws EmailInvalidoException Excepci�n lanzada si el mail no cumple con el formato previsto.
     */
    public void modificaAlumno(Alumno alumno, String nombre, String apellido, String domicilio, String telefono, 
                               String mail) throws NoEstaEntidadException, EmailInvalidoException // RF03
    {   
        String nombreViejo;
        String nombreNuevo;
        
        Formato.verificaMail(mail);
        if (!alumno.getNombre().toUpperCase().equals(nombre.toUpperCase()) || !alumno.getApellido().toUpperCase().equals(apellido.toUpperCase()))
        // El nombre o apellido ha cambiado, hay movimiento de cubetas.
        {
            nombreViejo = alumno.getClave().toUpperCase(); // El hash est� en may�scula
            if (this.alumnos.containsKey(nombreViejo) && this.alumnos.get(nombreViejo).remove(alumno.getLegajo(), alumno))
            {
                if (this.alumnos.get(nombreViejo).isEmpty()) // El HashMap ha quedado vac�o, sin alumnos
                    this.alumnos.remove(nombreViejo);
                
                alumno.setNombre(nombre);
                alumno.setApellido(apellido);
                nombreNuevo = alumno.getClave().toUpperCase(); // El hash est� en may�scula
                
                if (this.alumnos.containsKey(nombreNuevo))
                    this.alumnos.get(nombreNuevo).put(alumno.getLegajo(), alumno); // Agregamos al HashMap el alumno
                else
                {
                    HashMap<String, Alumno> hash = new HashMap<String, Alumno>(); // Creamos una nueva cubeta y 
                                                                                  // depositamos al alumno all�
                    hash.put(alumno.getLegajo(), alumno);
                    this.alumnos.put(nombreNuevo, hash);
                }
            }
            else
                throw new NoEstaEntidadException(alumno, "Alumno no encontrado en el sistema.");
        }
        alumno.setDomicilio(domicilio);
        alumno.setTelefono(telefono);
        alumno.setMail(mail);   
    }
    
    /**
     * Se a�ade a la historia acad�mica del alumno la nueva asignatura aprobada por el mismo.<br>
     * 
     * <b>Pre:</b> El alumno y la asignatura son entidades v�lidas que existen en el sistema.<br>
     * <b>Post:</b> El alumno posee la asignatura en su historia acad�mica o, en el caso que ya la tuviese, se lanza
     * una excepci�n.
     * 
     * @param alumno Alumno al cual se le dar� por aprobada la asignatura. Alumno != null.
     * @param asignatura Asignatura aprobada por el alumno. Asignatura != null.
     * @throws EntidadRepetidaException Excepci�n con la asignatura repetida y el mensaje de error.
     */
    public void aprobarAsignatura(Alumno alumno, Asignatura asignatura) throws EntidadRepetidaException // RF03
    {
        if (alumno.getHistoriaAcademica().containsKey(asignatura.getId()))
            throw new EntidadRepetidaException(asignatura, "El alumno ya ha aprobado la asignatura.");
        else
            alumno.getHistoriaAcademica().put(asignatura.getId(), asignatura);
    }

    /**
     * Se elimina de la histor�a acad�mica del alumno la asignatura recibida por par�metro.<br>
     * 
     * <b>Pre:</b> El alumno y la asignatura son entidades v�lidas que existen en el sistema.<br>
     * <b>Post:</b> El alumno deja de poseer la asignatura en su historia acad�mica o, en el caso que no
     * se la encuentre, se lanza una excepci�n.
     * 
     * @param alumno Alumno a quitarle la asignatura. Alumno != null.
     * @param asignatura Asignatura a dar de daja de la historia acad�mica del alumno. Asignatura != null.
     * @throws NoEstaEntidadException Excepci�n lanzada en el caso que la asignatura no se halle en la
     * historia acad�mica del alumno.
     */
    public void bajaAprobada(Alumno alumno, Asignatura asignatura) throws NoEstaEntidadException // RF03
    {
        if (!alumno.getHistoriaAcademica().remove(asignatura.getId(), asignatura))
            throw new NoEstaEntidadException(asignatura, "No se ha encontrado la asignatura en la historia acad�mica del alumno");
    }
    
    public void quitarAsignatura(Alumno alumno, Asignatura asignatura) throws NoEstaEntidadException // RF03
    {
        HashMap <String, Asignatura> historiaAcademica = alumno.getHistoriaAcademica();
        
        if (!historiaAcademica.remove(asignatura.getId(), asignatura))
            throw new NoEstaEntidadException(asignatura, "El alumno no tiene esta asignatura en su historia");
    }

    /**
     * M�todo que devuelve todos aquellos alumnos que cumplen con los par�metros de b�squeda.
     * En este caso, todos aquellos que posean dicho nombre y apellido.<br>
     * 
     * <b>Pre:</b> La colecci�n de alumnos ya fue inicializada.<br>
     * <b>Post:</b> Se devuelve una colecci�n con todos aquellos alumnos que surgieron de la b�squeda o se lanza
     * una excepci�n en el caso que no se encuente a la entidad.
     * 
     * @param nombre Nombre del alumno a ubicar. Nombre != null y nombre != "".
     * @param apellido Apellido del alumno a ubicar. Apellido != null y apellido != "".
     * @return HashMap con todos aquellos alumnos que cumplen la condici�n.
     * @throws NoEstaEntidadException Excepci�n lanzada en el caso que no se encuentre ning�n alumno con 
     * el nombre y apellido solicitados.
     */
    public HashMap<String, Alumno> ubicarAlumno(String nombre, String apellido) throws NoEstaEntidadException // RF05
    {
        HashMap<String, Alumno> ret = this.alumnos.get((nombre + apellido).toUpperCase()); // El hash est� en may�scula
        
        if(ret == null)
            throw new NoEstaEntidadException("Ning�n alumno encontrado.");
        
        return ret;
    }

    /**
     * M�todo que realiza una incripci�n de un alumno a una cursada.<br>
     * 
     * <b>Pre:</b> Tanto el alumno como la cursada son entidades v�lidas que existen en el sistema.<br>
     * <b>Post:</b> El alumno es incripto en la cursada o una excepci�n es lanzada en el caso que el estudiante
     * no cumpla con las correlatividades y/u horarios.
     * 
     * @param alumno Alumno a dar de alta en la cursada. Alumno != null.
     * @param cursada Cursada en la que se inscribir� el alumno. Cursada != null.
     * @throws EntidadNoAptaParaCursadaException Excepci�n lanzada en el caso que el alumno no cumpla con las 
     * correlatividades pedidas o presente una superposici�n de horarios con otra cursada.
     */
    public void altaAlumnoACursada(Alumno alumno, Cursada cursada) throws EntidadNoAptaParaCursadaException 
        // RF11, RF13
    {
        this.verificaAlumnoAptoParaCursada(alumno, cursada);
        cursada.getAlumnos().put(alumno.getLegajo(), alumno); // La clave del alumno ser� su legajo dentro de la Cursada
    }

    /**
     * M�todo que elimina a un alumno de la cursada que se encuentra inscripto.<br>
     * 
     * <b>Pre:</b> Tanto el alumno como la cursada son entidades v�lidas que existen en el sistema.<br>
     * <b>Post:</b> Se elimina al alumno de la cursada correspondiente o se lanza una excepci�n en el
     * caso que no se lo halle en la misma.
     * 
     * @param alumno Alumno a eliminar de la cursada. Alumno != null.
     * @param cursada Cursada en la cual se eliminar� el alumno en cuesti�n. Cursada != null.
     * @throws NoEstaEntidadException Excepci�n lanzada en el caso que no se pueda encontrar al alumno
     * en la cursada.
     */
    public void bajaAlumnoDeCursada(Alumno alumno, Cursada cursada) throws NoEstaEntidadException // RF11, RF14
    {
        if(!cursada.getAlumnos().remove(alumno.getLegajo(), alumno))
            throw new NoEstaEntidadException(alumno, "Alumno no encontrado en la cursada.");
    }
    
    /*
     * ***************************************************************************************************************
     * ***************************************************************************************************************
     * ***************************************************************************************************************
     */

    /**
     * M�todo que realiza un alta de un profesor en el sistema.<br>
     * 
     * <b>Pre:</b> La colecci�n de profesores ya fue inicializada.<br>
     * <b>Post:</b> El profesor es dado de alta o se lanza una excepci�n en el caso que el formato del mail sea incorrecto.
     * 
     * @param nombre Nombre del profesor. Nombre != null y nombre != "".
     * @param apellido Apellido del profesor. Apellido != null y apellido != "".
     * @param domicilio Domicilio del profesor. Domicilio != null y domicilio != "".
     * @param telefono Telefono del profesor. Telefono != null y telefono != "".
     * @param mail Mail del profesor. Mail != null y mail != "".
     * @throws EmailInvalidoException Excepci�n lanzada si el formato del mail no es v�lido.
     */
    public void altaProfesor(String nombre, String apellido, String domicilio, String telefono, String mail)
        throws EmailInvalidoException // RF01
    {
        Formato.verificaMail(mail);
        Profesor profesor = (Profesor) Factory.getPersona(Factory.PROFESOR, nombre, apellido, domicilio, telefono, mail);
        String nombreCompleto = profesor.getClave().toUpperCase(); // El hash est� en may�scula
        
        if (this.profesores.containsKey(nombreCompleto))
            this.profesores.get(nombreCompleto).put(profesor.getLegajo(), profesor); // Agregamos al HashMap el profesor 
                                                                                     // cuya clave ser� su legajo
        else
        {
            HashMap<String, Profesor> hash = new HashMap<String, Profesor>(); // Creamos una nueva cubeta y depositamos 
                                                                              // al profesor all�
            hash.put(profesor.getLegajo(), profesor);
            this.profesores.put(nombreCompleto, hash); 
        }
    }
    
    /**
     * M�todo que da de baja un profesor del sistema.<br>
     * 
     * <b>Pre:</b> La colecci�n de profesores ya fue inicializada.<br>
     * <b>Post:</b> Se da de baja al profesor de la instituci�n o se lanza una excepci�n si el mismo no fue
     * encontrado en la colecci�n.
     * 
     * @param profesor Profesor a dar de baja. Profesor != null.
     * @throws NoEstaEntidadException Excepci�n lanzada si el profesor no es encontrado en el sistema.
     */
    public void bajaProfesor(Profesor profesor) throws NoEstaEntidadException // RF02
    {
        Iterator<HashMap<String, Cursada>> itH;
        Iterator<Cursada> itC;
        String nombreCompleto = profesor.getClave().toUpperCase(); // El hash est� en may�scula
        
        if (this.profesores.containsKey(nombreCompleto) && this.profesores.get(nombreCompleto).remove(profesor.getLegajo(), profesor))
            // Si el profesor existe en la facultad y fue posible eliminarlo proseguimos a eliminarlo de todas las cursadas
        {
            if (this.profesores.get(nombreCompleto).isEmpty()) // El HashMap ha quedado vac�o, sin profesores
                this.profesores.remove(nombreCompleto);
            
            // Eliminamos al profesor de todas las cursadas inscripto
            itH = this.cursadas.values().iterator();
            // La sentencia anterior nos devuelve un iterator de TreeSet 
            while(itH.hasNext())
            {
                itC = itH.next().values().iterator();
                while (itC.hasNext())
                    itC.next().getProfesores().remove(profesor.getLegajo()); // Se lo busca por legajo aqu�
                    // El remove puede dar T o F, logre eliminarlo o no seguimos avanzando.
            }
        }
        else
            throw new NoEstaEntidadException(profesor, "Profesor no encontrado en el sistema.");
    }
    
    /**
     * M�todo que modifica los atributos del profesor si este ya se encuentra en el sistema.<br>
     * 
     * <b>Pre:</b> El profesor es una entidad v�lida.<br>
     * <b>Post:</b> Los atributos del profesor son modificados en el caso que se lo encuentre y cumplan con el formato.
     * Si el mail no es v�lido se lanza una excepci�n notificando esto y si el profesor no es encontrado en la colecci�n
     * tambi�n se lanza una excepci�n para mostrar este problema.
     * 
     * @param profesor Profesor al cual se le modificar�n los atributos. Profesor != null.
     * @param nombre Nombre del profesor. Nombre != null y nombre != "".
     * @param apellido Apellido del profesor. Apellido != null y apellido != "".
     * @param domicilio Domicilio del profesor. Domicilio != null y domicilio != "".
     * @param telefono Telefono del profesor. Telefono != null y telefono != "".
     * @param mail Mail del profesor. Mail != null y mail != "".
     * @throws NoEstaEntidadException Excepci�n lanzada si el profesor no es encontrado en la colecci�n.
     * @throws EmailInvalidoException Excepci�n lanzada si el mail no cumple con el formato previsto.
     */
    public void modificaProfesor(Profesor profesor, String nombre, String apellido, String domicilio, String telefono,
                                 String mail) throws NoEstaEntidadException, EmailInvalidoException // RF03
    {   
        String nombreViejo;
        String nombreNuevo;
        
        Formato.verificaMail(mail);
        if (!profesor.getNombre().toUpperCase().equals(nombre.toUpperCase()) || !profesor.getApellido().toUpperCase().equals(apellido.toUpperCase()))
        // El nombre o apellido ha cambiado, hay movimiento de cubetas.
        {
            nombreViejo = profesor.getClave().toUpperCase(); // El hash est� en may�scula
            
            if (this.profesores.containsKey(nombreViejo) && this.profesores.get(nombreViejo).remove(profesor.getLegajo(), profesor))
            {
                if (this.profesores.get(nombreViejo).isEmpty()) // El HashMap ha quedado vac�o, sin profesores
                    this.profesores.remove(nombreViejo);
                
                profesor.setNombre(nombre);
                profesor.setApellido(apellido);
                nombreNuevo = profesor.getClave().toUpperCase(); // El hash est� en may�scula
                
                if (this.profesores.containsKey(nombreNuevo))
                    this.profesores.get(nombreNuevo).put(profesor.getLegajo(), profesor); // Agregamos al HashMap el profesor
                else
                {
                    HashMap<String, Profesor> hash = new HashMap<String, Profesor>(); // Creamos una nueva cubeta y 
                                                                                      // depositamos al profesor all�
                    hash.put(profesor.getLegajo(), profesor);
                    this.profesores.put(nombreNuevo, hash); 
                }
            }
            else
                throw new NoEstaEntidadException(profesor, "Profesor no encontrado en el sistema.");
        }
        profesor.setDomicilio(domicilio);
        profesor.setTelefono(telefono);
        profesor.setMail(mail);  
    }

    /**
     * Se a�ade a las competencias del profesor una nueva asignatura que est� en condiciones de dictar.<br>
     * 
     * <b>Pre:</b> El profesor y la asignatura son entidades v�lidas que existen en el sistema.<br>
     * <b>Post:</b> El profesor comienza a poseer la asignatura en sus competencias o, en el caso que ya la tuviese, 
     * se lanza una excepci�n.
     * 
     * @param profesor Profesor al cual se le dar� por v�lida una nueva asignatura a dictar. Profesor != null.
     * @param asignatura Asignatura que puede ense�ar el profesor. Asignatura != null.
     * @throws EntidadRepetidaException Excepci�n con la asignatura repetida y el mensaje de error.
     */
    public void agregarCompetencia(Profesor profesor, Asignatura asignatura) throws EntidadRepetidaException // RF03
    {   
        if (profesor.getCompetencias().containsKey(asignatura.getId()))
            throw new EntidadRepetidaException(asignatura, "El profesor ya tiene la competencia.");
        else
            profesor.getCompetencias().put(asignatura.getId(), asignatura);
    }

    /**
     * M�todo que elimina una asignatura de las competencias del profesor en el caso que se la encuentre.<br>
     * 
     * <b>Pre:</b> El profesor y la asignatura son entidades v�lidas que existen en el sistema.<br>
     * <b>Post:</b> El profesor ya no se encuentra m�s habilitado para dictar la asignatura. En el caso que no se 
     * la encuentre se lanza una excepci�n informando de la imposibilidad de eliminarla.
     * 
     * @param profesor Profesor a quitarle su competencia. Profesor != null.
     * @param asignatura Asignatura a eliminar de las competencias del profesor. Asignatura != null.
     * @throws NoEstaEntidadException Excepci�n lanzada en el caso que no se encuentre la asignatura entre las
     * competencias del profesor.
     */
    public void bajaCompetencia(Profesor profesor, Asignatura asignatura) throws NoEstaEntidadException // RF03
    {
        if (!profesor.getCompetencias().remove(asignatura.getId(), asignatura))
            throw new NoEstaEntidadException(asignatura, "No se encuentra la asignatura entre las competencias.");
    }
    
    /**
     * M�todo que devuelve todos aquellos profesores que cumplen con los par�metros de b�squeda.
     * En este caso, todos aquellos que posean dicho nombre y apellido.<br>
     * 
     * <b>Pre:</b> La colecci�n de profesores ya fue inicializada.<br>
     * <b>Post:</b> Se devuelve una colecci�n con todos aquellos profesores que surgieron de la b�squeda o se lanza
     * una excepci�n en el caso que no se encuente a la entidad.
     * 
     * @param nombre Nombre del profesor a ubicar. Nombre != null y nombre != "".
     * @param apellido Apellido del profesor a ubicar. Apellido != null y apellido != "".
     * @return HashMap con todos aquellos profesores que cumplen la condici�n.
     * @throws NoEstaEntidadException Excepci�n lanzada en el caso que no se encuentre ning�n profesor con 
     * el nombre y apellido solicitados.
     */
    public HashMap<String, Profesor> ubicarProfesor(String nombre, String apellido) throws NoEstaEntidadException // RF05
    {
        HashMap<String, Profesor> ret = this.profesores.get((nombre + apellido).toUpperCase()); // El hash est� en may�scula
        
        if(ret == null)
            throw new NoEstaEntidadException("Ning�n profesor encontrado.");
        
        return ret;
    }
    
    /**
     * M�todo que realiza una incripci�n de un profesor a una cursada.<br>
     * 
     * <b>Pre:</b> Tanto el profesor como la cursada son entidades v�lidas que existen en el sistema.<br>
     * <b>Post:</b> El profesor es incripto en la cursada o una excepci�n es lanzada en el caso que el docente
     * no cumpla con las competencias y/u horarios.
     * 
     * @param profesor Profesor a dar de alta en la cursada. Profesor != null.
     * @param cursada Cursada en la que se inscribir� el profesor. Cursada != null.
     * @throws EntidadNoAptaParaCursadaException Excepci�n lanzada en el caso que el profesor no  posea la asignatura
     * de la cursada entre sus competencias o presente una superposici�n de horarios con otra cursada.
     */
    public void altaProfesorACursada(Profesor profesor, Cursada cursada) throws EntidadNoAptaParaCursadaException
        // RF12, RF15
    {
        this.verificaProfesorAptoParaCursada(profesor, cursada);
        cursada.getProfesores().put(profesor.getLegajo(), profesor); // La clave del profesor ser� su legajo dentro de 
                                                                     // la Cursada
    }
             
    /**
     * M�todo que elimina a un profesor de la cursada que se encuentra inscripto.<br>
     * 
     * <b>Pre:</b> Tanto el profesor como la cursada son entidades v�lidas que existen en el sistema.<br>
     * <b>Post:</b> Se elimina al profesor de la cursada correspondiente o se lanza una excepci�n en el
     * caso que no se lo halle en la misma.
     * 
     * @param profesor Profesor a eliminar de la cursada. Profesor != null.
     * @param cursada Cursada en la cual se eliminar� el profesor en cuesti�n. Cursada != null.
     * @throws NoEstaEntidadException Excepci�n lanzada en el caso que no se pueda encontrar al profesor
     * en la cursada.
     */                   
    public void bajaProfesorDeCursada(Profesor profesor, Cursada cursada) throws NoEstaEntidadException // RF12, RF16
    {
        if(!cursada.getProfesores().remove(profesor.getLegajo(), profesor))
            throw new NoEstaEntidadException(profesor, "Profesor no encontrado en la cursada");
    }
    
    /*
     * ***************************************************************************************************************
     * ***************************************************************************************************************
     * ***************************************************************************************************************
     */
    
    /**
     * M�todo que realiza un alta de una asignatura en el sistema.<br>
     * 
     * <b>Pre:</b> La colecci�n de asignaturas ya fue inicializada.<br>
     * <b>Post:</b> La asignatura es dada de alta.
     * 
     * @param nombre Nombre de la asignatura. Nombre != null y nombre != "".
     */
    public void altaAsignatura(String nombre) // RF01
    {
        String nombreAsignaturaMayus = nombre.toUpperCase(); // El hash est� en may�scula
        
        Asignatura asignatura = Factory.getAsignatura(nombreAsignaturaMayus);
        
        if (this.asignaturas.containsKey(nombreAsignaturaMayus))
            this.asignaturas.get(nombreAsignaturaMayus).put(asignatura.getId(), asignatura); // Agregamos al HashMap la asignatura
        else
        {
            HashMap<String, Asignatura> hash = new HashMap<String, Asignatura>(); // Creamos una nueva cubeta y 
                                                                                  // depositamos a la asignatura all�
            hash.put(asignatura.getId(), asignatura);
            this.asignaturas.put(nombreAsignaturaMayus, hash); 
        }
    }
    
    /**
     * M�todo que da de baja una asignatura del sistema.<br>
     * 
     * <b>Pre:</b> La colecci�n de asignaturas ya fue inicializada.<br>
     * <b>Post:</b> Se da de baja a la asignatura de la instituci�n y consecuentemente se hacen tres cosas:<br>
     * Se eliminan todas las cursadas que la tienen como asignatura.<br>
     * Se elimina la asignatura de todas las colecciones de correlatividades de aquellas asignaturas que la tienen como 
     * correlativa.<br>
     * Se elimina de todas las colecciones de competencias de profesores que la poseen.<br>
     * 
     * En el caso que no se la pueda encontrar en la colecci�n de asignaturas, se lanza una excepci�n y no se realiza
     * ninguno de los tres �tems expuestos.
     * 
     * @param asignatura Asignatura a dar de baja. Asignatura != null.
     * @throws NoEstaEntidadException Excepci�n lanzada si la asignatura no es encontrada en el sistema.
     */
    public void bajaAsignatura(Asignatura asignatura) throws NoEstaEntidadException // RF02
    {
        Iterator<HashMap<String, Cursada>> itHC;
        Iterator<Cursada> itC;
        Cursada cursada;
        String nombreAsignaturaMayus = asignatura.getClave().toUpperCase(); // El hash est� en may�scula
        
        Iterator<HashMap<String, Asignatura>> itHA;
        Iterator<Asignatura> itA;
        
        Iterator<HashMap<String, Profesor>> itHP;
        Iterator<Profesor> itP;
        
        String nombreCursadaMayus;
        
        if (this.asignaturas.containsKey(nombreAsignaturaMayus) && this.asignaturas.get(nombreAsignaturaMayus).remove(asignatura.getId(), asignatura))
            // Si hemos encontrado la asignatura y podido eliminarla
        {   
            if (this.asignaturas.get(nombreAsignaturaMayus).isEmpty()) // Si el HashMap de las asignaturas ha quedado
                                                                        // vac�o lo eliminamos del HashMap general de 
                                                                        // asignaturas
                this.asignaturas.remove(nombreAsignaturaMayus);
            
            // Eliminamos todas las cursadas en las que la asignatura aparece
            itHC = this.cursadas.values().iterator();
            while(itHC.hasNext())
            {
                itC = itHC.next().values().iterator();
                while (itC.hasNext())
                {
                    cursada = itC.next();
                    if (cursada.getAsignatura().equals(asignatura))
                    {
                        nombreCursadaMayus = cursada.getClave().toUpperCase(); // El hash est� en may�scula
                        this.cursadas.get(nombreCursadaMayus).remove(cursada); // Eliminamos la cursada de su HashMap
                        if (this.cursadas.get(nombreCursadaMayus).isEmpty()) // Si el HashMap queda vac�o se lo debe
                                                                             // eliminar del HashMap general de cursadas
                            this.cursadas.remove(nombreCursadaMayus);
                    }
                }
            }
            
            // Borramos a la asignatura de todas aquellas asignaturas que la posean como correlativa
            itHA = this.asignaturas.values().iterator();
            while (itHA.hasNext())
            {
                itA = itHA.next().values().iterator();
                while (itA.hasNext())
                    itA.next().getCorrelatividades().remove(asignatura);
            }
            
            // Borramos a la asignatura de todos los profesores que la tengan como una competencia
            itHP = this.profesores.values().iterator();
            while (itHP.hasNext())
            {
                itP = itHP.next().values().iterator();
                while (itP.hasNext())
                    itP.next().getCompetencias().remove(asignatura);
            }            
        }
        else
            throw new NoEstaEntidadException(asignatura, "Asignatura no encontrada en el sistema.");
    }
    
    /**
     * M�todo que modifica el nombre de la asignatura si la misma se encuentra en el sistema.<br>
     * 
     * <b>Pre:</b> La asignatura es una entidad v�lida.<br>
     * <b>Post:</b> El nombre de la asignatura es modificado en el caso que se la encuentre. Si esto no es as�
     * se lanza una excepci�n para notificar que no se hall� a la asignatura.
     * 
     * @param asignatura Asignatura a la cual se le modificar� el nombre. Asignatura != null.
     * @param nombre Nombre nuevo de la asignatura. Nombre != null y nombre != "".
     * @throws NoEstaEntidadException Excepci�n lanzada si la asignatura no es encontrada en la colecci�n.
     */
    public void modificaAsignatura(Asignatura asignatura, String nombre)
        throws NoEstaEntidadException // RF03
    {
        String nombreAsignaturaMayus = asignatura.getClave().toUpperCase(); // El hash est� en may�scula
        if (!nombreAsignaturaMayus.equals(nombre.toUpperCase()))
        // El nombre de la asignatura ha cambiado, habr� un movimiento de cubetas.
        {            
            if (this.asignaturas.containsKey(nombreAsignaturaMayus) && this.asignaturas.get(nombreAsignaturaMayus).remove(asignatura.getId(), asignatura))
            {
                if (this.asignaturas.get(nombreAsignaturaMayus).isEmpty()) // El HashMap ha quedado vac�o, sin asignaturas
                    this.asignaturas.remove(nombreAsignaturaMayus);
                
                asignatura.setNombre(nombre);
                nombreAsignaturaMayus = nombre.toUpperCase(); // El hash est� en may�scula
                
                if (this.asignaturas.containsKey(nombreAsignaturaMayus))
                    this.asignaturas.get(nombreAsignaturaMayus).put(asignatura.getId(), asignatura); // Agregamos al HashMap la asignatura
                else
                {
                    HashMap<String, Asignatura> hash = new HashMap<String, Asignatura>(); // Creamos una nueva cubeta y 
                                                                                          // depositamos a la
                                                                                          // asignatura all�
                    hash.put(asignatura.getId(), asignatura);
                    this.asignaturas.put(nombreAsignaturaMayus, hash); 
                }
            }
            else
                throw new NoEstaEntidadException(asignatura, "Asignatura no encontrada en el sistema.");
        }
    }
    
    /**
     * Se a�ade a las correlatividades de la asignatura una nueva asignatura que ser� necesaria para poder cursarla.<br>
     * 
     * <b>Pre:</b> La asignatura y la correlativa son entidades v�lidas que existen en el sistema.<br>
     * <b>Post:</b> La asignatura comienza a poseer a la correlativa en sus correlatividades o, en el caso que ya la 
     * tuviese, se lanza una excepci�n.
     * 
     * @param asignatura Asignatura a la cual se le agregar� una nueva correlativa. Asignatura != null.
     * @param correlativa Asignatura que comenzar� a ser correlativa de la asignatura en cuesti�n. Correlativa != null.
     * @throws EntidadRepetidaException Excepci�n con la correlativa repetida y el mensaje de error.
     */
    public void agregarCorrelativa(Asignatura asignatura, Asignatura correlativa) throws EntidadRepetidaException
    {
        if (asignatura.getCorrelatividades().containsKey(correlativa.getId()))
            throw new EntidadRepetidaException(correlativa, "La asignatura ya tiene la correlativa.");
        else 
            asignatura.getCorrelatividades().put(correlativa.getId(), correlativa);
    }
    
    /**
     * M�todo que elimina una correlativa de las correlatividades de la asignatura en el caso que se la encuentre.<br>
     * 
     * <b>Pre:</b> La asignatura y la correlativa son entidades v�lidas que existen en el sistema.<br>
     * <b>Post:</b> La correlativa ya no se encuentra m�s dentro de las correlatividades de la asignatura.
     * En el caso que no se la encuentre se lanza una excepci�n informando de la imposibilidad de eliminarla.
     * 
     * @param asignatura Asignatura a quitarle una de sus correlatividades. Asignatura != null.
     * @param correlativa Correlativa a eliminar de las correlatividades de la asignatura en cuesti�n. Correlativa != null.
     * @throws NoEstaEntidadException Excepci�n lanzada en el caso que no se encuentre la correlativa entre las
     * correlatividades de la asignatura.
     */
    public void bajaCorrelativa(Asignatura asignatura, Asignatura correlativa) throws NoEstaEntidadException
    {
        if (!asignatura.getCorrelatividades().remove(correlativa.getId(), correlativa))
            throw new NoEstaEntidadException(correlativa, "No se encuentra la correlativa dentro de las correlatividades.");
    }
    
    /**
     * M�todo que devuelve todas aquellas asignaturas que cumplen con los par�metros de b�squeda.
     * En este caso, todas aquellas que posean dicho nombre.<br>
     * 
     * <b>Pre:</b> La colecci�n de asignaturas ya fue inicializada.<br>
     * <b>Post:</b> Se devuelve una colecci�n con todas aquellas asignaturas que surgieron de la b�squeda o se lanza
     * una excepci�n en el caso que no se encuente a la entidad.
     * 
     * @param nombre Nombre de la asignatura a ubicar. Nombre != null y nombre != "".
     * @return HashMap con todas aquellas asignaturas que cumplen la condici�n.
     * @throws NoEstaEntidadException Excepci�n lanzada en el caso que no se encuentre ninguna asignatura con 
     * el nombre solicitado.
     */
    public HashMap<String, Asignatura> ubicarAsignatura(String nombre) throws NoEstaEntidadException // RF05
    {
        HashMap<String, Asignatura> ret = this.asignaturas.get(nombre.toUpperCase()); // El hash est� en may�scula
        
        if(ret == null)
            throw new NoEstaEntidadException("Ninguna asignatura encontrada.");
        
        return ret;
    }
    
    /*
     * ***************************************************************************************************************
     * ***************************************************************************************************************
     * ***************************************************************************************************************
     */

    /**
     * M�todo que realiza un alta de una cursada en el sistema.<br>
     * 
     * <b>Pre:</b> La colecci�n de cursadas ya fue inicializada.<br>
     * <b>Post:</b> La cursada es dada de alta o se lanza alguna excepci�n en el caso que el per�odo o alguna
     * de las horas no cumpla con los formatos prefijados.
     * 
     * @param nombre Nombre de la cursada a dar de alta. Nombre != null y nombre != "".
     * @param asignatura Asignatura a la que pertenecer� la cursada. Asignatura != null.
     * @param periodo Per�odo de la cursada. Periodo != null y periodo != "".
     * @param dia Dia de la semana en que se dicta la cursada. Dia != null y dia != "".
     * @param horaInicio Hora de inicio de la cursada. HoraInicio != null y horaInicio != "".
     * @param horaFin Hora de finalizaci�n de la cursada. HoraFin != null y horaFin != "".
     * @throws PeriodoInvalidoException Excepci�n lanzada si el formato del periodo no es v�lido.
     * @throws HoraInvalidaException Excepci�n lanzada si el formato de la hora no es v�lido.
     */
    public void altaCursada(String nombre, Asignatura asignatura, String periodo, String dia, String horaInicio, 
                            String horaFin) throws PeriodoInvalidoException, HoraInvalidaException // RF06
    {
        Formato.verificaCursadaPeriodo(periodo);
        Formato.verificaCursadaHora(horaInicio);
        Formato.verificaCursadaHora(horaFin);
        String nombreCursadaMayus = nombre.toUpperCase(); // El hash est� en may�scula
        Cursada cursada = Factory.getCursada(nombre, asignatura, periodo, dia, horaInicio, horaFin);
        
        if (this.cursadas.containsKey(nombreCursadaMayus))
            this.cursadas.get(nombreCursadaMayus).put(cursada.getId(), cursada); // Agregamos al HashMap la cursada
        else
        {
            HashMap<String, Cursada> hash = new HashMap<String, Cursada>(); // Creamos una nueva cubeta y depositamos
                                                                            // a la cursada all�
            hash.put(cursada.getId(), cursada);
            this.cursadas.put(nombreCursadaMayus, hash); 
        }
    }
    
    /**
     * M�todo que da de baja una cursada del sistema.<br>
     * 
     * <b>Pre:</b> La colecci�n de cursadas ya fue inicializada.<br>
     * <b>Post:</b> Se da de baja a la cursada de la instituci�n o, en el caso que no se la pueda encontrar,
     * se lanza una excepci�n informando de esta imposibilidad.
     * 
     * @param cursada Cursada a dar de baja. Cursada != null.
     * @throws NoEstaEntidadException Excepci�n lanzada si la cursada no es encontrada en el sistema.
     */
    public void bajaCursada(Cursada cursada) throws NoEstaEntidadException // RF07
    {        
        String nombreCursadaMayus = cursada.getClave().toUpperCase(); // El hash est� en may�scula
        
        if (this.cursadas.containsKey(nombreCursadaMayus) && this.cursadas.get(nombreCursadaMayus).remove(cursada.getId(), cursada))
            // Si hemos encontrado la cursada y podido eliminarla
            if (this.cursadas.get(nombreCursadaMayus).isEmpty()) // Si el HashMap de las cursadas ha quedado vac�o
                                                                  // lo eliminamos del HashMap general de cursadas
                this.cursadas.remove(nombreCursadaMayus);
        else
            throw new NoEstaEntidadException(cursada, "Cursada no encontrada en el sistema.");
    }
    
    /**
     * M�todo que modifica los atributos de la cursada si la misma se encuentra en el sistema.<br>
     * 
     * <b>Pre:</b> La cursada es una entidad v�lida.<br>
     * <b>Post:</b> Los atributos de la cursada son modificados en el caso que se la encuentre. Si esto no es as�
     * se lanza una excepci�n para notificar que no se hall� a la cursada. Si alguno de los atributos periodo,
     * horaInicio u horaFin no cumplen con el formato tambi�n se lanza la excepci�n correspondiente.
     * 
     * @param cursada  ursada a la cual se le modificar�n sus atributos. Cursada != null.
     * @param nombre Nombre de la cursada. Nombre != null y nombre != "".
     * @param asignatura Asignatura a la que pertenece la cursada. Asignatura != null.
     * @param periodo Per�odo de la cursada. Periodo != null y periodo != "".
     * @param dia Dia de la semana en que se dicta la cursada. Dia != null y dia != "".
     * @param horaInicio Hora de inicio de la cursada. HoraInicio != null y horaInicio != "".
     * @param horaFin Hora de finalizaci�n de la cursada. HoraFin != null y horaFin != "".
     * @throws NoEstaEntidadException Excepci�n lanzada si la cursada no es encontrada en la colecci�n.
     * @throws PeriodoInvalidoException Excepci�n lanzada si el formato del periodo no es v�lido.
     * @throws HoraInvalidaException Excepci�n lanzada si el formato de la hora no es v�lido.
     */
    public void modificaCursada(Cursada cursada, String nombre, Asignatura asignatura, String periodo, String dia, 
                                String horaInicio, String horaFin) throws NoEstaEntidadException, PeriodoInvalidoException, 
                                HoraInvalidaException //RF08
    {
        Formato.verificaCursadaPeriodo(periodo);
        Formato.verificaCursadaHora(horaInicio);
        Formato.verificaCursadaHora(horaFin);
        String nombreCursadaMayus = cursada.getClave().toUpperCase(); // El hash est� en may�scula
        
        if (!nombreCursadaMayus.equals(nombre.toUpperCase()))
        {            
            if (this.cursadas.containsKey(nombreCursadaMayus) && this.cursadas.get(nombreCursadaMayus).remove(cursada.getId(), cursada))
            {
                if (this.cursadas.get(nombreCursadaMayus).isEmpty()) // El HashMap ha quedado vac�o, sin cursadas
                    this.cursadas.remove(nombreCursadaMayus);
                
                cursada.setNombre(nombre);
                nombreCursadaMayus = nombre.toUpperCase(); // El hash est� en may�scula
                
                if (this.cursadas.containsKey(nombreCursadaMayus))
                    this.cursadas.get(nombreCursadaMayus).put(cursada.getId(), cursada); // Agregamos al HashMap la cursada
                else
                {
                    HashMap<String, Cursada> hash = new HashMap<String, Cursada>(); // Creamos una nueva cubeta y 
                                                                                    // depositamos a la cursada all�
                    hash.put(cursada.getId(), cursada);
                    this.cursadas.put(nombreCursadaMayus, hash); 
                }
            }
            else
                throw new NoEstaEntidadException(cursada, "Cursada no encontrada en el sistema.");
        }
        cursada.setAsignatura(asignatura);
        cursada.setPeriodo(periodo);
        cursada.setDia(dia);
        cursada.setHoraInicio(horaInicio);
        cursada.setHoraFin(horaFin);   
    }
    
    /**
     * M�todo que devuelve todas aquellas cursadas que cumplen con los par�metros de b�squeda.
     * En este caso, todas aquellas que posean dicho nombre.<br>
     * 
     * <b>Pre:</b> La colecci�n de cursadas ya fue inicializada.<br>
     * <b>Post:</b> Se devuelve una colecci�n con todas aquellas cursadas que surgieron de la b�squeda o se lanza
     * una excepci�n en el caso que no se encuente a la entidad.
     * 
     * @param nombre Nombre de la cursada a ubicar. Nombre != null y nombre != "".
     * @return HashMap con todas aquellas cursadas que cumplen la condici�n.
     * @throws NoEstaEntidadException Excepci�n lanzada en el caso que no se encuentre ninguna cursada con 
     * el nombre solicitado.
     */
    public HashMap<String, Cursada> ubicarCursada(String nombre) throws NoEstaEntidadException // RF05
    {
        HashMap<String, Cursada> ret = this.cursadas.get(nombre.toUpperCase()); // El hash est� en may�scula
        
        if(ret == null)
            throw new NoEstaEntidadException("Ninguna cursada encontrada");
        
        return ret;
    }
    
    /*
     * ***************************************************************************************************************
     * ***************************************************************************************************************
     * ***************************************************************************************************************
     */

    /**
     * M�todo que verifica si los horarios de las cursadas pasadas por par�metro se superponen.<br>
     * 
     * <b>Pre:</b> Las cursadas son v�lidas y existen en el sistema.<br>
     * <b>Post:</b> Se devuelve T o F dependiendo si los horarios se superponen o no.
     * 
     * @param c1 Cursada a comparar con c2. c1 != null.
     * @param c2 Cursada a comparar con c1. c2 != null.
     * @return Verdadero si las cursadas se superponen y Falso en caso contrario.
     */
    public boolean horariosSolapan(Cursada c1, Cursada c2)
    {
        return c1.getPeriodo().equals(c2.getPeriodo()) && c1.getDia().equals(c2.getDia()) &&
                    !(c1.getHoraInicio().compareTo(c2.getHoraFin()) > 0 || c1.getHoraFin().compareTo(c2.getHoraInicio()) < 0); 
        // Se verifica si las cursadas no se superponen y luego se lo niega. 
        // Esto es m�s sencillo que verificar si las cursada se superponen.
    }

    /**
     * M�todo que comprueba si el alumno es apto para inscribirse en la cursada. Se verifica que posea las 
     * correlativas previstas y que est� disponible en el horario de la cursada a inscribirse.<br>
     * 
     * <b>Pre:</b> El alumno y las cursada son entidades existentes en el sistema.<br>
     * <b>Post:</b> El m�todo finaliza su ejecuci�n si se cumplen las condiciones enunciadas. Caso contrario
     * se lanza una excepci�n informando que el alumno no es apto para asistir a la cursada.
     * 
     * @param alumno Alumno a evaluar. Alumno != null.
     * @param cursada Cursada en la que desea inscribirse el alumno. Cursada != null.
     * @throws EntidadNoAptaParaCursadaException Excepci�n lanzada en el caso que el alumno no cuente con las
     * correlativas o est� cursando en otra cursada en el mismo horario.
     */
    public void verificaAlumnoAptoParaCursada(Alumno alumno, Cursada cursada) throws EntidadNoAptaParaCursadaException
    {
        this.verificaAlumnoHabilitado(alumno, cursada);
        this.verificaAlumnoOcupado(alumno, cursada);
    }

    /**
     * M�todo que verifica si el alumno cuenta con las correlativas necesarias para inscribirse a la cursada.<br>
     * 
     * <b>Pre:</b> Tanto el alumno como la cursada son entidades v�lidas y existentes en el sistema.<br>
     * <b>Post:</b> El m�todo finaliza su ejecuci�n si el alumno cuenta con las correlativas necesarias.
     * Si esto no es as� una excepci�n es lanzada informando esta situaci�n.
     * 
     * @param alumno Alumno a verificar. Alumno != null.
     * @param cursada Cursada en la que desea inscribirse el alumno. Cursada != null.
     * @throws EntidadNoAptaParaCursadaException Excepci�n lanzada en el caso que el alumno no cuente con las
     * correlativas necesarias.
     */
    public void verificaAlumnoHabilitado(Alumno alumno, Cursada cursada) throws EntidadNoAptaParaCursadaException
        // RF19
    {
        Iterator<Asignatura> it;
        boolean sigue = true;
        
        it = cursada.getAsignatura().getCorrelatividades().values().iterator();
        while(it.hasNext() && sigue)
            sigue = alumno.getHistoriaAcademica().containsKey(it.next().getId()); // La clave de la asignatura es su ID
        
        if(!sigue)
            throw new EntidadNoAptaParaCursadaException(alumno, "El alumno no cumple con las correlatividades para " +
                "inscribirse a la cursada");
    }

     /**
      * M�todo que verifica si el alumno se encuentra disponible para poder asistir a la cursada.<br>
      * 
      * <b>Pre:</b> Tanto el alumno como la cursada son entidades v�lidas y existentes en el sistema.<br>
      * <b>Post:</b> El m�todo finaliza su ejecuci�n si el alumno puede asistir a la cursada.
      * Si esto no es as� una excepci�n es lanzada informando esta situaci�n.
      * 
      * @param alumno Alumno a verificar. Alumno != null.
      * @param cursada Cursada en la que desea inscribirse el alumno. Cursada != null.
      * @throws EntidadNoAptaParaCursadaException Excepci�n lanzada en el caso que el alumno est� cursando
      * otra cursada y los horarios se superpongan.
      */
    public void verificaAlumnoOcupado(Alumno alumno, Cursada cursada) throws EntidadNoAptaParaCursadaException
        // RF21
    {
        Iterator<HashMap<String, Cursada>> itH;
        Iterator<Cursada> itC;
        Cursada c;
        boolean sigue = true;
        
        itH = this.cursadas.values().iterator();
        while (itH.hasNext() && sigue)
        {
            itC = itH.next().values().iterator();
            while(itC.hasNext() && sigue)
            {
                c = itC.next();
                if(c.getAlumnos().containsKey(alumno.getLegajo()))
                    sigue = !this.horariosSolapan(cursada, c);
            }
        }
        
        if(!sigue)
            throw new EntidadNoAptaParaCursadaException(alumno, "El alumno no cumple con las franjas horarias para " +
                "inscribirse a la cursada");
    }
    
    /**
     * M�todo que comprueba si el profesor es apto para inscribirse en la cursada. Se verifica que posea la 
     * asignatura de la cursada entre sus competencias y que est� disponible en el horario de la cursada a inscribirse.<br>
     * 
     * <b>Pre:</b> El profesor y las cursada son entidades existentes en el sistema.<br>
     * <b>Post:</b> El m�todo finaliza su ejecuci�n si se cumplen las condiciones enunciadas. Caso contrario
     * se lanza una excepci�n informando que el profesor no es apto para dictar la cursada.
     * 
     * @param profesor Profesor a evaluar. Profesor != null.
     * @param cursada Cursada en la que desea inscribirse el profesor. Cursada != null.
     * @throws EntidadNoAptaParaCursadaException Excepci�n lanzada en el caso que el profesor no cuente con la
     * competencia o est� ense�ando en otra cursada en el mismo horario.
     */
    public void verificaProfesorAptoParaCursada(Profesor profesor, Cursada cursada) throws EntidadNoAptaParaCursadaException
    {
        this.verificaProfesorHabilitado(profesor, cursada);
        this.verificaProfesorOcupado(profesor, cursada);
    }
            
    /**
     * M�todo que verifica si el profesor cuenta con la competencia necesaria para inscribirse a la cursada.<br>
     * 
     * <b>Pre:</b> Tanto el profesor como la cursada son entidades v�lidas y existentes en el sistema.<br>
     * <b>Post:</b> El m�todo finaliza su ejecuci�n si el profesor cuenta con la competencia necesaria.
     * Si esto no es as� una excepci�n es lanzada informando esta situaci�n.
     * 
     * @param profesor Profesor a verificar. Profesor != null.
     * @param cursada Cursada en la que desea inscribirse el profesor. Cursada != null.
     * @throws EntidadNoAptaParaCursadaException Excepci�n lanzada en el caso que el profesor no cuente con la
     * competencia necesaria.
     */
    public void verificaProfesorHabilitado(Profesor profesor, Cursada cursada) throws EntidadNoAptaParaCursadaException
        // RF18
    {
        if(!profesor.getCompetencias().containsKey(cursada.getAsignatura().getId()))
                throw new EntidadNoAptaParaCursadaException(profesor, "El profesor no est� habilitado para dictar la cursada.");

    }
    
    /**
     * M�todo que verifica si el profesor se encuentra disponible para poder asistir a la cursada.<br>
     * 
     * <b>Pre:</b> Tanto el profesor como la cursada son entidades v�lidas y existentes en el sistema.<br>
     * <b>Post:</b> El m�todo finaliza su ejecuci�n si el profesor puede asistir a la cursada.
     * Si esto no es as� una excepci�n es lanzada informando esta situaci�n.
     * 
     * @param profesor Profesor a verificar. Profesor != null.
     * @param cursada Cursada en la que desea inscribirse el profesor. Cursada != null.
     * @throws EntidadNoAptaParaCursadaException Excepci�n lanzada en el caso que el profesor est� ense�ando en
     * otra cursada y los horarios se superpongan.
     */
    public void verificaProfesorOcupado(Profesor profesor, Cursada cursada) throws EntidadNoAptaParaCursadaException
        // RF20
    {        
        Iterator<HashMap<String, Cursada>> itH;
        Iterator<Cursada> itC;
        Cursada c;
        boolean sigue = true;
        
        itH = this.cursadas.values().iterator();
        while (itH.hasNext() && sigue)
        {
            itC = itH.next().values().iterator();
            while(itC.hasNext() && sigue)
            {
                c = itC.next();
                if(c.getProfesores().containsKey(profesor.getLegajo()))
                    sigue = !this.horariosSolapan(cursada, c);
            }
        }
            
        if(!sigue)
            throw new EntidadNoAptaParaCursadaException(profesor, "El profesor no cumple con las franjas horarias para " +
                "inscribirse a la cursada");
    }
}
