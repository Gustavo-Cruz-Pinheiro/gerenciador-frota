package dao;
import model.Veiculo;
import java.util.List;

public interface DaoVeiculo {
    public void salvar(Veiculo veiculo);
    public void alterar(int id);
    public void excluir(int id);
    public List<Veiculo> getVeiculo();          
}