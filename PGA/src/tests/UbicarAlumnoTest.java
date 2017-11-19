package tests;

import exceptions.NoEstaEntidadException;

import java.util.HashMap;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

import pga.Alumno;

public class UbicarAlumnoTest
{
    private TestFixtureAlumno fixture = new TestFixtureAlumno();

    public UbicarAlumnoTest()
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
     * @see pga.Manager#ubicarAlumno(String,String)
     */
    @Test
    public void testUbicarAlumnoExitoso()
    {
        try
        {
            HashMap<String, Alumno> hash = this.fixture
                                               .manager
                                               .ubicarAlumno(this.fixture.alumno_b.getNombre(), this.fixture.alumno_b.getApellido());
            assertTrue("El alumno deber�a haber sido encontrado en el sistema.", hash.containsKey(this.fixture.alumno_b.getLegajo()));
            
        }
        catch (NoEstaEntidadException e)
        {
            fail("No deber�a lanzarse la excepci�n NoEstaEntidadException");
        }
    }
    
    /**
     * @see pga.Manager#ubicarAlumno(String,String)
     */
    @Test
    public void testUbicarAlumnoErroneo2_1()
    {
        try
        {
            this.fixture.manager.ubicarAlumno(null, this.fixture.alumno_b.getApellido());
            fail("Tendr�a que haberse lanzado la excepci�n NullPointerException.");
        } 
        catch (NoEstaEntidadException e)
        {
            fail("No deber�a lanzarse la excepci�n NoEstaEntidadException");
        }
        catch(NullPointerException e)
        {
            fail("Se intent� buscar un alumno con un nombre nulo.");
        }
    }
    
    /**
     * @see pga.Manager#ubicarAlumno(String,String)
     */
    @Test
    public void testUbicarAlumnoErroneo2_2()
    {
        try
        {
            this.fixture.manager.ubicarAlumno(this.fixture.alumno_b.getNombre(), null);
            fail("Tendr�a que haberse lanzado la excepci�n NullPointerException.");
        } 
        catch (NoEstaEntidadException e)
        {
            fail("No deber�a lanzarse la excepci�n NoEstaEntidadException");
        }
        catch(NullPointerException e)
        {
            fail("Se intent� buscar un alumno con un apellido nulo.");
        }
    }
    
    /**
     * @see pga.Manager#ubicarAlumno(String,String)
     */
    @Test
    public void testUbicarAlumnoErroneo4()
    {
        try
        {
            this.fixture.manager.ubicarAlumno(this.fixture.alumno_a.getApellido(), this.fixture.alumno_a.getNombre());
            fail("Tendr�a que haberse lanzado la excepci�n NoEstaEntidadException.");
        } 
        catch (NoEstaEntidadException e)
        {
            assertEquals("El mensaje de la excepci�n no coincide con el previsto.", e.getMessage(), "Ning�n alumno encontrado.");
        }
    }
}
