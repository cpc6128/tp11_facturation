package fr.codevallee.formation.tp.outils;

import com.fasterxml.jackson.databind.ObjectMapper;

import spark.ResponseTransformer;

/**
 * http://www.mscharhag.com/java/building-rest-api-with-spark
 * 
 * @author ronan
 *
 */
public class JsonUtil {

	public static String toJson(Object object) throws Exception {
		return new ObjectMapper().writeValueAsString(object);
	}

	public static ResponseTransformer json() {
		return JsonUtil::toJson;
	}

}