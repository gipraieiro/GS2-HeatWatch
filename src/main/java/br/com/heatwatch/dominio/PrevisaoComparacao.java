package br.com.heatwatch.dominio;
import java.util.List;
public class PrevisaoComparacao extends EstrategiaPrevisao {
    public PrevisaoComparacao(){super("Comparação com leitura anterior");}
    @Override public double prever(List<Double> historico){
        super.prever(historico);
        if(historico.size()<2) return historico.get(0);
        double ultima=historico.get(historico.size()-1), anterior=historico.get(historico.size()-2);
        return ultima+(ultima-anterior);
    }
}
