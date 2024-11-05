package dao;

import model.Viagem;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import conexao.Conexao;
import java.sql.SQLException;

public class DaoViagemImp implements DaoViagem {
    Conexao con = new Conexao();
    
    @Override
    public void salvar(Viagem viagem) {
        con.conecta();
        con.executaSQL("select*from tb_viagem");
        String sqlinsert = "INSERT INTO tb_viagem (nome, km, data, valor, idVeiculo) VALUES ('"+viagem.getNome()+"', '"+viagem.getKm()+"', '"+viagem.getData()+"',  '"+viagem.getValor()+"', '"+viagem.getIdVeiculo()+"')";
        try{
            con.stm.executeUpdate(sqlinsert);
            JOptionPane.showMessageDialog(null, "Travel successfully registered! =)");
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Error resgistering " + erro + " SQL " + sqlinsert + ":(");
        }
        con.desconecta();
    }
    
    public void alterar(int id) {
        con.conecta();
        con.executaSQL("select*from tb_viagem");
        String sqlupdate = "UPDATE tb_viagem SET nome = '"+JOptionPane.showInputDialog("Please type a new name")+"', data = '"+ JOptionPane.showInputDialog("Type a new date")+"', km='"+JOptionPane.showInputDialog("Type a new Km")+"', valor='"+JOptionPane.showInputDialog("Type a new trip total price")+"', idVeiculo='"+JOptionPane.showInputDialog("Type a new Vehicle Id")+"' WHERE id ="+id+";";
        try{
            con.stm.executeUpdate(sqlupdate);
            JOptionPane.showMessageDialog(null, "Travel successfully updated!:)");
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Error while updating" + erro + " SQL " + sqlupdate + " :(");
        }
        con.desconecta();
    }
 @Override
    public void excluir(int id) {
        int resp = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete the record nº"+id, "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
        if(resp==0){
            con.conecta();
            con.executaSQL("select*from tb_viagem");
            String sqlremove = "DELETE FROM tb_viagem WHERE id ="+id;
            try{
                con.stm.executeUpdate(sqlremove);
                JOptionPane.showMessageDialog(null, "Successfully deleted trip! =)");
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
    public List<Viagem> getViagem() {
        List<Viagem> list = new ArrayList<Viagem>();
        con.conecta();
        con.executaSQL("select*from tb_viagem");
        try{
            con.rs.first();
            do{
                Viagem viagem = new Viagem();
                viagem.setId(con.rs.getInt("id"));
                viagem.setNome(con.rs.getString("nome"));
                viagem.setKm(con.rs.getFloat("km"));
                viagem.setData(con.rs.getString("data"));
                viagem.setValor(con.rs.getFloat("valor"));
                viagem.setIdVeiculo(con.rs.getInt("idVeiculo"));
                list.add(viagem);
            }while(con.rs.next());
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Error fetching data!" + erro + ":(");
        }
        con.desconecta();
        return list;
    }
    
}