package dao;
import java.util.List;
import metier.entities.Groupe;

public interface IGroupeDao {
	public Groupe save(Groupe gp);
	public Groupe getGroupe(Long id);
	public Groupe updateGroupe(Groupe gp);
	public void deleteGroupe(Long id);
	public List<Groupe> getAllGroupe();

}
