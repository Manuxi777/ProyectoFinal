package testsCajaBlanca;

import exceptions.NoEstaEntidadException;

import org.junit.After;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class ModificaAsignaturaTest
{
    private TestFixtureAsignatura fixture = new TestFixtureAsignatura();

    public ModificaAsignaturaTest()
    {
    }

    @After
    public void tearDown() throws Exception
    {
        fixture.tearDown();
    }

    /**
     * @see pga.Manager#modificaAsignatura(pga.Asignatura,String)
     */
    @Test
    public void testModificaAsignaturaCamino1() throws Exception
    {
        try
        {
            this.fixture.setUpModificaAsignaturaCamino1();
            this.fixture
                .manager
                .modificaAsignatura(this.fixture.asignatura_a, "Programaci�n 3");
            assertTrue("La asignatura no se modific� correctamente en el sistema", this.fixture.asignatura_a.getNombre().equals("Programaci�n 3"));
        }
        catch (NoEstaEntidadException e)
        {
            fail("No deber�a lanzarse la excepci�n porque en el TestFixtureAsignatura se la agreg�.");
        }
    }
    
    /**
     * @see pga.Manager#modificaAsignatura(pga.Asignatura,String)
     */
    @Test
    public void testModificaAsignaturaCamino2() throws Exception
    {
        try
        {
            this.fixture.setUpModificaAsignaturaCamino2();
            this.fixture
                .manager
                .modificaAsignatura(this.fixture.asignatura_a, "Programaci�n 2");
            fail("Tendr�a que haberse lanzado la excepci�n NoEstaEntidadException.");
        }
        catch (NoEstaEntidadException e)
        {
            assertTrue("El mensaje de la excepci�n no coincide con el previsto.", e.getMessage().equals("Asignatura no encontrada en el sistema."));
        }
    }
    
    /**
     * @see pga.Manager#modificaAsignatura(pga.Asignatura,String)
     */
    @Test
    public void testModificaAsignaturaCamino3() throws Exception
    {
        try
        {
            this.fixture.setUpModificaAsignaturaCamino3();
            this.fixture
                .manager
                .modificaAsignatura(this.fixture.asignatura_b, "Programaci�n 2");
            fail("Tendr�a que haberse lanzado la excepci�n NoEstaEntidadException.");
        }
        catch (NoEstaEntidadException e)
        {
            assertTrue("El mensaje de la excepci�n no coincide con el previsto.", e.getMessage().equals("Asignatura no encontrada en el sistema."));
        }
    }
    
    /**
     * @see pga.Manager#modificaAsignatura(pga.Asignatura,String)
     */
    @Test
    public void testModificaAsignaturaCamino4() throws Exception
    {
        try
        {
            this.fixture.setUpModificaAsignaturaCamino4();
            this.fixture
                .manager
                .modificaAsignatura(this.fixture.asignatura_a, "Programaci�n 2");
            assertTrue("La asignatura no se modific� correctamente en el sistema", this.fixture.asignatura_a.getNombre().equals("Programaci�n 2"));
        }
        catch (NoEstaEntidadException e)
        {
            fail("No deber�a lanzarse la excepci�n porque en el TestFixtureAsignatura se la agreg�.");
        }
    }
}
