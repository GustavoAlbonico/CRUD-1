package repository;

import model.Cidade;
import model.EstadoEnum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CidadeRepository {

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/ambientes_inovacao";
        Connection conenection = DriverManager.getConnection(url, "root", "");

        return conenection;
    }

    public void insere(Cidade cidade) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("insert into cidade values(null,?,?)");


        stmt.setString(1, cidade.getNome());
        stmt.setInt(2,cidade.getUF().ordinal());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas inseridas");
        connection.close();
    }

    public List<Cidade> busca() throws SQLException, ClassNotFoundException {
        List<Cidade> cidades = new ArrayList<>();
        List<EstadoEnum> estadoEnumAchado;
        Connection connection = getConnection();


        PreparedStatement stmt = connection.prepareStatement("select * from cidade");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {

            Cidade cidade = new Cidade();
            cidade.setId(resultSet.getInt(1));
            cidade.setNome(resultSet.getString(2));
            estadoEnumAchado = CidadeDAO.buscaEstadoEnumBancoDados(resultSet.getInt(3));
            cidade.setUF(estadoEnumAchado.get(0));
            cidades.add(cidade);
        }

        connection.close();
        return cidades;
    }

    public void delete(Cidade cidade) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM cidade" +
                " WHERE id = ?");
        stmt.setInt(1, cidade.getId().intValue());
        stmt.executeUpdate();
        connection.close();
    }
}
