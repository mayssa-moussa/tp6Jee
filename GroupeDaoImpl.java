package dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Groupe;
import util.JPAutil;

public class GroupeDaoImpl implements IGroupeDao {
	private EntityManager entityManager=JPAutil.getEntityManager("TP5_JEE_Etudiant");
	@Override
	public Groupe save(Groupe gp ) {
	EntityTransaction tx = entityManager.getTransaction();
	tx.begin();
	entityManager.persist(gp);
	tx.commit();
	return gp;
	}
	@Override
	public Groupe getGroupe(Long id) {
	return entityManager.find(Groupe.class, id);
	}
	@Override
	public Groupe updateGroupe(Groupe gp) {
	EntityTransaction tx = entityManager.getTransaction();
	tx.begin();
	entityManager.merge(gp);
	tx.commit();
	return gp;
	}
	@Override
	public void deleteGroupe(Long id) {
	Groupe groupe = entityManager.find(Groupe.class, id);
	entityManager.getTransaction().begin();
	entityManager.remove(groupe);
	entityManager.getTransaction().commit();
	}
	@Override
	public List<Groupe> getAllGroupe() {
	List<Groupe> cats =

	entityManager.createQuery("select g from Groupe g").getResultList();
	return cats;
	}
	}

