package main.java.com.crud.repository;

import main.java.com.crud.model.Cidade;

import main.java.com.crud.model.EstadoEnum;

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
        stmt.setString(2,cidade.getUF().toString());

        int i = stmt.executeUpdate();
        System.out.println(i + " linha(s) inserida(s)");
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
            estadoEnumAchado = CidadeDAO.buscarPorNomeEstado(resultSet.getString(3));
            cidade.setUF(estadoEnumAchado.get(0));
            cidades.add(cidade);
        }

        connection.close();
        return cidades;
    }

    public Integer buscaQtdAmbienteCidade(Integer id) throws SQLException, ClassNotFoundException {
        Integer QtdAmbiente = 0;
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select count(*) from ambiente am, cidade c where c.id = am.cidade_id and c.id = ? group by c.nome;");
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            QtdAmbiente = resultSet.getInt(1);
        }
        connection.close();
        return QtdAmbiente;
    }



    public List<Cidade> buscaPorId(Integer id) throws SQLException, ClassNotFoundException {
        List<Cidade> cidades = new ArrayList<>();
        List<EstadoEnum> estadoEnumAchado;
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from cidade WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            Cidade cidade = new Cidade();
            cidade.setId(resultSet.getInt(1));
            cidade.setNome(resultSet.getString(2));
            estadoEnumAchado = CidadeDAO.buscarPorNomeEstado(resultSet.getString(3));
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
        int i = stmt.executeUpdate();
        System.out.println(i + " linha(s) removidas(s)");
        connection.close();
    }

    public void update(Cidade cidade) throws SQLException, ClassNotFoundException {

        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("update cidade " +
                "SET nome = ?, uf = ? WHERE id = ?");
        stmt.setString(1, cidade.getNome());
        stmt.setString(2, cidade.getUF().toString());
        stmt.setInt(3, cidade.getId().intValue());

        int i = stmt.executeUpdate();
        System.out.println(i + " linha(s) atualizada(s)");
        connection.close();
    }
}
