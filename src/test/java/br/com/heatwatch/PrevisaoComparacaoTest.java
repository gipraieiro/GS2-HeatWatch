package br.com.heatwatch;
import br.com.heatwatch.dominio.PrevisaoComparacao;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class PrevisaoComparacaoTest {
 @Test void projetaTendenciaDaUltimaVariacao(){var estrategia=new PrevisaoComparacao();assertEquals(35,estrategia.prever(List.of(20.0,25.0,30.0)),0.001);}
}
