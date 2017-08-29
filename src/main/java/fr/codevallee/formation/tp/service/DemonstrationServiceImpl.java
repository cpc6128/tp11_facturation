package fr.codevallee.formation.tp.service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.codevallee.formation.tp.modele.Adresse;
import fr.codevallee.formation.tp.modele.Article;
import fr.codevallee.formation.tp.modele.Client;
import fr.codevallee.formation.tp.modele.Description;
import fr.codevallee.formation.tp.modele.Facture;
import fr.codevallee.formation.tp.modele.LigneFacture;
import fr.codevallee.formation.tp.modele.Statut;
import fr.codevallee.formation.tp.repositories.DemonstrationRepository;

/**
 * Cette classe Service comporte des méthodes permettant de tester notre modèle
 * 
 * @author ronan
 *
 */
@Component
public class DemonstrationServiceImpl {

	@Autowired
	private DemonstrationRepository demonstrationRepository;

	/**
	 * Cette méthode permet de créer un jeu de données dans la BD
	 * 
	 * @return l'identifiant de la facture créée
	 */
	@Transactional
	public Facture creerJeuDeDonneesExemple() {

		// Création des articles :
		Description description = new Description();
		description.setDescription("Voici un exemple");

		demonstrationRepository.save(description);

		Article article = new Article();
		article.setDescription(description);
		article.setPrix(100);
		article.setReference("R001");

		demonstrationRepository.save(article);

		Article article2 = new Article();
		article2.setDescription(description); // Même description !
		article2.setPrix(300);
		article2.setReference("R002");

		demonstrationRepository.save(article2);

		Description description2 = new Description();
		description2.setDescription("Voici un exemple n°2");

		demonstrationRepository.save(description2);

		Article article3 = new Article();
		article3.setDescription(description2);
		article3.setPrix(90);
		article3.setReference("R003");

		demonstrationRepository.save(article3);

		// Création des adresses :

		Adresse adresseLivraison = new Adresse();
		adresseLivraison.setNumero(10);
		adresseLivraison.setVoie("avenue du codage");

		demonstrationRepository.save(adresseLivraison);

		Adresse adresseFacturation = new Adresse();
		adresseFacturation.setNumero(42);
		adresseFacturation.setVoie("avenue de la programmation");

		demonstrationRepository.save(adresseFacturation);

		Client client = new Client();
		client.setAdresseFacturation(adresseFacturation);
		client.setAdresseLivraison(adresseLivraison);
		client.setNom("Torvalds");
		client.setPrenom("Linus");

		demonstrationRepository.save(client);

		Facture facture = new Facture();
		facture.setDate(Calendar.getInstance().getTime());
		facture.setStatut(Statut.NON_PAYE);

		facture.setClient(client);

		// Ne marche pas :
		// Set<Facture> factures = new HashSet<>();
		// factures.add(facture);
		// client.setFactures(factures);

		demonstrationRepository.save(client);

		LigneFacture ligneFacture = new LigneFacture();
		ligneFacture.setArticle(article);
		ligneFacture.setQuantite(100);

		// Attention !
		Set<LigneFacture> lignes = facture.getLignes();
		if (lignes == null) {
			lignes = new HashSet<>();
			lignes.add(ligneFacture);
			facture.setLignes(lignes);
		} else {
			facture.getLignes().add(ligneFacture);
		}

		demonstrationRepository.save(ligneFacture);

		demonstrationRepository.save(facture);

		return facture;

	}

	/**
	 * Ajouter à une facture une ligne de facture
	 * 
	 * @param id
	 *            numéro de facture
	 */
	@Transactional
	public Facture ajouterLigneFacturationAFacture(int id) {

		Facture facture = demonstrationRepository.loadFactureById(id);

		Description description = new Description();
		description.setDescription("Voici un exemple");

		demonstrationRepository.save(description);
		System.out.println("sauvegarde de description");

		Article article = new Article();
		article.setDescription(description);
		article.setPrix(100);
		article.setReference("R " + Calendar.getInstance().getTime().toString());

		demonstrationRepository.save(article);
		System.out.println("sauvegarde de article");

		LigneFacture ligneFacture = new LigneFacture();
		ligneFacture.setArticle(article);
		ligneFacture.setQuantite(100);

		demonstrationRepository.save(ligneFacture);
		System.out.println("sauvegarde de ligneFacture");

		facture.getLignes().add(ligneFacture);

		demonstrationRepository.save(facture);
		System.out.println("sauvegarde de Facture");

		return facture;

	}

}
