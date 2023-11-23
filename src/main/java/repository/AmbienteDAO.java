package main.java.repository;

import main.java.model.Ambiente;

import main.java.model.Empresa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class AmbienteDAO implements IGenericDAO<Ambiente>{

    List<Ambiente> ambientes = new ArrayList<>();


    public Object[] findAmbienteInArray() {
        List<Ambiente> ambientes1 = buscarTodos();
        List<String> ambientesNomes = new ArrayList<>();

        for (Ambiente ambiente : ambientes1) {
            ambientesNomes.add(ambiente.getNome());
        }

        return ambientesNomes.toArray();
    }

    public Integer buscaQtdAmbienteTotal(){
        AmbienteRepository ambienteRepository = new AmbienteRepository();
        Integer qtdAmbienteTotal = 0;
        try {
            qtdAmbienteTotal = ambienteRepository.buscaQtdAmbiente();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return qtdAmbienteTotal;
    }

    public boolean verificaParents (Ambiente ambiente){
        EmpresaDAO empresaDAO = new EmpresaDAO();
        List<Empresa> listaEmpresa = empresaDAO.buscarTodos();

        for (Empresa empresa : listaEmpresa){
            if (empresa.getAmbiente().getId().equals(ambiente.getId())){
                return false;
            }
        }
        return true;
    }

    public  Object[] findAmbienteQTDInArray() throws SQLException, ClassNotFoundException {
        List<Ambiente> ambientes1 = buscarTodos();
        List<String> cidadeNomes = new ArrayList<>();
        AmbienteRepository ambienteRepository = new AmbienteRepository();

        for (Ambiente ambiente : ambientes1) {
            cidadeNomes.add(ambiente.getNome()+" "+"("+ambienteRepository.buscaQtdEmpresaAmbiente(ambiente.getId())+")");
        }
        return cidadeNomes.toArray();
    }

    public List<Ambiente> buscarPorNomeSemQTD(String nome) {
        List<Ambiente> ambienteFiltrados = new ArrayList<>();
        List<Ambiente> listaAmbiente = buscarTodos();

        String nomeNovo = "";
        String parenteses = "(";
        Integer tamanhoString = 0;

        for (int x = 0; x < nome.length(); x++) {
            if (nome.charAt(x) == parenteses.charAt(0)) {
                break;
            }
            tamanhoString = tamanhoString + 1;
        }

        for (Ambiente ambiente : listaAmbiente) {
            for (int x = 0 ; x < ambiente.getNome().length();x++) {
                if (x < ambiente.getNome().length() && x < nome.length()) {
                    if (ambiente.getNome().charAt(x) == nome.charAt(x)) {
                        nomeNovo = nomeNovo+nome.charAt(x);
                    }
                }
            }
            if (ambiente.getNome().equals(nomeNovo) && ambiente.getNome().length() == tamanhoString - 1) {
                ambienteFiltrados.add(ambiente);
            }
            nomeNovo = "";
        }
        return ambienteFiltrados;
    }

    @Override
    public void salvar(Ambiente ambiente) {
        AmbienteRepository repository =  new AmbienteRepository();

        try {
            if (ambiente.getId() != null){
                repository.update(ambiente);
            } else {
                repository.insere(ambiente);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remover(Ambiente ambiente) throws SQLException, ClassNotFoundException {
        AmbienteRepository ambienteRepository = new AmbienteRepository();
        ambienteRepository.delete(ambiente);
    }

    @Override
    public List<Ambiente> buscarTodos(){
        AmbienteRepository ambienteRepository = new AmbienteRepository();
        try {
            ambientes = ambienteRepository.busca();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ambientes;
    }

    @Override
    public List<Ambiente> buscarPorNome(String nome) {
        List<Ambiente> ambientes1 = buscarTodos();
        List<Ambiente> filtradas = new ArrayList<>();

        for (Ambiente ambiente : ambientes1) {
            if (ambiente.getNome().contains(nome)) {
                filtradas.add(ambiente);
            }
        }
        return filtradas;
    }
}
