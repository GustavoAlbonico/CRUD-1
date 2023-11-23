package main.java.com.crud.repository;


import main.java.com.crud.model.Empresa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class EmpresaDAO implements IGenericDAO<Empresa> {

    List<Empresa> empresas = new ArrayList<>();

    public Object[] findEmpresaInArray() throws SQLException, ClassNotFoundException{
        List<Empresa> empresas1 = buscarTodos();
        List<String> empresaNomes = new ArrayList<>();

        for (Empresa empresa : empresas1) {
            empresaNomes.add(empresa.getNome());
        }
        return empresaNomes.toArray();
    }


    public List<Empresa> buscarPorId(Integer id) throws SQLException, ClassNotFoundException {
        EmpresaRepository empresaRepository = new EmpresaRepository();
        List<Empresa> empresas1 = empresaRepository.buscaPorId(id);
        return empresas1;
    }


    public Integer buscaQtdEmpresaTotal(){
        EmpresaRepository empresaRepository = new EmpresaRepository();
        Integer qtdEmpresaTotal = 0;
        try {
            qtdEmpresaTotal = empresaRepository.buscaQtdEmpresa();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return qtdEmpresaTotal;
    }

    public List<Empresa> buscarTodosPorAmbiente(Integer id){

        EmpresaRepository empresaRepository = new EmpresaRepository();
        try {
            empresas = empresaRepository.buscaPorAmbiente(id);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return empresas;
    }


    @Override
    public void salvar(Empresa empresa) {
        EmpresaRepository repository = new EmpresaRepository();

        try {
            if(empresa.getId() != null) {
                repository.update(empresa);
            } else {
                repository.insere(empresa);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verificaNomeCadastro (String nome){
        List<Empresa> listaEmpresa = buscarTodos();

        for (Empresa empresa : listaEmpresa){
            if (empresa.getNome().equals(nome)){
                return false;
            }
        }
        return true;
    }

    public boolean verificaNomeEdicao (String nome,Empresa empresaEdit){
        List<Empresa> listaEmpresa = buscarTodos();

        for (int x = 0; x < listaEmpresa.size();x++){
            if (listaEmpresa.get(x).getNome().equals(empresaEdit.getNome())){
                listaEmpresa.remove(listaEmpresa.get(x));
            }
        }

        for (Empresa empresa1 : listaEmpresa){
            if (empresa1.getNome().equals(nome)){
                return false;
            }
        }
        return true;
    }

    @Override
    public void remover(Empresa empresa) throws SQLException, ClassNotFoundException {
        EmpresaRepository empresaRepository = new EmpresaRepository();
        empresaRepository.delete(empresa);
    }

    @Override
    public List<Empresa> buscarTodos() {
        EmpresaRepository empresaRepository = new EmpresaRepository();

        try {
            empresas = empresaRepository.busca();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return empresas;
    }

    @Override
    public List<Empresa> buscarPorNome(String nome) {
        List<Empresa> empresas1 = buscarTodos();
        List<Empresa> filtradas = new ArrayList<>();

        for(Empresa empresa : empresas1) {
            if(empresa.getNome().contains(nome)){
                filtradas.add(empresa);
            }
        }
        return filtradas;
    }
}
