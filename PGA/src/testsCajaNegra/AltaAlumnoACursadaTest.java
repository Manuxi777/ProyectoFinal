package testsCajaNegra;

import exceptions.EntidadNoAptaParaCursadaException;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class AltaAlumnoACursadaTest
{
    private TestFixtureAlumno fixture = new TestFixtureAlumno();

    public AltaAlumnoACursadaTest()
    {
    }

    @Before
    public void setUp() throws Exception
    {
        fixture.setUpAgregarACursada();
    }

    @After
    public void tearDown() throws Exception
    {
        fixture.tearDown();
    }

    /**
     * @see pga.Manager#altaAlumnoACursada(pga.Alumno,pga.Cursada)
     */
    @Test
    public void testAltaAlumnoACursadaExitoso()
    {
        try
        {
            this.fixture
                .manager
                .altaAlumnoACursada(this.fixture.alumno_b, this.fixture.cursada_b);
            assertTrue("El alumno no se incribi� correctamente en la cursada.", this.fixture.cursada_b.getAlumnos().containsKey(this.fixture.alumno_b.getLegajo()));
        } 
        catch (EntidadNoAptaParaCursadaException e)
        {
            fail("No tendr�a que haberse lanzado la excepci�n EntidadNoAptaParaCursadaException ya que en el TestFixtureAlumno se arregl� el escenario para que esto no ocurra.");
        }
    }
    
    /**
     * @see pga.Manager#altaAlumnoACursada(pga.Alumno,pga.Cursada)
     */
    @Test
    public void testAltaAlumnoACursadaErroneo_2_1()
    {
        try
        {
            this.fixture
                .manager
                .altaAlumnoACursada(this.fixture.alumno_b, null);
            fail("Tendr�a que haberse lanzado la excepci�n NullPointerException.");
        } 
        catch (EntidadNoAptaParaCursadaException e)
        {
            fail("No tendr�a que haberse lanzado la excepci�n EntidadNoAptaParaCursadaException.");
        }
        catch(NullPointerException e)
        {
            fail("Se intent� agregar un alumno a una cursada nula.");
        }
    }
    
    /**
     * @see pga.Manager#altaAlumnoACursada(pga.Alumno,pga.Cursada)
     */
    @Test
    public void testAltaAlumnoACursadaErroneo_2_2()
    {
        try
        {
            this.fixture
                .manager
                .altaAlumnoACursada(null, this.fixture.cursada_b);
            fail("Tendr�a que haberse lanzado la excepci�n NullPointerException.");
        } 
        catch (EntidadNoAptaParaCursadaException e)
        {
            fail("No tendr�a que haberse lanzado la excepci�n EntidadNoAptaParaCursadaException");
        }
        catch(NullPointerException e)
        {
            fail("Se intent� agregar a una cursada un alumno nulo.");
        }
    }
    
    /**
     * @see pga.Manager#altaAlumnoACursada(pga.Alumno,pga.Cursada)
     */
    @Test
    public void testAltaAlumnoACursadaErroneo_6()
    {
        try
        {
            this.fixture
                .manager
                .altaAlumnoACursada(this.fixture.alumno_a, this.fixture.cursada_b);
            fail("Tendr�a que haberse lanzado la excepci�n EntidadNoAptaParaCursadaException.");
        } 
        catch (EntidadNoAptaParaCursadaException e)
        {
            assertEquals("El mensaje de la excepci�n no coincide con el previsto.", 
                         e.getMessage(), "El alumno no cumple con las correlatividades para inscribirse a la cursada");
        }
    }
    
    /**
     * @see pga.Manager#altaAlumnoACursada(pga.Alumno,pga.Cursada)
     */
    @Test
    public void testAltaAlumnoACursadaErroneo_8()
    {
        try
        {
            this.fixture
                .manager
                .altaAlumnoACursada(this.fixture.alumno_a, this.fixture.cursada_d);
            fail("Tendr�a que haberse lanzado la excepci�n EntidadNoAptaParaCursadaException.");
        } 
        catch (EntidadNoAptaParaCursadaException e)
        {
            assertEquals("El mensaje de la excepci�n no coincide con el previsto.", 
                         e.getMessage(), "El alumno no cumple con las franjas horarias para inscribirse a la cursada");
        }
    }
}
