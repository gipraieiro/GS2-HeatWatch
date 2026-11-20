package br.com.heatwatch.dominio;
public interface Sensor {
    double lerValor();
    boolean verificarFuncionamento();
    String retornarTipo();
}
