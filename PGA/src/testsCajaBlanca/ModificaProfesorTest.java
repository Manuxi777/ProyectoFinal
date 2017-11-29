package testsCajaBlanca;

import exceptions.EmailInvalidoException;
import exceptions.NoEstaEntidadException;

import org.junit.After;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class ModificaProfesorTest
{
    private TestFixtureProfesor fixture = new TestFixtureProfesor();

    public ModificaProfesorTest()
    {
    }

    @After
    public void tearDown() throws Exception
    {
        fixture.tearDown();
    }

    /**
     * @see pga.Manager#modificaProfesor(pga.Profesor,String,String,String,String,String)
     */
    @Test
    public void testModificaProfesorCamino2() throws Exception
    {
        try
        {
            this.fixture.setUpModificaProfesorCamino2();
            this.fixture
                .manager
                .modificaProfesor(this.fixture.profesor_a, "Manuel", "Nucci", "Ju 345", "16789", "nu@gmail.com");
            assertTrue("El profesor no se modific� correctamente en el sistema.", 
                       this.fixture.profesor_a.getNombre().equals("Manuel") &&
                       this.fixture.profesor_a.getApellido().equals("Nucci") &&
                       this.fixture.profesor_a.getDomicilio().equals("Ju 345") &&
                       this.fixture.profesor_a.getTelefono().equals("16789") &&
                       this.fixture.profesor_a.getMail().equals("nu@gmail.com"));
            
        }
        catch (EmailInvalidoException e)
        {
            fail("No deber�a lanzarse la excepci�n por el formato del email incorrecto.");
        }
        catch (NoEstaEntidadException e)
        {
            fail("No deber�a lanzarse la excepci�n porque en el TestFixtureProfesor se lo agreg�.");
        }
    }
    
    /**
     * @see pga.Manager#modificaProfesor(pga.Profesor,String,String,String,String,String)
     */
    @Test
    public void testModificaProfesorCamino5() throws Exception
    {
        try
        {
            this.fixture.setUpModificaProfesorCamino5();
            this.fixture
                .manager
                .modificaProfesor(this.fixture.profesor_b, "Manuel", "nucci", "Jujuy 345", "123456789", "nucci@gmail.com");
            fail("Tendr�a que haberse lanzado la excepci�n NoEstaEntidadException.");            
        }
        catch (EmailInvalidoException e)
        {
            fail("No deber�a lanzarse la excepci�n por el formato del email incorrecto.");
        }
        catch (NoEstaEntidadException e)
        {
            assertTrue("El mensaje de la excepci�n no coincide con el previsto.", e.getMessage().equals("Profesor no encontrado en el sistema."));
        }
    }
    
    /**
     * @see pga.Manager#modificaProfesor(pga.Profesor,String,String,String,String,String)
     */
    @Test
    public void testModificaProfesorCamino6() throws Exception
    {
        try
        {
            this.fixture.setUpModificaProfesorCamino6();
            this.fixture
                .manager
                .modificaProfesor(this.fixture.profesor_a, "Manuelita", "nucci", "Jujuy 345", "123456789", "nucci@gmail.com");
            assertTrue("El profesor no se modific� correctamente en el sistema.", 
                       this.fixture.profesor_a.getNombre().equals("Manuelita") &&
                       this.fixture.profesor_a.getApellido().equals("nucci") &&
                       this.fixture.profesor_a.getDomicilio().equals("Jujuy 345") &&
                       this.fixture.profesor_a.getTelefono().equals("123456789") &&
                       this.fixture.profesor_a.getMail().equals("nucci@gmail.com"));         
        }
        catch (EmailInvalidoException e)
        {
            fail("No deber�a lanzarse la excepci�n por el formato del email incorrecto.");
        }
        catch (NoEstaEntidadException e)
        {
            fail("No deber�a lanzarse la excepci�n porque en el TestFixtureProfesor se lo agreg�.");
        }
    }
}
