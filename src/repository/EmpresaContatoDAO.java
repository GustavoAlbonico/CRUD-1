package repository;

import model.EmpresaContato;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class EmpresaContatoDAO implements IGenericDAO<EmpresaContato>{
    List<EmpresaContato> empresaContatos = new ArrayList<>();

    public Object[] findEmpesaContatosInArray() throws SQLException, ClassNotFoundException{
        List<EmpresaContato> empresaContatos1 = buscarTodos();
        List<String> empresaContatoNomes = new ArrayList<>();

        for (EmpresaContato empresaContato : empresaContatos1) {
            empresaContatoNomes.add(empresaContato.getDescricao());
        }
        return empresaContatoNomes.toArray();
    }

    public void salvar(EmpresaContato empresaContato) {
        EmpresaContatoRepository repository = new EmpresaContatoRepository();

        try {
            repository.insere(empresaContato);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
        return null;
    }

    //Necessário? Buscar por perfil? Nome -> interface
//    public List<EmpresaContato> buscarPorNome(EmpresaContato descricao){
//        List<EmpresaContato> empresaContatos1 = buscarTodos();
//        List<EmpresaContato> filtradas = new ArrayList<>();
//
//        for(EmpresaContato empresaContato : empresaContatos1) {
//            if (empresaContato.getDescricao().contains((CharSequence) descricao)){
//                filtradas.add(descricao);
//            }
//        }
//        return filtradas;
//    }
}
