package br.com.heatwatch.dominio;
import java.util.Random;
public class SensorConsumoEnergia implements Sensor {
    private final Random random = new Random();
    public double lerValor(){return 50 + random.nextDouble()*100;}
    public boolean verificarFuncionamento(){return true;}
    public String retornarTipo(){return "CONSUMO_KW";}
}
