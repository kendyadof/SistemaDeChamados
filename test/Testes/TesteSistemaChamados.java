/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import controle.ControladorPrincipal;
import entidade.Chamado;
import entidade.Empresa;
import entidade.Tecnico;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pepe
 */
public class TesteSistemaChamados {

    public TesteSistemaChamados() {
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

    @Test
    public void testaInsercaoEmpresa() {
        ControladorPrincipal controller = new ControladorPrincipal();
        controller.start();
        Empresa empresa = new Empresa((long) 121, "teste9");
        controller.getCtrEmpresa().inserir(empresa.getNumeroContrato(), empresa.getNomeEmpresa());
        assertTrue(controller.getCtrEmpresa().checar(empresa.getNumeroContrato(), empresa.getNomeEmpresa()));
    }

    @Test
    public void testaInsercaoCliente() {
        ControladorPrincipal controller = new ControladorPrincipal();
        controller.start();
        controller.getCtrClientes().getClienteDAO().put(controller.getCtrClientes().
                incluiNovoCliente(controller.getCtrEmpresa().retorna(123, "teste"),
                        4983031, "clienteteste", 55314271));
        assertTrue(controller.getCtrClientes().getClienteDAO().validarCPF(4983031));

    }

    @Test
    public void testaInsercaoTecnico() {
        ControladorPrincipal controller = new ControladorPrincipal();
        controller.start();
        Tecnico tecnico = new Tecnico("Arnaldo", (long) 213);
        assertNotNull(controller.getCtrTecnicos().inserir(tecnico.getTelefone(), tecnico.getNome()));
    }

    @Test
    public void testaInsercaoChamadoBancoDeDados() {
        ControladorPrincipal controller = new ControladorPrincipal();
        controller.start();
        Tecnico tecnico = new Tecnico("Arnaldo", (long) 213);
        Chamado chamado = controller.getCtrChamados().
                InserirChamadoBancoDeDados("teste1", "descricao", 1, tecnico,
                        controller.getCtrClientes().
                                incluiNovoCliente(controller.getCtrEmpresa().retorna(123, "teste"),
                                        4983031, "clienteteste", 55314271),
                        "Windows", "1.0", "banco");
        assertNotNull(controller.getCtrChamados().alterarChamado(chamado, chamado.getStatus(), "nada", "naosei"));
    }

    @Test
    public void testaInsercaoChamadoRede() {
        ControladorPrincipal controller = new ControladorPrincipal();
        controller.start();
        Tecnico tecnico = new Tecnico("Arnaldo", (long) 213);
        Chamado chamado = controller.getCtrChamados().
                InserirChamadoRede("teste1", "descricao", 1, tecnico,
                        controller.getCtrClientes().
                                incluiNovoCliente(controller.getCtrEmpresa().retorna(123, "teste"),
                                        4983031, "clienteteste", 55314271),
                        "Windows", "1.0", "conexao", "192.169.0.1");
        assertNotNull(controller.getCtrChamados().alterarChamado(chamado, chamado.getStatus(), "nada", "naosei"));
    }

    @Test
    public void testaInsercaoChamadoDesempenho() {
        ControladorPrincipal controller = new ControladorPrincipal();
        controller.start();
        Tecnico tecnico = new Tecnico("Arnaldo", (long) 213);
        Chamado chamado = controller.getCtrChamados().
                InserirChamadoDesempenho("teste1", "descricao", 1, tecnico,
                        controller.getCtrClientes().
                                incluiNovoCliente(controller.getCtrEmpresa().retorna(123, "teste"),
                                        4983031, "clienteteste", 55314271),
                        "Windows", "1.0", "operacao", 2.0);
        assertNotNull(controller.getCtrChamados().alterarChamado(chamado, chamado.getStatus(), "nada", "naosei"));
    }

    @Test
    public void testaInsercaoRegistroChamado() {
        ControladorPrincipal controller = new ControladorPrincipal();
        controller.start();
        Tecnico tecnico = new Tecnico("Arnaldo", (long) 213);
        Chamado chamado = controller.getCtrChamados().
                InserirChamadoDesempenho("teste1", "descricao", 1, tecnico,
                        controller.getCtrClientes().
                                incluiNovoCliente(controller.getCtrEmpresa().retorna(123, "teste"),
                                        4983031, "clienteteste", 55314271),
                        "Windows", "1.0", "operacao", 2.0);
        assertNotNull(controller.getCtrChamados().inserirRegistroChamado("assunto", chamado, tecnico));
    }
    
    @Test
    public void testeValidacaoEmpresaSemUso(){
        ControladorPrincipal controller = new ControladorPrincipal();
        controller.start();
        Empresa empresa = new Empresa((long) 121, "teste9");
        //A empresa não está em uso
        assertEquals(4, controller.getCtrEmpresa().validar(empresa.getNumeroContrato(), empresa.getNomeEmpresa()));
    }
    
    @Test
    public void testeValidacaoEmpresaEmUso(){
        ControladorPrincipal controller = new ControladorPrincipal();
        controller.start();
        Empresa empresa = new Empresa((long) 121, "teste9");
        //A empresa está em uso
        assertEquals(1, controller.getCtrEmpresa().validar(empresa.getNumeroContrato(), empresa.getNomeEmpresa()));
    }
    
        @Test
    public void testeValidacaoEmpresaNomeEmUso(){
        ControladorPrincipal controller = new ControladorPrincipal();
        controller.start();
        Empresa empresa = new Empresa((long) 121, "teste9");
        //Só o nome da empresa está em uso
        assertEquals(2, controller.getCtrEmpresa().validar(empresa.getNumeroContrato(), empresa.getNomeEmpresa()));
    }
    
        @Test
    public void testeValidacaoEmpresaCodigoEmUso(){
        ControladorPrincipal controller = new ControladorPrincipal();
        controller.start();
        Empresa empresa = new Empresa((long) 121, "teste9");
        //Só o código da empresa está em uso
        assertEquals(3, controller.getCtrEmpresa().validar(empresa.getNumeroContrato(), empresa.getNomeEmpresa()));
    }

}
