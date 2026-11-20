package br.com.heatwatch.excecao;
public class DadosInvalidosException extends RuntimeException {
    public DadosInvalidosException(String mensagem) { super(mensagem); }
}
