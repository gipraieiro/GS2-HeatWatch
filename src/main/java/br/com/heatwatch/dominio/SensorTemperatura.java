package br.com.heatwatch.dominio;
import java.util.Random;
public class SensorTemperatura implements Sensor {
    private final Random random = new Random();
    public double lerValor(){return 25 + random.nextDouble()*20;}
    public boolean verificarFuncionamento(){return true;}
    public String retornarTipo(){return "TEMPERATURA_C";}
}
