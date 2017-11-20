package pga;

import gui.VentanaPrincipal;

import java.io.FileNotFoundException;

/**
 * Clase lanzadora de la aplicaci�n. Desde aqu� se deserializa el archivo y se crean las clases que representar�n
 * al modelo, la interfaz gr�fica y el controlador o nexo entre ellas.
 */
public class Principal
{
    public Principal()
    {
        super();
    }

    /**
     * El el m�todo main se crean las instancias de Manager, VentanaPrincipal y Controlador definiendo
     * quien conoce a quien, para luego delegar a estos objetos la posterior evoluci�n de la ejecuci�n.
     * 
     * @param args No se recibe ning�n argumento como par�metro.
     */
    public static void main(String[] args)
    {
        Manager manager;
        try
        {
            manager = PersistenciaXML.leerArchivo();
            VentanaPrincipal v = new VentanaPrincipal();
            Controlador c = new Controlador(manager, v);
            v.setControlador(c);
        } catch (FileNotFoundException e)
        {
            System.out.println("Error al intentar leer el archivo.");
        }
    }
}
