package pga;

import exceptions.EmailInvalidoException;
import exceptions.EntidadNoAptaParaCursadaException;
import exceptions.HoraInvalidaException;
import exceptions.NoEstaEntidadException;

import exceptions.PeriodoInvalidoException;

import gui.VentanaPrincipal;

import java.util.HashMap;

/**
 * Clase que sirve como intermediaria entre el modelo y la interfaz gr�fica.
 * Posee m�todos que funcionan como mensajeros invocando m�todos del modelo y, si fuese necesario,
 * devuelven los resultados a la GUI para que los utilice luego.
 */
public class Controlador
{
    private Manager modelo; // Modelo del sistema con todos sus m�todos.
    private VentanaPrincipal ventana; // Referencia a la ventana principal de la interfaz gr�fica.
    
    /**
     * Constructor que crea una nueva instancia del Controlador.<br>
     * 
     * <b>Pre:</b> Los par�metros deben ser v�lidos.<br>
     * <b>Post:</b> Se crea una nueva instancia del controlador.
     * 
     * @param modelo Es el modelo o sistema. Modelo != null.
     * @param ventana Es la interfaz de la aplicaci�n. Ventana != null
     */
    public Controlador(Manager modelo, VentanaPrincipal ventana)
    {
        super();
        this.modelo = modelo;
        this.ventana = ventana;
    }
    
    public void altaAlumno(String nombre, String apellido, String domicilio, String telefono, String mail) throws EmailInvalidoException
    {
        this.modelo.altaAlumno(nombre, apellido, domicilio, telefono, mail);
    }
    
    public void bajaAlumno(Alumno alumno) throws NoEstaEntidadException
    {
        this.modelo.bajaAlumno(alumno);
    }
    
    public void modificaAlumno(Alumno alumno, String nombre, String apellido, String domicilio, String telefono, 
                               String mail) throws NoEstaEntidadException, EmailInvalidoException
    {
        this.modelo.modificaAlumno(alumno, nombre, apellido, domicilio, telefono, mail);
    }
    
    public HashMap<String, Alumno> ubicarAlumno(String nombre, String apellido) throws NoEstaEntidadException
    {
        return this.modelo.ubicarAlumno(nombre, apellido);
    }
    
    public void altaAlumnoACursada(Alumno alumno, Cursada cursada) throws EntidadNoAptaParaCursadaException
    {
        this.modelo.altaAlumnoACursada(alumno, cursada);
    }
    
    public void bajaAlumnoDeCursada(Alumno alumno, Cursada cursada) throws NoEstaEntidadException
    {
        this.modelo.bajaAlumnoDeCursada(alumno, cursada);
    }
    
    public void aprobarAsignatura(Alumno alumno, Asignatura asignatura) throws exceptions.EntidadRepetidaException
    {
        this.modelo.aprobarAsignatura(alumno, asignatura);
    }
    
    public void quitarAsignatura(Alumno alumno, Asignatura asignatura) throws NoEstaEntidadException
    {
        this.modelo.quitarAsignatura(alumno, asignatura);
    }
    
    public HashMap<String, Asignatura> ubicarAsignatura(String nombre) throws NoEstaEntidadException
    {
        return this.modelo.ubicarAsignatura(nombre);
    }
    
    public void altaCursada(String nombre, Asignatura asignatura, String periodo, String dia, String horaInicio, 
                            String horaFin) throws PeriodoInvalidoException, HoraInvalidaException
    {
        this.modelo.altaCursada(nombre, asignatura, periodo, dia, horaInicio, horaFin);
    }
    
    public void bajaCursada(Cursada cursada) throws NoEstaEntidadException
    {
        this.modelo.bajaCursada(cursada);
    }
    
    public void modificaCursada(Cursada cursada, String nombre, Asignatura asignatura, String periodo, String dia, 
                                String horaInicio, String horaFin) throws NoEstaEntidadException, PeriodoInvalidoException, 
                                HoraInvalidaException
    {
        this.modelo.modificaCursada(cursada, nombre, asignatura, periodo, dia, horaInicio, horaFin);
    }
    
    public HashMap<String, Cursada> ubicarCursada(String nombre) throws NoEstaEntidadException
    {
        return this.modelo.ubicarCursada(nombre);
    }
    
    public void guardarArchivo()
    {
        PersistenciaXML.guardarArchivo(this.modelo);
    }
}
