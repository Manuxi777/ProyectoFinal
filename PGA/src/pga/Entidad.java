package pga;


/**
 * Interfaz que sirve como generalizaci�n de las cuatro clases principales de la aplicaci�n:
 * Alumno, Asignatura, Cursada y Profesor. Todas ellas hacen uso de esta interfaz para poder
 * ser nucleadas bajo un mismo tipo y poseer un m�todo en com�n.
 * Todas las excepciones cuentan con una entidad como atributo a la cual se le puede pedir
 * su clave.
 */
public interface Entidad
{
    public String getClave();
}
