package repository;

import model.Ambiente;
import model.Categoria;

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
