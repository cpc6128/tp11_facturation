package fr.codevallee.formation.tp.modele;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Facture {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Date date;

	@OneToMany
	private Set<LigneFacture> lignes;

	@ManyToOne(fetch = FetchType.EAGER)
	private Client client;

	private Statut statut;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Set<LigneFacture> getLignes() {
		return lignes;
	}

	public void setLignes(Set<LigneFacture> lignes) {
		this.lignes = lignes;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Integer getId() {
		return id;
	}

}
