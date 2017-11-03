package pga;

import exceptions.EmailInvalidoException;
import exceptions.HoraInvalidaException;
import exceptions.PeriodoInvalidoException;

/**
 * Clase que se encarga de validar que ciertos atributos de las clases utilizadas en la aplicaci�n cuenten
 * con el formato correcto explicitado en la SRS.
 * Se declara a la clase como final para evitar su extensi�n.
 */
public final class Formato
{
    public static final int TAMANO_PERIODO = 7; // Longitud que debe poseer el per�odo si respeta el formato
    public static final int TAMANO_HORA = 7; // Longitud que deben poseer las horas de inicio y fin si respetan el formato
    
    /**
     * Constructor de la clase. Se lo declara privado para evitar la instanciaci�n de la clase Formato,
     * la cual es una mera verificadora de formatos en las cadenas.
     */
    private Formato()
    {
        super();
    }
    
    /**
     * M�todo que verifica si el mail ingresado cumple con el formato establecido.<br>
     * 
     * <b>Pre:</b> El mail que viene por par�metro no es nulo.<br>
     * <b>Post:</b> El m�todo finaliza su ejecuci�n si el mail cumple con el formato. Si no lo hace se lanza
     * una excepci�n notificando esta situaci�n.
     * 
     * @param mail Mail del alumno o profesor. Mail != null.
     * @throws EmailInvalidoException Excepci�n lanzada si el mail no cumple con el formato.
     */
    public static void verificaMail(String mail) throws EmailInvalidoException
    {
        int indiceArroba;
        
        indiceArroba = mail.indexOf("@");
        if (!(indiceArroba != -1 && indiceArroba == mail.lastIndexOf("@") && indiceArroba != 0 && indiceArroba != mail.length() - 1))
            throw new EmailInvalidoException(mail, "El mail ingresado no cumple con el formato previsto.");
    }
    
    /**
     * M�todo que verifica si el per�odo ingresado cumple con el formato establecido.<br>
     * 
     * <b>Pre:</b> El per�odo que viene por par�metro no es nulo.<br>
     * <b>Post:</b> El m�todo finaliza su ejecuci�n si el per�odo cumple con el formato. Si no lo hace se lanza
     * una excepci�n notificando esta situaci�n.
     * 
     * @param periodo Per�odo de la cursada. Periodo != null.
     * @throws PeriodoInvalidoException Exceci�n lanzada si el per�odo infringe el formato estipulado.
     */
    public static void verificaCursadaPeriodo(String periodo) throws PeriodoInvalidoException
    {
        boolean ret = false;
        String cad;
        
        if(periodo.length() == TAMANO_PERIODO && periodo.indexOf("-") == 2)
        {
            cad = periodo.substring(0,1);
            if(cad.equals("01") || cad.equals("02"))
            {
                cad = periodo.substring(3, 6);
                ret = cad.compareTo("2000") >= 0 && cad.compareTo("2100") <= 0;
            }
        }
        if (!ret)
            throw new PeriodoInvalidoException(periodo, "El periodo ingresado no cumple con el formato previsto.");
    }
    
    /**
     * M�todo que verifica si la hora ingresada cumple con el formato establecido.<br>
     * 
     * <b>Pre:</b> La hora que viene por par�metro no es nula.<br>
     * <b>Post:</b> El m�todo finaliza su ejecuci�n si la hora cumple con el formato. Si no lo hace se lanza
     * una excepci�n notificando esta situaci�n.
     * 
     * @param hora Hora (inicio o fin) de la cursada. Hora != null.
     * @throws HoraInvalidaException Excepci�n lanzada si la hora no cumple con el formato.
     */
    public static void verificaCursadaHora(String hora) throws HoraInvalidaException
    {
        boolean ret = false;
        String cad;
        
        if(hora.length() == TAMANO_HORA && hora.indexOf(":") == 2)
        {
            cad = hora.substring(0,1);
            if(cad.compareTo("00") >= 0 && cad.compareTo("23") <= 0)
            {
                cad = hora.substring(3, 4);
                ret = cad.compareTo("00") >= 0 && cad.compareTo("59") <= 0;
            }
        }
        if (!ret)
            throw new HoraInvalidaException(hora, "La hora ingresada no cumple con el formato previsto.");
    }
}
