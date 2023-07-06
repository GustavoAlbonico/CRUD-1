import model.Ambiente;
import model.Empresa;
import relatorios.RelatorioEmpresaAmbiente;


import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class RelatorioEmpresaAmbienteForm  extends JPanel{

    private static final long serialVersionUID = 1L;

    public static final String[] nomeColunas =
            {"Nome", "Site", ""};

    protected JTable table;
    protected JScrollPane scroller;
    protected RelatorioEmpresaAmbiente tabela;

    public RelatorioEmpresaAmbienteForm(Vector<Empresa> vetorDados) {
        iniciarComponentes(vetorDados);
    }


    public void iniciarComponentes(Vector<Empresa> vetorDados) {
        tabela = new RelatorioEmpresaAmbiente(nomeColunas, vetorDados);
        table = new JTable();
        table.setModel(tabela);
        table.setSurrendersFocusOnKeystroke(true);
        scroller = new javax.swing.JScrollPane(table);
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(600, 400));

        TableColumn colunaEscondida = table.getColumnModel().getColumn(RelatorioEmpresaAmbiente.INDEX_ESCONDIDO);
        colunaEscondida.setMinWidth(2);
        colunaEscondida.setPreferredWidth(2);
        colunaEscondida.setMaxWidth(2);
        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);
    }

    public static void emitirRelatorio (List<Empresa> empresasAmbiente, Ambiente ambiente) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            JFrame frame = new JFrame("Relatório de Empresas Ambiente ("+ambiente.getNome()+")");

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
            for (Empresa empresa : empresasAmbiente) {
                vetorDados.add(empresa);
            }

            frame.getContentPane().add(new RelatorioEmpresaAmbienteForm(vetorDados));
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
