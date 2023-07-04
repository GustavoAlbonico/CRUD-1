package repository;

import model.EstadoEnum;

import java.util.ArrayList;
import java.util.List;

public class CidadeDAO {


    public static Object[] findEstadoUFInArray() {

        List<EstadoEnum> estadoUf = new ArrayList<>();
       for (EstadoEnum estadoEnum : EstadoEnum.values()){
           estadoUf.add(estadoEnum);
       }

    return estadoUf.toArray();
    }

    public static List<EstadoEnum> buscarPorNome(Object nome) {
        List<EstadoEnum> estadoUfFiltradas = new ArrayList<>();
        for (EstadoEnum estadoEnum : EstadoEnum.values()) {
            if (estadoEnum.name().contains(nome.toString())) {
                estadoUfFiltradas.add(estadoEnum);
            }
        }
        return estadoUfFiltradas;
    }
}
