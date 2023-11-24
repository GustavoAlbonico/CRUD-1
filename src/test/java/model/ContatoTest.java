package test.java.model;

import main.java.model.Contato;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContatoTest {

    private Contato contato;

    @Before
    public void setUp() {
        contato = new Contato(1, "Nome do Contato");
    }

    @Test
    public void testGettersAndSetters() {
        assertEquals(Integer.valueOf(1), contato.getId());
        assertEquals("Nome do Contato", contato.getNome());

        contato.setId(2);
        contato.setNome("Novo Nome do Contato");

        assertEquals(Integer.valueOf(2), contato.getId());
        assertEquals("Novo Nome do Contato", contato.getNome());
    }

    @Test
    public void testConstructorWithoutArguments() {
        Contato novoContato = new Contato();

        assertNull(novoContato.getId());
        assertNull(novoContato.getNome());
    }
}
