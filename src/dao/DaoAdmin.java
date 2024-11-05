package dao;
import model.Admin;
import java.util.List;

public interface DaoAdmin {
    public void salvar(Admin admin);
    public void alterar(int id);
    public void excluir(int id);
    public List<Admin> getAdmin();
}
