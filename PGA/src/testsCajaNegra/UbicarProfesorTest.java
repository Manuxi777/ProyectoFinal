package testsCajaNegra;

import exceptions.NoEstaEntidadException;

import java.util.HashMap;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

import pga.Profesor;

public class UbicarProfesorTest
{
    private TestFixtureProfesor fixture = new TestFixtureProfesor();

    public UbicarProfesorTest()
    {
    }

    @Before
    public void setUp() throws Exception
    {
        fixture.setUpUbicar();
    }

    @After
    public void tearDown() throws Exception
    {
        fixture.tearDown();
    }

    /**
     * @see pga.Manager#ubicarProfesor(String,String)
     */
    @Test
    public void testUbicarProfesorExitoso()
    {
        try
        {
            HashMap<String, Profesor> hash = this.fixture.manager.ubicarProfesor(
                                                this.fixture.profesor_b.getNombre(), this.fixture.profesor_b.getApellido());
            assertTrue("El profesor deber�a haber sido encontrado en el sistema.", hash.containsKey(this.fixture.profesor_b.getLegajo()));
            
        }
        catch (NoEstaEntidadException e)
        {
            fail("No deber�a lanzarse la excepci�n NoEstaEntidadException");
        }
    }
    

    /**
     * @see pga.Manager#ubicarProfesor(String,String)
     */
    @Test
    public void testUbicarProfesorErroneo2_1()
    {
        try
        {
            this.fixture.manager.ubicarProfesor(null, this.fixture.profesor_b.getApellido());
            fail("Tendrpia que haberse lanzado la excepci�n NoEstaEntidadException.");
        } 
        catch (NoEstaEntidadException e)
        {
            fail("Se intent� buscar un profesor con un nombre nulo.");
        }
    }
    
    /**
     * @see pga.Manager#ubicarProfesor(String,String)
     */
    @Test
    public void testUbicarProfesorErroneo2_2()
    {
        try
        {
            this.fixture.manager.ubicarProfesor(this.fixture.profesor_b.getNombre(), null);
            fail("Tendrpia que haberse lanzado la excepci�n NoEstaEntidadException.");
        } 
        catch (NoEstaEntidadException e)
        {
            fail("Se intent� buscar un profesor con un apellido nulo.");
        }
    }
    
    /**
     * @see pga.Manager#ubicarProfesor(String,String)
     */
    @Test
    public void testUbicarProfesorErroneo4()
    {
        try
        {
            this.fixture.manager.ubicarProfesor(this.fixture.profesor_a.getApellido(), this.fixture.profesor_a.getNombre());
            fail("Tendr�a que haberse lanzado la excepci�n NoEstaEntidadException.");
        } 
        catch (NoEstaEntidadException e)
        {
            assertEquals("El mensaje de la excepci�n no coincide con el previsto.", "Ning�n profesor encontrado.", e.getMessage());
        }
    }

}
