package tests;

import exceptions.NoEstaEntidadException;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class BajaAlumnoTest
{
    private TestFixtureAlumno fixture = new TestFixtureAlumno();

    public BajaAlumnoTest()
    {
    }

    @Before
    public void setUp() throws Exception
    {
        fixture.setUpBaja();
    }

    @After
    public void tearDown() throws Exception
    {
        fixture.tearDown();
    }

    /**
     * @see pga.Manager#bajaAlumno(pga.Alumno)
     */
    @Test
    public void testBajaAlumnoExitoso()
    {
        try
        {
            this.fixture
                .manager
                .bajaAlumno(this.fixture.alumno_a);
        } 
        catch (NoEstaEntidadException e)
        {
            fail("No deber�a lanzarse la excepci�n NoEstaEntidadException");
        }
    }
    
    /**
     * @see pga.Manager#bajaAlumno(pga.Alumno)
     */
    @Test
    public void testBajaAlumnoErroneo2()
    {
        try
        {
            this.fixture
                .manager
                .bajaAlumno(null);
        } 
        catch (NoEstaEntidadException e)
        {
            fail("No deber�a lanzarse la excepci�n NoEstaEntidadException");
        }
        catch(NullPointerException e)
        {
            fail("Se intent� borrar un alumno nulo.");
        }
    }
    
    /**
     * @see pga.Manager#bajaAlumno(pga.Alumno)
     */
    @Test
    public void testBajaAlumnoErroneo4()
    {
        try
        {
            this.fixture
                .manager
                .bajaAlumno(this.fixture.alumno_b);
            fail("El m�todo deber�a haber lanzado la excepci�n NoEstaEntidadException.");
        } 
        catch (NoEstaEntidadException e)
        {
            assertEquals("El mensaje de la excepci�n no coincide con el previsto.", e.getMessage(), "Alumno no encontrado en el sistema.");
        }
    }
    
    /**
     * @see pga.Manager#bajaAlumno(pga.Alumno)
     */
    @Test
    public void testBajaAlumnoErroneo6()
    {
        try
        {
            this.fixture
                .manager
                .bajaAlumno(this.fixture.alumno_c);
            assertTrue("La cursada a�n contiene al alumno a eliminar.", this.fixture.
                                                                             cursada.
                                                                             getAlumnos().
                                                                             containsKey(this.fixture.alumno_c.getLegajo()));
        } 
        catch (NoEstaEntidadException e)
        {
            fail("No deber�a lanzarse la excepci�n porque en el TextFicture se lo agreg�.");
        }
    }
    
}
