package main.java.repository;

import main.java.model.Empresa;

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

        PreparedStatement stmt = connection.prepareStatement("insert into empresa values(null,?,?,?,?)");


        stmt.setString(1, empresa.getNome());
        stmt.setString(2,empresa.getLogo());
        stmt.setString(3,empresa.getSite());
        stmt.setInt(4,empresa.getAmbiente().getId());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas inseridas");
        connection.close();

        List<Empresa> empresas = buscaPorNome(empresa.getNome());
        EmpresaContatoDAO empresaContatoDAO =  new EmpresaContatoDAO();
        empresaContatoDAO.salvar(empresas.get(0).getId(),empresa.getListaContato());
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

            empresas.add(empresa);
        }
        connection.close();
        return empresas;
    }

    public List<Empresa> buscaPorNome(String nome) throws SQLException, ClassNotFoundException {
        List<Empresa> empresas = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from empresa where nome = ?");
        stmt.setString(1, nome);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            Empresa empresa = new Empresa();
            empresa.setId(resultSet.getInt(1));
            empresa.setNome(resultSet.getString(2));
            empresa.setLogo(resultSet.getString(3));

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
            empresa.setSite(resultSet.getString(4));

            AmbienteRepository ambienteRepository = new AmbienteRepository();
            empresa.setAmbiente(ambienteRepository.buscaPorId(resultSet.getInt(5)).get(0));
            empresas.add(empresa);
        }
        connection.close();
        return empresas;
    }

    public void update(Empresa empresa) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("update empresa set nome = ?, logo = ?, site = ?, ambiente_id = ? where id = ?");

        stmt.setString(1, empresa.getNome());
        stmt.setString(2, empresa.getLogo());
        stmt.setString(3, empresa.getSite());
        stmt.setInt(4, empresa.getAmbiente().getId().intValue());
        stmt.setInt(5, empresa.getId().intValue());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas atualizadas");
        connection.close();

        EmpresaContatoDAO empresaContatoDAO =  new EmpresaContatoDAO();
        empresaContatoDAO.update(empresa.getId(),empresa.getListaContato());
    }

    public void delete(Empresa empresa) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("delete from empresa where id = ?");

        stmt.setInt(1, empresa.getId().intValue());
        int i = stmt.executeUpdate();
        System.out.println(i + " linhas removidas");
        connection.close();
    }

    public List<Empresa> buscaPorIdAmbiente(Integer id) throws SQLException, ClassNotFoundException {
        List<Empresa> empresas = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from empresa where ambiente_id = ?");
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            Empresa empresa = new Empresa();

            empresa.setId(resultSet.getInt(1));
            empresa.setNome(resultSet.getString(2));
            empresa.setLogo(resultSet.getString(3));

            empresas.add(empresa);

        }
        connection.close();
        return empresas;
    }

    //BUSCA QUANTIDADE DE EMPRESA
    public Integer buscaQtdEmpresa() throws SQLException, ClassNotFoundException {
        Integer qtdEmpresa = 0;
        Connection connection = getConnection();


        PreparedStatement stmt = connection.prepareStatement("select count(*) from empresa;");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {

            qtdEmpresa = resultSet.getInt(1);
        }
        connection.close();
        return qtdEmpresa;
    }

    public List<Empresa> buscaPorAmbiente(Integer id) throws SQLException, ClassNotFoundException {
        List<Empresa> empresas = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from empresa where ambiente_id = ?;");
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {

            Empresa empresa = new Empresa();
            empresa.setId(resultSet.getInt(1));
            empresa.setNome(resultSet.getString(2));
            empresa.setLogo(resultSet.getString(3));
            empresa.setSite(resultSet.getString(4));

            AmbienteRepository ambienteRepository = new AmbienteRepository();
            empresa.setAmbiente(ambienteRepository.buscaPorId(resultSet.getInt(5)).get(0));

            empresas.add(empresa);
        }

        connection.close();
        return empresas;
    }

}
