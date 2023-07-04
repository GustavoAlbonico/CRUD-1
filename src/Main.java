import model.Cidade;
import model.EstadoEnum;
import repository.CidadeDAO;

import javax.swing.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        chamaMenuPrincipal();
    }

    private static void chamaMenuPrincipal() {
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

        private static void chamaMenuCadastros() {
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

        private static void chamaMenuCadastroCidade() {

            String[] opcoesMenuCadastro = {"Cadastrar", "Remover", "Editar","Voltar"};
            int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                    "Menu Cadastro Cidade",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

            switch (menuCadastro) {
                case 0: //CadastroCidade
                    chamaCadastroCidade();
                    break;
                case 1: //RemoverCidade
                    //   chamaRemoverCidade();
                    break;
                case 2: //EditarCidade
                    //   chamaEditarCidade();
                    break;
                case 3: //Voltar
                    chamaMenuCadastros();
                    break;
            }
        }
    private static void chamaCadastroCidade() {

        String nomeCidade = JOptionPane.showInputDialog(null, "Informe o nome da cidade:",
                "Cadastro Cidade", JOptionPane.DEFAULT_OPTION);

        Object[] opcoesUfEstado = CidadeDAO.findEstadoUFInArray();
        Object selectionUf = JOptionPane.showInputDialog(null, "Selecione a UF referente ao estado da cidade informada:",
                "Cadastrar Compra", JOptionPane.DEFAULT_OPTION, null, opcoesUfEstado, opcoesUfEstado[0]);
        List<EstadoEnum> estadoUf = CidadeDAO.buscarPorNome(selectionUf);

        Cidade cidade = new Cidade(null,nomeCidade,estadoUf.get(0));

    }

    }