package main.java.repository;

import main.java.model.Categoria;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepository {

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/ambientes_inovacao";
        Connection conenection = DriverManager.getConnection(url, "root", "");

        return conenection;
    }

    public void insere(Categoria categoria) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("insert into categoria values(null,?)");

        stmt.setString(1, categoria.getNome());

        int i = stmt.executeUpdate();
        System.out.println(i + " linha(s) inserida(s)");
        connection.close();
    }

    public List<Categoria> busca() throws SQLException, ClassNotFoundException {
        List<Categoria> categorias = new ArrayList<>();
        Connection connection = getConnection();


        PreparedStatement stmt = connection.prepareStatement("select * from categoria");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {

            Categoria categoria = new Categoria();
            categoria.setId(resultSet.getInt(1));
            categoria.setNome(resultSet.getString(2));
            categorias.add(categoria);
        }

        connection.close();
        return categorias;
    }

    public List<Categoria> buscaPorId(Integer id) throws SQLException, ClassNotFoundException {
        List<Categoria> cidades = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from categoria WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {

            Categoria categoria = new Categoria();
            categoria.setId(resultSet.getInt(1));
            categoria.setNome(resultSet.getString(2));
            cidades.add(categoria);
        }
        connection.close();
        return cidades;
    }

    public void delete(Categoria categoria) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM categoria" +
                " WHERE id = ?");
        stmt.setInt(1, categoria.getId().intValue());
        int i = stmt.executeUpdate();
        System.out.println(i + " linha(s) removida(s)");
        connection.close();
    }

    public void update(Categoria categoria) throws SQLException, ClassNotFoundException {

        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("update categoria " +
                "SET nome = ? WHERE id = ?");
        stmt.setString(1, categoria.getNome());
        stmt.setInt(2, categoria.getId().intValue());

        int i = stmt.executeUpdate();
        System.out.println(i + " linha(s) atualizada(s)");
        connection.close();
    }

}
