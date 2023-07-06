package repository;


import model.Contato;
import model.EstadoEnum;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ContatoDAO implements IGenericDAO<Contato> {

    List<Contato> contatos = new ArrayList<>();

    public Object[] findContatoInArray() throws SQLException, ClassNotFoundException {
        List<Contato> contatos1 = buscarTodos();
        List<String> contatoNomes = new ArrayList<>();

        for(Contato contato : contatos1) {
            contatoNomes.add(contato.getNome());
        }
        return contatoNomes.toArray();
    }

    @Override
    public void salvar(Contato contato) {
        ContatoRepository repository = new ContatoRepository();

        try {
            if(contato.getId() != null) {
                repository.update(contato);
            } else {
                repository.insere(contato);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remover(Contato contato) throws SQLException, ClassNotFoundException {
        ContatoRepository contatoRepository = new ContatoRepository();
        contatoRepository.delete(contato);
    }

    @Override
    public List<Contato> buscarTodos() {
        ContatoRepository contatoRepository = new ContatoRepository();
        try {
            contatos = contatoRepository.busca();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return contatos;
    }

    @Override
    public List<Contato> buscarPorNome(String nome) {
        List<Contato> contatos1 = buscarTodos();
        List<Contato> filtradas = new ArrayList<>();

        for(Contato contato : contatos1){
            if (contato.getNome().contains(nome)){
                filtradas.add(contato);
            }
        }
        return filtradas;
    }
}

