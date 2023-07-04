import model.Cidade;
import model.EstadoEnum;
import repository.CidadeDAO;
import repository.CidadeRepository;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        chamaMenuPrincipal();
    }

    private static void chamaMenuPrincipal() throws SQLException, ClassNotFoundException {
        String[] opcoesMenuPrincipal = {"Cadastros", "Relatórios", "Sair"};
        int opcaoMenuPrincipal = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Principal",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuPrincipal, opcoesMenuPrincipal[0]);
        switch (opcaoMenuPrincipal) {
            case 0: //Cdastros
                chamaMenuCadastros();
                break;
            case 1: //Relatórios
               // chamaMenuRelatórios();
                break;
            case 2://Sair
                System.exit(0);
                break;
        }
    }

        private static void chamaMenuCadastros() throws SQLException, ClassNotFoundException {
            String[] opcoesMenuCadastro = {"Cidade", "Categoria", "Ambiente","Empresa","Voltar"};
            int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                    "Menu Cadastros",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

            switch (menuCadastro) {
                case 0: //menuCadastroCidade
                    chamaMenuCadastroCidade();
                    break;
                case 1: //menuCadastroCategoria
                 //   chamaMenuCadastroCategoria();
                    break;
                case 2: //menuCadastroAmbiente
                 //   chamaMenuCadastroAmbiente();
                    break;
                case 3: //menuCadastroEmpresa
                   // chamaMenuCadastroEmpresa();
                    break;
                case 4: //Voltar
                    chamaMenuPrincipal();
                    break;
            }
        }

        private static void chamaMenuCadastroCidade() throws SQLException, ClassNotFoundException {

            String[] opcoesMenuCadastro = {"Cadastrar", "Remover", "Editar","Voltar"};
            int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                    "Menu Cadastro Cidade",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

            switch (menuCadastro) {
                case 0: //CadastroCidade
                    chamaCadastroCidade();
                    break;
                case 1: //RemoverCidade
                    chamaRemoverCidade();
                    break;
                case 2: //EditarCidade
                    //   chamaEditarCidade();
                    break;
                case 3: //Voltar
                    chamaMenuCadastros();
                    break;
            }
        }
    private static void chamaCadastroCidade() throws SQLException, ClassNotFoundException {

        String nomeCidade = JOptionPane.showInputDialog(null, "Informe o nome da cidade:",
                "Cadastro Cidade", JOptionPane.DEFAULT_OPTION);

        Object[] opcoesUfEstado = CidadeDAO.findEstadoUFInArray();
        Object selectionUf = JOptionPane.showInputDialog(null, "Selecione a UF referente ao estado da cidade informada:",
                "Cadastro Cidade", JOptionPane.DEFAULT_OPTION, null, opcoesUfEstado, opcoesUfEstado[0]);
        List<EstadoEnum> estadoUf = CidadeDAO.buscarPorNome(selectionUf);

        Cidade cidade = new Cidade(null,nomeCidade,estadoUf.get(0));

        getCidadeDAO().salvar(cidade);

        JOptionPane.showConfirmDialog(null, "Cidade cadastrada com sucesso!",
                "Cadastro Cidade", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

        chamaMenuCadastroCidade();

    }
    private  static void chamaRemoverCidade() throws SQLException, ClassNotFoundException{

        Object[] selectionValuesCidade = getCidadeDAO().findCidadeInArray();
        String initialSelectionCidade = (String) selectionValuesCidade[0];
        Object selectionCidade = JOptionPane.showInputDialog(null, "Selecione a cidade que deseja remover:",
                "Remover Cidade", JOptionPane.DEFAULT_OPTION, null, selectionValuesCidade, initialSelectionCidade);
        List<Cidade> cidades = getCidadeDAO().buscarPorNome((String) selectionCidade);

        getCidadeDAO().remover(cidades.get(0));

        JOptionPane.showConfirmDialog(null, "Cidade removida com sucesso!",
                "Remover Cidade", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

        chamaMenuCadastroCidade();

    }

    public static CidadeDAO getCidadeDAO() {
        CidadeDAO cidadeDAO = new CidadeDAO();
        return cidadeDAO;
    }


    }