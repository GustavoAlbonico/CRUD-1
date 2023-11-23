package main.java.com.crud.repository;


import main.java.com.crud.model.Ambiente;
import main.java.com.crud.model.Cidade;
import main.java.com.crud.model.EstadoEnum;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class CidadeDAO implements IGenericDAO<Cidade> {

    List<Cidade> cidades = new ArrayList<>();


    public static Object[] findEstadoUFInArray() {

        List<EstadoEnum> estadoUf = new ArrayList<>();
       for (EstadoEnum estadoEnum : EstadoEnum.values()){
           estadoUf.add(estadoEnum);
       }

    return estadoUf.toArray();
    }

    public static List<EstadoEnum> buscarPorNomeEstado(Object nome) {
        List<EstadoEnum> estadoUfFiltradas = new ArrayList<>();
        for (EstadoEnum estadoEnum : EstadoEnum.values()) {
            if (estadoEnum.name().contains(nome.toString())) {
                estadoUfFiltradas.add(estadoEnum);
            }
        }
        return estadoUfFiltradas;
    }

    public boolean verificaParents (Cidade cidade){
        AmbienteDAO ambienteDAO = new AmbienteDAO();
        List<Ambiente> listaAmbiente = ambienteDAO.buscarTodos();

        for (Ambiente ambiente : listaAmbiente){
            if (ambiente.getCidade().getId().equals(cidade.getId())){
                return false;
            }
        }
        return true;
    }

    public  Object[] findCidadeQTDInArray() throws SQLException, ClassNotFoundException {
        List<Cidade> cidades1 = buscarTodos();
        List<String> cidadeNomes = new ArrayList<>();
        CidadeRepository cidadeRepository = new CidadeRepository();

        for (Cidade cidade : cidades1) {
            cidadeNomes.add(cidade.getNome()+" "+"("+cidadeRepository.buscaQtdAmbienteCidade(cidade.getId())+")");
        }
        return cidadeNomes.toArray();
    }

    public Object[] findCidadeInArray() {
        List<Cidade> cidade = buscarTodos();
        List<String> cidadeNomes = new ArrayList<>();

        for (Cidade cidade1 : cidade) {
           cidadeNomes.add(cidade1.getNome());
        }

        return cidadeNomes.toArray();
    }

    @Override
    public void salvar(Cidade cidade) {
        CidadeRepository repository =  new CidadeRepository();

        try {
            if (cidade.getId() != null){
                repository.update(cidade);
            } else {
                repository.insere(cidade);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void remover(Cidade cidade) throws SQLException, ClassNotFoundException {
        CidadeRepository cidadeRepository = new CidadeRepository();
        cidadeRepository.delete(cidade);

    }

    public List<Cidade> buscarPorNomeSemQTD(String nome) {
        List<Cidade> produtosFiltradas = new ArrayList<>();
        List<Cidade> listaCidades = buscarTodos();

        String nomeNovo = "";
        String parenteses = "(";
        Integer tamanhoString = 0;

        for (int x = 0; x < nome.length(); x++) {
            if (nome.charAt(x) == parenteses.charAt(0)) {
                break;
            }
            tamanhoString = tamanhoString + 1;
        }

        for (Cidade cidade : listaCidades) {
            for (int x = 0 ; x < cidade.getNome().length();x++) {
                if (x < cidade.getNome().length() && x < nome.length()) {
                    if (cidade.getNome().charAt(x) == nome.charAt(x)) {
                        nomeNovo = nomeNovo+nome.charAt(x);
                    }
                }
            }
            if (cidade.getNome().equals(nomeNovo) && cidade.getNome().length() == tamanhoString - 1) {
                produtosFiltradas.add(cidade);
            }
            nomeNovo = "";
        }
        return produtosFiltradas;
    }

    @Override
    public List<Cidade> buscarPorNome(String nome) {
        List<Cidade> cidade = buscarTodos();
        List<Cidade> filtradas = new ArrayList<>();

        for (Cidade cidade1 : cidade) {
            if (cidade1.getNome().contains(nome)) {
                filtradas.add(cidade1);
            }
        }
        return filtradas;
    }

    @Override
    public List<Cidade> buscarTodos() {
        CidadeRepository cidadeRepository = new CidadeRepository();
        try {
            cidades = cidadeRepository.busca();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cidades;
    }
}

