package relatorios;

import model.AmbienteGeral;
import model.Empresa;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class RelatorioEmpresaGeral extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    public static final int INDEX_NOME = 0;
    public static final int INDEX_SITE = 1;
    public static final int INDEX_AMBIENTE = 2;
    public static final int INDEX_ESCONDIDO = 3;

    protected String[] nomeColunas;
    protected Vector<Empresa> vetorDados;

    public RelatorioEmpresaGeral(String[] columnNames, Vector<Empresa> vetorDados) {
        this.nomeColunas = columnNames;
        this.vetorDados = vetorDados;
    }

    @Override
    public String getColumnName(int column) {
        return nomeColunas[column];
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        if (coluna == INDEX_ESCONDIDO) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Empresa registroEmpresaGeral = (Empresa) vetorDados.get(linha);
        switch (coluna) {
            case INDEX_NOME:
                return registroEmpresaGeral.getNome();
            case INDEX_SITE:
                return registroEmpresaGeral.getSite();
            case INDEX_AMBIENTE:
                return registroEmpresaGeral.getAmbiente().getNome();
            default:
                return new Object();
        }
    }

    @Override
    public int getRowCount() {
        return vetorDados.size();
    }

    @Override
    public int getColumnCount() {
        return nomeColunas.length;
    }
}
