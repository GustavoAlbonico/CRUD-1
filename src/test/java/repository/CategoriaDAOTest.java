//package test.java.repository;
//
//import main.java.model.Categoria;
//import main.java.repository.CategoriaDAO;
//import main.java.repository.CategoriaRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.sql.SQLException;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.Mockito.*;
//
//public class CategoriaDAOTest {
//
//    @Mock
//    private CategoriaRepository categoriaRepository;
//
//    private CategoriaDAO categoriaDAO;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        categoriaDAO = new CategoriaDAO();
//    }
//
//    @Test
//    public void testBuscarTodos() throws SQLException, ClassNotFoundException {
//        // Configurando o comportamento simulado do repositório
//        List<Categoria> categoriasMock = Arrays.asList(
//                new Categoria(1, "Categoria1"),
//                new Categoria(2, "Categoria2")
//        );
//        when(categoriaRepository.busca()).thenReturn(categoriasMock);
//
//        // Executando o método que queremos testar
//        List<Categoria> result = categoriaDAO.buscarTodos();
//
//        // Verificando se o método do repositório foi chamado
//        verify(categoriaRepository).busca();
//
//        // Verificando o resultado
//        assertEquals(categoriasMock, result);
//    }
//
//    @Test
//    public void testBuscarPorNome() {
//        // Configurando o comportamento simulado do repositório
//        List<Categoria> categoriasMock = Arrays.asList(
//                new Categoria(1, "Categoria1"),
//                new Categoria(2, "Categoria2")
//        );
//        when(categoriaRepository.busca()).thenReturn(categoriasMock);
//
//        // Executando o método que queremos testar
//        List<Categoria> result = categoriaDAO.buscarPorNome("Categoria");
//
//        // Verificando se o método do repositório não foi chamado, pois usamos a lista mockada
//        verifyNoInteractions(categoriaRepository);
//
//        // Verificando o resultado
//        assertEquals(categoriasMock, result);
//    }
//
//    // Adicione outros testes conforme necessário...
//}
