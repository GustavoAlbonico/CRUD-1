package repository;

import model.Contato;
import model.Empresa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaRepository {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/ambientes_inovacao";
        Connection connection = DriverManager.getConnection(url, "root", "");

        return connection;
    }

    public void insere (Empresa empresa) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("insert into empresa values (?, ?, ?, ?)");
        stmt.setInt(1, empresa.getId().intValue());
        stmt.setString(2, empresa.getNome());
        stmt.setString(3, empresa.getLogo());
        stmt.setInt(4, empresa.getAmbiente().getId().intValue());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas inseridas");
        connection.close();
    }

    public List<Empresa> buscaPorId(Integer id) throws SQLException, ClassNotFoundException {
        List<Empresa> empresas = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from empresa where id = ?");
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            Empresa empresa = new Empresa();
            empresa.setId(resultSet.getInt(1));
            empresa.setNome(resultSet.getString(2));
            empresa.setLogo(resultSet.getString(3));

            //FK ambientes
            AmbienteRepository ambienteRepository = new AmbienteRepository();
            //empresa.setAmbiente(ambienteRepository.buscaPorId(resultSet.getInt(4)).get(0));

            empresas.add(empresa);
        }
        connection.close();
        return empresas;
    }

    public List<Empresa> busca() throws SQLException, ClassNotFoundException {
        List<Empresa> empresas = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from empresa");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            Empresa empresa = new Empresa();
            empresa.setId(resultSet.getInt(1));
            empresa.setNome(resultSet.getString(2));
            empresa.setLogo(resultSet.getString(3));

            AmbienteRepository ambienteRepository = new AmbienteRepository();
            //empresa.setAmbiente(ambienteRepository.buscaPorId(resultSet.getInt(4)).get(0));
            empresas.add(empresa);
        }
        connection.close();
        return empresas;
    }

    public Integer proximoId() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select max(id) from empresa");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1) + 1;
        }
        return 1;
    }

    public void update(Empresa empresa) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("update empresa set nome = ?, logo = ?, ambiente = ? where id = ?");
        stmt.setString(1, empresa.getNome());
        stmt.setString(2, empresa.getLogo());
        stmt.setInt(3, empresa.getAmbiente().getId().intValue());
        stmt.setInt(4, empresa.getId().intValue());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas inseridas");
        connection.close();
    }

    public void delete(Empresa empresa) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("delete from empresas where id = ?");

        stmt.setInt(1, empresa.getId().intValue());
        stmt.executeUpdate();
        connection.close();
    }
}
