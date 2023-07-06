package repository;

import model.Ambiente;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AmbienteRepository {

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/ambientes_inovacao";
        Connection conenection = DriverManager.getConnection(url, "root", "");

        return conenection;
    }

    public void insere(Ambiente ambiente) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("insert into ambiente values(null,?,?,?,?,?,?,?)");

        stmt.setString(1, ambiente.getNome());
        stmt.setString(2, ambiente.getCep());
        stmt.setString(3, ambiente.getRua());
        stmt.setString(4, ambiente.getNumero());
        stmt.setString(5, ambiente.getBairro());
        stmt.setInt(6, ambiente.getCidade().getId());
        stmt.setInt(7, ambiente.getCategoria().getId());

        int i = stmt.executeUpdate();
        System.out.println(i + " linha(s) inserida(s)");
        connection.close();
    }

    public List<Ambiente> busca() throws SQLException, ClassNotFoundException {
        List<Ambiente> ambientes = new ArrayList<>();
        Connection connection = getConnection();


        PreparedStatement stmt = connection.prepareStatement("select * from ambiente");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {

            Ambiente ambiente = new Ambiente();
            ambiente.setId(resultSet.getInt(1));
            ambiente.setNome(resultSet.getString(2));
            ambiente.setCep(resultSet.getString(3));
            ambiente.setRua(resultSet.getString(4));
            ambiente.setNumero(resultSet.getString(5));
            ambiente.setBairro(resultSet.getString(6));

            CidadeRepository cidadeRepository =  new CidadeRepository();
            ambiente.setCidade(cidadeRepository.buscaPorId(resultSet.getInt(7)).get(0));

            CategoriaRepository categoriaRepository = new CategoriaRepository();
            ambiente.setCategoria(categoriaRepository.buscaPorId(resultSet.getInt(8)).get(0));

            ambientes.add(ambiente);
        }

        connection.close();
        return ambientes;
    }

    public List<Ambiente> buscaPorId(Integer id) throws SQLException, ClassNotFoundException {
        List<Ambiente> ambientes = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from ambiente WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {

            Ambiente ambiente = new Ambiente();
            ambiente.setId(resultSet.getInt(1));
            ambiente.setNome(resultSet.getString(2));
            ambiente.setCep(resultSet.getString(3));
            ambiente.setRua(resultSet.getString(4));
            ambiente.setNumero(resultSet.getString(5));
            ambiente.setBairro(resultSet.getString(6));

            CidadeRepository cidadeRepository =  new CidadeRepository();
            ambiente.setCidade(cidadeRepository.buscaPorId(resultSet.getInt(7)).get(0));

            CategoriaRepository categoriaRepository = new CategoriaRepository();
            ambiente.setCategoria(categoriaRepository.buscaPorId(resultSet.getInt(8)).get(0));

            ambientes.add(ambiente);
        }
        connection.close();
        return ambientes;
    }

    public Integer buscaQtdAmbiente() throws SQLException, ClassNotFoundException {
        Integer qtdAmbiente = 0;
        Connection connection = getConnection();


        PreparedStatement stmt = connection.prepareStatement("select count(*) from ambiente;");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {

            qtdAmbiente = resultSet.getInt(1);
        }
        connection.close();
        return qtdAmbiente;
    }

    public void delete(Ambiente ambiente) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM ambiente" +
                " WHERE id = ?");
        stmt.setInt(1, ambiente.getId().intValue());
        int i = stmt.executeUpdate();
        System.out.println(i + " linha(s) removida(s)");
        connection.close();
    }

    public void update(Ambiente ambiente) throws SQLException, ClassNotFoundException {

        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("update ambiente " +
                "SET nome = ?, cep = ?, rua = ?, numero = ?, bairro = ?, cidade_id = ?, categoria_id = ? WHERE id = ?");

        stmt.setString(1, ambiente.getNome());
        stmt.setString(2, ambiente.getCep());
        stmt.setString(3, ambiente.getRua());
        stmt.setString(4, ambiente.getNumero());
        stmt.setString(5, ambiente.getBairro());
        stmt.setInt(6, ambiente.getCidade().getId());
        stmt.setInt(7, ambiente.getCategoria().getId());
        stmt.setInt(8, ambiente.getId().intValue());

        int i = stmt.executeUpdate();
        System.out.println(i + " linha(s) atualizada(s)");
        connection.close();
    }

    public Integer buscaQtdEmpresaAmbiente(Integer id) throws SQLException, ClassNotFoundException {
        Integer QtdEmpresa = 0;
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select count(*) from ambiente a, empresa em where a.id = em.ambiente_id and a.id = ? group by a.nome;");
        stmt.setInt(1, id);
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            QtdEmpresa = resultSet.getInt(1);
        }
        connection.close();
        return QtdEmpresa;
    }

}
