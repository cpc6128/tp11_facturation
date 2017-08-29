package fr.codevallee.formation.tp;

import static fr.codevallee.formation.tp.outils.JsonUtil.json;
import static spark.Spark.after;
import static spark.Spark.get;

import fr.codevallee.formation.tp.configuration.C;
import fr.codevallee.formation.tp.service.DemonstrationServiceImpl;
import spark.servlet.SparkApplication;

/**
 * Pour utiliser cette exemple, il faut appeler dans un premier temps l'adresse
 * suivante : <code>http://localhost:8080/demonstration</code> qui retourne la
 * facture créée au format JSON.<br>
 * Ensuite, on peut appeller l'adresse :
 * <code>http://localhost:8080/ajouterligne?id=28</code>. Cette adresse ajoute
 * une ligne de facture à la facture <br>
 * Il est utile d'installer l'extension JSON Formatter si vous avez Chrome.
 * 
 * @author ronan
 *
 */
public class Router implements SparkApplication {

	DemonstrationServiceImpl demonstrationServiceImpl = null;

	public void init() {

		demonstrationServiceImpl = C.i.getDemonstrationServiceImpl();

		after("/*", (request, response) -> {
			response.type("application/json");
		});

		get("/demonstration", (request, response) -> {
			return demonstrationServiceImpl.creerJeuDeDonneesExemple();
		}, json());

		get("/ajouterligne", (request, response) -> {
			// Ajouter une ligne à une facture identifiée par l'id :
			int id = Integer.valueOf(request.queryParams("id"));
			return demonstrationServiceImpl.ajouterLigneFacturationAFacture(id);
		}, json());

	}

}