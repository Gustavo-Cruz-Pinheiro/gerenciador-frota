package dao;
import model.Viagem;
import java.util.List;

public interface DaoViagem {
    public void salvar(Viagem viagem);
    public void alterar(int id);
    public void excluir(int id);
    public List<Viagem> getViagem();          
}