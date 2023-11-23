package test.java.model;

import main.java.com.crud.model.Ambiente;
import main.java.com.crud.model.Categoria;
import main.java.com.crud.model.Cidade;
import main.java.com.crud.model.EstadoEnum;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AmbienteTest {

    private Ambiente ambiente;

    @Before
    public void setUp() {
        Cidade cidade = new Cidade(1, "São Paulo", EstadoEnum.SP);
        Categoria categoria = new Categoria(1, "Categoria Teste");

        ambiente = new Ambiente(1, "Ambiente Teste", "12345", "Rua Teste", "123", "Bairro Teste", cidade, categoria);
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(Integer.valueOf(1), ambiente.getId());
        assertEquals("Ambiente Teste", ambiente.getNome());
        assertEquals("12345", ambiente.getCep());
        assertEquals("Rua Teste", ambiente.getRua());
        assertEquals("123", ambiente.getNumero());
        assertEquals("Bairro Teste", ambiente.getBairro());
        assertEquals("São Paulo", ambiente.getCidade().getNome());
        assertEquals("Categoria Teste", ambiente.getCategoria().getNome());

        ambiente.setId(2);
        assertEquals(Integer.valueOf(2), ambiente.getId());

        ambiente.setNome("Novo Ambiente");
        assertEquals("Novo Ambiente", ambiente.getNome());

        Cidade novaCidade = new Cidade(2, "Rio de Janeiro", EstadoEnum.RJ);
        ambiente.setCidade(novaCidade);

        assertEquals("Rio de Janeiro", ambiente.getCidade().getNome());
    }
}
