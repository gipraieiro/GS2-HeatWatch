package br.com.heatwatch;
import br.com.heatwatch.dominio.DadosTerritorio;
import br.com.heatwatch.excecao.DadosInvalidosException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class DadosTerritorioTest {
 @Test void atualizaLimiteValido(){var dados=new DadosTerritorio();dados.setLimiteTemperatura(38);assertEquals(38,dados.getLimiteTemperatura());}
 @Test void rejeitaLimiteInvalido(){var dados=new DadosTerritorio();assertThrows(DadosInvalidosException.class,()->dados.setLimiteConsumo(-1));}
}
