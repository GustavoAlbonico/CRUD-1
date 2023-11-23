package main.java.com.crud.relatorios;

import main.java.com.crud.model.Empresa;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class RelatorioEmpresaAmbiente extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    public static final int INDEX_NOME = 0;
    public static final int INDEX_SITE = 1;
    public static final int INDEX_ESCONDIDO = 2;

    protected String[] nomeColunas;
    protected Vector<Empresa> vetorDados;

    public RelatorioEmpresaAmbiente(String[] columnNames, Vector<Empresa> vetorDados) {
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
        Empresa registroEmpresaAmbiente = (Empresa) vetorDados.get(linha);
        switch (coluna) {
            case INDEX_NOME:
                return registroEmpresaAmbiente.getNome();
            case INDEX_SITE:
                return registroEmpresaAmbiente.getSite();
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

