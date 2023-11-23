package test.java.model;

import main.java.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AmbienteGeralTest {

    private AmbienteGeral ambienteGeral;
    private Contato contato;
    private Contato contato2;

    private Ambiente ambiente;

    @Before
    public void setUp() {
        Cidade cidade = new Cidade(1, "São Paulo", EstadoEnum.SP);
        Categoria categoria = new Categoria(1, "Categoria Teste");
        ambiente = new Ambiente(1, "Ambiente Teste", "12345", "Rua Teste", "123", "Bairro Teste", cidade, categoria);
        contato = new Contato(1, "Nome do Contato");
        contato2 = new Contato(2, "Nome do Contato2");

        List<Empresa> listaEmpresa = new ArrayList<>();

        ambienteGeral = new AmbienteGeral();
        ambienteGeral.setId(1);
        ambienteGeral.setNome("AmbienteGeral Teste");
        ambienteGeral.setCep("12345");
        ambienteGeral.setRua("Rua Teste");
        ambienteGeral.setNumero("123");
        ambienteGeral.setBairro("Bairro Teste");
        ambienteGeral.setCidade(cidade);
        ambienteGeral.setCategoria(categoria);
        ambienteGeral.setListaEmpresa(listaEmpresa);
    }

    @Test
    public void testGettersAndSetters() {

        Empresa empresa1 = new Empresa(1, "Empresa Teste 1", "logo1", "site1", ambiente);
        Empresa empresa2 = new Empresa(2, "Empresa Teste 2", "logo2", "site2", ambiente);

        // Adicionando contatos às empresas para ilustrar o método adicionarContato
        empresa1.adicionarContato(new EmpresaContato(1, "Contato 1", empresa1, contato));
        empresa2.adicionarContato(new EmpresaContato(2, "Contato 2", empresa2,contato2));

        // Criando uma nova lista de empresas e adicionando as empresas criadas
        List<Empresa> novaListaEmpresa = new ArrayList<>();
        novaListaEmpresa.add(empresa1);
        novaListaEmpresa.add(empresa2);

        ambienteGeral.setListaEmpresa(novaListaEmpresa);

        assertEquals(2, ambienteGeral.getListaEmpresa().size());
        assertEquals("Empresa Teste 1", ambienteGeral.getListaEmpresa().get(0).getNome());
        assertEquals("logo1", ambienteGeral.getListaEmpresa().get(0).getLogo());
        assertEquals("site1", ambienteGeral.getListaEmpresa().get(0).getSite());

        assertEquals("Empresa Teste 2", ambienteGeral.getListaEmpresa().get(1).getNome());
        assertEquals("logo2", ambienteGeral.getListaEmpresa().get(1).getLogo());
        assertEquals("site2", ambienteGeral.getListaEmpresa().get(1).getSite());

        // Verificando contatos nas empresas
        assertEquals(1, ambienteGeral.getListaEmpresa().get(0).getListaContato().size());
        assertEquals("Contato 1", ambienteGeral.getListaEmpresa().get(0).getListaContato().get(0).getDescricao());

        assertEquals(1, ambienteGeral.getListaEmpresa().get(1).getListaContato().size());
        assertEquals("Contato 2", ambienteGeral.getListaEmpresa().get(1).getListaContato().get(0).getDescricao());
    }
}
