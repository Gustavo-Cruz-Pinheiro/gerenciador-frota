package Cliente;

import java.util.List;
import java.util.ArrayList;
import dao.DaoViagemImp;
import model.Viagem;
import model.Veiculo;
import dao.DaoVeiculoImp;
import javax.swing.JOptionPane;

public class Cliente {

    public static void main(String[] args) {
        int op;
        DaoViagemImp dav = new DaoViagemImp();
        DaoVeiculoImp dvi = new DaoVeiculoImp();
        List<Viagem> recebeViagem = new ArrayList<Viagem>();
        recebeViagem = dav.getViagem();
        List<Veiculo> recebeVei = new ArrayList<Veiculo>();
        recebeVei = dvi.getVeiculo();

        JOptionPane.showMessageDialog(null, "Welcome to our program! \nHere you will schedule your trip. \nLet's start?");

        do {
            op = Integer.parseInt(JOptionPane.showInputDialog("Choose an Option: \n1-Register a trip \n2-Update a trip \n3-Consult trips \n4-Delete a trip \n5-Exit \n\nType the number of your choice:"));
            switch (op) {
                case 1:
                    String nome = "";
                    float km = 0;
                    float newKm = 0;
                    String data = "";
                    float valor = 0;
                    int idVeiculo = 0;
                    int idChec = -1;

                    recebeVei = dvi.getVeiculo();

                    nome = JOptionPane.showInputDialog("Type your name: ");
                    do{
                       try{
                           km = Float.parseFloat(JOptionPane.showInputDialog("Type the amount of kilometers to the destination: "));
                       }catch(NumberFormatException erro){
                           JOptionPane.showMessageDialog(null, "Error" + erro);
                       }
                    }while(km==0);
                    
                    data = JOptionPane.showInputDialog("Type the trip date(dd/mm/aaaa): ");

                    JOptionPane.showMessageDialog(null, "Tt will be shown on the screen the company vehicles, analyze them and then choose one!");

                    do {
                        for (int i = 0; i < recebeVei.size(); i++) {
                            JOptionPane.showMessageDialog(null, "ID: " + recebeVei.get(i).getId()
                                    + "\nModel: " + recebeVei.get(i).getModelo()
                                    + "\nBrand: " + recebeVei.get(i).getMarca()
                                    + "\nPlate: " + recebeVei.get(i).getPlaca()
                                    + "\nColor: " + recebeVei.get(i).getCor()
                                    + "\nYear: " + recebeVei.get(i).getAno()
                                    + "\nKm: " + recebeVei.get(i).getKm()
                                    + "\nPrice: " + recebeVei.get(i).getPreco()
                            );
                        }
                        idVeiculo = Integer.parseInt(JOptionPane.showInputDialog("Enter the vehicle id: "));
                        for (int l = 0; l < recebeVei.size(); l++) {
                            if (recebeVei.get(l).getId() == idVeiculo) {
                                newKm = recebeVei.get(l).getKm() + km;
                                valor = recebeVei.get(l).getPreco() * km;
                                idChec = idVeiculo;
                            }
                        }
                        if (idChec != idVeiculo) {
                            JOptionPane.showMessageDialog(null, "Vehicle not found!");
                        }else{
                            JOptionPane.showMessageDialog(null, "Vehicle Found!");
                        }
                    } while (idChec != idVeiculo);
                    dvi.alteraKm(idVeiculo, newKm);
                    JOptionPane.showMessageDialog(null, "Trip total price: "+valor);
                    Viagem viagem = new Viagem(0, nome, km, data, valor, idVeiculo);
                    dav.salvar(viagem);

                    break;

                case 2:
                    int alt = 0;
                    try {
                        alt = Integer.parseInt(JOptionPane.showInputDialog("Type the trip id you want to update"));
                    } catch (NumberFormatException erro) {
                        JOptionPane.showMessageDialog(null, "Error" + erro);
                    }
                    dav.alterar(alt);
                    break;

                case 3:
                    recebeViagem = dav.getViagem();
                    for (int i = 0; i < recebeViagem.size(); i++) {
                        JOptionPane.showMessageDialog(null, "ID: " + recebeViagem.get(i).getId()
                                + "\nClient name: " + recebeViagem.get(i).getNome()
                                + "\nKm: " + recebeViagem.get(i).getKm()
                                + "\nDate: " + recebeViagem.get(i).getData()
                                + "\nValue: " + recebeViagem.get(i).getValor()
                                + "\nVehicle ID: " + recebeViagem.get(i).getIdVeiculo()
                        );
                    }
                    break;

                case 4:
                    int exc = 0;
                    try {
                        exc = Integer.parseInt(JOptionPane.showInputDialog("Type the trip id you want to delete"));
                    } catch (NumberFormatException erro) {
                        JOptionPane.showMessageDialog(null, "Error" + erro);
                    }
                    dav.excluir(exc);
                    break;

                case 5:
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option!");
                    break;
            }
        } while (op != 5);
    }
}
