
package br.com.heatwatch;

import br.com.heatwatch.dominio.*;
import br.com.heatwatch.persistencia.MonitoramentoDAO;
import br.com.heatwatch.servico.ServicoMonitoramento;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServicoMonitoramentoTest {

    @Test
    void classificaConsumoComoAtencao() {
        MonitoramentoDAO dao = mock(MonitoramentoDAO.class);

        ServicoMonitoramento servico =
            new ServicoMonitoramento(dao, new DadosTerritorio());

        assertEquals(
            "ATENÇÃO",
            servico.avaliar("CONSUMO_KW", 110).getNivel()
        );

        verify(dao).salvarAlerta(any(Alerta.class));
    }

    @Test
    void classificaConsumoComoAlerta() {
        MonitoramentoDAO dao = mock(MonitoramentoDAO.class);

        ServicoMonitoramento servico =
            new ServicoMonitoramento(dao, new DadosTerritorio());

        assertEquals(
            "ALERTA",
            servico.avaliar("CONSUMO_KW", 125).getNivel()
        );
    }

    @Test
    void classificaConsumoComoCritico() {
        MonitoramentoDAO dao = mock(MonitoramentoDAO.class);

        ServicoMonitoramento servico =
            new ServicoMonitoramento(dao, new DadosTerritorio());

        assertEquals(
            "CRÍTICO",
            servico.avaliar("CONSUMO_KW", 150).getNivel()
        );
    }

    @Test
    void naoRegistraAlertaEmCondicaoNormal() {
        MonitoramentoDAO dao = mock(MonitoramentoDAO.class);

        ServicoMonitoramento servico =
            new ServicoMonitoramento(dao, new DadosTerritorio());

        assertEquals(
            "NORMAL",
            servico.avaliar("CONSUMO_KW", 60).getNivel()
        );

        verify(dao, never()).salvarAlerta(any(Alerta.class));
    }
}
