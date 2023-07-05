import model.Ambiente;
import model.Categoria;
import model.Cidade;
import model.EstadoEnum;
import repository.AmbienteDAO;
import repository.CategoriaDAO;
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
            String[] opcoesMenuCadastro = {"Cidade", "Categoria","Contato","Ambiente","Empresa","Sair"};
            int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                    "Menu Cadastros",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

            switch (menuCadastro) {
                case 0: //menuCadastroCidade
                    chamaMenuCadastroCidade();
                    break;
                case 1: //menuCadastroCategoria
                    chamaMenuCadastroCategoria();
                    break;
                case 2: //menuCadastroContato
                  //  chamaMenuCadastroContato();
                    break;
                case 3: //menuCadastroAmbiente
                    chamaMenuCadastroAmbiente();
                    break;
                case 4: //menuCadastroEmpresa
                   // chamaMenuCadastroEmpresa();
                    break;
                case 5: //Sair
                    System.exit(0);
                    break;
            }
        }

        private static void chamaMenuCadastroCidade() throws SQLException, ClassNotFoundException {

            String[] opcoesMenuCadastro = {"Cadastrar", "Editar", "Remover","Voltar"};
            int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                    "Menu Cadastro Cidade",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

            switch (menuCadastro) {
                case 0: //CadastroCidade
                    chamaCadastroCidade();
                    break;
                case 1: //EditarCidade
                  //  chamaEditarCidade();
                    break;
                case 2: //RemoverCidade
                    //  chamaRemoverCidade();;
                    break;
                case 3: //Voltar
                    chamaMenuPrincipal();
                    break;
            }
        }
    private static void chamaCadastroCidade() throws SQLException, ClassNotFoundException {

        String nomeCidade = JOptionPane.showInputDialog(null, "Informe o nome da cidade:",
                "Cadastro Cidade", JOptionPane.DEFAULT_OPTION);

        Object[] opcoesUfEstado = CidadeDAO.findEstadoUFInArray();
        Object selectionUf = JOptionPane.showInputDialog(null, "Selecione a UF referente ao estado da cidade informada:",
                "Cadastro Cidade", JOptionPane.DEFAULT_OPTION, null, opcoesUfEstado, opcoesUfEstado[0]);
        List<EstadoEnum> estadoUf = CidadeDAO.buscarPorNomeEstado(selectionUf);

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
    private static void chamaMenuCadastroCategoria() throws SQLException, ClassNotFoundException {

        String[] opcoesMenuCadastro = {"Cadastrar", "Editar", "Remover","Voltar"};
        int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Cadastro Categoria",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menuCadastro) {
            case 0: //CadastroCategoria
                chamaCadastroCategoria();
                break;
            case 1: //EditarCategoria
               // chamaEditarCategoria();
                break;
            case 2: //RemoverCategoria
                //   chamaRemoverCategoria();
                break;
            case 3: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static void chamaCadastroCategoria() throws SQLException, ClassNotFoundException {

        String nomeCategoria = JOptionPane.showInputDialog(null, "Informe o nome da categoria:",
                "Cadastro Categoria", JOptionPane.DEFAULT_OPTION);

        Categoria categoria =  new Categoria(null,nomeCategoria);

        getCategoriaDAO().salvar(categoria);

        JOptionPane.showConfirmDialog(null, "Categoria cadastrada com sucesso!",
                "Cadastro Categoria", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

        chamaMenuCadastroCategoria();

    }

    private static void chamaMenuCadastroAmbiente() throws SQLException, ClassNotFoundException {

        String[] opcoesMenuCadastro = {"Cadastrar", "Editar", "Remover","Voltar"};
        int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Cadastro Categoria",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuCadastro, opcoesMenuCadastro[0]);

        switch (menuCadastro) {
            case 0: //CadastroAmbiente
                chamaCadastroAmbiente();
                break;
            case 1: //EditarAmbiente
                // chamaEditarAmbiente();
                break;
            case 2: //RemoverAmbiente
                //   chamaRemoverAmbiente();
                break;
            case 3: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static void chamaCadastroAmbiente() throws SQLException, ClassNotFoundException {

        String nomeAmbiente = JOptionPane.showInputDialog(null, "Informe o nome do ambiente de inovação:",
                "Cadastro Ambiente", JOptionPane.DEFAULT_OPTION);

        Object[] selectionValuesCategoria = getCategoriaDAO().findCategoriaInArray();
        String initialSelectionCategoria = (String) selectionValuesCategoria[0];
        Object selectionCategoria = JOptionPane.showInputDialog(null, "Selecione a categoria do ambiente de inovação:",
                "Cadastrar Ambiente", JOptionPane.DEFAULT_OPTION, null, selectionValuesCategoria, initialSelectionCategoria);
        List<Categoria> categoria = getCategoriaDAO().buscarPorNome((String) selectionCategoria);

        String cep = JOptionPane.showInputDialog(null, "Informe o CEP do ambiente de inovação:",
                "Cadastro Ambiente", JOptionPane.DEFAULT_OPTION);

        String rua = JOptionPane.showInputDialog(null, "Informe a rua do ambiente de inovação:",
                "Cadastro Ambiente", JOptionPane.DEFAULT_OPTION);

        String numero = JOptionPane.showInputDialog(null, "Informe o número do ambiente de inovação:",
                "Cadastro Ambiente", JOptionPane.DEFAULT_OPTION);

        String bairro = JOptionPane.showInputDialog(null, "Informe o bairro do ambiente de inovação:",
                "Cadastro Ambiente", JOptionPane.DEFAULT_OPTION);

        Object[] selectionValuesCidade = getCidadeDAO().findCidadeInArray();
        String initialSelectionCidade = (String) selectionValuesCidade[0];
        Object selectionCidade = JOptionPane.showInputDialog(null, "Selecione a cidade do ambiente de inovação:",
                "Cadastrar Ambiente", JOptionPane.DEFAULT_OPTION, null, selectionValuesCidade, initialSelectionCidade);
        List<Cidade> cidade = getCidadeDAO().buscarPorNome((String) selectionCidade);


        Ambiente ambiente = new Ambiente(null,nomeAmbiente,cep,rua,numero,bairro,cidade.get(0),categoria.get(0));

        getAmbienteDAO().salvar(ambiente);

        JOptionPane.showConfirmDialog(null, "Ambiente cadastrada com sucesso!",
                "Cadastro Ambiente", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

        chamaMenuPrincipal();
    }

    public static CidadeDAO getCidadeDAO() {
        CidadeDAO cidadeDAO = new CidadeDAO();
        return cidadeDAO;
    }

    public static CategoriaDAO getCategoriaDAO() {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        return categoriaDAO;
    }

    public static AmbienteDAO getAmbienteDAO() {
        AmbienteDAO ambienteDAO = new AmbienteDAO();
        return ambienteDAO;
    }
    }