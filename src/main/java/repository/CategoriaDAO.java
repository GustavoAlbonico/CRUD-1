package main.java.repository;

import main.java.model.Ambiente;
import main.java.model.Categoria;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public final class CategoriaDAO implements IGenericDAO<Categoria>{

    List<Categoria> categorias = new ArrayList<>();

    public Object[] findCategoriaInArray() {
        List<Categoria> categorias1 = buscarTodos();
        List<String> categoriaNomes = new ArrayList<>();

        for (Categoria categoria : categorias1) {
            categoriaNomes.add(categoria.getNome());
        }

        return categoriaNomes.toArray();
    }

    public boolean verificaParents (Categoria categoria){
        AmbienteDAO ambienteDAO = new AmbienteDAO();
        List<Ambiente> listaAmbiente = ambienteDAO.buscarTodos();

        for (Ambiente ambiente : listaAmbiente){
            if (ambiente.getCategoria().getId().equals(categoria.getId())){
                return false;
            }
        }
        return true;
    }

    @Override
    public void salvar(Categoria categoria) {
        CategoriaRepository repository =  new CategoriaRepository();

        try {
            if (categoria.getId() != null){
                repository.update(categoria);
            }else{
                repository.insere(categoria);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void remover(Categoria categoria) throws SQLException, ClassNotFoundException {
        CategoriaRepository categoriaRepository = new CategoriaRepository();
        categoriaRepository.delete(categoria);
    }

    @Override
    public List<Categoria> buscarTodos(){
        CategoriaRepository categoriaRepository = new CategoriaRepository();
        try {
            categorias = categoriaRepository.busca();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return categorias;
    }

    @Override
    public List<Categoria> buscarPorNome(String nome) {
        List<Categoria> categorias1 = buscarTodos();
        List<Categoria> filtradas = new ArrayList<>();

        for (Categoria categoria : categorias1) {
            if (categoria.getNome().contains(nome)) {
                filtradas.add(categoria);
            }
        }
        return filtradas;
    }
}
