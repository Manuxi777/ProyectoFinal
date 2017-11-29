package testsCajaNegra;

import exceptions.NoEstaEntidadException;

import java.util.HashMap;
import java.util.Iterator;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

import pga.Cursada;

public class BajaCursadaTest
{
    private TestFixtureCursada fixture = new TestFixtureCursada();

    public BajaCursadaTest()
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
     * @see pga.Manager#bajaCursada(pga.Cursada)
     */
    @Test
    public void testBajaCursadaExitosa()
    {
        try
        {
            this.fixture.manager.bajaCursada(this.fixture.cursada_a);
            assertFalse("La cursada no fue eliminada.", this.buscaCursada(this.fixture.cursada_a));
        } 
        catch (NoEstaEntidadException e)
        {
            fail("No tendr�a que haber salido la excepci�n NoEstaEntidadException.");
        }
    }
    
    /**
     * @see pga.Manager#bajaCursada(pga.Cursada)
     */
    @Test
    public void testBajaCursadaErronea_2()
    {
        try
        {
            this.fixture.manager.bajaCursada(null);
            fail("Tendr�a que haberse lanzado la excepci�n NullPointerException.");
        } 
        catch (NoEstaEntidadException e)
        {
            fail("No tendr�a que haber salido la excepci�n NoEstaEntidadException.");
        } 
        catch(NullPointerException e)
        {
            fail("Se intent� dar de baja una cursada nula.");
        }
    }
    
    /**
     * @see pga.Manager#bajaCursada(pga.Cursada)
     */
    @Test
    public void testBajaCursadaErronea_4()
    {
        try
        {
            this.fixture.manager.bajaCursada(this.fixture.cursada_b);
            fail("Tendr�a que haber salido por la excepci�n NoEstaEntidadException");
        } 
        catch (NoEstaEntidadException e)
        {
            assertEquals("Las excepciones no coinciden", "Cursada no encontrada en el sistema.", e.getMessage());
        } 
    }

    private boolean buscaCursada(Cursada cursada)
    {
        Iterator<HashMap<String, Cursada>> it = this.fixture.manager.getCursadas().values().iterator();
        boolean sigue = false;
        
        while(it.hasNext() && !sigue)
        {
            sigue = it.next().containsKey(cursada.getId());
        }
        return sigue;
    }
}
