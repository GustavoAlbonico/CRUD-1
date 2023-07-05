package repository;

import com.mysql.cj.xdevapi.Client;
import model.Cidade;
import model.EstadoEnum;

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
            repository.insere(cidade);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void remover(Cidade cidade) throws SQLException, ClassNotFoundException {
        CidadeRepository cidadeRepository = new CidadeRepository();
        cidadeRepository.delete(cidade);

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

