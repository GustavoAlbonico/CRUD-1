package repository;

import model.Contato;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoRepository {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/ambientes_inovacao";
        Connection connection = DriverManager.getConnection(url, "root", "");

        return connection;
    }

    public void insere(Contato contato) throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("insert into contato values (null, ?)");
        stmt.setString(1, contato.getNome());

        int i = stmt.executeUpdate();
        System.out.println(i + " linha(s) inserida(s)");
        connection.close();
    }

    public List<Contato> busca() throws SQLException, ClassNotFoundException{
        List<Contato> contatos = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from contato");
        ResultSet resultSet = stmt.executeQuery();

        while(resultSet.next()){
            Contato contato = new Contato();
            contato.setId(resultSet.getInt(1));
            contato.setNome(resultSet.getString(2));
            contatos.add(contato);
        }
        connection.close();
        return contatos;
    }

    public List<Contato> buscaPorId(Integer id) throws SQLException, ClassNotFoundException{
        List<Contato> contatos = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from contato where id = ?");
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            Contato contato = new Contato();
            contato.setId(resultSet.getInt(1));
            contato.setNome(resultSet.getString(2));
            contatos.add(contato);
        }
        connection.close();
        return contatos;
    }

    public void update(Contato contato) throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("update contato SET nome = ? WHERE id = ? ");
        stmt.setString(1, contato.getNome());
        stmt.setInt(2,contato.getId());

        int i = stmt.executeUpdate();
        System.out.println(i + " linha(s) atualizada(s)");
        connection.close();

    }

    public void delete(Contato contato) throws SQLException, ClassNotFoundException{
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("delete from contato where id = ?");
        stmt.setInt(1, contato.getId().intValue());
        int i = stmt.executeUpdate();
        System.out.println(i + " linha(s) removida(s)");
        connection.close();
    }
}