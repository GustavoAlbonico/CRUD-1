package repository;

import model.Ambiente;
import model.Categoria;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AmbienteDAO implements IGenericDAO<Ambiente>{

    List<Ambiente> ambientes = new ArrayList<>();

    @Override
    public void salvar(Ambiente ambiente) {
        AmbienteRepository repository =  new AmbienteRepository();

        try {
            repository.insere(ambiente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remover(Ambiente objeto) throws SQLException, ClassNotFoundException {

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
