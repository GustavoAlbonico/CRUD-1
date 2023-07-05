package repository;

import model.EmpresaContato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaContatoRepository {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/ambientes_inovacao";
        Connection connection = DriverManager.getConnection(url, "root", "");

        return connection;
    }

    public void insere(EmpresaContato empresaContato) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("insert into empresa_contato values (null, ?, ?, ?)");
        stmt.setString(2, empresaContato.getPerfil());
        stmt.setInt(3, empresaContato.getEmpresa().getId().intValue());
        stmt.setInt(4, empresaContato.getContato().getId().intValue());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas inseridas");
        connection.close();
    }

    public List<EmpresaContato> buscaPorId(Integer id) throws SQLException, ClassNotFoundException{
        List<EmpresaContato> empresaContatos = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from empresa_contato where id = ?");
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            EmpresaContato empresaContato = new EmpresaContato();
            empresaContato.setId(resultSet.getInt(1));
            empresaContato.setPerfil(resultSet.getString(2));

            //FK - empresa e contato
            EmpresaRepository empresaRepository = new EmpresaRepository();
            empresaContato.setEmpresa(empresaRepository.buscaPorId(resultSet.getInt(3)).get(0));

            ContatoRepository contatoRepository = new ContatoRepository();
            empresaContato.setContato(contatoRepository.buscaPorId(resultSet.getInt(4)).get(0));

            empresaContatos.add(empresaContato);
        }
        connection.close();
        return empresaContatos;
    }

    public List<EmpresaContato> busca() throws SQLException, ClassNotFoundException{
        List<EmpresaContato> empresaContatos = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from empresa_contato");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
            EmpresaContato empresaContato = new EmpresaContato();
            empresaContato.setId(resultSet.getInt(1));
            empresaContato.setPerfil(resultSet.getString(2));

            EmpresaRepository empresaRepository = new EmpresaRepository();
            empresaContato.setEmpresa(empresaRepository.buscaPorId(resultSet.getInt(3)).get(0));
            ContatoRepository contatoRepository = new ContatoRepository();
            empresaContato.setContato(contatoRepository.buscaPorId(resultSet.getInt(4)).get(0));

            empresaContatos.add(empresaContato);
        }
        connection.close();
        return empresaContatos;
    }

    public void update(EmpresaContato empresaContato) throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("update empresa_contato set perfil = ?, empresa_id = ?, contato_id = ? where id = ?");
        stmt.setString(1, empresaContato.getPerfil());
        stmt.setInt(2, empresaContato.getEmpresa().getId().intValue());
        stmt.setInt(3, empresaContato.getContato().getId().intValue());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas inseridas");
        connection.close();
    }

    public void delete(EmpresaContato empresaContato) throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("delete from empresa_contato where id = ?");

        stmt.setInt(1, empresaContato.getId().intValue());
        stmt.executeUpdate();
        connection.close();
    }
}