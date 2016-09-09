/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trt23.contavinculada.entities;

import java.time.LocalDate;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author j129-9
 */
public class ColaboradorTest {
    
    public ColaboradorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTitularEm method, of class Colaborador.
     */
    @Test
    public void testGetTitularEm() {
        System.out.println("getTitularEm");
        Colaborador instance = new Colaborador();
        Set<Alocacao> expResult = null;
        Set<Alocacao> result = instance.getTitularEm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AddTitularEm method, of class Colaborador.
     */
    @Test
    public void testAddTitularEm() {
        System.out.println("AddTitularEm");
        Alocacao alocacao = null;
        Colaborador instance = new Colaborador();
        instance.AddTitularEm(alocacao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubstitutoEm method, of class Colaborador.
     */
    @Test
    public void testGetSubstitutoEm() {
        System.out.println("getSubstitutoEm");
        Colaborador instance = new Colaborador();
        Set<Alocacao> expResult = null;
        Set<Alocacao> result = instance.getSubstitutoEm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AddSubstitutoEm method, of class Colaborador.
     */
    @Test
    public void testAddSubstitutoEm() {
        System.out.println("AddSubstitutoEm");
        Alocacao alocacao = null;
        Colaborador instance = new Colaborador();
        instance.AddSubstitutoEm(alocacao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubstituicoes method, of class Colaborador.
     */
    @Test
    public void testGetSubstituicoes() {
        System.out.println("getSubstituicoes");
        Colaborador instance = new Colaborador();
        Set<FaturamentoItem> expResult = null;
        Set<FaturamentoItem> result = instance.getSubstituicoes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AddSubstituicoes method, of class Colaborador.
     */
    @Test
    public void testAddSubstituicoes() {
        System.out.println("AddSubstituicoes");
        FaturamentoItem faturamentoIt = null;
        Colaborador instance = new Colaborador();
        instance.AddSubstituicoes(faturamentoIt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitularidades method, of class Colaborador.
     */
    @Test
    public void testGetTitularidades() {
        System.out.println("getTitularidades");
        Colaborador instance = new Colaborador();
        Set<FaturamentoItem> expResult = null;
        Set<FaturamentoItem> result = instance.getTitularidades();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AddTitularidades method, of class Colaborador.
     */
    @Test
    public void testAddTitularidades() {
        System.out.println("AddTitularidades");
        FaturamentoItem faturamentoIt = null;
        Colaborador instance = new Colaborador();
        instance.AddTitularidades(faturamentoIt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRetencoes method, of class Colaborador.
     */
    @Test
    public void testGetRetencoes() {
        System.out.println("getRetencoes");
        Colaborador instance = new Colaborador();
        Set<Retencao> expResult = null;
        Set<Retencao> result = instance.getRetencoes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AddRetencoes method, of class Colaborador.
     */
    @Test
    public void testAddRetencoes() {
        System.out.println("AddRetencoes");
        Retencao retencao = null;
        Colaborador instance = new Colaborador();
        instance.AddRetencoes(retencao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testEquals(){
        Colaborador c1 = new Colaborador();
        Colaborador c2 = new Colaborador();        
        Colaborador c3 = new Colaborador();        
        Colaborador c4 = null;   
        Colaborador c5 = c1;
        Colaborador c6 = new Colaborador();
        Colaborador c7 = new Colaborador();
        Colaborador c8 = new Colaborador();        
        Colaborador c9 = new Colaborador();                
        PessoaFisica ze = new PessoaFisica();
        ze.setCpf("81916191134");
        PessoaFisica mane = new PessoaFisica();
        mane.setCpf("57756239407");
        PessoaJuridica ltda = new PessoaJuridica();
        ltda.setCnpj("14475817000120");
        PessoaJuridica sa = new PessoaJuridica();
        sa.setCnpj("23778577000124");
        c1.setId(1L);
        c2.setId(2L);
        c3.setId(1L);        
        c6.setColaborador(mane);
        c6.setEmpregador(ltda);
        c7.setColaborador(ze);
        c7.setEmpregador(sa);
        c8.setColaborador(ze);
        c8.setEmpregador(sa);
        c9.setEmpregador(sa);
        c9.setColaborador(mane);
        assertEquals(false, c1.equals(c2));
        assertEquals(true, c1.equals(c3));
        assertEquals(false, c1.equals(c4));
        assertEquals(true, c1.equals(c5));
        assertEquals(true, c1.equals(c2));
        assertEquals(false, c6.equals(c7));    
        assertEquals(true, c7.equals(c8));
        assertEquals(false, c8.equals(c9));
    }
    /**
     * Test of getSalarios method, of class Colaborador.
     */
    @Test
    public void testGetSalarios() {
        System.out.println("getSalarios");
        Colaborador instance = new Colaborador();
        Set<Salario> expResult = null;
        Set<Salario> result = instance.getSalarios();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AddSalarios method, of class Colaborador.
     */
    @Test
    public void testAddSalarios() {
        System.out.println("AddSalarios");
        Salario salario = null;
        Colaborador instance = new Colaborador();
        instance.AddSalarios(salario);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Colaborador.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Colaborador instance = new Colaborador();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmpregador method, of class Colaborador.
     */
    @Test
    public void testGetEmpregador() {
        System.out.println("getEmpregador");
        Colaborador instance = new Colaborador();
        PessoaJuridica expResult = null;
        PessoaJuridica result = instance.getEmpregador();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmpregador method, of class Colaborador.
     */
    @Test
    public void testSetEmpregador() {
        System.out.println("setEmpregador");
        PessoaJuridica empregador = null;
        Colaborador instance = new Colaborador();
        instance.setEmpregador(empregador);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColaborador method, of class Colaborador.
     */
    @Test
    public void testGetColaborador() {
        System.out.println("getColaborador");
        Colaborador instance = new Colaborador();
        PessoaFisica expResult = null;
        PessoaFisica result = instance.getColaborador();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setColaborador method, of class Colaborador.
     */
    @Test
    public void testSetColaborador() {
        System.out.println("setColaborador");
        PessoaFisica colaborador = null;
        Colaborador instance = new Colaborador();
        instance.setColaborador(colaborador);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIsDesligamentoPorJustaCausa method, of class Colaborador.
     */
    @Test
    public void testGetIsDesligamentoPorJustaCausa() {
        System.out.println("getIsDesligamentoPorJustaCausa");
        Colaborador instance = new Colaborador();
        Boolean expResult = null;
        Boolean result = instance.getIsDesligamentoPorJustaCausa();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIsDesligamentoPorJustaCausa method, of class Colaborador.
     */
    @Test
    public void testSetIsDesligamentoPorJustaCausa() {
        System.out.println("setIsDesligamentoPorJustaCausa");
        Boolean isDesligamentoPorJustaCausa = null;
        Colaborador instance = new Colaborador();
        instance.setIsDesligamentoPorJustaCausa(isDesligamentoPorJustaCausa);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecebeuManualEm method, of class Colaborador.
     */
    @Test
    public void testGetRecebeuManualEm() {
        System.out.println("getRecebeuManualEm");
        Colaborador instance = new Colaborador();
        LocalDate expResult = null;
        LocalDate result = instance.getRecebeuManualEm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRecebeuManualEm method, of class Colaborador.
     */
    @Test
    public void testSetRecebeuManualEm() {
        System.out.println("setRecebeuManualEm");
        LocalDate recebeuManualEm = null;
        Colaborador instance = new Colaborador();
        instance.setRecebeuManualEm(recebeuManualEm);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResponsavelTecnico method, of class Colaborador.
     */
    @Test
    public void testGetResponsavelTecnico() {
        System.out.println("getResponsavelTecnico");
        Colaborador instance = new Colaborador();
        String expResult = "";
        String result = instance.getResponsavelTecnico();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setResponsavelTecnico method, of class Colaborador.
     */
    @Test
    public void testSetResponsavelTecnico() {
        System.out.println("setResponsavelTecnico");
        String responsavelTecnico = "";
        Colaborador instance = new Colaborador();
        instance.setResponsavelTecnico(responsavelTecnico);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
