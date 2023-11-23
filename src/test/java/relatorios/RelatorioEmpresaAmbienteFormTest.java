package test.java.relatorios;

import main.java.RelatorioEmpresaAmbienteForm;
import main.java.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class RelatorioEmpresaAmbienteFormTest {

    private Ambiente ambiente;

    @Before
    public void setUp() {
        Cidade cidade = new Cidade(1, "São Paulo", EstadoEnum.SP);
        Categoria categoria = new Categoria(1, "Categoria Teste");
        ambiente = new Ambiente(1, "Ambiente Teste", "12345", "Rua Teste", "123", "Bairro Teste", cidade, categoria);
    }

    @Test
    public void testEmitirRelatorio() {

        Empresa empresa1 = new Empresa(1, "Empresa Teste 1", "logo1", "site1", ambiente);
        Empresa empresa2 = new Empresa(2, "Empresa Teste 2", "logo2", "site2", ambiente);
        List<Empresa> empresas = Arrays.asList(empresa1, empresa2);

        RelatorioEmpresaAmbienteForm.emitirRelatorio(empresas, ambiente);
    }

}
