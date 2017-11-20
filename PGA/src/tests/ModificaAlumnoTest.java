package tests;

import exceptions.EmailInvalidoException;
import exceptions.NoEstaEntidadException;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

import pga.Alumno;

public class ModificaAlumnoTest
{
    private TestFixtureAlumno fixture = new TestFixtureAlumno();

    public ModificaAlumnoTest()
    {
    }

    @Before
    public void setUp() throws Exception
    {
        fixture.setUpAgregarYModif();
    }

    @After
    public void tearDown() throws Exception
    {
        fixture.tearDown();
    }

    /**
     * @see pga.Manager#modificaAlumno(Alumno,String,String,String,String,String)
     */
    @Test
    public void testModificaAlumnoExitoso_1_1()
    {   
        try
        {
            this.fixture.manager.modificaAlumno(this.fixture.alumno_a, "Martin", "Perez", "Catamarca 2050", "2235235475", "mprez@live.com");            
            assertTrue("El alumno no se modific� correctamente en el sistema", 
                       this.fixture.alumno_a.getNombre().equals("Martin") &&
                       this.fixture.alumno_a.getApellido().equals("Perez") &&
                       this.fixture.alumno_a.getDomicilio().equals("Catamarca 2050") &&
                       this.fixture.alumno_a.getTelefono().equals("2235235475") &&
                       this.fixture.alumno_a.getMail().equals("mprez@live.com"));
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
     * @see pga.Manager#modificaAlumno(Alumno,String,String,String,String,String)
     */
    @Test
    public void testModificaAlumnoExitoso_1_2()
    {   
        try
        {
            this.fixture.manager.modificaAlumno(this.fixture.alumno_a, "M", "P", "D", "1", "a@j");
            assertTrue("El alumno no se modific� correctamente en el sistema", 
                       this.fixture.alumno_a.getNombre().equals("M") &&
                       this.fixture.alumno_a.getApellido().equals("P") &&
                       this.fixture.alumno_a.getDomicilio().equals("D") &&
                       this.fixture.alumno_a.getTelefono().equals("1") &&
                       this.fixture.alumno_a.getMail().equals("a@j"));
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
     * @see pga.Manager#modificaAlumno(Alumno,String,String,String,String,String)
     */
    @Test
    public void testModificaAlumnoErroneo_2_2()
    {   
        try
        {
            this.fixture.manager.modificaAlumno(this.fixture.alumno_a, null, "Quito", "Alvear 123", "4756545", "mimail@gmail.com");
            fail("Tendr�a que haberse lanzado la excepci�n NullPointerException.");
        } 
        catch (EmailInvalidoException e)
        {
            fail("No deber�a lanzarse la excepci�n por el formato del email incorrecto.");
        }
        catch (NullPointerException e)
        {
            fail("Se intent� modificar un alumno d�ndole un nombre nulo.");
        } 
        catch (NoEstaEntidadException e)
        {
            fail("No deber�a lanzarse la excepci�n porque en el TestFixtureAlumno se lo agreg�.");
        }
    }
    
    /**
     * @see pga.Manager#modificaAlumno(Alumno,String,String,String,String,String)
     */
    @Test
    public void testModificaAlumnoErroneo_2_3()
    {   
        try
        {
            this.fixture.manager.modificaAlumno(this.fixture.alumno_a, "Adolfo", null, "Alvear 123", "4756545", "mimail@gmail.com");
            fail("Puede o no lanzarse la excepci�n NullPointerException si llegase a venir un apellido nulo.");
        } 
        catch (EmailInvalidoException e)
        {
            fail("No deber�a lanzarse la excepci�n por el formato del email incorrecto.");
        }
        catch (NullPointerException e)
        {
            fail("Se intent� modificar un alumno d�ndole un apellido nulo.");
        } 
        catch (NoEstaEntidadException e)
        {
            fail("No deber�a lanzarse la excepci�n porque en el TestFixtureAlumno se lo agreg�.");
        }
    }
    
    /**
     * @see pga.Manager#modificaAlumno(Alumno,String,String,String,String,String)
     */
    @Test
    public void testModificaAlumnoErroneo_2_4()
    {   
        try
        {
            this.fixture.manager.modificaAlumno(this.fixture.alumno_a, "Leonel", "Quito", null, "4756545", "mimail@gmail.com");
            fail("Se intent� modificar un alumno d�ndole domicilio nulo.");
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
     * @see pga.Manager#modificaAlumno(Alumno,String,String,String,String,String)
     */
    @Test
    public void testModificaAlumnoErroneo_2_5()
    {   
        try
        {
            this.fixture.manager.modificaAlumno(this.fixture.alumno_a, "Aquiles", "Bailo", "Alvear 123", null, "mimail@gmail.com");
            fail("Se intent� modificar un alumno d�ndole un tel�fono nulo.");
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
     * @see pga.Manager#modificaAlumno(Alumno,String,String,String,String,String)
     */
    @Test
    public void testModificaAlumnoErroneo_2_6()
    {   
        try
        {
            this.fixture.manager.modificaAlumno(this.fixture.alumno_a, "Elsa", "Pato", "Alvear 123", "4756545", null);
            fail("Se deber�a haber lanzado la excepci�n NullPointerException.");
        } 
        catch (EmailInvalidoException e)
        {
            fail("No deber�a lanzarse la excepci�n por el formato del email incorrecto.");
        }
        catch (NullPointerException e)
        {
            fail("Se intent� modificar un alumno d�ndole un email nulo.");
        } 
        catch (NoEstaEntidadException e)
        {
            fail("No deber�a lanzarse la excepci�n porque en el TestFixtureAlumno se lo agreg�.");
        }
    }
    
    /**
     * @see pga.Manager#modificaAlumno(Alumno,String,String,String,String,String)
     */
    @Test
    public void testModificaAlumnoErroneo_4_2()
    {   
        try
        {
            this.fixture.manager.modificaAlumno(this.fixture.alumno_a, "", "Quito", "Alvear 123", "4756545", "mimail@gmail.com");
            fail("Se intent� modificar un alumno poni�ndole un nombre vac�o.");
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
     * @see pga.Manager#modificaAlumno(Alumno,String,String,String,String,String)
     */
    @Test
    public void testModificaAlumnoErroneo_4_3()
    {   
        try
        {
            this.fixture.manager.modificaAlumno(this.fixture.alumno_a, "Adolfo", "", "Alvear 123", "4756545", "mimail@gmail.com");
            fail("Se intent� modificar un alumno poni�ndole un apellido vac�o.");
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
     * @see pga.Manager#modificaAlumno(Alumno,String,String,String,String,String)
     */
    @Test
    public void testModificaAlumnoErroneo_4_4()
    {   
        try
        {
            this.fixture.manager.modificaAlumno(this.fixture.alumno_a, "Leonel", "Quito", "", "4756545", "mimail@gmail.com");
            fail("Se intent� modificar un alumno poni�ndole un domicilio vac�o.");
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
     * @see pga.Manager#modificaAlumno(Alumno,String,String,String,String,String)
     */
    @Test
    public void testModificaAlumnoErroneo_4_5()
    {   
        try
        {
            this.fixture.manager.modificaAlumno(this.fixture.alumno_a, "Aquiles", "Bailo", "Alvear 123", "", "mimail@gmail.com");
            fail("Se intent� modificar un alumno poni�ndole un tel�fono vac�o.");
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
     * @see pga.Manager#modificaAlumno(Alumno,String,String,String,String,String)
     */
    @Test
    public void testModificaAlumnoErroneo_4_6()
    {   
        try
        {
            this.fixture.manager.modificaAlumno(this.fixture.alumno_a, "Elsa", "Pato", "Alvear 123", "4756545", "");
            fail("Tendr�a que haberse lanzado la excepci�n EmailInvalidoException.");
        } 
        catch (EmailInvalidoException e)
        {
            assertEquals("El mensaje de la excepci�n no coincide con el previsto.", e.getMessage(), "El mail ingresado no cumple con el formato previsto.");
        } 
        catch (NoEstaEntidadException e)
        {
            fail("No deber�a lanzarse la excepci�n porque en el TestFixtureAlumno se lo agreg�.");
        }
    }
    
    /**
     * @see pga.Manager#modificaAlumno(Alumno,String,String,String,String,String)
     */
    @Test
    public void testModificaAlumnoErroneo_8_1()
    {   
        try
        {
            this.fixture.manager.modificaAlumno(this.fixture.alumno_a, "Elsa", "Pato", "Alvear 123", "4756545", "@mail");
            fail("Tendr�a que haberse lanzado la excepci�n EmailInvalidoException.");
        } 
        catch (EmailInvalidoException e)
        {
            assertEquals("El mensaje de la excepci�n no coincide con el previsto.", e.getMessage(), "El mail ingresado no cumple con el formato previsto.");
        } 
        catch (NoEstaEntidadException e)
        {
            fail("No deber�a lanzarse la excepci�n porque en el TestFixtureAlumno se lo agreg�.");
        }
    }
    
    /**
     * @see pga.Manager#modificaAlumno(Alumno,String,String,String,String,String)
     */
    @Test
    public void testModificaAlumnoErroneo_8_2()
    {   
        try
        {
            this.fixture.manager.modificaAlumno(this.fixture.alumno_a, "Elsa", "Pato", "Alvear 123", "4756545", "mail@");
            fail("Tendr�a que haberse lanzado la excepci�n EmailInvalidoException.");
        } 
        catch (EmailInvalidoException e)
        {
            assertEquals("El mensaje de la excepci�n no coincide con el previsto.", e.getMessage(), "El mail ingresado no cumple con el formato previsto.");
        } 
        catch (NoEstaEntidadException e)
        {
            fail("No deber�a lanzarse la excepci�n porque en el TestFixtureAlumno se lo agreg�.");
        }
    }
    
    /**
     * @see pga.Manager#modificaAlumno(Alumno,String,String,String,String,String)
     */
    @Test
    public void testModificaAlumnoErroneo_8_3()
    {   
        try
        {
            this.fixture.manager.modificaAlumno(this.fixture.alumno_a, "Elsa", "Pato", "Alvear 123", "4756545", "mail");
            fail("Tendr�a que haberse lanzado la excepci�n EmailInvalidoException.");
        } 
        catch (EmailInvalidoException e)
        {
            assertEquals("El mensaje de la excepci�n no coincide con el previsto.", e.getMessage(), "El mail ingresado no cumple con el formato previsto.");
        } 
        catch (NoEstaEntidadException e)
        {
            fail("No deber�a lanzarse la excepci�n porque en el TestFixtureAlumno se lo agreg�.");
        }
    }
    
    /**
     * @see pga.Manager#modificaAlumno(Alumno,String,String,String,String,String)
     */
    @Test
    public void testModificaAlumnoErroneo_14()
    {   
        try
        {
            Alumno alumno = new Alumno("Manuel", "Nucci", "Rawson 273", "4518663", "manuucci96@gmail.com");
            this.fixture.manager.modificaAlumno(alumno, "Esteban", "Quito", "Alvear 123", "4756545", "mimail@gmail.com");
            fail("Tendr�a que haberse lanzado la excepci�n NoEstaEntidadException.");
        } 
        catch (EmailInvalidoException e)
        {
            fail("No deber�a lanzarse la excepci�n por el formato del email incorrecto.");
        } 
        catch (NoEstaEntidadException e)
        {
            assertEquals("El mensaje de la excepci�n no coincide con el previsto.", e.getMessage(), "Alumno no encontrado en el sistema.");
        }
    }
}
