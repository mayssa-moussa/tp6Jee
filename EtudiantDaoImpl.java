package dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import metier.entities.Etudiant;
import util.JPAutil;
public class EtudiantDaoImpl implements IEtudiantDao {
	private EntityManager entityManager=JPAutil.getEntityManager("TP5_JEE_Etudiants");
@Override
public Etudiant save(Etudiant e) {
	EntityTransaction tx = entityManager.getTransaction();
	tx.begin();
	entityManager.persist(e);
	tx.commit();
	return e;
}
@Override
public List<Etudiant> etudiantParMC(String mc) {
List<Etudiant> etuds =
entityManager.createQuery("select e from Etudiant e where e.nom like :mc").setParameter("mc", "%"+mc+"%").getResultList();

return etuds;
}
@Override
public Etudiant getEtudiant(Long id) {
return entityManager.find(Etudiant.class, id);
}
@Override
public Etudiant updateEtudiant(Etudiant e) {
EntityTransaction tx = entityManager.getTransaction();
tx.begin();
entityManager.merge(e);
tx.commit();
return e;
}
@Override
public void deleteEtudiant(Long id) {
Etudiant etud = entityManager.find(Etudiant.class, id);
entityManager.getTransaction().begin();
entityManager.remove(etud);
entityManager.getTransaction().commit();
}
}
