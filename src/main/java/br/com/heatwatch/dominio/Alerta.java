package br.com.heatwatch.dominio;
import java.time.LocalDateTime;
public class Alerta {
    private int id; private final String nivel, descricao; private final LocalDateTime data=LocalDateTime.now();
    public Alerta(String nivel,String descricao){this.nivel=nivel;this.descricao=descricao;}
    public int getId(){return id;} public void setId(int id){this.id=id;}
    public String getNivel(){return nivel;} public String getDescricao(){return descricao;} public LocalDateTime getData(){return data;}
}
