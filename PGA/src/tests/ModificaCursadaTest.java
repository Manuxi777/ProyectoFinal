package tests;

import exceptions.HoraInvalidaException;
import exceptions.NoEstaEntidadException;
import exceptions.PeriodoInvalidoException;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import pga.Cursada;

public class ModificaCursadaTest
{
    TestFixtureCursada fixture1 = new TestFixtureCursada();

    public ModificaCursadaTest()
    {
    }

    @Before
    public void setUp() throws Exception
    {
        fixture1.setUpAltayModif();
    }

    @After
    public void tearDown() throws Exception
    {
        fixture1.tearDown();
    }

    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testModificaCursadaExitosa_1_1()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes", "15:00", "17:00");
            assertTrue("La cursada no se modifico correctamente", this.fixture1.cursada_b.getNombre().equals("Cursada Mate A 1") &&
                                                    this.fixture1.cursada_b.getAsignatura().equals(this.fixture1.asignatura_a) &&
                                                    this.fixture1.cursada_b.getDia().equals("Lunes") &&
                                                    this.fixture1.cursada_b.getPeriodo().equals("01-2017") &&
                                                    this.fixture1.cursada_b.getHoraInicio().equals("15:00") &&
                                                    this.fixture1.cursada_b.getHoraFin().equals("17:00"));
                                                    
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        } catch (NoEstaEntidadException e)
        {
            fail("No tendria que haber salido por la excepcion NoEstaEntidadException");
        }
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testModificaCursadaExitosa_1_2()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "C", this.fixture1.asignatura_a, "01-2000", "Lunes", "00:00", "00:01");
            assertTrue("La cursada no se modifico correctamente", this.fixture1.cursada_b.getNombre().equals("C") &&
                                                    this.fixture1.cursada_b.getAsignatura().equals(this.fixture1.asignatura_a) &&
                                                    this.fixture1.cursada_b.getDia().equals("Lunes") &&
                                                    this.fixture1.cursada_b.getPeriodo().equals("01-2000") &&
                                                    this.fixture1.cursada_b.getHoraInicio().equals("00:00") &&
                                                    this.fixture1.cursada_b.getHoraFin().equals("00:01"));
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        } catch (NoEstaEntidadException e)
        {
            fail("No tendria que haber salido por la excepcion NoEstaEntidadException");
        }
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testModificaCursadaExitosa_1_3()
    {
        
        try
        {
            int size = this.fixture1.manager.getCursadas().size();
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "C", this.fixture1.asignatura_a, "02-2100", "Domingo", "23:58", "23:59");
            assertTrue("La cursada no se modifico correctamente", this.fixture1.cursada_b.getNombre().equals("C") &&
                                                    this.fixture1.cursada_b.getAsignatura().equals(this.fixture1.asignatura_a) &&
                                                    this.fixture1.cursada_b.getDia().equals("Domingo") &&
                                                    this.fixture1.cursada_b.getPeriodo().equals("02-2100") &&
                                                    this.fixture1.cursada_b.getHoraInicio().equals("23:58") &&
                                                    this.fixture1.cursada_b.getHoraFin().equals("23:59"));
            } catch (HoraInvalidaException e)
            {
                fail("No tendria que haber salido por la excepcion HoraInvalidaException");
            } catch (PeriodoInvalidoException e)
            {
                fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
            } catch (NoEstaEntidadException e)
            {
                fail("No tendria que haber salido por la excepcion NoEstaEntidadException");
            }
    }
        
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testModificaCursadaErronea_2_2()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, null, this.fixture1.asignatura_a, "01-2017", "Lunes", "12:00", "14:00");
            fail("La cursada se modifico con un nombre nulo");
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        } catch (NullPointerException e)
        {
            fail("Se intento modificar una cursada con nombre nulo");
        } catch (NoEstaEntidadException e)
        {
            fail("No tendria que haber salido por la excepcion NoEstaEntidadException");
        }

    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_2_3()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", null, "01-2017", "Lunes", "12:00", "14:00");
            fail("La cursada se modifico con una asignatura nula");
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        } catch (NoEstaEntidadException e)
        {
            fail("No tendria que haber salido por la excepcion NoEstaEntidadException");
        }

    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_2_4()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, null, "Lunes", "12:00", "14:00");
            fail("La cursada se anadio al sistema");
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        } catch (NullPointerException e)
        {
            fail("Se intento dar de alta una cursada con periodo nulo");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_2_5()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", null, "12:00", "14:00");
            fail("La cursada se anadio al sistema");
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        } catch (NullPointerException e)
        {
            fail("Se intento dar de alta una cursada con dia nulo");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_2_6()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes", null, "14:00");
            fail("La cursada se anadio al sistema");
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        } catch (NullPointerException e)
        {
            fail("Se intento dar de alta una cursada con hora de inicio nulo");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_2_7()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","12:00", null);
            fail("La cursada se anadio al sistema");
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        } catch (NullPointerException e)
        {
            fail("Se intento dar de alta una cursada con hora de fin nulo");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_4_2()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "", this.fixture1.asignatura_a, "01-2017", "Lunes","12:00","14:00");
            fail("La cursada se anadio al sistema con un nombre vacio");
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_4_3()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "", "Lunes","12:00","14:00");
            fail("La cursada se anadio al sistema con un periodo vacio");
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            assertEquals("Las excepciones no coinciden", "El periodo ingresado no cumple con el formato previsto.", e.getMessage());
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_4_4()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "","12:00","14:00");
            fail("La cursada se anadio al sistema con un dia vacio");
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_4_5()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","","14:00");
            fail("La cursada se anadio al sistema con una hora de inicio vacio");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coincides", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_4_6()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","12:00","");
            fail("La cursada se anadio al sistema con una hora de fin vacio");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coincides", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_8_1()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01P-A2017", "Lunes","12:00","14:00");
            fail("La cursada se anadio al sistema con un periodo invalido");
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            assertEquals("Las excepciones no coinciden", "El periodo ingresado no cumple con el formato previsto.", e.getMessage());
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_8_2()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01:2017", "Lunes","12:00","14:00");
            fail("La cursada se anadio al sistema con un periodo invalido");
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            assertEquals("Las excepciones no coinciden", "El periodo ingresado no cumple con el formato previsto.", e.getMessage());
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_8_3()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "03-2017", "Lunes","12:00","14:00");
            fail("La cursada se anadio al sistema con un periodo invalido");
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            assertEquals("Las excepciones no coinciden", "El periodo ingresado no cumple con el formato previsto.", e.getMessage());
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_8_4()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-20L7", "Lunes","12:00","14:00");
            fail("La cursada se anadio al sistema con un periodo invalido");
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            assertEquals("Las excepciones no coinciden", "El periodo ingresado no cumple con el formato previsto.", e.getMessage());
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_8_5()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-1950", "Lunes","12:00","14:00");
            fail("La cursada se anadio al sistema con un periodo invalido");
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            assertEquals("Las excepciones no coinciden", "El periodo ingresado no cumple con el formato previsto.", e.getMessage());
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_8_5_2()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-1999", "Lunes","12:00","14:00");
            fail("La cursada se anadio al sistema con un periodo invalido");
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            assertEquals("Las excepciones no coinciden", "El periodo ingresado no cumple con el formato previsto.", e.getMessage());
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_8_6()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2150", "Lunes","12:00","14:00");
            fail("La cursada se anadio al sistema con un periodo invalido");
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            assertEquals("Las excepciones no coinciden", "El periodo ingresado no cumple con el formato previsto.", e.getMessage());
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_8_6_2()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2101", "Lunes","12:00","14:00");
            fail("La cursada se anadio al sistema con un periodo invalido");
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            assertEquals("Las excepciones no coinciden", "El periodo ingresado no cumple con el formato previsto.", e.getMessage());
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_10()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2101", "Osvaldo","12:00","14:00");
            fail("La cursada se anadio al sistema con un dia invalido");
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            assertEquals("Las excepciones no coinciden", "El periodo ingresado no cumple con el formato previsto.", e.getMessage());
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_1()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","122:00","14:00");
            fail("La cursada se anadio al sistema con una hora de inicio invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_2()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","A1:00","14:00");
            fail("La cursada se anadio al sistema con una hora de inicio invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }   
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_3()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","12:D2","14:00");
            fail("La cursada se anadio al sistema con una hora de inicio invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }   
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_4()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","12-00","14:00");
            fail("La cursada se anadio al sistema con una hora de inicio invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_5()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","-5:00","14:00");
            fail("La cursada se anadio al sistema con una hora de inicio invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_5_2()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","-1:00","14:00");
            fail("La cursada se anadio al sistema con una hora de inicio invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_6()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","40:00","14:00");
            fail("La cursada se anadio al sistema con una hora de inicio invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_6_2()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","24:00","14:00");
            fail("La cursada se anadio al sistema con una hora de inicio invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_7()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","12:-5","14:00");
            fail("La cursada se anadio al sistema con una hora de inicio invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_7_2()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","12:-1","14:00");
            fail("La cursada se anadio al sistema con una hora de inicio invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_8()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","12:67","14:00");
            fail("La cursada se anadio al sistema con una hora de inicio invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_8_2()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","12:60","14:00");
            fail("La cursada se anadio al sistema con una hora de inicio invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_9()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","12:00","143:00");
            fail("La cursada se anadio al sistema con una hora de fin invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_10()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","12:00","A2:00");
            fail("La cursada se anadio al sistema con una hora de fin invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_11()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","12:00","14:F3");
            fail("La cursada se anadio al sistema con una hora de fin invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_12()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","12:00","14-00");
            fail("La cursada se anadio al sistema con una hora de fin invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_14()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","12:00","-5:00");
            fail("La cursada se anadio al sistema con una hora de fin invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_14_2()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","12:00","-1:00");
            fail("La cursada se anadio al sistema con una hora de fin invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_15()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","12:00","14:-5");
            fail("La cursada se anadio al sistema con una hora de fin invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_15_2()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","12:00","14:-1");
            fail("La cursada se anadio al sistema con una hora de fin invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_16()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","12:00","14:67");
            fail("La cursada se anadio al sistema con una hora de fin invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_12_16_2()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","12:00","14:60");
            fail("La cursada se anadio al sistema con una hora de fin invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_14()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada(this.fixture1.cursada_b, "Cursada Mate A 1", this.fixture1.asignatura_a, "01-2017", "Lunes","16:00","14:00");
            fail("La cursada se anadio al sistema con una hora de fin invalido");
        } catch (HoraInvalidaException e)
        {
            assertEquals("Las excepciones no coinciden", "La hora ingresada no cumple con el formato previsto.", e.getMessage());
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
    
    /**
     * @see pga.Manager#modificaCursada(pga.Cursada,String,pga.Asignatura,String,String,String,String)
     */
    @Test
    public void testAltaCursadaErronea_28()
    {
        
        try
        {
            this.fixture1.manager.modificaCursada("Cursada Estadistica", this.fixture1.asignatura_d, "01-2017", "Lunes","16:00","14:00");
            fail("La cursada se anadio al sistema con una asignatura que no pertenece al sistema");
        } catch (HoraInvalidaException e)
        {
            fail("No tendria que haber salido por la excepcion HoraInvalidaException");
        } catch (PeriodoInvalidoException e)
        {
            fail("No tendria que haber salido por la excepcion PeriodoInvalidoException");
        }
        
    }
}
