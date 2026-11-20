package br.com.heatwatch;
import br.com.heatwatch.dominio.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class PrevisaoMediaMovelTest {
 @Test void calculaMediaDasUltimasTres(){var estrategia=new PrevisaoMediaMovel();double resultado=estrategia.prever(List.of(20.0,30.0,40.0,50.0));assertEquals(40.0,resultado,0.001);}
 @Test void rejeitaHistoricoVazio(){var estrategia=new PrevisaoMediaMovel();assertThrows(IllegalArgumentException.class,()->estrategia.prever(List.of()));}
}
