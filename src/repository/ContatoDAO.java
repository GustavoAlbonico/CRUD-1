package repository;

import model.Contato;
import model.ContatoEnum;
import model.EstadoEnum;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    public static Object[] findContatoInArray() {
        List<ContatoEnum> contatoEnums = new ArrayList<>();

        for (ContatoEnum contatoEnum : ContatoEnum.values()) {
            contatoEnums.add(contatoEnum);
        }

        return contatoEnums.toArray();
    }

    public static List<ContatoEnum> buscarPorNome(Object nome) {
        List<ContatoEnum> tipoContatoFiltradas = new ArrayList<>();
        for (ContatoEnum ContatoEnum : ContatoEnum.values()) {
            if (ContatoEnum.name().contains(nome.toString())) {
                tipoContatoFiltradas.add(ContatoEnum);
            }
        }
        return tipoContatoFiltradas;
    }
}

