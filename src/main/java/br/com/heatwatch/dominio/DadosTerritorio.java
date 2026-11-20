package br.com.heatwatch.dominio;
import br.com.heatwatch.excecao.DadosInvalidosException;
public class DadosTerritorio {
    private double limiteTemperatura=35.0;
    private double limiteConsumo=120.0;
    public double getLimiteTemperatura(){return limiteTemperatura;}
    public double getLimiteConsumo(){return limiteConsumo;}
    public void setLimiteTemperatura(double v){if(v< -20 || v>60) throw new DadosInvalidosException("Limite de temperatura deve estar entre -20 e 60 C."); limiteTemperatura=v;}
    public void setLimiteConsumo(double v){if(v<=0 || v>10000) throw new DadosInvalidosException("Limite de consumo deve ser positivo e até 10000 kW."); limiteConsumo=v;}
}
