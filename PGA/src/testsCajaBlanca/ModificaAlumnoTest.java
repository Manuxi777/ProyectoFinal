package testsCajaBlanca;

import exceptions.EmailInvalidoException;
import exceptions.NoEstaEntidadException;

import org.junit.After;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class ModificaAlumnoTest
{
    private TestFixtureAlumno fixture = new TestFixtureAlumno();

    public ModificaAlumnoTest()
    {
    }
    
    @After
    public void tearDown() throws Exception
    {
        fixture.tearDown();
    }

    /**
     * @see pga.Manager#modificaAlumno(pga.Alumno,String,String,String,String,String)
     */
    @Test
    public void testModificaAlumnoCamino2() throws Exception
    {
        try
        {
            this.fixture.setUpModificaAlumnoCamino2();
            this.fixture.manager.modificaAlumno(this.fixture.alumno_a, "Manuel", "Nucci", "Ju 345", "16789", "nu@gmail.com");
            assertTrue("El alumno no se modific� correctamente en el sistema.", 
                       this.fixture.alumno_a.getNombre().equals("Manuel") &&
                       this.fixture.alumno_a.getApellido().equals("Nucci") &&
                       this.fixture.alumno_a.getDomicilio().equals("Ju 345") &&
                       this.fixture.alumno_a.getTelefono().equals("16789") &&
                       this.fixture.alumno_a.getMail().equals("nu@gmail.com"));
        } 
        catch (EmailInvalidoException e)
        {
            fail("No deber�a lanzarse la excepci�n por el formato del email incorrecto.");
        } 
        catch (NoEstaEntidadException e)
        {
            fail("No deber�a lanzarse la excepci�n porque en el TestFixtureAlumno se lo agreg�.");
        }
    }
    
    /**
     * @see pga.Manager#modificaAlumno(pga.Alumno,String,String,String,String,String)
     */
    @Test
    public void testModificaAlumnoCamino4() throws Exception
    {
        try
        {
            this.fixture.setUpModificaAlumnoCamino4();
            this.fixture.manager.modificaAlumno(this.fixture.alumno_b, "Manuel", "nucci", "Jujuy 345", "123456789", "nucci@gmail.com");
            fail("Tendr�a que haberse lanzado la excepci�n NoEstaEntidadException.");
        } 
        catch (EmailInvalidoException e)
        {
            fail("No deber�a lanzarse la excepci�n por el formato del email incorrecto.");
        } 
        catch (NoEstaEntidadException e)
        {
            assertTrue("El mensaje de la excepci�n no coincide con el previsto.", e.getMessage().equals("Alumno no encontrado en el sistema."));
        }
    }
    
    /**
     * @see pga.Manager#modificaAlumno(pga.Alumno,String,String,String,String,String)
     */
    @Test
    public void testModificaAlumnoCamino5() throws Exception
    {
        try
        {
            this.fixture.setUpModificaAlumnoCamino5();
            this.fixture.manager.modificaAlumno(this.fixture.alumno_b, "Manuel", "nucci", "Jujuy 345", "123456789", "nucci@gmail.com");
            fail("Tendr�a que haberse lanzado la excepci�n NoEstaEntidadException.");
        } 
        catch (EmailInvalidoException e)
        {
            fail("No deber�a lanzarse la excepci�n por el formato del email incorrecto.");
        } 
        catch (NoEstaEntidadException e)
        {
            assertTrue("El mensaje de la excepci�n no coincide con el previsto.", e.getMessage().equals("Alumno no encontrado en el sistema."));
        }
    }
    
    /**
     * @see pga.Manager#modificaAlumno(pga.Alumno,String,String,String,String,String)
     */
    @Test
    public void testModificaAlumnoCamino6() throws Exception
    {
        try
        {
            this.fixture.setUpModificaAlumnoCamino6();
            this.fixture.manager.modificaAlumno(this.fixture.alumno_a, "Manuelita", "nucci", "Jujuy 345", "123456789", "nucci@gmail.com");
            assertTrue("El alumno no se modific� correctamente en el sistema.", 
                       this.fixture.alumno_a.getNombre().equals("Manuelita") &&
                       this.fixture.alumno_a.getApellido().equals("nucci") &&
                       this.fixture.alumno_a.getDomicilio().equals("Jujuy 345") &&
                       this.fixture.alumno_a.getTelefono().equals("123456789") &&
                       this.fixture.alumno_a.getMail().equals("nucci@gmail.com"));
        } 
        catch (EmailInvalidoException e)
        {
            fail("No deber�a lanzarse la excepci�n por el formato del email incorrecto.");
        } 
        catch (NoEstaEntidadException e)
        {
            fail("No deber�a lanzarse la excepci�n porque en el TestFixtureAlumno se lo agreg�.");
        }
    }
}
