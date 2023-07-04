package repository;

import model.Categoria;
import model.Cidade;
import model.EstadoEnum;


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
        System.out.println(i + " linhas inseridas");
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
}
