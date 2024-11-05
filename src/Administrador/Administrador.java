package Administrador;
import java.util.List;
import java.util.ArrayList;
import model.Veiculo;
import model.Admin;
import dao.DaoVeiculoImp;
import dao.DaoAdminImp;
import javax.swing.JOptionPane;

public class Administrador {
    
    public static void main(String[] args) {
        String email;
        String senha;
        String senharec = "";
        int op_menu;
        int op;
        int op1;
        DaoAdminImp dai = new DaoAdminImp();
        DaoVeiculoImp dvi = new DaoVeiculoImp();
        
        do{
            List<Admin> recebeAdmin = new ArrayList<Admin>();
            recebeAdmin = dai.getAdmin();
            
            do{
                email = JOptionPane.showInputDialog("Type your email: ");
                if(email.equals("")){
                    JOptionPane.showMessageDialog(null, "Empty field!");
                }
            }while(email.equals(""));
            
            for(int i = 0; i<recebeAdmin.size(); i++){
                if(recebeAdmin.get(i).getEmail().equals(email)){
                    senharec = recebeAdmin.get(i).getSenha();
                }
            }
            
            do{
                senha = JOptionPane.showInputDialog("Type the password: ");
                if(senha.equals("")){
                    JOptionPane.showMessageDialog(null, "Empty field!");
                }
            }while(senha.equals(""));
            
            if(!senha.equals(senharec)){
                JOptionPane.showMessageDialog(null, "Incorrect password and/or non-existent e-mail!");
            }
        }while(!senha.equals(senharec));
        
        JOptionPane.showMessageDialog(null, "Welcome to the system!");
        
        do{
            op_menu = Integer.parseInt(JOptionPane.showInputDialog("Choose an Option: \n1-Administrator \n2-Vehicle \n3-Exit \n\n Type the number of your choice:"));
            switch(op_menu){
                case 1:
                    do{
                        op = Integer.parseInt(JOptionPane.showInputDialog("Choose an Option: \n1-Register \n2-Update \n3-Consult \n4-Delete \n5-Exit \n\n Type the number of your choice:"));
                        switch(op){
                            case 1:
                                Admin admin = new Admin(0, 
                                    JOptionPane.showInputDialog("Type the Admin name: "), 
                                    JOptionPane.showInputDialog("Type the Admin email: "), 
                                    JOptionPane.showInputDialog("Type the Admin password: ")
                                );
                                dai.salvar(admin);
                            break;
                            
                            case 2:
                                int alt = 0;
                                try{
                                     alt = Integer.parseInt(JOptionPane.showInputDialog("Type the admin id you want to update"));
                                }catch(NumberFormatException erro){
                                      JOptionPane.showMessageDialog(null, "Error"+erro);
                                }
                                dai.alterar(alt);
                            break;
                            
                            case 3:
                                List<Admin> recebeAdmin = new ArrayList<Admin>();
                                recebeAdmin = dai.getAdmin();
                                
                                recebeAdmin = dai.getAdmin();
                                for(int i = 0; i < recebeAdmin.size(); i++){
                                    JOptionPane.showMessageDialog(null,"ID: " + recebeAdmin.get(i).getId()+ 
                                            "\nName: " + recebeAdmin.get(i).getNome() + 
                                            "\nPassword: " + recebeAdmin.get(i).getSenha() +
                                            "\nE-mail: " + recebeAdmin.get(i).getEmail()
                                    );
                                }
                            break;
                            
                            case 4:
                                int exc = 0;
                                try{
                                     exc = Integer.parseInt(JOptionPane.showInputDialog("Type the admin id you want to delete"));
                                }catch(NumberFormatException erro){
                                      JOptionPane.showMessageDialog(null, "Error"+erro);
                                }
                                dai.excluir(exc);
                            break;
                            
                            case 5:
                            break;
                            
                            default:
                                JOptionPane.showMessageDialog(null, "Invalid option!");
                            break;
                        }
                    }while(op!=5);
                break;
                
                case 2:
                    do{
                        op1 = Integer.parseInt(JOptionPane.showInputDialog("Choose an option: \n1-Register \n2-Update \n3-Consult \n4-Delete \n5-Exit \n\nType the number of your choice:"));
                        switch(op1){
                            case 1:
                                int ano = 0;
                                float km = 0;
                                float preco = 0;
                                try{
                                ano = Integer.parseInt(JOptionPane.showInputDialog("Type the vehicle year: "));
                                }catch(NumberFormatException erro){
                                    JOptionPane.showMessageDialog(null, erro);
                                }
                                try{ 
                                km = Float.parseFloat(JOptionPane.showInputDialog("Type the amount of kilometers driven: "));
                                }catch(NumberFormatException erro){
                                    JOptionPane.showMessageDialog(null, erro);
                                }
                                try{
                                preco = Float.parseFloat(JOptionPane.showInputDialog("Type vehicle price per kilometers: "));
                                }catch(NumberFormatException erro){
                                    JOptionPane.showMessageDialog(null, erro);
                                }
                                Veiculo vei = new Veiculo(0, 
                                    JOptionPane.showInputDialog("Type the vehicle model: "), 
                                    JOptionPane.showInputDialog("Type the vehicle brand: "), 
                                    JOptionPane.showInputDialog("Type the vehicle plate: "),
                                    JOptionPane.showInputDialog("Type the vehicle color: "),
                                    ano,
                                    km,
                                    preco
                                );
                                dvi.salvar(vei);
                            break;
                            
                            case 2:
                                int alt = 0;
                                try{
                                     alt = Integer.parseInt(JOptionPane.showInputDialog("Type the vehicle id you want to update"));
                                }catch(NumberFormatException erro){
                                      JOptionPane.showMessageDialog(null, "Error"+erro);
                                }
                                dvi.alterar(alt);
                            break;
                            
                            case 3:
                                List<Veiculo> recebeVei = new ArrayList<Veiculo>();
                                recebeVei = dvi.getVeiculo();

                                for(int i = 0; i < recebeVei.size(); i++){
                                    JOptionPane.showMessageDialog(null,"ID: " + recebeVei.get(i).getId() 
                                        + "\nModel: " + recebeVei.get(i).getModelo()
                                        + "\nBrand: " + recebeVei.get(i).getMarca()
                                        + "\nPlate: " + recebeVei.get(i).getPlaca()
                                        + "\nColor: " + recebeVei.get(i).getCor()
                                        + "\nYear: " + recebeVei.get(i).getAno()
                                        + "\nKm: " + recebeVei.get(i).getKm()
                                        + "\nPrice: " + recebeVei.get(i).getPreco()
                                    );
                                }
                            break;
                            
                            case 4:
                                int exc = 0;
                                try{
                                     exc = Integer.parseInt(JOptionPane.showInputDialog("Type the id of the vehicle you want to delete"));
                                }catch(NumberFormatException erro){
                                      JOptionPane.showMessageDialog(null, "Error"+erro);
                                }
                                dvi.excluir(exc);
                            break;
                            
                            case 5:
                            break;
                            
                            default:
                                JOptionPane.showMessageDialog(null, "Invalid option!");
                            break;
                        }
                    }while(op1!=5);
                break;
                
                case 3:  
                break;
                
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option!");
                break;
            }
        }while(op_menu!=3);
    }
}

