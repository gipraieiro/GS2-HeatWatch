package br.com.heatwatch.persistencia;
import br.com.heatwatch.dominio.*;
import java.sql.*;
import java.util.*;
public class MonitoramentoDAO {
    private final String url;
    public MonitoramentoDAO(String url){this.url=url;inicializar();}
    private Connection conectar() throws SQLException{return DriverManager.getConnection(url,"sa","");}
    private void inicializar(){
        try(Connection c=conectar(); Statement s=c.createStatement()){
            s.execute("CREATE TABLE IF NOT EXISTS MEDICAO (ID IDENTITY PRIMARY KEY, SENSOR VARCHAR(60), VALOR DOUBLE, DATA_HORA TIMESTAMP)");
            s.execute("CREATE TABLE IF NOT EXISTS ALERTA (ID IDENTITY PRIMARY KEY, NIVEL VARCHAR(20), DESCRICAO VARCHAR(500), DATA_HORA TIMESTAMP)");
        }catch(SQLException e){throw new IllegalStateException("Falha ao inicializar banco: "+e.getMessage(),e);}
    }
    public void salvarMedicao(Medicao m){
        String sql="INSERT INTO MEDICAO(SENSOR,VALOR,DATA_HORA) VALUES(?,?,?)";
        try(Connection c=conectar();PreparedStatement p=c.prepareStatement(sql)){p.setString(1,m.getSensor());p.setDouble(2,m.getValor());p.setTimestamp(3,Timestamp.valueOf(m.getData()));p.executeUpdate();}
        catch(SQLException e){throw new IllegalStateException("Não foi possível salvar medição: "+e.getMessage(),e);}
    }
    public void salvarAlerta(Alerta a){
        String sql="INSERT INTO ALERTA(NIVEL,DESCRICAO,DATA_HORA) VALUES(?,?,?)";
        try(Connection c=conectar();PreparedStatement p=c.prepareStatement(sql)){p.setString(1,a.getNivel());p.setString(2,a.getDescricao());p.setTimestamp(3,Timestamp.valueOf(a.getData()));p.executeUpdate();}
        catch(SQLException e){throw new IllegalStateException("Não foi possível salvar alerta: "+e.getMessage(),e);}
    }
    public List<Medicao> listarMedicoes(String sensor){
        List<Medicao> lista=new ArrayList<>(); String sql="SELECT ID,SENSOR,VALOR FROM MEDICAO WHERE SENSOR=? ORDER BY ID";
        try(Connection c=conectar();PreparedStatement p=c.prepareStatement(sql)){p.setString(1,sensor);try(ResultSet r=p.executeQuery()){while(r.next()){Medicao m=new Medicao(r.getString("SENSOR"),r.getDouble("VALOR"));m.setId(r.getInt("ID"));lista.add(m);}}}
        catch(SQLException e){throw new IllegalStateException("Falha ao consultar medições: "+e.getMessage(),e);} return lista;
    }
    public List<String> listarAlertas(){
        List<String> lista=new ArrayList<>();
        try(Connection c=conectar();PreparedStatement p=c.prepareStatement("SELECT NIVEL,DESCRICAO FROM ALERTA ORDER BY ID");ResultSet r=p.executeQuery()){while(r.next())lista.add(r.getString(1)+" - "+r.getString(2));}
        catch(SQLException e){throw new IllegalStateException("Falha ao consultar alertas: "+e.getMessage(),e);}return lista;
    }
}
