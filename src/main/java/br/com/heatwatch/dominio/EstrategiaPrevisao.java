package br.com.heatwatch.dominio;
import java.util.List;
public abstract class EstrategiaPrevisao {
    protected final String nome;
    protected EstrategiaPrevisao(String nome){this.nome=nome;}
    public String getNome(){return nome;}
    public double prever(List<Double> historico){
        if(historico==null || historico.isEmpty()) throw new IllegalArgumentException("Histórico vazio: não é possível prever.");
        return historico.get(historico.size()-1);
    }
}
