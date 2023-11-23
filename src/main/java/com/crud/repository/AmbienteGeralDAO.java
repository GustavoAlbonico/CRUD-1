package main.java.com.crud.repository;


import main.java.com.crud.model.AmbienteGeral;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class AmbienteGeralDAO {

    List<AmbienteGeral> ambientesGeral = new ArrayList<>();

    public List<AmbienteGeral> buscarTodos(){

        AmbienteGeralRepository ambienteGeralRepository = new AmbienteGeralRepository();
        try {
            ambientesGeral = ambienteGeralRepository.busca();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ambientesGeral;
    }

    public List<AmbienteGeral> buscarTodosPorCidade(Integer id){

        AmbienteGeralRepository ambienteGeralRepository = new AmbienteGeralRepository();
        try {
            ambientesGeral = ambienteGeralRepository.buscaPorCidade(id);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ambientesGeral;
    }

    }



