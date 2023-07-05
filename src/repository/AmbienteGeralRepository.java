package repository;

import model.Ambiente;
import model.AmbienteGeral;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AmbienteGeralRepository {

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/ambientes_inovacao";
        Connection conenection = DriverManager.getConnection(url, "root", "");

        return conenection;
    }

    public List<AmbienteGeral> busca() throws SQLException, ClassNotFoundException {
        List<AmbienteGeral> ambientes = new ArrayList<>();
        Connection connection = getConnection();


        PreparedStatement stmt = connection.prepareStatement("select * from ambiente");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {

            AmbienteGeral ambienteGeral = new AmbienteGeral();
            ambienteGeral.setId(resultSet.getInt(1));
            ambienteGeral.setNome(resultSet.getString(2));
            ambienteGeral.setCep(resultSet.getString(3));
            ambienteGeral.setRua(resultSet.getString(4));
            ambienteGeral.setNumero(resultSet.getString(5));
            ambienteGeral.setBairro(resultSet.getString(6));

            CidadeRepository cidadeRepository =  new CidadeRepository();
            ambienteGeral.setCidade(cidadeRepository.buscaPorId(resultSet.getInt(7)).get(0));

            CategoriaRepository categoriaRepository = new CategoriaRepository();
            ambienteGeral.setCategoria(categoriaRepository.buscaPorId(resultSet.getInt(8)).get(0));

            EmpresaRepository empresaRepository = new EmpresaRepository();
            ambienteGeral.setListaEmpresa(empresaRepository.buscaPorIdAmbiente(resultSet.getInt(1)));

            ambientes.add(ambienteGeral);
        }

        connection.close();
        return ambientes;
    }

    public List<AmbienteGeral> buscaPorCidade(Integer id) throws SQLException, ClassNotFoundException {
        List<AmbienteGeral> ambientes = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from ambiente where cidade_id = ?;");
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {

            AmbienteGeral ambienteGeral = new AmbienteGeral();
            ambienteGeral.setId(resultSet.getInt(1));
            ambienteGeral.setNome(resultSet.getString(2));
            ambienteGeral.setCep(resultSet.getString(3));
            ambienteGeral.setRua(resultSet.getString(4));
            ambienteGeral.setNumero(resultSet.getString(5));
            ambienteGeral.setBairro(resultSet.getString(6));

            CidadeRepository cidadeRepository =  new CidadeRepository();
            ambienteGeral.setCidade(cidadeRepository.buscaPorId(resultSet.getInt(7)).get(0));

            CategoriaRepository categoriaRepository = new CategoriaRepository();
            ambienteGeral.setCategoria(categoriaRepository.buscaPorId(resultSet.getInt(8)).get(0));

            EmpresaRepository empresaRepository = new EmpresaRepository();
            ambienteGeral.setListaEmpresa(empresaRepository.buscaPorIdAmbiente(resultSet.getInt(1)));

            ambientes.add(ambienteGeral);
        }

        connection.close();
        return ambientes;
    }
}

