package fr.codevallee.formation.tp.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import fr.codevallee.formation.tp.modele.Facture;

/**
 * Répository générique... Juste pour la démonstration !
 * 
 * @author ronan
 *
 */
@Repository
public class DemonstrationRepository {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Sauvegarde dans la base de données l'entity
	 * 
	 * @param entity
	 *            l'entité a sauvegarder
	 */
	public void save(Object entity) {
		entityManager.persist(entity);
	}

	/**
	 * Recupere depuis la base de données une facture depuis si id
	 * 
	 * @param id
	 *            l'id de la facture
	 * @return la facture chargée
	 */
	public Facture loadFactureById(int id) {
		return entityManager.find(Facture.class, id);
	}

}
