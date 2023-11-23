package test.java.relatorios;

import main.java.model.AmbienteGeral;
import java.util.Vector;

import main.java.relatorios.RelatorioAmbienteCidade;
import org.junit.Test;

import static org.junit.Assert.*;

public class RelatorioAmbienteCidadeTest {

    @Test
    public void testGetColumnName() {
        String[] columnNames = {"Nome", "CEP", "Rua", "Número", "Bairro", "Categoria", "Quantidade Empresas", "Escondido"};
        Vector<AmbienteGeral> vetorDados = new Vector<>();
        RelatorioAmbienteCidade relatorio = new RelatorioAmbienteCidade(columnNames, vetorDados);

        assertEquals("Nome", relatorio.getColumnName(0));
        assertEquals("CEP", relatorio.getColumnName(1));
        assertEquals("Rua", relatorio.getColumnName(2));
        assertEquals("Número", relatorio.getColumnName(3));
        assertEquals("Bairro", relatorio.getColumnName(4));
        assertEquals("Categoria", relatorio.getColumnName(5));
        assertEquals("Quantidade Empresas", relatorio.getColumnName(6));
        assertEquals("Escondido", relatorio.getColumnName(7));
    }

    @Test
    public void testIsCellEditable() {
        String[] columnNames = {"Nome", "CEP", "Rua", "Número", "Bairro", "Categoria", "Quantidade Empresas", "Escondido"};
        Vector<AmbienteGeral> vetorDados = new Vector<>();
        RelatorioAmbienteCidade relatorio = new RelatorioAmbienteCidade(columnNames, vetorDados);

        assertTrue(relatorio.isCellEditable(0, 0));
        assertTrue(relatorio.isCellEditable(0, 1));
        assertFalse(relatorio.isCellEditable(0, 7));
    }

    @Test
    public void testGetValueAt() {
        String[] columnNames = {"Nome", "CEP", "Rua", "Número", "Bairro", "Categoria", "Quantidade Empresas", "Escondido"};
        Vector<AmbienteGeral> vetorDados = new Vector<>();
        AmbienteGeral ambienteGeral = new AmbienteGeral();
        vetorDados.add(ambienteGeral);
        RelatorioAmbienteCidade relatorio = new RelatorioAmbienteCidade(columnNames, vetorDados);

        assertEquals(ambienteGeral.getNome(), relatorio.getValueAt(0, 0));
        assertEquals(ambienteGeral.getCep(), relatorio.getValueAt(0, 1));
        assertEquals(ambienteGeral.getRua(), relatorio.getValueAt(0, 2));
        assertEquals(ambienteGeral.getNumero(), relatorio.getValueAt(0, 3));
        assertEquals(ambienteGeral.getBairro(), relatorio.getValueAt(0, 4));
    }

    @Test
    public void testGetRowCount() {
        String[] columnNames = {"Nome", "CEP", "Rua", "Número", "Bairro", "Categoria", "Quantidade Empresas", "Escondido"};
        Vector<AmbienteGeral> vetorDados = new Vector<>();
        vetorDados.add(new AmbienteGeral());
        vetorDados.add(new AmbienteGeral());
        RelatorioAmbienteCidade relatorio = new RelatorioAmbienteCidade(columnNames, vetorDados);

        assertEquals(2, relatorio.getRowCount());
    }

    @Test
    public void testGetColumnCount() {
        String[] columnNames = {"Nome", "CEP", "Rua", "Número", "Bairro", "Categoria", "Quantidade Empresas", "Escondido"};
        Vector<AmbienteGeral> vetorDados = new Vector<>();
        RelatorioAmbienteCidade relatorio = new RelatorioAmbienteCidade(columnNames, vetorDados);

        assertEquals(columnNames.length, relatorio.getColumnCount());
    }
}
