package testsCajaNegra;

import exceptions.NoEstaEntidadException;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class BajaAlumnoDeCursadaTest
{
    private TestFixtureAlumno fixture = new TestFixtureAlumno();

    public BajaAlumnoDeCursadaTest()
    {
    }

    @Before
    public void setUp() throws Exception
    {
        fixture.setUpBajaDeCursada();
    }

    @After
    public void tearDown() throws Exception
    {
        fixture.tearDown();
    }

    /**
     * @see pga.Manager#bajaAlumnoDeCursada(pga.Alumno,pga.Cursada)
     */
    @Test
    public void testBajaAlumnoDeCursadaExitoso()
    {
        try
        {
            this.fixture
                .manager
                .bajaAlumnoDeCursada(this.fixture.alumno_a, this.fixture.cursada_a);
            assertTrue("No se elimin� correctamente al alumno de la cursada.", 
                       !this.fixture.cursada_a.getAlumnos().containsKey(this.fixture.alumno_a.getLegajo()));
        }
        catch (NoEstaEntidadException e)
        {
            fail("No tendr�a que haberse lanzado la excepci�n NoEstaEntidadException ya que en el TestFixtureAlumno se agreg� al alumno.");
        }
    }
    
    /**
     * @see pga.Manager#bajaAlumnoDeCursada(pga.Alumno,pga.Cursada)
     */
    @Test
    public void testBajaAlumnoDeCursadaErroneo_2_1()
    {
        try
        {
            this.fixture
                .manager
                .bajaAlumnoDeCursada(this.fixture.alumno_a, null);
            fail("Tendr�a que haberse lanzado la excepci�n NullPointerException.");
        }
        catch (NoEstaEntidadException e)
        {
            fail("No tendr�a que haberse lanzado la excepci�n NoEstaEntidadException.");
        }
        catch(NullPointerException e)
        {
            fail("Se intent� dar de baja un alumno de una cursada nula.");
        }
    }
    
    /**
     * @see pga.Manager#bajaAlumnoDeCursada(pga.Alumno,pga.Cursada)
     */
    @Test
    public void testBajaAlumnoDeCursadaErroneo_2_2()
    {
        try
        {
            this.fixture
                .manager
                .bajaAlumnoDeCursada(null, this.fixture.cursada_a);
            fail("Tendr�a que haberse lanzado la excepci�n NullPointerException.");
        }
        catch (NoEstaEntidadException e)
        {
            fail("No tendr�a que haberse lanzado la excepci�n NoEstaEntidadException.");
        }
        catch(NullPointerException e)
        {
            fail("Se intent� dar de baja de una cursada un alumno nulo.");
        }
    }
    
    /**
     * @see pga.Manager#bajaAlumnoDeCursada(pga.Alumno,pga.Cursada)
     */
    @Test
    public void testBajaAlumnoDeCursadaErroneo_4()
    {
        try
        {
            this.fixture
                .manager
                .bajaAlumnoDeCursada(this.fixture.alumno_b, this.fixture.cursada_a);
            fail("Tendr�a que haberse lanzado la excepci�n NoEstaEntidadException.");
        }
        catch (NoEstaEntidadException e)
        {
            assertEquals("El mensaje de la excepci�n no coincide con el previsto.", 
                         e.getMessage(), "Alumno no encontrado en la cursada.");
        }
    }
}
