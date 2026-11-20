package br.com.heatwatch.servico;
import br.com.heatwatch.dominio.DadosTerritorio;
public final class ConfiguracaoSistema {
    private static ConfiguracaoSistema instancia;
    private final DadosTerritorio dados=new DadosTerritorio();
    private ConfiguracaoSistema(){}
    public static synchronized ConfiguracaoSistema getInstancia(){if(instancia==null) instancia=new ConfiguracaoSistema();return instancia;}
    public DadosTerritorio getDados(){return dados;}
}
