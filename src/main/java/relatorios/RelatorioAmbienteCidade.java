package main.java.relatorios;

import main.java.model.AmbienteGeral;


import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class RelatorioAmbienteCidade extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    public static final int INDEX_NOME = 0;
    public static final int INDEX_CEP = 1;
    public static final int INDEX_RUA = 2;
    public static final int INDEX_NUMERO = 3;
    public static final int INDEX_BAIRRO = 4;
    public static final int INDEX_CATEGORIA = 5;
    public static final int INDEX_QUANTIDADE_EMPRESAS = 6;
    public static final int INDEX_ESCONDIDO = 7;

    protected String[] nomeColunas;
    protected Vector<AmbienteGeral> vetorDados;

    public RelatorioAmbienteCidade(String[] columnNames, Vector<AmbienteGeral> vetorDados) {
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
        AmbienteGeral registroAmbienteGeral = (AmbienteGeral) vetorDados.get(linha);
        switch (coluna) {
            case INDEX_NOME:
                return registroAmbienteGeral.getNome();
            case INDEX_CEP:
                return registroAmbienteGeral.getCep();
            case INDEX_RUA:
                return registroAmbienteGeral.getRua();
            case INDEX_NUMERO:
                return registroAmbienteGeral.getNumero();
            case INDEX_BAIRRO:
                return registroAmbienteGeral.getBairro();
            case INDEX_CATEGORIA:
                return registroAmbienteGeral.getCategoria().getNome();
            case INDEX_QUANTIDADE_EMPRESAS:
                return registroAmbienteGeral.getListaEmpresa().size();
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

