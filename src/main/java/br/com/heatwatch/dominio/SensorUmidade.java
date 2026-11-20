package br.com.heatwatch.dominio;
import java.util.Random;
public class SensorUmidade implements Sensor {
    private final Random random = new Random();
    public double lerValor(){return 20 + random.nextDouble()*70;}
    public boolean verificarFuncionamento(){return true;}
    public String retornarTipo(){return "UMIDADE_PERCENTUAL";}
}
