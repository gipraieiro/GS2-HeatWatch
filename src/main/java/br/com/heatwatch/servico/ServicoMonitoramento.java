
package br.com.heatwatch.servico;

import br.com.heatwatch.dominio.*;
import br.com.heatwatch.excecao.*;
import br.com.heatwatch.persistencia.MonitoramentoDAO;
import java.util.*;

public class ServicoMonitoramento {

    private final MonitoramentoDAO dao;
    private final DadosTerritorio limites;

    public ServicoMonitoramento(MonitoramentoDAO dao, DadosTerritorio limites) {
        this.dao = dao;
        this.limites = limites;
    }

    public Medicao registrarLeitura(Sensor sensor) {
        if (sensor == null || !sensor.verificarFuncionamento()) {
            throw new FonteIndisponivelException(
                "Sensor indisponível. Verifique a fonte de leitura."
            );
        }

        Medicao m = new Medicao(sensor.retornarTipo(), sensor.lerValor());
        dao.salvarMedicao(m);
        return m;
    }

    public Alerta avaliar(String sensor, double previsto) {

        String nivel = "NORMAL";
        String motivo = "Condições dentro dos limites.";

        if (sensor.equals("TEMPERATURA_C")) {

            double limite = limites.getLimiteTemperatura();

            if (previsto >= limite + 5) {
                nivel = "CRÍTICO";
                motivo = "Temperatura muito acima do limite: " + previsto + " C.";

            } else if (previsto >= limite) {
                nivel = "ALERTA";
                motivo = "Temperatura acima do limite: " + previsto + " C.";

            } else if (previsto >= limite - 3) {
                nivel = "ATENÇÃO";
                motivo = "Temperatura próxima do limite: " + previsto + " C.";
            }

        } else if (sensor.equals("CONSUMO_KW")) {

            double limite = limites.getLimiteConsumo();

            if (previsto >= limite + 20) {
                nivel = "CRÍTICO";
                motivo = "Consumo com risco elevado: " + previsto + " kW.";

            } else if (previsto >= limite) {
                nivel = "ALERTA";
                motivo = "Consumo acima do limite: " + previsto + " kW.";

            } else if (previsto >= limite - 20) {
                nivel = "ATENÇÃO";
                motivo = "Consumo próximo do limite: " + previsto + " kW.";
            }
        }

        Alerta alerta = new Alerta(nivel, sensor + ": " + motivo);

        if (!nivel.equals("NORMAL")) {
            dao.salvarAlerta(alerta);
        }

        return alerta;
    }

    public double prever(String sensor, EstrategiaPrevisao estrategia) {

        List<Double> valores = new ArrayList<>();

        for (Medicao m : dao.listarMedicoes(sensor)) {
            valores.add(m.getValor());
        }

        if (valores.size() < 2) {
            throw new FonteIndisponivelException(
                "Histórico insuficiente: registre pelo menos duas leituras."
            );
        }

        return estrategia.prever(valores);
    }
}
