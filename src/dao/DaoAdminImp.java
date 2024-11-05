package dao;
import model.Admin;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import conexao.Conexao;
import java.sql.SQLException;

public class DaoAdminImp implements DaoAdmin {
    Conexao con = new Conexao();
    
    @Override
    public void salvar(Admin admin) {
        con.conecta();
        con.executaSQL("select*from tb_admin");
        String sqlinsert = "INSERT INTO tb_admin (nome, email, senha) VALUES ('"+admin.getNome()+"', '"+admin.getEmail()+"', '"+admin.getSenha()+"')";
        try{
            con.stm.executeUpdate(sqlinsert);
            JOptionPane.showMessageDialog(null, "Administrator successfully registered! =)");
        }
        catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Error registering " + erro + " SQL " + sqlinsert + ":(");
        }
        con.desconecta();
    }
    
    public void alterar(int id) {
        con.conecta();
        con.executaSQL("select*from tb_admin");
        String sqlupdate = "UPDATE tb_admin SET nome = '"+JOptionPane.showInputDialog("Please type a new name")+"', email = '"+ JOptionPane.showInputDialog("Type a new email")+"', senha='"+JOptionPane.showInputDialog("Type a new password")+"' WHERE id ="+id+"; ";
        try{
            con.stm.executeUpdate(sqlupdate);
            JOptionPane.showMessageDialog(null, "Administrator successfully updated!:)");
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
            con.executaSQL("select*from tb_admin");
            String sqlremove = "DELETE FROM tb_admin WHERE id ="+id;
            try{
                con.stm.executeUpdate(sqlremove);
                JOptionPane.showMessageDialog(null, "Administrator successfully deleted! =)");
            }
            catch(SQLException erro){
                JOptionPane.showMessageDialog(null, "Error deleting" + erro + " SQL " + sqlremove + "=(");
            }
            con.desconecta();
        }
        if(resp==1){
            JOptionPane.showMessageDialog(null,"You did not delete!");
        }    
    }
    
    @Override
    public List<Admin> getAdmin() {
        List<Admin> list = new ArrayList<Admin>();
        con.conecta();
        con.executaSQL("select*from tb_admin");
        try{
            con.rs.first();
            do{
                Admin admin = new Admin();
                admin.setId(con.rs.getInt("id"));
                admin.setNome(con.rs.getString("nome"));
                admin.setEmail(con.rs.getString("email"));
                admin.setSenha(con.rs.getString("senha"));
                list.add(admin);
            }while(con.rs.next());
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "Error fetching data!" + erro + ":(");
        }
        con.desconecta();
        return list;
    }
}

