package br.com.heatwatch.dominio;
public abstract class ComponenteTerritorial {
    private final int id;
    private String nome;
    private String regiao;
    private String status;
    public ComponenteTerritorial(int id, String nome, String regiao) {
        this.id=id; this.nome=nome; this.regiao=regiao; this.status="ATIVO";
    }
    public abstract String verificarStatus();
    public int getId(){return id;} public String getNome(){return nome;}
    public String getRegiao(){return regiao;} public String getStatus(){return status;}
    public void setStatus(String status){this.status=status;}
}
