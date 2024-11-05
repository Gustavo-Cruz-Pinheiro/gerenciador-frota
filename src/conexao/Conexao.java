package conexao;
import java.sql.*;
import javax.swing.JOptionPane;

public class Conexao {
    Connection conexao;
    private final String url="jdbc:mysql://localhost:3306/db_transportadora";
    private final String usuario="root";
    private final String senha="";
    private final String drive="com.mysql.jdbc.Driver";
    
    public Statement stm; // caminho
    public ResultSet rs; // armazenar pesquisas
    
    public boolean conecta(){
        boolean result = true;
        try{
            Class.forName(drive);
            conexao = DriverManager.getConnection(url,usuario,senha);
            JOptionPane.showMessageDialog(null, "Successfully connected!");
        }
        catch(ClassNotFoundException Driver){
            JOptionPane.showMessageDialog(null,"Driver not found: " + Driver);
            result = false;
        }
        catch(SQLException Fonte){
            JOptionPane.showMessageDialog(null, "Error connecting to data source: " + Fonte);
            result = false;
        }
        return result;   
    }
    
    public boolean desconecta(){
        boolean result = true;
        try{
            conexao.close();
            JOptionPane.showMessageDialog(null,"Connection successfully completed!");
        }
        catch(SQLException Erro){
            JOptionPane.showMessageDialog(null,"Could not disconnect!"+Erro);
        }
        return result;
    }
    
    public void executaSQL(String sql){
        try{
            stm = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        }
        catch(SQLException Query){
            JOptionPane.showMessageDialog(null, "Error "+ Query + "SQL"+ sql);
        }
    }
}