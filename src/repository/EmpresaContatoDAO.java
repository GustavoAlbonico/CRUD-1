package repository;

import model.Ambiente;
import model.EmpresaContato;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class EmpresaContatoDAO implements IGenericDAO<EmpresaContato>{
    List<EmpresaContato> empresaContatos = new ArrayList<>();

    public Object[] findEmpesaContatosInArray(List<EmpresaContato> listaEmpresa) throws SQLException, ClassNotFoundException{
        List<EmpresaContato> empresaContatos1 = listaEmpresa;
        List<String> empresaContatoNomes = new ArrayList<>();

        for (EmpresaContato empresaContato : empresaContatos1) {
            empresaContatoNomes.add(empresaContato.getContato().getNome());
        }
        return empresaContatoNomes.toArray();
    }

    public Integer buscarPorPosicao(List<EmpresaContato> listaEmpresa,String nome) {
        List<EmpresaContato> empresaContatos = listaEmpresa;
        Integer posicaoContato = 0;

        for (int x =0 ; x < empresaContatos.size(); x ++) {
            if (empresaContatos.get(x).getContato().getNome().contains(nome)) {
                posicaoContato = x;
            }
        }
        return posicaoContato;
    }

    public List<EmpresaContato> findListaEmpesaContatosInArray(Integer id) throws SQLException, ClassNotFoundException{
        EmpresaContatoRepository empresaContatoRepository = new EmpresaContatoRepository();
        List<EmpresaContato> empresaContatos1 = empresaContatoRepository.buscaPorIdDeEmpresa(id);
        List<EmpresaContato> listaEmpresaContato = new ArrayList<>();

        for (EmpresaContato empresaContato : empresaContatos1) {
            listaEmpresaContato.add(empresaContato);
        }
        return listaEmpresaContato;
    }


    public void salvar(Integer id,List<EmpresaContato> empresaContato) {
        EmpresaContatoRepository repository = new EmpresaContatoRepository();

        try {
            repository.insere(id,empresaContato);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void salvar(EmpresaContato objeto) {

    }

    @Override
    public void remover(EmpresaContato empresaContato) throws SQLException, ClassNotFoundException {
        EmpresaContatoRepository empresaContatoRepository = new EmpresaContatoRepository();
        empresaContatoRepository.delete(empresaContato);
    }

    @Override
    public List<EmpresaContato> buscarTodos() {
        EmpresaContatoRepository empresaContatoRepository = new EmpresaContatoRepository();
        try {
            empresaContatos = empresaContatoRepository.busca();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return empresaContatos;
    }

    @Override
    public List<EmpresaContato> buscarPorNome(String nome) {
        List<EmpresaContato> empresaContatos = buscarTodos();
        List<EmpresaContato> filtradas = new ArrayList<>();

        for (EmpresaContato empresaContato : empresaContatos) {
            if (empresaContato.getContato().getNome().contains(nome)) {
                filtradas.add(empresaContato);
            }
        }
        return filtradas;
    }
}
