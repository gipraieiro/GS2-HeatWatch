
package br.com.heatwatch.dominio;

import br.com.heatwatch.excecao.DadosInvalidosException;
import java.time.LocalDateTime;

public class Medicao {

    private int id;
    private final String sensor;
    private final double valor;
    private final LocalDateTime data;

    public Medicao(String sensor, double valor) {

        if (sensor == null || sensor.isBlank()) {
            throw new DadosInvalidosException(
                "Tipo de sensor não informado."
            );
        }

        if (!Double.isFinite(valor)) {
            throw new DadosInvalidosException(
                "Medição inválida: o valor deve ser um número válido."
            );
        }

        switch (sensor) {

            case "TEMPERATURA_C":
                if (valor < -50 || valor > 60) {
                    throw new DadosInvalidosException(
                        "Temperatura fora da faixa permitida: -50°C a 60°C."
                    );
                }
                break;

            case "UMIDADE_PERCENTUAL":
                if (valor < 0 || valor > 100) {
                    throw new DadosInvalidosException(
                        "Umidade fora da faixa permitida: 0% a 100%."
                    );
                }
                break;

            case "CONSUMO_KW":
                if (valor < 0 || valor > 1000) {
                    throw new DadosInvalidosException(
                        "Consumo de energia fora da faixa permitida: 0 a 1000 kW."
                    );
                }
                break;

            default:
                throw new DadosInvalidosException(
                    "Tipo de sensor não reconhecido: " + sensor
                );
        }

        this.sensor = sensor;
        this.valor = valor;
        this.data = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSensor() {
        return sensor;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getData() {
        return data;
    }
}
