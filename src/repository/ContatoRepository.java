package repository;

import model.Contato;
import model.ContatoEnum;

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

    public void insere (Contato contato) throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("insert into contato values (?, ?, ?, ?)");
        stmt.setInt(1, contato.getId().intValue());
        stmt.setInt(2, contato.getTipoContato().ordinal());
        stmt.setString(3, contato.getDescricao());
        stmt.setInt(4, contato.getEmpresa().getId().intValue());

        int i = stmt.executeUpdate();
        System.out.println(i + " linhas inseridas");
        connection.close();
    }

    public List<Contato> busca() throws SQLException, ClassNotFoundException {
        List<Contato> contatos = new ArrayList<>();
        Connection connection = getConnection();

        PreparedStatement stmt = connection.prepareStatement("select * from contato");
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
            Contato contato = new Contato();
            contato.setId(resultSet.getInt(1));
            contato.setTipoContato(ContatoEnum.getTipoById(resultSet.getInt(2)));
            contato.setDescricao(resultSet.getString(3));

            EmpresaRepository empresaRepository = new EmpresaRepository();
            contato.setEmpresa(empresaRepository.buscaPorId(resultSet.getInt(4)).get(0));
            contatos.add(contato);
        }
        connection.close();
        return contatos;
    }

}
