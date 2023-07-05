import model.AmbienteGeral;
import model.Empresa;
import relatorios.RelatorioAmbienteGeral;
import relatorios.RelatorioEmpresaGeral;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class RelatorioEmpresaGeralForm extends JPanel {

    private static final long serialVersionUID = 1L;

    public static final String[] nomeColunas =
            {"Nome", "Site", "Ambiente", ""};

    protected JTable table;
    protected JScrollPane scroller;
    protected RelatorioEmpresaGeral tabela;

    public RelatorioEmpresaGeralForm(Vector<Empresa> vetorDados) {
        iniciarComponentes(vetorDados);
    }


    public void iniciarComponentes(Vector<Empresa> vetorDados) {
        tabela = new RelatorioEmpresaGeral(nomeColunas, vetorDados);
        table = new JTable();
        table.setModel(tabela);
        table.setSurrendersFocusOnKeystroke(true);
        scroller = new javax.swing.JScrollPane(table);
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(800, 300));

        TableColumn colunaEscondida = table.getColumnModel().getColumn(RelatorioEmpresaGeral.INDEX_ESCONDIDO);
        colunaEscondida.setMinWidth(2);
        colunaEscondida.setPreferredWidth(2);
        colunaEscondida.setMaxWidth(2);
        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);
    }

    public static void emitirRelatorio (List<Empresa> empresasGeral) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            JFrame frame = new JFrame("Relatório de Ambientes Geral");

            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent evt) {
                    frame.setVisible(false);
                    try {
                        Main.chamaMenuRelatorioEmpresa();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Vector<Empresa> vetorDados = new Vector<Empresa>();
            for (Empresa empresa : empresasGeral) {
                vetorDados.add(empresa);
            }

            frame.getContentPane().add(new RelatorioEmpresaGeralForm(vetorDados));
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
