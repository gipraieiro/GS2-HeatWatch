package br.com.heatwatch.servico;
import br.com.heatwatch.dominio.*;
public class FabricaPrevisao {
    public EstrategiaPrevisao criar(String tipo){
        return switch(tipo.toUpperCase()){
            case "MEDIA" -> new PrevisaoMediaMovel();
            case "COMPARACAO" -> new PrevisaoComparacao();
            default -> throw new IllegalArgumentException("Estratégia desconhecida: "+tipo);
        };
    }
}
