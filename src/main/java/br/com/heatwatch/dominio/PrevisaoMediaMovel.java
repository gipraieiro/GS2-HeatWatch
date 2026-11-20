package br.com.heatwatch.dominio;
import java.util.List;
public class PrevisaoMediaMovel extends EstrategiaPrevisao {
    public PrevisaoMediaMovel(){super("Média móvel");}
    @Override public double prever(List<Double> historico){
        super.prever(historico);
        int inicio=Math.max(0,historico.size()-3); double soma=0;
        for(int i=inicio;i<historico.size();i++) soma+=historico.get(i);
        return soma/(historico.size()-inicio);
    }
}
