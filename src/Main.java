import model.*;
import repository.*;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        chamaMenuPrincipal();
    }

        private static void chamaMenuPrincipal() throws SQLException, ClassNotFoundException {
            String[] opcoesMenuCadastro = {"Cidade", "Categoria","Contato","Ambiente","Empresa","Relatórios","Sair"};
            int menuCadastro = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                    "Menu Principal",
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
                case 5: //menuRelatorios
                    chamaMenuRelatorio();
                    break;
                case 6: //Sair
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
                    chamaEditarCidade();
                    break;
                case 2: //RemoverCidade
                    chamaRemoverCidade();
                    break;
                case 3: //Voltar
                    chamaMenuPrincipal();
                    break;
            }
        }
    private static void chamaCadastroCidade() throws SQLException, ClassNotFoundException {

        try {

        String nomeCidade = JOptionPane.showInputDialog(null, "Informe o nome da cidade:",
                "Cadastro Cidade", JOptionPane.DEFAULT_OPTION);

        if (nomeCidade.isEmpty()) {
            JOptionPane.showConfirmDialog(null, "ERRO!",
                    "Cadastrar Receita", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            chamaCadastroCidade();
            return;
        }

        Object[] opcoesUfEstado = CidadeDAO.findEstadoUFInArray();
        Object selectionUf = JOptionPane.showInputDialog(null, "Selecione a UF referente ao estado da cidade informada:",
                "Cadastro Cidade", JOptionPane.DEFAULT_OPTION, null, opcoesUfEstado, opcoesUfEstado[0]);
        List<EstadoEnum> estadoUf = CidadeDAO.buscarPorNomeEstado(selectionUf);

        Cidade cidade = new Cidade(null,nomeCidade,estadoUf.get(0));

        getCidadeDAO().salvar(cidade);

        JOptionPane.showConfirmDialog(null, "Cidade cadastrada com sucesso!",
                "Cadastro Cidade", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

        chamaMenuCadastroCidade();

        } catch (NullPointerException e) {
            chamaMenuCadastroCidade();
        }

    }

    private static void chamaEditarCidade() throws SQLException, ClassNotFoundException {

        if (getCidadeDAO().findCidadeInArray().length < 1) {
            JOptionPane.showConfirmDialog(null, "Não existe cidade cadastrada !!!",
                    "Editar Cidade", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
            chamaMenuCadastroCidade();
            return;
        }

        try {


        Object[] selectionValuesCidade = getCidadeDAO().findCidadeInArray();
        String initialSelectionCidade = (String) selectionValuesCidade[0];
        Object selectionCidade = JOptionPane.showInputDialog(null, "Selecione a cidade que deseja editar:",
                "Editar Cidade", JOptionPane.DEFAULT_OPTION, null, selectionValuesCidade, initialSelectionCidade);
        List<Cidade> cidadesEdit = getCidadeDAO().buscarPorNome((String) selectionCidade);

        Object nomeCidade = JOptionPane.showInputDialog(null, "Informe o nome da cidade:",
                "Editar Cidade", JOptionPane.DEFAULT_OPTION, null, null, cidadesEdit.get(0).getNome());

        if (nomeCidade.toString().isEmpty()) {
            JOptionPane.showConfirmDialog(null, "ERRO!",
                    "Editar Cidade", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            chamaEditarCidade();
            return;
        }

        Object[] opcoesUfEstado = CidadeDAO.findEstadoUFInArray();
        Object selectionUf = JOptionPane.showInputDialog(null, "Selecione a UF referente ao estado da cidade informada:",
                "Editar Cidade", JOptionPane.DEFAULT_OPTION, null, opcoesUfEstado, cidadesEdit.get(0).getUF());
        List<EstadoEnum> estadoUf = CidadeDAO.buscarPorNomeEstado(selectionUf);

        Cidade cidade = new Cidade(cidadesEdit.get(0).getId(),nomeCidade.toString(),estadoUf.get(0));

        getCidadeDAO().salvar(cidade);

        JOptionPane.showConfirmDialog(null, "Cidade editada com sucesso!",
                "Editar Cidade", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

        chamaMenuCadastroCidade();

        } catch (NullPointerException e) {
            chamaMenuCadastroCidade();
        }
    }

    private  static void chamaRemoverCidade() throws SQLException, ClassNotFoundException{

        if (getCidadeDAO().findCidadeInArray().length < 1) {
            JOptionPane.showConfirmDialog(null, "Não existe cidade cadastrada !!!",
                    "Editar Cidade", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
            chamaMenuCadastroCidade();
            return;
        }

        try {

        Object[] selectionValuesCidade = getCidadeDAO().findCidadeInArray();
        String initialSelectionCidade = (String) selectionValuesCidade[0];
        Object selectionCidade = JOptionPane.showInputDialog(null, "Selecione a cidade que deseja remover:",
                "Remover Cidade", JOptionPane.DEFAULT_OPTION, null, selectionValuesCidade, initialSelectionCidade);
        List<Cidade> cidades = getCidadeDAO().buscarPorNome((String) selectionCidade);

        if (getCidadeDAO().verificaParents(cidades.get(0)) == false){
            JOptionPane.showConfirmDialog(null, "Não é possivel remover uma cidade que pertence a um ambiente !!!",
                    "Remover Cidade", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
            chamaMenuCadastroCidade();
            return;
        }

        getCidadeDAO().remover(cidades.get(0));

        JOptionPane.showConfirmDialog(null, "Cidade removida com sucesso!",
                "Remover Cidade", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

        chamaMenuCadastroCidade();

        } catch (NullPointerException e) {
            chamaMenuCadastroCidade();
        }

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
                chamaEditarCategoria();
                break;
            case 2: //RemoverCategoria
                   chamaRemoverCategoria();
                break;
            case 3: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static void chamaCadastroCategoria() throws SQLException, ClassNotFoundException {

        try {

        String nomeCategoria = JOptionPane.showInputDialog(null, "Informe o nome da categoria:",
                "Cadastro Categoria", JOptionPane.DEFAULT_OPTION);

        if (nomeCategoria.isEmpty()) {
            JOptionPane.showConfirmDialog(null, "ERRO!",
                    "Cadastrar Categoria", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            chamaCadastroCategoria();
            return;
        }

        Categoria categoria =  new Categoria(null,nomeCategoria);

        getCategoriaDAO().salvar(categoria);

        JOptionPane.showConfirmDialog(null, "Categoria cadastrada com sucesso!",
                "Cadastro Categoria", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

        chamaMenuCadastroCategoria();

        } catch (NullPointerException e) {
            chamaMenuCadastroCategoria();
    }

    }

    private static void chamaEditarCategoria() throws SQLException, ClassNotFoundException {

        if (getCategoriaDAO().findCategoriaInArray().length < 1) {
            JOptionPane.showConfirmDialog(null, "Não existe categoria cadastrada !!!",
                    "Editar Categoria", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
            chamaMenuCadastroCategoria();
            return;
        }

        try {

        Object[] selectionValuesCategoria = getCategoriaDAO().findCategoriaInArray();
        String initialSelectionCategoria = (String) selectionValuesCategoria[0];
        Object selectionCategoria = JOptionPane.showInputDialog(null, "Selecione a categoria que deseja editar:",
                "Editar Categoria", JOptionPane.DEFAULT_OPTION, null, selectionValuesCategoria, initialSelectionCategoria);
        List<Categoria> categoriaEdit = getCategoriaDAO().buscarPorNome((String) selectionCategoria);

        Object nomeCategoria = JOptionPane.showInputDialog(null, "Informe o nome da categoria:",
                "Editar Categoria", JOptionPane.DEFAULT_OPTION, null, null, categoriaEdit.get(0).getNome());

        if (nomeCategoria.toString().isEmpty()) {
            JOptionPane.showConfirmDialog(null, "ERRO!",
                    "Editar Categoria", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            chamaEditarCategoria();
            return;
        }

        Categoria categoria = new Categoria(categoriaEdit.get(0).getId(),nomeCategoria.toString());

        getCategoriaDAO().salvar(categoria);

        JOptionPane.showConfirmDialog(null, "Categoria editada com sucesso!",
                "Editar Categoria", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

        chamaMenuCadastroCategoria();

        } catch (NullPointerException e) {
        chamaMenuCadastroCategoria();
    }
    }

    private static void chamaRemoverCategoria() throws SQLException, ClassNotFoundException {

        if (getCategoriaDAO().findCategoriaInArray().length < 1) {
            JOptionPane.showConfirmDialog(null, "Não existe categoria cadastrada !!!",
                    "Remover Categoria", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
            chamaMenuCadastroCategoria();
            return;
        }
        try {

        Object[] selectionValuesCategoria = getCategoriaDAO().findCategoriaInArray();
        String initialSelectionCategoria = (String) selectionValuesCategoria[0];
        Object selectionCategoria = JOptionPane.showInputDialog(null, "Selecione a categoria que deseja remover:",
                "Remover Categoria", JOptionPane.DEFAULT_OPTION, null, selectionValuesCategoria, initialSelectionCategoria);
        List<Categoria> categoria = getCategoriaDAO().buscarPorNome((String) selectionCategoria);

        if (getCategoriaDAO().verificaParents(categoria.get(0)) == false){
            JOptionPane.showConfirmDialog(null, "Não é possivel remover uma categoria que pertence a um ambiente !!!",
                    "Remover Cidade", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
            chamaMenuCadastroCategoria();
            return;
        }

        getCategoriaDAO().remover(categoria.get(0));

        JOptionPane.showConfirmDialog(null, "Categoria removida com sucesso!",
                "Remover Categoria", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

        chamaMenuCadastroCategoria();

    } catch (NullPointerException e) {
        chamaMenuCadastroCategoria();
    }

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
                chamaEditarAmbiente();
                break;
            case 2: //RemoverAmbiente
                chamaRemoverAmbiente();
                break;
            case 3: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    private static void chamaCadastroAmbiente() throws SQLException, ClassNotFoundException {

        try {

        String nomeAmbiente = JOptionPane.showInputDialog(null, "Informe o nome do ambiente de inovação:",
                "Cadastro Ambiente", JOptionPane.DEFAULT_OPTION);

        if (nomeAmbiente.isEmpty()) {
            JOptionPane.showConfirmDialog(null, "ERRO!",
                    "Cadastro Ambiente", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            chamaCadastroAmbiente();
            return;
        }

        if (getCategoriaDAO().findCategoriaInArray().length < 1) {
            JOptionPane.showConfirmDialog(null, "Não existe categoria cadastrada !!!",
                    "Cadastro Ambiente", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
            chamaMenuCadastroAmbiente();
            return;
        }

        Object[] selectionValuesCategoria = getCategoriaDAO().findCategoriaInArray();
        String initialSelectionCategoria = (String) selectionValuesCategoria[0];
        Object selectionCategoria = JOptionPane.showInputDialog(null, "Selecione a categoria do ambiente de inovação:",
                "Cadastro Ambiente", JOptionPane.DEFAULT_OPTION, null, selectionValuesCategoria, initialSelectionCategoria);
        List<Categoria> categoria = getCategoriaDAO().buscarPorNome((String) selectionCategoria);

        String cep = JOptionPane.showInputDialog(null, "Informe o CEP do ambiente de inovação:",
                "Cadastro Ambiente", JOptionPane.DEFAULT_OPTION);

        if (cep.isEmpty()) {
            JOptionPane.showConfirmDialog(null, "ERRO!",
                    "Cadastro Ambiente", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            chamaCadastroAmbiente();
            return;
        }

        String rua = JOptionPane.showInputDialog(null, "Informe a rua do ambiente de inovação:",
                "Cadastro Ambiente", JOptionPane.DEFAULT_OPTION);

        if (rua.isEmpty()) {
            JOptionPane.showConfirmDialog(null, "ERRO!",
                    "Cadastro Ambiente", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            chamaCadastroAmbiente();
            return;
        }

        String numero = JOptionPane.showInputDialog(null, "Informe o número do ambiente de inovação:",
                "Cadastro Ambiente", JOptionPane.DEFAULT_OPTION);

        String bairro = JOptionPane.showInputDialog(null, "Informe o bairro do ambiente de inovação:",
                "Cadastro Ambiente", JOptionPane.DEFAULT_OPTION);

        if (bairro.isEmpty()) {
            JOptionPane.showConfirmDialog(null, "ERRO!",
                    "Cadastro Ambiente", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            chamaCadastroAmbiente();
            return;
        }

        if (getCidadeDAO().findCidadeInArray().length < 1) {
            JOptionPane.showConfirmDialog(null, "Não existe cidade cadastrada !!!",
                    "Cadastro Ambiente", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
            chamaMenuCadastroAmbiente();
            return;
        }

        Object[] selectionValuesCidade = getCidadeDAO().findCidadeInArray();
        String initialSelectionCidade = (String) selectionValuesCidade[0];
        Object selectionCidade = JOptionPane.showInputDialog(null, "Selecione a cidade do ambiente de inovação:",
                "Cadastrar Ambiente", JOptionPane.DEFAULT_OPTION, null, selectionValuesCidade, initialSelectionCidade);
        List<Cidade> cidade = getCidadeDAO().buscarPorNome((String) selectionCidade);

        if (numero.isEmpty()){

            Ambiente ambiente = new Ambiente(null,nomeAmbiente,cep,rua,bairro,cidade.get(0),categoria.get(0));

            getAmbienteDAO().salvar(ambiente);

            JOptionPane.showConfirmDialog(null, "Ambiente cadastrada com sucesso!",
                    "Cadastro Ambiente", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

            chamaMenuCadastroAmbiente();

        } else {


        Ambiente ambiente = new Ambiente(null,nomeAmbiente,cep,rua,numero,bairro,cidade.get(0),categoria.get(0));

        getAmbienteDAO().salvar(ambiente);

        JOptionPane.showConfirmDialog(null, "Ambiente cadastrada com sucesso!",
                "Cadastro Ambiente", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

        chamaMenuCadastroAmbiente();
        }

        } catch (NullPointerException e) {
        chamaMenuCadastroAmbiente();
    }
    }

    private static void chamaEditarAmbiente() throws SQLException, ClassNotFoundException {

        if (getAmbienteDAO().findAmbienteInArray().length < 1) {
            JOptionPane.showConfirmDialog(null, "Não existe ambiente cadastrado !!!",
                    "Editar Ambiente", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
            chamaMenuCadastroAmbiente();
            return;
        }
        try{

        Object[] selectionValuesAmbiente = getAmbienteDAO().findAmbienteInArray();
        String initialSelectionAmbiente = (String) selectionValuesAmbiente[0];
        Object selectionAmbiente = JOptionPane.showInputDialog(null, "Selecione o ambiente que deseja editar:",
                "Editar Ambiente", JOptionPane.DEFAULT_OPTION, null, selectionValuesAmbiente, initialSelectionAmbiente);
        List<Ambiente> ambienteEdit = getAmbienteDAO().buscarPorNome((String) selectionAmbiente);

        Object nomeAmbiente = JOptionPane.showInputDialog(null, "Informe o nome do ambiente de inovação:",
                "Editar Ambiente", JOptionPane.DEFAULT_OPTION, null, null, ambienteEdit.get(0).getNome());

        if (nomeAmbiente.toString().isEmpty()) {
            JOptionPane.showConfirmDialog(null, "ERRO!",
                    "Editar Ambiente", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            chamaEditarAmbiente();
            return;
        }

        if (getCategoriaDAO().findCategoriaInArray().length < 1) {
            JOptionPane.showConfirmDialog(null, "Não existe categoria cadastrada !!!",
                    "Editar Ambiente", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
            chamaMenuCadastroAmbiente();
            return;
        }

        Object[] selectionValuesCategoria = getCategoriaDAO().findCategoriaInArray();
        Object selectionCategoria = JOptionPane.showInputDialog(null, "Selecione a categoria do ambiente de inovação:",
                "Editar Ambiente", JOptionPane.DEFAULT_OPTION, null, selectionValuesCategoria, ambienteEdit.get(0).getCategoria());
        List<Categoria> categoria = getCategoriaDAO().buscarPorNome((String) selectionCategoria);

        Object cep = JOptionPane.showInputDialog(null, "Informe o cep do ambiente de inovação:",
                "Editar Ambiente", JOptionPane.DEFAULT_OPTION, null, null, ambienteEdit.get(0).getCep());

        if (cep.toString().isEmpty()) {
            JOptionPane.showConfirmDialog(null, "ERRO!",
                    "Editar Ambiente", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            chamaEditarAmbiente();
            return;
        }

        Object rua = JOptionPane.showInputDialog(null, "Informe a rua do ambiente de inovação:",
                "Editar Ambiente", JOptionPane.DEFAULT_OPTION, null, null, ambienteEdit.get(0).getRua());

        if (rua.toString().isEmpty()) {
            JOptionPane.showConfirmDialog(null, "ERRO!",
                    "Editar Ambiente", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            chamaEditarAmbiente();
            return;
        }

        Object numero = JOptionPane.showInputDialog(null, "Informe o numero do ambiente de inovação:",
                "Editar Ambiente", JOptionPane.DEFAULT_OPTION, null, null, ambienteEdit.get(0).getNumero());

        Object bairro = JOptionPane.showInputDialog(null, "Informe o bairro do ambiente de inovação:",
                "Editar Ambiente", JOptionPane.DEFAULT_OPTION, null, null, ambienteEdit.get(0).getBairro());

        if (bairro.toString().isEmpty()) {
            JOptionPane.showConfirmDialog(null, "ERRO!",
                    "Editar Ambiente", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            chamaEditarAmbiente();
            return;
        }

        if (getCidadeDAO().findCidadeInArray().length < 1) {
            JOptionPane.showConfirmDialog(null, "Não existe cidade cadastrada !!!",
                    "Editar Ambiente", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
            chamaMenuCadastroAmbiente();
            return;
        }

        Object[] selectionValuesCidade = getCidadeDAO().findCidadeInArray();
        Object selectionCidade = JOptionPane.showInputDialog(null, "Selecione a cidade do ambiente de inovação:",
                "Editar Ambiente", JOptionPane.DEFAULT_OPTION, null, selectionValuesCidade, ambienteEdit.get(0).getCidade());
        List<Cidade> cidade = getCidadeDAO().buscarPorNome((String) selectionCidade);

        if (numero.toString().isEmpty()){

            Ambiente ambiente = new Ambiente(ambienteEdit.get(0).getId(), nomeAmbiente.toString(), cep.toString(), rua.toString(),
                    bairro.toString(), cidade.get(0), categoria.get(0));

            getAmbienteDAO().salvar(ambiente);

            JOptionPane.showConfirmDialog(null, "Ambiente editado com sucesso!",
                    "Editar Ambiente", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

            chamaMenuCadastroAmbiente();

        }else {
            Ambiente ambiente = new Ambiente(ambienteEdit.get(0).getId(), nomeAmbiente.toString(), cep.toString(), rua.toString(), numero.toString(),
                    bairro.toString(), cidade.get(0), categoria.get(0));

            getAmbienteDAO().salvar(ambiente);

            JOptionPane.showConfirmDialog(null, "Ambiente editado com sucesso!",
                    "Editar Ambiente", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

            chamaMenuCadastroAmbiente();

        }

        } catch (NullPointerException e) {
            chamaMenuCadastroAmbiente();
        }

    }
    private static void chamaRemoverAmbiente() throws SQLException, ClassNotFoundException {

        if (getAmbienteDAO().findAmbienteInArray().length < 1) {
            JOptionPane.showConfirmDialog(null, "Não existe ambiente cadastrado !!!",
                    "Remover Ambiente", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
            chamaMenuCadastroAmbiente();
            return;
        }

        try {

        Object[] selectionValuesAmbiente = getAmbienteDAO().findAmbienteInArray();
        String initialSelectionAmbiente = (String) selectionValuesAmbiente[0];
        Object selectionAmbiente = JOptionPane.showInputDialog(null, "Selecione o ambiente que deseja remover:",
                "Remover Ambiente", JOptionPane.DEFAULT_OPTION, null, selectionValuesAmbiente, initialSelectionAmbiente);
        List<Ambiente> ambiente = getAmbienteDAO().buscarPorNome((String) selectionAmbiente);

            if (getAmbienteDAO().verificaParents(ambiente.get(0)) == false){
                JOptionPane.showConfirmDialog(null, "Não é possivel remover um ambiente que tenha empresa cadastrada!!!",
                        "Remover Ambeinte", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
                chamaMenuCadastroAmbiente();
                return;
            }

        getAmbienteDAO().remover(ambiente.get(0));

        JOptionPane.showConfirmDialog(null, "Ambiente removido com sucesso!",
                "Remover Ambiente", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);

        chamaMenuCadastroAmbiente();

        } catch (NullPointerException e) {
        chamaMenuCadastroAmbiente();
    }

    }

    private static void chamaMenuRelatorio() throws SQLException, ClassNotFoundException {

        String[] opcoesMenuRelatorios = {"Ambiente", "Empresa","Voltar"};
        int menuRelatorios = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Relatórios",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuRelatorios, opcoesMenuRelatorios[0]);

        switch (menuRelatorios) {
            case 0: //chamaMenuRelatoriosAmbiente
                chamaMenuRelatoriosAmbiente();
                break;
            case 1: //chamaRelatorioEmpresa
              //  chamaRelatorioEmpresa();
                break;
            case 2: //Voltar
                chamaMenuPrincipal();
                break;
        }
    }

    public static void chamaMenuRelatoriosAmbiente() throws SQLException, ClassNotFoundException {

        String[] opcoesMenuRelatoriosAmbiente = {"Geral", "Cidade","Voltar"};
        int menuRelatoriosAmbiente = JOptionPane.showOptionDialog(null, "Escolha uma opção:",
                "Menu Relatórios Ambiente ("+getAmbienteDAO().buscaQtdAmbienteTotal()+")",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesMenuRelatoriosAmbiente, opcoesMenuRelatoriosAmbiente[0]);

        switch (menuRelatoriosAmbiente) {
            case 0: //RelatorioGeralAmbiente
                     chamaRelatorioGeralAmbiente();
                break;
            case 1: //RelatorioCidadeAmbiente
                     chamaRelatorioCidadeAmbiente();
                break;
            case 2: //Voltar
                chamaMenuRelatorio();
                break;
        }
    }

    private static void chamaRelatorioGeralAmbiente() throws SQLException, ClassNotFoundException {

        if (getAmbienteGeralDAO().buscarTodos().size() < 1) {
            JOptionPane.showConfirmDialog(null, "Não foi realizada nenhum cadastro de ambiente até o momento !!!",
                    "Relatório Compras", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
            chamaMenuRelatoriosAmbiente();
            return;
        }

        List<AmbienteGeral> ambienteGerals = getAmbienteGeralDAO().buscarTodos();
        RelatorioAmbienteGeralForm.emitirRelatorio(ambienteGerals);
    }

    private static void chamaRelatorioCidadeAmbiente() throws SQLException, ClassNotFoundException {

        if (getCidadeDAO().findCidadeQTDInArray().length < 1) {
            JOptionPane.showConfirmDialog(null, "Não existe cidade cadastrada !!!",
                    "Menu Relatorio Ambiente Cidade", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
            chamaMenuRelatoriosAmbiente();
            return;
        }
        try {

        Object[] selectionValuesCidade = getCidadeDAO().findCidadeQTDInArray();
        String initialSelectionCidade = (String) selectionValuesCidade[0];
        Object selectionCidade = JOptionPane.showInputDialog(null, "Selecione a cidade que deseja visualizar relatorio:",
                "Menu Relatorio Ambiente Cidade", JOptionPane.DEFAULT_OPTION, null, selectionValuesCidade, initialSelectionCidade);
        List<Cidade> cidades = getCidadeDAO().buscarPorNomeSemQTD((String) selectionCidade);

        if (getAmbienteGeralDAO().buscarTodosPorCidade(cidades.get(0).getId()).size() < 1) {
            JOptionPane.showConfirmDialog(null, "Não foi realizada nenhum cadastro de ambiente nessa cidade até o momento !!!",
                    "Menu Relatorio Ambiente Cidade", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null);
            chamaMenuRelatoriosAmbiente();
            return;
        }

        List<AmbienteGeral> ambienteCidade = getAmbienteGeralDAO().buscarTodosPorCidade(cidades.get(0).getId());
        RelatorioAmbienteCidadeForm.emitirRelatorio(ambienteCidade,cidades.get(0));

        } catch (NullPointerException e) {
            chamaMenuRelatoriosAmbiente();
        }
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

    public static AmbienteGeralDAO getAmbienteGeralDAO() {
        AmbienteGeralDAO ambienteGeralDAO = new AmbienteGeralDAO();
        return ambienteGeralDAO;
    }
}

