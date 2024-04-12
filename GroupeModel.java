package web;
import java.util.ArrayList;
import java.util.List;
import metier.entities.Groupe;

public class GroupeModel {
	List<Groupe> groupes = new ArrayList<>();
	public List<Groupe> getGroupes() {
	return groupes;
	}
	public void setGroupes(List<Groupe> groupes) {
	this.groupes = groupes;
	}
}
