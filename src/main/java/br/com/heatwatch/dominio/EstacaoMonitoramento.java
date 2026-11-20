package br.com.heatwatch.dominio;
public class EstacaoMonitoramento extends ComponenteTerritorial {
    public EstacaoMonitoramento(int id, String nome, String regiao){super(id,nome,regiao);}
    @Override public String verificarStatus(){return "Estação "+getNome()+" na região "+getRegiao()+" está "+getStatus();}
}
