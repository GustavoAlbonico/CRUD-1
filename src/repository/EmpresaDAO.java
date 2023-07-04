package repository;

import model.Empresa;

import java.sql.SQLException;
import java.util.List;

public class EmpresaDAO {
    public List<Empresa> buscarPorId(Integer id) throws SQLException, ClassNotFoundException {
        EmpresaRepository empresaRepository = new EmpresaRepository();
        List<Empresa> empresas1 = empresaRepository.buscaPorId(id);
        return empresas1;
    }


}
