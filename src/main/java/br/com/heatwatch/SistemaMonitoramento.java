
package br.com.heatwatch;

import br.com.heatwatch.dominio.*;
import br.com.heatwatch.persistencia.MonitoramentoDAO;
import br.com.heatwatch.servico.*;
import br.com.heatwatch.excecao.*;

import java.util.*;

public class SistemaMonitoramento {

    public static void main(String[] args) {

        MonitoramentoDAO dao =
            new MonitoramentoDAO("jdbc:h2:file:./data/heatwatch");

        DadosTerritorio dados =
            ConfiguracaoSistema.getInstancia().getDados();

        ServicoMonitoramento servico =
            new ServicoMonitoramento(dao, dados);

        List<Sensor> sensores = List.of(
            new SensorTemperatura(),
            new SensorConsumoEnergia(),
            new SensorUmidade()
        );

        EstacaoMonitoramento estacao =
            new EstacaoMonitoramento(1, "HeatWatch Central", "Região monitorada");

        Scanner sc = new Scanner(System.in);
        boolean executando = true;

        while (executando) {

            System.out.println("\n=== HEATWATCH ===");
            System.out.println("1 - Registrar leituras dos sensores");
            System.out.println("2 - Ver histórico de temperatura");
            System.out.println("3 - Ver histórico de consumo");
            System.out.println("4 - Gerar previsão e alerta");
            System.out.println("5 - Listar alertas");
            System.out.println("6 - Configurar limites");
            System.out.println("7 - Ver status territorial");
            System.out.println("8 - Verificar funcionamento dos sensores");
            System.out.println("0 - Sair");

            try {

                System.out.print("Opção: ");
                int op = Integer.parseInt(sc.nextLine());

                switch (op) {

                    case 1 -> {
                        for (Sensor s : sensores) {
                            Medicao m = servico.registrarLeitura(s);
                            System.out.printf(
                                "%s: %.2f%n",
                                m.getSensor(),
                                m.getValor()
                            );
                        }
                        System.out.println("Leituras salvas.");
                    }

                    case 2 -> exibirMedicoes(dao, "TEMPERATURA_C");

                    case 3 -> exibirMedicoes(dao, "CONSUMO_KW");

                    case 4 -> {
                        System.out.println("1 - Média móvel | 2 - Comparação");
                        String tipo = sc.nextLine().equals("2")
                            ? "COMPARACAO" : "MEDIA";

                        System.out.println("1 - Temperatura | 2 - Consumo");
                        String sensor = sc.nextLine().equals("2")
                            ? "CONSUMO_KW" : "TEMPERATURA_C";

                        double previsao = servico.prever(
                            sensor,
                            new FabricaPrevisao().criar(tipo)
                        );

                        System.out.printf("Previsão: %.2f%n", previsao);

                        Alerta alerta = servico.avaliar(sensor, previsao);

                        System.out.println("Classificação: " + alerta.getNivel());
                        System.out.println(alerta.getDescricao());
                    }

                    case 5 -> {
                        List<String> alertas = dao.listarAlertas();

                        if (alertas.isEmpty()) {
                            System.out.println("Nenhum alerta registrado.");
                        } else {
                            alertas.forEach(System.out::println);
                        }
                    }

                    case 6 -> {
                        System.out.print("Novo limite de temperatura: ");
                        dados.setLimiteTemperatura(
                            Double.parseDouble(sc.nextLine())
                        );

                        System.out.print("Novo limite de consumo: ");
                        dados.setLimiteConsumo(
                            Double.parseDouble(sc.nextLine())
                        );

                        System.out.println("Limites atualizados.");
                    }

                    case 7 -> {
                        System.out.println(estacao.verificarStatus());

                        System.out.printf(
                            "Limite de temperatura: %.1f C%n",
                            dados.getLimiteTemperatura()
                        );

                        System.out.printf(
                            "Limite de consumo: %.1f kW%n",
                            dados.getLimiteConsumo()
                        );
                    }

                    case 8 -> {
                        for (Sensor s : sensores) {
                            System.out.println(
                                s.retornarTipo() + ": " +
                                (s.verificarFuncionamento()
                                    ? "FUNCIONANDO" : "INDISPONÍVEL")
                            );
                        }
                    }

                    case 0 -> executando = false;

                    default -> System.out.println("Opção inválida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");

            } catch (DadosInvalidosException | FonteIndisponivelException e) {
                System.out.println("Não foi possível concluir: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }

        sc.close();
        System.out.println("HeatWatch encerrado.");
    }

    private static void exibirMedicoes(
        MonitoramentoDAO dao,
        String sensor
    ) {
        List<Medicao> lista = dao.listarMedicoes(sensor);

        if (lista.isEmpty()) {
            System.out.println("Nenhuma medição registrada.");
            return;
        }

        for (Medicao m : lista) {
            System.out.printf(
                "%s: %.2f%n",
                m.getSensor(),
                m.getValor()
            );
        }
    }
}
