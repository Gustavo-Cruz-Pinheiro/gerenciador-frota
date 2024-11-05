package dao;
import model.Veiculo;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import conexao.Conexao;
import java.sql.SQLException;

public class DaoVeiculoImp implements DaoVeiculo {
    Conexao con = new Conexao();
    
    @Override
    public void salvar(Veiculo veiculo) {
        con.conecta();
        con.executaSQL("select*from tb_veiculo");
        String sqlinsert = "INSERT INTO tb_veiculo (modelo, marca, placa, cor, ano, km, preco) VALUES ('"+veiculo.getModelo()+"', '"+veiculo.getMarca()+"', '"+veiculo.getPlaca()+"', '"+veiculo.getCor()+ "', '"+veiculo.getAno()+"', '"+veiculo.getKm()+"', '"+veiculo.getPreco()+"');";
        try{
            con.stm.executeUpdate(sqlinsert);
            JOptionPane.showMessageDialog(null, "Vehicle successfully registered! =)");
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Error registering " + erro + " SQL " + sqlinsert + " :(");
        }
        con.desconecta();
    }
    
    public void alterar(int id) {
        con.conecta();
        con.executaSQL("select*from tb_veiculo");
        String sqlupdate = "UPDATE tb_veiculo SET marca = '"+JOptionPane.showInputDialog("Type a new brand")+"', modelo = '"+ JOptionPane.showInputDialog("Type a new template")+"', placa = '"+JOptionPane.showInputDialog("Type a new card")+"', cor = '"+JOptionPane.showInputDialog("Type a new color")+"', ano = '"+JOptionPane.showInputDialog("Type a new year")+"', km = '"+JOptionPane.showInputDialog("Type a new amount of km")+"', preco = '"+JOptionPane.showInputDialog("Type a new price")+"' WHERE id ="+id+"; ";
        try{
            con.stm.executeUpdate(sqlupdate);
            JOptionPane.showMessageDialog(null, "Vehicle successfully updated!:)");
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Error while updating " + erro + " SQL " + sqlupdate + ":(");
        }
        con.desconecta();
    }
 @Override
    public void excluir(int id) {
        int resp = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete the record nº"+id, "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
        if(resp==0){
            con.conecta();
            con.executaSQL("select*from tb_veiculo");
            String sqlremove = "DELETE FROM tb_veiculo WHERE id ="+id;
            try{
                con.stm.executeUpdate(sqlremove);
                JOptionPane.showMessageDialog(null, "Vehicle successfully deleted! =)");
            }
            catch(SQLException erro){
                JOptionPane.showMessageDialog(null, "Error deleting " + erro + " SQL " + sqlremove + "=(");
            }
            con.desconecta();
        }
        if(resp==1){
            JOptionPane.showMessageDialog(null,"You did not delete!");
        }    
    }
    
    @Override
    public List<Veiculo> getVeiculo() {
        List<Veiculo> list = new ArrayList<Veiculo>();
        con.conecta();
        con.executaSQL("select*from tb_veiculo");
        try{
            con.rs.first();
            do{
                Veiculo veiculo = new Veiculo();
                veiculo.setId(con.rs.getInt("id"));
                veiculo.setModelo(con.rs.getString("modelo"));
                veiculo.setMarca(con.rs.getString("marca"));
                veiculo.setPlaca(con.rs.getString("placa"));
                veiculo.setCor(con.rs.getString("cor"));
                veiculo.setAno(con.rs.getInt("ano"));
                veiculo.setKm(con.rs.getFloat("km"));
                veiculo.setPreco(con.rs.getFloat("preco"));
                list.add(veiculo);
            }while(con.rs.next());
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Error fetching data!" + erro + ":(");
        }
        con.desconecta();
        return list;
    }
    
    public void alteraKm(int id, float newKm){
        con.conecta();
        con.executaSQL("select*from tb_veiculo");
        String sqlupdate = "UPDATE tb_veiculo SET km = '"+newKm+"' WHERE id ="+id+"; ";
        try{
            con.stm.executeUpdate(sqlupdate);
            JOptionPane.showMessageDialog(null, "Kilometros aumentados no tb_veiculo com sucesso!:)");
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Error while changing " + erro + " SQL " + sqlupdate + ":(");
        }
        con.desconecta();
    }
}

