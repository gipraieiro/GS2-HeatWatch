
package br.com.heatwatch;

import br.com.heatwatch.dominio.Medicao;
import br.com.heatwatch.excecao.DadosInvalidosException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicaoTest {

    @Test
    void aceitaTemperaturaDentroDaFaixa() {
        // Arrange
        double temperatura = 35.0;

        // Act
        Medicao medicao = new Medicao("TEMPERATURA_C", temperatura);

        // Assert
        assertEquals(35.0, medicao.getValor());
    }

    @Test
    void rejeitaTemperaturaAcimaDaFaixa() {
        // Arrange
        double temperatura = 65.0;

        // Act e Assert
        assertThrows(
            DadosInvalidosException.class,
            () -> new Medicao("TEMPERATURA_C", temperatura)
        );
    }

    @Test
    void aceitaUmidadeDentroDaFaixa() {
        // Arrange
        double umidade = 75.0;

        // Act
        Medicao medicao = new Medicao(
            "UMIDADE_PERCENTUAL", umidade
        );

        // Assert
        assertEquals(75.0, medicao.getValor());
    }

    @Test
    void rejeitaUmidadeAcimaDeCemPorCento() {
        // Arrange
        double umidade = 130.0;

        // Act e Assert
        assertThrows(
            DadosInvalidosException.class,
            () -> new Medicao("UMIDADE_PERCENTUAL", umidade)
        );
    }

    @Test
    void aceitaConsumoDentroDaFaixa() {
        // Arrange
        double consumo = 500.0;

        // Act
        Medicao medicao = new Medicao("CONSUMO_KW", consumo);

        // Assert
        assertEquals(500.0, medicao.getValor());
    }

    @Test
    void rejeitaConsumoAcimaDaFaixa() {
        // Arrange
        double consumo = 1200.0;

        // Act e Assert
        assertThrows(
            DadosInvalidosException.class,
            () -> new Medicao("CONSUMO_KW", consumo)
        );
    }
}
