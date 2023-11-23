package main.java.com.crud;

import main.java.com.crud.model.AmbienteGeral;
import main.java.com.crud.model.Cidade;
import main.java.com.crud.relatorios.RelatorioAmbienteCidade;


import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class RelatorioAmbienteCidadeForm  extends JPanel {

    private static final long serialVersionUID = 1L;

    public static final String[] nomeColunas =
            {"Nome", "Cep", "Rua", "Número", "Bairro", "Categoria", "Quantidade Empresas", ""};

    protected JTable table;
    protected JScrollPane scroller;
    protected RelatorioAmbienteCidade tabela;

    public RelatorioAmbienteCidadeForm(Vector<AmbienteGeral> vetorDados) {
        iniciarComponentes(vetorDados);
    }


    public void iniciarComponentes(Vector<AmbienteGeral> vetorDados) {
        tabela = new RelatorioAmbienteCidade(nomeColunas, vetorDados);
        table = new JTable();
        table.setModel(tabela);
        table.setSurrendersFocusOnKeystroke(true);
        scroller = new javax.swing.JScrollPane(table);
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(1100, 300));

        TableColumn colunaEscondida = table.getColumnModel().getColumn(RelatorioAmbienteCidade.INDEX_ESCONDIDO);
        colunaEscondida.setMinWidth(2);
        colunaEscondida.setPreferredWidth(2);
        colunaEscondida.setMaxWidth(2);
        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);
    }

    public static void emitirRelatorio (List<AmbienteGeral> ambienteGerals, Cidade cidade) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            JFrame frame = new JFrame("Relatório de Ambientes Cidade ("+cidade.getNome()+") ("+cidade.getUF()+")");

            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent evt) {
                    frame.setVisible(false);
                    try {
                        Main.chamaMenuRelatoriosAmbiente();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Vector<AmbienteGeral> vetorDados = new Vector<AmbienteGeral>();
            for (AmbienteGeral ambienteGeral : ambienteGerals) {
                vetorDados.add(ambienteGeral);
            }

            frame.getContentPane().add(new RelatorioAmbienteCidadeForm(vetorDados));
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
