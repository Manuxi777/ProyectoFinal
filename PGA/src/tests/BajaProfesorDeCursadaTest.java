package tests;

import exceptions.NoEstaEntidadException;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class BajaProfesorDeCursadaTest
{
    private TestFixtureProfesor fixture = new TestFixtureProfesor();

    public BajaProfesorDeCursadaTest()
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
     * @see pga.Manager#bajaProfesorDeCursada(pga.Profesor,pga.Cursada)
     */
    @Test
    public void testBajaProfesorDeCursadaExitoso()
    {
        try
        {
            this.fixture
                .manager
                .bajaProfesorDeCursada(this.fixture.profesor_a, this.fixture.cursada_a);
            assertTrue("No se elimin� correctamente al profesor de la cursada.", 
                       !this.fixture.cursada_a.getProfesores().containsKey(this.fixture.profesor_a.getLegajo()));
        }
        catch (NoEstaEntidadException e)
        {
            fail("No tendr�a que haberse lanzado la excepci�n NoEstaEntidadException ya que en el TestFixtureProfesor se agreg� al profesor.");
        }
    }
    
    /**
     * @see pga.Manager#bajaProfesorDeCursada(pga.Profesor,pga.Cursada)
     */
    @Test
    public void testBajaProfesorDeCursadaErroneo_2_1()
    {
        try
        {
            this.fixture
                .manager
                .bajaProfesorDeCursada(this.fixture.profesor_a, null);
            fail("Tendr�a que haberse lanzado la excepci�n NullPointerException.");
        }
        catch (NoEstaEntidadException e)
        {
            fail("No tendr�a que haberse lanzado la excepci�n NoEstaEntidadException.");
        }
        catch(NullPointerException e)
        {
            fail("Se intent� dar de baja un profesor de una cursada nula.");
        }
    }
    
    /**
     * @see pga.Manager#bajaProfesorDeCursada(pga.Profesor,pga.Cursada)
     */
    @Test
    public void testBajaProfesorDeCursadaErroneo_2_2()
    {
        try
        {
            this.fixture
                .manager
                .bajaProfesorDeCursada(null, this.fixture.cursada_a);
            fail("Tendr�a que haberse lanzado la excepci�n NullPointerException.");
        }
        catch (NoEstaEntidadException e)
        {
            fail("No tendr�a que haberse lanzado la excepci�n NoEstaEntidadException.");
        }
        catch(NullPointerException e)
        {
            fail("Se intent� dar de baja de una cursada un profesor nulo.");
        }
    }
    
    /**
     * @see pga.Manager#bajaProfesorDeCursada(pga.Profesor,pga.Cursada)
     */
    @Test
    public void testBajaProfesorDeCursadaErroneo_4()
    {
        try
        {
            this.fixture
                .manager
                .bajaProfesorDeCursada(this.fixture.profesor_b, this.fixture.cursada_a);
            fail("Tendr�a que haberse lanzado la excepci�n NoEstaEntidadException.");
        }
        catch (NoEstaEntidadException e)
        {
            assertEquals("El mensaje de la excepci�n no coincide con el previsto.", 
                         e.getMessage(), "Profesor no encontrado en la cursada.");
        }
    }
}
