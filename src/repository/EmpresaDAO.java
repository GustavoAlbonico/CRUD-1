package repository;

import model.Empresa;

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


    @Override
    public void salvar(Empresa empresa) {
        EmpresaRepository repository = new EmpresaRepository();

        try {
            repository.insere(empresa);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
